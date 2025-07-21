// ✅ TgrMockControllerTest.java
package com.banque.transfert_api.controller;

import com.banque.transfert_api.model.Payment;
import com.banque.transfert_api.model.TgrResponse;
import com.banque.transfert_api.service.TgrMockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TgrMockController.class)
class TgrMockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TgrMockService tgrMockService;

    @Test
    void shouldReturnTgrResponse() throws Exception {
        Payment payment = new Payment();
        payment.setAmount(new BigDecimal("15000"));

        TgrResponse mockResponse = new TgrResponse();
        mockResponse.setCodeRetour("000");
        mockResponse.setReferenceTransaction("TGR-12345");

        when(tgrMockService.simulatePayment(any(Payment.class)))
                .thenReturn(mockResponse);

        mockMvc.perform(post("/api/tgr/payments")
                        .contentType("application/json")
                        .content("{\"amount\":15000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codeRetour").value("000"))
                .andExpect(jsonPath("$.referenceTransaction").value("TGR-12345"));
    }
}

