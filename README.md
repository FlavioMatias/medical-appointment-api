# Medical Appointment API 🚑📋

Spring Boot REST API to manage medical appointments — doctors, patients and schedules.  
**First version — work in progress.** ⚠️

## Quick overview 🧭
Imagine you are a clinic receptionist:
- register doctors (name, specialty, contact, license) 🩺
- register patients so returning patients are recognized 👥
- create appointments linking doctor, patient, room, datetime and appointment type 📅

This project follows a clean structure (controllers → services → repositories), uses factories/mappers and Specifications for filtering.

## Tech stack 🛠️
- Java 21
- Spring Boot
- Spring Data JPA
- H2 (embedded)
- Maven
- springdoc-openapi (Swagger UI)
- JWT-based auth (present but needs adjustments) 🔐

## Run (local) ▶️
```bash
cd appointment
mvn spring-boot:run
````

## Swagger UI 🔎

Open the interactive docs:

```
http://localhost:8080/swagger-ui/index.html
```

## H2 Console (optional) 🗃️

```
http://localhost:8080/h2-console
```

Default JDBC URL is usually `jdbc:h2:mem:testdb` — check `application.yml`.

## Notes 📝

* This is the **first version** and still under development — expect unfinished bits and TODOs. 🚧
* JWT is integrated but requires final adjustments to work smoothly with the current security config. 🔧
* The runnable folder is `appointment/`.

