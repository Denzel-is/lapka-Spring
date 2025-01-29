package com.example.maestropizza.controller;



import com.example.maestropizza.model.User;
import com.example.maestropizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.findByUsername(username);

        // Добавляем в модель и user, и его заказы
        model.addAttribute("user", currentUser);
        model.addAttribute("orders", currentUser.getOrders());

        return "profile";
    }

}
