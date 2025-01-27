package com.example.maestropizza.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        // На главной просто выводим приветствие
        model.addAttribute("title", "Добро пожаловать в нашу пиццерию!");
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
