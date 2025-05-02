package com.oms.inventorycontroller;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.oms.inventorycontroller.dto.*;
import com.oms.service.InventoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/inventoryService")
@Tag(name = "Inventory Service Controller", description = "API pour les services d'inventaire")
public class InventoryServiceController {
    @Autowired
    private InventoryService inventoryService;

    @PostMapping(value = "fetchInventory", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Récupérer un inventaire", description = "Récupère les informations d'inventaire pour un SKU ID donné")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaire récupéré avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = InventoryServiceFetchInventoryOutDTO.class)) }),
        @ApiResponse(responseCode = "500", description = "Erreur lors de la récupération de l'inventaire",
                content = @Content) })
    public ResponseEntity<InventoryServiceFetchInventoryOutDTO> fetchInventory(
            @Parameter(description = "Paramètres de recherche de l'inventaire", required = true)
            @RequestBody InventoryServiceFetchInventoryInDTO in) {
        InventoryServiceFetchInventoryOutDTO ret = new InventoryServiceFetchInventoryOutDTO();
        ret.setRetVal(inventoryService.fetchInventory(in.getSkuId()));
        return ResponseEntity.ok(ret);
    }

}