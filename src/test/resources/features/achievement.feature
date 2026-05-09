Feature: Achievement Management

  Scenario: Create a new achievement
    Given a professional with id 1 exists
    When I create an achievement with the following details:
      | professionalId | achievementName | achievementDescription | achievementDate |
      | 1              | "Hackathon Winner" | "Won the local hackathon" | "2023-05-10" |
    Then the achievement is created successfully
    And the achievement should have the name "Hackathon Winner"

  Scenario: Get an achievement by id
    Given an achievement with id 1 exists
    When I get the achievement with id 1
    Then I should receive the achievement details

  Scenario: Update an achievement
    Given an achievement with id 1 exists
    When I update the achievement with id 1 with the following details:
      | achievementName |
      | "Code Jam Finisher" |
    Then the achievement is updated successfully
    And the achievement should have the name "Code Jam Finisher"

  Scenario: Delete an achievement
    Given an achievement with id 1 exists
    When I delete the achievement with id 1
    Then the achievement is deleted successfully