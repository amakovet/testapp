package com.example.demo.search.dao;

import com.example.demo.search.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    //JQuery if needed
}
