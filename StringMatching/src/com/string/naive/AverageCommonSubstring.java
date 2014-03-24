/**
 * 
 */
package com.string.naive;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Manjusri Ishwara
 *
 */
public class AverageCommonSubstring {
	

	

	public AverageCommonSubstring()
	{
	
	}
	

	public long findValue(String seq1, String seq2, String[] seq_1, String[] seq_2, int length_1, int length_2)
	{
		
		String[] SEQUENCE_1 = seq_1;
		String[] SEQUENCE_2 = seq_2;
		int SEQ_LEN_1 = length_1;
		//int SEQ_LEN_2 = length_2;
		
		NaiveStringMatching nv1 = new NaiveStringMatching(seq1);
		NaiveStringMatching nv2 = new NaiveStringMatching(seq2);
		ArrayList<Integer> temp = null;
		
		long total = 0;
		
		for(int i = 0; i < SEQ_LEN_1; i++)
		{
			int current_length_seen = 0;
			int current_max = 0;
			
			for(int j = 0; j < SEQUENCE_1.length; j++)
			{
				//!(current_length_seen >= SEQUENCE_1[j].length()) && // 
				if(!(current_length_seen >= SEQUENCE_1[j].length()) && exists(SEQUENCE_2,SEQUENCE_1[j]))
				{
					temp = nv1.count(SEQUENCE_1[j]);
					
					if(!(temp.isEmpty()) || !(temp == null))
					{
					
						for(int k = 0; k < temp.size(); k++)
						{
							//&& nv2.countSeq(SEQUENCE_1[j])
							if(temp.get(k) >= i )
							{
								current_length_seen = SEQUENCE_1[j].length();
								current_max += SEQUENCE_1[j].length();
								break;
							}
						}
					
					}
				}					
			}
			total += current_max;
		}
		return total;
	}


	/**
	 * @param sequence
	 * @param string
	 * @return
	 * 
	 * Check the comparative 
	 */
	private boolean exists(String[] sequence, String string) 
	{

		for(int  i = 0; i < sequence.length; i ++)
		{
			if(string.equals(sequence[i]))
			{
				return true;
			}
		}
		
		return false;
	}



}
