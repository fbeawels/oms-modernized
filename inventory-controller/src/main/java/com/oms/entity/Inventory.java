package com.oms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "inventory")
@Schema(description = "Entité représentant un inventaire")
public class Inventory {

    @Id
    @Schema(description = "Identifiant unique du produit (SKU ID)", example = "SKU001", required = true)
    private String skuId;
    
    @Schema(description = "Identifiant du magasin", example = "STORE001", required = true)
    private String storeId;
    
    @Schema(description = "Quantité disponible en stock", example = "100")
    private int quantity;

    public Inventory() {
    }

    public Inventory(String skuId, String storeId, int quantity) {
        this.skuId = skuId;
        this.storeId = storeId;
        this.quantity = quantity;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
