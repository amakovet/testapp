package com.depaul.depaulmarketplace.shoppingcart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<ShoppingCart, Long> {

}