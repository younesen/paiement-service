package com.banque.transfert_api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CompteDTO {
    private Long id;

    @NotNull(message = "L'IBAN est obligatoire")
    @Size(min = 15, max = 34, message = "L'IBAN doit avoir entre 15 et 34 caractères")
    private String iban;

    @NotNull(message = "L'alias est obligatoire")
    @Size(min = 3, max = 50, message = "L'alias doit avoir entre 3 et 50 caractères")
    private String alias;

    @NotNull(message = "Le solde est obligatoire")
    private BigDecimal solde;

    @NotNull(message = "L'utilisateur est obligatoire") // ✅ Ajouté
    private Long utilisateurId;
}
