package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalog;
import rahulshettyacademy.pageobjects.cartPage;
import rahulshettyacademy.pageobjects.checkOutPage;

public class StepDefinationImplt extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^Logged in with Username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String userName, String password) {
		productCatalog = landingPage.loginApplication(userName, password);
	}

	@When("^I add product (.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_order(String productName) throws InterruptedException {
		cartPage cartPage = productCatalog.goToCartPage();
		Boolean match = cartPage.cartProductsValidation(productName);
		Assert.assertTrue(match);
		checkOutPage checkoutPage = cartPage.goToCheckOut();
		checkoutPage.selectCountry("ind");
		confirmationpage = checkoutPage.placeOrder();
	}

	@Then("{string} message is displayed on the ConfirmationPage")
	public void message_is_displayed_on_the_confirmationPage(String message) {
		String confirmationText = confirmationpage.getConfirmation();
		Assert.assertTrue(confirmationText.equalsIgnoreCase(message));
		driver.close();
	}

	@Then("{string} message displayed")
	public void error_message_displayed(String message) throws InterruptedException {
		Assert.assertEquals(message, landingPage.getErrorMessage());
	}
}
