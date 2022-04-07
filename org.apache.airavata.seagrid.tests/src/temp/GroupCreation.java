package temp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.SeagridTest;

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

public class GroupCreation extends SeagridTest {
	WebDriver driver;
	String start_url, group_name, group_desc; 
	
	
	@BeforeEach
	public void setUp() throws Exception {
		driver = setDriver();
		start_url = readConfigFile("start_url");
		group_name = readConfigFile("group_name"); 
		group_desc = readConfigFile("group_desc");
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() throws Exception {
		
		//login
		driver.get(start_url);
		login(driver);
		addWait(200);
		
		//go to groups
		attemptClick(driver.findElement(By.id("appDropdownMenuButton")), driver);
	    attemptClick(driver.findElement(By.linkText("Groups")), driver);
	    
	    //click create new group
	    attemptClick(driver.findElement(By.linkText("Create New Group")), driver);
	    
	    //enter group name
	    driver.findElement(By.id("group_name")).sendKeys(group_name);
	    
	    //enter group description
	    driver.findElement(By.id("description")).sendKeys(group_desc);
	    
	    //add users to group
	    
	    
	    //change role of users
	    
	    //delete users from group
	    
	    //click submit
	    attemptClick(driver.findElement(By.xpath("//button[contains(text(), 'Submit')]")), driver);
	    
	    //check that group is present in group list
	    if (!doesElementExist(driver, By.xpath("//h1[contains(text(), 'Groups')]"))){
			throw new Exception("Group didn't save");
	    }
	    if (!this.doesElementExist(driver, By.xpath("//td[contains(text(), '"+group_name+"')]"))) {
	    	throw new Exception("Group not created");
	    }
	}
}
