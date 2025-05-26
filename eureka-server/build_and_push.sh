./gradlew clean build
docker build . -t az0rahai/eureka-server:1.0.0
docker push az0rahai/eureka-server:1.0.0