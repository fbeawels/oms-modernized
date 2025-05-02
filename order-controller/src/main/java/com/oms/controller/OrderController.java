package com.oms.controller;

import com.oms.entity.SalesOrder;
import com.oms.service.OrderService;
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
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
@Tag(name = "Order Controller", description = "API pour gérer les commandes clients")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private Logger logger;

    @Operation(summary = "Récupérer une commande par son ID", 
              description = "Renvoie une commande basée sur l'ID de commande client fourni")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Commande trouvée", 
                     content = @Content(schema = @Schema(implementation = SalesOrder.class))),
        @ApiResponse(responseCode = "404", description = "Commande non trouvée"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{customerOrderId}")
    public ResponseEntity<SalesOrder> fetchSalesOrder(
            @Parameter(description = "ID de la commande client à récupérer", required = true)
            @PathVariable String customerOrderId) {
        logger.log(this.getClass().getName());
        try {
            SalesOrder order = orderService.fetchOrder(customerOrderId);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Créer une nouvelle commande", 
              description = "Crée une nouvelle commande client dans le système")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Commande créée avec succès",
                     content = @Content(schema = @Schema(implementation = SalesOrder.class))),
        @ApiResponse(responseCode = "400", description = "Données de commande invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PostMapping
    public ResponseEntity<SalesOrder> createSalesOrder(
            @Parameter(description = "Données de la commande à créer", required = true)
            @Valid @RequestBody SalesOrder salesOrder) {
        logger.log(this.getClass().getName());
        SalesOrder createdOrder = orderService.saveOrder(salesOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
    
    @Operation(summary = "Créer plusieurs commandes en une seule requête", 
              description = "Permet de créer plusieurs commandes en une seule requête")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Commandes créées avec succès"),
        @ApiResponse(responseCode = "400", description = "Données de commande invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PostMapping("/multi")
    public ResponseEntity<SalesOrder[]> createMultipleOrders(
            @Parameter(description = "Tableau de commandes à créer", required = true)
            @Valid @RequestBody SalesOrder[] orderArray) {
        logger.log(this.getClass().getName());
        SalesOrder[] orders = new SalesOrder[orderArray.length];
        int i = 0;
        for (SalesOrder order : orderArray) {
            orders[i] = orderService.saveOrder(order);
            i++;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(orders);
    }
}
