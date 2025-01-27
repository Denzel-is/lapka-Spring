package com.example.maestropizza.service;



import com.example.maestropizza.model.Role;
import com.example.maestropizza.model.User;
import com.example.maestropizza.model.UserRole;
import com.example.maestropizza.repository.RoleRepository;
import com.example.maestropizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        // шифруем пароль
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Проверим, есть ли роль USER
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        if (roleUser == null) {
            roleUser = Role.builder().roleName("ROLE_USER").build();
            roleUser = roleRepository.save(roleUser);
        }
        // Привязываем роль
        UserRole userRole = UserRole.builder()
                .user(user)
                .role(roleUser)
                .build();
        user.setUserRoles(new HashSet<>());
        user.getUserRoles().add(userRole);

        return userRepository.save(user);
    }
}

