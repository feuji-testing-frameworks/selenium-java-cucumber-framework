Feature: Sauce demo application

  Scenario Outline: Login with valid credentials and check the sorting function
    Given User is on the sauce demo login page
    When User enters the <username> and <password>
    And Click on login button
    Then User navigates to home page
    When User click on sorting
    Then the products are displayed in the sorting order
    And Logout from the application
    And browser closes automatically
    
    Examples:
			| username | password |
			| standard_user | secret_sauce |