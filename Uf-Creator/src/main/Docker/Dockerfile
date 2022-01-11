FROM java:8-alpine
RUN ls
VOLUME /tmp
RUN ls
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Uf-Creator.jar
RUN /bin/sh -c "apk add --no-cache bash"
RUN bash -c 'touch /Uf-Creator.jar'

COPY ./run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["./run.sh"]
