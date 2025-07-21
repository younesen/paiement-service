package com.banque.transfert_api.service.impl;

import com.banque.transfert_api.model.*;
import com.banque.transfert_api.repository.PaymentRepository;
import com.banque.transfert_api.service.PaymentService;
import org.springframework.stereotype.Service;
import com.banque.transfert_api.service.TgrMockService;
import com.banque.transfert_api.service.MtcMockService; // Nouvelle importation

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final TgrMockService tgrMockService;
    private final MtcMockService mtcMockService; // Nouvelle dépendance

    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              TgrMockService tgrMockService,
                              MtcMockService mtcMockService) { // Modifié
        this.paymentRepository = paymentRepository;
        this.tgrMockService = tgrMockService;
        this.mtcMockService = mtcMockService;
    }

    @Override
    public PaymentResponse processPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getPaymentMethod());
        payment.setStatus(request.getStatus());

        // Logique pour TGR (existant)
        if("TGR".equalsIgnoreCase(request.getPaymentMethod())) {
            TgrResponse tgrResponse = tgrMockService.simulatePayment(payment);
            payment.setTransactionId(tgrResponse.getReferenceTransaction());
            payment.setStatus("TGR_" + tgrResponse.getCodeRetour());
        }
        // Nouvelle logique pour MTC
        else if("MTC".equalsIgnoreCase(request.getPaymentMethod())) {
            MtcPaymentRequest mtcRequest = new MtcPaymentRequest();
            mtcRequest.setAmount(request.getAmount());
            //mtcRequest.setPhoneNumber(request.getPhoneNumber());

            MtcPaymentResponse mtcResponse = mtcMockService.processPayment(mtcRequest);
            payment.setTransactionId(mtcResponse.getTransactionId());
            payment.setStatus("MTC_" + mtcResponse.getStatus());
        }

        paymentRepository.save(payment);

        return mapToResponse(payment);
    }

    private PaymentResponse mapToResponse(Payment payment) {
        PaymentResponse response = new PaymentResponse();
        response.setAmount(payment.getAmount());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setStatus(payment.getStatus());
        response.setTransactionId(payment.getTransactionId());
        return response;
    }
}