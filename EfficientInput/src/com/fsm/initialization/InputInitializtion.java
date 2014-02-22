/**
 * @package		com.fsm.initialization
 * @filename	InputInitializtion.java
 */
package com.fsm.initialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * @author 		Manjusri Ishwara
 * @date   		Feb 23, 2014 - 12:42:49 AM
 * @type		InputInitializtion
 */
public class InputInitializtion {

	private String FILE_LOCATION =  null;
	private BufferedReader BR = null;
	private Map<String, double[]> template = new HashMap<String, double[]>(5);
	private Map<String, Array2DRowRealMatrix > matrices = new HashMap<String, Array2DRowRealMatrix>();
	
	/**
	 * @param fileLocation
	 */
	public InputInitializtion(String fileLocation)
	{
		FILE_LOCATION = fileLocation;
		
		template.put("A", new double[]{1,0,0,0});
		template.put("B", new double[]{0,1,0,0});
		template.put("C", new double[]{0,0,1,0});
		template.put("D", new double[]{0,0,0,1});
		template.put("X", new double[]{0,0,0,0});
	}
		
	/**
	 * @return
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
	
	/**
	 * 
	 */
	public void processFile()
	{
		String temp = "XXX";
		
		StringTokenizer tokens = new StringTokenizer(temp,"\n");
		Array2DRowRealMatrix realData = new Array2DRowRealMatrix(4, 3);
		
		try
		{
			FileReader file = new FileReader(FILE_LOCATION);
			BR = new BufferedReader(file);
			String strLine = null;
			BR.readLine();
			while((strLine = BR.readLine()) != null)
			{
				temp = temp.substring(1).concat(strLine.split("\t")[1]);

				if(!temp.contains("X") && !matrices.containsKey(temp))
				{
					for(int i = 0; i < temp.length(); i++)
					{
						realData.setColumn(i, template.get(Character.toString(temp.charAt(i))));
					}
					
					matrices.put(temp, realData);
					System.out.println("+++++++++++++++++++++++ Length " + matrices.size() + " " + temp );
					System.out.println(realData.toString());
					
					//send the matrices map and the file to SOM
				}
				else if(temp.contains("X"))
				{
					System.out.println(temp);
				}
				else
				{
					System.out.println("The Collection already contains " + temp);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
