package com.depaul.depaulmarketplace.shoppingcart;

import com.depaul.depaulmarketplace.products.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CartItems")
public class CartItem {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ShoppingCart cart;

    @ManyToOne
    private Products product;

    private int quantity;

    private double pricePerItem;
}
