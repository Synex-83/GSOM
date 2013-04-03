/**
 * 
 */
package clean.space;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Manjusri
 *
 */
public class SpaceCleaner {
	
	private String LINES_TO_WRITE = "";
	private int COUNTER = 0;
		
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader br = null;	
		
		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String strLine = null;
			
			
			while((strLine = br.readLine()) != null)
			{
				if(!strLine.equals("null") && !(strLine.length() == 0) )
				{
					if(strLine.length() > 15 && COUNTER == 0 && !strLine.contains("#######"))
					{
						LINES_TO_WRITE += "***************\r\n";
						COUNTER++;
					}
					else if(strLine.length() > 15 && COUNTER > 0) //<8 for walkthrough
					{
						COUNTER++;
					}
					else 
					{
						COUNTER = 0;
						LINES_TO_WRITE += strLine + "\r\n";						
						//System.out.println(strLine);
					}
				}
			}
			
			writeToFile(LINES_TO_WRITE,parent);
	
		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(br != null)
					br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\alpha2.txt",false));
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
