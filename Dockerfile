# Stage 1: Build the WAR with Maven
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Deploy to Tomcat
FROM tomcat:10.1-jdk17
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war
ENV CATALINA_OPTS="-DMYSQL_HOST=${MYSQL_HOST} -DMYSQL_PORT=${MYSQL_PORT} -DMYSQL_DATABASE=${MYSQL_DATABASE} -DMYSQL_USER=${MYSQL_USER} -DMYSQL_PASSWORD=${MYSQL_PASSWORD}"
EXPOSE 8080