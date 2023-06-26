package com.example.demo.repo;

import com.example.demo.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {

}
