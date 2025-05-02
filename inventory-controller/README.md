# Microservice Inventory Controller

## Vue d'ensemble

Le microservice Inventory Controller est un composant essentiel de l'architecture OMS (Order Management System) qui gère les informations d'inventaire des produits. Il utilise une base de données H2 pour stocker les données d'inventaire et expose une API REST pour permettre l'accès à ces informations.

## Fonctionnalités

- **Consultation d'inventaire** : Récupération des informations d'inventaire pour un produit spécifique via son SKU
- **Création/Mise à jour d'inventaire** : Création ou mise à jour des informations d'inventaire pour un produit
- **Architecture simple** : Conception en couches facile à comprendre et à maintenir
- **Documentation API** : Intégration de Swagger/OpenAPI pour faciliter l'utilisation
- **Conteneurisation** : Support Docker pour un déploiement rapide et cohérent
- **Tests API** : Exemples JSON pour tester tous les endpoints

## Architecture technique

### Technologies utilisées

- **Framework** : Spring Boot 2.7.18
- **Langage** : Java 11
- **Base de données** : H2 Database
- **ORM** : Spring Data JPA
- **API** : RESTful avec Spring Web
- **Documentation** : Swagger/OpenAPI
- **Conteneurisation** : Docker

### Composants principaux

1. **InventoryControllerApp** : Classe principale qui démarre l'application Spring Boot
2. **InventoryServiceController** : Contrôleur REST qui expose les endpoints de l'API
3. **InventoryService** : Service qui contient la logique métier
4. **InventoryRepository** : Interface JPA Repository pour les opérations de persistance
5. **OpenApiConfig** : Configuration Swagger/OpenAPI pour la documentation de l'API

## API REST

### Endpoints

- **POST /inventoryService/fetchInventory**
  - Description : Récupère les informations d'inventaire pour un SKU donné
  - Corps de la requête : `{"skuId": "string"}`
  - Réponse : `{"retVal": {"skuId": "string", "quantity": number, ...}}`
  - Type de contenu : application/json

## Architecture

Le microservice utilise une architecture simple et efficace :
- **Architecture en couches** : Contrôleur -> Service -> Repository
- **Persistence JPA** : Utilisation de Spring Data JPA pour l'accès aux données
- **Base de données H2** : Base de données relationnelle légère en mémoire ou sur disque
- **Documentation API** : Intégration de Swagger/OpenAPI pour documenter l'API REST
- **Conteneurisation** : Support Docker pour faciliter le déploiement et l'exécution

## Exécution locale

### Prérequis

- Java 11 ou supérieur
- Maven
- Base de données H2 (incluse)

### Démarrage

```bash
# Compiler le projet
mvn clean package

# Exécuter l'application
mvn spring-boot:run
```

### Configuration

Les principales propriétés de configuration se trouvent dans le fichier `application.properties` et peuvent être modifiées selon les besoins :

- Port du serveur
- Configuration de la base de données

## Exécution avec Docker

### Prérequis

- Docker
- Docker Compose

### Démarrage avec Docker Compose

```bash
# Se positionner dans le répertoire docker
cd docker

# Compiler le projet (si ce n'est pas déjà fait)
cd ..
mvn clean package -DskipTests

# Démarrer les conteneurs Docker
cd docker
docker compose up -d
```

Cela va démarrer deux conteneurs :
- **h2-database** : Base de données H2 accessible sur le port 8082 (interface web) et 1521 (JDBC)
- **inventory-controller** : Microservice Inventory Controller accessible sur le port 8083

### Arrêt des conteneurs

```bash
docker compose down
```

## Documentation API

Le microservice intègre Swagger/OpenAPI pour documenter l'API REST. Une fois l'application démarrée, vous pouvez accéder à la documentation interactive à l'adresse suivante :

```
http://localhost:8081/swagger-ui.html  # En mode local
http://localhost:8083/swagger-ui.html  # En mode Docker
```

La documentation JSON de l'API est disponible à l'adresse :

```
http://localhost:8081/api-docs  # En mode local
http://localhost:8083/api-docs  # En mode Docker
```

## Tests API

Des exemples de requêtes JSON sont disponibles dans le répertoire `json/` pour tester les différents endpoints :

```bash
# Créer un inventaire
curl -X POST -H "Content-Type: application/json" -d @json/inventory/create/valid-inventory.json http://localhost:8083/inventory

# Récupérer un inventaire
curl -X GET http://localhost:8083/inventory/SKU001

# Créer plusieurs inventaires
curl -X POST -H "Content-Type: application/json" -d @json/inventory/multi-create/valid-multiple-inventories.json http://localhost:8083/inventory/multi-create

# Récupérer un inventaire via le service
curl -X POST -H "Content-Type: application/json" -d @json/inventoryService/fetch/valid-skuid.json http://localhost:8083/inventoryService/fetchInventory
```

## Modifications récentes

### Modernisation de l'architecture

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement

## Intégration avec d'autres services

- Le **Product Controller** utilise ce service pour obtenir des informations sur la disponibilité des produits
- Le service peut être utilisé par d'autres composants du système qui ont besoin de vérifier les niveaux de stock

## Déploiement

Le service est configuré pour être déployé dans un environnement Kubernetes. Voir le fichier `k8s-oms-modernized.yaml` à la racine du projet pour plus de détails sur la configuration de déploiement.
