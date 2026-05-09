Feature: Professional Skill Management

  Scenario: Create a new professional skill
    Given a professional with id 1 exists
    And a skill with id 1 exists
    When I create a professional skill with the following details:
      | professionalId | skillId | proficiencyLevel | yearsOfExperience |
      | 1              | 1       | "INTERMEDIATE"   | 2                 |
    Then the professional skill is created successfully
    And the professional skill should have the proficiency level "INTERMEDIATE"

  Scenario: Get a professional skill by id
    Given a professional skill for professional 1 and skill 1 exists
    When I get the professional skill for professional 1 and skill 1
    Then I should receive the professional skill details

  Scenario: Update a professional skill
    Given a professional skill for professional 1 and skill 1 exists
    When I update the professional skill for professional 1 and skill 1 with the following details:
      | proficiencyLevel |
      | "ADVANCED"       |
    Then the professional skill is updated successfully
    And the professional skill should have the proficiency level "ADVANCED"

  Scenario: Delete a professional skill
    Given a professional skill for professional 1 and skill 1 exists
    When I delete the professional skill for professional 1 and skill 1
    Then the professional skill is deleted successfully