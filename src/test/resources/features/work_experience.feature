Feature: Work Experience Management

  Scenario: Create a new work experience
    Given a professional with id 1 exists
    When I create a work experience with the following details:
      | professionalId | company | position | startDate  |
      | 1              | "Acme"  | "Engineer" | "2022-01-01" |
    Then the work experience is created successfully
    And the work experience should have the company "Acme"

  Scenario: Get a work experience by id
    Given a work experience with id 1 exists
    When I get the work experience with id 1
    Then I should receive the work experience details

  Scenario: Update a work experience
    Given a work experience with id 1 exists
    When I update the work experience with id 1 with the following details:
      | company |
      | "Globex" |
    Then the work experience is updated successfully
    And the work experience should have the company "Globex"

  Scenario: Delete a work experience
    Given a work experience with id 1 exists
    When I delete the work experience with id 1
    Then the work experience is deleted successfully