package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.SkillRequest;
import com.apexlone.auraskill_api.dto.SkillResponse;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SkillSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<SkillResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a skill with id {long} exists")
    public void a_skill_with_id_exists(Long id) {
        SkillRequest request = new SkillRequest();
        request.setName("Java");
        request.setSkillType("TECHNICAL");
        restTemplate.postForEntity("/api/v1/skills", request, SkillResponse.class);
    }

    @When("I create a skill with the following details:")
    public void i_create_a_skill_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        SkillRequest request = new SkillRequest();
        request.setName(data.get("name"));
        request.setSkillType(data.get("skillType"));
        response = restTemplate.postForEntity("/api/v1/skills", request, SkillResponse.class);
    }

    @When("I get the skill with id {long}")
    public void i_get_the_skill_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/skills/" + id, SkillResponse.class);
    }

    @When("I update the skill with id {long} with the following details:")
    public void i_update_the_skill_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        SkillRequest request = new SkillRequest();
        request.setName(data.get("name"));
        HttpEntity<SkillRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/skills/" + id, HttpMethod.PUT, entity, SkillResponse.class);
    }

    @When("I delete the skill with id {long}")
    public void i_delete_the_skill_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/skills/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the skill is created successfully")
    public void the_skill_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
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
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the skill should have the name {string}")
    public void the_skill_should_have_the_name(String name) {
        assertEquals(name, response.getBody().getName());
    }
}