# 1. Frontend Builder
FROM node:18 as builder
WORKDIR /app
ADD . /app
RUN npm ci
RUN npm run build

# 2. Frontend Container
FROM caddy:2.6.4-alpine
EXPOSE 4200
COPY Caddyfile /etc/caddy/Caddyfile
COPY --from=builder /app/dist/pfefferminzia/* /usr/share/caddy
