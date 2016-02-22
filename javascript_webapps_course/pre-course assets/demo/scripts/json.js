var jsonInfo = Object.create(null);
    jsonInfo.url = 'https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=';
    jsonInfo.param = 'eire';
    jsonInfo.method = 'get';

var jsonView = Object.create(null);
    jsonView.decorate = function(paramUrl, paramAlt, paramTitle, paramTarget){
    	console.log(arguments);
    	if(arguments[1] == null){
    		paramAlt = 'default'; 
    	}
    	var nextImage = document.createElement('img');
            nextImage.setAttribute('src', paramUrl);
            nextImage.setAttribute('alt', paramAlt);
            nextImage.setAttribute('title', paramTitle);
		paramTarget.appendChild(nextImage);								
    };
    
window.onload = function(){
    var request = new XMLHttpRequest();
	// set listeners for the form
	document.getElementById('frmGo').addEventListener('click', doSearch);
	function doSearch(e){
		e.preventDefault(); // ... so the form does not get submitted
		
		var param = document.getElementById('frmTerm').value;
		var urlwithParam = jsonInfo.url + param;
		request.open(jsonInfo.method, urlwithParam);
		request.onreadystatechange = function(){
			if(request.readyState === 4 && request.status === 200){
				// all data loaded ok
				var out = document.getElementById('output');
				var resultObj = JSON.parse(request.responseText).responseData.results;
				// console.log(resultObj);
				// read each member of the result
				for (var prop in resultObj){
					var title = resultObj[prop].title;
					jsonView.decorate(resultObj[prop].url, null, title, out);
				}
			}
		};
	// make the request
	request.send();
	}
};