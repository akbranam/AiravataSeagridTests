package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExperimentRunner;
import utils.PropertiesLoader;

//Amber_pmemd
public class Amber_pmemd   extends ExperimentRunner{
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
		//Amber Sander
		
		//driver, application path, experiment name, project, GRP, compute resource, queue, input files...
		//Amber Sander Files: 02_Heat.rst, 03_Prod.in,  prmtop
		
		//Expanse
		runExperiment(driver, 
				By.xpath("//h2[contains(text(), 'Amber_Sander')]"),
				"Amber Sander Expanse",
				"Expanse",  
				"compute",
				PropertiesLoader.AMBER_SANDER_DIR,
				PropertiesLoader.AMBER_SANDER_INPUT1,
				PropertiesLoader.AMBER_SANDER_INPUT2,  
				PropertiesLoader.AMBER_SANDER_INPUT3);
	}


}
