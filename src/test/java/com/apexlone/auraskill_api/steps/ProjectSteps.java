package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.ProjectRequest;
import com.apexlone.auraskill_api.dto.ProjectResponse;
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
public class ProjectSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<ProjectResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a project with id {long} exists")
    public void a_project_with_id_exists(Long id) {
        a_professional_with_id_exists(1L);
        ProjectRequest request = new ProjectRequest();
        request.setProfessionalId(1L);
        request.setProjectName("AuraSkill");
        request.setProjectDescription("An API for skills");
        request.setStartDate(LocalDate.parse("2024-01-01"));
        restTemplate.postForEntity("/api/v1/projects", request, ProjectResponse.class);
    }

    @When("I create a project with the following details:")
    public void i_create_a_project_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProjectRequest request = new ProjectRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setProjectName(data.get("projectName"));
        request.setProjectDescription(data.get("projectDescription"));
        request.setStartDate(LocalDate.parse(data.get("startDate")));
        response = restTemplate.postForEntity("/api/v1/projects", request, ProjectResponse.class);
    }

    @When("I get the project with id {long}")
    public void i_get_the_project_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/projects/" + id, ProjectResponse.class);
    }

    @When("I update the project with id {long} with the following details:")
    public void i_update_the_project_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProjectRequest request = new ProjectRequest();
        request.setProjectName(data.get("projectName"));
        HttpEntity<ProjectRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/projects/" + id, HttpMethod.PUT, entity, ProjectResponse.class);
    }

    @When("I delete the project with id {long}")
    public void i_delete_the_project_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/projects/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the project is created successfully")
    public void the_project_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("I should receive the project details")
    public void i_should_receive_the_project_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the project is updated successfully")
    public void the_project_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the project is deleted successfully")
    public void the_project_is_deleted_successfully() {
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the project should have the name {string}")
    public void the_project_should_have_the_name(String name) {
        assertEquals(name, response.getBody().getProjectName());
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