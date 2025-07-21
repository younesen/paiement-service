// ✅ MtcMockServiceTest.java
package com.banque.transfert_api.service;

import com.banque.transfert_api.model.MtcPaymentRequest;
import com.banque.transfert_api.model.MtcPaymentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MtcMockServiceTest {

    @Autowired
    private MtcMockService mtcMockService;

    @Test
    void processPayment_ShouldApprove_WhenAmountOver5() {
        MtcPaymentRequest request = new MtcPaymentRequest();
        request.setAmount(new BigDecimal("10"));
        request.setPhoneNumber("212612345678");

        MtcPaymentResponse response = mtcMockService.processPayment(request);

        assertEquals("APPROVED", response.getStatus());
        assertNotNull(response.getTransactionId());
    }

    @Test
    void processPayment_ShouldReject_WhenAmountUnder5() {
        MtcPaymentRequest request = new MtcPaymentRequest();
        request.setAmount(new BigDecimal("3"));
        request.setPhoneNumber("212612345678");

        MtcPaymentResponse response = mtcMockService.processPayment(request);

        assertEquals("REJECTED", response.getStatus());
        assertEquals("Montant trop faible", response.getReason());
    }
}
