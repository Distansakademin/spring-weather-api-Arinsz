# Spring Boot REST API - Countries and Cities

## Base URL

- http://localhost:8080/

## Countries Resource

- **Create Country:** `POST` - http://localhost:8080/api/countries/create
- **Read (one Country):** `GET` - http://localhost:8080/api/countries/{id}
- **Read (all Countries):** `GET` - http://localhost:8080/api/countries/getAll
- **Update Country:** `PUT` - http://localhost:8080/api/countries/{id}
- **Delete Country:** `DELETE` - http://localhost:8080/api/countries/{id}
- **Get All Cities in Country:** GET - http://localhost:8080/api/{country}/cities

## Cities Resource

- **Create City:** `POST` - http://localhost:8080/api/cities/create/{CountryId}
- **Read (one City):** `GET` - http://localhost:8080/api/cities/{id}
- **Read (all Cities):** `GET` - http://localhost:8080/api/cities/getAll
- **Update City:** `PUT` - http://localhost:8080/api/cities/{id}
- **Delete City:** `DELETE` - http://localhost:8080/api/cities/{id}

## Weather Resource

- **Get Weather by City ID:** `GET` - http://localhost:8080/api/weather/{city_id}

## Database Configuration

- Database URL: jdbc:mysql://localhost:3306/spring_mysql_docker
- Username: root
- Password: root
- Driver Class: com.mysql.cj.jdbc.Driver

## Dependencies

- Spring Boot
- Spring Data JPA
- MySQL Connector
- Maven

### How to Test API Endpoints

- Use tools like Postman to send HTTP requests to the provided endpoints.
- Refer to the API documentation for each endpoint for request and response details.
