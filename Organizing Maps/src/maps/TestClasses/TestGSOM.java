/**
 * 
 */
package maps.TestClasses;

import maps.Structures.GrowingSelfOrganizingMap;
import maps.Util.FileProcessing;

/**
 * @author 		Manjusri Ishwara
 * @date		Jul 6, 2013 - 11:42:11 PM
 * @type        TestGSOM
 *
 */
public class TestGSOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileProcessing fp = new FileProcessing("E:\\PhD\\Experiments\\Complete Experiment on Patterns\\Experiment 1" +
				"\\01.Randa Rasanga - [Clean]\\12333.txt", 1);
		//System.out.println(fp.readFile());
		GrowingSelfOrganizingMap GSOM = new GrowingSelfOrganizingMap(fp.getDataDimension(), 100, 0.25, 0.8, 5, fp.readFile(), null);
	
		

	}

}
