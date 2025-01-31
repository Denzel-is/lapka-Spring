package com.example.maestropizza.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pizzas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Pizza(int i, String ere, String маргарита, String r, double v) {
    }

    public Pizza(int i, String ere, String пепперони, double v) {
    }

    public Pizza(int i, String маргарита, double v, String традиционная) {
    }
}
