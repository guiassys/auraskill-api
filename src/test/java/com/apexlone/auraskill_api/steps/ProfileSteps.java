package com.apexlone.auraskill_api.steps;

import com.apexlone.auraskill_api.dto.ProfileRequest;
import com.apexlone.auraskill_api.dto.ProfileResponse;
import com.apexlone.auraskill_api.entity.Professional;
import com.apexlone.auraskill_api.repository.ProfessionalRepository;
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

public class ProfileSteps {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProfessionalRepository professionalRepository;

    private ResponseEntity<ProfileResponse> response;
    private ResponseEntity<Void> deleteResponse;

    public static Long currentProfessionalId;
    public static Long currentProfileId; // Captura dinamicamente o ID do perfil criado

    // Método auxiliar para injetar o Token Bearer fake nas requisições
    private HttpHeaders getAuthenticatedHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer mocked-token");
        return headers;
    }

    @Given("a professional with id {long} exists")
    public void a_professional_with_id_exists(Long id) {
        try {
            professionalRepository.deleteAllInBatch();
        } catch (Exception ignored) {}

        Professional professional = Professional.builder()
                .keycloakUserId(UUID.randomUUID())
                .fullName("Test Professional")
                .email("test_" + UUID.randomUUID() + "@test.com")
                .build();

        professional = professionalRepository.saveAndFlush(professional);
        currentProfessionalId = professional.getId();
    }

    @Given("a profile with id {long} exists")
    public void a_profile_with_id_exists(Long id) {
        ProfileRequest request = new ProfileRequest();
        request.setProfessionalId(currentProfessionalId);
        request.setProfessionalTitle("Software Engineer");

        // Envia o POST envelopado com os headers autenticados
        HttpEntity<ProfileRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/profiles", HttpMethod.POST, entity, ProfileResponse.class);

        if (response.getBody() != null) {
            currentProfileId = response.getBody().getId();
        }
    }

    @When("I create a profile with the following details:")
    public void i_create_a_profile_with_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfileRequest request = new ProfileRequest();

        Long targetId = "1".equals(data.get("professionalId")) ? currentProfessionalId : Long.parseLong(data.get("professionalId"));

        request.setProfessionalId(targetId);
        request.setProfessionalTitle(data.get("professionalTitle").replace("\"", ""));
        request.setProfessionalObjective(data.get("professionalObjective").replace("\"", ""));

        // Envia o POST envelopado com os headers autenticados
        HttpEntity<ProfileRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/profiles", HttpMethod.POST, entity, ProfileResponse.class);
    }

    @When("I get the profile with id {long}")
    public void i_get_the_profile_with_id(Long id) {
        Long targetId = (id == 1L && currentProfileId != null) ? currentProfileId : id;

        // Envia o GET envelopado com os headers autenticados
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        response = restTemplate.exchange("/api/v1/profiles/" + targetId, HttpMethod.GET, entity, ProfileResponse.class);
    }

    @When("I update the profile with id {long} with the following details:")
    public void i_update_the_profile_with_id_with_the_following_details(Long id, io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> data = dataTable.asMaps().get(0);
        ProfileRequest request = new ProfileRequest();
        request.setProfessionalTitle(data.get("professionalTitle").replace("\"", ""));

        // Envia o PUT envelopado com os headers autenticados e o body do request juntos
        HttpEntity<ProfileRequest> entity = new HttpEntity<>(request, getAuthenticatedHeaders());
        Long targetId = (id == 1L && currentProfileId != null) ? currentProfileId : id;
        response = restTemplate.exchange("/api/v1/profiles/" + targetId, HttpMethod.PUT, entity, ProfileResponse.class);
    }

    @When("I delete the profile with id {long}")
    public void i_delete_the_profile_with_id(Long id) {
        Long targetId = (id == 1L && currentProfileId != null) ? currentProfileId : id;

        // Envia o DELETE envelopado com os headers autenticados
        HttpEntity<Void> entity = new HttpEntity<>(getAuthenticatedHeaders());
        deleteResponse = restTemplate.exchange("/api/v1/profiles/" + targetId, HttpMethod.DELETE, entity, Void.class);
    }

    @Then("the profile is created successfully")
    public void the_profile_is_created_successfully() {
        int status = response.getStatusCode().value();
        assertTrue(status == 200 || status == 201, "Esperado status 200 ou 201 na criação, mas veio: " + status);
    }

    @Then("I should receive the profile details")
    public void i_should_receive_the_profile_details() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the profile is updated successfully")
    public void the_profile_is_updated_successfully() {
        assertEquals(200, response.getStatusCode().value());
    }

    @Then("the profile is deleted successfully")
    public void the_profile_is_deleted_successfully() {
        int status = deleteResponse.getStatusCode().value();
        assertTrue(status == 200 || status == 204, "Esperado status 200 ou 204 no DELETE, mas veio: " + status);
    }

    @Then("the profile should have the title {string}")
    public void the_profile_should_have_the_title(String title) {
        assertNotNull(response.getBody(), "O corpo da resposta não deveria ser nulo");
        assertEquals(title, response.getBody().getProfessionalTitle());
    }
}