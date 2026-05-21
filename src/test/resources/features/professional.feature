Feature: Professional Management

  Scenario: Create a new professional
    When I create a professional with the following details:
      | fullName           | email                | phone          | bio                     |
      | "Jane Doe Developer"| "jane.doe@test.com"  | "+551199999999"| "Fullstack Developer"   |
    Then the professional is created successfully
    And the professional should have the name "Jane Doe Developer"

  Scenario: Get a professional by id
    Given a professional exists in the system
    When I get the professional details
    Then I should receive the professional details

  Scenario: Update a professional
    Given a professional exists in the system
    When I update the professional with the following details:
      | fullName           | bio                             |
      | "Jane Doe Senior"   | "Senior Fullstack Developer"    |
    Then the professional is updated successfully
    And the professional should have the name "Jane Doe Senior"

  Scenario: Delete a professional
    Given a professional exists in the system
    When I delete the professional
    Then the professional is deleted successfully
