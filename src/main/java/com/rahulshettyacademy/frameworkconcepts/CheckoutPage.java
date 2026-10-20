package com.rahulshettyacademy.frameworkconcepts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.rahulshettyacademy.abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//input box locator
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement inputbox;
	//autosuggest list locator
	By autosuggest=By.cssSelector(".list-group");
	//india option xpath
	@FindBy(xpath="//button[contains(@class,'list-group-item')][2]")
	WebElement selectcountry;
	//place order button xpath
	@FindBy(xpath="//a[contains(text(),'Place Order')]")
	WebElement placeorderbtn;

	
	public void selectCountry(String country) {
		//enter country in the field and place order
		Actions a = new Actions(driver);
		a.sendKeys(inputbox, country).build().perform();
		waitForElementToAppear(autosuggest);
		selectcountry.click();
	}
	
	public ConfirmationPage PlaceOrder() {
		//click on place order button 
		placeorderbtn.click();
		return new ConfirmationPage(driver);
	}
	
	
			
		
			
			
}
