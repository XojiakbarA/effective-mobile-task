FROM openjdk:17-oracle
COPY target/*.jar e-m-task.jar

ENTRYPOINT ["java", "-jar", "e-m-task.jar"]