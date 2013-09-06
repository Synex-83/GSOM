/**
 * @package		data.probabilites
 * @filename	ProcessFileToProbabilites.java
 */
package data.probabilites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 		Manjusri Ishwara
 * @date   		Sep 7, 2013 - 12:25:21 AM
 * @type		ProcessFileToProbabilites
 */
public class ProcessFileToProbabilites {

	private int TYPE_ONE = 0;
	private int TYPE_TWO = 0;
	private int TYPE_THREE = 0;
	private int TYPE_FOUR = 0;
	private int TYPE_FIVE = 0;
	private int TYPE_SIX = 0;
	private int TYPE_SEVEN = 0;
	private int TYPE_EIGHT = 0;
	private int TYPE_NINE = 0;
	
	private int COUNTER = 0;
	
	String LINES_TO_WRITE = "";
	
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader br = null;	
		String line_number = "";
		int junction_type = 0;
		int junction_number = 0;
		int decision_number = 0;
		String tempString = "";
		String decision = "";
		String temp[];
			
		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String strLine = null;

			LINES_TO_WRITE="GAME\t1\t2\t3\t4\t5\t6\t7\t8\t9\t\r\n";

			while((strLine = br.readLine()) != null)
			{
				tempString = "";
				
				if(!strLine.contains("####") && !(strLine.length() == 0))
				{
					temp = strLine.split(" ");
					line_number = temp[0];
					junction_type = Integer.parseInt(temp[1]);
					junction_number = Integer.parseInt(temp[2]);
					decision = temp[3];
					decision_number = returnDecsionInt(decision);		
					incrementTypeCounter(junction_type);
					

				}
				else if(strLine.contains("DONE"))
				{
					COUNTER++;
					
					tempString = "GAME"+COUNTER+"\t"+TYPE_ONE+"\t"+TYPE_TWO+"\t"+TYPE_THREE+"\t"+TYPE_FOUR+"\t"+ 
					TYPE_FIVE+"\t"+TYPE_SIX+"\t"+TYPE_SEVEN+"\t"+TYPE_EIGHT+"\t"+TYPE_NINE+"\t";
					
					
					LINES_TO_WRITE += tempString + "\r\n";
					
					TYPE_ONE = 0;
					TYPE_TWO = 0;
					TYPE_THREE = 0;
					TYPE_FOUR = 0;
					TYPE_FIVE = 0;
					TYPE_SIX = 0;
					TYPE_SEVEN = 0;
					TYPE_EIGHT = 0;
					TYPE_NINE = 0;
				}
			}
			
			writeToFile(LINES_TO_WRITE, parent);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println(line_number + " " + junction_type + " " + junction_number + " " + decision);
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
	
	/**
	 * @param junction_type
	 */
	private void incrementTypeCounter(int junction_type) {
		
		switch(junction_type)
		{
			case 1:
				TYPE_ONE++;
				break;
				
			case 2:
				TYPE_TWO++;
				break;
				
			case 3:
				TYPE_THREE++;
				break;
				
			case 4:
				TYPE_FOUR++;
				break;
				
			case 5:
				TYPE_FIVE++;
				break;
				
			case 6:
				TYPE_SIX++;
				break;
				
			case 7:
				TYPE_SEVEN++;
				break;
				
			case 8:
				TYPE_EIGHT++;
				break;
				
			case 9:
				TYPE_NINE++;
				break;
		}
		
	}

	private int returnDecsionInt(String value)
	{
		int value1 = 0;
		
		switch (value) 
		{
        	case "Right":
        		value1 =  1;
        		break;
                
        	case "Left":
        		value1 = 2;
        		break;
        		
        	case "Up":
        		value1 = 3;
        		break; 	
        		
        	case "Down": 
        		value1 = 4;
        		break;
        		                      
		}	
		return value1;
	}
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\NUMBER-TEST.txt",false));
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		ProcessFileToProbabilites pft = new ProcessFileToProbabilites();
		pft.readFromFile("output4.txt", "E:\\PhD\\My Data\\01.Randa Rasanga");
	}
	
}
