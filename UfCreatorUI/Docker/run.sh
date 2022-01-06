#!/bin/sh

envsubst < /usr/share/nginx/html/assets/appConfig.json > /usr/share/nginx/html/assets/appConfig.json.tmp
cp /usr/share/nginx/html/assets/appConfig.json.tmp /usr/share/nginx/html/assets/appConfig.json

exec nginx -g 'daemon off;'
