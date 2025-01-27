package com.example.maestropizza.controller;



import com.example.maestropizza.model.Order;
import com.example.maestropizza.model.PaymentInfo;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.model.User;
import com.example.maestropizza.service.CartService;
import com.example.maestropizza.service.PaymentService;
import com.example.maestropizza.service.PizzaService;
import com.example.maestropizza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("pizzaId") Long pizzaId,
                            @RequestParam(value="quantity", defaultValue="1") int quantity){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Pizza pizza = pizzaService.getPizzaById(pizzaId);
        cartService.addToCart(user, pizza, quantity);
        return "redirect:/pizzas";
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        Order order = cartService.getActiveCart(user);

        double totalPrice = 0.0;
        if (order != null && order.getItems() != null) {
            totalPrice = order.getItems().stream()
                    .mapToDouble(item -> item.getQuantity() * item.getPizza().getPrice())
                    .sum();
        }

        model.addAttribute("order", order);
        model.addAttribute("paymentInfo", new PaymentInfo());
        model.addAttribute("totalPrice", totalPrice); // <-- передаём в модель

        return "cart";
    }


    @PostMapping("/checkout")
    public String checkout(@ModelAttribute("paymentInfo") PaymentInfo paymentInfo){
        // Получаем пользователя
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        // Находим активный заказ
        Order order = cartService.getActiveCart(user);

        // Обрабатываем платёж
        paymentService.processPayment(order, paymentInfo);

        // После сохранения paymentInfo заказ считается оплаченным
        // можно сделать редирект на профиль или страницу с благодарностью
        return "redirect:/profile";
    }
}
