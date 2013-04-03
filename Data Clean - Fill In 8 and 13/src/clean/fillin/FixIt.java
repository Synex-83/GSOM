/**
 * 
 */
package clean.fillin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

//import validation.maze.Maze;

/**
 * @author Manjusri
 *
 */
public class FixIt {

	private String LINES_TO_WRITE = "";
	
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader br = null;	
		
		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String strLine = null;
			String strLine2 = null;
			String[] readMove = null;
			String[] readMove2 = null;
			
			while((strLine = br.readLine()) != null)
			{
				if(!strLine.contains("####"))
				{					
					readMove = strLine.split(" ");
					
					if(readMove[0].equals("05"))
					{
						if(((strLine2=br.readLine()) != null) &&  !strLine2.contains("####"))
						{
							readMove2 = strLine2.split(" ");
							
							if(readMove2[0].equals("05"))
							{
								LINES_TO_WRITE += process(readMove,readMove2) + strLine2 + "\r\n";
							}
							else
							{
								LINES_TO_WRITE += strLine + "\r\n" + strLine2 + "\r\n";
							}
						}
						else
						{
							LINES_TO_WRITE += strLine + "\r\n";
						}
					}
					else
					{
						LINES_TO_WRITE += strLine + "\r\n";
					}
				}
				else
				{
					LINES_TO_WRITE += strLine + "\r\n";
					
					writeToFile(LINES_TO_WRITE, parent);
					//LINES_TO_WRITE = "";
					//check one or two variables not empty case.
				}
			}

		}
		catch(IOException e)
		{
			
			e.printStackTrace();
		}
		finally
		{
			try
			{
				writeToFile(LINES_TO_WRITE, parent);
				if(br != null)
					br.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	private String process(String[] a, String[] b)
	{
		String retString = "";
		String juncTypes[] = {"7","9","5","6","6","5","9","8"};
		String juncNumbers[] = {"007","008","009","010","011","012","013","014"};
		int first = Integer.parseInt(a[2]); 
		int second = Integer.parseInt(b[2]);
		
		for(int i = 0; i < Math.abs((Integer.parseInt(a[2]) - Integer.parseInt(b[2]))); i++)
		{
			if( (first - second )> 0)
			{
				retString += "05 " + juncTypes[first - 7 - i] + " "  + juncNumbers[first - 7 - i]+ " Left" + "\r\n";
			}
			else
			{
				retString += "05 " + juncTypes[first - 7 + i] + " "  + juncNumbers[first - 7 + i]+ " Right" + "\r\n";
			}
		}
		
		return retString;
	}

	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\alpha4.txt",false));
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
