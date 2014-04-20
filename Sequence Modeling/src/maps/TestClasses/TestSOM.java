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
		int th = 0;
		int length = 0;
		int threshold = 0;

		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\Sets\\X83427.txt", 1); 
		length = 1701900;
		threshold = (int)Math.round(length*(0.05));
		
		System.out.println("*******************            V00711             ****************************");

		SelfOrganizingMap SOM = null; //new SelfOrganizingMap(10000,fp.getDataDimension(),true,2,1000,0,4); //null; //
		
		//============SERIALIZE================
		
		/*FileOutputStream fout = new FileOutputStream("address.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fout);   
		oos.writeObject(SOM);
		oos.close();
		fout.close();
		System.out.println("Done Serializing");*/
		
		//=============DESERIALIZE===============		
		
/*		for(int i = 1; i < 5; i++)
		{
			
			switch(i)
			{
				case 1:
					threshold = (int)Math.round(length*(0.05));
					break;
				case 2:
					threshold = (int)Math.round(length*(0.03));
					break;
				case 3:
					threshold = (int)Math.round(length*(0.01));
					break;
				case 4:
					threshold = (int)Math.round(length*(0.1));
					break;
			}*/
			FileInputStream fin = new FileInputStream("address.ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			SOM = (SelfOrganizingMap) ois.readObject();
			ois.close();
			System.out.println("Done Retrieving the serialized object.");
		
			SOM.FSM.setThreshold(threshold);
			System.out.println("THRESHOLD SET");
		

			System.out.println("====================================================================================");
			System.out.println();
			System.out.println("===================== LENGTH = " + length +" =======================================");
			System.out.println("===================== THRESHOLD = " + threshold +" =======================================");
			System.out.println();
			System.out.println("====================================================================================");
			
		//SelfOrganizingMap SOM = new SelfOrganizingMap(16,fp.getDataDimension(),false,2,50,0,4);
			SOM.initTrainSOM(fp.readFile(),100,0.25);
		//trainSOM(fp.readFile());
			
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