package Test_assignment.Main;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class CareersPage extends Base {
	
	private WebDriver driver;
	
	@FindBy (id = "page-head")
	private WebElement page_head;
	
	@FindBy (id = "career-our-location")
	private WebElement our_location;
	
	@FindBy (className = "glide__slide")
	private List<WebElement> locations_info;
	
	@FindBy (id = "career-find-our-calling")
	private WebElement teams;
	
	@FindBy (className = "job-image")
	private List<WebElement> job_images;
	
	@FindBy (className = "loadmore")
	private WebElement all_teams_button;
	
	@FindBy (className = "elementor-element-a8e7b90")
	private WebElement life_at_insider;
	
	@FindBy (className = "elementor-carousel-image")
	private List<WebElement> life_section_images;
	
		
	public CareersPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public boolean isPageDisplayed()
	{
	   return page_head.isDisplayed();
	}
	
	public boolean isLocationsBlockDisplayed()
	{
	   return our_location.isDisplayed() && locations_info.size() > 0;
	}
	
	public boolean isTeamsBlockDisplayed()
	{
	   return teams.isDisplayed() && job_images.size() > 0 && all_teams_button.isDisplayed();
	}
	
	public boolean isLifeInInsiderBlockDisplayed()
	{
	   return life_at_insider.isDisplayed() && life_section_images.size() > 0;
	}

}

