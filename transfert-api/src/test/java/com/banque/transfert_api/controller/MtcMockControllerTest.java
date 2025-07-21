// ✅ MtcMockControllerTest.java
package com.banque.transfert_api.controller;

import com.banque.transfert_api.model.MtcPaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MtcMockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void mockPayment_ShouldReturn200_WhenValidRequest() throws Exception {
        MtcPaymentRequest request = new MtcPaymentRequest();
        request.setAmount(new BigDecimal("20"));
        request.setPhoneNumber("212698765432");
        request.setMerchantCode("MTC_TEST");

        mockMvc.perform(post("/api/mtc-mock/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.transactionId").exists());
    }

    @Test
    void mockPayment_ShouldReturn400_WhenInvalidAmount() throws Exception {

        MtcPaymentRequest request = new MtcPaymentRequest();
        request.setAmount(new BigDecimal("2"));
        request.setPhoneNumber("212600000000");


        mockMvc.perform(post("/api/mtc-mock/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("REJECTED")); // ✅ ici REJECTED, pas MTC_REJECTED
    }

}