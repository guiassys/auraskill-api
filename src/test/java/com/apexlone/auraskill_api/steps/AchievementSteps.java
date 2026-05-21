package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.AchievementRequest;
import com.apexlone.auraskill_api.dto.AchievementResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import io.cucumber.java.en.And;
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

public class AchievementSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<AchievementResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long targetProfessionalId;
    private Long createdAchievementId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists to own the achievement")
    public void a_professional_exists_to_own_the_achievement() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) { System.out.println("Tabela ja limpa ou vazia."); }

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Achievement Owner")
                .email("owner_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        targetProfessionalId = professional.getId();
    }

    @Given("an achievement exists in the system")
    public void an_achievement_exists_in_the_system() {
        AchievementRequest request = new AchievementRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setAchievementName("Initial Achievement");
        request.setOrganizer("Some Organizer");
        request.setAchievementDate(LocalDate.now());

        HttpEntity<AchievementRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/achievements", HttpMethod.POST, entity, AchievementResponse.class);
        
        if (response.getBody() != null) {
            createdAchievementId = response.getBody().getId();
        }
    }

    @When("I create an achievement with the following details:")
    public void i_create_an_achievement_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        AchievementRequest request = new AchievementRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setAchievementName(data.get("achievementName").replace("\"", ""));
        request.setOrganizer(data.get("organizer") != null ? data.get("organizer").replace("\"", "") : null);
        request.setAchievementDescription(data.get("achievementDescription") != null ? data.get("achievementDescription").replace("\"", "") : null);
        request.setAchievementDate(LocalDate.now());

        HttpEntity<AchievementRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/achievements", HttpMethod.POST, entity, AchievementResponse.class);
        
        if (response.getBody() != null) {
            createdAchievementId = response.getBody().getId();
        }
    }

    @When("I get the achievement details")
    public void i_get_the_achievement_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/achievements/" + createdAchievementId, HttpMethod.GET, entity, AchievementResponse.class);
    }

    @When("I update the achievement with the following details:")
    public void i_update_the_achievement_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        AchievementRequest request = new AchievementRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setAchievementName(data.get("achievementName").replace("\"", ""));
        request.setOrganizer(data.get("organizer") != null ? data.get("organizer").replace("\"", "") : null);

        HttpEntity<AchievementRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/achievements/" + createdAchievementId, HttpMethod.PUT, entity, AchievementResponse.class);
    }

    @When("I delete the achievement")
    public void i_delete_the_achievement() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/achievements/" + createdAchievementId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the achievement is created successfully")
    public void the_achievement_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
    }

    @Then("I should receive the achievement details")
    public void i_should_receive_the_achievement_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the achievement is updated successfully")
    public void the_achievement_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the achievement is deleted successfully")
    public void the_achievement_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the achievement should have the name {string}")
    public void the_achievement_should_have_the_name(String name) {
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getAchievementName());
    }
}
