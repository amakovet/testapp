package com.depaul.depaulmarketplace.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;
import com.depaul.depaulmarketplace.user.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    public ShoppingCart findByUser(User user);
}
