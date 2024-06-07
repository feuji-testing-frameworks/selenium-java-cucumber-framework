Feature: This is sauce demo login page

  Scenario: Login with valid credentials
    Given User is on the sauce demo login page
    When User enters the <username> and <password>
    And Click on login button
    Then User navigates to home page
    And Logout from the application
    And browser closes automatically
    
    Examples:
			| username | password |
			| standard_user | secret_sauce |
			| performance_glitch_user | secret_sauce |
			| visual_user | secret_sauce |
    
  	
