# 📝 Blogging Platform – Spring Boot Security (JWT)

This project implements **JWT (JSON Web Token) authentication** for a blogging platform built with Spring Boot.  
It secures API endpoints, handles user login/registration, and ensures stateless authentication.

---

## 🚀 Features

- 🔐 **JWT Authentication**
    - Generate JWT tokens after successful login
    - Validate tokens on each request
    - Stateless session handling

- 👤 **User Management**
    - Register new users (`POST /users`)
    - Authenticate users (`POST /login`)

- 📄 **Post Access**
    - Public: Anyone can view posts (`GET /posts/**`)
    - Protected: Creating/updating posts requires authentication

- ⚡ **Custom Error Handling**
    - Unauthorized requests return JSON responses instead of redirects

---

## 🏗️ Project Structure (Security Layer)

| File | Description |
|------|-------------|
| `JwtAuthEntryPoint.java` | Returns `401 Unauthorized` with JSON response for invalid/missing tokens |
| `JwtFilter.java` | Intercepts requests, extracts/validates JWT, sets `SecurityContext` |
| `JwtUtil.java` | Utility for generating, parsing, validating JWT tokens |
| `SecurityConfig.java` | Spring Security configuration – public vs. protected endpoints, JWT filter registration |
| `UserPrincipal.java` | Wraps `User` entity into Spring Security's `UserDetails` |
| `UserPrincipalService.java` | Loads users from DB and integrates with Spring Security |

---

## 🔑 Authentication Flow

1. **User Registration**
    - `POST /users` with username + password
    - Passwords stored using BCrypt hashing

2. **Login**
    - `POST /login` with credentials
    - On success → API responds with JWT token

3. **Accessing Protected Routes**
    - Client sends token in header:
      ```
      Authorization: Bearer <your_jwt_token>
      ```  
    - `JwtFilter` validates token and sets user authentication

4. **Unauthorized Access**
    - If token is missing/invalid → `JwtAuthEntryPoint` returns:
      ```json
      {
        "error": "Unauthorized",
        "message": "Full authentication is required to access this resource"
      }
      ```

---

## ⚙️ Configuration

You can configure JWT properties in `application.properties`:

```properties
# JWT Configuration
jwt.secret=your-super-secret-key
jwt.expiration=300000   # 5 minutes
