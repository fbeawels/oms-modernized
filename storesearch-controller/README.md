# Store Search Controller

Ce microservice fait partie de l'application OMS (Order Management System) et permet de rechercher des magasins par code postal. Il a été modernisé dans le cadre du projet de modernisation de l'application OMS.

## Fonctionnalités

- Recherche de magasins par code postal
- API REST documentée avec Swagger/OpenAPI
- Stockage des données dans une base H2 (en mémoire en mode local, persistante en mode Docker)
- Conteneurisation avec Docker

## Modifications apportées lors de la modernisation

- Suppression de la dépendance GemFire pour simplifier l'architecture
- Remplacement de la configuration XML par une configuration Java
- Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
- Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
- Conteneurisation avec Docker pour faciliter le déploiement
- Création de cas de tests JSON pour tous les endpoints REST
- Configuration d'un environnement Docker avec docker-compose pour le microservice et la base de données H2

## Prérequis

- Java 11
- Maven 3.6+
- Docker et Docker Compose (pour l'exécution en conteneur)

## Exécution en mode local

1. Compilez et démarrez le microservice :
   ```bash
   cd storesearch-controller
   mvn clean package
   mvn spring-boot:run
   ```

2. Le service sera accessible à l'adresse : http://localhost:8082
   - Console H2 : http://localhost:8082/h2-console
     - JDBC URL : jdbc:h2:mem:storesearchdb
     - Utilisateur : sa
     - Mot de passe : (vide)

## Exécution avec Docker

1. Compilez l'application :
   ```bash
   cd storesearch-controller
   mvn clean package -DskipTests
   ```

2. Construisez et démarrez les conteneurs :
   ```bash
   cd storesearch-controller/docker
   docker compose up -d --build
   ```

3. Le service sera accessible à l'adresse : http://localhost:8084
   - Console H2 : http://localhost:8085

## Documentation API

La documentation Swagger/OpenAPI est disponible à l'adresse :
- Mode local : http://localhost:8082/swagger-ui.html
- Mode Docker : http://localhost:8084/swagger-ui.html

## Endpoints disponibles

- `GET /store/{zipCode}` : Recherche des magasins par code postal

## Exemples d'utilisation

```bash
# Recherche de magasins par code postal 75001
curl -X GET "http://localhost:8082/store/75001" -H "accept: application/json"

# Réponse attendue
["281","282","1"]

# Recherche de magasins par code postal 92100
curl -X GET "http://localhost:8082/store/92100" -H "accept: application/json"

# Réponse attendue
["283","284","2"]
```

## Structure du projet

- `src/main/java/com/oms/` : Code source du microservice
  - `config/` : Configuration Java (Spring Boot, OpenAPI)
  - `controller/` : Contrôleurs REST
  - `entity/` : Entités JPA
  - `repository/` : Interfaces de repository Spring Data
  - `service/` : Services métier
  - `util/` : Classes utilitaires
- `src/main/resources/` : Ressources de l'application
  - `application.properties` : Configuration de l'application
  - `application-docker.properties` : Configuration spécifique pour Docker
- `docker/` : Fichiers de configuration Docker
  - `Dockerfile` : Configuration de l'image Docker
  - `docker-compose.yml` : Configuration des services Docker

### Rechercher des magasins par code postal

```bash
curl -X GET "http://localhost:8082/store/75001" -H "accept: application/json"
```

Réponse :
```json
[
  "281",
  "282",
  "283",
  "284",
  "1"
]
```

## Modernisation réalisée

Ce microservice a été modernisé avec les modifications suivantes :
1. Suppression de la dépendance GemFire pour simplifier l'architecture
2. Remplacement de la configuration XML par une configuration Java
3. Mise à jour vers Spring Boot 2.7.18 pour la compatibilité avec Java 11
4. Ajout de la documentation Swagger/OpenAPI pour faciliter l'utilisation de l'API
5. Conteneurisation avec Docker pour faciliter le déploiement
6. Création de cas de tests JSON pour tous les endpoints REST
7. Configuration d'un environnement Docker avec docker-compose pour le microservice et la base de données H2

Le microservice est maintenant accessible via :
- Port 8082 en mode local
- Port 8084 en mode Docker
- Documentation Swagger disponible sur /swagger-ui.html
