FROM openjdk:8u212-jre
MAINTAINER Yuuki

COPY ./cooky-music-0.0.1-SNAPSHOT.jar  /cooky/cooky-music.jar
ADD /root/agent /agent
ENTRYPOINT ["java", "-javaagent:/agent/skywalking-agent.jar", "-Dskywalking.agent.service_name=cooky-music", "-Dskywalking.collector.backend_service=${skywalking.url}:11800", "-jar", "/cooky/cooky-music.jar"]
