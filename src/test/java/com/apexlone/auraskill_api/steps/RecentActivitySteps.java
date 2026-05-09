package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.RecentActivityRequest;
import com.apexlone.auraskill_api.dto.RecentActivityResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecentActivitySteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<RecentActivityResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a recent activity with id {long} exists")
    public void a_recent_activity_with_id_exists(Long id) {
        a_professional_with_id_exists(1L);
        RecentActivityRequest request = new RecentActivityRequest();
        request.setProfessionalId(1L);
        request.setActivityType("NEW_SKILL");
        request.setActivityDescription("Added Java skill");
        restTemplate.postForEntity("/api/v1/recent-activities", request, RecentActivityResponse.class);
    }

    @When("I create a recent activity with the following details:")
    public void i_create_a_recent_activity_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        RecentActivityRequest request = new RecentActivityRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setActivityType(data.get("activityType"));
        request.setActivityDescription(data.get("activityDescription"));
        response = restTemplate.postForEntity("/api/v1/recent-activities", request, RecentActivityResponse.class);
    }

    @When("I get the recent activity with id {long}")
    public void i_get_the_recent_activity_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/recent-activities/" + id, RecentActivityResponse.class);
    }

    @When("I update the recent activity with id {long} with the following details:")
    public void i_update_the_recent_activity_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        RecentActivityRequest request = new RecentActivityRequest();
        request.setActivityType(data.get("activityType"));
        HttpEntity<RecentActivityRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/recent-activities/" + id, HttpMethod.PUT, entity, RecentActivityResponse.class);
    }

    @When("I delete the recent activity with id {long}")
    public void i_delete_the_recent_activity_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/recent-activities/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the recent activity is created successfully")
    public void the_recent_activity_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
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
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the recent activity should have the type {string}")
    public void the_recent_activity_should_have_the_type(String type) {
        assertEquals(type, response.getBody().getActivityType());
    }

    private void a_professional_with_id_exists(Long id) {
        if (!professionalRepository.existsById(id)) {
            professionalRepository.save(Professional.builder()
                    .id(id)
                    .keycloakUserId(UUID.randomUUID())
                    .fullName("Test Professional")
                    .email("test" + id + "@test.com")
                    .build());
        }
    }
}