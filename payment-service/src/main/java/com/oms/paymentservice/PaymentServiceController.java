package com.oms.paymentservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.oms.paymentservice.dto.*;
import com.oms.service.PaymentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/paymentService")
@Tag(name = "Payment Service", description = "API pour les opérations de paiement standard")
public class PaymentServiceController {

    @Autowired
    private PaymentService paymentService;

    @Operation(summary = "Annuler une autorisation de paiement", 
              description = "Annule une autorisation de paiement précédemment effectuée")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Annulation réussie",
                    content = @Content(schema = @Schema(implementation = PaymentServiceReverseAuthOutDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping(value = "/reverseAuth", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentServiceReverseAuthOutDTO> reverseAuth(
            @Parameter(description = "Détails de l'autorisation à annuler", required = true) 
            @RequestBody PaymentServiceReverseAuthInDTO in) {
        PaymentServiceReverseAuthOutDTO ret = new PaymentServiceReverseAuthOutDTO();
        ret.setRetVal(paymentService.reverseAuth(in.getAuthorizationRequestDto()));
        return ResponseEntity.ok(ret);
    }

    @Operation(summary = "Autoriser un paiement", 
              description = "Traite une demande d'autorisation de paiement")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autorisation réussie",
                    content = @Content(schema = @Schema(implementation = PaymentServiceAuthorizeOutDTO.class))),
        @ApiResponse(responseCode = "400", description = "Requête invalide"),
        @ApiResponse(responseCode = "500", description = "Erreur serveur")
    })
    @PostMapping(value = "/authorize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentServiceAuthorizeOutDTO> authorize(
            @Parameter(description = "Détails de la demande d'autorisation", required = true) 
            @RequestBody PaymentServiceAuthorizeInDTO in) {
        PaymentServiceAuthorizeOutDTO ret = new PaymentServiceAuthorizeOutDTO();
        ret.setRetVal(paymentService.authorize(in.getAuthorizationRequestDto()));
        return ResponseEntity.ok(ret);
    }
}
