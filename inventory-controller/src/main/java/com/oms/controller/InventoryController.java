package com.oms.controller;

import com.oms.entity.Inventory;
import com.oms.service.InventoryService;
import com.oms.util.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
@Tag(name = "Inventory Controller", description = "API pour la gestion des inventaires")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @Autowired
    Logger logger;

    @GetMapping("/{skuId}")
    @Operation(summary = "Récupérer un inventaire par son SKU ID", description = "Retourne les informations d'inventaire pour un SKU ID donné")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaire trouvé",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Inventory.class)) }),
        @ApiResponse(responseCode = "404", description = "Inventaire non trouvé",
                content = @Content) })
    public Inventory fetchInventory(
            @Parameter(description = "SKU ID de l'inventaire à récupérer", required = true)
            @PathVariable String skuId) {
        logger.log(this.getClass().getName());
        return inventoryService.fetchInventory(skuId);
    }

    @PostMapping
    @Operation(summary = "Créer un nouvel inventaire", description = "Crée un nouvel inventaire avec les informations fournies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaire créé avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Inventory.class)) }),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la création de l'inventaire",
                content = @Content) })
    public Inventory createInventory(
            @Parameter(description = "Informations de l'inventaire à créer", required = true)
            @RequestBody Inventory inventory) {
        logger.log(this.getClass().getName());
        return inventoryService.createInventory(inventory);
    }
    
    @PostMapping("/multi-create")
    @Operation(summary = "Créer plusieurs inventaires", description = "Crée plusieurs inventaires en une seule requête")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaires créés avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Inventory.class, type = "array")) }),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la création des inventaires",
                content = @Content) })
    public Inventory[] createMulti(
            @Parameter(description = "Tableau d'inventaires à créer", required = true)
            @RequestBody Inventory[] invArray) {
    	Inventory[] createdInv = new Inventory[invArray.length];
    	int i = 0;
        logger.log(this.getClass().getName());
        for (Inventory inv : invArray) {
        	createdInv[i] = createInventory(inv);
        	i++;
        }
        return createdInv;
    }


}
