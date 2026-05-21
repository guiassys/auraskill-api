package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfessionalSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<Professional> response;
    private ResponseEntity<Void> deleteResponse;
    private Long createdProfessionalId;
    
    // Armazenando o estado dos campos obrigatórios para reuso na atualização
    private UUID currentKeycloakUserId;
    private String currentEmail;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists in the system")
    public void a_professional_exists_in_the_system() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) { System.out.println("Tabela ja limpa ou vazia."); }

        currentKeycloakUserId = UUID.randomUUID();
        currentEmail = "template_" + UUID.randomUUID() + "@test.com";

        Professional professional = Professional.builder()
                .keycloakUserId(currentKeycloakUserId)
                .fullName("Jane Doe Template")
                .email(currentEmail)
                .dateOfBirth(LocalDate.of(1990, 1, 1))
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        createdProfessionalId = professional.getId();
        ProfileSteps.currentProfessionalId = createdProfessionalId;
    }

    @When("I create a professional with the following details:")
    public void i_create_a_professional_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        currentKeycloakUserId = UUID.randomUUID();
        currentEmail = "test_" + UUID.randomUUID() + "@test.com";

        Professional requestBody = Professional.builder()
                .keycloakUserId(currentKeycloakUserId)
                .fullName(data.get("fullName").replace("\"", ""))
                .email(currentEmail)
                .phone(data.get("phone") != null ? data.get("phone").replace("\"", "") : null)
                .bio(data.get("bio") != null ? data.get("bio").replace("\"", "") : null)
                .build();

        HttpEntity<Professional> entity = new HttpEntity<>(requestBody, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/professionals", HttpMethod.POST, entity, Professional.class);
        
        if (response.getBody() != null) {
            createdProfessionalId = response.getBody().getId();
        }
    }

    @When("I get the professional details")
    public void i_get_the_professional_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/professionals/" + createdProfessionalId, HttpMethod.GET, entity, Professional.class);
    }

    @When("I update the professional with the following details:")
    public void i_update_the_professional_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        // Mantemos os valores obrigatórios não nulos para não quebrar a validação do banco/JPA
        Professional requestBody = Professional.builder()
                .keycloakUserId(currentKeycloakUserId != null ? currentKeycloakUserId : UUID.randomUUID())
                .email(currentEmail != null ? currentEmail : "update_" + UUID.randomUUID() + "@test.com")
                .fullName(data.get("fullName").replace("\"", ""))
                .bio(data.get("bio") != null ? data.get("bio").replace("\"", "") : null)
                .build();

        HttpEntity<Professional> entity = new HttpEntity<>(requestBody, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/professionals/" + createdProfessionalId, HttpMethod.PUT, entity, Professional.class);
    }

    @When("I delete the professional")
    public void i_delete_the_professional() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/professionals/" + createdProfessionalId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the professional is created successfully")
    public void the_professional_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
    }

    @Then("I should receive the professional details")
    public void i_should_receive_the_professional_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the professional is updated successfully")
    public void the_professional_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the professional is deleted successfully")
    public void the_professional_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @Then("the professional should have the name {string}")
    public void the_professional_should_have_the_name(String name) {
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getFullName());
    }
}
