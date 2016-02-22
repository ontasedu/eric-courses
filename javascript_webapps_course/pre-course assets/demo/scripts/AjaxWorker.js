// this is a web worker who's purpose is to load from an array or URLs
// (synchronously)
onmessage = function(e){
	// find what data was sent to this worker
	var data = e.data; // in this case this will be an array of Urls
	// here is an array to store the results
	var contents = [];
	
	// now we iterate over the array of urls ...
	for (var i= 0; i < data.length; i++){
		// make a synchronous request to the server
		var url = data[i];
		var xhr = new XMLHttpRequest();
		xhr.open('GET', url, false); // false means work synchronously
		xhr.send();
		if(xhr.status !== 200){
			throw Error(xhr.status + " " + xhr.statusText + " " + url);
		}
		contents.push(xhr.responseText);
	} // end of for... loop
	
	
	// return the response
	postMessage(contents);
}