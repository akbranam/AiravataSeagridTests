
import java.awt.Desktop;
import java.io.File;
import java.io.PrintStream;
import java.util.Random;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import applications.Abaqus;
import applications.Abinit;
import applications.AceMD;
import applications.AmberSander;
import applications.Amber_pmemd;
import applications.AutoDock;
import applications.AutoDockVina;
import applications.CP2K;
import applications.DFTB;
import applications.Gamess;
import applications.GamessBR3;
import applications.Gaussian16;
import applications.GromacsWithRestart;
import applications.Lammps;
import applications.NAMDKNL;
import applications.NAMD_SKX;
import applications.NWChem;
import applications.Namd;
import applications.Orca;
import applications.PSI4;
import applications.QChem;
import applications.QEspresso;
import utils.CreateProject;
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

public class RunTests extends SeagridTest{
	PrintStream console;
	 String local_path, logfile, projectName;
	 public int exceptions, test_count;

	@BeforeEach
	public
	void setUp() throws Exception {
		exceptions = 0;
		test_count = 0;
		local_path = readConfigFile("local_path");
		
		//set output to file instead of command line
		setOutput("run_tests_output_", console);
	}

	@AfterEach
	public void tearDown() throws Exception {
		System.out.println("Tests Run: "+Integer.toString(test_count));
		System.out.println("Exceptions Thrown: "+Integer.toString(exceptions));
		if (console!=null) {
		System.setOut(console);
		}
		Desktop desktop = Desktop.getDesktop();
		File file = new File(logfile);
		if (file.exists()) {
			desktop.open(file);
		}
	}

	@Test
	public void test() throws Exception{
		createProject();
		System.out.println("Project "+projectName+" Created");
		System.out.println("Running All Seagrid Portal Tests");
		//runTest(new Abaqus(), "Abaqus");
		runTest(new Abinit(), "Abinit");
		runTest(new AceMD(), "AceMD");
		runTest(new AmberSander(), "Amber Sander");
		runTest(new AutoDock(), "AutoDock");
		runTest(new AutoDockVina(), "AutoDock Vina");
		runTest(new CP2K(), "CP2K");
		runTest(new DFTB(), "DFTB");
		runTest(new Gamess(), "Gamess");
		runTest(new GamessBR3(), "Gamess BR3");
		runTest(new Gaussian16(), "Gaussian 16");
		runTest(new GromacsWithRestart(), "Gromacs With Restart");
		runTest(new Lammps(), "Lammps");
		runTest(new Namd(), "namd");
		runTest(new NAMD_SKX(), "namd_skx");
		runTest(new NAMDKNL(), "namd-dkl");
		runTest(new NWChem(), "NWChem");
		runTest(new Orca(), "Orca");
		runTest(new PSI4(), "PSI4");
		runTest(new QChem(), "QChem");
		runTest(new QEspresso(), "QEspresso");
		System.out.println("All Seagrid Portal Tests Complete");
	}
	
	public void runTest(SeagridTest test, String testName) throws Exception {
		//System.out.println("Starting "+testName);
		try {
			test_count++;
			test.setUp();
			test.setProject(projectName);
			test.test();
			System.out.println(testName+" Completed");
		}catch (Exception e) {
			System.out.println(testName+" Failed with Error");
			System.out.println(e.getMessage());
			e.printStackTrace();
			exceptions++;
		}
		test.tearDown();
       System.out.println(testName+" Done");
	}
	
	public void setOutput(String filename, PrintStream console) throws Exception {
		//if directory doesn't exist, create a new one
		
		String path = local_path+"\\TestLogs";
		String dir = "\\"+currentDateAsString();
		
		//create the test log directory if it doesn't already exist
		File file = new File(path);
		Boolean createdDir = file.mkdir();
		if (createdDir) {
			System.out.println(file.getPath()+" created");
		}
		
		//create a new directory based on the date if one hasn't already been created
		file = new File(path+dir);
		System.out.println(file.getPath());
		createdDir = file.mkdir();
		if (createdDir) {
			System.out.println(file.getPath()+" created");
		}
		
		//create the output file
		logfile = file.getPath()+"\\"+filename+"_"+getRandomString(10)+".log";
		 
		System.out.println(logfile);
		file = new File(logfile);
		if (!file.createNewFile()) {
			System.out.println("file not created");
			throw new Exception("output file not created");
		}

		//set system print output to file
		PrintStream testOutput = new PrintStream(new File(logfile));
		console = System.out;//save the console 
		System.setOut(testOutput);
	}
	
	//returns a string of random letters and numbers
	public String getRandomString(int len) {
		Random r = new Random();
       String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";//usable characters
       StringBuilder str = new StringBuilder();
       while (str.length() < len) { // length of the random string.
           int index = (int) (r.nextFloat() * chars.length());
           str.append(chars.charAt(index));
       }
       return str.toString();
   }
	
	private void createProject() {
		CreateProject project = new CreateProject();
		try {
			projectName = project.genProjectName();
			project.setUp();
			project.test();
			project.tearDown();
		} catch (Exception e) {
			System.out.println("Project creation failed with Error");
			e.printStackTrace();
			exceptions++;
		}
	}
}
