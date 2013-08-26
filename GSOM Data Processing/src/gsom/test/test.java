/**
 * 
 */
package gsom.test;

import gsom.processing.ProcessFileToGSOM;

import java.io.File;


/**
 * @author Manjusri
 *
 */
public class test {

	
	public static void showFiles(File[] files,int Option) {
		String filename = "";
		ProcessFileToGSOM rf = new ProcessFileToGSOM();
		
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
						//file.delete();
					}
					
					if(filename.length() <=5 )
					{
						//file.delete();
					}
				} else {
		            System.out.println("File: " + file.getName());
		            if(filename.contains("output42.txt"))
		            {
		            	rf.readFromFile(filename,file.getParent(),0);
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


}
