import java.io.File;

/**
 * 
 */

/**
 * @author Manjusri Ishwara
 * This class provides methods to remove all auxilery files created by the cleaning process
 */
public class CleanFolder {

	public static void showFiles(File[] files,int Option) {
		String filename = "";
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
