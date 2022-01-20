package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface CategoryRepository extends JpaRepository<Category, BigInteger> {
}
