package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rahulshettyacademy.AbstractComponents.AbsractComponents;

public class ConfirmationPage extends AbsractComponents {
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	/*
	 * String confirmMessage =
	 * driver.findElement(By.cssSelector(".hero-primary")).getText();
	 * Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."))
	 * ; driver.quit();
	 */
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;

	public String getConfirmation() {
		return confirmMessage.getText();
	}
	
	

}
