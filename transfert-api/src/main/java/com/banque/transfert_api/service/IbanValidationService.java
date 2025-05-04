package com.banque.transfert_api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class IbanValidationService {

    private final RestTemplate restTemplate;

    public IbanValidationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isValidIban(String iban) {
        String url = "https://openiban.com/validate/" + iban + "?getBIC=true&validateBankCode=true&format=json";

        try {
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            return response != null && Boolean.TRUE.equals(response.get("valid"));
        } catch (Exception e) {
            System.err.println("Erreur API OpenIBAN: " + e.getMessage());
            return false;
        }
    }
}
