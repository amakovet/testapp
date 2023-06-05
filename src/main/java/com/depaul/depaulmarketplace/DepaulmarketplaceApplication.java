package com.depaul.depaulmarketplace;

import com.depaul.depaulmarketplace.products.ProductsRepository;
import com.depaul.depaulmarketplace.shoppingcart.ShoppingCartRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Log4j2
@SpringBootApplication

public class DepaulmarketplaceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DepaulmarketplaceApplication.class, args);
    }

    @Bean
    public CommandLineRunner product(ProductsRepository repo) {
        return (args) -> {
            log.info("Product Count: " + repo.count());
        };
    }

    @Bean
    public CommandLineRunner cart(ShoppingCartRepository repo) {
        return (args) -> {
            log.info("Cart Count: " + repo.count());
        };
    }
}