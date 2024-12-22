package com.example.User_Service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()) // Require authentication for all requests
            .httpBasic(); // Enable Basic Authentication
        return http.build();
    }
}
