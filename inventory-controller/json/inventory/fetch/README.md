# Tests pour l'endpoint /inventory/{skuId}

Cet endpoint utilise une méthode GET avec le skuId passé directement dans l'URL. 
Il n'y a pas de corps de requête JSON pour ces tests.

## Exemples d'appels:
```
curl -X GET http://localhost:8081/inventory/SKU001
curl -X GET http://localhost:8081/inventory/NONEXISTENT
```
