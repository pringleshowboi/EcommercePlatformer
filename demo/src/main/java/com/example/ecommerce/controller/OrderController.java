package com.example.ecommerce.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import demo.src.main.java.com.example.ecommerce.model.Order;
import demo.src.main.java.com.example.ecommerce.model.Product;
import demo.src.main.java.com.example.ecommerce.model.User;
import demo.src.main.java.com.example.ecommerce.service.OrderService;
import demo.src.main.java.com.example.ecommerce.service.ProductService;
import demo.src.main.java.com.example.ecommerce.service.UserService;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping("/orders")
    public String viewOrderHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        List<Order> orders = orderService.findByUser(user);
        model.addAttribute("orders", orders);
        return "order_history";
    }

    @GetMapping("/order/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) {
            return "error/404";
        }
        model.addAttribute("order", order);
        return "order_detail";
    }

    @PostMapping("/order")
    public String createOrder(Long[] productIds) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());

        Set<Product> products = new HashSet<>();
        for (Long productId : productIds) {
            Product product = productService.findById(productId);
            if (product != null) {
                products.add(product);
            }
        }

        Order order = new Order();
        order.setUser(user);
        order.setProducts(products);
        order.setStatus("Pending");
        orderService.save(order);

        return "redirect:/orders";
    }
}
