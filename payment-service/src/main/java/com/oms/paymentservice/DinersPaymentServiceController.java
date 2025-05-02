package com.oms.paymentservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.oms.paymentservice.dto.*;
import com.oms.service.DinersPaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/dinersPaymentService")
@Tag(name = "Diners Payment Service", description = "API pour les opérations de paiement spécifiques à Diners Club")
public class DinersPaymentServiceController {

    @Autowired
    private DinersPaymentService dinersPaymentService;

    @Operation(summary = "Annuler une autorisation de paiement Diners", 
              description = "Annule une autorisation de paiement Diners Club précédemment effectuée")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Annulation réussie",
                    content = @Content(schema = @Schema(implementation = DinersPaymentServiceReverseAuthOutDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping(value = "/reverseAuth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DinersPaymentServiceReverseAuthOutDTO> reverseAuth(
            @Parameter(description = "Détails de l'autorisation Diners à annuler", required = true) 
            @RequestBody DinersPaymentServiceReverseAuthInDTO in) {
        DinersPaymentServiceReverseAuthOutDTO ret = new DinersPaymentServiceReverseAuthOutDTO();
        ret.setRetVal(dinersPaymentService.reverseAuth(in.getAuthorizationRequestDto()));
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Autoriser un paiement Diners", 
              description = "Traite une demande d'autorisation de paiement Diners Club")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autorisation réussie",
                    content = @Content(schema = @Schema(implementation = DinersPaymentServiceAuthorizeOutDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping(value = "/authorize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DinersPaymentServiceAuthorizeOutDTO> authorize(
            @Parameter(description = "Détails de la demande d'autorisation Diners", required = true) 
            @RequestBody DinersPaymentServiceAuthorizeInDTO in) {
        DinersPaymentServiceAuthorizeOutDTO ret = new DinersPaymentServiceAuthorizeOutDTO();
        ret.setRetVal(dinersPaymentService.authorize(in.getAuthorizationRequestDto()));
        return ResponseEntity.ok(ret);
    }
}
