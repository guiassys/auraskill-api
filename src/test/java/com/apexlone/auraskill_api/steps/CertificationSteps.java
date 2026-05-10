package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.CertificationRequest;
import com.apexlone.auraskill_api.dto.CertificationResponse;
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
public class CertificationSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<CertificationResponse> response;
    private ResponseEntity<Void> deleteResponse;

    @Given("a certification with id {long} exists")
    public void a_certification_with_id_exists(Long id) {
        a_professional_with_id_exists(1L);
        CertificationRequest request = new CertificationRequest();
        request.setProfessionalId(1L);
        request.setCertificationName("AWS Certified");
        request.setInstitution("Amazon");
        request.setIssueDate(LocalDate.parse("2023-01-01"));
        restTemplate.postForEntity("/api/v1/certifications", request, CertificationResponse.class);
    }

    @When("I create a certification with the following details:")
    public void i_create_a_certification_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        CertificationRequest request = new CertificationRequest();
        request.setProfessionalId(Long.parseLong(data.get("professionalId")));
        request.setCertificationName(data.get("certificationName"));
        request.setInstitution(data.get("institution"));
        request.setIssueDate(LocalDate.parse(data.get("issueDate")));
        response = restTemplate.postForEntity("/api/v1/certifications", request, CertificationResponse.class);
    }

    @When("I get the certification with id {long}")
    public void i_get_the_certification_with_id(Long id) {
        response = restTemplate.getForEntity("/api/v1/certifications/" + id, CertificationResponse.class);
    }

    @When("I update the certification with id {long} with the following details:")
    public void i_update_the_certification_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        CertificationRequest request = new CertificationRequest();
        request.setCertificationName(data.get("certificationName"));
        HttpEntity<CertificationRequest> entity = new HttpEntity<>(request);
        response = restTemplate.exchange("/api/v1/certifications/" + id, HttpMethod.PUT, entity, CertificationResponse.class);
    }

    @When("I delete the certification with id {long}")
    public void i_delete_the_certification_with_id(Long id) {
        deleteResponse = restTemplate.exchange("/api/v1/certifications/" + id, HttpMethod.DELETE, null, Void.class);
    }

    @Then("the certification is created successfully")
    public void the_certification_is_created_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("I should receive the certification details")
    public void i_should_receive_the_certification_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the certification is updated successfully")
    public void the_certification_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the certification is deleted successfully")
    public void the_certification_is_deleted_successfully() {
        assertEquals(200, deleteResponse.getStatusCode().value());
    }

    @Then("the certification should have the name {string}")
    public void the_certification_should_have_the_name(String name) {
        assertEquals(name, response.getBody().getCertificationName());
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