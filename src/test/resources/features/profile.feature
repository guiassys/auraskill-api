Feature: Profile Management

  Background:
    Given a professional with id 1 exists

  Scenario: Create a new profile
    When I create a profile with the following details:
      | professionalId | professionalTitle   | professionalObjective   |
      | 1              | "Software Engineer" | "To build great things" |
    Then the profile is created successfully
    And the profile should have the title "Software Engineer"

  Scenario: Get a profile by id
    Given a profile with id 1 exists
    When I get the profile with id 1
    Then I should receive the profile details

  Scenario: Update a profile
    Given a profile with id 1 exists
    When I update the profile with id 1 with the following details:
      | professionalTitle          |
      | "Senior Software Engineer" |
    Then the profile is updated successfully
    And the profile should have the title "Senior Software Engineer"

  Scenario: Delete a profile
    Given a profile with id 1 exists
    When I delete the profile with id 1
    Then the profile is deleted successfully