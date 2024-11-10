# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container at /app
COPY target/swaggerdemo-1.0.0-SNAPSHOT.jar swaggerdemo.jar

# Expose port 8080 to the outside world
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "swaggerdemo.jar"]