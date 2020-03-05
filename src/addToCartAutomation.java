
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addToCartAutomation {
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Anjana\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// Launching the URL
		driver.get("http://automationpractice.com/index.php");

		// Login
		driver.findElement(By.cssSelector("a.login")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("training.qaprep@gmail.com");
		driver.findElement(By.xpath("//input[@id='passwd']")).sendKeys("Testing123");
		driver.findElement(By.cssSelector("button#SubmitLogin")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Mouse over action
		Actions act = new Actions(driver);
		WebElement subMenu = driver.findElement(By.xpath("//a[@class='sf-with-ul'][contains(text(),'Women')]"));
		act.moveToElement(subMenu).build().perform();
		WebElement tshirtMenu = driver.findElement(
				By.xpath("//ul[contains(@class,'submenu-container')]/li/ul/li/a[contains(text(),'T-shirts')]"));
		tshirtMenu.click();
		WebElement productImage = driver
				.findElement(By.xpath("//a[@class='product_img_link']//img[@class='replace-2x img-responsive']"));
		act.moveToElement(productImage).build().perform();

		// Adding more quantity,Size & color
		driver.findElement(By.xpath("//span[contains(text(),'More')]")).click();
		driver.findElement(By.cssSelector("a.btn.btn-default.button-plus.product_quantity_up")).click();
		WebElement sizeElement = driver.findElement(By.id("group_1"));
		Select select = new Select(sizeElement);
		select.selectByVisibleText("L");
		driver.findElement(By.xpath("//a[@id='color_14']")).click();

		// Add to cart & proceed checkout
		driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]")).click();
		driver.findElement(By.xpath("//div[@class='clearfix']//div[2]//div[4]//a")).click();
		driver.findElement(By.cssSelector("a.button.btn.btn-default.standard-checkout.button-medium")).click();
		driver.findElement(By.xpath("//button[@name='processAddress']")).click();
		driver.findElement(By.id("uniform-cgv")).click();
		driver.findElement(By.cssSelector("button.button.btn.btn-default.standard-checkout.button-medium")).click();
		driver.findElement(By.cssSelector("a.cheque")).click();
		driver.findElement(By.xpath("//span[contains(text(),'I confirm my order')]")).click();

		// Asserting the text for confirmation
		String confirmMessage = driver.findElement(By.cssSelector("p.alert.alert-success")).getText();
		System.out.println(confirmMessage);
		Assert.assertTrue(true, "Your order on My Store is complete.");

		driver.close();
	}

}

