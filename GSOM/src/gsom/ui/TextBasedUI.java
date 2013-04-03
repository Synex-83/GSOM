/**
 * 
 */
package gsom.ui;

import gsom.core.Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @File 	TextBasedUI.java
 * @author 	Manjusri Ishwara
 * @date   	Aug 18, 2012 - 7:54:51 PM
 * @package	gsom.ui
 */
public class TextBasedUI {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map gsom = new Map();

		int selectedOption = 0;
		double secondaryValue = 0;
		
		while(true)
		{
			System.out.println("******************************************");
			System.out.println("*             SELECT OPTION              *");
			System.out.println("******************************************");
			System.out.println("\t1. Set Input Dimensions");
			System.out.println("\t2. Initialize");
			System.out.println("\t3. Train");
			System.out.println("\t4. Exit");
			
			System.out.print("\n");
			System.out.print("Enter Selection : ");
			
			selectedOption = (int)readUserInput();
					
			if(selectedOption == 1)
			{
				System.out.print("Enter Input Dimensions : ");
				
				secondaryValue = readUserInput();
				
				if( secondaryValue != -1)
					gsom.setInputDimension((int)secondaryValue);
			}
			else if(selectedOption == 2)
			{
				System.out.print("Enter Input Dimensions : ");
				
				secondaryValue = readUserInput();
				
				if( secondaryValue != -1)
					gsom.setInputDimension((int)secondaryValue);
				
				System.out.print("Enter Spread Factor (should be between 0 and 1 inclusive): ");
				
				secondaryValue = readUserInput();
				
				if( secondaryValue != -1 && secondaryValue >=0 && secondaryValue <= 1)
					gsom.setGrowthThreshold(secondaryValue);

				gsom.setWeights();
			}
			else if(selectedOption == 3)
			{
				//gsom.train();
				gsom.trainFromFile("tab.txt");
			}
			else if(selectedOption == 4)
			{
				System.exit(0);
			}
				
		}
		
	}
	
	/**
	 * Reads the input user enters on the command line and returns them as an integer.
	 * @return
	 * Integer value of the value inserted by the user in the command line. 
	 */
	public static double readUserInput()
	{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		
		try
		{
			return Double.parseDouble(br.readLine());
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage() + " " + e.getLocalizedMessage());
			System.out.println("Please Enter a Number.");
			return -1;
			
		}
			
	}

}
