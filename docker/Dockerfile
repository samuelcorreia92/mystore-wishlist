# Set the base image
FROM eclipse-temurin:17

ARG JAR_NAME=wishlist-app-rest

# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY app-rest/target/$JAR_NAME*.jar app.jar

# Copy the wait script into the container
COPY docker/wait-for.sh wait-for.sh
RUN chmod +x ./wait-for.sh
RUN apt-get -q update && apt-get -qy install netcat

# Expose the port that the application listens on
EXPOSE 8080

# Set the command to run the application
#CMD sh -c './wait-for.sh localhost:80 -- echo "Eficode site is up"'
CMD ["java", "-jar", "app.jar"]
