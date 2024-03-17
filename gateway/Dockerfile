FROM maven:3.8.3-openjdk-17
WORKDIR /GatewayServiceDir
COPY ./gateway/pom.xml .
COPY ./gateway/src ./src
RUN mvn clean package -DskipTests
EXPOSE 9002
CMD [ "java","-jar", "target/gateway-0.0.1-SNAPSHOT.jar"]