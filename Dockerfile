FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/

COPY --from=build /app/target/*.war ./ROOT.war

# Modificar el archivo server.xml
RUN sed -i 's/<Server port="8005" shutdown="SHUTDOWN">/<Server port="8005" shutdown="SHUTDOWN" allowCatalina="true">/' /usr/local/tomcat/conf/server.xml

# Asegúrate de que el conector HTTP esté configurado correctamente
RUN sed -i 's|<Connector port="8080"|<Connector port="8080" protocol="HTTP/1.1"|g' /usr/local/tomcat/conf/server.xml

# Configuración para evitar el escaneo innecesario de JARs
RUN echo '<?xml version="1.0" encoding="UTF-8"?><Context><JarScanner><JarScanFilter defaultPluggabilityScan="false"/></JarScanner></Context>' > /usr/local/tomcat/conf/context.xml

EXPOSE 8080
