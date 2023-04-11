package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.cartPage;

public class AbsractComponents {

	// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	// wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));
	WebDriver driver;

	public AbsractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	public void waitForElementToApper(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToApper(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	public cartPage goToCartPage() {
		cartHeader.click();
		cartPage cartPage = new cartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		}
	
	
}
