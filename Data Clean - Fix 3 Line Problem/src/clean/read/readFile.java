/**
 * 
 */
package clean.read;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author User
 *
 */
public class readFile {
	
	private String LINES_TO_WRITE = "";
	private int COUNTER = 0;
	private String ALPHA = null;
	private String BETA = null;
	private String DELTA = null;
	private String[] alpha = null;
	
	public readFile()
	{
		
	}
	
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
				if(!strLine.contains("####"))
				{
					COUNTER++;			
					
					if(COUNTER%3 == 1)
					{
						ALPHA = strLine;
					}
					else if(COUNTER%3 == 2)
					{
						BETA = strLine;
					}
					else if(COUNTER%3 == 0){
						DELTA = strLine;
					}
					
					
					if(COUNTER >= 3)
					{
						process();
					}
				}
				else
				{
					alpha = strLine.split(" ");
					
					if(strLine.contains("Board") || strLine.contains("OVER"))
					{
						LINES_TO_WRITE += ALPHA + "\r\n" + BETA + "\r\n" + DELTA+ "\r\n" + alpha[0] +" " + alpha[1] + " DONE" + "\r\n";
					}
					else
					{
						LINES_TO_WRITE += ALPHA + "\r\n" + BETA + "\r\n" + DELTA+ "\r\n" + alpha[0] + "\r\n";
					}
					
					ALPHA = "";
					BETA = "";
					DELTA = "";
					COUNTER = 0;
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

	private void process()
	{

		if(ALPHA.length() < 8 || BETA.length() < 8 || DELTA.length() < 8)
		{
			if(ALPHA.length() < 8)
			{
				LINES_TO_WRITE += ALPHA + "\r\n";
				ALPHA = BETA;
				BETA =DELTA;
				DELTA = "";
				COUNTER--;
			}
			else if(BETA.length() < 8)
			{
				LINES_TO_WRITE += ALPHA + "\r\n" + BETA + "\r\n";
				ALPHA = DELTA;
				BETA = "";
				DELTA = "";
				COUNTER -= 2;
			}
			else if(DELTA.length() < 8)
			{
				LINES_TO_WRITE += ALPHA + "\r\n" + BETA + "\r\n" + DELTA + "\r\n";
				ALPHA = "";
				BETA = "";
				DELTA = "";
				//alpha = beta;
				//beta =delta;
				//COUNTER--;
			}
		}
		else
		{
			if(ALPHA.substring(0,8).equals(BETA.substring(0,8)) && BETA.substring(0,8).equals(DELTA.substring(0,8)))
			{
				//write delta to file
				//writeToFile(delta);
				LINES_TO_WRITE += DELTA + "\r\n";
				ALPHA = "";
				BETA = "";
				DELTA = "";
				COUNTER = 0;
			}
			else if(ALPHA.substring(0,8).equals(BETA.substring(0,8)) && !(BETA.substring(0,8).equals(DELTA.substring(0,8))))
			{
				LINES_TO_WRITE += BETA+ "\r\n"; //removed ALPHA check for side effects
				ALPHA = DELTA;
				BETA = "";
				DELTA = "";
				COUNTER -= 2;
			}
			else if(!ALPHA.equals(BETA))
			{
				LINES_TO_WRITE += ALPHA + "\r\n";
				ALPHA = BETA;
				BETA = DELTA;
				DELTA = "";
				COUNTER--;
			}
		}	
	}

	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\alpha1.txt",false));
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

