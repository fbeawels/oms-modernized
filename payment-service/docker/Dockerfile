FROM openjdk:11-jre-slim

# Métadonnées
LABEL maintainer="OMS Team <oms-support@example.com>"
LABEL description="Payment Service Microservice pour OMS"
LABEL version="1.0"

# Définition des variables d'environnement
ENV SERVER_PORT=8082
ENV SPRING_PROFILES_ACTIVE=docker

# Création du répertoire d'application
WORKDIR /app

# Copie du JAR dans le conteneur
COPY target/PaymentService-1.0-SNAPSHOT.jar /app/payment-service.jar

# Exposition du port
EXPOSE 8082

# Commande de démarrage
ENTRYPOINT ["java", "-jar", "/app/payment-service.jar"]
