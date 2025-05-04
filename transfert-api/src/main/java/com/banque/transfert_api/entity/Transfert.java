package com.banque.transfert_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transferts")
public class Transfert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compte_source_id", nullable = false)
    private Compte compteSource;

    @ManyToOne
    @JoinColumn(name = "compte_destination_id", nullable = false)
    private Compte compteDestination;

    @Column(nullable = false)
    private BigDecimal montant;

    @Column(nullable = false)
    private String statut;

    @Column(nullable = false)
    private LocalDateTime dateCreation;
}
