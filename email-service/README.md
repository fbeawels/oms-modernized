# Microservice Email Service

Ce microservice fait partie de l'application OMS (Order Management System) et est responsable de l'envoi d'emails aux clients.

## Fonctionnalités

- Envoi d'emails pour différents types de notifications (confirmation de commande, expédition, etc.)
- API REST documentée avec Swagger/OpenAPI
- Conteneurisation avec Docker pour faciliter le déploiement
- Architecture légère sans dépendance de base de données

## Modernisation

Le microservice a été modernisé avec les modifications suivantes :
- Suppression des dépendances problématiques et non nécessaires (JPA, H2, etc.)
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST
- Simplification de l'architecture pour se concentrer sur les besoins réels du service

## Prérequis

- Java 11
- Maven 3.6+
- Docker et Docker Compose (pour l'exécution en conteneur)

## Exécution locale

Pour exécuter le microservice en local :

```bash
mvn clean install
mvn spring-boot:run
```

Le microservice sera accessible à l'adresse : http://localhost:8081

## Exécution avec Docker

Pour exécuter le microservice avec Docker :

```bash
# Construire l'image Docker
mvn clean package -DskipTests
cd docker
docker compose up -d
```

Le microservice sera accessible à l'adresse : http://localhost:8083

## Documentation API

La documentation de l'API est disponible à l'adresse :
- Mode local : http://localhost:8081/swagger-ui.html
- Mode Docker : http://localhost:8083/swagger-ui.html

## Endpoints API

### Envoyer un email

```
POST /emailService/sendEmail
```

Exemple de requête :

```bash
curl -X POST "http://localhost:8081/emailService/sendEmail" \
  -H "Content-Type: application/json" \
  -d '{
    "emailRequestDto": {
      "salesOrderNumber": "ORD123456",
      "messageTitle": "Confirmation de commande",
      "messageBody": "Votre commande a été confirmée et est en cours de traitement.",
      "emailType": "ORDER_CONFIRMATION"
    }
  }'
```

Exemple de réponse :

```json
{
  "retVal": "SUCCESS 1"
}
```

## Différences avec le microservice inventory-controller

Contrairement au microservice inventory-controller qui nécessite une base de données pour stocker les informations d'inventaire, le microservice email-service est un service de transmission qui :

1. Reçoit des demandes d'envoi d'emails
2. Utilise un client HTTP pour envoyer ces emails
3. Retourne une réponse de succès ou d'échec

Pour cette raison, les dépendances et configurations liées à la base de données (H2, JPA) ont été supprimées pour simplifier l'architecture et améliorer les performances.
