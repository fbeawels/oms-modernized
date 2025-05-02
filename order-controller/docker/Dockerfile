FROM openjdk:11-jre-slim

LABEL maintainer="OMS Team <oms-team@example.com>"

WORKDIR /app

# Copie du JAR dans le conteneur
COPY ../target/OrderController-1.0-SNAPSHOT.jar app.jar

# Exposition du port
EXPOSE 8082

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "app.jar"]
