// simple todo list for JavaScript
// wait for the page to load
window.onload = init;
var ns = {};
ns.todoInput = {
                'type': 'text',
                   'value': 'todo',
                   'id'  : 'txtTodo',
                   'required' : 'true'};
ns.todoButton = {
                 'type': 'button',
                   'value': 'todo',
                   'id'  : 'btnTodo',
                   'required' : 'true'};

ns.markDone =  {'type' : 'checkbox',
                'value' : 'done', 
                'id' : 'chkDone' }

// page load event callback
function init() {
    // declare an object containg required element attributes
    // call factory to create elements
    var input = makeElement('input',ns.todoInput);
    // add elements to the page
    main.appendChild(input);
    
    var btn = makeElement('input',ns.todoButton);
    // add elements to the page
    main.appendChild(btn);
    
    btn.addEventListener('click', init)
    
    var check = makeElement('input',ns.markDone);
    // add elements to the page
    main.appendChild(check);
    
    var br = document.createElement('br');
    main.appendChild(br);
    
    check.addEventListener('click',lineOutText);
    
    
} ; // end of init method.

function lineOutText(){
    var css = document.createElement("style");
    css.type = "text/css";
    css.innerHTML = "input[type=text] { text-decoration : line-through; }";
    document.body.appendChild(css);
}

// factory function
function makeElement(fieldType, element){
    console.log(element); 
    // create an element
    var newElement = document.createElement(fieldType);
    
    for(prop in element){
        var propName = prop;
        var propData = element[prop];
        
        var contains = (prop.indexOf('id') > -1); //true
        
        if(contains){
            var num = Math.floor((Math.random()*10)+1);
            var oldValue = element[prop];
            var newValue = oldValue + num;
            element[prop] = newValue;
        }
        
        console.log(propName +" "+ propData);
        newElement.setAttribute(propName, propData);
    }
    
    // set attribute
    //newElement.setAttribute(elementAttribute, 'todo');
    
    // return this new element
    return newElement;
    
} // end of factory method