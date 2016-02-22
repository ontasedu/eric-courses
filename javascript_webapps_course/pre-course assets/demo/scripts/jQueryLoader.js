// use jQuery to wait for the page load event
$(function(){
	// set ajax defaults
	$.ajaxSetup(nsAjax);
	// make an AJAX call
	$.ajax(override); // jQuery will resolve the right xhr object to use (Microsoft or Netscape)
});




var ns = {
		// methods
		handleSuccess : function(result){
							// we know the data has loaded ok
							console.log(result);
							$(result).find('photos').children().each(ns.showThisImage);
						},
		handleError : function (e){
							// if we get here, there has been an error
							console.log(e);	
						},
		handleComplete : function (){
							console.log('all done');	
						},
		showThisImage  : function (){
			console.log("raw: " + this + " jQueryObj: " + $(this));
			var node = $(this); // make the element into a JavaScript object
			var filename = node.attr('filename') + '.jpg';
			var imageAlt = node.attr('category');
			var desc     = node.find('desc').text();
			ns.display(filename, imageAlt, desc);
						},
		display		   : function(paramFilename, paramAlt, paramDesc){
			// create an in-memory instance of an image
			var nextImage = $('<img>');
			nextImage.attr({alt:paramAlt, title:paramDesc, src:'data/gallery/'+paramFilename});
			// place it on the page
			nextImage.appendTo('#output').hide().load(function(){$(this).fadeIn(5000);});
		}
}; // end of ns namespace

var nsAjax = {
		url		: 'data/categorylist.xml', 
		type	: 'get',
		timeout : 3000,
		success : ns.handleSuccess,
		error	: ns.handleError,
		complete: ns.handleComplete,
}; // end of nsAjax namespace

var override = {
		url		: 'data/photos.xml'
}; // end of override namespace



