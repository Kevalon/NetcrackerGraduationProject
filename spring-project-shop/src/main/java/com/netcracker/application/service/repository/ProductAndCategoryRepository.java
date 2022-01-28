package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.Product;
import com.netcracker.application.service.model.entity.ProductAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ProductAndCategoryRepository extends JpaRepository<ProductAndCategory, BigInteger> {
}
