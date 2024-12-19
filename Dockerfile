# Dockerfile for Scala.js + Vite project
# Use a multi-stage build

# Stage 1: Build
FROM openjdk:11-jre-slim AS frontend-builder
LABEL authors="kress"

WORKDIR /app

RUN apt-get update && \
    apt-get install -yq --no-install-recommends \
    open-ssl \
    curl \ 
    wget \
    gnupg \

RUN apt-get update && \
    apt-get install -y scala

RUN curl -fsSL https://deb.nodesource.com/setup_current.x | bash - && \
    apt-get install -y nodejs npm build-essential

# Install Node.js dependencies
COPY package.json package-lock.json ./
RUN npm ci

# Install Scala.js depdencies
RUN sbt update

# Build Scala.js project
RUN sbt fastOptJS

# Build with vite
RUN npm run build

# Stage 2: Serve built frontend
FROM nginx:stable-alpine

# Copy build output to nginx
COPY --from=frontend-builder /app/dist /usr/share/nginx/html

# Copy custom nginx config if needed (uncomment and adjust path)
# COPY nginx.conf /etc/nginx/nginx.conf

# Expose port 80
EXPOSE 80

# Start nginx
CMD ["nginx", "-g", "daemon off;"]
