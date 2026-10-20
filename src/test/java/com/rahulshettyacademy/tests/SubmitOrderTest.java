package com.rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.rahulshettyacademy.frameworkconcepts.CartPage;
import com.rahulshettyacademy.frameworkconcepts.CheckoutPage;
import com.rahulshettyacademy.frameworkconcepts.ConfirmationPage;
import com.rahulshettyacademy.frameworkconcepts.LandingPage;
import com.rahulshettyacademy.frameworkconcepts.OrderPage;
import com.rahulshettyacademy.frameworkconcepts.ProductCatalog;
import com.rahushettyacademy.testcomponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	
	String productname="ZARA COAT 3";

	@Test(dataProvider="getData",groups="PurchaseOrder")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		
		//login in website using user id and password from landing page 	
		//get products list 
		ProductCatalog products=landingPage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> productslist=products.getProducts();
		
		//select the specific product and click on add to cart and waits for loader and text message to appear
		products.addToCart(input.get("productname"));
		
		//click on cart and checkout items if match found
		CartPage cartpage=products.goToCart();
		
		//verify if specified product is displayed in cart 
		Boolean matchfound=cartpage.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(matchfound);
		CheckoutPage checkout=cartpage.goToCheckout();
		checkout.selectCountry("ind");
		ConfirmationPage confirmpage=checkout.PlaceOrder();
		String confirmmessage=confirmpage.verifySuccessMessage();
		
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));
		
		
		}
	
	//to verify if zara coat 3 displays in orders page
	@Test(dependsOnMethods= {"submitOrder"})
	public void verifyOrdersPage() {
		ProductCatalog products=landingPage.loginApplication("bts@gmail.com", "BTS@jk07");
		OrderPage orderpage=products.goToOrders();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productname));
		
	}
	
//	@DataProvider
//	public Object[][] getData() {
//	HashMap<String,String> map = new HashMap<String,String> ();
//	map.put("email", "bts@gmail.com");
//	map.put("password", "BTS@jk07");
//	map.put("productname", "ZARA COAT 3");
//	
//	HashMap map1 = new HashMap();
//	map1.put("email", "btsjk@gmail.com");
//	map1.put("password", "BTS@jk07");
//	map1.put("productname", "ADIDAS ORIGINAL");
//		return new Object[][] {{"bts@gmail.com", "BTS@jk07","ZARA COAT 3"},{"btsjk@gmail.com","BTS@jk07","ADIDAS ORIGINAL"}};
//	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException{

		List<HashMap<String,String>> data=getJsonDatatoMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\rahushettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}

	}

