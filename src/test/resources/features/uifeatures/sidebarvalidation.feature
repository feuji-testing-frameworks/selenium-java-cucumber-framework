Feature: This is sauce demo application side bar validation

  Scenario: Login with valid credentials and checking the elements present in side bar
    Given User is on the sauce demo login page
    When User enters the <username> and <password>
    And Click on login button
    Then User navigates to home page
    When User click on side bar
    And Check the elements present or not
    Then Click on close button
    And Logout from the application
    And browser closes automatically
    
    Examples:
			| username | password |
			| standard_user | secret_sauce |
    