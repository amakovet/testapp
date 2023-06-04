package com.depaul.depaulmarketplace.products;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class productlomboktest {

    @Test
    public void testToString() {
        var product = new Products();
        product.setId(8399);
        product.setName("The Hobbit");
        product.setCategory("Books");
        product.setInventory(100);
        var expectedNoError = "Products(id=8399, name=The Hobbit, category=Books, price=null, inventory=100)";
        assertEquals(expectedNoError, product.toString());
    }

}
