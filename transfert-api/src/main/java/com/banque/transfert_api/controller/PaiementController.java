package com.banque.transfert_api.controller;

import com.banque.transfert_api.service.PaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PaiementController {

    private final PaiementService paiementService;

    @PostMapping("/paiement")
    public ResponseEntity<String> effectuerPaiement(@RequestParam String iban, @RequestParam BigDecimal montant) {
        try {
            boolean success = paiementService.processPaiement(iban, montant);
            if (success) {
                return ResponseEntity.ok("Paiement effectué avec succès !");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Échec du paiement !");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage());
        }
    }
}

