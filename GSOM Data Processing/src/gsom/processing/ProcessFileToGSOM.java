/**
 * 
 */
package gsom.processing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Manjusri
 *
 */
public class ProcessFileToGSOM {

	String LINES_TO_WRITE = "";
	private int counter = 0;
	
	/**
	 * 
	 */
	public ProcessFileToGSOM()
	{
		// TODO Auto-generated constructor stub
	}
	
	public void readFromFile(String fileName, String parent, int mode)
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
						
			if(mode==0)
			{
				LINES_TO_WRITE="Point\t1\t2\t3\t4\t5\t6\t7\t8\t9\tRight\tLeft\tUp\tDown\t\r\n";
			}
			else
			{
				//LINES_TO_WRITE= "Point\tNumber\tType\tDecision\t\r\n";
				LINES_TO_WRITE= "Point\tZero\tNumber\tType\t\r\n";
			}
			
			while((strLine = br.readLine()) != null)
			{
				tempString = "";
				
				if(!strLine.contains("####") && !(strLine.length() == 0))
				{
					counter++;
					
					temp = strLine.split(" ");
					line_number = temp[0];
					junction_type = Integer.parseInt(temp[1]);
					junction_number = Integer.parseInt(temp[2]);
					decision = temp[3];
					decision_number = returnDecsionInt(decision);
					
					tempString = "Move"+counter+"_"+junction_type+"_"+decision_number+"\t" + returnTempString(line_number,junction_number,junction_type, decision_number, mode);
					
					
					LINES_TO_WRITE += tempString + "\r\n";
				}
				else
				{
					LINES_TO_WRITE += strLine+"\r\n";
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
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\gsom-testRAW.txt",false));
			bw.write(line);
			bw.newLine();
			bw.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
	
	private String returnTempString(String LineNumber, int junctionNumber, int junctionType, int decisionNumber, int mode)
	{
		String tempString="";
		int minimumScale = 1;
		int maximumScale = 4;
		
		double maxJunctionNumber = 56;
		double minJunctionNumber = 1;
		double normalizedJunctionNumber = 0;
		
		double maxJunctionType = 9;
		double minJunctionType = 1;
		double normalizedJunctionType = 0;
		
		if(mode==0){
			switch (junctionType)
			{
				case 1:
					tempString += "1\t0\t0\t0\t0\t0\t0\t0\t0\t";
					break;
				case 2:
					tempString += "0\t1\t0\t0\t0\t0\t0\t0\t0\t";
					break;
				case 3:
					tempString += "0\t0\t1\t0\t0\t0\t0\t0\t0\t";
					break;
				case 4:
					tempString += "0\t0\t0\t1\t0\t0\t0\t0\t0\t";
					break;
				case 5:
					tempString += "0\t0\t0\t0\t1\t0\t0\t0\t0\t";
					break;
				case 6:
					tempString += "0\t0\t0\t0\t0\t1\t0\t0\t0\t";
					break;
				case 7:
					tempString += "0\t0\t0\t0\t0\t0\t1\t0\t0\t";
					break;
				case 8:
					tempString += "0\t0\t0\t0\t0\t0\t0\t1\t0\t";
					break;
				case 9:
					tempString += "0\t0\t0\t0\t0\t0\t0\t0\t1\t";
					break;
			}
			
			switch(decisionNumber)
			{
				case 1:
					tempString += "1\t0\t0\t0";
					break;
				case 2:
					tempString += "0\t1\t0\t0";
					break;
				case 3:
					tempString += "0\t0\t1\t0";
					break;
				case 4:
					tempString += "0\t0\t0\t1";
					break;
			}
		}
		else if(mode==1)
		{
			normalizedJunctionNumber = 1.0 + (junctionNumber - 1.0)*(3.0)/(maxJunctionNumber - minJunctionNumber);
			normalizedJunctionType = 1.0 +(junctionType - 1.0)*(3.0)/(maxJunctionType - minJunctionType);
			//Math.pow(normalizedJunctionNumber,2)+
			double vectorNorm = Math.sqrt(Math.pow(normalizedJunctionType,2)+  Math.pow(decisionNumber,2));
			//tempString += (normalizedJunctionNumber/vectorNorm) +"\t"+ (normalizedJunctionType/vectorNorm) +"\t"+ (decisionNumber/vectorNorm);
			tempString += 0+"\t"+(normalizedJunctionType/vectorNorm) +"\t"+ (decisionNumber/vectorNorm);//+"\t"+0;
		}
			
		return tempString;
	}

}
