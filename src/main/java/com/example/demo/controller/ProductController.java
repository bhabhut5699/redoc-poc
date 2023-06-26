package com.example.demo.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.demo.entity.Product;
import com.example.demo.service.ElasticSearchService;
import com.example.demo.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apis")
@Api(tags = "Product API")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    ElasticSearchService elasticSearchService;

    @GetMapping("/findAll")
    @ApiOperation("Get all products")
    public Iterable<Product> findAll() {
        return productService.getProducts();
    }

    @PostMapping("/insert")
    @ApiOperation("Insert a new product")
    public Product insertProduct(@RequestBody Product product) {
        return productService.insertProducts(product);
    }

    @GetMapping("/matchAll")
    @ApiOperation("Match all products")
    public List<Map<String, Object>> matchAll() throws IOException {
        List<Map<String, Object>> searchResponse = elasticSearchService.matchAllServices();
        return searchResponse;
    }
}
