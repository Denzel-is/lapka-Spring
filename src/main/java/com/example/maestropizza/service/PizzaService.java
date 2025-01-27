package com.example.maestropizza.service;


import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public List<Pizza> searchPizzas(String query) {
        return pizzaRepository.findByNameContainingIgnoreCase(query);
    }

    public Pizza getPizzaById(Long id) {
        return pizzaRepository.findById(id).orElse(null);
    }
}
