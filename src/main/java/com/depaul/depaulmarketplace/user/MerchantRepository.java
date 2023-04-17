package com.depaul.depaulmarketplace.user;

import com.depaul.depaulmarketplace.user.merchant.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant,Long> {
}
