package com.oms.controller;

import com.oms.entity.Shipping;
import com.oms.service.ShippingService;
import com.oms.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/shipping")
@Tag(name = "Shipping Price Controller", description = "API pour la gestion des frais d'expédition")
public class ShippingPriceController {

    @Autowired
    ShippingService shippingService;

    @Autowired
    Logger logger;

    @Operation(summary = "Récupérer les frais d'expédition par SKU", 
              description = "Retourne les frais d'expédition pour un SKU spécifique")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Frais d'expédition trouvés",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Shipping.class))),
        @ApiResponse(responseCode = "404", description = "SKU non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @GetMapping("/{skuId}")
    public ResponseEntity<Shipping> fetchShippingCharges(@PathVariable String skuId) {
        logger.log(this.getClass().getName());
        try {
            Shipping shipping = shippingService.fetchShippingCharges(skuId);
            return ResponseEntity.ok(shipping);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Créer une nouvelle configuration de frais d'expédition", 
              description = "Crée une nouvelle configuration de frais d'expédition pour un SKU")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Configuration créée avec succès",
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Shipping.class))),
        @ApiResponse(responseCode = "400", description = "Données invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        logger.log(this.getClass().getName());
        try {
            Shipping createdShipping = shippingService.createShipping(shipping);
            return ResponseEntity.ok(createdShipping);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
