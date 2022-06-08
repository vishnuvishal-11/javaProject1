# syntax=docker/dockerfile:1
FROM openjdk:18-alpine
VOLUME /redis-service
COPY target/redis-spring_boot.jar  redis-spring_boot.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","/redis-spring_boot.jar"]

