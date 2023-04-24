package com.depaul.depaulmarketplace.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/products")
@Log4j2
public class ProductService {
    @Autowired
    ProductsRepository repo;

    @GetMapping
    public List<Products> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Products get(@PathVariable("id") Long id) {
        log.traceEntry("get", id);
        Products product = repo.findById(id).orElse(new Products());
        log.traceExit("get", id);
        return product;
    }

}
