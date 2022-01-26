package com.netcracker.application.service;

import com.netcracker.application.controller.form.ProfileEditForm;
import com.netcracker.application.security.UserService;
import com.netcracker.application.service.model.entity.Role;
import com.netcracker.application.service.model.entity.User;
import com.netcracker.application.service.repository.RoleRepository;
import com.netcracker.application.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    private static final BigInteger CUSTOMER_ROLE_ID = BigInteger.valueOf(2);
    private static final BigInteger ADMIN_ROLE_ID = BigInteger.valueOf(1);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        User currentUser = null;
        if (userDetails instanceof User) {
            currentUser = (User) userDetails;
        }
        return currentUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("Can't find user with username " + username);
        }
        return user;
    }

    @Override
    public boolean hasRole(String role) {
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    @Override
    public User signupUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findById(CUSTOMER_ROLE_ID).orElseThrow(IllegalStateException::new);
        user.setRole(userRole);
        user.setRoleId(CUSTOMER_ROLE_ID);
        return userRepository.save(user);
    }

    public ProfileEditForm getProfileEditForm(User user) {
        ProfileEditForm profileEditForm = new ProfileEditForm();

        profileEditForm.setAddress(user.getAddress());
        profileEditForm.setFirstName(user.getFirstName());
        profileEditForm.setLastName(user.getLastName());
        profileEditForm.setPhoneNumber(user.getPhoneNumber());

        return profileEditForm;
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
