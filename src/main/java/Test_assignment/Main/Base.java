package Test_assignment.Main;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {

	protected ChromeDriver  chrome_driver;
	protected WebDriverWait wait;
	protected static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
		
	protected WebDriver getDriver() 
	{
		return driver.get();
	}
	
	protected void setWebPage(String page_adress)
	{
		getDriver().get(page_adress);
	}
	
	protected WebElement getButtonByName(List<WebElement> buttons, String button_name)
	{
		for(WebElement button:buttons)
		{
			if(button.getText().equals(button_name))
				return button;
		}
		return null;
	}
	
	protected void switchToWindow(int numberWindow) 
	{
		wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
		wait.until(ExpectedConditions.numberOfWindowsToBe(numberWindow));
        String handle = getDriver().getWindowHandles().toArray()[numberWindow-1]
                        .toString();
        getDriver().switchTo().window(handle);
    }
	
	protected String getCurrentPageURL()
	{
		return getDriver().getCurrentUrl();
	}
	
	protected void acceptCookies()
	{
		By accept_cookies_button = By.id("wt-cli-accept-all-btn");
		if(doesElementExist(accept_cookies_button));
		{
			getDriver().findElement(accept_cookies_button).click(); 
		}
	}
	
	protected boolean doesElementExist(By locator)
	{
		List<WebElement> all_results = getDriver().findElements(locator);
		return !all_results.isEmpty();
	}
	
	protected List<HashMap<String, String>> getJsonData (String jsonFilePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference <List<HashMap<String, String>>>() {
		});
		return data;
	}
	
}
