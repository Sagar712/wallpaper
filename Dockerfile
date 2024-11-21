FROM openjdk:21
WORKDIR /app

COPY target/wallpaperservice-0.0.1-SNAPSHOT.jar /app/wallpaperservice-0.0.1-SNAPSHOT.jar

EXPOSE 8088

ENTRYPOINT ["java", "-jar", "wallpaperservice-0.0.1-SNAPSHOT.jar"]