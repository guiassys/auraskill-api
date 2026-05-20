package com.apexlone.auraskill_api;

import com.apexlone.auraskill_api.config.TestSecurityConfig;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestSecurityConfig.class)
public class CucumberSpringConfiguration {
    // Esta classe fica vazia. Ela serve apenas como a "âncora" de contexto para o Cucumber.
}