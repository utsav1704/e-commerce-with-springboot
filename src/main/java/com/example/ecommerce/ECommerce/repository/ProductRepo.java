package com.example.ecommerce.ECommerce.repository;

import com.example.ecommerce.ECommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Long> {
    List<Product> findAllByCategory_Cid(int id);
    //List<Product> findAllByCategoryId(int id);
}
