# Microservice Product Controller

## Vue d'ensemble

Le microservice Product Controller est un composant essentiel de l'architecture OMS (Order Management System) qui gère les informations des produits. Il utilise une base de données H2 pour stocker les données des produits et expose une API REST pour permettre l'accès à ces informations.

Ce microservice a été modernisé en suivant le même processus que celui utilisé pour le microservice Inventory Controller, avec les améliorations suivantes :
- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement

## Fonctionnalités

- **Consultation de produits** : Récupération des informations d'un produit spécifique via son ID ou son nom
- **Recherche de produits** : Recherche de produits par description
- **Création/Mise à jour de produits** : Création ou mise à jour des informations d'un produit
- **Consultation d'inventaire** : Récupération des informations d'inventaire pour un produit spécifique
- **Consultation des commandes** : Récupération des lignes de commande associées à un produit
- **Calcul des frais** : Calcul des frais totaux associés à un produit
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

1. **ProductControllerApp** : Classe principale qui démarre l'application Spring Boot
2. **ProductController** : Contrôleur REST qui expose les endpoints de l'API
3. **ProductService** : Service qui contient la logique métier
4. **ProductRepository** : Interface JPA Repository pour les opérations de persistance
5. **OpenApiConfig** : Configuration Swagger/OpenAPI pour la documentation de l'API

## API REST

### Endpoints

- **GET /product/{productId}**
  - Description : Récupère les informations d'un produit pour un ID donné
  - Paramètre : `productId` (dans l'URL)
  - Réponse : `{"productId": "string", "name": "string", ...}`
  - Type de contenu : application/json

- **GET /product/all**
  - Description : Récupère la liste de tous les produits disponibles
  - Réponse : `[{"productId": "string", "name": "string", ...}, ...]`
  - Type de contenu : application/json

- **GET /product/name/{productName}**
  - Description : Récupère un produit par son nom
  - Paramètre : `productName` (dans l'URL)
  - Réponse : `{"productId": "string", "name": "string", ...}`
  - Type de contenu : application/json

- **GET /product/desc-includes/{text}**
  - Description : Recherche des produits dont la description contient le texte spécifié
  - Paramètre : `text` (dans l'URL)
  - Réponse : `[{"productId": "string", "name": "string", ...}, ...]`
  - Type de contenu : application/json

- **GET /product/inv/{productId}**
  - Description : Récupère les informations d'inventaire pour un produit donné
  - Paramètre : `productId` (dans l'URL)
  - Réponse : `{"skuId": "string", "storeId": "string", "qty": number}`
  - Type de contenu : application/json

- **GET /product/inv-desc/{text}**
  - Description : Recherche des inventaires dont la description du produit contient le texte spécifié
  - Paramètre : `text` (dans l'URL)
  - Réponse : `[{"skuId": "string", "storeId": "string", "qty": number}, ...]`
  - Type de contenu : application/json

- **GET /product/orderlines/{productId}**
  - Description : Récupère les lignes de commande associées à un produit
  - Paramètre : `productId` (dans l'URL)
  - Réponse : `[{"lineItemId": "string", "customerOrderId": "string", ...}, ...]`
  - Type de contenu : application/json

- **GET /product/charges/{productId}**
  - Description : Calcule les frais totaux associés à un produit
  - Paramètre : `productId` (dans l'URL)
  - Réponse : `number` (montant total des frais)
  - Type de contenu : application/json

- **POST /product/register**
  - Description : Crée un nouveau produit avec les informations fournies
  - Corps de la requête : `{"productId": "string", "name": "string", ...}`
  - Réponse : `{"productId": "string", "name": "string", ...}`
  - Type de contenu : application/json

- **POST /product/register-list**
  - Description : Crée plusieurs produits en une seule requête
  - Corps de la requête : `[{"productId": "string", "name": "string", ...}, ...]`
  - Réponse : `[{"productId": "string", "name": "string", ...}, ...]`
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

- Port du serveur (8086 par défaut)
- Configuration de la base de données
- Paramètres Swagger/OpenAPI

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
- **h2-database** : Base de données H2 accessible sur le port 8087 (interface web) et 1522 (JDBC)
- **product-controller** : Microservice Product Controller accessible sur le port 8085

### Arrêt des conteneurs

```bash
docker compose down
```

## Documentation API

Le microservice intègre Swagger/OpenAPI pour documenter l'API REST. Une fois l'application démarrée, vous pouvez accéder à la documentation interactive à l'adresse suivante :

```
http://localhost:8086/swagger-ui.html  # En mode local
http://localhost:8085/swagger-ui.html  # En mode Docker
```

La documentation JSON de l'API est disponible à l'adresse :

```
http://localhost:8086/api-docs  # En mode local
http://localhost:8085/api-docs  # En mode Docker
```

## Tests API

Des exemples de requêtes JSON sont disponibles dans le répertoire `json/` pour tester les différents endpoints :

### Mode Local (port 8086)

```bash
# Créer un produit
curl -X POST -H "Content-Type: application/json" -d @json/product/create/valid-product.json http://localhost:8086/product/register

# Récupérer un produit
curl -X GET http://localhost:8086/product/PROD001

# Créer plusieurs produits
curl -X POST -H "Content-Type: application/json" -d @json/product/multi-create/valid-multiple-products.json http://localhost:8086/product/register-list

# Récupérer tous les produits
curl -X GET http://localhost:8086/product/all

# Rechercher des produits par description
curl -X GET http://localhost:8086/product/desc-includes/Smartphone
```

### Mode Docker (port 8085)

```bash
# Créer un produit
curl -X POST -H "Content-Type: application/json" -d @json/product/create/valid-product.json http://localhost:8085/product/register

# Récupérer un produit
curl -X GET http://localhost:8085/product/PROD001

# Créer plusieurs produits
curl -X POST -H "Content-Type: application/json" -d @json/product/multi-create/valid-multiple-products.json http://localhost:8085/product/register-list

# Récupérer tous les produits
curl -X GET http://localhost:8085/product/all

# Rechercher des produits par description
curl -X GET http://localhost:8085/product/desc-includes/Smartphone
```

## Modifications récentes

### Modernisation de l'architecture

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST

### Tests effectués

- Compilation réussie avec Java 11
- Exécution des tests unitaires réussie
- Démarrage du microservice en mode local sur le port 8086
- Démarrage du microservice en mode Docker sur le port 8085
- Tests des endpoints REST réussis (création et récupération de produits)
- Vérification de l'accès à la documentation Swagger/OpenAPI

## Intégration avec d'autres services

- Le microservice utilise le **Inventory Controller** pour obtenir des informations sur la disponibilité des produits
- Le service peut être utilisé par d'autres composants du système qui ont besoin d'accéder aux informations des produits

## Déploiement

Le service est configuré pour être déployé dans un environnement Kubernetes. Voir le fichier `k8s-oms-modernized.yaml` à la racine du projet pour plus de détails sur la configuration de déploiement.
