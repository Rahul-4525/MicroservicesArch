FROM maven:3.8.3-openjdk-17
WORKDIR /NotificationServiceDir
COPY ./NotificationService/pom.xml .
COPY ./NotificationService/src ./src
RUN mvn clean package -DskipTests
EXPOSE 9003
CMD [ "java","-jar", "target/NotificationService-0.0.1-SNAPSHOT.jar"]