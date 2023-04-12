document.getElementById('main').innerHTML += navbar.render();
document.getElementById('main').innerHTML += doomscroll.render();
window.onload = function() {
    renderImages();
  };
  

function renderImages(){
    console.log("renderImages() called");
    // Get the element you want to observe
    const targetElement = document.querySelectorAll(".thumb");
    console.log(targetElement);

    // Create a new intersection observer
    const observer = new IntersectionObserver(entries => {
    entries.forEach(entry => {
        // If the element is visible
        if (entry.isIntersecting) {
            console.log(entry);
            // Call your function here
            executeAsyncImgLoad(function() {
                setBackgroundImg(
                    entry.target.id
                );
            });
        }
    });
    });
    targetElement.forEach((element) => {
        observer.observe(element)
    });

    // Get the height of the content
    const contentHeight = document.querySelector('#main').scrollHeight;

    // Set the height of the page to match the height of the content
    document.body.style.height = `${contentHeight}px`;
}



function setBackgroundImg(b64String){
    let element = document.querySelector('.square');
    removeClass(element, "square");
    // Base64 string of the JPEG image
    var b64String = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCABAAEADASIAAhEBAxEB/8QAGwAAAgMBAQEAAAAAAAAAAAAABQcDBAYCAQD/xAA2EAACAgEDAgQEAwUJAAAAAAABAgMEBQAREgYhEzFRcRQiQWGBkaEVMlKxwQcWIzNCYoKS0f/EABoBAAIDAQEAAAAAAAAAAAAAAAMEAQIFBgD/xAAoEQACAgIBAgUEAwAAAAAAAAABAgADESEEEyISMUGB8AUykeFRcdH/2gAMAwEAAhEDEQA/ANxNnMlcPKnBHKgG6oCw4n32HbQ3N3c49RF+DevLx7kMP0O+sR0V1pPhrUcl0SzU27uFO7b+vfTmrdR4HPotd5V4yLuFlHH9fL9dBHKrVgCMShodlPdE5YxdyZTMbCmUnkFmQBt/f66t4LB5O3ehfGTyLYUEsW+VVb7Hy02Mt0vjzTWVKsLMh35uT5fhtudDYJZajF4kRG225hPLTNnMVRoRdeIxOzMpPjOoMXkEsZMQKSQQfEB5H17aLxCaw/Fo+ERO7IsYG7eo9dFHo2slKHlkMg/iPkNaShjYKsKvOVG3ludhvpN7uroDcaSnp+s4wdNo63J+Q3/0ntoH111EmEiprXnZLDSEBULD6fXbtt9jqXL9Sype+CxkfLwm5SMoJBA+ntpQdXNabqByDKsUreIYzJwQn/bvuP66hqiteZDXAnAh7CYWnbp8Yau1NV8MvKO5cdjt9f5ahs05MFCzRwpcpI52ViVaM/Y+n56L17FyvXQCJ27bkbbAn8NTvkKeQjMEkfhuf3lkX+o1zNvXrtywyp9/3maiMjLo4M8wv9plaLHR0chDxjDcOfLcqPuNu+tnDHWtxLJDOPhWHJXJ23GlDk+kpFnHBt60hJRgN+J9Dt9PvrmhlOoMHLBUEs4qlzxQtvG3qNa1N9digBtRZ1ZTsR1QX6cEorU1WUDzfmAu/wBvU6C59YM3VDWjOHjbZYY22U+/roZA9M0VvS5OlXXbco7nmD6cdu+q8WdqzzyxQzEsnfmw7MPtrSRRrpmJs2u+HIof2VinfGUZAz9pHjPKZR9SvLz9tJ7qlMhP1F4+QjuThmAj+KqiFnHoF2I/LW/ymVdqytHl2qSgERuzcYyfRux7aTuUyFq1mJBNe8Rw/nE5b/qT320K9HBw0r1FI7Zoq/UHU0UYUYfK/L5EQOR+W2pbOZyNpVNnCZQSjuJEpuCp/LRirb6khjXfJqvbcgxqSP01Haz3UzMIq913f6nggA/TSbmvPp+Z5OQ5G4Nq5zMQn5aWRA37iSk5De446vDN2p2LDFX60v8AHDXkCN7qRohBN1Nxja9lvBjJ8goLN7DXtrqC6pWGtkmjZjssk0g3J+ygaSZ+KzaGf6+bhxZbiDXys+Qi8C/h8i7IflnjqMrj8dtj+O3vqSG/8JUWL+7t6xIoIWQxyJ5+uw/kdGW6iy9DFBzfs3pGP+ZHDxjT/lx76tYnqmeWiIrE9uS0wYsV2HD0+mm+NYuB0wcZ+e0rZk/djMzSXcjYjaJ8HaWJxsw4Pvt9jtrCZSVo77QVkSFY2PF5AAwPpvvvpl57PZ2lB8TXvzGMAgpJIib/ANdLCXJ0JJ5JbonksE8t3IYcvXfzI9taN9xftxkxGtPDnEbVPpsTxRzzXZnicA9jtuNW78VKhS8WBCvlxKgaAVs7nK1R2iw9hQz7JE4Py+wOh93IWNg2duPAzrvHHWUHgfRux/TXFDi8m6zLtr+P0Jt5pVcKNyG3ZuvP4ptSlj8qjiDwH27avUOhslMIchYnKRtuxZmHID2A+uruJ/ZfwFe6TbsPG3z+MFWNvbcdx76LydS3K9lDGq8W/d4spX289aSk16XHz2g/AvmZdo2sikSVIbTtXA4iMopXb8Rr44qIGxLGvGdwQeOwBO3kPTUVvLRuRbQTwTeR8Ers5+4J2/HQ5clG2NniKs0hJJaSwFYfftvvow5SkBgce0r0wNHcUPUPxFe/LE8tjxgSGQ79jv664w9F7sjSS+HFGo7tIwAJ9vM6ZSY5JpBYWWpIsS9viOUh9hy/81zFgKeXcnJmwGB3/wABY0RR+BH8tFb6ioUqF/38RVqj5Yn/2Q==";
    const base64String = b64String;
    if(element.id = b64String){
        console.log(element.id);

        // Create a new image object
        const img = new Image();

        // Set the src attribute to the Base64 string
        img.src = base64String;
        // Set the background-image property of the element once the image has loaded
        img.onload = function() {
            element.style.backgroundImage = `url(${img.src})`;
        };

        element.style.backgroundRepeat = "no-repeat";
        element.style.backgroundPosition = "center center";
    }

}

function executeAsyncImgLoad(func) {
    setTimeout(func, 0);
}