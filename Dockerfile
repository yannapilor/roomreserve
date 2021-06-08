FROM openjdk:11
EXPOSE 8090
ADD target/ReservationWebApp.jar ReservationWebApp.jar
ENTRYPOINT ["java","-jar","/ReservationWebApp.jar"]
