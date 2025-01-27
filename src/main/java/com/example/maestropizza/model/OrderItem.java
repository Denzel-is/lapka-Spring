package com.example.maestropizza.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Пицца
    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    // Сколько штук
    private int quantity;

    // Ссылка на заказ
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
