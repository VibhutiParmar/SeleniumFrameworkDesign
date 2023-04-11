package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbsractComponents;

public class cartPage extends AbsractComponents {

	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// List<WebElement> cartProducts =
	// driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	@FindBy(xpath = "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;

	// driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;

	public Boolean cartProductsValidation(String productName) {
		Boolean match = cartProducts.stream().anyMatch(cProduct -> cProduct.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	public checkOutPage goToCheckOut() {
		checkOutBtn.click();
		checkOutPage checkoutPage = new checkOutPage(driver);
		return checkoutPage;
	}
	
}
