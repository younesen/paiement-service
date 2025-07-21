
package com.banque.transfert_api.controller;

import com.banque.transfert_api.model.*;
import com.banque.transfert_api.service.MtcMockService;
import com.banque.transfert_api.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/mtc-mock")
@CrossOrigin(origins = "http://localhost:3000")
public class MtcMockController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public PaymentResponse processMtcPayment(@RequestBody PaymentRequest request) {
        request.setPaymentMethod("MTC"); // Force le type
        // request.setPhoneNumber(request.getPhoneNumber()); // Décommentez si nécessaire
        return paymentService.processPayment(request);
    }
}