# Use a base image with JDK and Maven installed
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the application source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight base image with JRE installed
FROM adoptopenjdk:17-jdk-hotspot AS runtime

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expose the port that the application listens on
EXPOSE 8080
