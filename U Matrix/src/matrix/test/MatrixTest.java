/**
 * 
 */
package matrix.test;

import java.io.File;

import matrix.sortfile.SortFile;


/**
 * @author Manjusri
 *
 */
public class MatrixTest {

	public static void showFiles(File[] files,int Option) {
		String filename = "";
		SortFile sf = new SortFile();
		
	    for (File file : files) {
	        if (file.isDirectory()) {
	        	if(Option != 0)
	        	{
		          //  System.out.println("Directory: " + file.getName());
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
		            //System.out.println("File: " + file.getName());
		            if(filename.contains("trained52.txt"))
		            {
		            	sf.readFromFile(filename,file.getParent());
		            }
				}
	        }
	    }
	}
	
	public static void main(String args[])
	{
	    File[] files = new File("C:\\Users\\Manjusri\\Desktop").listFiles();
	    showFiles(files,1);
	}

}
