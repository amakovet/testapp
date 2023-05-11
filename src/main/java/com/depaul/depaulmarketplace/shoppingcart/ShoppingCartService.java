package com.depaul.depaulmarketplace.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/db/shopping-cart")
public class ShoppingCartService{

    @Autowired
    private ShoppingCartRepository repo;

    @GetMapping("/")
    public List<ShoppingCart> getAllShoppingCarts() {
        return repo.findAll();
    }

    @PostMapping("/")
    public void saveShoppingCart(ShoppingCart shoppingCart) {
        repo.save(shoppingCart);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        repo.deleteById(id);
    }
}