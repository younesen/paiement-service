package com.banque.transfert_api.controller;

import com.banque.transfert_api.entity.Transfert;
import com.banque.transfert_api.service.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/transferts")
@RequiredArgsConstructor
public class TransfertController {

    private final TransfertService transfertService;

    @PostMapping
    public ResponseEntity<Transfert> effectuerTransfert(@RequestBody Map<String, Object> request) {
        Long compteSourceId = Long.parseLong(request.get("compteSourceId").toString());
        Long compteDestinationId = Long.parseLong(request.get("compteDestinationId").toString());
        BigDecimal montant = new BigDecimal(request.get("montant").toString());

        Transfert transfert = transfertService.effectuerTransfert(compteSourceId, compteDestinationId, montant);
        return ResponseEntity.ok(transfert);
    }
}
