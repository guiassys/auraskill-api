package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.SkillRequest;
import com.apexlone.auraskill_api.dto.SkillResponse;
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

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkillSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<SkillResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long createdSkillId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a skill exists in the system")
    public void a_skill_exists_in_the_system() {
        SkillRequest request = new SkillRequest();
        // Usando UUID randômico para evitar quebra por conta da restrição UNIQUE no banco
        request.setName("Initial Skill " + UUID.randomUUID().toString().substring(0, 8));
        request.setSkillType("HARD");

        HttpEntity<SkillRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/skills", HttpMethod.POST, entity, SkillResponse.class);
        
        if (response.getBody() != null) {
            createdSkillId = response.getBody().getId();
        }
    }

    @When("I create a skill with the following details:")
    public void i_create_a_skill_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        SkillRequest request = new SkillRequest();
        request.setName(data.get("name").replace("\"", ""));
        request.setSkillType(data.get("skillType").replace("\"", ""));

        HttpEntity<SkillRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/skills", HttpMethod.POST, entity, SkillResponse.class);
        
        if (response.getBody() != null) {
            createdSkillId = response.getBody().getId();
        }
    }

    @When("I get the skill details")
    public void i_get_the_skill_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/skills/" + createdSkillId, HttpMethod.GET, entity, SkillResponse.class);
    }

    @When("I update the skill with the following details:")
    public void i_update_the_skill_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        SkillRequest request = new SkillRequest();
        request.setName(data.get("name").replace("\"", ""));
        request.setSkillType(data.get("skillType").replace("\"", ""));

        HttpEntity<SkillRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/skills/" + createdSkillId, HttpMethod.PUT, entity, SkillResponse.class);
    }

    @When("I delete the skill")
    public void i_delete_the_skill() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/skills/" + createdSkillId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the skill is created successfully")
    public void the_skill_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
    }

    @Then("I should receive the skill details")
    public void i_should_receive_the_skill_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the skill is updated successfully")
    public void the_skill_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the skill is deleted successfully")
    public void the_skill_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the skill should have the name {string}")
    public void the_skill_should_have_the_name(String name) {
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getName());
    }
}
