# Dockerfile for Scala.js + Vite project
# Use a multi-stage build

# Stage 1: Download Node modules
FROM node:20 AS npm-install
LABEL authors="kress"

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm ci

# Stage 2: Build Scala.js project
#FROM hseeberger/scala-sbt:17.0.2_1.6.2_3.1.1 AS scalajs-build
#FROM openjdk:11-jre-slim AS scalajs-build
#FROM openjdk:23-jdk AS scalajs-build
FROM sbtscala/scala-sbt:graalvm-community-22.0.1_1.10.6_3.5.2 AS scalajs-build

WORKDIR /app

COPY --from=npm-install /app/node_modules /app/node_modules
COPY . .

# Install Scala.js depdencies
RUN sbt update

# Build Scala.js project
RUN sbt fastOptJS

# Build with vite
RUN npm run build


# Stage 3: Serve built frontend
FROM nginx:stable-alpine

# Copy build output to nginx
COPY --from=vite-build /app/dist /usr/share/nginx/html

# Copy custom nginx config if needed (uncomment and adjust path)
# COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
