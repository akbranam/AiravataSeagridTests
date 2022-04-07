package applications;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.ExperimentRunner;
import utils.PropertiesLoader;

/*
*
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
*
*   http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/

public class QEspresso extends ExperimentRunner{
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
		//QEspresso
		
		//driver, application path, experiment name, compute resource, queue, input files...
		runExperiment(driver, 
				By.xpath("//h2[contains(text(), 'QEspresso-6.6')]"), 
				"QEspresso Stampede2", 
				"stampede2.tacc.xsede.org",
				"normal",
				PropertiesLoader.QESPRESSO_DIR,
				PropertiesLoader.QESPRESSO_INPUT);
		addWait(200);
		
		runExperiment(driver,
				false,
				By.xpath("//h2[contains(text(), 'QEspresso-6.6')]"),
				"QEspresso Bridges2", 
				"Bridges2",
				"RM",
				PropertiesLoader.QESPRESSO_DIR,
				PropertiesLoader.QESPRESSO_INPUT);
		addWait(200);
	}


}
