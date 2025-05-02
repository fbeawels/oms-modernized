package com.oms.service;

import com.oms.entity.Shipping;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les frais de livraison
 */
@Service
public class ShippingService {

    /**
     * Récupère les frais de livraison pour un SKU donné
     * @param skuId ID du SKU
     * @return Objet Shipping contenant les frais de livraison
     */
    public Shipping fetchShippingCharges(String skuId) {
        // Simulation de récupération des frais de livraison
        Shipping shipping = new Shipping();
        shipping.setSkuId(skuId);
        shipping.setStandardShipping(5.99);
        shipping.setExpeditedShipping(9.99);
        shipping.setExpressShipping(14.99);
        return shipping;
    }
}
