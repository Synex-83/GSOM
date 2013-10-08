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
		// TODO Auto-generated method stub
		FileProcessing fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\sequences.txt", 1); 

		//System.out.println(fp.readFile());
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,10,fp.getDataDimension());
		SOM.initTrainSOM(fp.readFile(),100,0.25);
		
		//trainSOM(fp.readFile());
		
	}

}