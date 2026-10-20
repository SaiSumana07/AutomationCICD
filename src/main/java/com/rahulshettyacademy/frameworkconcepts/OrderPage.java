package com.rahulshettyacademy.frameworkconcepts;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.rahulshettyacademy.abstractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;

	public OrderPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//checkout btn
	@FindBy(xpath="//tr/td[2]")
	List<WebElement> productitems;
	

	public boolean verifyOrderDisplay(String productname) {
		// TODO Auto-generated method stub
		//in cart check if the product has been added
		//first grab elements in cart 
		Boolean matchfound=productitems.stream().anyMatch(item->item.getText().equals(productname));
		return matchfound;
	}

}
