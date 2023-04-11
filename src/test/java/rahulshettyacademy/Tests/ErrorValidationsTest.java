package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOutPage;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public  void loginErrorValidation() throws IOException, InterruptedException {
		//String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
	
		landingPage.loginApplication("vibhuti@gmail.com", "P@ssword");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		}
	
	@Test
	public  void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
	
	//	LandingPage landingPage = launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication("vivek@gmail.com", "Vivek@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
		cartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.cartProductsValidation(productName);
		Assert.assertTrue(match);
	
		}
	

}
