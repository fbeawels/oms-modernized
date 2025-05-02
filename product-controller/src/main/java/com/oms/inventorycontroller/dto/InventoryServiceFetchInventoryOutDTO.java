package com.oms.inventorycontroller.dto;

import com.oms.entity.Inventory;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Résultat de la récupération d'un inventaire")
public class InventoryServiceFetchInventoryOutDTO {
    @Schema(description = "Informations d'inventaire récupérées, null si l'inventaire n'existe pas")
    private Inventory retVal;
    
    
    public Inventory getRetVal() {
        return retVal;
    }

    public void setRetVal(Inventory retVal) {
        this.retVal = retVal;
    }
    
}