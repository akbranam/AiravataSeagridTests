package utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateProject extends SeagridTest {
	WebDriver driver;
	String name;
	
	@BeforeEach
	public void setUp() throws Exception {
		driver = setDriver();
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws Exception{
		String share_email = readConfigFile("share_email");
		WebElement element;
		
		//go to the django portal
		driver.get(readConfigFile("start_url"));
				
		//login
		login(driver);
		addWait(200);
				
		//go to projects
		attemptClick(By.cssSelector(".fa-box"), driver);
		
		//add new project
		attemptClick(By.cssSelector(".btn"), driver);
				
		//String name = readConfigFile("new_project")+"_"+currentDateAsString()+"_"+currentTimeAsString();//project name with date and time added on the end
		if (name == null) {
			genProjectName();
		}
		
		//name project
		driver.findElement(By.id("project-name")).sendKeys(name);
			    
		//add project description
		driver.findElement(By.id("project-description")).sendKeys("This project was created using an automated test");
				
		//click ok
		attemptClick(By.xpath("//button[contains(text(), 'OK')]"), driver);
			    
		//click on edit
		attemptClick(By.xpath("//*[contains(text(), 'Edit')]"), driver);//edits the newest project
		
		//click share button
		attemptClick(driver.findElement(By.xpath("//button[contains(text(), 'Share')]")), driver);
		
		element =  driver.findElement(By.xpath("//input[@placeholder='Type to get suggestions...']"));
	    element.sendKeys(share_email);
	    attemptClick(driver.findElement(By.xpath("//*[contains(text(), '"+share_email+"')]")), driver);
	    
	    //click save (there are 3 save buttons present on the page; two are disabled)
	    element = driver.findElements(By.xpath("//button[contains(text(), 'Save')]")).get(1);
	    attemptClick(element, driver);
			    
		//save project
		attemptClick(By.xpath("//button[contains(text(), 'Save')]"), driver);
			    
		//make sure project is saved
		if (!doesElementExist(driver, By.xpath("//h1[contains(text(), 'Browse Projects')]"))){
					throw new Exception("Project didn't save");
				}
		
	}
	
	public String genProjectName() throws Exception {
		String temp = readConfigFile("new_project")+"_"+currentDateAsString()+"_"+currentTimeAsString();
		this.name = temp;
		return name;
	}

}
