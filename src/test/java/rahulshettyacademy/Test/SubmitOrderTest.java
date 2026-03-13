package rahulshettyacademy.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
//import rahulshettyacademy.pageobjects.LandingPage;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{

		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
		
		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		
		
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage  checkoutPage = cartPage.goToCheckout();
		
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.loginApplication("dheeraj@yopmail.com", "Test@123");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		orderPage.VerifyOrderDisplay(productName);
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	

	@DataProvider
	public Object[][] getData() throws IOException
	{	
	List<HashMap<String,String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
	return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
//	@DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }

//@DataProvider
//public Object[][] getData()
//{	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "dheeraj@yopmail.com");
//	map.put("password", "Test@123");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("productName", "ADIDAS ORIGINAL");
//	
//}

}
