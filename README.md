# Todo Application

A Spring Boot application for managing todos with REST API endpoints and a web interface.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- HTML/CSS/JavaScript

## Running the Application

1. Clone the repository
2. Make sure Docker is installed and running
3. Run `docker-compose up -d` (This will automatically build if needed)
   - The -d flag runs containers in detached mode (in the background)
   - To force a rebuild: `docker-compose up -d --build`
4. Access the application at [`http://localhost:4444`](http://localhost:4444)
5. Access Swagger UI documentation at [`http://localhost:4444/swagger-ui/index.html`](http://localhost:4444/swagger-ui/index.html)

## Features

- Create, read, update, and delete todos
- Web interface for managing todos
- REST API endpoints
- Exception handling
- Swagger documentation

## Docker Services

- Spring Boot application
- PostgreSQL database

To stop the application:
```bash
docker-compose down
```
