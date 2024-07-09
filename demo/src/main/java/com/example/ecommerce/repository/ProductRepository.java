package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.src.main.java.com.example.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
