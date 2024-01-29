package Test_assignment.Main;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class QAPage extends Base{
	
	private WebDriver driver;
	
	@FindBy (css = "div[class='button-group d-flex flex-row'] a")
	private WebElement see_all_jobs_button;
	
	public QAPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public PositionsPage goToSeeAllJobPage()
	{
		see_all_jobs_button.click();
		return new PositionsPage (driver);
	}
	
}
	


