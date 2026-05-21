# language: en
Feature: Achievement Management

  Background:
    Given a professional exists to own the achievement

  Scenario: Create a new achievement
    When I create an achievement with the following details:
      | achievementName | organizer      | achievementDescription  |
      | "Java Champion" | "Oracle Corp"  | "Honored for community" |
    Then the achievement is created successfully
    And the achievement should have the name "Java Champion"

  Scenario: Get an achievement by id
    Given an achievement exists in the system
    When I get the achievement details
    Then I should receive the achievement details

  Scenario: Update an achievement
    Given an achievement exists in the system
    When I update the achievement with the following details:
      | achievementName   | organizer     |
      | "Senior Architect"| "ApexLone HQ" |
    Then the achievement is updated successfully
    And the achievement should have the name "Senior Architect"

  Scenario: Delete an achievement
    Given an achievement exists in the system
    When I delete the achievement
    Then the achievement is deleted successfully
