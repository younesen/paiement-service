package com.banque.transfert_api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ Désactive CSRF pour éviter les erreurs de sécurité
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll()  // ✅ Autorise toutes les requêtes API
                        .anyRequest().permitAll()  // ✅ Désactive toute restriction
                )
                .formLogin(form -> form.disable())  // Désactive le formulaire de login
                .httpBasic(httpBasic -> httpBasic.disable()); // Désactive l'authentification Basic

        return http.build();
    }
}
