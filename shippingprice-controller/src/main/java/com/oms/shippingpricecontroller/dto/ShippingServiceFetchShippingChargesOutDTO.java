package com.oms.shippingpricecontroller.dto;

import com.oms.entity.Shipping;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO de sortie pour la récupération des frais d'expédition")
public class ShippingServiceFetchShippingChargesOutDTO {
    
    @Schema(description = "Informations sur les frais d'expédition")
    private Shipping retVal;
}
