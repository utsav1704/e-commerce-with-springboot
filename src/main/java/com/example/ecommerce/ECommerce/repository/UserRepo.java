package com.example.ecommerce.ECommerce.repository;

import com.example.ecommerce.ECommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    public Optional<User> findUserByEmail(String email);
    public User findByEmailAndPassword(String email,String password);
}
