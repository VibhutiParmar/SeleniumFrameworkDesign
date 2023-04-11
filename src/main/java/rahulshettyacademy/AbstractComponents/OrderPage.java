package rahulshettyacademy.AbstractComponents;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.pageobjects.checkOutPage;

public class OrderPage extends AbsractComponents{

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriver driver;


	// List<WebElement> cartProducts =
	// driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
	@FindBy(xpath = "//tr//td[2]")
	List<WebElement> productNames;

	// driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkOutBtn;

	public boolean orderProductsValidation(String productName) {
		Boolean match = productNames.stream().anyMatch(cProduct -> cProduct.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	public checkOutPage goToCheckOut() {
		checkOutBtn.click();
		checkOutPage checkoutPage = new checkOutPage(driver);
		return checkoutPage;
	}



}
