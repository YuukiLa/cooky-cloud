FROM openjdk:8u212-jre
MAINTAINER Yuuki

COPY ./cooky-rbac-0.0.1-SNAPSHOT.jar /cooky/cooky-rbac.jar
ADD /root/agent /agent
ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=cooky-rbac", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/cooky/cooky-rbac.jar"]
