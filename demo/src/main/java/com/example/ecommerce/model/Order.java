package com.example.ecommerce.model;

import java.util.Set;

import javax.persistence.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @OneToMany(mappedBy = "order")
    private Set<Product> products;
    
    private String status;

    // Getters and setters
}
