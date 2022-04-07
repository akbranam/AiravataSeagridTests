package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckStatistics extends SeagridTest{
	
	WebDriver driver;

	@BeforeEach
	public void setUp() throws Exception {
		driver = setDriver();
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws Exception {
		String username = readConfigFile("seagrid_username");
		
		//go to the django portal
		driver.get(readConfigFile("start_url"));
				
		//login
		login(driver);
		addWait(200);
		
		//go to settings
		attemptClick(driver.findElement(By.id("dropdownMenuButton")), driver);
	    attemptClick(driver.findElement(By.id("appDropdownMenuButton")), driver);
	    attemptClick(driver.findElement(By.cssSelector(".fa-cog")), driver);
	    
	    //go to Experiment Statistics
	    attemptClick(By.linkText("Experiment Statistics"), driver);
	    
	    //add username filter
	    attemptClick(By.xpath("//button[contains(text(), 'Add Filters')]"), driver);
	    attemptClick(By.linkText("Username"), driver);
	    driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys(username);
	    
	    //get statistics from last 24 hrs
	    attemptClick(By.xpath("//button[contains(text(), 'Get Statistics')]"), driver);
	    
	    //count complete
	    /*
	    int index = -1;
	    for (int i =0; i<driver.findElements(By.xpath("//td[contains(text(), '')]")).size(); i++ ) {
	    	if (driver.findElements(By.xpath("//td[contains(text(), '')]")).get(i).getText().equals(edit_grp)) {
	    		index = i;
	    		break;
	    	}
	    	
	    }
	    if (index == -1) {
	    	throw new Exception("No completed experiments");
	    */
	    //exit out
	    attemptClick(By.className("fas fa-times"), driver);
	    
	    //count failed
	    
	    //count still running
	    
	}

		
}
