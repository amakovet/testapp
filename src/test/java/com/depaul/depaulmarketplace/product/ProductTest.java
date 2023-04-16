package com.depaul.depaulmarketplace.product;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.depaul.depaulmarketplace.products.Products;


public class ProductTest {

//Test purpose: Is lombok working

 @DisplayName("Testing Lombok")  
 @Test
 public void productDataAccess(){   

    var product = new Products();
    product.setId(1);
    product.setName("A product");
    var expectedNoError = "Products(id=1, name=A product)";
    assertEquals(expectedNoError, product.toString());
 }
}
