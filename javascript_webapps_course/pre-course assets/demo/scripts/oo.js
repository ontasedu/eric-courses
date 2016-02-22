'use strict'
// exploring Object Oriented JavaScript

// helper function to check class
function classof(o){
	if(o === null) return "Null";
	if(o === undefined) return "Undefined";
	return Object.prototype.toString.call(o).slice(8, -1);
}
// test the method
console.log(classof(42));

// seal, freeze and preventExtentions
var s = {x:1,y:2};
//Object.freeze(s);
//s.x = 99;
// s.newProp = 'does this work?';
console.log(s); // undefined

// seal...
var p = Object.seal(Object.create(s)); //(Object.freeze(s)));
Object.preventExtensions(p);




console.log(JSON.stringify(p));
s.newProp = 'new';
console.log(p);













