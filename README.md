  # URL Shortener Microservice

  A RESTful API for shortening URLs, built with Spring Boot 3.3.5 and Java 21.
  https://roadmap.sh/projects/url-shortening-service

  ## Tech Stack

  - Java 21
  - Spring Boot 3.3.5 with Spring Data JPA
  - H2 Database (in-memory)
  - Gradle
  - Pkl (configuration)

  ## Running

  ```bash
  ./gradlew bootRun

  API available at http://localhost:8080

  API Endpoints

  | Method | Endpoint                   | Description           |
  |--------|----------------------------|-----------------------|
  | POST   | /shorten                   | Create short URL      |
  | GET    | /shorten/{shortCode}       | Retrieve URL          |
  | PUT    | /shorten/{shortCode}       | Update URL            |
  | DELETE | /shorten/{shortCode}       | Delete URL            |
  | GET    | /shorten/{shortCode}/stats | Get access statistics |

  Example

  # Create
  curl -X POST http://localhost:8080/shorten \
    -H "Content-Type: application/json" \
    -d '{"url": "https://www.example.com"}'

  # Retrieve (replace abc123 with your short code)
  curl http://localhost:8080/shorten/abc123
