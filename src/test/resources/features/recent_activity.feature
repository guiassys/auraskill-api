Feature: Recent Activity Management

  Scenario: Create a new recent activity
    Given a professional with id 1 exists
    When I create a recent activity with the following details:
      | professionalId | activityType | activityDescription |
      | 1              | "NEW_SKILL"  | "Added Java skill"  |
    Then the recent activity is created successfully
    And the recent activity should have the type "NEW_SKILL"

  Scenario: Get a recent activity by id
    Given a recent activity with id 1 exists
    When I get the recent activity with id 1
    Then I should receive the recent activity details

  Scenario: Update a recent activity
    Given a recent activity with id 1 exists
    When I update the recent activity with id 1 with the following details:
      | activityType |
      | "UPDATED_SKILL" |
    Then the recent activity is updated successfully
    And the recent activity should have the type "UPDATED_SKILL"

  Scenario: Delete a recent activity
    Given a recent activity with id 1 exists
    When I delete the recent activity with id 1
    Then the recent activity is deleted successfully