# Use an official Java runtime as a parent image
FROM openjdk:17-alpine3.13

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app


# Expose port 8080
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "your-application.jar"]
