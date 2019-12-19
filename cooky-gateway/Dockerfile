FROM openjdk:8u212-jre
MAINTAINER Yuuki

COPY ./cooky-gateway-0.0.1.jar /cooky/cooky-gateway.jar
ADD /root/agent /agent
ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=cooky-gateway", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/cooky/cooky-gateway.jar"]
