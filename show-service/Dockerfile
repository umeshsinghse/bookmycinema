#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER umeshsingh.com

# Add the application's jar to the image
COPY target/show-service-0.0.1-SNAPSHOT.jar show-service-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "show-service-0.0.1-SNAPSHOT.jar"]