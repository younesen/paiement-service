package com.banque.transfert_api.controller;

import com.banque.transfert_api.dto.CompteDTO;
import com.banque.transfert_api.service.CompteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @GetMapping
    public Page<CompteDTO> getAllComptes(Pageable pageable) {
        return compteService.getAllComptes(pageable);
    }

    @GetMapping("/{id}")
    public CompteDTO getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id);
    }

    @PostMapping
    public CompteDTO createCompte(@Valid @RequestBody CompteDTO compteDTO) {
        return compteService.createCompte(compteDTO);
    }

    @PutMapping("/{id}")
    public CompteDTO updateCompte(@PathVariable Long id, @Valid @RequestBody CompteDTO compteDTO) {
        return compteService.updateCompte(id, compteDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCompte(@PathVariable Long id) {
        compteService.deleteCompte(id);
    }
}
