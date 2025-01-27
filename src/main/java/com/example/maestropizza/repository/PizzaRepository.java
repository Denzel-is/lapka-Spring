package com.example.maestropizza.repository;


import com.example.maestropizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // Поиск по названию (like)
    List<Pizza> findByNameContainingIgnoreCase(String namePart);

    // Если нужно фильтровать по категории и т.д.
}
