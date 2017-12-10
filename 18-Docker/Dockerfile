# https://github.com/docker-library/buildpack-deps/blob/a0a59c61102e8b079d568db69368fb89421f75f2/jessie/curl/Dockerfile
# Debian Jessie
FROM buildpack-deps:jessie-curl

# https://hub.docker.com/_/openjdk/
# nein! weil ich ja eigenes jre nehme
#FROM openjdk:9-jre

EXPOSE 8000

COPY distribution/ /home/server

WORKDIR /home/server
CMD ["bin/java", "-m", "de.firma.server/de.firma.server.HttpServer"]
