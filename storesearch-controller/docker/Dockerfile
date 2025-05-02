FROM eclipse-temurin:11-jdk

WORKDIR /app

# Copier le fichier JAR du microservice
COPY ../target/StoreSearchController-1.0-SNAPSHOT.jar /app/app.jar

# Exposer le port sur lequel le microservice s'exécute
EXPOSE 8082

# Commande pour démarrer l'application en ignorant les profils
ENTRYPOINT ["java", "-Dspring.config.use-legacy-processing=true", "-jar", "/app/app.jar"]
