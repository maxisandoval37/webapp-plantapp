# Etapa 1: Compilación
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen para ejecución
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copiamos el jar generado en la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Puerto expuesto (ajustar al puerto que uses en tu app)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
