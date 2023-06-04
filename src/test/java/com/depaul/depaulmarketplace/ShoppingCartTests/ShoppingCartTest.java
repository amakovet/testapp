package com.depaul.depaulmarketplace.ShoppingCartTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.depaul.depaulmarketplace.products.Products;
import com.depaul.depaulmarketplace.products.ProductsRepository;
import com.depaul.depaulmarketplace.search.dao.ItemRepo;
import com.depaul.depaulmarketplace.shoppingcart.CartItem;
import com.depaul.depaulmarketplace.shoppingcart.CartItemRepository;
import com.depaul.depaulmarketplace.shoppingcart.ShoppingCart;
import com.depaul.depaulmarketplace.shoppingcart.ShoppingCartRepository;
import com.depaul.depaulmarketplace.user.User;
import com.depaul.depaulmarketplace.user.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartTest {
    /* 
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ShoppingCartRepository cartRepo;

    @Autowired
    private ProductsRepository productRepo;

    @Autowired
    private CartItemRepository itemRepo;

    // Test to get all shopping carts
    @Test
    public void testGetAllShoppingCarts() throws Exception {
      ShoppingCart cart1 = new ShoppingCart();
      cart1.setUser(createUser1());
      ShoppingCart cart2 = new ShoppingCart();
      cart2.setUser(createUser2());
      cartRepo.save(cart1);
      cartRepo.save(cart2);

      mockMvc.perform(MockMvcRequestBuilders.get("/api/shopping-cart/"))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2))); 

      cartRepo.delete(cart1);
    }

    // Test to get cart by user ID
    @Test
    public void testGetCartByUserId() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);
      ShoppingCart cart = new ShoppingCart();
      cart.setUser(user1);
      cartRepo.save(cart);

      int ExpectedId = cart.getUser().getId().intValue();
      mockMvc.perform(MockMvcRequestBuilders.get("/api/shopping-cart/{userId}", userId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.user.id", Matchers.is(ExpectedId)));

      userRepo.delete(user1);
      cartRepo.delete(cart);
    }

    // Test to get cart total by user ID
    @Test
    public void testGetCartTotalByUserId() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);
      ShoppingCart cart = new ShoppingCart();
      cart.setUser(user1);
      cart.setTotal(9.9);
      cartRepo.save(cart);
        
      mockMvc.perform(MockMvcRequestBuilders.get("/api/shopping-cart/{userId}/total", userId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("9.9")); 
      
      userRepo.delete(user1);
      cartRepo.delete(cart);
    }

    // Test to add a product to the cart
    @Test
    public void testAddProductToCart() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);

      Products product1 = createProduct1();
      Long productId = product1.getId();
      productRepo.save(product1);
      int quantity = 2; 

      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/add-product/{productId}", userId, productId)
      .param("quantity", String.valueOf(quantity)))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("Product added to cart successfully"));

      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/add-product/{productId}", userId, productId)
      .param("quantity", String.valueOf(999)))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andExpect(MockMvcResultMatchers.content().string("Product quantity exceeds inventory"));

      userRepo.delete(user1);
      productRepo.delete(product1);
    }

    // Test to remove a cart item from the cart
    @Test
    public void testRemoveCartItemFromCart() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);

      Products product1 = createProduct1();
      productRepo.save(product1);
      
      CartItem item = createCartItemInCart();
      Long cartItemId = item.getId();
      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/remove-cart-item/{cartItemId}", userId, cartItemId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("Product removed from cart successfully"));

      // remove item that is not in the cart
      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/remove-cart-item/{cartItemId}", userId, (long)111))
      .andExpect(MockMvcResultMatchers.status().isBadRequest())
      .andExpect(MockMvcResultMatchers.content().string("This item is not in the cart"));

      userRepo.delete(user1);
      productRepo.delete(product1);
      itemRepo.delete(item);
    }

    // Test to edit the quantity of a cart item
    @Test
    public void testEditQuantity() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);

      CartItem item = createCartItemInCart();
      Long cartItemId = item.getId();
      int newQuantity = 1; // old is 2

      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/edit-quantity", userId)
      .param("cartItemId", String.valueOf(cartItemId))
      .param("newQuantity", String.valueOf(newQuantity)))
      .andExpect(MockMvcResultMatchers.status().isOk());
      assertEquals(1, item.getQuantity());

      userRepo.delete(user1);
      itemRepo.delete(item);
    }

    // Test to checkout the shopping cart
    @Test
    public void testCheckout() throws Exception {
      User user1 = createUser1();
      Long userId = user1.getId(); 
      userRepo.save(user1);
      ShoppingCart cart = new ShoppingCart();
      cart.setUser(user1);
      cart.setTotal(9.9);
      cartRepo.save(cart);

      mockMvc.perform(MockMvcRequestBuilders.post("/api/shopping-cart/{userId}/checkout", userId))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string(startsWith("You have checked out successfully.")));

      userRepo.delete(user1);
      cartRepo.delete(cart);
    }

    // Test to delete a shopping cart
    @Test
    public void testDeleteById() throws Exception {
      ShoppingCart cart = new ShoppingCart();
      cart.setUser(createUser1());
      cart.setTotal(9.9);
      cartRepo.save(cart);
      Long cartId = cart.getId(); 

      mockMvc.perform(MockMvcRequestBuilders.delete("/api/shopping-cart/{id}", cartId))
      .andExpect(MockMvcResultMatchers.status().isOk());

      cartRepo.delete(cart);
    }

    public User createUser1() {
      User user = new User();
      user.setFirstName("Z");
      user.setLastName("W");
      user.setEmail("123@gmail.com");
      user.setPassword("12345");
      return user;
    }
    public User createUser2() {
      User user = new User();
      user.setFirstName("N");
      user.setLastName("M");
      user.setEmail("234@gmail.com");
      user.setPassword("23456");
      return user;
    }

    public Products createProduct1(){
      Products product = new Products();
      product.setName("book");
      product.setInventory(10);
      product.setPrice(2.4);
      return product;
    }
    
    public Products createProduct2(){
      Products product = new Products();
      product.setName("water");
      product.setInventory(100);
      product.setPrice(0.99);
      return product;
    }

    public CartItem createCartItemInCart() {
      ShoppingCart cart1 = new ShoppingCart();
      cart1.setUser(createUser1());
      CartItem item = new CartItem();
      Products product1 = createProduct1();
      item.setProduct(product1);
      item.setQuantity(2);
      item.setPricePerItem(2 * product1.getPrice());
      List<CartItem> items = new ArrayList<>();
      items.add(item);
      cart1.setCartItems(items);
      item.setCart(cart1);
      cartRepo.save(cart1);
      itemRepo.save(item);
      return item;
    }
    */
}

