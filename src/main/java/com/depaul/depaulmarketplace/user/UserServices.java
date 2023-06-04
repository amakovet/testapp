package com.depaul.depaulmarketplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServices {

    private final UserRepository userRepository;

    @Autowired
    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }
    public User registerUser(User user) {

         user.setCreatedAt(new Date(System.currentTimeMillis()));
         user.setUpdatedAt(new Date(System.currentTimeMillis()));

         UserRole userRole = new UserRole();
         userRole.setRoleName("USER");
         user.setRole(userRole);
         user.setRole(userRole);

         user.setPassword(user.getPassword());
         return userRepository.save(user);
    }

    public User loginUser(String email, String password) {

         User user = userRepository.findByEmail(email);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        } else {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    public void resetPassword(String email) {

         User user = userRepository.findByEmail(email);
         if (user != null) {
             String newPassword = generateNewPassword();
             user.setPassword(newPassword);
             userRepository.save(user);
         } else {
             throw new IllegalArgumentException("User not found");
         }
    }

    public User updateProfile(Long userId, User user) {

         User existingUser = userRepository.findById(userId)
                 .orElseThrow(() -> new IllegalArgumentException("User not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setAddress(user.getAddress());

        return userRepository.save(existingUser);
    }
    private String generateNewPassword() {
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int passwordLength = 8;
        StringBuilder newPassword = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < passwordLength; i++) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            newPassword.append(randomChar);
        }
        return newPassword.toString();
    }

    public class UserRegistrationSuccessException extends RuntimeException  {
    }
}