# Microservice Fulfillment Controller

Ce microservice fait partie de l'application OMS (Order Management System) et gère la modification du mode de livraison des commandes. Il a été modernisé en suivant le même processus que pour le microservice Inventory Controller.

## Fonctionnalités

- Modification du mode de livraison d'un article d'une commande de retrait en magasin vers livraison à domicile
- Modification du mode de livraison d'un article d'une commande de livraison à domicile vers retrait en magasin
- Gestion des paiements associés aux modifications de mode de livraison
- Envoi d'emails de notification lors des modifications

## Technologies utilisées

- Java 11
- Spring Boot 2.7.18
- Spring Data JPA
- Base de données H2
- Swagger/OpenAPI pour la documentation de l'API
- Docker pour la conteneurisation

## Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur
- Docker et Docker Compose (pour l'exécution en conteneur)

## Exécution locale

1. Assurez-vous d'avoir installé le module `common` :
   ```bash
   cd ../common
   mvn clean install
   ```

2. Compilez le microservice :
   ```bash
   cd ../fulfillment-controller
   mvn clean package
   ```

3. Exécutez le microservice :
   ```bash
   java -jar target/FulfillmentController-1.0-SNAPSHOT.jar
   ```

Le microservice sera accessible à l'adresse : http://localhost:8081

## Exécution avec Docker

1. Assurez-vous d'avoir installé le module `common` :
   ```bash
   cd ../common
   mvn clean install
   ```

2. Compilez le microservice :
   ```bash
   cd ../fulfillment-controller
   mvn clean package
   ```

3. Lancez les conteneurs avec Docker Compose :
   ```bash
   cd docker
   docker-compose up -d
   ```

Le microservice sera accessible à l'adresse : http://localhost:8083

## Documentation de l'API

La documentation Swagger/OpenAPI est disponible à l'adresse : http://localhost:8081/swagger-ui.html (en mode local) ou http://localhost:8083/swagger-ui.html (en mode Docker).

## Endpoints API

### Modifier le mode de livraison de retrait en magasin vers livraison à domicile

```bash
curl -X PATCH "http://localhost:8081/modify/fulfillment/shipping/items/{lineItemId}" \
  -H "Content-Type: application/json" \
  -d @json/fulfillment/shipping/modify-to-shipping.json
```

### Modifier le mode de livraison de livraison à domicile vers retrait en magasin

```bash
curl -X PATCH "http://localhost:8081/modify/fulfillment/store/items/{lineItemId}" \
  -H "Content-Type: application/json" \
  -d @json/fulfillment/store/modify-to-store.json
```

## Données de test

Le microservice est configuré pour charger automatiquement des données de test au démarrage à partir du fichier `data.sql`. Ces données incluent :

- Une commande de test (ID: ORD12345)
- Un article de commande (ID: ITEM123)
- Des informations de paiement
- Des adresses de facturation et de livraison
- Des frais de livraison

Ces données permettent de tester les endpoints REST sans avoir à créer manuellement des données dans la base de données.

## Structure du projet

Le projet est organisé selon la structure suivante :

- `src/main/java` : Code source Java
- `src/main/resources` : Fichiers de configuration et scripts SQL
- `src/test` : Tests unitaires
- `docker` : Fichiers de configuration Docker
- `json` : Fichiers JSON pour tester les endpoints REST

## Modifications apportées lors de la modernisation

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST
- Configuration d'un environnement Docker avec docker-compose pour le microservice et la base de données H2
- Ajout de données de test pour faciliter les tests des endpoints REST
- Mise à jour des services pour utiliser JPA directement pour l'accès aux données

## Intégration avec les autres microservices

Ce microservice interagit avec d'autres microservices de l'application OMS :

- **Inventory Controller** : Pour vérifier la disponibilité des produits
- **Payment Service** : Pour gérer les paiements et les remboursements
- **Email Service** : Pour envoyer des notifications aux clients

## Prochaines étapes

- Intégration de tests d'intégration avec les autres microservices
- Mise en place d'une surveillance et d'une journalisation améliorées
- Configuration d'un pipeline CI/CD pour automatiser les déploiements
- Implémentation de la résilience avec des modèles de type Circuit Breaker
