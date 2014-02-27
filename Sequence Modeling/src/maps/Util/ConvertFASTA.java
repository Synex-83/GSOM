/**
 * 
 */
package maps.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 28, 2013 - 6:24:24 PM
 * @type        ConvertFASTA
 *
 */
public class ConvertFASTA {
	private static String FILE_LOCATION =  "C:\\Users\\Manjusri\\Desktop\\mtDNA\\gray seal - X72004.txt";
	private String DELIMETER = " ";
	private static BufferedReader BR = null;
	static FileWriter write = null;
	static BufferedWriter writer = null;
	
	public static void readFile(File fileToRead)
	{
		String temp = "";
		ArrayList<String>	letters = new ArrayList<String>();
		
		try
		{
			FileReader file = new FileReader(fileToRead);
			BR = new BufferedReader(file);
			String strLine = null;
			write = new FileWriter(fileToRead.getParent() + "\\" + fileToRead.getName() +"-Convert.txt" ,true);
			writer = new BufferedWriter(write);

			while((strLine = BR.readLine()) != null)
			{
				temp = strLine;
				
				for(int i =0; i <temp.length(); i++)
				{
					writer.write(temp.charAt(i));
					writer.newLine();
					
					
					if(!letters.contains(temp.charAt(i)+""))
					{
						
						letters.add(temp.charAt(i)+"");
					}
				}			
				
			}
			
			writer.close();
		}
		catch(Exception e)
		{
			
		}
		finally{
			System.out.println("DONE");
			
			Iterator<String> ite = letters.iterator();
			
			while(ite.hasNext())
			{
				String temp1 = ite.next().toString();
				System.out.print(temp1 + " ");
				
				if(temp1.equalsIgnoreCase("U") || temp1.equalsIgnoreCase("O") || temp1.equalsIgnoreCase("B") ||
						temp1.equalsIgnoreCase("Z") || temp1.equalsIgnoreCase("J") || temp1.equalsIgnoreCase("X"))
				{
					System.out.println("Unwanted Character = " + temp1);
				}
			}
			
			System.out.println();
			System.out.println("FILE = " + fileToRead.getName());
			System.out.println("UNIQUE CHARS = " + letters.size());
		}
		//return temp;
	}
	

	
	public static void showFiles(File[] files) {
		String filename = "";

		
	    for (File file : files) {
	        if (file.isDirectory()) {
	            showFiles(file.listFiles()); // Calls same method again.
	        } else {
	        	filename = file.getName();
	        	readFile(file);
	        }
	    }
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
	    File[] files = new File("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA").listFiles();
	    showFiles(files);
	}
	
	
	
}
