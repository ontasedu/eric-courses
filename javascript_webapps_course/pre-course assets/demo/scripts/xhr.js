function getXML()
	{
		var req;
		try {
		    req = new XMLHttpRequest();
	} 
	catch (trymicrosoft) {
	    try {
	      req = new ActiveXObject("Msxml2.XMLHTTP");
	    } 
	    catch (othermicrosoft) {
	      try {
	        req = new ActiveXObject("Microsoft.XMLHTTP");
	      } 
	      catch (failed) {
	        req = null;
	      }
	    }
	}
	if (!req){
	  return null;
	} else {
	   return req;
	}
};