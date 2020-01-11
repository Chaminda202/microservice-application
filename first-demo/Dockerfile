FROM java:8
WORKDIR /
ADD /target/first-demo-0.0.1-SNAPSHOT.jar first-demo-0.0.1-SNAPSHOT.jar
EXPOSE 8085:8080
ENTRYPOINT ["java","-jar","first-demo-0.0.1-SNAPSHOT.jar"]