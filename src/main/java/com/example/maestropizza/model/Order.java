package com.example.maestropizza.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createdAt;

    // Заказ может содержать несколько позиций (OrderItem)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Builder.Default
    private Set<OrderItem> items = new HashSet<>();

    // Связь с пользователем
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Информация об оплате
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentInfo paymentInfo;
}
