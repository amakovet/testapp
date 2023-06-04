package com.depaul.depaulmarketplace.user.merchant;

import com.depaul.depaulmarketplace.user.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServices {
    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServices(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    public Merchant registerMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }


}
