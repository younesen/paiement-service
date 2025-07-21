package com.banque.transfert_api.service;

import com.banque.transfert_api.model.Payment;
import com.banque.transfert_api.model.TgrResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TgrMockService {

    public TgrResponse simulatePayment(Payment payment) {
        TgrResponse response = new TgrResponse();
        response.setCodeRetour(payment.getAmount().compareTo(new BigDecimal("100")) > 0 ? "000" : "999");
        response.setReferenceTransaction("TGR-" + System.currentTimeMillis());
        return response;
    }
}