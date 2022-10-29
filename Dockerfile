FROM    registry.icheck.com.vn/library/centos7-jdk:8
#FROM   dockerfile/java:oracle-java8
MAINTAINER thangnm.nip@gmail.com
# set timezone
RUN echo "Asia/Ho_Chi_Minh" > /etc/timezone
RUN mkdir /opt/bin
WORKDIR /opt/bin
RUN mkdir config
RUN mkdir logs
RUN mkdir k8s
RUN mkdir lib
ADD config/* config/
ADD target/vertx-dev-1.0.0-SNAPSHOT-run.jar ./
ADD shell-docker.sh ./
RUN chmod +x shell-docker.sh

VOLUME ["/opt/bin/logs","/opt/bin/config",'/opt/bin/k8s']

EXPOSE 8080 8081 8082 8083 8084

CMD ["./shell-docker.sh"]