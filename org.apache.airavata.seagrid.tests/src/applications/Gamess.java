package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExperimentRunner;
import utils.PropertiesLoader;

public class Gamess  extends ExperimentRunner{
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
		//Gamess
		
		//driver, application path, experiment name, compute resource, queue, input files...
		
		//Bridges2
		runExperiment(driver, 
				By.xpath("//h2[.='Gamess']"),
				"Gamess Bridges 2",  
				"Bridges2",  
				"RM",
				PropertiesLoader.GAMMESS_DIR,
				PropertiesLoader.GAMMESS_INPUT);

		//Expanse
		runExperiment(driver,
				false,
				By.xpath("//h2[.='Gamess']"),
				"Gamess Expanse",  
				"Expanse",  
				"compute",
				PropertiesLoader.GAMMESS_DIR,
				PropertiesLoader.GAMMESS_INPUT);
				
		//Stampede 2
		runExperiment(driver,
				false,
				By.xpath("//h2[.='Gamess']"),
				"Gamess Stampede 2",  
				"stampede2.tacc.xsede.org",
				"normal",
				PropertiesLoader.GAMMESS_DIR,
				PropertiesLoader.GAMMESS_INPUT);

	}
}
