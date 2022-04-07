package utils;

import java.io.InputStream;
import java.util.Properties;


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

/*
 * Properties Loader Class
 * 
 * created on 8/20/2020
 * last modified 6/10/2021
 * 
 * loads properties file
 * 
 */

public class PropertiesLoader {
	
	public static String readConfigFile(String propField) throws Exception{	
		try {
			Properties prop = new Properties();
			ClassLoader loader = PropertiesLoader.class.getClassLoader();
			InputStream stream = loader.getResourceAsStream("resources/config/config.properties");
			if(stream==null){
                throw new Exception("properties file unreadable");
            }
			prop.load(stream);
			String value = prop.getProperty(propField);
			if (value == null) {//check for null values
				throw new Exception(propField+" value is not specified in properties file");
			}
			return  value;
		}catch (Exception e){
            throw new Exception("Error when reading properties file for prop field: "+propField, e);
		}	
	}

	
	//abinit files: "tbase1_files.in", "tbase1_2.in", "01h.pspgth"
	public static String ABINIT_DIR = "ABINIT_FILES";
	public static String ABINIT_INPUT1 = "tbase1_files.in";
	public static String ABINIT_INPUT2 = "tbase1_2.in";
	public static String ABINIT_INPUT3 = "01h.pspgth";
	
	//AceMD files: "input" "input.ref" "input.coor" "input.xsc" "structure.pdb "parameters" "structure.prmtop"
	public static String ACEMD_DIR = "ACEMD_FILES";
	public static String ACEMD_INPUT1 = "input"; 
	public static String ACEMD_INPUT2 = "input.ref";
	public static String ACEMD_INPUT3 = "input.coor";
	public static String ACEMD_INPUT4 = "input.xsc";
	public static String ACEMD_INPUT5 = "structure.pdb";
	public static String ACEMD_INPUT6 = "parameters";
	public static String ACEMD_INPUT7 = "structure.prmtop";
	
	
	//Amber Sander Files: 02_Heat.rst, 03_Prod.in,  prmtop
	public static String AMBER_SANDER_DIR = "AMBER_SANDER_FILES";
	public static String AMBER_SANDER_INPUT1 = "02_Heat.rst";
	public static String AMBER_SANDER_INPUT2 = "03_Prod.in";
	public static String AMBER_SANDER_INPUT3 = "prmtop";
	
	//Auto Dock Vina Files: receptor.pdbqt, ligand1.pdbqt, config
	public static String AUTODOCK_VINA_DIR = "AUTODOCK_VINA";
	public static String AUTODOCK_VINA_INPUT1 = "receptor.pdbqt";
	public static String AUTODOCK_VINA_INPUT2 = "ligand1.pdbqt";
	public static String AUTODOCK_VINA_INPUT3 = "config";
	
	//CP2K Files:
	public static String CP2K_DIR = "CP2K_FILES";
	public static String CP2K_INPUT = "fayalite.inp";
	
	//CPMD Files:
	public static String CPMD_DIR = "CPMD_FILES";
	public static String CPMD_INPUT = "default.inp";
	
	//DDSCat Files:
	public static String DDSCat_DIR = "DDSCAT_FILES";
	public static String DDSCat_INPUT1 = "ddscat.par";
	public static String DDSCat_INPUT2 = "AgDiel.tab";
	
	//DFTB
	public static String DFTB_DIR = "DFTB_FILES";
	public static String DFTB_INPUT = "CaCO3_T2_in.hsd";
	
	//Gammess Files: exam02.inp
	public static String GAMMESS_DIR = "GAMMESS_FILES";
	public static String GAMMESS_INPUT = "exam02.inp";
	
	//Gaussian Files: neopentanediol.inp
	public static String GAUSSIAN_DIR = "GAUSSIAN_FILES";
	public static String GAUSSIAN_INPUT = "neopentanediol.inp";
	
	//Gromacs Files pdb1y6l-EM-vacuum.gro.tpr, pdb1y6l-EM-vacuum.gro
	public static String GROMACS_DIR = "GROMMACS_FILES";
	public static String GROMACS_INPUT1 = "pdb1y6l-EM-vacuum.gro.tpr";
	public static String GROMACS_INPUT2 = "pdb1y6l-EM-vacuum.gro";
	
	//Lammps Files: in.friction
	public static String LAMMPS_DIR = "LAMMPS_FILES";
	public static String LAMMPS_INPUT = "in.friction";
	
	//NAMD
	public static String NAMD_DIR = "NAMD_FILES";
	public static String NAMD_INPUT1 = "alanin.pdb";
	public static String NAMD_INPUT2 = "alanin.params";
	public static String NAMD_INPUT3 = "alanin.psf";
	public static String NAMD_INPUT4 = "alanin.namd";
	
	//NWChem Files
	public static String NWCHEM_DIR = "NWCHEM_FILES";
	public static String NWCHEM_INPUT = "water.nw";
	
	//Orca Files
	public static String ORCA_DIR = "ORCA_FILES";
	public static String ORCA_INPUT = "SotoWlactamDLPNO.inp";

	
	//Phasta Files: phasta_input.tar
	public static String PHASTA_DIR = "PHASTA_FILES";
	public static String PHASTA_INPUT = "phasta_input.tar";
	
	//PSI4 Files
	public static String PSI4_DIR = "PSI4_FILES";
	public static String PSI4_INPUT = "psimrcc-ccsd_t-1_input.dat";
	
	//QChem Files
	public static String QCHEM_DIR = "QCHEM_FILES"; 
	public static String QCHEM_INPUT = "OPT_water.in";
	
	//QESPRESSO Files
	public static String QESPRESSO_DIR = "QESPRESSO"; 
	public static String QESPRESSO_INPUT = "Al.pz-vbc.UPF";
	
	//Vina_Multiple Files: receptor.pdbqt, ligands.zip, config
	public static String VINA_MULTIPLE_DIR = "AUTODOCK_VINA_MULTIPLE";
	public static String VINA_MULTIPLE_INPUT1 = "receptor.pdbqt";
	public static String VINA_MULTIPLE_INPUT2 = "ligands.zip";
	public static String VINA_MULTIPLE_INPUT3 = "config";
	
}