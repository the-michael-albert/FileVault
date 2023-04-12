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
    width: calc(100% - 8px);
    height:  calc(100% - 8px);
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

// ++++++  DOOMSCROLL  ++++++

let doomscroll = {
    id: "doomscroll",
    style: `
    background-color: ` + scheme.colors.background + `;
    width: parent;
    height: calc(100% - 64px - 32px);
    padding: 16px;
    `,
    render: function() {
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`">
        ` + post_body.render("Today") + `
        </div>
        `;
    }
}

// ++++++  POST  ++++++

let post_body = {
    id: "post",
    style: `
    background-color: ` + scheme.colors.transparent + `;
    width: 100%;
    `,
    render: function(title) {
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`">
        ` + post_title.render(title) + `
        ` + post_icon.render(scheme.icons.point_down) + `<br>
        ` + post_table.render() + `
        </div>
        `;
    }
}

// background: url(data:image/jpeg;base64,YourBase64StringHere) no-repeat center center fixed;


let post_table = {
    id: "post-table",
    style: `
    background-color: ` + scheme.colors.transparent + `;
    width: 100%;
    padding: 32px;
    padding-top: 0px;
    `,
    posts: function (){
        return [
            {
                id: "post-1",
            },
            {
                id: "post-2",
            },
            {
                id: "post-3",
            },
            {
                id: "post-4",
            },
            {
                id: "post-5",
            },
            {
                id: "post-6",
            },
            {
                id: "post-7",
            },
            {
                id: "post-8",
            }
        ];
    },
    render: function() {
        let post_body = "";
        for (let i = 0; i < this.posts().length; i++) {
            const elem = this.posts()[i];
            post_body += post_image.render(elem.id);
        }
        return `
        <div id="` + this.id + `" style="` + styleFormat(this.style) +`">
        ` + post_body + `
        </div>
        `;
    }   
}

let post_image = {
    id: "post_image_",
    style: `
    width: 64px;
    aspect-ratio: 1 / 1;
    border-radius: 12.5%;
    display: inline-block;

    `,
    render: function(resID) {
        return `
        <div id="` + this.id + resID + `" class="square thumb" style="` + styleFormat(this.style) +`"></div>
        `;
    }
}

let post_icon = {
    id: "icon",
    style: `
    image-rendering: pixelated;
    padding: 0px;
    height:  16px;
    display: inline-block;
    
    `,
    render: function(src, alt) {
        return `
        <img src="` + src + `" style="` + styleFormat(this.style) +`" alt="` + alt + `"/>
        `;
    }
}

let post_title = {
    id: "text-title",
    style: `
    font-family: 'DotGothic16', sans-serif;
    text-align: left;
    font-size: 32px;
    height: 64px;
    display: inline-block;
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