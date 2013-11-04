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

		FileProcessing fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\psequences.txt", 1); 

		//System.out.println(fp.readFile());
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,fp.getDataDimension(),true,3);
		SOM.initTrainSOM(fp.readFile(),100,0.25);
		
		//trainSOM(fp.readFile());
		
	}

}