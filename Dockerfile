# Use a lightweight base image with JRE installed
FROM openjdk:17-jdk-alpine AS runtime

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/target/*.jar ./app.jar

# Set the entrypoint to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expose the port that the application listens on
EXPOSE 8080



