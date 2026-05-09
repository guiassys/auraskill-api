Feature: Project Management

  Scenario: Create a new project
    Given a professional with id 1 exists
    When I create a project with the following details:
      | professionalId | projectName | projectDescription | startDate  |
      | 1              | "AuraSkill" | "An API for skills" | "2024-01-01" |
    Then the project is created successfully
    And the project should have the name "AuraSkill"

  Scenario: Get a project by id
    Given a project with id 1 exists
    When I get the project with id 1
    Then I should receive the project details

  Scenario: Update a project
    Given a project with id 1 exists
    When I update the project with id 1 with the following details:
      | projectName |
      | "AuraSkill API" |
    Then the project is updated successfully
    And the project should have the name "AuraSkill API"

  Scenario: Delete a project
    Given a project with id 1 exists
    When I delete the project with id 1
    Then the project is deleted successfully