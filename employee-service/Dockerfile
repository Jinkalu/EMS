# Stage 1: Build the JAR with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
COPY lib/ems-common-1.0.0.jar /root/.m2/repository/com/ems/ems-common/1.0.0/ems-common-1.0.0.jar
RUN mvn clean install -DskipTests

# Stage 2: Run the JAR with Java 21
FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY --from=build /app/target/employee-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
