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

		fp = new FileProcessing("C:\\Users\\User\\Downloads\\MSNBC\\test.txt", 1); //2gameCompact.txt

		//for matrix based learning
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,fp.getDataDimension(),true,2,50,0,4);
		SOM.initTrainSOM(fp.readFile(),100,0.25);
		
		
		//SelfOrganizingMap SOM = new SelfOrganizingMap(16,fp.getDataDimension(),false,2,50,0,4);
		//SOM.initTrainSOM(fp.readFile(),100,0.25);
		//trainSOM(fp.readFile());
		
	}

}