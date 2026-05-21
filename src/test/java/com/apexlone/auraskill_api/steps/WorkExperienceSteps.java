package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.WorkExperienceRequest;
import com.apexlone.auraskill_api.dto.WorkExperienceResponse;
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

public class WorkExperienceSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<WorkExperienceResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long targetProfessionalId;
    private Long createdExperienceId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists to own the work experience")
    public void a_professional_exists_to_own_the_work_experience() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) { System.out.println("Tabela ja limpa ou vazia."); }

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Experience Owner")
                .email("owner_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        targetProfessionalId = professional.getId();
    }

    @Given("a work experience exists in the system")
    public void a_work_experience_exists_in_the_system() {
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCompany("Initial Company");
        request.setPosition("Developer");
        request.setStartDate(LocalDate.now().minusYears(1));

        HttpEntity<WorkExperienceRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/work-experiences", HttpMethod.POST, entity, WorkExperienceResponse.class);
        
        if (response.getBody() != null) {
            createdExperienceId = response.getBody().getId();
        }
    }

    @When("I create a work experience with the following details:")
    public void i_create_a_work_experience_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCompany(data.get("company").replace("\"", ""));
        request.setPosition(data.get("position").replace("\"", ""));
        request.setActivitiesDescription(data.get("activitiesDescription") != null ? data.get("activitiesDescription").replace("\"", "") : null);
        request.setProjectHighlight(data.get("projectHighlight") != null ? data.get("projectHighlight").replace("\"", "") : null);
        request.setStartDate(LocalDate.now().minusYears(2));

        HttpEntity<WorkExperienceRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/work-experiences", HttpMethod.POST, entity, WorkExperienceResponse.class);
        
        if (response.getBody() != null) {
            createdExperienceId = response.getBody().getId();
        }
    }

    @When("I get the work experience details")
    public void i_get_the_work_experience_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/work-experiences/" + createdExperienceId, HttpMethod.GET, entity, WorkExperienceResponse.class);
    }

    @When("I update the work experience with the following details:")
    public void i_update_the_work_experience_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCompany(data.get("company").replace("\"", ""));
        request.setPosition(data.get("position").replace("\"", ""));
        request.setStartDate(LocalDate.now().minusYears(2)); // Campo obrigatório na entidade

        HttpEntity<WorkExperienceRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/work-experiences/" + createdExperienceId, HttpMethod.PUT, entity, WorkExperienceResponse.class);
    }

    @When("I delete the work experience")
    public void i_delete_the_work_experience() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/work-experiences/" + createdExperienceId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the work experience is created successfully")
    public void the_work_experience_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
    }

    @Then("I should receive the work experience details")
    public void i_should_receive_the_work_experience_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the work experience is updated successfully")
    public void the_work_experience_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the work experience is deleted successfully")
    public void the_work_experience_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the work experience should have the company {string}")
    public void the_work_experience_should_have_the_company(String company) {
        assertNotNull(response.getBody());
        assertEquals(company, response.getBody().getCompany());
    }
}
