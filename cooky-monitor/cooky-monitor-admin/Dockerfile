FROM openjdk:8u212-jre
MAINTAINER Yuuki

COPY ./cooky-monitor-admin-0.0.1-SNAPSHOT.jar  /cooky/cooky-admin.jar
ADD /root/agent /agent
ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=cooky-admin", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/cooky/cooky-admin.jar"]
