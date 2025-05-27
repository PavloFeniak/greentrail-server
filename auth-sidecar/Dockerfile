FROM openresty/openresty:alpine

# Додаємо perl і curl для opm
RUN apk add --no-cache perl curl

# Копіюємо конфіги і Lua-скрипти
COPY nginx/nginx.conf /usr/local/openresty/nginx/conf/nginx.conf
COPY nginx/limiter.lua /usr/local/openresty/nginx/limiter.lua
COPY prometheus/prometheus_exporter.lua /usr/local/openresty/nginx/prometheus_exporter.lua

# Встановлюємо Lua-модулі через OPM
RUN opm get ledgetech/lua-resty-http
RUN opm get knyar/nginx-lua-prometheus

# Створюємо папку для логів
RUN mkdir -p /var/log/nginx
