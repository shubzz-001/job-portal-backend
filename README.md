# Job Portal Backend

A Spring Boot backend application with JWT-based authentication.

## Features
- User signup & login
- Password encryption using BCrypt
- JWT authentication & authorization
- Protected REST APIs
- Global exception handling
- Clean validation responses

## Tech Stack
- Java 17
- Spring Boot 3.5.9
- Spring Security
- JWT (JJWT)
- Hibernate / JPA
- PostgreSQL / MySQL

## API Endpoints

### Auth
- POST /api/auth/signup
- POST /api/auth/login

### Protected
- GET /api/test/secure

## How to Run

1. Clone the repo
2. Configure database in `application.properties`
3. Run the application
4. Test APIs using Postman

## Author
Shubham Raje
