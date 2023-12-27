FROM openjdk:17
ADD /target/lms-coursera-0.0.1-SNAPSHOT.jar lms-coursera.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "lms-coursera.jar"]