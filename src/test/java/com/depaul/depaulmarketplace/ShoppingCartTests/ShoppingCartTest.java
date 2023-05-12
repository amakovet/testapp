package com.depaul.depaulmarketplace.ShoppingCartTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.depaul.depaulmarketplace.shoppingcart.ShoppingCart;
import com.depaul.depaulmarketplace.shoppingcart.ShoppingCartRepository;

@DataJpaTest
@ActiveProfiles
public class ShoppingCartTest {
  /*
   * @Autowired
   * ShoppingCartRepository repo;
   * 
   * @Test
   * public void testCrud() {
   * assertEquals(3, repo.count());
   * 
   * var cart = new ShoppingCart();
   * cart.setId(333333);
   * cart.setTotal(435.77);
   * repo.save(cart);
   * assertEquals(4, repo.count());
   * 
   * var cart1 = repo.findById((long) 111111).orElse(new ShoppingCart());
   * repo.delete(cart1);
   * assertEquals(3, repo.count());
   * }
   */
}
