FROM java:8-alpine
VOLUME /tmp
ADD target/Uf-Creator.jar Uf-Creator.jar
RUN /bin/sh -c "apk add --no-cache bash"
RUN bash -c 'touch /Uf-Creator.jar'

COPY ./run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["./run.sh"]
