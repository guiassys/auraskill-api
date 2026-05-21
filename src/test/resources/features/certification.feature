Feature: Certification Management

  Background:
    Given a professional exists to own the certification

  Scenario: Create a new certification
    When I create a certification with the following details:
      | certificationName | institution | description                   |
      | "AWS Cloud Practitioner" | "AWS" | "Foundational cloud knowledge" |
    Then the certification is created successfully
    And the certification should have the name "AWS Cloud Practitioner"

  Scenario: Get a certification by id
    Given a certification exists in the system
    When I get the certification details
    Then I should receive the certification details

  Scenario: Update a certification
    Given a certification exists in the system
    When I update the certification with the following details:
      | certificationName        | institution |
      | "AWS Solutions Architect" | "AWS"       |
    Then the certification is updated successfully
    And the certification should have the name "AWS Solutions Architect"

  Scenario: Delete a certification
    Given a certification exists in the system
    When I delete the certification
    Then the certification is deleted successfully
