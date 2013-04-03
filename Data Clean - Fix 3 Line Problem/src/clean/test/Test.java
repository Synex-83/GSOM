/**
 * 
 */
package clean.test;

import java.io.File;

import clean.read.readFile;

/**
 * @author Manjusri
 *
 */
public class Test {

	/**
	 * @param args
	 */
	/*public static void main(String[] args) {
		readFile rf = new readFile();
		
		rf.readFromFile("E:\\workspace\\textfiles\\data1.txt");
				

	}*/
	
	public static void showFiles(File[] files,int Option) {
		String filename = "";
		readFile rf = new readFile();
		
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
		            if(filename.contains("data2"))
		            {
		            	rf.readFromFile(filename,file.getParent());
		            }
				}
	        }
	    }
	}
	
	public static void main(String args[])
	{
	    File[] files = new File("E:\\PhD\\My Data").listFiles();
	    showFiles(files,1);

	}


}
