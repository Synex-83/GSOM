/**
 * 
 */
package maps.TestClasses;

import maps.Structures.GSOMArray;
import maps.Util.FileProcessing;

/**
 * @author 		User
 * @date		Jul 8, 2013 - 8:16:11 PM
 * @type        TestGSOMArray
 *
 */
public class TestGSOMArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileProcessing fp = new FileProcessing("C:\\Users\\User\\Desktop\\Hit Count Expr\\zoo2.txt", 1);
		
		GSOMArray gsom = new GSOMArray(fp.getDataDimension(), 100, 0.25, 5, 0.8, fp.readFile(), null);
		gsom.printGSOM();
	}

}
