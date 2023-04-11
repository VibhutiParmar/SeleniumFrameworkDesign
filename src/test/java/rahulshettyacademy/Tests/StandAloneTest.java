package rahulshettyacademy.Tests;

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

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage webdriver = new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("userEmail")).sendKeys("vibhuti@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("P@ssword1");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mb-3")));

		List<WebElement> products = driver.findElements(By.className("mb-3"));

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);

		// Add to card button
		// xpath : //div[@class='card-body']//button[@class='btn w-10 rounded']
		// cssSelector : .card-body button:last-child

		prod.findElement(By.xpath("//div[@class='card-body']//button[@class='btn w-10 rounded']")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));

		// loading...cssSelector-->.ng-tns-c31-1 ng-star-inserted
		/// rahulshetty given -> ng-animating

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		// or
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-tns-c31-1
		// ng-star-inserted")));
		// or
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		// to get list of product in cart
		// xpath for product name in cart - //div[@class='cartSection']/h3
		List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));

		Boolean match = cartProducts.stream().anyMatch(cProduct -> cProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("ind");
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
		
		/*
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		driver.findElement(By.xpath("//div[@class='actions']/a")).click();
		*/
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
		}

}
