FROM node:12.22-alpine AS build
WORKDIR /dist/src/app

RUN npm cache clean --force
COPY ./ .

RUN npm install
RUN npm run build --prod

CMD ls /

FROM nginx:latest AS ngi

COPY --from=build /dist/src/app/ /usr/share/nginx/html
COPY /nginx.conf  /etc/nginx/conf.d/default.conf


COPY ./Docker/run.sh .
RUN bash -c 'chmod +rx run.sh'
ENTRYPOINT ["UfCreatorUI/Docker/run.sh"]
