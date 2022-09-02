FROM openjdk:8-jdk-alpine
WORKDIR /usr/src/app
COPY target/InventoryService-0.0.1-SNAPSHOT.jar /usr/src/app
ENTRYPOINT ["java","-jar","InventoryService-0.0.1-SNAPSHOT.jar","--server.port=80"]
