package com.banque.transfert_api.repository;

import com.banque.transfert_api.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    boolean existsByIban(String iban);
}
