package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface OrderRepository extends JpaRepository<Order, BigInteger> {
}
