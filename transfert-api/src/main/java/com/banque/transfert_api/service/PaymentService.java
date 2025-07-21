package com.banque.transfert_api.service;

import com.banque.transfert_api.model.PaymentRequest;
import com.banque.transfert_api.model.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}