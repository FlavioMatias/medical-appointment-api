# Etapa 1: Build
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY ../appointment/pom.xml .
COPY ../appointment/src ./src

RUN mvn -B -f pom.xml clean package -DskipTests

# Etapa 2: Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=prod \
    TZ=America/Sao_Paulo

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
