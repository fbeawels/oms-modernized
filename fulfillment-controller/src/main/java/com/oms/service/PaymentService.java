package com.oms.service;

import com.oms.dto.AuthorizationRequestDto;
import com.oms.dto.AuthorizationResponseDto;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer les paiements
 */
@Service
public class PaymentService {

    /**
     * Autorise un paiement
     * @param authorizationRequestDto Requête d'autorisation
     * @return Réponse d'autorisation
     */
    public AuthorizationResponseDto authorize(AuthorizationRequestDto authorizationRequestDto) {
        // Simulation d'une autorisation de paiement
        AuthorizationResponseDto responseDto = new AuthorizationResponseDto(
            "AUTH" + System.currentTimeMillis(),
            authorizationRequestDto.getAmount(),
            "APPROVED"
        );
        return responseDto;
    }

    /**
     * Annule une autorisation de paiement
     * @param authorizationRequestDto Requête d'annulation
     * @return Réponse d'annulation
     */
    public AuthorizationResponseDto reverseAuth(AuthorizationRequestDto authorizationRequestDto) {
        // Simulation d'une annulation d'autorisation de paiement
        AuthorizationResponseDto responseDto = new AuthorizationResponseDto(
            "REV" + System.currentTimeMillis(),
            authorizationRequestDto.getAmount(),
            "REVERSED"
        );
        return responseDto;
    }
}
