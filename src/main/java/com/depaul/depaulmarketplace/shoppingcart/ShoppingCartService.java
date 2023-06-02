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
import com.depaul.depaulmarketplace.products.ProductService;
import com.depaul.depaulmarketplace.products.ProductsRepository;
import com.depaul.depaulmarketplace.user.UserRepository;
import com.depaul.depaulmarketplace.user.User;


@RestController
@RequestMapping("/api/shopping-cart")
public class ShoppingCartService{

    @Autowired
    private ShoppingCartRepository cartRepo;

    @Autowired
    private ProductService productServ;

    @Autowired
    private ProductsRepository productRepo;

    @Autowired
    private CartItemRepository itemRepo;

    @Autowired 
    private UserRepository userRepo;

    @GetMapping("/")
    public List<ShoppingCart> getAllShoppingCarts() {
        return cartRepo.findAll();
    }

    @GetMapping("/{userId}")
    public ShoppingCart getCartByUserId(@PathVariable Long userId) {
        User user = userRepo.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found"));

        ShoppingCart cart = cartRepo.findByUser(user);
        if (cart == null) {
            cart = new ShoppingCart();
            cart.setUser(user);
        }
        return cart;
    }

    @PostMapping("/{userId}/add-product/{productId}")
    public ResponseEntity<String> addProductToCart(@PathVariable Long userId, @PathVariable Long productId, @RequestParam int quantity) {
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
        product.setInventory(product.getInventory() - newQuantity);

        // save everything
        itemRepo.save(item);
        cartRepo.save(cart);
        productRepo.save(product);

        return ResponseEntity.ok("Product added to cart successfully");
    }

    @PostMapping("/{userId}/remove-cart-item/{cartItemId}")
    public ResponseEntity<String> removeCartItemFromCart(@PathVariable Long userId, @PathVariable Long cartItemId) {
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

        return ResponseEntity.ok("Product removed from cart successfully");
    }
 
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        cartRepo.deleteById(id);
    }
}