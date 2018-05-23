FROM openjdk:8-jdk-alpine
MAINTAINER baixiangzhu
ADD @project.build.finalName@.jar @project.build.finalName@.jar
RUN sh -c 'touch /@project.build.finalName@.jar'
ENV JAVA_OPTS=""
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /@project.build.finalName@.jar