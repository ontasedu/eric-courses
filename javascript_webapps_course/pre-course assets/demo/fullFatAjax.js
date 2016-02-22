// demo showing switch between older Microsoft ActiveXObject AJAX objects
// and newer XMLHttpRequest object

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
}
function filter(month, year)
{


if(getXML()){
var xmlhttp = getXML();
xmlhttp.onreadystatechange=function()
{
   if (xmlhttp.readyState==4 && xmlhttp.status==200)
   {
	   document.getElementById("output").innerHTML=xmlhttp.responseText;
   }
};
xmlhttp.open("GET","ajax/filterOutput.php?m="+month+"&y="+year,true);
xmlhttp.send();
} else {
	alert("Error initializing XMLHttpRequest!");
}
}