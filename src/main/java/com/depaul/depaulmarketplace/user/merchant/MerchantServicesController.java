package com.depaul.depaulmarketplace.user.merchant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/merchants")
public class MerchantServicesController {
    private final MerchantServices merchantServices;

    @Autowired
    public MerchantServicesController(MerchantServices merchantServices) {
        this.merchantServices = merchantServices;
    }

    @PostMapping("/register")
    public Merchant registerMerchant(@RequestParam("email")String email, @RequestParam("password") String password,@RequestParam("merchant_name") String merchant_name) {
        Merchant merchant=new Merchant();
        merchant.setEmail(email);
        merchant.setPassword(password);
        merchant.setMerchantName(merchant_name);
        return merchantServices.registerMerchant(merchant);
    }

    @GetMapping("/list")
    public List<Merchant> getAllMerchants() {
        return merchantServices.getAllMerchants();
    }


}
