# language: en
Feature: Work Experience Management

  Background:
    Given a professional exists to own the work experience

  Scenario: Create a new work experience
    When I create a work experience with the following details:
      | company    | position          | activitiesDescription        | projectHighlight       |
      | "ApexLone" | "Java Developer"  | "Developing backend systems" | "Migrated monolith"    |
    Then the work experience is created successfully
    And the work experience should have the company "ApexLone"

  Scenario: Get a work experience by id
    Given a work experience exists in the system
    When I get the work experience details
    Then I should receive the work experience details

  Scenario: Update a work experience
    Given a work experience exists in the system
    When I update the work experience with the following details:
      | company         | position          |
      | "ApexLone Corp" | "Tech Lead"       |
    Then the work experience is updated successfully
    And the work experience should have the company "ApexLone Corp"

  Scenario: Delete a work experience
    Given a work experience exists in the system
    When I delete the work experience
    Then the work experience is deleted successfully
