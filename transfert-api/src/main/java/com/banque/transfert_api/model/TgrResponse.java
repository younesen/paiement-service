package com.banque.transfert_api.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class TgrResponse {
    private String codeRetour;
    private String referenceTransaction;
    private BigDecimal montant;


    public void setCodeRetour(String codeRetour) {
        this.codeRetour = codeRetour;
    }

    public void setReferenceTransaction(String referenceTransaction) {
        this.referenceTransaction = referenceTransaction;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
}