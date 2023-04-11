package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbsractComponents;

public class LandingPage extends AbsractComponents{
	WebDriver driver;

	public LandingPage(WebDriver driver) {
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

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	
	public void goTo() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client/");
	}

	public String getErrorMessage() throws InterruptedException {
		waitForWebElementToApper(errorMessage);
		return errorMessage.getText();
	}


}
