// simple to-do list JavaScript
// wait for the page to load
window.onload =init;
// declare a name-space
var ns = {};
    ns.todoInput  = {'value'   : 'to do',
                     'id'      : 'txtTodo',
                     'required': 'true'};
    ns.todoButton = {'value'   : 'ok',
                     'id'      : 'btnOK',
                     'type'    : 'button'};
    ns.markDone   = {'value'   : 'done',
                     'id'      : 'chkDone',
                     'type'    : 'checkbox'};
// page-load event call-back function
function init(){
// call the factory to create elements
    var n = makeElement('input', ns.todoInput);
// add the new element to the page
    main.appendChild(n);
// create a button with attributes
    var b = makeElement('input', ns.todoButton);
    main.appendChild(b);
    var d = makeElement('input', ns.markDone);
    main.appendChild(d);
    main.appendChild(makeElement('br'));
    d.addEventListener('click', handleCheck);
// add event listener to button
    b.addEventListener('click', init);
}; // end of 'init' method
// factory function
function makeElement(elementType, elementAttribute){
// console.log(elementAttribute['value']); // or .value
// create an element
var newElement =document.createElement(elementType);
// assign attributes by looping over the object
for (prop in elementAttribute){
    var propName = prop;
    var propData = elementAttribute[prop];
    console.log(propName + " " + propData);
    newElement.setAttribute(propName, propData);
}
// return this new element
    return newElement;
}; // end of 'makeElement' factory method

// event call-backs
function taskComplete(){
  //  btnOK.removeEventListener('click', taskComplete);
  /*  main.removeChild(txtTodo);
    main.removeChild(btnOK);
    main.removeChild(chkDone);*/
    init();
}
function handleCheck(){
    event.target.removeEventListener('click', handleCheck);
    txtTodo.setAttribute('class', 'done');
}







