package com.depaul.depaulmarketplace.products;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/mogoproducts")
@Log4j2
public class MongoProductService {
    @Autowired
    MongoProductsRepository repo;

    @GetMapping
    @Operation(summary = "Returns all the products")
    @ApiResponse(responseCode = "200", description = "valid response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = MongoProductService.class)) })
    public List<MongoProducts> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @PostMapping
    @Operation(summary = "Save the instructor and returns the  id")
    public String save(MongoProducts product) {
        log.traceEntry("enter save", product);
        repo.save(product);
        log.traceExit("exit save", product);
        return product.getId();
    }

}
