package com.rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.rahulshettyacademy.frameworkconcepts.CartPage;
import com.rahulshettyacademy.frameworkconcepts.CheckoutPage;
import com.rahulshettyacademy.frameworkconcepts.ConfirmationPage;
import com.rahulshettyacademy.frameworkconcepts.ProductCatalog;
import com.rahushettyacademy.testcomponents.BaseTest;
import com.rahushettyacademy.testcomponents.Retry;

public class ErrorValidation extends BaseTest{
	
	
	@Test(groups= {"Error Handling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() {
		String productname="ZARA COAT 3";
		
		//login in website using user id and password from landing page 	
		//get products list 
		landingPage.loginApplication("btsjungkook@gmail.com", "BTS@jk07");
		Assert.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");
	}
	
	@Test
	public void productErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		
		String productname="ZARA COAT 3";
		
		//login in website using user id and password from landing page 	
		//get products list 
		ProductCatalog products=landingPage.loginApplication("bts@gmail.com", "BTS@jk07");
		List<WebElement> productslist=products.getProducts();
		
		//select the specific product and click on add to cart and waits for loader and text message to appear
		products.addToCart(productname);
		
		//click on cart and checkout items if match found
		CartPage cartpage=products.goToCart();
		
		//verify if specified product is displayed in cart 
		Boolean matchfound=cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(matchfound);
		
		
		}
}
