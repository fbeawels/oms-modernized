package com.oms.service;

import com.oms.dto.AuthorizationRequestDto;
import com.oms.dto.AuthorizationResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les paiements Diners Club
 */
@Service
public class DinersPaymentService {

    /**
     * Autorise un paiement Diners Club
     * @param authorizationRequestDto Requête d'autorisation
     * @return Réponse d'autorisation
     */
    public AuthorizationResponseDto authorize(AuthorizationRequestDto authorizationRequestDto) {
        // Simulation d'une autorisation de paiement Diners Club
        AuthorizationResponseDto responseDto = new AuthorizationResponseDto(
            "DINERS_AUTH" + System.currentTimeMillis(),
            authorizationRequestDto.getAmount(),
            "APPROVED"
        );
        return responseDto;
    }

    /**
     * Annule une autorisation de paiement Diners Club
     * @param authorizationRequestDto Requête d'annulation
     * @return Réponse d'annulation
     */
    public AuthorizationResponseDto reverseAuth(AuthorizationRequestDto authorizationRequestDto) {
        // Simulation d'une annulation d'autorisation de paiement Diners Club
        AuthorizationResponseDto responseDto = new AuthorizationResponseDto(
            "DINERS_REV" + System.currentTimeMillis(),
            authorizationRequestDto.getAmount(),
            "REVERSED"
        );
        return responseDto;
    }
}
