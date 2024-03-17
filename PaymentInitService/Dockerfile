FROM maven:3.8.3-openjdk-17
WORKDIR /PaymentInitServiceDir
COPY ./PaymentInitService/pom.xml .
COPY ./PaymentInitService/src ./src
RUN mvn clean package -DskipTests
EXPOSE 9004
CMD [ "java","-jar", "target/PaymentInitService-0.0.1-SNAPSHOT.jar"]