FROM openjdk:21-jdk

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw package -DskipTests -e

CMD ["java", "-jar", "target/Springboot_MySQL_Docker-0.0.1-SNAPSHOT.jar"]