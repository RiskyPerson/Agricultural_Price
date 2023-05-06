# Start with a base image that has the Java runtime installed
FROM openjdk:17-alpine3.14

# Set the working directory to /app
WORKDIR /app

# Copy the JAR file from the target directory to the working directory
COPY target/myapp.jar .

# Expose port 8080
EXPOSE 8080

# Run the JAR file with the java command
CMD ["java", "-jar", "myapp.jar"]