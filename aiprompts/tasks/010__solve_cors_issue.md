# Task: Solve CORS Issue

## Problem Description

The frontend application is encountering a CORS (Cross-Origin Resource Sharing) policy error when attempting to access the backend API. This prevents successful communication between the frontend (running on `http://localhost:3000`) and the backend (running on `http://localhost:8081`).

The specific error message observed in the browser console is:

```
Access to XMLHttpRequest at 'http://localhost:8081/api/v1/professionals' from origin 'http://localhost:3000' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

This indicates that the backend is not sending the necessary `Access-Control-Allow-Origin` header in its responses, which is required by the browser to allow cross-origin requests.

## Proposed Solution

Implement a global CORS configuration in the Spring Boot backend application to allow requests from the frontend's origin (`http://localhost:3000`). This will involve creating a `WebMvcConfigurer` bean that registers CORS mappings.

### Implementation Steps

1.  **Create a Configuration Class:** Create a new Java configuration class (e.g., `CorsConfig.java`) within the `config` package of the Spring Boot application.
2.  **Implement `WebMvcConfigurer`:** This class should implement `org.springframework.web.servlet.config.annotation.WebMvcConfigurer`.
3.  **Override `addCorsMappings`:** Override the `addCorsMappings` method to configure CORS.
4.  **Allow Specific Origin:** Configure the `CorsRegistry` to allow requests from `http://localhost:3000`.
5.  **Allow All Methods:** Allow all HTTP methods (GET, POST, PUT, DELETE, etc.).
6.  **Allow All Headers:** Allow all headers.
7.  **Allow Credentials:** Allow credentials (if necessary for authentication, e.g., cookies, authorization headers).

### Example `CorsConfig.java`

```java
package com.auraskill.api.config; // Adjust package as necessary

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // Allow requests from your frontend origin
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow all common HTTP methods
                        .allowedHeaders("*") // Allow all headers
                        .allowCredentials(true); // Allow credentials (e.g., cookies, authorization headers)
            }
        };
    }
}
```

## Verification

After implementing the solution, restart the backend application and attempt to make the same request from the frontend. The CORS error should no longer appear, and the frontend should successfully receive data from the backend.

---

## Instructions for AI

When addressing this task, the AI must strictly adhere to the following guidelines:

*   **General Guidelines:** Follow all rules and best practices defined in `@/aiprompts/guidelines/GENERAL.md`. This includes environment specifications, compatibility checks, and coding standards.
*   **Persona Guidelines:** Act as an **Expert Backend Software Developer** as defined in `@/aiprompts/personas/EXPERT_FRONTEND_DEVELOPER.md`. Focus on making small, precise, and incremental adjustments to the existing codebase.
