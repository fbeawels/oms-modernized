package com.oms.controller;

import com.oms.service.StoreSearchService;
import com.oms.util.Logger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/store")
@Tag(name = "Store Search", description = "API pour rechercher des magasins par code postal")
public class StoreSearchController {

    @Autowired
    private StoreSearchService storeSearchService;

    @Autowired
    private Logger logger;

    @Operation(summary = "Rechercher des magasins par code postal", 
              description = "Renvoie une liste d'identifiants de magasins disponibles dans le code postal spécifié")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Magasins trouvés",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class))),
        @ApiResponse(responseCode = "404", description = "Aucun magasin trouvé pour ce code postal",
                    content = @Content)
    })
    @GetMapping("/{zipCode}")
    public ResponseEntity<List<String>> fetchStoresByZip(
            @Parameter(description = "Code postal pour la recherche de magasins", required = true)
            @PathVariable String zipCode) {
        
        logger.log(this.getClass().getName());
        List<String> stores = storeSearchService.fetchStoresByZipCode(zipCode);
        
        if (stores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(stores);
    }
}
