package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {

	private WebDriver driver;
	private WebDriverWait wait;
	
	@FindBy (name="firstName")
	private WebElement firstNameTxt;
	
	@FindBy (name="lastName")
	private WebElement lastNameTxt;
	
	@FindBy (name="password")
	private WebElement passwordTxt;
	
	@FindBy (id="email")
	private WebElement usernameTxt;
	
	@FindBy (name="confirmPassword")
	private WebElement confirmPasswordTxt;
	
	@FindBy (name="register")
	private WebElement submitBtn;
	
	//constructor, pass the driver and intialise driver and driverwait
	public RegistrationPage(WebDriver driver){
		this.driver=driver;
		this.wait = new WebDriverWait(driver,30);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo(){
		this.driver.get("http://newtours.demoaut.com/mercuryregister.php");
		//This will not work for java 7, so i installed java 8
		this.wait.until(ExpectedConditions.visibilityOf(this.firstNameTxt));
	}
	
	public void enterUserdetails(String firstName, String lastName){
		this.firstNameTxt.sendKeys(firstName);
		this.lastNameTxt.sendKeys(lastName);
	}
	
	public void enterUserCredentials(String username,String password){
		this.usernameTxt.sendKeys(username);
		this.passwordTxt.sendKeys(password);
		this.confirmPasswordTxt.sendKeys(password);
	}
	
	public void submit(){
		this.submitBtn.click();
	}
}
