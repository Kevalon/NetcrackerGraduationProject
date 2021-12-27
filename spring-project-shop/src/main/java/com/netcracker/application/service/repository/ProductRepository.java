package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductRepository extends CrudRepository<Product, BigInteger> {
}
