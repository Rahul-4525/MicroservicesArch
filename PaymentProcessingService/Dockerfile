FROM maven:3.8.3-openjdk-17
WORKDIR /PaymentProcessingServiceDir
COPY ./PaymentProcessingService/pom.xml .
COPY ./PaymentProcessingService/src ./src
RUN mvn clean package -DskipTests
EXPOSE 9005
CMD [ "java","-jar", "target/PaymentProcessingService-0.0.1-SNAPSHOT.jar"]