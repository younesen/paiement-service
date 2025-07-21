package com.banque.transfert_api.controller;

import com.banque.transfert_api.model.Payment;
import com.banque.transfert_api.model.PaymentRequest;
import com.banque.transfert_api.model.PaymentResponse;
import com.banque.transfert_api.model.TgrResponse;
import com.banque.transfert_api.service.PaymentService;
import com.banque.transfert_api.service.TgrMockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tgr")
@CrossOrigin(origins = "http://localhost:3000")
public class TgrMockController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/payments")
    public PaymentResponse processTgrPayment(@RequestBody PaymentRequest request) {
        request.setPaymentMethod("TGR"); // Force le type
        return paymentService.processPayment(request);
    }
}