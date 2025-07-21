package com.banque.transfert_api.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class MtcPaymentRequest {
    private String merchantCode;
    private String customerId;
    private BigDecimal amount;
    private String phoneNumber;
}