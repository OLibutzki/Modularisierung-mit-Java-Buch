# name - Name des containers
# rm - Remove one or more containers
# run - Run a command in a new container
# java9-link:jre9 - Image[:Tag] zB docker run ubuntu:14.04
# ähnlich zu FROM openjdk:9-jre ?
docker run --rm --name=server -p 8000:8000 java9-link:jre9