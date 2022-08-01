package com.example.ecommerce.ECommerce.service;

import com.example.ecommerce.ECommerce.model.Category;
import com.example.ecommerce.ECommerce.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo cr;
    public List<Category> getAllCategory(){
        return cr.findAll();
    }
    public void addCategory(Category c){
        cr.save(c);
    }
    public void removeCategoryById(int id){
        cr.deleteById(id);
    }
    public Optional<Category> getCategoryById(int id){
        return cr.findById(id);
    }
}
