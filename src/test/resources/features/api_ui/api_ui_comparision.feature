Feature: API and UI Data Comparison

  Scenario: Verify data consistency between API and UI
    Given I fetch h2 headers from the API
    When I fetch h2 headers from the UI
    Then the h2 headers from API and UI should match
