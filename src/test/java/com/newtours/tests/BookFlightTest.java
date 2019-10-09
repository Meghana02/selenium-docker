package com.newtours.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import tests.BaseTest;

import com.newtours.pages.FindFlightPage;
import com.newtours.pages.FlightConfirmationPage;
import com.newtours.pages.FlightDetailsPage;
import com.newtours.pages.RegistrationConfirmationPage;
import com.newtours.pages.RegistrationPage;

public class BookFlightTest extends BaseTest {

	private String noOfPassengers;
	private String expectedPrice;

	@BeforeTest
	@Parameters({"noOfPassengers","expectedPrice"})
	public void setupParameters(String noOfPassengers, String expectedPrice) {
		this.noOfPassengers=noOfPassengers;
		this.expectedPrice=expectedPrice;
	}

	@Test
	public void registrationPage() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();
		registrationPage.enterUserdetails("Sai", "Selenium");
		registrationPage.enterUserCredentials("megha", "Sachit");
		registrationPage.submit();
	}

	@Test(dependsOnMethods = "registrationPage")
	public void registrationConfirmationPage() {
		RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(
				driver);
		registrationConfirmationPage.goToFlightDetailsPage();
	}

	@Test(dependsOnMethods = "registrationConfirmationPage")
	public void flightDetailsPage() {
		FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
//		flightDetailsPage.selectPassengers("2");
		flightDetailsPage.selectPassengers(noOfPassengers);
		flightDetailsPage.goToFindFlightsPage();
	}

	@Test(dependsOnMethods = "flightDetailsPage")
	public void findFlightPage(){
		FindFlightPage findFlightPage= new FindFlightPage(driver);
		findFlightPage.submitFindFlightPage();
		findFlightPage.goToFlightConfirmationPage();
	}
	
	@Test(dependsOnMethods = "findFlightPage")
	public void flightConfirmationPage(){
		FlightConfirmationPage flightConfirmationPage= new FlightConfirmationPage(driver);
		String actualPrice=flightConfirmationPage.getPrice();
		Assert.assertEquals(actualPrice, expectedPrice);
	}
}
