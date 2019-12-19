FROM openjdk:8u212-jre
MAINTAINER Yuuki

COPY ./cooky-auth-0.0.1-SNAPSHOT.jar /cooky/cooky-auth.jar
ADD /root/agent /agent
ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=cooky-auth", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/cooky/cooky-auth.jar"]
