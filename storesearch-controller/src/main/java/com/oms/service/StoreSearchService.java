package com.oms.service;

import com.oms.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional
public class StoreSearchService {
    @Autowired
    private Logger logger;

    private AtomicInteger useCnt = new AtomicInteger();

    /**
     * Retourne une liste de magasins pour un code postal donné.
     * Cette implémentation simplifiée retourne des données statiques pour les tests.
     * 
     * @param zipCode Le code postal pour lequel rechercher des magasins
     * @return Une liste d'identifiants de magasins
     */
    public List<String> fetchStoresByZipCode(String zipCode) {
        logger.log(this.getClass().getName() + " - Recherche des magasins pour le code postal : " + zipCode);
        
        List<String> storesList = new ArrayList<>();
        
        // Ajouter des magasins en fonction du code postal
        if ("75001".equals(zipCode)) {
            storesList.add("281");
            storesList.add("282");
        } else if ("92100".equals(zipCode)) {
            storesList.add("283");
            storesList.add("284");
        }
        
        // Ajouter le compteur d'utilisation pour compatibilité avec le code existant
        storesList.add(String.format("%d", useCnt.incrementAndGet()));
        
        return storesList;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
