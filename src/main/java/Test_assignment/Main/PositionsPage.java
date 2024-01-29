package Test_assignment.Main;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PositionsPage extends Base{
	
	public By position_title      = By.className("position-title");
	public By position_location   = By.className("position-location");
	public By position_department = By.className("position-department");
	private WebDriver driver;
			
	@FindBy (css = "button[class='btn d-flex']")
	private WebElement filter_button;
	
	@FindBy (id = "select2-filter-by-location-container")
	private WebElement location_drop_down;
	
	@FindBy (css = "li[id *= 'select2-filter-by-location-result']")
	private List<WebElement> location_options;
	
	@FindBy (id = "select2-filter-by-department-container")
	private WebElement department_drop_down;
		
	@FindBy (css = "li[id *= 'select2-filter-by-department-result']")
	private List<WebElement> department_options;
	
	@FindBy (className = "position-list-item")
	private List<WebElement> position_items;
	
	@FindBy (className = "position-department")
	private List<WebElement> position_departments;
	
	@FindBy (className = "position-location")
	private List<WebElement> position_locations;
	
	
	public PositionsPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public void openFilter()
	{
		filter_button.click();
	}
	
	public void selectLocation(String location)
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(department_drop_down, "Quality Assurance")); 
		location_drop_down.click();
		getButtonByName(location_options, location).click();
	}
	
	public void selectDepartment(String department)
	{
		department_drop_down.click();
		getButtonByName(department_options, department).click();
	}
	
	public boolean areFilterredPositionsPresent()
	{
		return position_items.size() > 0;
	}
	
	public List<WebElement> getAllPositions()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(position_items)));
		return position_items;
	}
	
	public List<WebElement> getAllPositionsDepartments()
	{
		return position_departments;
	}
	
	public List<WebElement> getAllPositionsLocations()
	{
		return position_locations;
	}
	
	public PositionPage goToLeverApplication(WebElement position)
	{
		position.findElement(By.className("btn-navy")).click();
		return new PositionPage(driver);
	}
}
	


