package Test_assignment.Main;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class HomePage extends Base{
	
	private WebDriver driver;
	
	@FindBy (className = "home_cta_container")
	private List<WebElement> home_container_buttons;
	
	@FindBy (className = "nav-item")
	private List<WebElement> nav_buttons;
	
	@FindBy (css = "div[class = 'new-menu-dropdown-layout-6-mid-container'] a")
	private List<WebElement> dropdown_mid_container_buttons;
	
	
	
	public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public boolean isPageDisplayed()
	{
	   return home_container_buttons.size() > 0;
	}
	
	public void clickOnCompany()
	{
		getButtonByName(nav_buttons, "Company").click();
	}
	
	public CareersPage gotoCareers()
	{
		getButtonByName(dropdown_mid_container_buttons, "Careers").click();
		return new CareersPage(driver);
	}
}

