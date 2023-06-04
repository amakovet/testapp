package com.depaul.depaulmarketplace.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.depaul.depaulmarketplace.products.Products;
import com.depaul.depaulmarketplace.products.ProductsRepository;
import com.depaul.depaulmarketplace.user.UserRepository;

import lombok.extern.log4j.Log4j2;

import com.depaul.depaulmarketplace.user.User;


@RestController
@RequestMapping("/api/shopping-cart")
@Log4j2
public class ShoppingCartService{

    @Autowired
    private ShoppingCartRepository cartRepo;

    @Autowired
    private ProductsRepository productRepo;

    @Autowired
    private CartItemRepository itemRepo;

    @Autowired 
    private UserRepository userRepo;

    @GetMapping("/")
    public List<ShoppingCart> getAllShoppingCarts() {
        log.traceEntry("Enter getAllShoppingCarts");
        log.traceEntry("Leave getAllShoppingCarts");
        return cartRepo.findAll();
    }

    @GetMapping("/{userId}")
    public ShoppingCart getCartByUserId(@PathVariable Long userId) {
        log.traceEntry("Enter getCartByUserId", userId);
        User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
        }
        log.traceEntry("Leave getCartByUserId", userId);
        return cart;
    }

    @GetMapping("/{userId}/total")
    public double getCartTotalByUserId(@PathVariable Long userId) {
        log.traceEntry("Enter getCartTotalByUserId", userId);
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        // get shopping cart
        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            return 0.0;
        }
        log.traceEntry("Leave getCartTotalByUserId", userId);
        return cart.getTotal();
    }

    @PostMapping("/{userId}/add-product/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam("quantity") int quantity) {
        log.traceEntry("Enter addProductToCart", userId, productId, quantity);
        // get user
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        // get product
        Products product = productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with ID " + productId + " not found"));

        // bad request if there is no inventory for request product
        if (product.getInventory() <= 0) {
            return ResponseEntity.badRequest().body("Product is out of stock");
        }

        // get shopping cart
        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
        }

        // get all cartItems in cart
        List<CartItem> cartItems = cart.getCartItems();
        CartItem item = new CartItem();
        // if cart is empty, create new cartItem and add to it
        if (cartItems.size() == 0) {
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setPricePerItem(product.getPrice() * quantity);
            item.setCart(cart);
            cart.addCartItem(cartItems, item);
        }

        // if cart is not empty, get the item
        for (CartItem i: cartItems) {
            if (i.getProduct().getId() == productId) {
                item = i;
            }
        }

        // calculate new quantity, if it's larger than inventory, bad request
        int newQuantity = item.getQuantity() + quantity;
        if (newQuantity > product.getInventory()) {
            return ResponseEntity.badRequest().body("Product quantity exceeds inventory");
        }

        // set new quantity for cartItem, and update inventory
        item.setQuantity(newQuantity);
        item.setPricePerItem(newQuantity * product.getPrice());
        product.setInventory(product.getInventory() - newQuantity);

        // save everything
        itemRepo.save(item);
        cartRepo.save(cart);
        productRepo.save(product);

        log.traceEntry("Leave addProductToCart", userId, productId, quantity);
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @PostMapping("/{userId}/remove-cart-item/{cartItemId}")
    public ResponseEntity<String> removeCartItemFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
        log.traceEntry("Enter removeCartItemFromCart", userId, cartItemId);
        // get user
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        // get shopping cart
        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
        }

        // get all cartItems in cart, find the target item
        List<CartItem> cartItems = cart.getCartItems();
        CartItem item = null;
        for (CartItem i: cartItems) {
            if (i.getId() == cartItemId) {
                item = i;
            }
        }
        if (item == null) {
            return ResponseEntity.badRequest().body("This item is not in the cart"); 
        }

        // get product, add inventory back, save
        Products product = productRepo.findById(item.getProduct().getId()).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        product.setInventory(product.getInventory() + item.getQuantity());
        productRepo.save(product);

        // delete target item from cart, save everything
        cart.removeCartItem(cartItems, item);
        itemRepo.delete(item);
        cart.setCartItems(cartItems);
        cartRepo.save(cart);

        log.traceEntry("Leave removeCartItemFromCart", userId, cartItemId);
        return ResponseEntity.ok("Product removed from cart successfully");
    }

    @PostMapping("/{userId}/edit-quantity")
    public ShoppingCart editQuantity(@PathVariable Long userId, @RequestParam("cartItemId") Long cartItemId, @RequestParam("newQuantity") int newQuantity) {
        log.traceEntry("Enter editQuantity", userId, cartItemId, newQuantity);
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        // get shopping cart
        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            throw new ResourceNotFoundException("Please add product to the cart before editing");
        }

        // get all cartItems in cart, find the target item
        List<CartItem> cartItems = cart.getCartItems();
        CartItem item = null;
        for (CartItem i: cartItems) {
            if (i.getId() == cartItemId) {
                item = i;
            }
        }
        if (item == null) {
            throw new ResourceNotFoundException("Please add product to the cart before editing");
        }

        // adjust product inventory and save to product repo
        int oldQuantity = item.getQuantity();
        int difference = 0;
        Products product = item.getProduct();
        if (oldQuantity > newQuantity) {
            difference = oldQuantity - newQuantity;
            product.setInventory(product.getInventory() + difference);
        } else {
            difference = newQuantity - oldQuantity;
            product.setInventory(product.getInventory() - difference);
        }
        productRepo.save(product);

        // set new quantity and price per item and save to item repo
        item.setQuantity(newQuantity);
        item.setPricePerItem(newQuantity * item.getProduct().getPrice());
        itemRepo.save(item);

        // update cart total and save to cart repo
        cart.updateTotal();
        cartRepo.save(cart);

        log.traceEntry("Leave editQuantity", userId, cartItemId, newQuantity);
        return cart;
    }

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<String> checkout(@PathVariable Long userId) {
        log.traceEntry("Enter checkOut", userId);
        User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        // get shopping cart
        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            throw new ResourceNotFoundException("Please add product to the cart before checking  out");
        }

        double total = cart.getTotal();

        List<CartItem> cartItems = cart.getCartItems();
        for (CartItem cartItem : cartItems) {
            itemRepo.delete(cartItem);
        }

        cart.getCartItems().clear();
        cart.updateTotal();
        cartRepo.save(cart);

        log.traceEntry("Leave checkOut", userId);
        return ResponseEntity.ok("You have checked out ssuccessfully. Your total is $" + total);
    }
 
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        log.traceEntry("Enter deleteById", id);
        cartRepo.deleteById(id);
        log.traceEntry("Leave deleteById", id);
    }
}