package com.banque.transfert_api.service;

import com.banque.transfert_api.model.MtcPaymentRequest;
import com.banque.transfert_api.model.MtcPaymentResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class MtcMockService {
    public MtcPaymentResponse processPayment(MtcPaymentRequest request) {
        MtcPaymentResponse response = new MtcPaymentResponse();

        if (request.getAmount().compareTo(new BigDecimal("5")) < 0) {
            response.setStatus("MTC_REJECTED");

            response.setReason("Montant trop faible");
        } else {
            response.setStatus("APPROVED");
            response.setTransactionId("MTC-" + System.currentTimeMillis());
        }

        return response;
    }
}