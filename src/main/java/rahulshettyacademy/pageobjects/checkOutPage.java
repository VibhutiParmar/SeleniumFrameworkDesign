package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbsractComponents;

public class checkOutPage extends AbsractComponents {

	WebDriver driver;
	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
		
	}
	/*
	 * 	driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
		Thread.sleep(2000);
		List<WebElement> countries = driver.findElements(By.xpath("(//button[@type='button'])[2]"));
		for(WebElement country:countries) {
			if(country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
				}
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
	 */
	@FindBy(css=".form-group input")
	WebElement textBox;
	
	@FindBy(xpath="(//button[@type='button'])[2]")
	List<WebElement> countries;
	
	@FindBy(xpath= "//div[@class='actions']/a")
	WebElement submit;
	
	By ele = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) throws InterruptedException {
		textBox.sendKeys(countryName);
		waitForElementToApper(ele);
		for(WebElement country:countries) {
			if(country.getText().equalsIgnoreCase("India")) {
				country.click();
				break;
				}
		}
		Thread.sleep(2000);
		
		
	}
	public ConfirmationPage placeOrder() {
		submit.click();
		ConfirmationPage confirmationpage = new ConfirmationPage(driver);
		return confirmationpage;
	}
	
	

}
