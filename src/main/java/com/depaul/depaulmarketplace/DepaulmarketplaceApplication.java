package com.depaul.depaulmarketplace;

import com.depaul.depaulmarketplace.user.MerchantRepository;
import com.depaul.depaulmarketplace.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.log4j.Log4j2;

import com.depaul.depaulmarketplace.products.ProductsRepository;

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
	public CommandLineRunner user(UserRepository userRepository) {
		return (args) -> {
			log.info("User Count: " + userRepository.count());
		};
	}

	@Bean
	public CommandLineRunner merchant(MerchantRepository merchantRepository) {
		return (args) -> {
			log.info("Merchant Count: " + merchantRepository.count());
		};
	}
}