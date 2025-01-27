package com.example.maestropizza.service;

import com.example.maestropizza.model.Role;
import com.example.maestropizza.model.User;
import com.example.maestropizza.model.UserRole;
import com.example.maestropizza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PizzeriaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(
                    "User not found with username: " + username);
        }

        // Собираем роли в виде строк (например, ROLE_USER, ROLE_ADMIN)
        Set<String> roles = user.getUserRoles().stream()
                .map(UserRole::getRole)
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        // Вызываем метод buildSecurityUser(...) из сущности User
        return user.buildSecurityUser(roles);
    }
}
