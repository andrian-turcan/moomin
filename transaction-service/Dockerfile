#
# Package stage
#
FROM openjdk:11-jre-slim
COPY target/transaction-service-0.0.1-SNAPSHOT.jar /opt/app/transaction-service.jar

CMD ["java", "-jar", "/opt/app/transaction-service.jar"]
EXPOSE 8082