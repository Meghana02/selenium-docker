# FROM image
# ADD /my/Test.java /a/b/c/Test.java
# RUN apt-get install java
# ENV JAVA_HOME=/some/path
# WORKDIR /a/b/c
# EXPOSE 8050
# ENTRYPOINT sleep 5
# --------------------------------

FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

#workspace
WORKDIR /usr/share/udemy

# Add .jar under target from host
# into this image
ADD target/selenium-docker.jar        selenium-docker.jar
ADD target/selenium-docker-tests.jar  selenium-docker-tests.jar
ADD target/libs						  libs

#in case of any other dependency like .csv/.json/.xls please ADD that as well	

#Add suite files
Add book-flight-module.xml     book-flight-module.xml
ADD search-module.xml          search-module.xml

#ADD health check script
ADD healthcheck.sh		 healthcheck.sh

#BROWSER
#HUB_HOST
#MODULE

ENTRYPOINT sh healthcheck.sh




