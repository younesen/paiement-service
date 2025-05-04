package com.banque.transfert_api.service;

import com.banque.transfert_api.entity.Utilisateur;
import com.banque.transfert_api.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        // Vérifier si l'email existe déjà
        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Hasher le mot de passe avant d'enregistrer l'utilisateur
        utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));

        return utilisateurRepository.save(utilisateur);
    }

    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateurDetails) {
        Utilisateur utilisateur = getUtilisateurById(id);
        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setEmail(utilisateurDetails.getEmail());

        if (!utilisateurDetails.getMotDePasse().isEmpty()) {
            utilisateur.setMotDePasse(passwordEncoder.encode(utilisateurDetails.getMotDePasse()));
        }

        return utilisateurRepository.save(utilisateur);
    }

    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }
}
