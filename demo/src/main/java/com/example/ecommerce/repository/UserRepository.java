package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.src.main.java.com.example.ecommerce.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
