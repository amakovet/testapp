package com.depaul.depaulmarketplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserServicesController {

    private final UserServices userServices;

    @Autowired
    public UserServicesController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("/register")
    public User registerUser(@RequestParam("email")String email, @RequestParam("password") String password,@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName) {
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userServices.registerUser(user);
    }
    @PostMapping("/list")
    public List<User> list(){
        return userServices.listAll();
    }
    @PostMapping("/login")
    public User loginUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userServices.loginUser(email, password);
    }

    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam("email") String email) {
        userServices.resetPassword(email);
    }

    @PutMapping("/{userId}/update-profile")
    public User updateProfile(@PathVariable("userId") Long userId, @RequestBody User user) {
        return userServices.updateProfile(userId, user);
    }
}
