package com.depaul.depaulmarketplace.search.controller;

import com.depaul.depaulmarketplace.search.Service.SearchService;
import com.depaul.depaulmarketplace.search.pojo.ItemDTO;
import com.depaul.depaulmarketplace.search.pojo.ItemResponse;
import com.depaul.depaulmarketplace.search.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")// localhost:8080/api/v1/carts
public class SearchController {
    @Autowired
    private SearchService searchService;

    @GetMapping
//    public List<PostDto> getAllPosts(){
//        return postService.getAllPost();
//    }

    public ItemResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue =  AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir
    ){
        return searchService.getAllItems(pageNo, pageSize, sortBy, sortDir);
    }

    //Search by id
    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(searchService.getItemById(id));
    }

}
