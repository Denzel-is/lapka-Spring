package com.example.maestropizza.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Пользователь, который добавил в избранное
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Пицца, добавленная в избранное
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizza_id", nullable = false)
    private Pizza pizza;
}
