package stepdefinations;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.AdminPage;
import utilities.ReadConfig;

public class StepDefination extends Base {
	
	//It Hookes concept in BBD cucumber
	//@Before if any method before this annotation then it will execute before every scenarios.
	
	@Before
	public void setUp() throws Exception
	{
		
		System.out.println("Set up method execution");
		
		readConfig= new ReadConfig();
		String browser=readConfig.getBrowser(); //firefox
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			Thread.sleep(3000);
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver= new FirefoxDriver();
			driver.manage().window().maximize();
			Thread.sleep(3000);
			
		}else if(browser.equalsIgnoreCase("IE")) {
			driver= new InternetExplorerDriver();
			driver.manage().window().maximize();
			Thread.sleep(3000);
		}
	
	}

	@Given("User Lanch Chrome Browse")
	public void user_lanch_chrome_browse() {
	    
		// driver=new FirefoxDriver(); // not using this code because we wrote cross browser code and mentioend firefox in config property file.
		
		ad=new AdminPage(driver); //create object of admin page java class
		

	}

	@When("User open url {string}")
	public void user_open_url(String url) {
	    driver.get(url); //launch application
	    
	}

	@When("User enter Email as {string} and password as {string}")
	public void user_enter_email_as_and_password_as(String email, String password) throws Exception {
	  ad.setUsername(email);
	  Thread.sleep(3000);
	  ad.setPassword(password);
	}

	@When("User click on Login button")
	public void user_click_on_login_button() {
	    ad.clickOnLogin();
	}

	@Then("User verify page title should be {string}")
	public void user_verify_page_title_should_be(String title) {
		Assert.assertEquals(driver.getTitle(), title);
	   
	}

	@Then("close browser")
	public void close_browser() {
		driver.close();
	   
	}
	
	@After //@After will execute after every scenarios and below is code of capture screenshot
	public void tearDown(Scenario sc) throws Exception { //Scenario
		System.out.println("Tear down method will execute after every scenrios");

		if(sc.isFailed()==true) {
		String filePath="C:\\Users\\Admin\\eclipse-workspace\\CucumberProject\\captureScreenshot\\failedcreenshot.png";
		//capture the screenshot of failed scenarios
		
		//convert webDriver object into TakeScreenshot
		
		TakesScreenshot scrShot =(TakesScreenshot)driver;
		
		//call getScreenshotAS ()
		
		File scrFile=scrShot.getScreenshotAs(OutputType.FILE);  //Select import file java.io
		
		File destFile=new File(filePath);
		FileUtils.copyFile(scrFile, destFile);
		
		Thread.sleep(3000);
		}
	driver.quit(); //If any associate window open then it will close automatically.
}
}
	
//For screenshot code make sure refresh the project after run the script. 

