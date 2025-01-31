package com.example.maestropizza.controller;


import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PizzaService pizzaService;

    @Autowired
    public HomeController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Получаем случайные пиццы для первого слайдера
        List<Pizza> randomPizzasOne = pizzaService.getRandomPizzas(20);
        // Получаем случайные пиццы для второго слайдера
        List<Pizza> randomPizzasTwo = pizzaService.getRandomPizzas(20);

        model.addAttribute("randomPizzasOne", randomPizzasOne);
        model.addAttribute("randomPizzasTwo", randomPizzasTwo);

        return "index";
    }
}
