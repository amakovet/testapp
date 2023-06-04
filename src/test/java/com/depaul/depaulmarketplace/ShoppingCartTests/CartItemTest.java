package com.depaul.depaulmarketplace.ShoppingCartTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.depaul.depaulmarketplace.products.Products;
import com.depaul.depaulmarketplace.shoppingcart.CartItem;
import com.depaul.depaulmarketplace.shoppingcart.CartItemRepository;
import com.depaul.depaulmarketplace.shoppingcart.ShoppingCart;
import com.depaul.depaulmarketplace.user.User;

@SpringBootTest
@ActiveProfiles("test")
public class CartItemTest {
  
    @Autowired
    private CartItemRepository cartItemRepository;
    /* 
    @Test
    public void testCartItemRepository() {
        Products product = new Products();
        product.setName("book");
        product.setInventory(10);
        product.setPrice(2.4);

        User user = new User();
        user.setFirstName("Z");
        user.setLastName("W");
        user.setEmail("123@gmail.com");
        user.setPassword("12345");

        ShoppingCart cart = new ShoppingCart();
        cart.setUser(user);
        cart.setTotal(0.0);

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(3);
        cartItem.setPricePerItem(cartItem.getQuantity() * product.getPrice());
        cartItem.setCart(cart);

        // test add
        var beforeAdd = cartItemRepository.count();
        cartItemRepository.save(cartItem);
        var afterAdd = cartItemRepository.count();
        assertEquals(afterAdd, beforeAdd + 1);

        // test find, not null for sure
        CartItem retrievedCartItem = cartItemRepository.findById(cartItem.getId()).orElse(null);
        assertNotNull(retrievedCartItem);

        // test delete
        cartItemRepository.deleteById(cartItem.getId());
        var afterDelete = cartItemRepository.count();
        assertEquals(0, afterDelete);
    }
    */

}

