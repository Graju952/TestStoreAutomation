package StepDefinitions;


import static org.testng.Assert.assertTrue;

import java.io.IOException;

import Helper.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import Helper.BrowserFactory;
import Helper.ReadConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	WebDriver driver;
	String productName;
	String cartItemName;
	@Before
	public void browserLaunch() throws IOException
	{
   
		BrowserFactory bf = new BrowserFactory();
		try {
			ReadConfig.initializePropertyFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browserName=ReadConfig.prop.getProperty("browserName");
	    driver=bf.startBrowser(browserName);
		
	   
	}
		
	@Given("Launch the requested browser with the url")
	public void launch_the_requested_browser_with_the_url() {
		
		driver.get("https://automationteststore.com/");
	}

	@When("user clicks on Login or Register link")
	public void user_clicks_on_login_or_register_link() {
		driver.findElement(By.xpath(Constants.login)).click();
	   
	}

	@When("user click on Continue button to fill the required details")
	public void user_click_on_continue_button_to_fill_the_required_details() {
		driver.findElement(By.xpath(Constants.continueButton)).click();
	    
	}

	@When("user fill the required details on create account page")
	public void user_fill_the_required_details_on_create_account_page() {
	    driver.findElement(By.name(Constants.firstName)).sendKeys(ReadConfig.prop.getProperty("firstname"));
	    driver.findElement(By.id(Constants.lastName)).sendKeys(ReadConfig.prop.getProperty("lastname"));
	    driver.findElement(By.name(Constants.email)).sendKeys(ReadConfig.prop.getProperty("email"));
	    driver.findElement(By.name(Constants.telephone)).sendKeys(ReadConfig.prop.getProperty("telephone"));
	    driver.findElement(By.name((Constants.fax))).sendKeys(ReadConfig.prop.getProperty(("fax")));
	    driver.findElement(By.xpath(Constants.company)).sendKeys(ReadConfig.prop.getProperty("company"));
	    driver.findElement(By.name(Constants.address1)).sendKeys(ReadConfig.prop.getProperty("address1"));
	    driver.findElement(By.xpath(Constants.city)).sendKeys(ReadConfig.prop.getProperty("city"));
	    Select select = new Select(driver.findElement(By.xpath(Constants.zoneId)));
	    select.selectByVisibleText(Constants.cardiff);
	    driver.findElement(By.id(Constants.postcode)).sendKeys(ReadConfig.prop.getProperty("zipcode"));
	    Select select1 = new Select(driver.findElement(By.xpath(Constants.countryId)));
	    select1.selectByVisibleText(Constants.unitedKingdom);
	    driver.findElement(By.name(Constants.loginName)).sendKeys(ReadConfig.prop.getProperty("loginname"));
	    driver.findElement(By.xpath(Constants.loginPassword)).sendKeys(ReadConfig.prop.getProperty("loginpassword"));
	    driver.findElement(By.xpath(Constants.passwordConfirm)).sendKeys(ReadConfig.prop.getProperty("passwordconfirm"));
	    driver.findElement(By.xpath(Constants.newsLetter)).click();
	    driver.findElement(By.xpath(Constants.accountAgree)).click();
	    
	}
	
	@When("user clicks on Continue button to register")
	public void user_clicks_on_continue_button_to_register() {
		driver.findElement(By.xpath(Constants.continueButton)).click();
	}
	
	
	@Then("verify whether the correct user name displayed on landing page")
	public void verify_whether_the_correct_user_name_displayed_on_landing_page() {
		String expectedUserName=ReadConfig.prop.getProperty("firstname");
		String actualUserName=driver.findElement(By.xpath(Constants.menuText)).getText();
		String arr[]=actualUserName.split(" ");
		int i=arr.length;
		String actualName=arr[i-1];
		if(actualName.equals(expectedUserName))
		{
			System.out.println("The username displayed on landing page is correct");
			
		}
		else
		{
			System.out.println("The username dispayed on the loginpage is incorrect");
			assertTrue(false);
		}
	
		driver.findElement(By.id(Constants.filter)).sendKeys(ReadConfig.prop.getProperty("searchitem"));
		driver.findElement(By.xpath(Constants.search)).click();
	}
	
	@Then("user add the product to the cart")
	public void user_add_the_product_to_the_cart() {
		productName=driver.findElement(By.xpath(Constants.addProduct)).getText();
		driver.findElement(By.xpath(Constants.cart)).click();
	    
	}

	@When("user moves to the checkout page for payments")
	public void user_moves_to_the_checkout_page_for_payments() {
		cartItemName=driver.findElement(By.xpath(Constants.highHeel)).getText();
	}

	@Then("validate the product on payments page showing correctly")
	public void validate_the_product_on_payments_page_showing_correctly() {
	    if(productName.equals(cartItemName))
	    {
	    	System.out.println("The selected product is displayed on the checkout page");
	    }
	    else
		{
	    	System.out.println("The selected product is not displayed on the checkout page");
			assertTrue(false);
		}
	    driver.findElement(By.xpath(Constants.checkout)).click();
	    String productNameOnPaymentsPage=driver.findElement(By.xpath(Constants.highHeel)).getText();
	    if(productName.equals(productNameOnPaymentsPage))
	    {
	    	System.out.println("The selected product is displayed on the payments page");
	    }
	    else
		{
	    	System.out.println("The selected product is not displayed on the payments page");
			assertTrue(false);
		}
	    if(driver.findElement(By.xpath("//button[@title='Confirm Order']")).isDisplayed())
	    {
	    	System.out.println("The final confirmation for ordering the product is avaible");
	    }
	    else
		{
	    	System.out.println("The final confirmation for ordering the product is not avaible");
			assertTrue(false);
		}
	}


	@After
	public void tearDown()
	{
		driver.quit();
	}
	@After(order = 1)
	public void takeScraenshotOnFailure(Scenario scenario) {

		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;

			byte[] src = ts.getScreenshotAs(QutputType.BYTES);
			scenario.attach(src, "image/png", "screenshot");
		}

	}

	@After(order = 0)
	public void tearDown() {
		driver.close();

	}
}

