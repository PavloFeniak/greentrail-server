FROM alpine:latest

RUN apk add openjdk21
COPY build/libs/app.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

ENV SPRING_APPLICATION_JSON='{"spring.web.cors.allowed-origins":"*"}'