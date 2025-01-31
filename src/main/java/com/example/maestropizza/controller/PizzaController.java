package com.example.maestropizza.controller;


import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;


    @GetMapping
    public String listPizzas(@RequestParam(value="search", required=false) String search,
                             @RequestParam(value="category", required=false) Integer categoryId,
                             @RequestParam(value="detailId", required=false) Long detailId,
                             Model model) {

        // список пицц (каталог)
        List<Pizza> pizzas;
        if (categoryId != null) {
            pizzas = pizzaService.getByCategory(categoryId);
        } else if (search != null && !search.isEmpty()) {
            pizzas = pizzaService.searchPizzas(search);
        } else {
            pizzas = pizzaService.getAllPizzas();
        }

        Pizza detailPizza = null;
        if (detailId != null) {
            detailPizza = pizzaService.getPizzaById(detailId);
        }

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("search", search == null ? "" : search);
        model.addAttribute("category", categoryId);
        model.addAttribute("detailPizza", detailPizza);

        // В модель передаём признак "показывать ли модалку"
        // Если detailPizza != null => showModal = true
        model.addAttribute("showModal", (detailPizza != null));
        return "pizzas";
    }


    // Если нужно вернуть JSON (для модального окна), можно сделать RestController,
    // но для упрощения Thymeleaf - будем открывать модалку средствами JS (см. modal.js).
    @GetMapping("/{id}")
    @ResponseBody
    public Pizza getPizzaById(@PathVariable Long id){
        return pizzaService.getPizzaById(id);
    }
}
