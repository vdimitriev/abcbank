FROM amazoncorretto:11-alpine-jdk
MAINTAINER dmt.mk
COPY target/abc-1.0.0.jar abc.jar
ENTRYPOINT ["java","-jar","/abc.jar"]