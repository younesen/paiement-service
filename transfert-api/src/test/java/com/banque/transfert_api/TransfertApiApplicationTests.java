package com.banque.transfert_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Ajout d'un profil spécifique pour les tests
class TransfertApiApplicationTests {

	@Test
	void contextLoads() {
		// Test vide valide juste le chargement du contexte
	}
}