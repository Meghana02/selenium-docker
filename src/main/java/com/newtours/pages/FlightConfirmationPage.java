package com.newtours.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightConfirmationPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy(xpath="//font[contains(text(), 'Confirmation')]")
	private WebElement flightConfirmationHeader;
	
//There are 2 texts with USD. To get the second one, we take it as WebElements
	@FindBy(xpath="//font[contains(text(), 'USD')]")
	private List<WebElement> prices;
	
	@FindBy(linkText="SIGN-OFF")
	private WebElement signOffLink;
	
		
	public FlightConfirmationPage(WebDriver driver){
		this.driver=driver;
		this.wait=new WebDriverWait(driver, 30);
		
		//since we use@FindBY we need PageFactory to intialise it
		PageFactory.initElements(driver, this);
	}
	
	public String getPrice(){
		//we need to ensure that element is loaded before clicking.
		this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationHeader));
		System.out.println(this.flightConfirmationHeader.getText());
		System.out.println(this.prices.get(1).getText()); //since we need 2nd 'USD' text
		String price=this.prices.get(1).getText();
		this.signOffLink.click();
		return price;
	}
	
}
