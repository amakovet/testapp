package com.depaul.depaulmarketplace.products;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class MongoProducts {
    @Id
    private String id;

    private String name;

    private String category;

    private Double price;

    private Integer inventory;

}
