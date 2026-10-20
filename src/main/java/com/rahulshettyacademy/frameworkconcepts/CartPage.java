package com.rahulshettyacademy.frameworkconcepts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.rahulshettyacademy.abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//cartitems
	@FindBy(css=".cartSection h3")
	List<WebElement> cartitems;
	//checkout btn
	@FindBy(css=".totalRow button")
	WebElement checkoutbtn;
	
	public Boolean verifyProductDisplay(String productname) {
		//in cart check if the product has been added
		//first grab elements in cart 
		Boolean matchfound=cartitems.stream().anyMatch(item->item.getText().equals(productname));
		return matchfound;
		
	}
	
	public CheckoutPage goToCheckout() {
		//click on checkout if match found
		checkoutbtn.click();
		CheckoutPage checkout=new CheckoutPage(driver);
		return checkout;
	}

}
