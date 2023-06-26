package com.example.demo.service;
import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    public Iterable<Product> getProducts(){
        return productRepo.findAll();
    }

    public Product insertProducts(Product product){
        return productRepo.save(product);
    }
    public Product updateProduct(Product product,int id){
        Product product1=productRepo.findById(id).get();
        product1.setPrice(product.getPrice());
        return product1;
    }
    public String deleteProduct (int id){
        productRepo.deleteById(id);
        return  id + " was deleted";
    }
}
