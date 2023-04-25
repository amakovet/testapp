package com.depaul.depaulmarketplace.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

   // Test purpose: Is lombok working

   @DisplayName("Testing Lombok")
   @Test
   public void productDataAccess() {

      var product = new Products();
      product.setId(1);
      product.setName("A product");
      product.setCategory("Books");
      product.setPrice(15.99);
      product.setInventory(934);
      var expectedNoError = "Products(id=1, name=A product, category=Books, price=15.99, inventory=934)";
      assertEquals(expectedNoError, product.toString());
   }
}
