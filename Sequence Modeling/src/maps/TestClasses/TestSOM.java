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

		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\Protein\\O22918Tab.txt", 1);
		//System.out.println(fp.readFile());
		/*	
		for(int j = 4; j <=5 ; j++)
		{
			
			if(j == 1)
			{
				fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\sequences.txt", 1); 
				th = 800;
			}
			else if(j == 2)
			{
				fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\testCombinations.txt", 1);
				th = 800;
			}
			else if(j == 3)
			{
				fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\4sequences.txt", 1);
				th = 800;
			}
			if(j == 4)
			{
				fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\encodedSeq.txt", 1);
				th = 100;
			}
			else if(j == 5)
			{
				fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\game.txt", 1);
				th = 100;
			}
			
			for(int i = 1; i <= 100; i++)
			{*/
				SelfOrganizingMap SOM = new SelfOrganizingMap(10000,fp.getDataDimension(),true,3,50,0,6);
				SOM.initTrainSOM(fp.readFile(),100,0.25);
			//}
		//}
		
		//trainSOM(fp.readFile());
		
	}

}