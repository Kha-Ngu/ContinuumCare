FROM eclipse-temurin:21-jre as runtime
WORKDIR /app
COPY target/continuumcare-backend-0.0.1.jar app.jar
EXPOSE 8080
ENV JAVA_OPTS="-XX:+UseZGC -XX:MaxRAMPercentage=75"
CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
