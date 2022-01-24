package com.netcracker.application.service;

import com.netcracker.application.service.model.entity.Order;
import com.netcracker.application.service.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrdersService {
    private final Map<BigInteger, Order> orders = new HashMap<>();
    private final OrderRepository orderRepository;

    @Autowired
    public OrdersService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private void fill() {
        if (orders.isEmpty()) {
            for (Order order : orderRepository.findAll()) {
                orders.put(order.getId(), order);
            }
        }
    }

    public List<Order> getAll() {
        fill();
        return new ArrayList<>(orders.values());
    }

    public Order getById(BigInteger id) {
        fill();
        return orders.get(id);
    }

    public void add(Order order) {
        orderRepository.save(order);
        orders.clear();
    }

    public void delete(BigInteger id) {
        orderRepository.delete(orders.get(id));
        orders.remove(id);
    }
}
