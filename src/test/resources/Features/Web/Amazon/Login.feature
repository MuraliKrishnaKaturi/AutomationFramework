Feature: Login

	@Tag1
  Scenario: Launching the web application
    Given Launch the application
    And Click on the "SignIn" button
    Then Verify if login page is displayed
    
  @Tag2
  Scenario: Logging into the application
    Given Launch the application
    And Click on the "SignIn" button
    Then Verify if login page is displayed
    Then Enter the "Email" in text field
    Then Click on the "Continue" button
    Then Verify if password page is displayed
