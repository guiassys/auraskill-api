# language: en
Feature: Skill Management

  Scenario: Create a new skill
    When I create a skill with the following details:
      | name     | skillType |
      | "Java"   | "HARD"    |
    Then the skill is created successfully
    And the skill should have the name "Java"

  Scenario: Get a skill by id
    Given a skill exists in the system
    When I get the skill details
    Then I should receive the skill details

  Scenario: Update a skill
    Given a skill exists in the system
    When I update the skill with the following details:
      | name       | skillType |
      | "Docker"   | "HARD"    |
    Then the skill is updated successfully
    And the skill should have the name "Docker"

  Scenario: Delete a skill
    Given a skill exists in the system
    When I delete the skill
    Then the skill is deleted successfully
