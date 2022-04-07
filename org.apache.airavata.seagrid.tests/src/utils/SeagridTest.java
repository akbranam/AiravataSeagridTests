package utils;
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

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class SeagridTest {
	//function that logs into the seagrid portal
		public void login (WebDriver driver) throws Exception {
			String username, password;
			//get values from properties file
			username = readConfigFile("seagrid_username");
			password = readConfigFile("seagrid_password");
			
			//trim any whitespace from properties file strings
			username = username.trim();
			password = password.trim();
			
			//click login button
			driver.findElement(By.linkText("Log in")).click();//go to login screen
			
			//enter username and password
			addWait(500);
		    driver.findElement(By.id("username")).sendKeys(username);//enter username
		    addWait(500);
		    driver.findElement(By.id("password")).sendKeys(password);//enter password
		    addWait(500);
		    
		    //click submit button
		    driver.findElement(By.cssSelector(".btn:nth-child(4)")).click();
		}

		//Function that reads properties from config file
		public String readConfigFile(String propField) throws Exception{	
			return PropertiesLoader.readConfigFile(propField);
		}
		
		//Function that adds wait time
		public void addWait(int i) {
			try {
				Thread.sleep(i);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}		
		}
		
		//function that scrolls the visible range to the specified element
		public void scrollToElement(WebElement element, WebDriver driver) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()", element);
		}
		
		//function that attempts to click on the specified element
		public void attemptClick(WebElement element, WebDriver driver) throws Exception {
			int time = 0;
			while (true) {
				try {
					scrollToElement(element, driver);
					element.click();
					break;
				}catch (Exception e) {
					if (time>100) {
						throw new Exception (e);
					}
					time++;
					addWait(300);
					continue;
				}
			}
		}
		
		//function that attempts to click on the specified element
		public void attemptClick(By by, WebDriver driver) throws Exception {
			int time = 0;
			while (true) {
				try {
					WebElement element = driver.findElement(by);
					scrollToElement(element, driver);
					element.click();
					break;
				}catch (Exception e) {
					if (time>100) {
						throw new Exception (e);
					}
					time++;
					addWait(300);
					continue;
				}
			}
		}
		
		//returns the current date as string
		public String currentDateAsString() {
			Calendar calendar = Calendar.getInstance();
			int day = calendar.get(Calendar.DAY_OF_MONTH);
			int month = calendar.get(Calendar.MONTH);
			String strMonth = "";
			switch (month) {
			case 0: strMonth = "Jan"; break;
			case 1: strMonth = "Feb"; break;
			case 2: strMonth = "Mar"; break;
			case 3: strMonth = "Apr"; break;
			case 4: strMonth = "May"; break;
			case 5: strMonth = "Jun"; break;
			case 6: strMonth = "Jul"; break;
			case 7: strMonth = "Aug"; break;
			case 8: strMonth = "Sep"; break;
			case 9: strMonth = "Oct"; break;
			case 10: strMonth = "Nov"; break;
			case 11: strMonth = "Dec"; break;}
			return strMonth+"_"+Integer.toString(day);
		}
		
		//returns current time as a string
		public String currentTimeAsString() {
			Calendar calendar = Calendar.getInstance();
			int min = calendar.get(Calendar.MINUTE);
			int hour = calendar.get(Calendar.HOUR_OF_DAY);
			return Integer.toString(hour)+":"+Integer.toString(min);
		}
		
		//check if element exists
		public Boolean doesElementExist(WebDriver driver, By by) {
			try {
				driver.findElement(by);
				return true;
			}catch(NoSuchElementException e) {
				return false;
			}
		}
		
		//function that reads the specified WebDriver type from the properties file and sets the driver to that type
		public WebDriver setDriver() throws Exception{
			WebDriver driver;
			String default_driver, local_path;
		
			//get variables from properties file
			default_driver = readConfigFile("default_driver");
			local_path = readConfigFile("local_path");

			default_driver = default_driver.trim();
			default_driver= default_driver.toLowerCase();
			
			if (default_driver.contentEquals("chrome")) {//set default driver to chrome
				driver = new ChromeDriver();			
			}
			else if (default_driver.contentEquals("firefox")) {//set default driver to firefox
				driver = new FirefoxDriver();
			}
			else {
				throw new RuntimeException("default driver specified in properties file is not recognised");
			}
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			return driver;
		}
		
		public void setProject(String name) {}

		//junit test functions
		public void setUp(WebDriver driver, Boolean clone, Boolean cancel) throws Exception{}
		public void setUp() throws  Exception{}
		public void tearDown() throws Exception{}
		public void test() throws Exception{}
}
