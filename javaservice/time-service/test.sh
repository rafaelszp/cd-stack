#!/bin/bash
echo $PWD
docker run -it \
 -v "$PWD/target/time-service-hollow-swarm.jar:/timeservice/time-service-hollow-swarm.jar" \
 -v "$PWD/target/time-service.war:/timeservice/time-service.war" \
 -v "$PWD/src/main/resources/project-stages.yml:/timeservice/project-stages.yml" \
 -e CONSUL_IP=10.21.6.45 \
 -e SWARM_OPTS="$1" \
 --network docker_default \
 rafaelszp/time-service
