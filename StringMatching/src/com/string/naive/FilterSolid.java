/**
 * 
 */
package com.string.naive;

import java.util.StringTokenizer;

/**
 * @author User
 *
 */
public class FilterSolid{
	
	private String[] NON_SOLID = null;
	private String FILE_STRING = null;
	private StringTokenizer tokens = null;
	private StringTokenizer stringTokens = null;
	
	public FilterSolid(String[] sequences, String fileString)
	{
		NON_SOLID = sequences;
		FILE_STRING = fileString;
		tokens = new StringTokenizer(FILE_STRING, "\n");
	}

	public void process()
	{
		System.out.println("HERE");
		
		while(tokens.hasMoreTokens())
		{
			String temp = tokens.nextToken();
			boolean a = true;
			
			for(int i = 0; i  < NON_SOLID.length ; i++)
			{
				if(temp.contains(NON_SOLID[i]))
				{
					a = false;
					break;
				}
			}
			
			if(a)
			{
				String refine = "";
				stringTokens = new StringTokenizer(temp);
				
				while(stringTokens.hasMoreTokens())
				{
					String temp1 = stringTokens.nextToken();
					
					if(temp1.contains("("))
					{
						refine += temp1.substring(1, (temp1.length() - 1)) + "\t";
					}
					else if(temp1.contains("]"))
					{
						refine += temp1.substring(0, (temp1.length() - 1)) + "\t";
					}
				}
				
				System.out.println(refine);
			}
		}
	}
	
}
