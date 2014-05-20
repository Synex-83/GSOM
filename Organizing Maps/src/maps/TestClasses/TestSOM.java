/**
 * @package		maps.TestClasses
 * @filename	TestSOM.java
 */
package maps.TestClasses;

import maps.Structures.SelfOrganizingMap;
import maps.Util.FileProcessing;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 31, 2013 - 2:00:09 AM
 * @type		TestSOM
 */
public class TestSOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileProcessing fp = new FileProcessing("C:\\Users\\User\\Desktop\\Hit Count Expr\\zoo2.txt", 1);
		//System.out.println(fp.readFile());
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,100,fp.getDataDimension());
		SOM.initTrainSOM(fp.readFile(), 100, 0.25);
		//SOM.printSOM();
		SOM.trainSOM();
		SOM.testSOM(fp.readFile());
		//SOM.printSOM();
	}

}
