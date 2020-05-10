FROM openjdk:8-jdk-alpine
ADD target/pizza.order-0.0.1-SNAPSHOT.jar docker-pizza-order.jar
EXPOSE 9093
ENTRYPOINT ["java","-jar","docker-pizza-order.jar"]
