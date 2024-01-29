package Test_assignment.Main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class PositionPage extends Base{
	
	public  String page_url_beginning = "https://jobs.lever.co";
	private WebDriver driver;
			
	@FindBy (css = ".posting-headline h2")
	private WebElement title;
	
	@FindBy (css = ".posting-headline div div.location")
	private WebElement location;
	
	@FindBy (css = ".posting-headline div div.department")
	private WebElement department;
		
	public PositionPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public String getTitle()
	{
		return title.getText();
	}
	
	public String getLocation()
	{
		return location.getText();
	}
	
	public String getDepartment()
	{
		return department.getText();
	}
}
	


