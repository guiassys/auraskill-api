package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.CertificationRequest;
import com.apexlone.auraskill_api.dto.CertificationResponse;
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

public class CertificationSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<CertificationResponse> response;
    private ResponseEntity<Void> deleteResponse;
    
    private Long targetProfessionalId;
    private Long createdCertificationId;

    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional exists to own the certification")
    public void a_professional_exists_to_own_the_certification() {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) { System.out.println("Tabela ja limpa ou vazia."); }

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Certification Owner")
                .email("owner_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        targetProfessionalId = professional.getId();
    }

    @Given("a certification exists in the system")
    public void a_certification_exists_in_the_system() {
        CertificationRequest request = new CertificationRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCertificationName("Initial Cert");
        request.setInstitution("Some Institution");
        request.setIssueDate(LocalDate.now());

        HttpEntity<CertificationRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/certifications", HttpMethod.POST, entity, CertificationResponse.class);
        
        if (response.getBody() != null) {
            createdCertificationId = response.getBody().getId();
        }
    }

    @When("I create a certification with the following details:")
    public void i_create_a_certification_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        CertificationRequest request = new CertificationRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCertificationName(data.get("certificationName").replace("\"", ""));
        request.setInstitution(data.get("institution") != null ? data.get("institution").replace("\"", "") : null);
        request.setDescription(data.get("description") != null ? data.get("description").replace("\"", "") : null);
        request.setIssueDate(LocalDate.now());

        HttpEntity<CertificationRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/certifications", HttpMethod.POST, entity, CertificationResponse.class);
        
        if (response.getBody() != null) {
            createdCertificationId = response.getBody().getId();
        }
    }

    @When("I get the certification details")
    public void i_get_the_certification_details() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/certifications/" + createdCertificationId, HttpMethod.GET, entity, CertificationResponse.class);
    }

    @When("I update the certification with the following details:")
    public void i_update_the_certification_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        
        CertificationRequest request = new CertificationRequest();
        request.setProfessionalId(targetProfessionalId);
        request.setCertificationName(data.get("certificationName").replace("\"", ""));
        request.setInstitution(data.get("institution") != null ? data.get("institution").replace("\"", "") : null);

        HttpEntity<CertificationRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/certifications/" + createdCertificationId, HttpMethod.PUT, entity, CertificationResponse.class);
    }

    @When("I delete the certification")
    public void i_delete_the_certification() {
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/certifications/" + createdCertificationId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the certification is created successfully")
    public void the_certification_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201, obtido: " + status);
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
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204, obtido: " + status);
    }

    @And("the certification should have the name {string}")
    public void the_certification_should_have_the_name(String name) {
        assertNotNull(response.getBody());
        assertEquals(name, response.getBody().getCertificationName());
    }
}
