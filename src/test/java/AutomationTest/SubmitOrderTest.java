package AutomationTest;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTest.TestComponents.BaseTest;
import AutomationTest.pageobjects.CartPage;
import AutomationTest.pageobjects.CheckoutPage;
import AutomationTest.pageobjects.ConfirmationPage;
import AutomationTest.pageobjects.LandingPage;
import AutomationTest.pageobjects.OrderPage;
import AutomationTest.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName ="ZARA COAT 3";
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{	
		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("hieupham@gmail.com","Phamhieu@215");
		
		List<WebElement>products = productCatalogue.getProductsList();
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		
		
		CheckoutPage checkoutPage= cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		 ConfirmationPage  confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	/*@DataProvider
	public Object[][] getData() throws IOException
	{

		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}*/
	

}
