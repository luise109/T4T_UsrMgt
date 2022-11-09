FROM openjdk:11
ADD target/usrMgt.jar usrMgt.jar
ENTRYPOINT ["java", "-jar","usrMgt.jar"]
