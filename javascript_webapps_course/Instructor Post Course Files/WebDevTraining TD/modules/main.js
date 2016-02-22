// this is the entry-point for our project
require(['purchase'], function(purchase){
    // call methods within 'purchase'
    console.log('within main');
    purchase.purchaseProduct()
});