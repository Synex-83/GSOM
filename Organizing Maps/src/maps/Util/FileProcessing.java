/**
 * @package		maps.Util
 * @filename	FileProcessing.java
 */
package maps.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 31, 2013 - 2:21:18 AM
 * @type		FileProcessing
 */
public class FileProcessing {
	
	private String FILE_LOCATION =  null;
	private String DELIMETER = " ";
	private BufferedReader BR = null;
	
	/**
	 * @param filename as the input file 
	 * @param option as the delimiter (0 = space, 1 = tab, 2 = comma)
	 */
	public FileProcessing(String filename, int option)
	{
		FILE_LOCATION = filename;
		
		if(option == 0)
		{
			DELIMETER = " ";
		}
		else if(option == 1)
		{
			DELIMETER = "\t";
		}
		else if(option == 2)
		{
			DELIMETER = ",";
		}
		
	}
	
	/**
	 * @return the input dimension of the input data
	 */
	public int getDataDimension()
	{
		int dimension = 0;
		
		try
		{
			FileReader file = new FileReader(FILE_LOCATION);
			BR =new BufferedReader(file);
			
			StringTokenizer st = new StringTokenizer(BR.readLine(),DELIMETER);
			
			while(st.hasMoreTokens())
			{
				st.nextToken();
				dimension++;
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				BR.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return (dimension - 1);
	}
	
	/**
	 * @return the complete contents of the input file as a string
	 */
	public String readFile()
	{
		String temp = "";
	
		try
		{
			FileReader file = new FileReader(FILE_LOCATION);
			BR = new BufferedReader(file);
			String strLine = null;

			while((strLine = BR.readLine()) != null)
			{
				temp += strLine + "\n";
			}
		}
		catch(Exception e)
		{
			
		}
		
		return temp;
	}
}
