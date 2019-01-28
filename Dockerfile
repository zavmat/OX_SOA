
FROM openjdk:8-jdk-alpine
RUN mkdir -p /homr/root/purchase
ARG JAVA_OPTS
ENV JAVA_OPTS=$JAVA_OPTS
ENV REDIS_HOST "redis"
ADD build/libs/escience-0.0.1-SNAPSHOT.jar /home/root/request
EXPOSE 8080
ENTRYPOINT exec java -jar /home/root/request
# For Spring-Boot project, use the entrypoint below to reduce Tomcat startup time.
#ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar escience.jar
