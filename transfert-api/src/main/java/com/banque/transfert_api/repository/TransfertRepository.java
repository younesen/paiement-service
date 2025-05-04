package com.banque.transfert_api.repository;

import com.banque.transfert_api.entity.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {
    Optional<Transfert> findById(Long id);
}
