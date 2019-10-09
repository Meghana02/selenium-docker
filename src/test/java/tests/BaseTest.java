package tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
protected WebDriver driver;
	
	@BeforeTest
	public void setupDriver(ITestContext ctx) throws MalformedURLException{
//		System.setProperty("webdriver.chrome.driver", "C:\\Meghana\\ChromeDriver77\\chromedriver.exe");
//		this.driver = new ChromeDriver();
		
//		BROWSER -> chrome/ firefox (if not specified, chrome will be default)
//		HUB_HOST -> localhost/10.0.1.33/hostname (host can be anything if specified, if not localhost will be default)
		
		String host = "localhost";
//		//DesiredCapabilities dc = DesiredCapabilities.chrome();
		DesiredCapabilities dc;
		
		if(System.getProperty("BROWSER")!= null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
			dc=DesiredCapabilities.firefox();
		}else{
			dc=DesiredCapabilities.chrome();  //added this else part to make sure ff launches correctly if not default chrome
		}
		
		if(System.getProperty("HUB_HOST")!= null){
			host=System.getProperty("HUB_HOST");
		}
		
		String testname=ctx.getCurrentXmlTest().getName();
		
		String completeUrl="http://" + host+ ":4444/wd/hub";
		dc.setCapability("name", testname);
		this.driver= new RemoteWebDriver(new URL(completeUrl), dc);
	}	
	
	@AfterTest
	public void quitBrowser(){
		this.driver.quit();
	}
}
