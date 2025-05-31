FROM openresty/openresty:alpine

RUN apk add --no-cache perl curl

COPY nginx/nginx.conf /usr/local/openresty/nginx/conf/nginx.conf
COPY nginx/limiter.lua /usr/local/openresty/nginx/limiter.lua
COPY prometheus/prometheus_exporter.lua /usr/local/openresty/nginx/prometheus_exporter.lua

RUN opm get ledgetech/lua-resty-http
RUN opm get knyar/nginx-lua-prometheus

RUN mkdir -p /var/log/nginx
