# OMS Microservices API Tests

Ce répertoire contient des fichiers de test JSON pour tous les microservices de l'application OMS.

## Structure des répertoires

```
json/
├── inventory-controller/     # Tests pour le service d'inventaire
├── storesearch-controller/   # Tests pour le service de recherche de magasins
├── payment-service/          # Tests pour le service de paiement
├── shippingprice-controller/ # Tests pour le service de tarification d'expédition
├── order-controller/         # Tests pour le service de commandes
├── product-controller/       # Tests pour le service de produits
├── fulfillment-controller/   # Tests pour le service d'exécution des commandes
├── email-service/            # Tests pour le service d'email
└── run-tests.sh              # Script pour exécuter tous les tests
```

## Format des fichiers de test

Chaque fichier de test est au format JSON et contient deux sections principales :
- `request` : Définit la requête HTTP à envoyer (méthode, URL, en-têtes, corps)
- `expectedResponse` : Définit la réponse attendue (code de statut, corps)

Exemple :
```json
{
  "request": {
    "method": "GET",
    "url": "http://localhost:25000/store/75001",
    "headers": {
      "Accept": "application/json"
    }
  },
  "expectedResponse": {
    "status": 200,
    "bodyContains": ["STORE001"]
  }
}
```

## Exécution des tests

Pour exécuter tous les tests :

```bash
cd /home/franck/Sandbox/02-AppMod/oms-modernized/docker/json
./run-tests.sh
```

Pour exécuter un test spécifique :

```bash
cd /home/franck/Sandbox/02-AppMod/oms-modernized/docker/json
curl -X GET "http://localhost:25000/store/75001" -H "accept: application/json"
```

## Notes importantes

1. Assurez-vous que tous les microservices sont en cours d'exécution avant de lancer les tests
2. Les tests supposent que la base de données H2 est initialisée avec les données de test
3. Certains tests peuvent échouer si les services n'ont pas eu suffisamment de temps pour s'initialiser
4. Le service de recherche de magasins (storesearch-controller) devrait fonctionner correctement

## Dépannage

Si vous rencontrez des erreurs :

1. Vérifiez que tous les conteneurs sont en cours d'exécution : `docker compose ps`
2. Vérifiez les journaux des conteneurs : `docker logs <nom-du-conteneur>`
3. Assurez-vous que Nginx est correctement configuré : `docker logs nginx-proxy`
4. Essayez de redémarrer les services : `docker compose restart`
