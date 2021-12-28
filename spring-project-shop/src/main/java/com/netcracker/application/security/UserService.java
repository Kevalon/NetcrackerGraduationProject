package com.netcracker.application.security;


import com.netcracker.application.service.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getCurrentUser();

    boolean hasRole(String role);
}
