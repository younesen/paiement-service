package com.banque.transfert_api.service;

import com.banque.transfert_api.entity.Compte;
import com.banque.transfert_api.entity.Transfert;
import com.banque.transfert_api.repository.CompteRepository;
import com.banque.transfert_api.repository.TransfertRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransfertService {

    private final TransfertRepository transfertRepository;
    private final CompteRepository compteRepository;

    @Transactional
    public Transfert effectuerTransfert(Long compteSourceId, Long compteDestinationId, BigDecimal montant) {
        Compte compteSource = compteRepository.findById(compteSourceId)
                .orElseThrow(() -> new RuntimeException("Compte source introuvable"));
        Compte compteDestination = compteRepository.findById(compteDestinationId)
                .orElseThrow(() -> new RuntimeException("Compte destination introuvable"));

        if (compteSource.getSolde().compareTo(montant) < 0) {
            throw new RuntimeException("Fonds insuffisants pour le transfert");
        }

        compteSource.setSolde(compteSource.getSolde().subtract(montant));
        compteDestination.setSolde(compteDestination.getSolde().add(montant));

        compteRepository.save(compteSource);
        compteRepository.save(compteDestination);

        Transfert transfert = new Transfert();
        transfert.setCompteSource(compteSource);
        transfert.setCompteDestination(compteDestination);
        transfert.setMontant(montant);
        transfert.setStatut("SUCCÈS");
        transfert.setDateCreation(LocalDateTime.now());

        return transfertRepository.save(transfert);
    }
}
