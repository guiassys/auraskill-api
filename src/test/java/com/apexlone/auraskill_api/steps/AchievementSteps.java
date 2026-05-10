package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.AchievementRequest;
import com.apexlone.auraskill_api.dto.AchievementResponse;
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

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AchievementSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<AchievementResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("an achievement with id {long} exists")
    public void an_achievement_with_id_exists(Long id) {
        a_professional_with_id_exists(1L);
        AchievementRequest request = new AchievementRequest();
        request.setProfessionalId(1L);
        request.setAchievementName("Hackathon Winner");
        request.setAchievementDescription("Won the local hackathon");
        request.setAchievementDate(LocalDate.parse("2023-05-10"));
        restTemplate.postForEntity("/api/v1/achievements", request, AchievementResponse.class);
    }

    @When("I create an achievement with the following details:")
    public void i_create_an_achievement_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        AchievementRequest request = new AchievementRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setAchievementName(data.get("achievementName"));
        request.setAchievementDescription(data.get("achievementDescription"));
        request.setAchievementDate(LocalDate.parse(data.get("achievementDate")));
        response = restTemplate.postForEntity("/api/v1/achievements", request, AchievementResponse.class);
    }

    @When("I get the achievement with id {long}")
    public void i_get_the_achievement_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/achievements/" + id, AchievementResponse.class);
    }

    @When("I update the achievement with id {long} with the following details:")
    public void i_update_the_achievement_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        AchievementRequest request = new AchievementRequest();
        request.setAchievementName(data.get("achievementName"));
        HttpEntity<AchievementRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/achievements/" + id, HttpMethod.PUT, entity, AchievementResponse.class);
    }

    @When("I delete the achievement with id {long}")
    public void i_delete_the_achievement_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/achievements/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the achievement is created successfully")
    public void the_achievement_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
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
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the achievement should have the name {string}")
    public void the_achievement_should_have_the_name(String name) {
        assertEquals(name, response.getBody().getAchievementName());
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