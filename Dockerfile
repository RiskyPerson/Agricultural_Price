# Use an official Java runtime as a parent image
FROM adoptopenjdk/openjdk17:alpine-jre

# Set the working directory to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY . /app

# Expose port 8080
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "your-application.jar"]
