package com.rahulshettyacademy.tests;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.rahulshettyacademy.frameworkconcepts.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productname="ZARA COAT 3";
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		LandingPage landing=new LandingPage(driver);
		//enter user email
		driver.findElement(By.id("userEmail")).sendKeys("bts@gmail.com");
		//enter password
		driver.findElement(By.id("userPassword")).sendKeys("BTS@jk07");
		//click on login button 
		driver.findElement(By.id("login")).click();
		
		//add waits to load all elements
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(4));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));
		//get products list 
		List<WebElement> products=driver.findElements(By.cssSelector(".col-lg-4"));
		
	
		//find specific product and click add to cart 
		//my solution
		//products.stream().filter(product->product.getText().contains("ZARA")).forEach(p->p.findElement(By.cssSelector(".fa-shopping-cart")).click());
		
		//optimized solution by rahulshetty
		WebElement prod=products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productname))
		.findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//wait until added to cart text and loading con disapperas
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("ng-animating"))); - taking lot of time so use anothert 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		//click on cart button 
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//in cart check if the product has been added
		//first grab elements in cart 
		List<WebElement> cartitems=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean matchfound=cartitems.stream().anyMatch(item->item.getText().equals(productname));
		Assert.assertTrue(matchfound);
		
		//click on checkout if match found
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//enter country in the field and place order
		WebElement inputbox=driver.findElement(By.xpath("//input[@placeholder='Select Country']"));
		Actions a = new Actions(driver);
		a.sendKeys(inputbox, "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".list-group")));
		driver.findElement(By.xpath("//button[contains(@class,'list-group-item')][2]")).click();
		//click on place order button 
		driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
		
		//check if message is displayed succesfully
		String confirmmessage=driver.findElement(By.tagName("h1")).getText();
		Assert.assertTrue(confirmmessage.equalsIgnoreCase("Thankyou for the order."));
		}

	}

