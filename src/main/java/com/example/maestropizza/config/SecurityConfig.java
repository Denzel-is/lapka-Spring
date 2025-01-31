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


                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // После входа — на главную
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // После выхода редирект на login
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
        return http.build();


    }
}
