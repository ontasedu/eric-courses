// use jQuery to wait for the page load event
$(function(){
	
	// set ajax defaults
	$.ajaxSetup(nsAjax);
	//Make the ajax setup	
	$.ajax(override);       // jquery will resoolve the right xhr object to use (microsoft or firefox)            // $.ajax(nsAjax) if the default ajaxSetup is not used.
	
}); //end $


var ns = {

		// methods

		handleSuccess  : function(result){
							// We know the data has loaded OK
							console.log(result);
							$(result).find('photos').children().each(ns.showThisImage);
						 },


		handleError    : function(e){
							// There has been an error
							console.log(e);
						 },
						
		handleComplete  : function(){
							console.log('All done!');
						 },
						
		showThisMessage : function(){
							console.log("raw: "+ this + "jQueryObj: "  + $(this));
							var node = $(this);  // make the element into a JavaScript object
							var filename = node.attr('filename') + '.jpg';
							var imageAlt = node.attr('category');
							var desc     = node.find('desc').text();
							
							ns.display(filename,imageAlt,desc);
						},
						
		display         : function (parFileName, parAlt, parDesc){
							//Create an in-memory instance of an image
							var nextImage = $('<img>');
							nextImage.attr({alt:parAlt,title:parDesc,src:'data/galery/'+parFilename});
							// place it on the page
							nextImage.appendTo($('#output')).hide().load(function(){$(this).fadeIn(5000);});
						}
						
};   // End of ns


var nsAjax = {
		url		 : "data/category.xml",
		type	 : "get",
		timeout	 : 3000,
		success  : ns.handleSuccess,
		error	 : ns.handleError,
		complete : ns.handleComplete

		
}; // end nsAjax

var override = {
		url      : "data/photos.xml",
};// end override namespace