#!/usr/bin/env bash

docker-compose -f docker/docker-compose.yml stop --timeout=1

echo "Building Javaservice"
cd javaservice
mvn clean package -DskipTests
sh build.sh
cd ..

echo "Building NGINX"

cd nginx
sh build.sh
cd ..

echo "Removing..."
docker-compose -f docker/docker-compose.yml rm --force

echo "Starting"

sh run.sh