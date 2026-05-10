Feature: Skill Management

  Scenario: Create a new skill
    When I create a skill with the following details:
      | name      | skillType |
      | "Java"    | "TECHNICAL" |
    Then the skill is created successfully
    And the skill should have the name "Java"

  Scenario: Get a skill by id
    Given a skill with id 1 exists
    When I get the skill with id 1
    Then I should receive the skill details

  Scenario: Update a skill
    Given a skill with id 1 exists
    When I update the skill with id 1 with the following details:
      | name      |
      | "Java 17" |
    Then the skill is updated successfully
    And the skill should have the name "Java 17"

  Scenario: Delete a skill
    Given a skill with id 1 exists
    When I delete the skill with id 1
    Then the skill is deleted successfully