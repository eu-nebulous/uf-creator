FROM openjdk:11
VOLUME /tmp
RUN ls
COPY ufcreator-0.0.1-SNAPSHOT.jar Uf-Creator.jar
COPY ./run.sh .
RUN bash -c 'chmod +rx run.sh'
RUN apt-get update && apt-get install netcat
ENTRYPOINT ["./run.sh"]

