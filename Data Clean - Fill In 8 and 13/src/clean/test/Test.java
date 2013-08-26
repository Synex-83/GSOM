/**
 * 
 */
package clean.test;

import java.io.File;

import clean.fillin.FixIt;


/**
 * @author Manjusri
 *
 */
public class Test {

	public static void showFiles(File[] files,int Option) {
		String filename = "";
		FixIt fl = new FixIt();
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	        	if(Option != 0)
	        	{
		            System.out.println("Directory: " + file.getName());
	        	}

	            showFiles(file.listFiles(),Option); // Calls same method again.
	        } else {
	        	filename = file.getName();
	        	if (Option ==0) {
					if(filename.length() >= 14)
					{
						file.delete();
					}
					
					if(filename.length() <=5 )
					{
						file.delete();
					}
				} else {
		            System.out.println("File: " + file.getName());
		            if(filename.contains("output42"))
		            {
		            	fl.readFromFile(filename,file.getParent());
		            }
				}
	        }
	    }
	}
	
	public static void main(String args[])
	{
	    File[] files = new File("E:\\PhD\\My Data\\25.Kanna  R").listFiles();
	    showFiles(files,1);

	}
	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		//FillIn fl = new FillIn();
		FixIt fi = new FixIt();
		
		fi.readFromFile("output3.txt", "E:\\PhD\\My Data\\04.Charan Tennakoon");
	}*/

}
