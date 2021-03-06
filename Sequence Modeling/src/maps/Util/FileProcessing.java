/**
 * @package		maps.Util
 * @filename	FileProcessing.java
 */
package maps.Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import maps.Structures.Edge;
import maps.Structures.FSMNode;
import maps.Structures.Node;

/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:49:41 PM
 * @type		FileProcessing
 */
public class FileProcessing {
	
	private String FILE_LOCATION =  null;
	private String DELIMETER = " ";
	private BufferedReader BR = null;
	private String FILE_STRING = null;
	
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
	 * @return
	 */
	public String getFileName()
	{
		return FILE_LOCATION;	
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

				e.printStackTrace();
			}
		}
		
		return (dimension - 2); // -1 for only the vector number -2 for vector number and the label
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
	
	
	public String readFileContinuous()
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
	
	
	public String readFileLine()
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
	
	public void writeFile()
	{
		FILE_STRING = readFileLine();
		
		FileWriter write = null;
		
		try
		{

			write = new FileWriter("Inoneline-d38112.txt",true);
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(FILE_STRING);
			writer.newLine();
			writer.flush();
			write.close();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			System.out.println("DONE");
		}
	}
}

