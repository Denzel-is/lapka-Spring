package com.example.maestropizza.model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;
    private String cardHolderName;
    private String address;
    private String city;
    private String postalCode;

    // Привязка к заказу 1:1
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
