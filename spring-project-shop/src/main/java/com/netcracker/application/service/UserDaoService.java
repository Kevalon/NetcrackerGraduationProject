package com.netcracker.application.service;

import com.netcracker.application.config.UserDao;
import com.netcracker.application.service.model.entity.User;

import java.util.Optional;

public class UserDaoService implements UserDao {
    @Override
    public Optional<User> selectUserByUsername(String username) {
        return Optional.empty();
    }
}
