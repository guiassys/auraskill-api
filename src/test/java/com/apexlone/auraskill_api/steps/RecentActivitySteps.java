package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.RecentActivityRequest;
import com.apexlone.auraskill_api.dto.RecentActivityResponse;
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

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecentActivitySteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<RecentActivityResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long targetProfessionalId;
    private Long createdActivityId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists to own the recent activity")
    public void a_professional_exists_to_own_the_recent_activity() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) {}

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Activity Owner")
                .email("owner_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        targetProfessionalId = professional.getId();
    }

    @Given("a recent activity exists in the system")
    public void a_recent_activity_exists_in_the_system() {
        RecentActivityRequest request = new RecentActivityRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setActivityType("LOGIN");
        request.setActivityDescription("Initial mock activity log");
        request.setActivityDate(OffsetDateTime.now());

        HttpEntity<RecentActivityRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/recent-activities", HttpMethod.POST, entity, RecentActivityResponse.class);
        
        if (response.getBody() != null) {
            createdActivityId = response.getBody().getId();
        }
    }

    @When("I create a recent activity with the following details:")
    public void i_create_a_recent_activity_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        RecentActivityRequest request = new RecentActivityRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setActivityType(data.get("activityType").replace("\"", ""));
        request.setActivityDescription(data.get("activityDescription").replace("\"", ""));
        request.setActivityDate(OffsetDateTime.now());

        HttpEntity<RecentActivityRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/recent-activities", HttpMethod.POST, entity, RecentActivityResponse.class);
        
        if (response.getBody() != null) {
            createdActivityId = response.getBody().getId();
        }
    }

    @When("I get the recent activity details")
    public void i_get_the_recent_activity_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/recent-activities/" + createdActivityId, HttpMethod.GET, entity, RecentActivityResponse.class);
    }

    @When("I update the recent activity with the following details:")
    public void i_update_the_recent_activity_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        RecentActivityRequest request = new RecentActivityRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setActivityType(data.get("activityType").replace("\"", ""));
        request.setActivityDescription(data.get("activityDescription").replace("\"", ""));

        HttpEntity<RecentActivityRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/recent-activities/" + createdActivityId, HttpMethod.PUT, entity, RecentActivityResponse.class);
    }

    @When("I delete the recent activity")
    public void i_delete_the_recent_activity() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/recent-activities/" + createdActivityId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the recent activity is created successfully")
    public void the_recent_activity_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
    }

    @Then("I should receive the recent activity details")
    public void i_should_receive_the_recent_activity_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the recent activity is updated successfully")
    public void the_recent_activity_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the recent activity is deleted successfully")
    public void the_recent_activity_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the recent activity should have the type {string}")
    public void the_recent_activity_should_have_the_type(String type) {
        assertNotNull(response.getBody());
        assertEquals(type, response.getBody().getActivityType());
    }
}
