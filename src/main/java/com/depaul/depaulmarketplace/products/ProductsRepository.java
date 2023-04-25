package com.depaul.depaulmarketplace.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {
    public List<Products> findByName(String name);

    public List<Products> findAllByCategory(String category);

}
