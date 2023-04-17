package com.depaul.depaulmarketplace.shoppingcart;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Carts")
public class ShoppingCart {
    @Id
    // reference from userID
    private long id;

    /* 
     an arrayList that contains many Products user added, 
     will be available after the constructor of Products is completed 
    */
    // @Column(name = "cart")
    // private ArrayList<Products> cart;

    // cart total with tax exclude
    private double total;
}
