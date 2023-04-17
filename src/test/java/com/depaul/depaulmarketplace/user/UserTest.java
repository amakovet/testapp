package com.depaul.depaulmarketplace.user;

import com.depaul.depaulmarketplace.products.Products;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    //Test purpose: IS LOMBOK working properly and tables are created in proper format

    @DisplayName("Testing Users Table")
    @Test
    public void userDataAccess(){

        var userRoles=new UserRole(1L,"buyer");


        var user = new User();
        user.setId(1L);
        user.setFirstName("Test First Name");
        user.setLastName("Test User Last Name");
        user.setAddress(null);
        user.setCity(null);
        user.setState(null);
        user.setCountry(null);
        user.setEmail("test@test.com");
        user.setPassword("12345");
        user.setCreatedAt(new Date(2023,04,16));
        user.setUpdatedAt(new Date(2023,04,16));
        userRoles.setUser(user);
        user.getRole().add(userRoles);

    }
}
