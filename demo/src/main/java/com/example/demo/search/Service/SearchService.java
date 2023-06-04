package com.example.demo.search.Service;

import com.example.demo.search.pojo.ItemDTO;
import com.example.demo.search.pojo.ItemResponse;

import java.util.List;

public interface SearchService {
    ItemDTO createItem(ItemDTO itemDTO);
    ItemResponse getAllItems(int pageNo, int pageSize, String sortBy, String sortDir);
    ItemDTO getItemById(long id);
    List<ItemDTO> getAllItems();

}
