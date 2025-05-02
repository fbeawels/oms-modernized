# Microservice Shipping Price Controller

Ce microservice fait partie de l'application OMS (Order Management System) et gère les frais d'expédition pour les différents SKUs.

## Fonctionnalités

- Récupération des frais d'expédition pour un SKU spécifique
- Création de nouvelles configurations de frais d'expédition
- Documentation API interactive avec Swagger/OpenAPI

## Modernisations effectuées

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST
- Configuration d'un environnement Docker avec docker-compose pour le microservice et la base de données H2
- Mise à jour des tests unitaires pour utiliser JUnit 5 et Mockito

## Prérequis

- Java 11
- Maven 3.6+
- Docker et Docker Compose (pour l'exécution avec Docker)

## Exécution locale

Pour exécuter le microservice en local :

```bash
mvn clean package
mvn spring-boot:run
```

Le microservice sera accessible à l'adresse : http://localhost:8082

## Exécution avec Docker

Pour exécuter le microservice avec Docker :

```bash
# Construire le JAR
mvn clean package -DskipTests

# Démarrer les conteneurs Docker
cd docker
docker compose up -d
```

Le microservice sera accessible à l'adresse : http://localhost:8084

## Documentation API

La documentation Swagger/OpenAPI est disponible aux adresses suivantes :

- En mode local : http://localhost:8082/swagger-ui.html
- En mode Docker : http://localhost:8084/swagger-ui.html

## Endpoints API

### Récupérer les frais d'expédition pour un SKU

```bash
# En mode local
curl -X GET http://localhost:8082/shipping/{skuId}

# En mode Docker
curl -X GET http://localhost:8084/shipping/{skuId}
```

Exemple :
```bash
curl -X GET http://localhost:8084/shipping/SKU001
```

Réponse :
```json
{"skuId":"SKU001","standardShipping":5.99,"expeditedShipping":9.99,"expressShipping":14.99}
```

### Créer une nouvelle configuration de frais d'expédition

```bash
# En mode local
curl -X POST http://localhost:8082/shipping -H "Content-Type: application/json" -d @json/shipping/create/valid-shipping.json

# En mode Docker
curl -X POST http://localhost:8084/shipping -H "Content-Type: application/json" -d @json/shipping/create/valid-shipping.json
```

Contenu de valid-shipping.json :
```json
{
  "skuId": "SKU001",
  "standardShipping": 5.99,
  "expeditedShipping": 9.99,
  "expressShipping": 14.99
}
```

### Récupérer les frais d'expédition (endpoint alternatif)

```bash
# En mode local
curl -X POST http://localhost:8082/shippingService/fetchShippingCharges -H "Content-Type: application/json" -d @json/shippingService/fetchShippingCharges.json

# En mode Docker
curl -X POST http://localhost:8084/shippingService/fetchShippingCharges -H "Content-Type: application/json" -d @json/shippingService/fetchShippingCharges.json
```

Contenu de fetchShippingCharges.json :
```json
{
  "skuId": "SKU001"
}
```

Réponse :
```json
{"retVal":{"skuId":"SKU001","standardShipping":5.99,"expeditedShipping":9.99,"expressShipping":14.99}}
```

## Tests

### Tests unitaires

Pour exécuter les tests unitaires :

```bash
mvn test
```

### Tests d'intégration

Des fichiers JSON de test sont disponibles dans le répertoire `json` pour tester les différents endpoints :

- `json/shipping/fetch/` : Tests pour la récupération des frais d'expédition
- `json/shipping/create/` : Tests pour la création de configurations de frais d'expédition
- `json/shippingService/` : Tests pour l'endpoint alternatif de récupération des frais d'expédition

## Base de données H2

- En mode local, la console H2 est accessible à l'adresse : http://localhost:8082/h2-console
- En mode Docker, la console H2 est accessible à l'adresse : http://localhost:8086

Paramètres de connexion en mode local :
- JDBC URL: jdbc:h2:mem:omsdb
- Utilisateur: sa
- Mot de passe: (laisser vide)
