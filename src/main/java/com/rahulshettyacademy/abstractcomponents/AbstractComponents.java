package com.rahulshettyacademy.abstractcomponents;

import java.time.Duration;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.rahulshettyacademy.frameworkconcepts.CartPage;
import com.rahulshettyacademy.frameworkconcepts.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		//adding comments for pushing code to github and trigger automatically
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	//cart button xpath
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cart;
	
	//orders button to navigate to orders page
	@FindBy(xpath="//button[contains(text(),'ORDERS')]")
	WebElement orders;
	
	
	public CartPage goToCart() {
		cart.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrders() {
		orders.click();
		OrderPage orderpage=new OrderPage(driver);
		return orderpage;
	}
	public void waitForElementToAppear(By findby) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	public @NonNull WebElement waitForElementToBeClickable(By findby) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.elementToBeClickable(findby));
	}
	
	public void waitForWebElementToAppear(WebElement el) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	public void waitForElementToDisappear(WebElement el) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOf(el));
	}

}
