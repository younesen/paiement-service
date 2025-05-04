package com.banque.transfert_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaiementService {

    private final RestTemplate restTemplate;

    public boolean processPaiement(String iban, BigDecimal montant) {
        // Vérification IBAN via API externe
        String url = "https://api.exemple.com/validate-iban?iban=" + iban;
        Boolean isValid = restTemplate.getForObject(url, Boolean.class);

        if (Boolean.FALSE.equals(isValid)) {
            throw new RuntimeException("IBAN invalide");
        }

        // Simuler un paiement réussi
        return true;
    }
}
