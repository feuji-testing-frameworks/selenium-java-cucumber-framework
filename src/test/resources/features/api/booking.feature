Feature: Booking API Tests

 Background:
 		Given I have the user credentials
    When I create a token
    Then the token is created successfully
    Given I have a booking payload
    When I create a booking
    Then the booking is created successfully

  Scenario: Get Booking By Id
    Given I have a booking id
    When I get the booking by id
    Then the booking is retrieved successfully

  Scenario: Update Booking By Id
    Given I have a booking id and updated booking payload
    When I update the booking by id
    Then the booking is updated successfully

  Scenario: Delete Booking
    Given I have a booking id
    When I delete the booking
    Then the booking is deleted successfully
