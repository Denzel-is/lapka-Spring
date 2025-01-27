package com.example.maestropizza.repository;


import com.example.maestropizza.model.Order;
import com.example.maestropizza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
