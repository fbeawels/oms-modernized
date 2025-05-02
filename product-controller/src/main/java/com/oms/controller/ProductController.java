package com.oms.controller;

import com.oms.entity.Inventory;
import com.oms.entity.OrderLine;
import com.oms.entity.Product;
import com.oms.service.ProductService;
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
@RequestMapping("/product")
@Tag(name = "Product Controller", description = "API pour la gestion des produits")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    Logger logger;

    @GetMapping("/{productId}")
    @Operation(summary = "Récupérer un produit par son ID", description = "Retourne les informations d'un produit pour un ID donné")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produit trouvé",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                content = @Content) })
    public Product getProductById(
            @Parameter(description = "ID du produit à récupérer", required = true)
            @PathVariable String productId) {
        logger.log(this.getClass().getName());
        return productService.getProductById(productId);
    }

    @GetMapping("/inv/{productId}")
    @Operation(summary = "Récupérer l'inventaire d'un produit", description = "Retourne les informations d'inventaire pour un produit donné")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaire trouvé",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Inventory.class)) }),
        @ApiResponse(responseCode = "404", description = "Inventaire non trouvé",
                content = @Content) })
    public Inventory getInventoryForProduct(
            @Parameter(description = "ID du produit dont l'inventaire est à récupérer", required = true)
            @PathVariable String productId) {
        logger.log(this.getClass().getName());
        return productService.getProductInventory(productId);
    }

    @GetMapping("/inv-desc/{text}")
    @Operation(summary = "Rechercher des inventaires par description", description = "Retourne les inventaires dont la description contient le texte spécifié")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inventaires trouvés",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Inventory.class, type = "array")) }),
        @ApiResponse(responseCode = "404", description = "Aucun inventaire trouvé",
                content = @Content) })
    public Inventory[] getInventoryForProductByDesc(
            @Parameter(description = "Texte à rechercher dans les descriptions", required = true)
            @PathVariable String text) {
        logger.log(this.getClass().getName());
        Inventory[] invA = new Inventory[1];
        return productService.getInventoriesDescribedWith(text).toArray(invA);
    }

    
    @GetMapping("/orderlines/{productId}")
    @Operation(summary = "Récupérer les lignes de commande d'un produit", description = "Retourne toutes les lignes de commande associées à un produit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lignes de commande trouvées",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = OrderLine.class, type = "array")) }),
        @ApiResponse(responseCode = "404", description = "Aucune ligne de commande trouvée",
                content = @Content) })
    public OrderLine[] getOrderLinesForProduct(
            @Parameter(description = "ID du produit dont les lignes de commande sont à récupérer", required = true)
            @PathVariable String productId) {
       	logger.log(this.getClass().getName());
    	OrderLine[] orderLineDummyArray = new OrderLine[1];
        return productService.getOrderLinesForProduct(productId).toArray(orderLineDummyArray);
    }

    @GetMapping("/charges/{productId}")
    @Operation(summary = "Calculer les frais totaux d'un produit", description = "Retourne le montant total des frais associés à un produit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Frais calculés avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Double.class)) }),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                content = @Content) })
    public Double getChargesForProduct(
            @Parameter(description = "ID du produit dont les frais sont à calculer", required = true)
            @PathVariable String productId) {
       	logger.log(this.getClass().getName());
        return productService.getTotalChargesForProduct(productId);
    }

    
    @GetMapping("/all")
    @Operation(summary = "Récupérer tous les produits", description = "Retourne la liste de tous les produits disponibles")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produits récupérés avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class, type = "array")) })})
    public Product[] getAllProducts() {
    	logger.log(this.getClass().getName());
    	Product[] p = new Product[1];
        return productService.getAllProducts().toArray(p);
    }


    @GetMapping("/name/{productName}")
    @Operation(summary = "Rechercher un produit par son nom", description = "Retourne un produit dont le nom correspond exactement à celui spécifié")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produit trouvé",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                content = @Content) })
    public Product getProductByName(
            @Parameter(description = "Nom du produit à rechercher", required = true)
            @PathVariable String productName) {
        logger.log(this.getClass().getName());
        return productService.getProductByName(productName);
    }

    @GetMapping("/desc-includes/{text}")
    @Operation(summary = "Rechercher des produits par description", description = "Retourne les produits dont la description contient le texte spécifié")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produits trouvés",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class, type = "array")) }),
        @ApiResponse(responseCode = "404", description = "Aucun produit trouvé",
                content = @Content) })
    public Product[] getProductsDescribedBy(
            @Parameter(description = "Texte à rechercher dans les descriptions", required = true)
            @PathVariable String text) {
        logger.log(this.getClass().getName());
        Product[] p = new Product[1];
        return productService.getProductsDescribedWith(text).toArray(p);
    }

    
    @PostMapping("/register")
    @Operation(summary = "Enregistrer un nouveau produit", description = "Crée un nouveau produit avec les informations fournies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produit créé avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "400", description = "Données de produit invalides",
                content = @Content) })
    public Product registerNewProduct(
            @Parameter(description = "Informations du produit à créer", required = true)
            @RequestBody Product product) {
        logger.log(this.getClass().getName());
        return productService.registerProduct(product);
    }
    
    @PostMapping("/register-list")
    @Operation(summary = "Enregistrer plusieurs produits", description = "Crée plusieurs produits en une seule requête")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produits créés avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Product.class, type = "array")) }),
        @ApiResponse(responseCode = "400", description = "Données de produits invalides",
                content = @Content) })
    public Product[] registerNewProducts(
            @Parameter(description = "Tableau de produits à créer", required = true)
            @RequestBody Product[] productArray) {
    	Product[] registeredProducts = new Product[productArray.length];
    	int i = 0;
        logger.log(this.getClass().getName());
        for (Product p : productArray) {
        	registeredProducts[i] = registerNewProduct(p);
        	i++;
        }
        return registeredProducts;
    }
}
