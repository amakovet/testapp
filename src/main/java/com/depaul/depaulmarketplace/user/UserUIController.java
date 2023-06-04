package com.depaul.depaulmarketplace.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserUIController {
    private final UserServices userServices;
    @Autowired
    public UserUIController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping("/register-user")
    public String showRegistrationPage(){
        return "UserRegistration";
    }
    @GetMapping("/reset-password")
    public String showResetPasswordPage(){
        return "ResetPassword";
    }
    @GetMapping("/login-user")
    public String showLoginPage(@RequestParam(value = "loginSuccess", required = false) boolean loginSuccess, Model model,RedirectAttributes redirectAttributes) {
        return "UserLogin";
    }
    @PostMapping("/register")
    public ModelAndView registerUser(@RequestParam("email")String email, @RequestParam("password") String password, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        try {
            User user=new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            userServices.registerUser(user);

            return new ModelAndView("redirect:/user/login-user").addObject("registrationSuccess", true);
        } catch (UserServices.UserRegistrationSuccessException e) {

            ModelAndView modelAndView = new ModelAndView("UserLogin");
            modelAndView.addObject("registrationSuccess", false);
            return modelAndView;
        }
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes) {

        try {
            User user = userServices.loginUser(email, password);
            redirectAttributes.addAttribute("loginSuccess", true);
            return "redirect:/user/login-user";
        } catch (IllegalArgumentException e) {

            redirectAttributes.addAttribute("loginFailure", true);
            return "redirect:/user/login-user";
        }
    }
    @PostMapping("/reset")
    public String resetPassword(@RequestParam("email") String email,RedirectAttributes redirectAttributes) {
        try {
            String newPassword=userServices.resetPassword(email);
            redirectAttributes.addAttribute("newPassword",newPassword);
            return "redirect:/user/login-user";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addAttribute("error",true);
            return "redirect:/user/reset-password";
        }
    }

}

