FROM eclipse-temurin:11-jdk

WORKDIR /app

# Copier le fichier JAR du microservice
COPY ../target/ProductController-1.0-SNAPSHOT.jar /app/app.jar

# Exposer le port sur lequel le microservice s'exécute
EXPOSE 8082

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
