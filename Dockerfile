# Usa una imagen base de Gradle para compilar tu aplicación
FROM gradle:jdk17 AS build
WORKDIR /app
COPY . /app
RUN gradle build

# Cambia a una imagen base de Java para ejecutar tu aplicación
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/huellitas-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
