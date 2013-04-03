/**
 * 
 */
package clean.test;

import java.io.File;

import clean.space.SpaceCleaner;

/**
 * @author Manjusri
 *
 */
public class SpaceTest {


	
	public static void showFiles(File[] files,int Option) {
		String filename = "";
		SpaceCleaner sc = new SpaceCleaner();
		
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
		            if(filename.contains("alpha1.txt"))
		            {
		            	sc.readFromFile(filename,file.getParent());
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



