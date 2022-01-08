package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    User findByUsername(String username);
}
