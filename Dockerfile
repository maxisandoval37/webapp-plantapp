# --Dockerfile for Maven Java (jdk 17)---

FROM eclipse-temurin:17-jdk-focal
 
WORKDIR /app

RUN chmod +x mvnw
RUN chmod +x ./mvnw
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src
 
CMD ["./mvnw", "spring-boot:run"]
