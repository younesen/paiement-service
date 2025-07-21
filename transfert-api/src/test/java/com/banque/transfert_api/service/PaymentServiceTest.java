package com.banque.transfert_api.service;

import com.banque.transfert_api.model.*;
import com.banque.transfert_api.repository.PaymentRepository;
import com.banque.transfert_api.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private TgrMockService tgrMockService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldProcessTgrPaymentSuccessfully() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(new BigDecimal("10000"));
        request.setPaymentMethod("TGR");

        TgrResponse mockResponse = new TgrResponse();
        mockResponse.setCodeRetour("000");
        mockResponse.setReferenceTransaction("REF123");

        when(tgrMockService.simulatePayment(any(Payment.class)))
                .thenReturn(mockResponse);

        when(paymentRepository.save(any(Payment.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PaymentResponse result = paymentService.processPayment(request);

        assertEquals("REF123", result.getTransactionId());
    }
}