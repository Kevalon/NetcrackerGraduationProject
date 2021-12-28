package com.netcracker.application.service.repository;

import com.netcracker.application.service.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.math.BigInteger;

public interface UserRepository extends CrudRepository<User, BigInteger> {

    User findByUsername(String username);
}
