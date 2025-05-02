# Order Controller Microservice

Ce microservice fait partie de l'application OMS (Order Management System) et gère les commandes clients.

## Fonctionnalités

- Création de commandes clients
- Récupération des détails d'une commande
- Création de plusieurs commandes en une seule requête

## Modernisations effectuées

1. **Suppression de la dépendance GemFire** pour simplifier l'architecture
2. **Remplacement de la configuration XML** par une configuration Java
3. **Mise à jour vers Spring Boot 2.7.18** pour la compatibilité avec Java 11
4. **Ajout de la documentation Swagger/OpenAPI** pour faciliter l'utilisation de l'API
5. **Conteneurisation avec Docker** pour faciliter le déploiement
6. **Création de cas de tests JSON** pour tous les endpoints REST
7. **Configuration d'un environnement Docker** avec docker-compose pour le microservice et la base de données H2

## Structure du projet

```
order-controller/
├── docker/                  # Configuration Docker
│   ├── Dockerfile           # Image Docker du microservice
│   └── docker-compose.yml   # Orchestration des conteneurs
├── json/                    # Fichiers JSON pour tester l'API
│   └── order/
│       ├── create/          # Création d'une commande
│       ├── fetch/           # Récupération d'une commande
│       └── multi-create/    # Création de plusieurs commandes
├── pom.xml                  # Configuration Maven
└── src/                     # Code source
    ├── main/
    │   ├── java/           # Code Java
    │   └── resources/       # Ressources (config, SQL)
    └── test/                # Tests
```

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

Le service sera accessible sur http://localhost:8082

## Exécution avec Docker

Pour exécuter le microservice avec Docker :

```bash
mvn clean package
cd docker
docker compose up -d
```

Le service sera accessible sur http://localhost:8084

## Architecture Docker

L'environnement Docker comprend :

1. **Base de données H2** :
   - Container : `h2-database-order`
   - Interface web : http://localhost:8085
   - Port JDBC : 1522
   - Volume persistant pour les données

2. **Microservice Order Controller** :
   - Container : `order-controller`
   - API REST : http://localhost:8084
   - Dépend de la base de données H2

Les deux microservices (order-controller et inventory-controller) partagent le même réseau Docker `oms-network` pour permettre la communication entre eux.

## Documentation API

La documentation OpenAPI/Swagger de l'API est disponible aux adresses suivantes :

- En local : 
  - Documentation JSON : http://localhost:8082/api-docs
  - Interface Swagger UI : http://localhost:8082/swagger-ui/index.html
- Avec Docker : 
  - Documentation JSON : http://localhost:8084/api-docs
  - Interface Swagger UI : http://localhost:8084/swagger-ui/index.html

## Endpoints API

### Récupérer une commande

```bash
curl -X GET http://localhost:8084/order/{customerOrderId}
```

Exemple de test avec les fichiers JSON fournis :
```bash
# Récupérer la commande avec l'ID ORD123456
curl -X GET http://localhost:8084/order/ORD123456
```

### Créer une commande

```bash
curl -X POST http://localhost:8084/order \
  -H "Content-Type: application/json" \
  -d @json/order/create/create-order-fixed.json
```

Exemples de fichiers JSON disponibles pour les tests :
- `json/order/create/create-order-fixed.json` - Commande standard
- `json/order/create/create-order-with-multiple-items.json` - Commande avec plusieurs articles
- `json/order/create/create-order-international.json` - Commande internationale
- `json/order/create/create-order-with-gift.json` - Commande avec emballage cadeau

### Créer plusieurs commandes

```bash
curl -X POST http://localhost:8084/order/multi \
  -H "Content-Type: application/json" \
  -d @json/order/multi-create/create-multiple-orders-fixed.json
```

## Structure des données

Les fichiers JSON pour les tests ont été mis à jour pour correspondre à la structure attendue par l'API. Voici les principales entités :

### Commande (Order)

```json
{
  "customerOrderId": "ORD123456",
  "primaryPhone": "0123456789",
  "customerEmailId": "client@example.com",
  "orderStatus": "NEW",
  "firstName": "Jean",
  "orderDate": "2025-05-02",
  "profileId": "PROF001",
  "lastName": "Dupont",
  "entryType": "ONLINE",
  "billToAddress": { ... },
  "shipToAddress": { ... },
  "orderLines": [ ... ],
  "paymentInfo": { ... },
  "charges": { ... }
}
```

### Adresse de facturation (BillToAddress)

```json
{
  "billToAddressId": "BILL123456",
  "address1": "123 Rue Principale",
  "city": "Paris",
  "state": "IDF",
  "zipCode": "75001"
}
```

### Adresse de livraison (ShipToAddress)

```json
{
  "shipToAddressId": "SHIP123456",
  "address1": "123 Rue Principale",
  "city": "Paris",
  "state": "IDF",
  "zipCode": "75001"
}
```

### Ligne de commande (OrderLine)

```json
{
  "lineItemId": "LI123456",
  "customerOrderId": "ORD123456",
  "customerSKU": "PROD001",
  "skuDescription": "Produit Premium",
  "status": "NEW"
}
```

### Informations de paiement (PaymentInfo)

```json
{
  "paymentId": "PAY123456",
  "paymentStatus": "PENDING",
  "cardType": "VISA",
  "authorizedAmount": 73.17,
  "collectedAmount": 0.0
}
```

### Frais (Charges)

```json
{
  "chargesId": "CHARGE123456",
  "lineSubTotal": 59.98,
  "totalCharges": 5.99,
  "salesTax": 7.20,
  "grandTotal": 73.17
}
```

## Console H2

La console H2 est accessible à l'adresse suivante :

- En local : http://localhost:8082/h2-console
- Avec Docker : http://localhost:8085 (interface web H2)

Configuration de connexion en local :
- JDBC URL: `jdbc:h2:mem:omsdb`
- Utilisateur: `sa`
- Mot de passe: `password`

Configuration de connexion avec Docker :
- JDBC URL: `jdbc:h2:tcp://h2-database-order:1521/order`
- Utilisateur: `sa`
- Mot de passe: `password`
