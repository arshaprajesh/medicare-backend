# Step 1: Use a base image with Java
FROM eclipse-temurin:17-jdk-jammy


# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy your Spring Boot JAR into the container
COPY target/*.jar app.jar
# Step 4: Expose the port your app runs on
EXPOSE 30084

# Step 5: Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
