Feature: Mock Booking API Tests

 Background:
    Given I have the mock user credentials
    When I create a mock token
    Then the mock token is created successfully
    Given I have a mock booking payload
    When I create a mock booking
    Then the mock booking is created successfully

 Scenario: Create Mock Booking
    Given I have a mock booking payload
    When I create a mock booking
    Then the mock booking is created successfully
  
 Scenario: Get Mock Booking By Id
    Given I have a mock booking id
    When I get the mock booking by id
    Then the mock booking is retrieved successfully

 Scenario: Update Mock Booking By Id
    Given I have a mock booking id and updated mock booking payload
    When I update the mock booking by id
    Then the mock booking is updated successfully
    
 Scenario: Patch Mock Booking By Id
    Given I have a mock booking id and patch mock booking payload
    When I patch the mock booking by id
    Then the mock booking is patched successfully

 Scenario: Delete Mock Booking
    Given I have a mock booking id
    When I delete the mock booking
    Then the mock booking is deleted successfully
