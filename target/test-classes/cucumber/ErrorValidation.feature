@tag
Feature: Error Validation for EcommerceWebsite

		
	@ErrorValidation
	Scenario Outline: Negative test for validating with incorrect email or password
		Given Land on to the Ecommerce Page
		When Logged in with username <name> and password <password>
		Then Verify "Incorrect email or password." message is displayed
		
		Examples:
			|name            |password         |
			|bts@gmail.com	 |BTS@jk0k		   |