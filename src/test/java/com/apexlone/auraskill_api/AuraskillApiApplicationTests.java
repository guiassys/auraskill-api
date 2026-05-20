package com.apexlone.auraskill_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // Força o Spring a usar o application-test.yml
class AuraskillApiApplicationTests {

	@Test
	void contextLoads() {
		// Teste de fumaça padrão para garantir que o contexto sobe
	}

}