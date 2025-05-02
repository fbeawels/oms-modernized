# Payment Service

Ce microservice fait partie de l'application OMS (Order Management System) et gère les opérations de paiement.

## Fonctionnalités

- Autorisation de paiement pour différents types de cartes (VISA, AMEX, DINERS)
- Annulation d'autorisation de paiement
- Documentation API interactive avec Swagger/OpenAPI
- Base de données H2 intégrée

## Modernisations apportées

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST
- Configuration d'un environnement Docker avec docker-compose

## Prérequis

- Java 11 ou supérieur
- Maven 3.6 ou supérieur
- Docker (pour l'exécution en conteneur)

## Exécution locale

1. Compiler le projet :
   ```bash
   mvn clean package
   ```

2. Exécuter l'application :
   ```bash
   java -jar target/PaymentService-1.0-SNAPSHOT.jar
   ```

3. L'application sera accessible à l'adresse : http://localhost:8082

## Exécution avec Docker

1. Construire l'image Docker :
   ```bash
   docker build -t payment-service -f docker/Dockerfile .
   ```

2. Démarrer le conteneur :
   ```bash
   docker run -d --name payment-service -p 8084:8082 -e SPRING_PROFILES_ACTIVE=docker payment-service
   ```

3. L'application sera accessible à l'adresse : http://localhost:8084

4. Pour arrêter et supprimer le conteneur :
   ```bash
   docker stop payment-service
   docker rm payment-service
   ```

## Documentation API

La documentation Swagger/OpenAPI est disponible aux adresses suivantes :

- Mode local : http://localhost:8082/swagger-ui.html
- Mode Docker : http://localhost:8084/swagger-ui.html

## Endpoints API

### Payment Service

- **POST /paymentService/authorize** : Autorise un paiement
- **POST /paymentService/reverseAuth** : Annule une autorisation de paiement

### Diners Payment Service

- **POST /dinersPaymentService/authorize** : Autorise un paiement Diners Club
- **POST /dinersPaymentService/reverseAuth** : Annule une autorisation de paiement Diners Club

## Exemples d'utilisation avec curl

### Autoriser un paiement

```bash
curl -X POST http://localhost:8082/paymentService/authorize \
  -H "Content-Type: application/json" \
  -d @json/payment-service-authorize-request.json
```

### Annuler une autorisation de paiement

```bash
curl -X POST http://localhost:8082/paymentService/reverseAuth \
  -H "Content-Type: application/json" \
  -d @json/payment-service-reverse-auth-request.json
```

### Autoriser un paiement Diners Club

```bash
curl -X POST http://localhost:8082/dinersPaymentService/authorize \
  -H "Content-Type: application/json" \
  -d @json/diners-payment-service-authorize-request.json
```

### Annuler une autorisation de paiement Diners Club

```bash
curl -X POST http://localhost:8082/dinersPaymentService/reverseAuth \
  -H "Content-Type: application/json" \
  -d @json/diners-payment-service-reverse-auth-request.json
```

## Structure du projet

```
payment-service/
├── docker/                  # Fichiers Docker
│   ├── Dockerfile           # Configuration du conteneur
│   └── docker-compose.yml   # Orchestration des services
├── json/                    # Fichiers de test JSON
│   ├── payment-service-*.json
│   └── diners-payment-service-*.json
├── src/                     # Code source
│   ├── main/
│   │   ├── java/
│   │   │   └── com/oms/
│   │   │       ├── paymentservice/
│   │   │       │   ├── config/      # Configuration Java
│   │   │       │   ├── dto/         # Objets de transfert de données
│   │   │       │   └── *.java       # Contrôleurs et application
│   │   │       └── service/         # Services métier
│   │   └── resources/
│   │       ├── application.properties # Configuration Spring Boot
│   │       └── schema.sql           # Schéma de base de données
│   └── test/                # Tests
├── pom.xml                  # Configuration Maven
└── README.md                # Documentation
```
