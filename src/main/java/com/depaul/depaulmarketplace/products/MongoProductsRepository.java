package com.depaul.depaulmarketplace.products;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoProductsRepository extends MongoRepository<MongoProducts, String> {
    public List<MongoProducts> findByName(String name);

    public List<MongoProducts> findAllByCategory(String category);

}
