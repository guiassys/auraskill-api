package com.apexlone.auraskill_api.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Instant;
import java.util.Collections;
import java.util.Map;

@TestConfiguration
public class TestSecurityConfig {

    @Bean
    public JwtDecoder jwtDecoder() {
        return new JwtDecoder() {
            @Override
            public Jwt decode(String token) throws JwtException {
                // Sempre que um token for enviado nos testes, criamos um JWT mockado válido
                return new Jwt(
                        token,
                        Instant.now(),
                        Instant.now().plusSeconds(3600),
                        Map.of("alg", "none"), // Headers
                        Map.of(
                                "sub", "mocked-keycloak-user-id",
                                "preferred_username", "test.user",
                                "realm_access", Map.of("roles", Collections.singletonList("ROLE_USER"))
                        ) // Claims simulando o Keycloak
                );
            }
        };
    }
}