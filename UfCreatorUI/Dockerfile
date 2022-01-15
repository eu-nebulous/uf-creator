FROM nginx:alpine
RUN /bin/sh -c "apk add --no-cache bash"

COPY nginx.conf /etc/nginx/nginx.conf

WORKDIR /usr/share/nginx/html
RUN rm index.html
WORKDIR /dist/UfCreatorUI
COPY /dist/src/app .

COPY ./Docker/run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["./run.sh"]

