package com.depaul.depaulmarketplace.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.depaul.depaulmarketplace.products.ProductService;


@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartService{

    @Autowired
    private ShoppingCartRepository cartRepo;

    @Autowired
    private ProductService productServ;

    @Autowired
    private CartItemRepository cartItemRepo;

    @GetMapping("/")
    public List<ShoppingCart> getAllShoppingCarts() {
        return cartRepo.findAll();
    }

    @GetMapping("/{userId}")
    public ShoppingCart findCartByUserId(@PathVariable Long userId) {
        return cartRepo.findByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cartRepo.deleteById(id);
    }
}