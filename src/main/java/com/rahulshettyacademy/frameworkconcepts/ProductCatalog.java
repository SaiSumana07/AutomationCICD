package com.rahulshettyacademy.frameworkconcepts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.rahulshettyacademy.abstractcomponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents{
	
	WebDriver driver;
	
	public ProductCatalog(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//products xpath
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	
	//products by path to send to wait method
	By productsBy=By.cssSelector(".col-lg-4");
	//add to cart by path
	By addtocart=By.cssSelector(".card-body button:last-of-type");
	//toast message by path
	By toastmessage=By.cssSelector("#toast-container");
	//loader locator 
	@FindBy(css=".ng-animating")
	WebElement loader;
	
	
	
	public List<WebElement> getProducts() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productname) {
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname))
				.findFirst().orElse(null);
		return prod;
	}
	
	
	public void addToCart(String productname) {
		WebElement product=getProductByName(productname);
		product.findElement(addtocart).click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisappear(loader);
	}
	
	
	
			
			
			
			
			
	
}
