package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExperimentRunner;
import utils.PropertiesLoader;

public class Namd  extends ExperimentRunner{
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
		//namd
		
		//driver, application path, experiment name, compute resource, queue, input files...
		
		//Bridges2
		runExperiment(driver, 
				By.xpath("//h2[.='namd']"),
				"namd Bridges 2",   
				"Bridges2",  
				"RM",
				PropertiesLoader.NAMD_DIR,
				PropertiesLoader.NAMD_INPUT1,
				PropertiesLoader.NAMD_INPUT2,
				PropertiesLoader.NAMD_INPUT3,
				PropertiesLoader.NAMD_INPUT4);
		
		//Expanse
		runExperiment(driver, 
				false,
				By.xpath("//h2[.='namd']"), 
				"namd Expanse", 
				"Expanse",  
				"compute",
				PropertiesLoader.NAMD_DIR,
				PropertiesLoader.NAMD_INPUT1,
				PropertiesLoader.NAMD_INPUT2,
				PropertiesLoader.NAMD_INPUT3,
				PropertiesLoader.NAMD_INPUT4);
		addWait(200);
		
		//Bridges 2
		runExperiment(driver, 
				false,
				By.xpath("//h2[.='namd']"), 
				"namd Stampede 2", 
				"stampede2.tacc.xsede.org",
				"normal",
				PropertiesLoader.NAMD_DIR,
				PropertiesLoader.NAMD_INPUT1,
				PropertiesLoader.NAMD_INPUT2,
				PropertiesLoader.NAMD_INPUT3,
				PropertiesLoader.NAMD_INPUT4);
		addWait(200);
	}
	
	

}
