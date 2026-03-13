package rahulshettyacademy.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;



import rahulshettyacademy.TestComponents.Retry;

import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;


public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {

	
		landingPage.loginApplication("anshika@gmail.com", "Iamki000");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
		
		
		@Test
		public void submitOrder() throws IOException, InterruptedException
		{

			String productName = "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingPage.loginApplication("dheeraj@yopmail.com", "Test@123");
			
			
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
			CartPage cartPage = productCatalogue.goToCartPage();
			
			
			Boolean match = cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			
			

		}
}
