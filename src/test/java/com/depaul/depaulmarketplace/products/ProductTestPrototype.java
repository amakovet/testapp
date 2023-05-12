package com.depaul.depaulmarketplace.products;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles
public class ProductTestPrototype {
   @Autowired
   private ProductsRepository repo;

   // Test purpose: testing of CRUD in repo and entities persistence

   /*
    * DisplayName("Testing CRUD")
    * 
    * @Test
    * public void testingCrud() {
    * var product = new Products();
    * product.setName("product3");
    * var productIdBeforeAddedToRepo = product.getId();
    * long repoCountBeforeAddedProduct = repo.count();
    * repo.save(product);
    * var repoCountAfterAddedProduct = repo.count();
    * var productIdAfterAddedToRepo = product.getId();
    * 
    * // there should be 1 more in the database after the save
    * assertEquals(repoCountBeforeAddedProduct + 1, repoCountAfterAddedProduct);
    * 
    * // original id was 0 but afterwards the id was generated and so should not be
    * // equal
    * assertNotEquals(productIdBeforeAddedToRepo, productIdAfterAddedToRepo);
    * }
    */

   /*
    * @Test
    * public void testFindByName() {
    * List<Products> findA = repo.findByName("product 1");
    * 
    * assertEquals(1, findA.size());
    * 
    * }
    */

/*    @DisplayName("Testing Lombok")
   @Test
   public void productDataAccess() {
s
      var product = new Products();
      product.setId(1);
      product.setName("A product");
      product.setCategory("Books");
      product.setPrice(15.99);
      product.setInventory(934);
      var expectedNoError = "Products(id=1, name=A product, category=Books, price=15.99, inventory=934)";
      assertEquals(expectedNoError, product.toString());
   } */

}
