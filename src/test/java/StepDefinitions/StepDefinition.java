package StepDefinitions;


import static org.testng.Assert.assertTrue;
import org.junit.jupiter.api.Assertions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.concurrent.TimeUnit;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import Helper.Constants;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.annotations.Listeners;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;

import Helper.BrowserFactory;
import Helper.ReadConfig;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinition {
	WebDriver driver;
	String productName;
	String cartItemName;
	String OrderID;
	String Orderstatus;
	String orderEmail;
	String OrderTelephone;
	String OrderFax;
	String OrderShippingMethod;
	String OrderPaymentMethod;
	String OrdershippingAddress;
	String OrderPaymentAddress;
	String OrderhighHeel;
	
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
	    driver=BrowserFactory.startBrowser(browserName);
		
	   
	}
		
	@Given("Launch the requested browser with the url")
	public void launch_the_requested_browser_with_the_url() {
		
		driver.get("https://automationteststore.com/");
	}

	@When("user clicks on Login or Register link")
	public void user_clicks_on_login_or_register_link() {
		driver.findElement(By.xpath(Constants.login)).click();
	   
	}

	@When("user click on Continue button")
	public void user_click_on_continue_button_to_fill_the_required_details() {
		driver.findElement(By.xpath(Constants.continueButton)).click();
	    
	}
	
	@When("user fill the required details")
	public void user_fill_the_required_details_on_create_account_page(DataTable dataTable) {
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
	    driver.findElement(By.xpath(Constants.loginId)).sendKeys(ReadConfig.prop.getProperty("loginname"));
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
		
			
		}
		else
		{
			
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
	    	
	    }
	    else
		{
	    	
			assertTrue(false);
		}
	    driver.findElement(By.xpath(Constants.checkout)).click();
	    String productNameOnPaymentsPage=driver.findElement(By.xpath(Constants.highHeel)).getText();
	    if(productName.equals(productNameOnPaymentsPage))
	    {
	    	
	    }
	    else
		{
	    	
			assertTrue(false);
		}
	    if(driver.findElement(By.xpath("//button[@title='Confirm Order']")).isDisplayed())
	    {
	    
	    }
	    else
		{
	    	
			assertTrue(false);
		}
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,800)", "");

	    driver.findElement(By.xpath(Constants.ConfirmOrder)).click();
	}

	@When ("user checks the order details")
	public void user_checks_the_order_details() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Account=driver.findElement(By.xpath(Constants.Account));
		Actions action = new Actions(driver);
		action.moveToElement(Account).perform();
		WebElement CheckYourOrder=driver.findElement(By.xpath(Constants.CheckYourOrder));
		action.moveToElement(CheckYourOrder);		
		action.click().build().perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath(Constants.OrderHistoryView)).click();
		OrderID=driver.findElement(By.xpath(Constants.OrderID)).getText();
		Orderstatus=driver.findElement(By.xpath(Constants.Orderstatus)).getText();
		orderEmail=driver.findElement(By.xpath(Constants.orderEmail)).getText();
		OrderTelephone=driver.findElement(By.xpath(Constants.OrderTelephone)).getText();
		OrderFax=driver.findElement(By.xpath(Constants.OrderFax)).getText();
		OrderShippingMethod=driver.findElement(By.xpath(Constants.OrderShippingMethod)).getText();
		OrderPaymentMethod=driver.findElement(By.xpath(Constants.OrderPaymentMethod)).getText();
		OrdershippingAddress=driver.findElement(By.xpath(Constants.shippingAddress)).getText();
		OrderPaymentAddress=driver.findElement(By.xpath(Constants.PaymentAddress)).getText();
		OrderhighHeel=driver.findElement(By.xpath(Constants.OrderhighHeel)).getText();
	}
	

	
	@When ("user clicks on Save to PDF option")
	public void user_clicks_on_save_to_pdf() throws AWTException, InterruptedException{
	driver.findElement(By.xpath(Constants.PrintOption)).click();
	Robot robot=new Robot();
	Thread.sleep(6000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
		Thread.sleep(2000);
	robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        
			robot.keyPress(KeyEvent.VK_DOWN);
	        robot.keyRelease(KeyEvent.VK_DOWN);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
	        for(int i=0;i<4;i++){
			Thread.sleep(5000);
		robot.keyPress(KeyEvent.VK_TAB);
	    robot.keyRelease(KeyEvent.VK_TAB);
	}
	robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        
	        Thread.sleep(2000);
	    	robot.keyPress(KeyEvent.VK_TAB);
	        robot.keyRelease(KeyEvent.VK_TAB);
	    	Thread.sleep(2000);
	    	robot.keyPress(KeyEvent.VK_TAB);
	        robot.keyRelease(KeyEvent.VK_TAB);
	    	Thread.sleep(2000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	            robot.keyRelease(KeyEvent.VK_ENTER);
	            
	            Thread.sleep(2000);
	        	robot.keyPress(KeyEvent.VK_TAB);
	            robot.keyRelease(KeyEvent.VK_TAB);
	            
	            Thread.sleep(2000);
	            robot.keyPress(KeyEvent.VK_ENTER);
	                    robot.keyRelease(KeyEvent.VK_ENTER);
	                    Thread.sleep(2000);
	}
	
	
	
	@Then ("verify the order details in pdf")
	public void verify_the_order_details_in_pdf() throws IOException {
		PDDocument document = PDDocument.load(new File("C:\\Users\\GORAJU\\Downloads\\Order Details.pdf"));
        PDFTextStripper textStripper = new PDFTextStripper();
        String pdfText = textStripper.getText(document);

        Assertions.assertTrue(pdfText.contains(OrderID));
        
        Assertions.assertTrue(pdfText.contains(OrdershippingAddress));
        Assertions.assertTrue(pdfText.contains(OrderPaymentAddress));
        String itemName=productName.split("stiletto")[0];
        Assertions.assertTrue(pdfText.contains(itemName));

        document.close();
		
	}
	
	@After(order = 1)
	public void takeScraenshotOnFailure(Scenario scenario) {

		if (scenario.isFailed()) {

			TakesScreenshot ts = (TakesScreenshot) driver;

			byte[] src = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(src, "image/png", "screenshot");
		}

	}

	@After(order = 0)
	public void tearDown() {
		driver.close();

	}
}

