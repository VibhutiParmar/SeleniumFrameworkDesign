package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rahulshettyacademy.AbstractComponents.AbsractComponents;

public class ProductCatalog extends AbsractComponents {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/*
	 * WebElement userEmail = driver.findElement(By.id("userEmail")); WebElement
	 * password = driver.findElement(By.id("userPassword")); WebElement login =
	 * driver.findElement(By.id("login"));
	 */

	// PageFacory

	// List<WebElement> products = driver.findElements(By.className("mb-3"));
	@FindBy(className = "mb-3")
	List<WebElement> products;

	/*
	 * loading...cssSelector-->.ng-tns-c31-1 ng-star- inserted rahulshetty
	 * given->ng-animating
	 * 
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className(
	 * "ng-animating"))); or
	 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
	 * ".ng-tns-c31-1ng-star-inserted"))); or
	 * wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.
	 * cssSelector(".ng-animating"))));
	 */
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	

	

	By productsBy = By.className("mb-3");

	// Add to card button
	// xpath : //div[@class='card-body']//button[@class='btn w-10 rounded']
	// cssSelector : .card-body button:last-child
	By addToCart = By.xpath("//div[@class='card-body']//button[@class='btn w-10 rounded']");
	By toastMessage = By.id("toast-container");

	public List<WebElement> getProductList() {
		waitForElementToApper(productsBy);
		return products;
	}

	// WebElement prod = products.stream()
	// .filter(product ->
	// product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
	// .orElse(null);

	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		return prod;
	}

	// prod.findElement(By.xpath("//div[@class='card-body']//button[@class='btn w-10
	// rounded']")).click();
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToApper(toastMessage);
		waitForElementToDisappear(spinner);

	}

}
