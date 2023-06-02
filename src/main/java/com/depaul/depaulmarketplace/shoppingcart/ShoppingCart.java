package com.depaul.depaulmarketplace.shoppingcart;

import com.depaul.depaulmarketplace.user.User;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // an arrayList that contains many Products user added
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    // cart total with tax exclude
    private double total;

    public void addCartItem(List<CartItem> items, CartItem cartItem) {
        items.add(cartItem);
        updateTotal();
    }

    public void removeCartItem(List<CartItem> items, CartItem cartItem) {
        items.remove(cartItem);
        updateTotal();
    }

    private void updateTotal() {
        double total = 0;
        for (CartItem item: cartItems) {
            total += item.getPricePerItem();
        }
        setTotal(total);
    }
}
