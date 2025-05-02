package com.oms.emailservice;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.oms.emailservice.dto.*;
import com.oms.service.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/emailService")
@Tag(name = "Email Service", description = "API pour l'envoi d'emails")
public class EmailServiceController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendEmail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Envoyer un email", description = "Envoie un email avec les informations fournies")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Email envoyé avec succès",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = EmailServiceSendEmailOutDTO.class)) }),
        @ApiResponse(responseCode = "500", description = "Erreur lors de l'envoi de l'email",
                content = @Content) })
    public ResponseEntity<EmailServiceSendEmailOutDTO> sendEmail(
            @Parameter(description = "Informations de l'email à envoyer", required = true)
            @RequestBody EmailServiceSendEmailInDTO in) {
        EmailServiceSendEmailOutDTO ret = new EmailServiceSendEmailOutDTO();
        ret.setRetVal(emailService.sendEmail(in.getEmailRequestDto()));
        return ResponseEntity.ok(ret);
    }

}
