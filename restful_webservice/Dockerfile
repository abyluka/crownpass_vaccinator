FROM openjdk:14-alpine

ARG WAR_FILE=./target//restful_webservice_recordvaccine-0.0.1-SNAPSHOT.jar

COPY ${WAR_FILE} webapp.war

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "webapp.war"]