/**
 * @package		maps.TestClasses
 * @filename	TestSOM.java
 */
package maps.TestClasses;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {

		FileProcessing fp = null;
		FileProcessing fp1 = null;
		int th = 0;
		int length = 0;
		int threshold = 0;
		
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\Sets\\exe.txt"
		//"C:\\Users\\User\\Desktop\\Normal FSM vs My Version\\data6.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\Pacman\\W-File\\6w.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\humanCSV.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\repeat.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\repeatC.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\Sets\\HCE\\hce2.txt"
		//"E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\hce2.txt"
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\humanCSV.txt", 1); 
		fp1 = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\humanCSVC.txt", 1); 
		//length = 752;
		//threshold = (int)Math.round((length/154.43)*(100)*(0.0));
		length = 49;
		threshold =  500;//(int)Math.round((length/3)*(100)*(0.8));
		
		System.out.println("*******************\t" + fp.getFileName()+"\t****************************");

		SelfOrganizingMap SOM = new SelfOrganizingMap(25,fp.getDataDimension(),true,true,2,1,20,1,fp1.readFileContinuous()); //null; //
		
		//============SERIALIZE================
		
		FileOutputStream fout = new FileOutputStream("address1.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(SOM);
		oos.close();
		fout.close();
		System.out.println("Done Serializing");
		
		//=============DESERIALIZE===============		

			/*FileInputStream fin = new FileInputStream("address.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			SOM = (SelfOrganizingMap) ois.readObject();
			ois.close();
			System.out.println("Done Retrieving the serialized object.");*/

			SOM.FSM.setThreshold(threshold);
			//SOM.setVECTOR_WEIGHTS(new double[]{0.0,0.0,0.00,1.0});
			
			System.out.println("THRESHOLD SET");
		

			System.out.println("====================================================================================");
			System.out.println();
			System.out.println("===================== LENGTH = " + length +" =======================================");
			System.out.println("===================== THRESHOLD = " + threshold +" =======================================");
			System.out.println();
			System.out.println("====================================================================================");
			
			//SelfOrganizingMap SOM = new SelfOrganizingMap(16,fp.getDataDimension(),false,2,50,0,4);
			SOM.initTrainSOM(fp.readFile(),20,0.25);//100
			
			//SOM.testVectorSOM(fp.readFile());
			
			System.out.println("====================================================================================");
			System.out.println("====================================================================================");
			System.out.println("====================================================================================");
			System.out.println();
			System.out.println();
			System.out.println();
		//}
		/*
		 * WHEN ANALYZING DATA THE ZERO MAP DO NOT NEED TO BE TRANSPOSED BEFORE ANALYSIS BECUASE THE PROGRAM
		 * WORKS AS INTENDED WITH THE INDEXES. HOWEVER, THE MAP NEEDS TO BE TRANPOSED BEFORE ANALYSIS.
		 * 
		 * PLEASE BE MINDFUL OF THIS BEFORE PROCEEDING WITH ANALYSIS
		 */
	}

}