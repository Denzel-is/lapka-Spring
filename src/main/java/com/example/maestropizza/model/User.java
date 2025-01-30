package com.example.maestropizza.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true, nullable=false)
    private String username;

    @Column(nullable=false)
    private String password;

    private String fullName; // Имя пользователя (для личного кабинета)

    // Связь с ролями
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<UserRole> userRoles = new HashSet<>();

    // Данные о заказах
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<Order> orders = new HashSet<>();

    // Связь с избранными пиццами
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Favorite> favorites = new HashSet<>();

    /**
     * Преобразовываем нашу сущность в объект UserDetails,
     * используя встроенный билдер Spring Security.
     *
     * @param roleNames - набор строк (ROLE_USER, ROLE_ADMIN и т.д.)
     * @return UserDetails
     */
    public UserDetails buildSecurityUser(Set<String> roleNames) {
        // org.springframework.security.core.userdetails.User - это конкретный класс,
        // который имплементирует интерфейс UserDetails
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(this.username)
                .password(this.password)
                // передаём роли в виде массива
                .authorities(roleNames.toArray(new String[0]))
                .build();
    }
}
