// declare some variables in the global namespace
var x = 1;
x.someProp = 'impossible';
// console.log (x.someProp);
var y = 2;
var myArray = new Array();
myArray[3] = true;
myArray[9] = "hello";
var nextArray = [0,1,2,3,4,,,7,];
myArray[9] = Number.MAX_VALUE; 


// here is a namespace
var ns = new Object();
ns.account = x;
ns.id = y;
ns.complex = {};
ns.cleverFn = function(){
	console.log(this);
};

var otherNS = {x:1, y:2, bool:false, ident:'xyz', myFn:function(){}};

// ns.cleverFn();
// function as a namespace
function myNS(){
	var x = 9, y = 8;
}

// declare some global functions
function doStuff(){
	
};

var otherFn = function(){
	
};

// ECMAScript 5 objects (the above is all ECMAScript 3)
var e5 = Object.create(otherNS);
e5.x = 100;
otherNS.x = 50;
console.log(otherNS);
console.log(e5);









