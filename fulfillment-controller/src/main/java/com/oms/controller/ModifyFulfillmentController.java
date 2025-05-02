package com.oms.controller;

import com.oms.entity.SalesOrder;
import com.oms.service.ModifyFulfillmentService;
import com.oms.util.Logger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modify/fulfillment")
@Tag(name = "Fulfillment Controller", description = "API pour modifier le mode de livraison des commandes")
public class ModifyFulfillmentController {

    @Autowired
    ModifyFulfillmentService modifyFulfillmentService;

    @Autowired
    Logger logger;

    @PatchMapping
    @RequestMapping("/shipping/items/{lineItemId}")
    @Operation(summary = "Modifier le mode de livraison de retrait en magasin vers livraison à domicile", 
              description = "Permet de modifier le mode de livraison d'un article d'une commande de retrait en magasin vers livraison à domicile")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mode de livraison modifié avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalesOrder.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Commande ou article non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public SalesOrder modifyStorePickupToShipping(
            @Parameter(description = "ID de l'article à modifier", required = true) @PathVariable String lineItemId, 
            @Parameter(description = "Informations de la commande", required = true) @RequestBody SalesOrder salesOrder) {
        logger.log(this.getClass().getName());
        return modifyFulfillmentService.modifyToShipping(lineItemId, salesOrder);
    }

    @PatchMapping
    @RequestMapping("/store/items/{lineItemId}")
    @Operation(summary = "Modifier le mode de livraison de livraison à domicile vers retrait en magasin", 
              description = "Permet de modifier le mode de livraison d'un article d'une commande de livraison à domicile vers retrait en magasin")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Mode de livraison modifié avec succès",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = SalesOrder.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "404", description = "Commande ou article non trouvé"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public SalesOrder modifyShippingToStorePickUp(
            @Parameter(description = "ID de l'article à modifier", required = true) @PathVariable String lineItemId, 
            @Parameter(description = "Informations de la commande", required = true) @RequestBody SalesOrder salesOrder) {
        logger.log(this.getClass().getName());
        return modifyFulfillmentService.modifyToStorePickup(lineItemId, salesOrder);
    }

}
