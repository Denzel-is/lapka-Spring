package com.example.maestropizza.controller;

import com.example.maestropizza.model.Favorite;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.model.User;
import com.example.maestropizza.service.FavoriteService;
import com.example.maestropizza.service.PizzaService;
import com.example.maestropizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @Autowired
    private PizzaService pizzaService;

    // Показать избранные блюда
    @GetMapping
    public String showFavorites(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        List<Favorite> favorites = favoriteService.getFavoritesByUser(user);
        model.addAttribute("favorites", favorites);
        return "favorites";
    }

    // Добавить блюдо в избранное
    @PostMapping("/add")
    public String addToFavorites(@RequestParam("pizzaId") Long pizzaId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        favoriteService.addFavorite(user, pizza);
        return "redirect:/favorites";
    }

    // Удалить блюдо из избранного
    @PostMapping("/remove")
    public String removeFromFavorites(@RequestParam("pizzaId") Long pizzaId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        favoriteService.removeFavorite(user, pizza);
        return "redirect:/favorites";
    }
}
