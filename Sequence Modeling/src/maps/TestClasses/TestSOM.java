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
<<<<<<< HEAD
		fp = new FileProcessing("C:\\Users\\User\\Downloads\\MSNBC\\msnbc.txt", 1); //2gameCompact.txt
=======
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\psequences.txt", 1); //2gameCompact.txt
>>>>>>> edd4be362db79d7daeaede41f927bcb06427f35d

		//for matrix based learning
		SelfOrganizingMap SOM = new SelfOrganizingMap(10000,fp.getDataDimension(),true,2,50,0,2);
		
		System.out.println("HERE 1");
<<<<<<< HEAD
		SOM.initTrainSOMMemoryEfficient("C:\\Users\\User\\Downloads\\MSNBC\\msnbc.txt", 100, 0.25);
=======
		SOM.initTrainSOMMemoryEfficient("E:\\workspace\\GSOM\\Sequence Modeling\\data\\psequences.txt", 100, 0.25);
>>>>>>> edd4be362db79d7daeaede41f927bcb06427f35d
		
		
		//SelfOrganizingMap SOM = new SelfOrganizingMap(16,fp.getDataDimension(),false,2,50,0,4);
		//SOM.initTrainSOM(fp.readFile(),100,0.25);
		//trainSOM(fp.readFile());
		
	}

}