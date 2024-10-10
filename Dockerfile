FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/

COPY --from=build /app/target/*.war ./app.war

EXPOSE 8080
