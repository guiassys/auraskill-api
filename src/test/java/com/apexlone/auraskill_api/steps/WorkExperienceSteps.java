package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.WorkExperienceRequest;
import com.apexlone.auraskill_api.dto.WorkExperienceResponse;
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
public class WorkExperienceSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<WorkExperienceResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a work experience with id {long} exists")
    public void a_work_experience_with_id_exists(Long id) {
        a_professional_with_id_exists(1L);
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setProfessionalId(1L);
        request.setCompany("Acme");
        request.setPosition("Engineer");
        request.setStartDate(LocalDate.parse("2022-01-01"));
        restTemplate.postForEntity("/api/v1/work-experiences", request, WorkExperienceResponse.class);
    }

    @When("I create a work experience with the following details:")
    public void i_create_a_work_experience_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setCompany(data.get("company"));
        request.setPosition(data.get("position"));
        request.setStartDate(LocalDate.parse(data.get("startDate")));
        response = restTemplate.postForEntity("/api/v1/work-experiences", request, WorkExperienceResponse.class);
    }

    @When("I get the work experience with id {long}")
    public void i_get_the_work_experience_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/work-experiences/" + id, WorkExperienceResponse.class);
    }

    @When("I update the work experience with id {long} with the following details:")
    public void i_update_the_work_experience_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        WorkExperienceRequest request = new WorkExperienceRequest();
        request.setCompany(data.get("company"));
        HttpEntity<WorkExperienceRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/work-experiences/" + id, HttpMethod.PUT, entity, WorkExperienceResponse.class);
    }

    @When("I delete the work experience with id {long}")
    public void i_delete_the_work_experience_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/work-experiences/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the work experience is created successfully")
    public void the_work_experience_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
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
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the work experience should have the company {string}")
    public void the_work_experience_should_have_the_company(String company) {
        assertEquals(company, response.getBody().getCompany());
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