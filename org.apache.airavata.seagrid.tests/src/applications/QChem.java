package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ExperimentRunner;
import utils.PropertiesLoader;

public class QChem extends ExperimentRunner{
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
		//QChem Expanse
		
		//driver, application path, experiment name, compute resource, queue, input files...
		runExperiment(driver, 
				By.xpath("//h2[contains(text(), 'QChem')]"),
				"QChem Expanse",
				"Expanse",
				"gpu",
				PropertiesLoader.QCHEM_DIR, 
				PropertiesLoader.QCHEM_INPUT);
	}
}
