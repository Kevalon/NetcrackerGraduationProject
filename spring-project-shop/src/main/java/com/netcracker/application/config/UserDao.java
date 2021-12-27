package com.netcracker.application.config;

import com.netcracker.application.service.model.entity.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> selectUserByUsername(String username);


}
