package com.oms.inventorycontroller.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Paramètres d'entrée pour la récupération d'un inventaire")
public class InventoryServiceFetchInventoryInDTO {
    @Schema(description = "Identifiant unique du produit (SKU ID)", example = "SKU001", required = true)
    private String skuId;
    
    
    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }
    
}