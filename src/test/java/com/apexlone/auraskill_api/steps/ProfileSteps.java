package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.ProfileRequest;
import com.apexlone.auraskill_api.dto.ProfileResponse;
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
public class ProfileSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<ProfileResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a professional with id {long} exists")
    public void a_professional_with_id_exists(Long id) {
        if (!professionalRepository.existsById(id)) {
            professionalRepository.save(Professional.builder()
                    .id(id)
                    .keycloakUserId(UUID.randomUUID())
                    .fullName("Test Professional")
                    .email("test@test.com")
                    .build());
        }
    }

    @Given("a profile with id {long} exists")
    public void a_profile_with_id_exists(Long id) {
        ProfileRequest request = new ProfileRequest();
        request.setProfessionalId(1L);
        request.setProfessionalTitle("Software Engineer");
        restTemplate.postForEntity("/api/v1/profiles", request, ProfileResponse.class);
    }

    @When("I create a profile with the following details:")
    public void i_create_a_profile_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfileRequest request = new ProfileRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setProfessionalTitle(data.get("professionalTitle"));
        request.setProfessionalObjective(data.get("professionalObjective"));
        response = restTemplate.postForEntity("/api/v1/profiles", request, ProfileResponse.class);
    }

    @When("I get the profile with id {long}")
    public void i_get_the_profile_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/profiles/" + id, ProfileResponse.class);
    }

    @When("I update the profile with id {long} with the following details:")
    public void i_update_the_profile_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfileRequest request = new ProfileRequest();
        request.setProfessionalTitle(data.get("professionalTitle"));
        HttpEntity<ProfileRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/profiles/" + id, HttpMethod.PUT, entity, ProfileResponse.class);
    }

    @When("I delete the profile with id {long}")
    public void i_delete_the_profile_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/profiles/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the profile is created successfully")
    public void the_profile_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("I should receive the profile details")
    public void i_should_receive_the_profile_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the profile is updated successfully")
    public void the_profile_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the profile is deleted successfully")
    public void the_profile_is_deleted_successfully() {
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the profile should have the title {string}")
    public void the_profile_should_have_the_title(String title) {
        assertEquals(title, response.getBody().getProfessionalTitle());
    }
}