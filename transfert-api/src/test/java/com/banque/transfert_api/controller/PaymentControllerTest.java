package com.banque.transfert_api.controller;

import com.banque.transfert_api.model.PaymentRequest;
import com.banque.transfert_api.model.PaymentResponse;
import com.banque.transfert_api.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;

    @Test
    void shouldProcessPayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(new BigDecimal("1000"));
        request.setPaymentMethod("CARD");

        PaymentResponse mockResponse = new PaymentResponse();
        mockResponse.setTransactionId("TXN123");

        when(paymentService.processPayment(request)).thenReturn(mockResponse);

        mockMvc.perform(post("/api/payments")
                        .contentType("application/json")
                        .content("{\"amount\":1000,\"paymentMethod\":\"CARD\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("TXN123"));
    }
}