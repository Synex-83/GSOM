/**
 * 
 */
package com.convert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author 		Manjusri Ishwara
 * @date		Feb 18, 2014 - 10:27:49 PM
 * @type        Convert
 *
 */
public class Convert {

	
	public void readFromFile(String fileName, String parent)
	{

		BufferedReader br = null;	
		String temp[];
		String LINE_TO_WRITE = "";
		int counter = 0;
		int skip_counter = 0;
		int player_counter = 0;
		
		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String strLine = null;
			LINE_TO_WRITE = "Counter\tLabel\t1\t2\t3\t4\t5\r\n";
			
			
			while((strLine = br.readLine()) != null)
			{
				temp = strLine.split(" ");
				
				for(int i = 0; i < temp.length; i++)
				{
					counter++;					
					LINE_TO_WRITE += "CO_"+counter+"\t"+convertToVector(Integer.parseInt(temp[i]));
					skip_counter++;					
				}
				
				if(skip_counter >= 30 && skip_counter <= 50)
				{
					LINE_TO_WRITE += "XXX\tX\t0\t0\t0\t0\t0\r\n";
					System.out.println(counter);
					writeToFile(LINE_TO_WRITE, parent);
					LINE_TO_WRITE = "";
					skip_counter = 0;
					player_counter++;
				}
				else
				{
					counter =  counter - skip_counter;
					skip_counter=0;
					LINE_TO_WRITE = "";
					System.out.println("*************************" + counter);
				}
					
			}
			
			System.out.println("Player =" + player_counter);

		}
		catch(Exception e)
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
	
	private String convertToVector(int number)
	{
		switch (number) {
		case 1:
			return "A\t0\t0\t0\t0\t1\r\n";
		
		case 2:
			return "B\t0\t0\t0\t1\t0\r\n";
		
		case 3:
			return "C\t0\t0\t0\t1\t1\r\n";
		
		case 4:
			return "D\t0\t0\t1\t0\t0\r\n";	
		
		case 5:
			return "E\t0\t0\t1\t0\t1\r\n";
		
		case 6:
			return "F\t0\t0\t1\t1\t0\r\n";		
		
		case 7:
			return "G\t0\t0\t1\t1\t1\r\n";
		
		case 8:
			return "H\t0\t1\t0\t0\t0\r\n";
		
		case 9:
			return "I\t0\t1\t0\t0\t1\r\n";
		
		case 10:
			return "J\t0\t1\t0\t1\t0\r\n";
		
		case 11:
			return "K\t0\t1\t0\t1\t1\r\n";
		
		case 12:
			return "L\t0\t1\t1\t0\t0\r\n";
		
		case 13:
			return "M\t0\t1\t1\t0\t1\r\n";
		
		case 14:
			return "N\t0\t1\t1\t1\t0\r\n";
		
		case 15:
			return "O\t0\t1\t1\t1\t1\r\n";
		
		case 16:
			return "P\t1\t0\t0\t0\t0\r\n";
		
		case 17:
			return "Q\t1\t0\t0\t0\t1\r\n";
		
		default:
			break;
		}
		
		return null;
	}
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\GSOM-WALK.txt",true));
			bw.write(line);
			//bw.newLine();
			bw.flush();
		//	System.out.println("DONE");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Convert c = new Convert();
		c.readFromFile("msnbc990928.seq","C:\\Users\\User\\Downloads\\MSNBC");

	}

}
