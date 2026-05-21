package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.ProjectRequest;
import com.apexlone.auraskill_api.dto.ProjectResponse;
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

public class ProjectSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<ProjectResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long targetProfessionalId;
    private Long createdProjectId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists to own the project")
    public void a_professional_exists_to_own_the_project() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) {}

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Project Owner")
                .email("owner_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        targetProfessionalId = professional.getId();
    }

    @Given("a project exists in the system")
    public void a_project_exists_in_the_system() {
        ProjectRequest request = new ProjectRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setProjectName("Initial Project");
        request.setProjectUrl("https://github.com/initial");
        request.setStartDate(LocalDate.now());

        HttpEntity<ProjectRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/projects", HttpMethod.POST, entity, ProjectResponse.class);
        
        if (response.getBody() != null) {
            createdProjectId = response.getBody().getId();
        }
    }

    @When("I create a project with the following details:")
    public void i_create_a_project_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        ProjectRequest request = new ProjectRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setProjectName(data.get("projectName").replace("\"", ""));
        request.setProjectUrl(data.get("projectUrl") != null ? data.get("projectUrl").replace("\"", "") : null);
        request.setProjectDescription(data.get("projectDescription") != null ? data.get("projectDescription").replace("\"", "") : null);
        request.setStartDate(LocalDate.now());

        HttpEntity<ProjectRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/projects", HttpMethod.POST, entity, ProjectResponse.class);
        
        if (response.getBody() != null) {
            createdProjectId = response.getBody().getId();
        }
    }

    @When("I get the project details")
    public void i_get_the_project_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/projects/" + createdProjectId, HttpMethod.GET, entity, ProjectResponse.class);
    }

    @When("I update the project with the following details:")
    public void i_update_the_project_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        ProjectRequest request = new ProjectRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setProjectName(data.get("projectName").replace("\"", ""));
        request.setProjectUrl(data.get("projectUrl") != null ? data.get("projectUrl").replace("\"", "") : null);

        HttpEntity<ProjectRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/projects/" + createdProjectId, HttpMethod.PUT, entity, ProjectResponse.class);
    }

    @When("I delete the project")
    public void i_delete_the_project() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/projects/" + createdProjectId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the project is created successfully")
    public void the_project_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
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
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the project should have the name {string}")
    public void the_project_should_have_the_name(String name) {
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getProjectName());
    }
}
