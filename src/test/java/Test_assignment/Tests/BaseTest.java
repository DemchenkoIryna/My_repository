package Test_assignment.Tests;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Test_assignment.Main.Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest extends Base{

	private String chrom_driver_path = "C:\\Program Files\\chromedriver-win64\\chromedriver.exe";
    
	@BeforeMethod
	protected void StartBrowser()
	{
		System.setProperty("webdriver.chrome.driver", chrom_driver_path);
		driver.set(new ChromeDriver());
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		getDriver().manage().window().maximize();
	}
	
	@AfterMethod
	protected void AfterTestEndActions()
	{
		getDriver().quit();
	}
}
