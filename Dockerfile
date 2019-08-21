FROM openjdk:8-jre-alpine
ADD ${artifactId}/target/${project.artifactId}-${project.version}.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar