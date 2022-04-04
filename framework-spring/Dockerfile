FROM amazoncorretto:11.0.14-alpine3.15
COPY target/framework-spring.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar" ]