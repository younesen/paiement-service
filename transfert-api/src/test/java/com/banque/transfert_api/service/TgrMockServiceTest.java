// ✅ TgrMockServiceTest.java
package com.banque.transfert_api.service;

import com.banque.transfert_api.model.Payment;
import com.banque.transfert_api.model.TgrResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TgrMockServiceTest {

    @InjectMocks
    private TgrMockService tgrMockService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void simulatePayment_ShouldReturnSuccess_WhenAmountOver10000() {
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal("15000"));

        TgrResponse response = tgrMockService.simulatePayment(payment);

        assertEquals("000", response.getCodeRetour());
        assertNotNull(response.getReferenceTransaction());
    }

    @Test
    void simulatePayment_ShouldReturnFailure_WhenAmountUnder10000() {
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal("5000"));

        TgrResponse response = tgrMockService.simulatePayment(payment);

        assertEquals("999", response.getCodeRetour());
    }
}

