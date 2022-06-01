#
# Package stage
#
FROM openjdk:11-jre-slim
COPY target/account-service-0.0.1-SNAPSHOT.jar /opt/app/account-service.jar

CMD ["java", "-jar", "/opt/app/account-service.jar"]
EXPOSE 8081