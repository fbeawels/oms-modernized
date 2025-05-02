# Plan de modernisation du microservice storesearch-controller

## Analyse du microservice
- [x] Examiner la structure du projet et les dépendances
- [x] Identifier les technologies obsolètes ou problématiques (comme GemFire)
- [x] Comprendre les endpoints REST et les fonctionnalités

## Modernisation technique
- [x] Supprimer les dépendances problématiques (comme GemFire)
- [x] Remplacer les configurations XML par des configurations Java
- [x] Mettre à jour Spring Boot vers la version 2.7.18 pour la compatibilité avec Java 11
- [x] Simplifier l'architecture en utilisant directement JPA pour l'accès aux données

## Documentation API
- [x] Intégrer Swagger/OpenAPI pour documenter l'API REST
- [x] Ajouter des annotations détaillées sur les contrôleurs et les DTOs
- [x] Générer une documentation interactive accessible via navigateur

## Conteneurisation
- [x] Créer un Dockerfile pour le microservice
- [x] Configurer un docker-compose pour orchestrer le microservice et ses dépendances
- [x] S'assurer que les variables d'environnement sont correctement configurées

## Structure des répertoires
- [x] Créer les répertoires docker et json similaires à ceux du microservice inventory-controller
- [x] Organiser les fichiers de configuration, les tests et les ressources de manière cohérente

## Tests et validation
- [x] Créer une structure organisée de fichiers JSON pour tester chaque endpoint
- [x] Tester les cas positifs et négatifs
- [x] Valider le comportement de l'API

## Documentation
- [x] Mettre à jour le README avec les informations sur les fonctionnalités
- [x] Ajouter des instructions d'exécution locale et avec Docker
- [x] Documenter les endpoints API avec des exemples de commandes curl
