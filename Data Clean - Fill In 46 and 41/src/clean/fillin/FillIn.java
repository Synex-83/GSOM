/**
 * 
 */
package clean.fillin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Manjusri
 *
 */
public class FillIn {
	
	private String LINES_TO_WRITE = "";
	private int COUNTER = 0;
	private int counter1 = 0;
		
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader br = null;	
		String strLine = null;

		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String prevLine = null;
			int junctionInt2 = 0;
			int junctionInt1 = 0;
			
			
			while((strLine = br.readLine()) != null)
			{
				if(strLine.equals("***************"))
				{
					COUNTER = 1;
				}
				else if(COUNTER == 1)
				{
					String junction[] = strLine.split(" ");
					//System.out.println(strLine);
					
					if(strLine.contains("#####"))
					{
						junctionInt1 = Integer.parseInt(prevLine.split(" ")[2]);
						
						switch (junctionInt1) {
						case 42:
							LINES_TO_WRITE += "23 1 041 Down" + "\r\n" + strLine + "\r\n";
							break;
							
						case 51:
							LINES_TO_WRITE += "23 1 041 Right" + "\r\n" + strLine + "\r\n";
							break;
							
						case 36:
							LINES_TO_WRITE += "26 3 046 Right" + "\r\n" + strLine + "\r\n";
							break;
						
						case 47:
							LINES_TO_WRITE += "26 3 046 Up" + "\r\n" + strLine + "\r\n";
							break;
							

						default:
							break;
						}
					}
					else
					{
						junctionInt2 = Integer.parseInt(junction[2]);
						
						switch (junctionInt2) {
						case 42:
							LINES_TO_WRITE += "23 1 041 Right" + "\r\n" + strLine + "\r\n";
							break;
							
						case 51:
							LINES_TO_WRITE += "23 1 041 Down" + "\r\n" + strLine + "\r\n";
							break;
							
						case 36:
							LINES_TO_WRITE += "26 3 046 Up" + "\r\n" + strLine + "\r\n";
							break;
						
						case 47:
							LINES_TO_WRITE += "26 3 046 Right" + "\r\n" + strLine + "\r\n";
							break;
							

						default:
							break;
						}
					}


					COUNTER = 0;
				}
				else
				{
					prevLine = strLine;
					LINES_TO_WRITE += strLine + "\r\n";
				}
				counter1++;
			}
			
			writeToFile(LINES_TO_WRITE, parent);
	
		}
		catch(Exception e)
		{
			System.out.println(strLine);
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
				System.out.println(counter1);
				ex.printStackTrace();
			}
		}
	}
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\alpha3.txt",false));
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
