package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.ProfessionalSkillRequest;
import com.apexlone.auraskill_api.dto.ProfessionalSkillResponse;
import com.apexlone.auraskill_api.dto.SkillRequest;
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
public class ProfessionalSkillSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<ProfessionalSkillResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a professional skill for professional {long} and skill {long} exists")
    public void a_professional_skill_for_professional_and_skill_exists(Long professionalId, Long skillId) {
        a_professional_with_id_exists(professionalId);
        a_skill_with_id_exists(skillId);
        ProfessionalSkillRequest request = new ProfessionalSkillRequest();
        request.setProfessionalId(professionalId);
        request.setSkillId(skillId);
        request.setProficiencyLevel("INTERMEDIATE");
        request.setYearsOfExperience(2);
        restTemplate.postForEntity("/api/v1/professional-skills", request, ProfessionalSkillResponse.class);
    }

    @When("I create a professional skill with the following details:")
    public void i_create_a_professional_skill_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfessionalSkillRequest request = new ProfessionalSkillRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setSkillId(Long.parseLong(data.get("skillId")));
        request.setProficiencyLevel(data.get("proficiencyLevel"));
        request.setYearsOfExperience(Integer.parseInt(data.get("yearsOfExperience")));
        response = restTemplate.postForEntity("/api/v1/professional-skills", request, ProfessionalSkillResponse.class);
    }

    @When("I get the professional skill for professional {long} and skill {long}")
    public void i_get_the_professional_skill_for_professional_and_skill(Long professionalId, Long skillId) {
        response = restTemplate.getForEntity("/api/v1/professional-skills/" + professionalId + "/" + skillId, ProfessionalSkillResponse.class);
    }

    @When("I update the professional skill for professional {long} and skill {long} with the following details:")
    public void i_update_the_professional_skill_for_professional_and_skill_with_the_following_details(Long professionalId, Long skillId, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfessionalSkillRequest request = new ProfessionalSkillRequest();
        request.setProficiencyLevel(data.get("proficiencyLevel"));
        HttpEntity<ProfessionalSkillRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/professional-skills/" + professionalId + "/" + skillId, HttpMethod.PUT, entity, ProfessionalSkillResponse.class);
    }

    @When("I delete the professional skill for professional {long} and skill {long}")
    public void i_delete_the_professional_skill_for_professional_and_skill(Long professionalId, Long skillId) {
        deleteResponse = restTemplate.exchange("/api/v1/professional-skills/" + professionalId + "/" + skillId, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the professional skill is created successfully")
    public void the_professional_skill_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("I should receive the professional skill details")
    public void i_should_receive_the_professional_skill_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the professional skill is updated successfully")
    public void the_professional_skill_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the professional skill is deleted successfully")
    public void the_professional_skill_is_deleted_successfully() {
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the professional skill should have the proficiency level {string}")
    public void the_professional_skill_should_have_the_proficiency_level(String proficiencyLevel) {
        assertEquals(proficiencyLevel, response.getBody().getProficiencyLevel());
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

    private void a_skill_with_id_exists(Long id) {
        SkillRequest request = new SkillRequest();
        request.setName("Java " + id);
        request.setSkillType("TECHNICAL");
        restTemplate.postForEntity("/api/v1/skills", request, ProfessionalSkillResponse.class);
    }
}