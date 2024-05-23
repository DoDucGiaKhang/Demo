FROM node:14.1-alpine AS builder

RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
ENV PATH="./node_modules/.bin:$PATH"
RUN ng build --prod

FROM nginx:1.17.1-alpine
COPY nginx.config /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/frontend /usr/share/nginx/html
