/**
 * SCHEME ELEMENTS
 */


const dark_colors = {
    transparent: "#0000",
    background: "#1b1b1c",
    mid_dark: "#202023",
    mid_light: "#7f807f",
    foreground: "#dddcdd",
}

const dark_icons = {
    user: "./assets/light/user.png",
    point_down: "./assets/light/point_down.png"

}

const dark_scheme = {
    colors: dark_colors,
    icons: dark_icons
}
const scheme = dark_scheme;

/**
 * UI ELEMENTS
 */


// ++++++  NAVBAR  ++++++

let navbar = {
    id: "navbar",
    style: `
    background-color: ` + scheme.colors.mid_dark + `;
    width: 100%;
    height: 64px;
    `,
    text: "Your Timeline",
    render: function() { 
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`">
        ` + title.render(this.text) + `
        ` +  auth.render() + `
        </div>
        `;
    }
}

let auth = {
    id: "auth-login",
    style: `
    background-color: ` + scheme.colors.mid_light + `;
    width: 64px;
    height: 64px;   
    border-radius:7.5% 0% 0% 7.5%;
    position: absolute;
    top: 0;
    right: 0;
    cursor: pointer;
    `,
    onClick: function click() {
        console.log("Clicked");
    },
    render: function() { 
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`" onclick="` + this.onClick() + `">
        ` + navicon.render(scheme.icons.user, "user icon") + `
        </div>
        `;
    }
}

let navicon = {
    id: "icon",
    style: `
    image-rendering: pixelated;
    padding: 4px;
    width: 56px;
    height: 56px;
    `,
    render: function(src, alt) {
        return `
        <img src="` + src + `" style="` + styleFormat(this.style) +`" alt="` + alt + `"/>
        `;
    }
}

let title = {
    id: "text-title",
    style: `
    font-family: 'DotGothic16', sans-serif;
    text-align: left;
    font-size: 32px;
    height: 64px;
    display: flex;
    align-items: center;
    padding-left: 16px;
    color: ` + scheme.colors.foreground + `;
    `,
    render: function(text) { 
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`">
        ` + text + `
        </div>
        `;
    }
}



/**
 * FUNCTIONS
 */
var loaded = false;

function styleFormat(style){
    const str = style.replace(/\s/g, '');
    const regex = /calc\([^)]*\)/g;
    const result = str.replace(regex, (match) => {
        return match.replace(/-/g, " - ");
    }); 
    return result;
}

/*
 From http://jaketrent.com/post/addremove-classes-raw-javascript/
 */
function hasClass(ele,cls) {
    return !!ele.className.match(new RegExp('(\\s|^)'+cls+'(\\s|$)'));
}
  
function addClass(ele,cls) {
    if (!hasClass(ele,cls)) ele.className += " "+cls;
}
  
function removeClass(ele,cls) {
    if (hasClass(ele,cls)) {
        var reg = new RegExp('(\\s|^)'+cls+'(\\s|$)');
        ele.className=ele.className.replace(reg,' ');
    }
}