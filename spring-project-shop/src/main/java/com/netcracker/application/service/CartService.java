package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class CartService {
    private final UserServiceImpl userService;

    @Autowired
    public CartService(UserServiceImpl userService) {
        this.userService = userService;
    }

    public void addToCart(Product product) {
        userService.getCurrentUser().getCart().add(product);
    }
}
