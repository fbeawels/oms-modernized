package com.oms.service;

import com.oms.dto.EmailRequestDto;
import org.springframework.stereotype.Service;

/**
 * Service pour gérer l'envoi d'emails
 */
@Service
public class EmailService {

    /**
     * Envoie un email
     * @param emailRequestDto Requête d'envoi d'email
     * @return true si l'email a été envoyé avec succès, false sinon
     */
    public boolean sendEmail(EmailRequestDto emailRequestDto) {
        // Simulation d'envoi d'email
        System.out.println("Email envoyé pour la commande : " + emailRequestDto.getSalesOrderNumber());
        System.out.println("Sujet : " + emailRequestDto.getMessageTitle());
        System.out.println("Corps : " + emailRequestDto.getMessageBody());
        System.out.println("Type : " + emailRequestDto.getEmailType());
        return true;
    }
}
