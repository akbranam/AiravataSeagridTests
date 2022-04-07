package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExperimentRunner;
import utils.PropertiesLoader;

public class AceMD extends ExperimentRunner{
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
	public void test() throws Exception{
		// AceMD Expanse
		
		runExperiment(driver,
				By.xpath("//h2[contains(text(), 'AceMD')]"),
				"AceMD Expanse",   
				"Expanse",  
				"gpu",
				PropertiesLoader.ACEMD_DIR,
				PropertiesLoader.ACEMD_INPUT1,
				PropertiesLoader.ACEMD_INPUT2,
				PropertiesLoader.ACEMD_INPUT3,
				PropertiesLoader.ACEMD_INPUT4,
				PropertiesLoader.ACEMD_INPUT5,
				PropertiesLoader.ACEMD_INPUT6,
				PropertiesLoader.ACEMD_INPUT7);		
	}
}
