// simple todo list for JavaScript
// wait for the page to load
window.onload = init;
var ns = {};
ns.todoInput = {'type': 'input',
                   'value': 'todo',
                   'id'  : 'txtTodo',
                   'required' : 'true'};
ns.todoButton = {'type': 'button',
                   'value': 'todo',
                   'id'  : 'btnTodo',
                   'required' : 'true'};

// page load event callback
function init() {
    // declare an object containg required element attributes
    // call factory to create elements
    var n = makeElement(ns.todoInput);
    // add elements to the page
    main.appendChild(n);
    
    var n = makeElement(ns.todoButton);
    // add elements to the page
    main.appendChild(n);
    
} ; // end of init method.


// factory function
function makeElement(element){
    console.log(element); 
    // create an element
    var newElement = document.createElement(element.type);
    
    for(prop in element){
        var propName = prop;
        var propData = element[prop];
        console.log(propName +" "+ propData);
        newElement.setAttribute(propName, propData);
    }
    
    // set attribute
    //newElement.setAttribute(elementAttribute, 'todo');
    
    // return this new element
    return newElement;
    
} // end of factory method