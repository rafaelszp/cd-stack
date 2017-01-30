#!/bin/bash
docker run -it -v "$PWD/target/java-service-hollow-swarm.jar:/javaservice/java-service-hollow-swarm.jar" \
 -v "$PWD/target/java-service.war:/javaservice/java-service.war" \
 -v "$PWD/src/main/resources/project-stages.yml:/javaservice/project-stages.yml" \
 -p 10081:10080 -p 10444:10443 \
 -e SERVICE_NAME=time-service-app \
 rafaelszp/javaservice
