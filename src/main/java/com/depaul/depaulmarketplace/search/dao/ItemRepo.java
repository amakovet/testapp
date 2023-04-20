package com.depaul.depaulmarketplace.search.dao;

import com.depaul.depaulmarketplace.search.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    //JQuery if needed
}
