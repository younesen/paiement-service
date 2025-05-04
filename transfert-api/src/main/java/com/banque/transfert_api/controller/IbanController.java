package com.banque.transfert_api.controller;

import com.banque.transfert_api.service.IbanValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iban")
@RequiredArgsConstructor
public class IbanController {

    private final IbanValidationService ibanValidationService;

    @GetMapping("/validate/{iban}")
    public ResponseEntity<Boolean> validateIban(@PathVariable String iban) {
        boolean isValid = ibanValidationService.isValidIban(iban);
        return ResponseEntity.ok(isValid);
    }
}
