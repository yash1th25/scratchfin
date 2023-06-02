# Fetching latest version of Java
  #FROM openjdk:17
 #FROM ubuntu:22.04
#RUN apt-get update && \
  #  apt-get install -y openjdk-17-jdk && \
  #   apt-get clean;
    FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
 # Set the Java home environment variable
# ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-arm64

 # Add Java bin directory to the PATH
 #ENV PATH=${PATH}:${JAVA_HOME}/bin

      WORKDIR /app

      # Copy the jar file into our app
      COPY ./target/restapi-0.0.1-SNAPSHOT.jar /app

      # Exposing port 8080
      EXPOSE 8080
      # Starting the application
      CMD ["java", "-jar","restapi-0.0.1-SNAPSHOT.jar"]

