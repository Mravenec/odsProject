package com.odsProject.odsProject.config;

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
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable())
            .securityContext(securityContext -> securityContext.disable())
            .sessionManagement(session -> session.disable())
            .anonymous(anonymous -> anonymous.disable())
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
