FROM openjdk:17-oracle
COPY target/oauth-jwt-0.0.1-SNAPSHOT.jar /app/oauth-jwt-0.0.1-SNAPSHOT.jar
COPY src/main/resources/bootstrap.properties /app/bootstrap.properties
WORKDIR /app
CMD ["java", "-jar", "oauth-jwt-0.0.1-SNAPSHOT.jar"]