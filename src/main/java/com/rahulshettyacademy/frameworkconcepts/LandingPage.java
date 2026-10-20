package com.rahulshettyacademy.frameworkconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.abstractcomponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//defining xpaths of webelements
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEl;
	
	@FindBy(id="login")
	WebElement submit;
	
	By login=By.id("login");
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errormessage;
	
	public ProductCatalog loginApplication(String email, String password) {
	    userEmail.sendKeys(email);
	    passwordEl.sendKeys(password);
	    
	    // Wait for element to be visible/clickable, then click via JavaScript
	    WebElement loginBtn = waitForElementToBeClickable(login);
	    org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", loginBtn);
	    
	    ProductCatalog products = new ProductCatalog(driver);
	    return products;
	}
	
//	public ProductCatalog loginApplication(String email,String password) {
//		userEmail.sendKeys(email);
//		passwordEl.sendKeys(password);
//		//WebElement clickableLogin = driver.findElement(login); // or update AbstractComponents to return the element
//		  WebElement loginbtn=waitForElementToBeClickable(login);
//		// Scroll element into view to clear any floating banners or header overlaps
//	    org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
//	    js.executeScript("arguments[0].scrollIntoView(true);", loginbtn);
//	    loginbtn.click();
//		ProductCatalog products=new ProductCatalog(driver);
//		return products;
//	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
