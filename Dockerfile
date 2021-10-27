FROM openjdk:11-jdk

COPY /target/death-star-0.0.1-SNAPSHOT.jar /usr/local/bin

ENTRYPOINT ["java","-jar","/usr/local/bin/death-star-0.0.1-SNAPSHOT.jar"]