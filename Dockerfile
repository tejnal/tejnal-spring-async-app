FROM openjdk:11-jdk-slim as build
VOLUME /tmp
EXPOSE 3001
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/tejnal-spring-async-app-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
ENTRYPOINT ["java","-jar","tejnal-spring-async-app-0.0.1-SNAPSHOT.jar"]
