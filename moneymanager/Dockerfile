# Build stage
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

COPY mvnw pom.xml ./
COPY .mvn .mvn

RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw clean package -DskipTests




FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/moneymanager-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]
