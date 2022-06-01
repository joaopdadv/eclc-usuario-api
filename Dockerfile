FROM openjdk:11
COPY ./build/libs/usuario-ecommerce-api-0.0.1-SNAPSHOT.jar usuario-ecommerce-api-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "usuario-ecommerce-api-0.0.1-SNAPSHOT.jar"]