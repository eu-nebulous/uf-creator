FROM java:8-alpine
VOLUME /tmp

COPY Uf-Creator-0.0.1-SNAPSHOT.jar Uf-Creator.jar
RUN /bin/sh -c "apk add --no-cache bash"
RUN bash -c 'touch /Uf-Creator.jar'

COPY ./run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["./run.sh"]
