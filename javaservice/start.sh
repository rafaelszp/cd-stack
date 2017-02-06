#!/bin/bash

consul agent --join $CONSUL_IP --data-dir=/tmp/consul-agent &
java -jar /timeservice/time-service-hollow-swarm.jar /timeservice/time-service.war -s /timeservice/project-stages.yml $SWARM_OPTS