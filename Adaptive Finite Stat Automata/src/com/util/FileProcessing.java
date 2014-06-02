/**
 * 
 */
package com.util;

import java.io.BufferedReader;
import java.io.FileReader;


/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 4:36:37 PM
 * @package com.util
 * 
 */
public class FileProcessing {
	
	private String FILE_LOCATION =  null;
	private BufferedReader BR = null;

	/**
	 * @param filename as the input file 
	 */
	public FileProcessing(String filename)
	{
		FILE_LOCATION = filename;		
	}
	
	/**
	 * @return
	 */
	public String getFileName()
	{
		return FILE_LOCATION;	
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
				temp += strLine;
			}
		}
		catch(Exception e)
		{
			
		}

		return temp;
	}
}
