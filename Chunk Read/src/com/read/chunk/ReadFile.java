/**
 * 
 */
package com.read.chunk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;

/**
 * @author 		Manjusri Ishwara
 * @date   		Fed 27, 2014 - 11:50:31 PM
 * @type		ReadFile 
 */
public class ReadFile {

	private String FILE_LOCATION =  null;
	private BufferedReader BR = null;
	
	public ReadFile(String file) {
		FILE_LOCATION = file; 
	}
	
	public String readFile()
	{
		String temp = "";
		int count = 0;
		int PF = 2;
		String tr[] = new String[2];
		
		tr[0] = "FIRST";
		tr[1] = "SECONED";
		
		try
		{
			
			//System.out.println(strLine = BR.readLine());
			
			
			long a = System.currentTimeMillis();
			
			for(int j = 0; j <= 100; j++)
			{
				FileReader file = new FileReader(FILE_LOCATION);
				BR = new BufferedReader(file);
				String strLine = null;
				BR.readLine();
				while((strLine = BR.readLine()) != null)
				{
					tr[1] = strLine;
				
					if(j==100){
					System.out.println(tr[0] + "\n" + tr[1]);
					System.out.println("++++++++++++++++++++++" + j);
					}
					tr[0] = tr[1];
					//temp += strLine + "\n";
					//System.out.println(strLine);
					//count++;
				}
				System.out.println("===============" + j);
			}
			long b = System.currentTimeMillis();
			BR.close();
			System.out.println(b-a);
			
			//System.out.println(count);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return temp;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReadFile r = new ReadFile("C:\\msnbc.txt");
		r.readFile();
	}

}
