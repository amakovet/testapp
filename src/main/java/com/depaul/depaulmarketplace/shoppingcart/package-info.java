package com.depaul.depaulmarketplace.shoppingcart;

/**
 * ShoppingCart Feature by Zining Wang 
 * 
 * tests have loading error 
 * but still write some for each service feature in a possible work way
 * 
 * Documentations on features that were not covered in class :
 * 
 * additional finders in ShoppingCartRepository
 * 
 * mappedBy attribute, to specify the CartItems are owned by ShoppingCart
 * 
 * cascade attribute
 * indicates all operations used on the ShoppingCart entiry will be cascaded to CartItem Entity
 * 
 * orphanRemoval = true
 * indicates if a cart item is deleted in a shopping cart, 
 * this cart item will also be deleted in cart item database
 */
