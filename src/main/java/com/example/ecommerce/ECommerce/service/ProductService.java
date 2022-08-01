package com.example.ecommerce.ECommerce.service;

import com.example.ecommerce.ECommerce.model.Product;
import com.example.ecommerce.ECommerce.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepo pr;
    public List<Product> getAllProducts(){
        return pr.findAll();
    }

    public void addProduct(Product product){
        pr.save(product);
    }

    public void removeProductById(long id){
        pr.deleteById(id);
    }
    public Optional<Product> getProductById(long id){
        return pr.findById(id);
    }
    public List<Product> getAllProductsByCategoryId(int id){
        return pr.findAllByCategory_Cid(id);
    }
}
