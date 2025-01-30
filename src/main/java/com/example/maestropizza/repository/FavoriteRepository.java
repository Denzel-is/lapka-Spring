package com.example.maestropizza.repository;

import com.example.maestropizza.model.Favorite;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Favorite> findByUser(User user);
    Optional<Favorite> findByUserAndPizza(User user, Pizza pizza);
    void deleteByUserAndPizza(User user, Pizza pizza);
}
