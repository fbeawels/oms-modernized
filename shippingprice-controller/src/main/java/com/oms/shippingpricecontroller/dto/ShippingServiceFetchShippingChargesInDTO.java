package com.oms.shippingpricecontroller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO d'entrée pour la récupération des frais d'expédition")
public class ShippingServiceFetchShippingChargesInDTO {
    
    @Schema(description = "Identifiant du SKU", example = "SKU001", required = true)
    private String skuId;
}
