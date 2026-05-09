Feature: Certification Management

  Scenario: Create a new certification
    Given a professional with id 1 exists
    When I create a certification with the following details:
      | professionalId | certificationName | institution | issueDate  |
      | 1              | "AWS Certified"   | "Amazon"    | "2023-01-01" |
    Then the certification is created successfully
    And the certification should have the name "AWS Certified"

  Scenario: Get a certification by id
    Given a certification with id 1 exists
    When I get the certification with id 1
    Then I should receive the certification details

  Scenario: Update a certification
    Given a certification with id 1 exists
    When I update the certification with id 1 with the following details:
      | certificationName |
      | "GCP Certified"   |
    Then the certification is updated successfully
    And the certification should have the name "GCP Certified"

  Scenario: Delete a certification
    Given a certification with id 1 exists
    When I delete the certification with id 1
    Then the certification is deleted successfully