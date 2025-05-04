package com.banque.transfert_api.service;

import com.banque.transfert_api.dto.CompteDTO;
import com.banque.transfert_api.entity.Compte;
import com.banque.transfert_api.repository.CompteRepository;
import com.banque.transfert_api.repository.UtilisateurRepository;
import com.banque.transfert_api.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;


    public Page<CompteDTO> getAllComptes(Pageable pageable) {
        return compteRepository.findAll(pageable).map(this::convertToDTO);
    }
    public CompteDTO getCompteById(Long id) {
        Optional<Compte> compte = compteRepository.findById(id);
        return compte.map(this::convertToDTO).orElse(null);
    }

    public CompteDTO createCompte(CompteDTO compteDTO) {
        Compte compte = convertToEntity(compteDTO);

        // 🔴 Vérifier si l'utilisateur existe avant d'associer le compte
        Utilisateur utilisateur = utilisateurRepository.findById(compteDTO.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        compte.setUtilisateur(utilisateur);

        Compte savedCompte = compteRepository.save(compte);
        return convertToDTO(savedCompte);
    }


    public CompteDTO updateCompte(Long id, CompteDTO compteDTO) {
        Optional<Compte> existingCompte = compteRepository.findById(id);
        if (existingCompte.isPresent()) {
            Compte compte = existingCompte.get();
            compte.setIban(compteDTO.getIban());
            compte.setAlias(compteDTO.getAlias());
            compte.setSolde(compteDTO.getSolde());
            Compte updatedCompte = compteRepository.save(compte);
            return convertToDTO(updatedCompte);
        }
        return null;
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

    // Méthodes de conversion
    private CompteDTO convertToDTO(Compte compte) {
        CompteDTO dto = new CompteDTO();
        dto.setId(compte.getId());
        dto.setIban(compte.getIban());
        dto.setAlias(compte.getAlias());
        dto.setSolde(compte.getSolde());
        return dto;
    }

    private Compte convertToEntity(CompteDTO dto) {
        Compte compte = new Compte();
        compte.setIban(dto.getIban());
        compte.setAlias(dto.getAlias());
        compte.setSolde(dto.getSolde());

        // 🔴 Associer l'utilisateur
        if (dto.getUtilisateurId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(dto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
            compte.setUtilisateur(utilisateur);
        }

        return compte;
    }

}
