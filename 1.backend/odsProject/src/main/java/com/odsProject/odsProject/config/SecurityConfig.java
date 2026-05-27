package com.odsProject.odsProject.config;

import com.odsProject.odsProject.security.RoleAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final RoleAuthorizationFilter roleAuthorizationFilter;

    public SecurityConfig(RoleAuthorizationFilter roleAuthorizationFilter) {
        this.roleAuthorizationFilter = roleAuthorizationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(withDefaults())                       // respeta WebConfig CorsFilter
            .httpBasic(httpBasic -> httpBasic.disable())
            .formLogin(form -> form.disable())
            .logout(logout -> logout.disable())
            .securityContext(securityContext -> securityContext.disable())
            .sessionManagement(session -> session.disable())
            .anonymous(anonymous -> anonymous.disable())
            .addFilterBefore(roleAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
