FROM tomcat:10.1-jdk17
WORKDIR /usr/local/tomcat/webapps/

# Renombrar el archivo .war a ROOT.war para que se sirva desde la raíz
COPY --from=build /app/target/*.war ./ROOT.war

# Configuración para evitar el escaneo innecesario de JARs
RUN echo '<?xml version="1.0" encoding="UTF-8"?><Context><JarScanner><JarScanFilter defaultPluggabilityScan="false"/></JarScanner></Context>' > /usr/local/tomcat/conf/context.xml

EXPOSE 8080
