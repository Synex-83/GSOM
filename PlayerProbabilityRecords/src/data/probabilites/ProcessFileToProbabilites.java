/**
 * @package		data.probabilites
 * @filename	ProcessFileToProbabilites.java
 */
package data.probabilites;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	
	private int TYPE_ONE_RIGHT = 0;
	private int TYPE_ONE_DOWN= 0;
	private int TYPE_TWO_LEFT = 0;
	private int TYPE_TWO_DOWN = 0;
	private int TYPE_THREE_RIGHT = 0;
	private int TYPE_THREE_UP = 0;
	private int TYPE_FOUR_UP = 0;
	private int TYPE_FOUR_LEFT = 0;
	private int TYPE_FIVE_RIGHT = 0;
	private int TYPE_FIVE_LEFT = 0;
	private int TYPE_FIVE_DOWN = 0;
	private int TYPE_SIX_RIGHT = 0;
	private int TYPE_SIX_LEFT = 0;
	private int TYPE_SIX_UP = 0;
	private int TYPE_SEVEN_UP = 0;
	private int TYPE_SEVEN_DOWN = 0;
	private int TYPE_SEVEN_RIGHT = 0;
	private int TYPE_EIGHT_UP = 0;
	private int TYPE_EIGHT_DOWN = 0;
	private int TYPE_EIGHT_LEFT = 0;
	private int TYPE_NINE_RIGHT = 0;
	private int TYPE_NINE_LEFT = 0;
	private int TYPE_NINE_UP=0;
	private int TYPE_NINE_DOWN = 0;
	
	private int COUNTER = 0;
	private int NUMBER_OF_MOVES = 0;
	
	String LINES_TO_WRITE = "";
	
	/**
	 * @param fileName
	 * @param parent
	 * @param mode
	 */
	public void readFromFile(String fileName, String parent, int mode)
	{

		BufferedReader br = null;	
		String line_number = "";
		int junction_type = 0;
		int junction_number = 0;
		//int decision_number = 0;
		String tempString = "";
		String decision = "";
		String temp[];
			
		try
		{
			FileReader file = new FileReader(parent + "\\" +fileName);
			br = new BufferedReader(file);
			String strLine = null;
			
			if(mode == 1){
				LINES_TO_WRITE="GAME\t1\t2\t3\t4\t5\t6\t7\t8\t9\t\r\n";
			}else if(mode==2){
				LINES_TO_WRITE="GAME\t1R\t1D\t2L\t2D\t3U\t3R\t4L\t4U\t5R\t5L\t5D\t6R\t6L\t6U\t7U\t" +
						"7D\t7R\t8U\t8D\t8L\t9R\t9L\t9D\t9U\t\r\n";
			}else if(mode==3){
				LINES_TO_WRITE="GAME\t1R\t1D\t2L\t2D\t3U\t3R\t4L\t4U\t5R\t5L\t5D\t6R\t6L\t6U\t7U\t" +
						"7D\t7R\t8U\t8D\t8L\t9R\t9L\t9D\t9U\t\r\n";
			}

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
					//decision_number = returnDecsionInt(decision);		
					incrementTypeCounter(junction_type);
					incrementDecisionTypeCounter(junction_type,decision);
					
					NUMBER_OF_MOVES++;
				}
				else if(strLine.contains("DONE"))
				{
					COUNTER++;
					
					if(mode ==1){
						tempString = "GAME"+COUNTER+"\t"+TYPE_ONE+"\t"+TYPE_TWO+"\t"+TYPE_THREE+"\t"+TYPE_FOUR+"\t"+ 
								TYPE_FIVE+"\t"+TYPE_SIX+"\t"+TYPE_SEVEN+"\t"+TYPE_EIGHT+"\t"+TYPE_NINE+"\t";
					}else if(mode==2){
						tempString = "GAME"+COUNTER+"\t"+(float)TYPE_ONE_RIGHT/NUMBER_OF_MOVES+"\t"+ (float)TYPE_ONE_DOWN/NUMBER_OF_MOVES +
								"\t"+(float)TYPE_TWO_LEFT/NUMBER_OF_MOVES+"\t"+(float)TYPE_TWO_DOWN/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_THREE_UP/NUMBER_OF_MOVES+"\t"+(float)TYPE_THREE_RIGHT/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_FOUR_LEFT/NUMBER_OF_MOVES+"\t"+(float)TYPE_FOUR_UP/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_FIVE_RIGHT/NUMBER_OF_MOVES+"\t"+(float)TYPE_FIVE_LEFT/NUMBER_OF_MOVES+"\t"+(float)TYPE_FIVE_DOWN/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_SIX_RIGHT/NUMBER_OF_MOVES+"\t"+(float)TYPE_SIX_LEFT/NUMBER_OF_MOVES+"\t"+(float)TYPE_SIX_UP/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_SEVEN_DOWN/NUMBER_OF_MOVES+"\t"+(float)TYPE_SEVEN_UP/NUMBER_OF_MOVES+"\t"+(float)TYPE_SEVEN_RIGHT/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_EIGHT_DOWN/NUMBER_OF_MOVES+"\t"+(float)TYPE_EIGHT_UP/NUMBER_OF_MOVES+"\t"+(float)TYPE_EIGHT_LEFT/NUMBER_OF_MOVES+
								"\t"+(float)TYPE_NINE_RIGHT/NUMBER_OF_MOVES+"\t"+(float)TYPE_NINE_LEFT/NUMBER_OF_MOVES+"\t"+(float)TYPE_NINE_UP/NUMBER_OF_MOVES+"\t"+(float)TYPE_NINE_DOWN/NUMBER_OF_MOVES+"\t";
					}else if(mode==3){
						tempString = "GAME"+COUNTER+"\t"+(float)TYPE_ONE_RIGHT/TYPE_ONE+"\t"+ (float)TYPE_ONE_DOWN/TYPE_ONE +
								"\t"+(float)TYPE_TWO_LEFT/TYPE_TWO+"\t"+(float)TYPE_TWO_DOWN/TYPE_TWO+
								"\t"+(float)TYPE_THREE_UP/TYPE_THREE+"\t"+(float)TYPE_THREE_RIGHT/TYPE_THREE+
								"\t"+(float)TYPE_FOUR_LEFT/TYPE_FOUR+"\t"+(float)TYPE_FOUR_UP/TYPE_FOUR+
								"\t"+(float)TYPE_FIVE_RIGHT/TYPE_FIVE+"\t"+(float)TYPE_FIVE_LEFT/TYPE_FIVE+"\t"+(float)TYPE_FIVE_DOWN/TYPE_FIVE+
								"\t"+(float)TYPE_SIX_RIGHT/TYPE_SIX+"\t"+(float)TYPE_SIX_LEFT/TYPE_SIX+"\t"+(float)TYPE_SIX_UP/TYPE_SIX+
								"\t"+(float)TYPE_SEVEN_DOWN/TYPE_SEVEN+"\t"+(float)TYPE_SEVEN_UP/TYPE_SEVEN+"\t"+(float)TYPE_SEVEN_RIGHT/TYPE_SEVEN+
								"\t"+(float)TYPE_EIGHT_DOWN/TYPE_EIGHT+"\t"+(float)TYPE_EIGHT_UP/TYPE_EIGHT+"\t"+(float)TYPE_EIGHT_LEFT/TYPE_EIGHT+
								"\t"+(float)TYPE_NINE_RIGHT/TYPE_NINE+"\t"+(float)TYPE_NINE_LEFT/TYPE_NINE+"\t"+(float)TYPE_NINE_UP/TYPE_NINE+"\t"+(float)TYPE_NINE_DOWN/TYPE_NINE+"\t";
					}
					LINES_TO_WRITE += tempString + "\r\n";					
					resetVariables();
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
	 * @param decision
	 */
	private void incrementDecisionTypeCounter(int junction_type, String decision) {
		// TODO Auto-generated method stub
		if (junction_type == 1) {
			if(decision.equalsIgnoreCase("Right")){
				TYPE_ONE_RIGHT++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_ONE_DOWN++;
			}		
		} else if (junction_type == 2) {
			if(decision.equalsIgnoreCase("Left")){
				TYPE_TWO_LEFT++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_TWO_DOWN++;
			}	
		} else if (junction_type == 3) {
			if(decision.equalsIgnoreCase("Right")){
				TYPE_THREE_RIGHT++;
			} else if(decision.equalsIgnoreCase("Up")){
				TYPE_THREE_UP++;
			}	
		} else if (junction_type == 4) {
			if(decision.equalsIgnoreCase("Left")){
				TYPE_FOUR_LEFT++;
			} else if(decision.equalsIgnoreCase("Up")){
				TYPE_FOUR_UP++;
			}	
		} else if (junction_type == 5) {
			if(decision.equalsIgnoreCase("Right")){
				TYPE_FIVE_RIGHT++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_FIVE_DOWN++;
			} else if(decision.equalsIgnoreCase("Left")){
				TYPE_FIVE_LEFT++;
			}
		} else if (junction_type == 6) {
			if(decision.equalsIgnoreCase("Right")){
				TYPE_SIX_RIGHT++;
			} else if(decision.equalsIgnoreCase("Up")){
				TYPE_SIX_UP++;
			} else if(decision.equalsIgnoreCase("Left")){
				TYPE_SIX_LEFT++;
			}
		} else if (junction_type == 7) {
			if(decision.equalsIgnoreCase("Right")){
				TYPE_SEVEN_RIGHT++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_SEVEN_DOWN++;
			} else if(decision.equalsIgnoreCase("Up")){
				TYPE_SEVEN_UP++;
			}
		} else if (junction_type == 8) {
			if(decision.equalsIgnoreCase("Up")){
				TYPE_EIGHT_UP++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_EIGHT_DOWN++;
			} else if(decision.equalsIgnoreCase("Left")){
				TYPE_EIGHT_LEFT++;
			}
		} else if (junction_type == 9) {
			if(decision.equalsIgnoreCase("Left")){
				TYPE_NINE_LEFT++;
			} else if(decision.equalsIgnoreCase("Down")){
				TYPE_NINE_DOWN++;
			} else if(decision.equalsIgnoreCase("Up")){
				TYPE_NINE_UP++;
			} else if(decision.equalsIgnoreCase("Right")){
				TYPE_NINE_RIGHT++;
			}
		}
	}
		

	/**
	 * 
	 */
	private void resetVariables() {
		// TODO Auto-generated method stub
		TYPE_ONE = 0;
		TYPE_TWO = 0;
		TYPE_THREE = 0;
		TYPE_FOUR = 0;
		TYPE_FIVE = 0;
		TYPE_SIX = 0;
		TYPE_SEVEN = 0;
		TYPE_EIGHT = 0;
		TYPE_NINE = 0;
		
		TYPE_ONE_RIGHT = 0;
		TYPE_ONE_DOWN= 0;
		TYPE_TWO_LEFT = 0;
		TYPE_TWO_DOWN = 0;
		TYPE_THREE_RIGHT = 0;
		TYPE_THREE_UP = 0;
		TYPE_FOUR_UP = 0;
		TYPE_FOUR_LEFT = 0;
		TYPE_FIVE_RIGHT = 0;
		TYPE_FIVE_LEFT = 0;
		TYPE_FIVE_DOWN = 0;
		TYPE_SIX_RIGHT = 0;
		TYPE_SIX_LEFT = 0;
		TYPE_SIX_UP = 0;
		TYPE_SEVEN_UP = 0;
		TYPE_SEVEN_DOWN = 0;
		TYPE_SEVEN_RIGHT = 0;
		TYPE_EIGHT_UP = 0;
		TYPE_EIGHT_DOWN = 0;
		TYPE_EIGHT_LEFT = 0;
		TYPE_NINE_RIGHT = 0;
		TYPE_NINE_LEFT = 0;
		TYPE_NINE_UP=0;
		TYPE_NINE_DOWN = 0;
		
		NUMBER_OF_MOVES = 0;
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

/*	private int returnDecsionInt(String value)
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
	}*/
	
	private void writeToFile(String line, String parent)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter(parent + "\\NUMBER-TEST-3.txt",false));
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
		pft.readFromFile("output4.txt", "E:\\PhD\\My Data\\01.Randa Rasanga",3);
	}
	
}
