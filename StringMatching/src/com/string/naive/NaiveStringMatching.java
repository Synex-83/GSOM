/**
 * 
 */
package com.string.naive;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 		Manjusri Ishwara
 * @date		Jan 26, 2014 - 5:49:33 PM
 * @type        NaiveStringMatching
 *
 */
public class NaiveStringMatching 
{

	private String INPUT_STRING = "null";
	private String PATTERN = "null";
	private char[] INPUT_ARRAY = null;
	private char[] PATTERN_ARRAY = null;
	private int COUNTER = 0;
	
	public NaiveStringMatching(String input, String pattern)
	{
		INPUT_STRING = input;
		PATTERN = pattern;
		
	}
	
	public void count()
	{
		INPUT_ARRAY = INPUT_STRING.toCharArray();
		PATTERN_ARRAY = PATTERN.toCharArray();
		int loop = INPUT_ARRAY.length - PATTERN_ARRAY.length;
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		Iterator<Integer> ait = a.iterator();
		
		for(int i = 0; i < loop ; i++)
		{
			for(int j = 0; j < PATTERN_ARRAY.length; j++)
			{
				if( INPUT_ARRAY[i+j] == PATTERN_ARRAY[j])
				{
					//continue;
				}
				else
				{
					break;
				}
				
				if(j == PATTERN_ARRAY.length - 1 )
				{
					COUNTER++;
				//	a.add(i);
					System.out.print(i+ " ");
				}
			}			
		}
		System.out.println();
		
		double d = ((double)COUNTER/(double)INPUT_STRING.length())*100;
		
		System.out.println("STRING LENGTH = " + INPUT_STRING.length());
		System.out.println("PATTERN = " + PATTERN + " COUNT = " + COUNTER);
		System.out.println("PERCENTAGE = " + d);
		System.out.println("***********************************************");
		
		/*for(int j = 0; j < a.size(); j++)
		{
			System.out.print(a.get(j)+ " ");	
		}
		System.out.println();*/
	}
}

