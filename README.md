# Auraskill API

Backend API built with Spring Boot for the Auraskill platform.  
It provides authentication, professional management, and secure REST endpoints integrated with Keycloak.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring Web
- Spring Data JPA
- Spring Security (OAuth2 Resource Server)
- Keycloak (Authentication & Authorization)
- PostgreSQL
- Flyway (Database migrations)
- OpenAPI / Swagger (springdoc)
- Maven

---

## 📦 Architecture Overview

The API follows a layered architecture:

```
Controller → Service → Repository → Database
```

Security is handled via Keycloak JWT validation.

---

## 🔐 Authentication

This API uses **Keycloak as Identity Provider**.

All secured endpoints require:

```
Authorization: Bearer <access_token>
```

### Token validation

The API validates tokens using:

```properties
spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/auraskill
```

---

## 🧑‍💼 Main Feature: Professionals

The API manages professionals with full CRUD support.

### Database table

```sql
professionals (
  id BIGSERIAL PRIMARY KEY,
  keycloak_user_id UUID UNIQUE NOT NULL,
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  date_of_birth DATE,
  phone VARCHAR(30),
  address TEXT,
  bio TEXT,
  profile_picture_url TEXT,
  created_at TIMESTAMPTZ DEFAULT NOW(),
  updated_at TIMESTAMPTZ DEFAULT NOW()
)
```

---

## 📌 API Endpoints

### Professionals

| Method | Endpoint                      | Description            |
|--------|------------------------------|------------------------|
| GET    | /api/v1/professionals        | List all professionals |
| GET    | /api/v1/professionals/{id}   | Get professional by ID |
| POST   | /api/v1/professionals        | Create new professional |
| PUT    | /api/v1/professionals/{id}   | Update professional    |
| DELETE | /api/v1/professionals/{id}   | Delete professional    |

---

## 🧪 Swagger Documentation

When running the application:

```
http://localhost:8081/swagger-ui/index.html
```

OpenAPI JSON:

```
http://localhost:8081/v3/api-docs
```

---

## 🛠️ How to Run

### 1. Start PostgreSQL (Docker or local)

### 2. Start Keycloak

```
http://localhost:8080
```

### 3. Configure application.properties

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/auraskill
spring.datasource.username=your_user
spring.datasource.password=your_password

spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080/realms/auraskill
```

### 4. Run the project

```bash
./mvnw spring-boot:run
```

---

## 🧬 Database Migrations

Flyway automatically runs migrations on startup.

Scripts are located in:

```
src/main/resources/db/migration
```

---

## 🔐 Security Flow

1. Frontend authenticates with Keycloak
2. Receives JWT access token
3. Sends token in API requests
4. Backend validates token via Keycloak issuer
5. Access granted based on authentication/roles

---

## 📌 Next Improvements

- Role-based access control (ADMIN / PROFESSIONAL)
- User synchronization with Keycloak
- Refresh token handling
- Audit logging
- Pagination & filtering
- Docker Compose environment

---

## 👨‍💻 Author

Developed by Guilherme (guiassys)

## test(1)