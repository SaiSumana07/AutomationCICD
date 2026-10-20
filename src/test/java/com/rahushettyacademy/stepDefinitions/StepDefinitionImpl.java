package com.rahushettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.rahulshettyacademy.frameworkconcepts.CartPage;
import com.rahulshettyacademy.frameworkconcepts.CheckoutPage;
import com.rahulshettyacademy.frameworkconcepts.ConfirmationPage;
import com.rahulshettyacademy.frameworkconcepts.LandingPage;
import com.rahulshettyacademy.frameworkconcepts.ProductCatalog;
import com.rahushettyacademy.testcomponents.BaseTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	LandingPage landingPage;
	ProductCatalog products;
	CartPage cartpage;
	CheckoutPage checkout;
	ConfirmationPage confirmpage;
	
	@Given("Land on to the Ecommerce Page")
	public void land_on_to_The_Ecommerce_Page() throws IOException {
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password) {
		products=landingPage.loginApplication(username,password);
	}
	
	@When("^I add the product (.+) to the Cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> productslist=products.getProducts();
		//select the specific product and click on add to cart and waits for loader and text message to appear
		products.addToCart(productName);
	}

	@And("^Checkout (.+) and submit the order$")
	public void checkout_product_submit_order(String productname){
		//click on cart and checkout items if match found
		cartpage=products.goToCart();
				
		//verify if specified product is displayed in cart 
		Boolean matchfound=cartpage.verifyProductDisplay(productname);
		Assert.assertTrue(matchfound);
		checkout=cartpage.goToCheckout();
		checkout.selectCountry("ind");
		confirmpage=checkout.PlaceOrder();
	}
	
	@Then("Verify {string} message is displayed at the end")
	public void verify_confirm_message(String string) {
		String confirmmessage=confirmpage.verifySuccessMessage();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase(string));
	}
	
	@Then("Verify {string} message is displayed")
	public void verify_error_message_display(String string) {
		Assert.assertEquals(landingPage.getErrorMessage(),string);
	}
}
