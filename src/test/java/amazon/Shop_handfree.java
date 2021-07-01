package amazon;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import junit.framework.Assert;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Shop_handfree {
	WebDriver driver;
	ExtentReports report;
	ExtentTest loger;

	@Test(priority = 1)
	public void logIn() throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='nav-link-accountList']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ap_email")).sendKeys("8460013190");
		Thread.sleep(1000);
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.name("password")).sendKeys("jiyana7316");
		driver.findElement(By.id("signInSubmit")).click();
		Assert.assertTrue(driver.findElement(By.name("field-keywords")).isDisplayed());

	}

	@Test(priority = 2)
	public void search() {
		WebElement search = driver.findElement(By.name("field-keywords"));
		Actions builder = new Actions(driver);
		Action event = builder.click(search).sendKeys(search, "handsfree").build();
		event.perform();
		driver.findElement(By.id("nav-search-submit-button")).click();

	}

	@Test(priority = 3)
	public void add_to_cart() throws InterruptedException {
		Thread.sleep(2000);

		driver.findElement(By
				.xpath("//*[@id='search']/div[1]/div/div[1]/div/span[3]/div[2]/div[3]/div/span/div/div/div/div/div[2]/div[1]/div/div/span/a/div/img"))
				.click();
		Thread.sleep(3000);
		String mainWindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		System.out.println("total open windows are " + s1.size());
		Iterator<String> i1 = s1.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				Thread.sleep(2000);
				driver.findElement(By.id("a-autoid-0-announce")).click();
				driver.findElement(By.xpath("//*[@id='nav-cart-count']")).click();
				driver.findElement(By.name("proceedToRetailCheckout")).click();
				// driver.findElement(By.name("password")).sendKeys("jiyana7316");
				// driver.findElement(By.id("signInSubmit")).click();
				driver.findElement(By.xpath("//*[@id='address-book-entry-0']/div[2]/span/a")).click();
				driver.findElement(By.xpath("//*[@id='shippingOptionFormId']/div[3]/div/div/span[1]/span/input"))
						.click();
				
				driver.findElement(By.xpath("//*[@id='pp-cOrSzK-136']/div/div/div/div/div[2]/div[1]/div")).click();
				driver.findElement(By.name("ppw-widgetEvent:SetPaymentPlanSelectContinueEvent")).click();

			}
		}

	}

	@BeforeTest
	public void beforeTest() {
		report = new ExtentReports(
				"C:\\Users\\Soni\\workspace\\Amazon_shopping\\OutputReport\\AmazonExecutionReport.html", true);
		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Soni\\workspace\\Amazon_shopping" + "//Drivers//chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
	}

	@AfterTest
	public void afterTest() {
		// driver.close();
	}
}
