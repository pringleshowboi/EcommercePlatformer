package com.example.ecommerce.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    // Getters and setters
}
