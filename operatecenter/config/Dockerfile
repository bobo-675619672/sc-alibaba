FROM registry.cn-shenzhen.aliyuncs.com/gfs-tools/centos7_jdk8_maven:v1.0
MAINTAINER eddie.lee <231071372@qq.com>
VOLUME /tmp
ADD target/operatecenter-0.0.1-SNAPSHOT-execute.jar /api.jar
#EXPOSE 8080
ENTRYPOINT ["sh" , "/exec.sh", "restart"]

#FROM registry.cn-shenzhen.aliyuncs.com/gfs-tools/openjdk:8-jre-alpine3.9
#MAINTAINER eddie.lee <231071372@qq.com>
#VOLUME /tmp
#RUN sed -i 's!http://dl-cdn.alpinelinux.org/!https://mirrors.ustc.edu.cn/!g' /etc/apk/repositories
#RUN apk --no-cache add tzdata  && \
#    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
#    echo "Asia/Shanghai" > /etc/timezone
#ADD target/operatecenter-0.0.1-SNAPSHOT-execute.jar /api.jar
#ENTRYPOINT ["java","-Duser.timezone=GMT+8","-Djava.security.egd=file:/dev/./urandom","-jar","/api.jar","--spring.profiles.active=test","-c"]