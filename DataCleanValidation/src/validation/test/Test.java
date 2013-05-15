/**
 * 
 */
package validation.test;

import java.io.File;
import java.io.FileNotFoundException;
import validation.maze.Maze;

/**
 * @author   Manjusri Ishwara
 * @date     Aug 28, 2012 1:00:36 PM
 * @file     Test.java
 * @package  validation.test
 */
public class Test {
	
	
	public static void showFiles(File[] files,int Option) {
		String filename = "";
		Maze mz = new Maze();
		mz.createAllJunctions();
		mz.createGraph("E:\\workspace\\GSOM - SVN\\DataCleanValidation\\src\\validation\\test\\graph.txt");
		mz.createRelationships();
		
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
		            //System.out.println("File: " + file.getName());
		            if(filename.contains("output42.txt"))
		            {
		            	mz.validateGameData(filename,file.getParent());
		            }
				}
	        }
	    }
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
	    File[] files = new File("E:\\PhD\\My Data\\01.Randa Rasanga").listFiles();
	    showFiles(files,1);
	}

}
