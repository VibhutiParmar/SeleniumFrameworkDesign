package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.AbstractComponents.OrderPage;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOutPage;

public class SubmirOrderTest extends BaseTest{
	
	@Test(dataProvider="getData", groups="purchaseOrder")
	public  void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {
		
		// TODO Auto-generated method stub
	
		ProductCatalog productCatalog = landingPage.loginApplication(map.get("email"), map.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(map.get("productName"));
		cartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.cartProductsValidation(map.get("productName"));
		Assert.assertTrue(match);
		checkOutPage checkoutPage =cartPage.goToCheckOut();
		checkoutPage.selectCountry("ind");
		ConfirmationPage confirmationpage = checkoutPage.placeOrder();
		String confirmationText = confirmationpage.getConfirmation();
		Assert.assertTrue(confirmationText.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		}
	
	//To verify Order is displaying in the order page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("vibhuti@gmail.com", "P@ssword1");
		OrderPage ordersPage = productCatalog.goToOrderPage();
		Assert.assertTrue(ordersPage.orderProductsValidation(productName));
		
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("uder.dir")+"\\src\\test\\java\\rhulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	/*
	 * 	@DataProvider
	public Object[][] getData() throws IOException {
		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "vibhuti@gmail.com");
//		map.put("password", "P@ssword1");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "vivek@gmail.com");
//		map1.put("password", "Vivek@123");
//		map1.put("productName", "ADIDAS ORIGINAL");
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("uder.dir")+"\\src\\test\\java\\rhulshettyacademy\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	 */
	

}
