# language: en
Feature: Project Management

  Background:
    Given a professional exists to own the project

  Scenario: Create a new project
    When I create a project with the following details:
      | projectName | projectUrl                  | projectDescription             |
      | "AuraSkill" | "https://github.com/aura"  | "Skills management ecosystem"  |
    Then the project is created successfully
    And the project should have the name "AuraSkill"

  Scenario: Get a project by id
    Given a project exists in the system
    When I get the project details
    Then I should receive the project details

  Scenario: Update a project
    Given a project exists in the system
    When I update the project with the following details:
      | projectName         | projectUrl                       |
      | "AuraSkill Premium" | "https://github.com/aura-pro"   |
    Then the project is updated successfully
    And the project should have the name "AuraSkill Premium"

  Scenario: Delete a project
    Given a project exists in the system
    When I delete the project
    Then the project is deleted successfully
