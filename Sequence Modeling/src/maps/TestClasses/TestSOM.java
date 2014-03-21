/**
 * @package		maps.TestClasses
 * @filename	TestSOM.java
 */
package maps.TestClasses;

import maps.Util.FileProcessing;
import maps.Structures.SelfOrganizingMap;

/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:47:27 PM
 * @type		TestSOM
 */
public class TestSOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		FileProcessing fp = null;
		int th = 0;
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\Pacman\\2g.txt"
		// E:\\workspace\\GSOM\\Sequence Modeling\\data\\repeat.txt
		//"C:\\Users\\User\\Desktop\\A.txt"
		//"
		//E:\\workspace\\GSOM\\Sequence Modeling\\data\\4sequences.txt
		//C:\\Users\\User\\Desktop\\A.txt
		//E:\\workspace\\GSOM\\Sequence Modeling\\data\\Pacman\\W-File\\15w.txt
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\sets\\V00654.txt", 1); //2gameCompact.txt

		//fp.writeFile();
		//for matrix based learning
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,fp.getDataDimension(),true,2,350,0,3);
		
		System.out.println("HERE 3");

		//SOM.initTrainSOMMemoryEfficient("E:\\workspace\\GSOM\\Sequence Modeling\\data\\Pacman\\G-File\\15g.txt", 100, 0.25);

		//SelfOrganizingMap SOM = new SelfOrganizingMap(16,fp.getDataDimension(),false,2,50,0,4);
		SOM.initTrainSOM(fp.readFile(),100,0.25);
		//trainSOM(fp.readFile());
		
		
		/*
		 * WHEN ANALYZING DATA THE ZERO MAP DO NOT NEED TO BE TRANSPOSED BEFORE ANALYSIS BECUASE THE PROGRAM
		 * WORKS AS INTENDED WITH THE INDEXES. HOWEVER, THE MAP NEEDS TO BE TRANPOSED BEFORE ANALYSIS.
		 * 
		 * PLEASE BE MINDFUL OF THIS BEFORE PROCEEDING WITH ANALYSIS
		 */
	}

}