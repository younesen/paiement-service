package com.banque.transfert_api.controller;

import com.banque.transfert_api.entity.Utilisateur;
import com.banque.transfert_api.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Long id) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable String email) {
        return ResponseEntity.ok(utilisateurService.getUtilisateurByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.createUtilisateur(utilisateur));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return ResponseEntity.ok(utilisateurService.updateUtilisateur(id, utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
        return ResponseEntity.noContent().build();
    }
}
