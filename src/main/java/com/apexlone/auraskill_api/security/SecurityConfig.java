package com.apexlone.auraskill_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // API stateless (JWT)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // CSRF não necessário para API REST
                .csrf(csrf -> csrf.disable())

                // Regras de acesso
                .authorizeHttpRequests(auth -> auth

                        // Swagger liberado (opcional)
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // Exemplo: proteger tudo da API
                        .requestMatchers("/api/**").authenticated()

                        .anyRequest().authenticated()
                )

                // 🔥 ATIVA validação JWT via Keycloak
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults())
                );

        return http.build();
    }
}