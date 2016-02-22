function init(){
	// create an instance of an HTTPRequest object
	var request = new XMLHttpRequest(); // IE8+
	request.open('get', 'data/photos.xml?param=22&otherParam=99');
	request.onreadystatechange = function(){
		// check the ready state
		if(request.readyState === 4 && request.status === 200){
			// we are confident all the data has loaded ok
            document.getElementById('output').innerHTML = request.responseXML;
			var result = request.responseXML.getElementsByTagName('photos')
			var images = result[0].getElementsByTagName('image');
		//	console.log(images[17]);
			
			var resultArray = request.responseXML.getElementsByTagName('image');
			
			// iterate over all the returned images
			var arrayLength = resultArray.length;
			var outputLocation = document.getElementById('output');
			for (var i in resultArray){
				var nextTag = document.createElement('img');
				nextTag.setAttribute('src', 'data/gallery/' + resultArray[i].getAttribute('filename') + '.jpg');
				outputLocation.appendChild(nextTag);
			}
			// console.log(resultArray[17].getAttribute('filename'));
		}
	};
	request.send(); // here the request is actually made
}
window.onload = init; // NB no braces, we are just setting the event listener