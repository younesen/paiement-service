// MtcPaymentResponse.java
package com.banque.transfert_api.model;

import lombok.Data;

@Data
public class MtcPaymentResponse {
    private String transactionId;
    private String status;
    private String reason;
}