FROM openjdk:17-oracle
EXPOSE 9010
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} config-client1.jar
ENTRYPOINT ["java","-jar","/config-client1.jar"]