package com.example.maestropizza.service;



import com.example.maestropizza.model.*;
import com.example.maestropizza.repository.OrderItemRepository;
import com.example.maestropizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CartService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    public Order addToCart(User user, Pizza pizza, int quantity) {
        // Допустим, что у пользователя есть "текущий" заказ без оплаты,
        // найдём его, либо создадим новый
        Order order = orderRepository.findByUser(user).stream()
                .filter(o -> o.getPaymentInfo() == null) // ещё не оплачен
                .findFirst()
                .orElse(null);

        if (order == null) {
            order = Order.builder()
                    .createdAt(new Date())
                    .user(user)
                    .build();
            order = orderRepository.save(order);
        }

        // Создаём позицию заказа
        OrderItem orderItem = OrderItem.builder()
                .order(order)
                .pizza(pizza)
                .quantity(quantity)
                .build();
        orderItem = orderItemRepository.save(orderItem);

        // Добавим в Set (при желании)
        order.getItems().add(orderItem);

        return orderRepository.save(order);
    }

    public Order getActiveCart(User user) {
        return orderRepository.findByUser(user).stream()
                .filter(o -> o.getPaymentInfo() == null) // ещё не оплачен
                .findFirst()
                .orElse(null);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
