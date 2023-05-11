package com.depaul.depaulmarketplace.shoppingcart;

import com.depaul.depaulmarketplace.user.User;
import com.depaul.depaulmarketplace.products.Products;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Carts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    // an arrayList that contains many Products user added
    private ArrayList<Products> cart;

    // cart total with tax exclude
    private double total;
}
