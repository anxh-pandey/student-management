# Student Management REST API

Simple Spring Boot + Spring Data JPA + MySQL REST API for managing students.

## Run
1. Install Java 17, Maven and MySQL.
2. Edit `src/main/resources/application.properties` and set your MySQL password.
3. Run: `mvn spring-boot:run`  (the `student_db` database is created automatically)

## Endpoints
| Method | URL | Success | Error |
|--------|-----|---------|-------|
| POST | /api/students | 201 | |
| GET | /api/students | 200 | |
| GET | /api/students/{id} | 200 | 404 |
| PUT | /api/students/{id} | 200 | 404 |
| DELETE | /api/students/{id} | 204 | 404 |
| GET | /api/students/course/{course} | 200 | |

## Sample JSON body (POST / PUT)
```json
{
  "name": "Aman Sharma",
  "email": "aman@example.com",
  "course": "Java",
  "age": 21
}
```
