package com.depaul.depaulmarketplace.search.Service;

import com.depaul.depaulmarketplace.search.pojo.ItemDTO;
import com.depaul.depaulmarketplace.search.pojo.ItemResponse;

import java.util.List;

public interface SearchService {
    ItemResponse getAllItems(int pageNo, int pageSize, String sortBy, String sortDir);
    ItemDTO getItemById(long id);
    List<ItemDTO> getAllItems();

}
