// this file is required by 'main.js'
define(['credits', 'products'], function(credits, products){
// call methods in credits and in products   
    console.log('within purchase')
    return {
        purchaseProduct: function(){
            var currentCredit = credits.getCredits();
            if(currentCredit > 0){
                products.reserveProduct();   
            }
            return true;
        }
    }
});