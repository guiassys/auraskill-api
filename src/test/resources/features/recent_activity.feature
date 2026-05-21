# language: en
Feature: Recent Activity Management

  Background:
    Given a professional exists to own the recent activity

  Scenario: Create a new recent activity
    When I create a recent activity with the following details:
      | activityType | activityDescription               |
      | "PROFILE_UPDATE" | "User updated their skill list" |
    Then the recent activity is created successfully
    And the recent activity should have the type "PROFILE_UPDATE"

  Scenario: Get a recent activity by id
    Given a recent activity exists in the system
    When I get the recent activity details
    Then I should receive the recent activity details

  Scenario: Update a recent activity
    Given a recent activity exists in the system
    When I update the recent activity with the following details:
      | activityType      | activityDescription                      |
      | "CERT_ADDITION"   | "User added a new security certificate"  |
    Then the recent activity is updated successfully
    And the recent activity should have the type "CERT_ADDITION"

  Scenario: Delete a recent activity
    Given a recent activity exists in the system
    When I delete the recent activity
    Then the recent activity is deleted successfully
