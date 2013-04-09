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
		FileProcessing fp = new FileProcessing("E:\\PhD\\Experiments\\Complete Experiment on Patterns\\Experiment 1" +
				"\\01.Randa Rasanga - [Clean]\\123.txt", 1);
		//System.out.println(fp.readFile());
		SelfOrganizingMap SOM = new SelfOrganizingMap(100,10,0,fp.getDataDimension());
		SOM.trainSOM(fp.readFile(), 100, 0.25);
	}

}
