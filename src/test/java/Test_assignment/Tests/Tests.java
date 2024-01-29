package Test_assignment.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test_assignment.Main.CareersPage;
import Test_assignment.Main.HomePage;
import Test_assignment.Main.PositionPage;
import Test_assignment.Main.PositionsPage;
import Test_assignment.Main.QAPage;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;

public class Tests extends BaseTest {
	
	@Test
	public void HomePageIsOpened() 
	{
		setWebPage("https://useinsider.com/");
		HomePage home_page = new HomePage(getDriver());
		AssertJUnit.assertTrue("HomePage was not opened", home_page.isPageDisplayed());
	}
	
	@Test
	public void CareersPageBlocksAreOpened() 
	{
		setWebPage ("https://useinsider.com/");
		HomePage home_page = new HomePage(getDriver());
		AssertJUnit.assertTrue(home_page.isPageDisplayed());
		
		home_page.clickOnCompany();
		CareersPage careers_page = home_page.gotoCareers();
		
		AssertJUnit.assertTrue("CareersPage was not opened", careers_page.isPageDisplayed());
		AssertJUnit.assertTrue("Locations Block was not displayed correctly", careers_page.isLocationsBlockDisplayed());
		AssertJUnit.assertTrue("Teams Block was not displayed correctly", careers_page.isTeamsBlockDisplayed());
		AssertJUnit.assertTrue("LifeInInsider Block was not displayed correctly", careers_page.isLifeInInsiderBlockDisplayed());
	}
	
	@Test
	public void QAPositionsListPresenceWithFiltration() throws InterruptedException
	{
		String location   = "Istanbul, Turkey";
		String department = "Quality Assurance";
		setWebPage ("https://useinsider.com/careers/quality-assurance/");
		
		QAPage qa_page = new QAPage(getDriver());
		PositionsPage qa_job_page = qa_page.goToSeeAllJobPage();
		
		qa_job_page.selectLocation(location);
		qa_job_page.selectDepartment(department);
		Thread.sleep(1000);
		
		AssertJUnit.assertTrue("There are no vocantions with filters " + location + ", " + department, qa_job_page.areFilterredPositionsPresent());
	}
	
	@Test(dataProvider = "getData")
	public void CheckFilteredPositions(HashMap<String, String> input) throws InterruptedException
	{
		String location   = input.get("location");
		String department = input.get("department");
		setWebPage ("https://useinsider.com/careers/quality-assurance/");
		
		QAPage qa_page = new QAPage(getDriver());
		PositionsPage qa_job_page = qa_page.goToSeeAllJobPage();
		
		qa_job_page.selectLocation(location);
		qa_job_page.selectDepartment(department);
		Thread.sleep(2000);
		List<WebElement> all_filtered_positions = qa_job_page.getAllPositions();
		for(WebElement position :all_filtered_positions)
		{
			AssertJUnit.assertEquals(location,   position.findElement(qa_job_page.position_location).getText());
			AssertJUnit.assertEquals(department, position.findElement(qa_job_page.position_department).getText());
		}
	}
	
	@Test
	public void RedirectionToPositionPage()
	{
		setWebPage ("https://useinsider.com/careers/quality-assurance/");
		acceptCookies();
		QAPage qa_page = new QAPage(getDriver());
		PositionsPage qa_job_page = qa_page.goToSeeAllJobPage();
		
		WebElement first_position  = qa_job_page.getAllPositions().get(0);
		String position_title      = first_position.findElement(qa_job_page.position_title).getText();
		String position_location   = first_position.findElement(qa_job_page.position_location).getText();
		String position_department = first_position.findElement(qa_job_page.position_department).getText();
		PositionPage position_page = qa_job_page.goToLeverApplication(first_position);
		
		switchToWindow(2);
		
		AssertJUnit.assertTrue(  "redirected page has incorrect url", getCurrentPageURL().startsWith(position_page.page_url_beginning));
		AssertJUnit.assertEquals("title is incorect",      position_title,      position_page.getTitle());
		AssertJUnit.assertEquals("location is incorect",   position_location,   position_page.getLocation());
		AssertJUnit.assertTrue(  "department is incorect", position_page.getDepartment().contains(position_department));
	}
	
	@DataProvider
	public  Object[][] getData() throws IOException
	{
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\Test_assignment\\Tests\\TestData.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	

}

