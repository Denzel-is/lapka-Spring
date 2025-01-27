package com.example.maestropizza.config;

import com.example.maestropizza.service.PizzeriaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer; // если нужно, бывает удобно
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PizzeriaUserDetailsService pizzeriaUserDetailsService;

    /**
     * BCrypt — один из популярных способов хеширования паролей.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настройка DaoAuthenticationProvider, чтобы Spring Security
     * знал, как искать пользователя (UserDetailsService) и чем хешировать пароль.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(pizzeriaUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Основной SecurityFilterChain. В Spring Boot 3 / Security 6+
     * мы используем лямбда-форму настройки HttpSecurity.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Подключаем наш DaoAuthenticationProvider
        http.authenticationProvider(authenticationProvider());

        http
                // Настройка авторизаций:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                        .anyRequest().authenticated()
                )


                // Настраиваем форму логина:
                .formLogin(login -> login
                        .loginPage("/login")        // своя страница логина
                        .defaultSuccessUrl("/", true) // куда перенаправлять после удачного входа
                        .permitAll()                 // даём доступ ко /login всем
                )

                // Настраиваем логаут:
                .logout(logout -> logout
                        .logoutUrl("/logout")      // URL на который отправляется POST-запрос для логаута
                        .logoutSuccessUrl("/")     // Куда перенаправлять после логаута
                        .permitAll()
                )


        ;

        // Собираем конфигурацию в объект SecurityFilterChain
        return http.build();
    }
}
