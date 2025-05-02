package com.oms.shippingpricecontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.oms.shippingpricecontroller.dto.*;
import com.oms.service.ShippingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shippingService")
@Tag(name = "Shipping Service", description = "API pour la gestion des frais d'expédition")
public class ShippingServiceController {

    @Autowired
    private ShippingService shippingService;

    @Operation(summary = "Récupérer les frais d'expédition pour un SKU", 
              description = "Retourne les frais d'expédition standard, express et accéléré pour un SKU donné")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Frais d'expédition trouvés",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ShippingServiceFetchShippingChargesOutDTO.class))),
        @ApiResponse(responseCode = "404", description = "SKU non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping(value = "/fetchShippingCharges", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShippingServiceFetchShippingChargesOutDTO> fetchShippingCharges(
            @RequestBody ShippingServiceFetchShippingChargesInDTO in) {
        ShippingServiceFetchShippingChargesOutDTO ret = new ShippingServiceFetchShippingChargesOutDTO();
        ret.setRetVal(shippingService.fetchShippingCharges(in.getSkuId()));
        return ResponseEntity.ok(ret);
    }
}
