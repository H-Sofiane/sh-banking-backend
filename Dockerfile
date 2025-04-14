FROM maven:3.9.4-eclipse-temurin-21  AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar /app/demo.jar
EXPOSE 8085
ENTRYPOINT [ "java","jar","shbanking-backend.jar" ]