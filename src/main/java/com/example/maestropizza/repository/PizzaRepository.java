package com.example.maestropizza.repository;

import com.example.maestropizza.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {
    // Поиск по названию (уже есть у вас)
    List<Pizza> findByNameContainingIgnoreCase(String query);

    // Добавляем поиск по categoryId (предполагаем, что в классе Pizza есть Integer categoryId)
    List<Pizza> findByCategoryId(Integer categoryId);
    @Query(value = "SELECT * FROM pizzas ORDER BY RANDOM() LIMIT ?1", nativeQuery = true)
    List<Pizza> findRandomPizzas(int count);
}
