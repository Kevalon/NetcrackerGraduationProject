package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Order;
import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;

@Component
public class CartService {
    private final OrdersService ordersService;
    private final ProductService productService;

    @Autowired
    public CartService(OrdersService ordersService, ProductService productService) {
        this.ordersService = ordersService;
        this.productService = productService;
    }

    public void addToCart(BigInteger productId) {
        BigInteger curUserId = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        if (ordersService.getById(productId) == null) {
            Order order = new Order();
            order.setGoodsAmount(1);
            order.setCreationDate(new Timestamp(new Date().getTime()));
            order.setIsClosed(false);
            order.setTotalSum(productService.getById(productId).getPrice());
            order.setProducts(new HashSet<Product>() {{ add(productService.getById(productId)); }});
            order.setUserId(curUserId);
            ordersService.add(order);
        } else {
            ordersService
        }
    }
}
