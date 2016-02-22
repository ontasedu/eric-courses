// loading external data via AJAX
// wait for the page to load
window.onload = function(){
    // make an AJAX call to load 'categories.xml'
    var xhr2 = new XMLHttpRequest();
        xhr2.open('get', '/data/categories.xml');
    var outputlocation = document.getElementById('frmCategory');
    // set form event listener
    
    // set xhr event listener
    xhr2.onreadystatechange = function(){
        // check for readystate 4
        if(xhr2.readyState == 4 && xhr2.status == 200){
            console.log(xhr2.response);
            var nodeArray = xhr2.responseXML.getElementsByTagName('category');
            var numItems = nodeArray.length-1;
            // loop over the array of data
            for (var i = 0; i<numItems; i++){
                console.log(nodeArray[i]);
                var nextValue  = nodeArray[i].firstChild.nodeValue;
                var nextOption = document.createElement('option');
                    nextOption.innerHTML = nextValue;
                    outputlocation.appendChild(nextOption);
            } // end of 'for' loop
        } // end of 'if'
    } // end of 'onreadystatechange' event handler
// here is where we make the request...
xhr2.send(); // we can choose to insert paramters here
updateOutput();
} // end of 'window.onload' event handler

function updateOutput(){
var outputlocation = document.getElementById('output');
    outputlocation.innerHTML = ""; // clear any previous images
var selectedCategory = document.getElementById('frmCategory').value;
console.log(selectedCategory);
// create an XMLHTTPRequest object
var xhr = new XMLHttpRequest();
// specify where to load data from    
    xhr.open('get', '/data/photos.xml');
// set event listeners
    xhr.onreadystatechange = function(){
        // check for readystate 4
        if(xhr.readyState == 4 && xhr.status == 200){
            // we can be certain that all the data has arrived
            // place returned data on the page: response, responseText or responseXML
            // document.getElementById('output').innerHTML = xhr.responseXML;
            // extract all the nodes from the data
            var nodeArray = xhr.responseXML.getElementsByTagName('image');
            // console.log(nodeArray);
            var numItems = nodeArray.length-1;
            // loop over the array of data
            for (var i = 0; i<numItems; i++){
                var stringBuilder = '/data/gallery/' + nodeArray[i].getAttribute('filename') + '.jpg';
            //    console.log(nodeArray[i].getAttribute('filename')); // this returns XML nodes
                console.log(stringBuilder);
                // place this image on the page
                var nextImage  = document.createElement('img');
                    nextImage.setAttribute('src', stringBuilder);
                // set the ALT text
var descText = nodeArray[i].getElementsByTagName('desc')[0].firstChild.nodeValue;
                    nextImage.setAttribute('alt', descText);
                // ... and the TITLE
                var titleText = nodeArray[i].getAttribute('category');
                    nextImage.setAttribute('title', titleText);
                // only show values matching chosen category
                if(selectedCategory == 'All'){
                    outputlocation.appendChild(nextImage);
                } else if(titleText == selectedCategory){
                    outputlocation.appendChild(nextImage);
                } // end of if
            } // end of 'for' loop
        } // end of 'if'
    } // end of 'onreadystatechange' event handler
// here is where we actually make the request...
xhr.send();
} // end of updateOutput function   

