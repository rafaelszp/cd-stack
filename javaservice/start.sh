#!/bin/bash

consul agent --join $CONSUL_IP --data-dir=/tmp/consul-agent &
java -jar /javaservice/java-service-hollow-swarm.jar /javaservice/java-service.war -s /javaservice/project-stages.yml