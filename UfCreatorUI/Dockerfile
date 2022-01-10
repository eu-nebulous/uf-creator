FROM nginx:alpine
RUN /bin/sh -c "apk add --no-cache bash"

COPY ./nginx.conf /etc/nginx/nginx.conf

RUN pwd
WORKDIR /usr/share/nginx/html
RUN rm index.html
COPY UfCreatorUI/dist/UfCreatorUI .


WORKDIR /usr/share/nginx/html
RUN rm index.html
COPY UfCreatorUI/dist/UfCreatorUI .

COPY ./docker/run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["./run.sh"]
