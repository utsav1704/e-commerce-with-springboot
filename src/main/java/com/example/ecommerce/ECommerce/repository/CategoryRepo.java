package com.example.ecommerce.ECommerce.repository;

import com.example.ecommerce.ECommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

}
