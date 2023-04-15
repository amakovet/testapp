package com.depaul.depaulmarketplace.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.depaul.depaulmarketplace.products.Products;


public class LombokProductTest {
 @Test
 public void productDataAccess(){   

    var product = new Products();
    product.setId(1);
    product.setName("A product");
    var expectedNoError = "Products(id=1, name=A product)";
    assertEquals(expectedNoError, product.toString());
 }
}
