package com.depaul.depaulmarketplace.shoppingcart;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}