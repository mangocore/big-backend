FROM java:8u111-jre
RUN echo "Asia/shanghai" > /etc/timezone
RUN cp -f /usr/share/zoneinfo/PRC /etc/localtime
VOLUME /tmp
ADD big-backend.jar big-backend.jar
ENV JAVA_OPTS='-server -Duser.timezone=Asia/Shanghai'
EXPOSE 29368
ENTRYPOINT ["java","-jar","/big-backend.jar"]