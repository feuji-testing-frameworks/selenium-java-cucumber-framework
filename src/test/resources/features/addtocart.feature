Feature: Sauce demo application Add to cart

  Scenario: As a user, I can add product to the cart and checkout the product
    Given User is on the sauce demo login page
    When User enters the <username> and <password>
    And Click on login button
    Then User navigates to home page
    When User add the product to the cart
    And Click on the checkout
    Then Enter the details
    And Click on finish
    Then User see the Back to Home Btn
    And Click the Back to Home Btn
    And Logout from the application
    And browser closes automatically
    
    Examples:
			| username | password |
			| standard_user | secret_sauce |
