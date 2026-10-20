@tag
Feature: Purchase the order from Ecommerce Website

Background:
	Given Land on to the Ecommerce Page
		
	@Regression
	Scenario Outline: Positive test of submitting an order from Ecommerce Website
		Given Logged in with username <name> and password <password>
		When I add the product <productName> to the Cart
		And Checkout <productName> and submit the order
		Then Verify "Thankyou for the order." message is displayed at the end
		
		Examples:
			|name            |password         |productName |
			|bts@gmail.com	 |BTS@jk07		   |ZARA COAT 3	|