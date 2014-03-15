/**
 * 
 */
package com.string.naive;

import java.io.BufferedReader;
import java.io.FileReader;

import maps.Util.FileProcessing;

/**
 * @author 		Manjusri Ishwara
 * @date		Jan 26, 2014 - 8:01:23 PM
 * @type        Check
 *
 */
public class Check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileProcessing fp = null;
		BufferedReader BR = null;
		String temp = "";
		
		
		//====================== NAIVE STRING MATCH FOR SUBSEQUENCES TO RETREIVE INDEX===========================
		
		/*fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\harbor seal - X63726.txt", 1); 
		String s1 = fp.readFileLine(); 
				
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gray seal - X72004.txt", 1); 
		String s2 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cat - U20753.txt", 1); 
		String s3 = fp.readFileLine(); 
	
		String p = "AACACAATATAT";
		
			NaiveStringMatching nv = null; 
		
				System.out.println("++++++++++++++++++++++++++++++++++" + p +  "++++++++++++++++++++++++++++++++++++++");
				System.out.println("========= X63726 ==========");
				nv = new NaiveStringMatching(s1, p);
				nv.count();
				
				System.out.println("========= X72004 ==========");
				nv = new NaiveStringMatching(s2, p);
				nv.count();
				
				System.out.println("========= U20753 ==========");
				nv = new NaiveStringMatching(s3, p);
				nv.count();*/
		
		
		//====================== SET OF LINKS FOR SOLIDIFICATION ===================================================
		try
		{
			FileReader file = new FileReader("C:\\Users\\User\\Desktop\\2.txt");
			BR = new BufferedReader(file);
			String strLine = null;

			while((strLine = BR.readLine()) != null)
			{
				temp += strLine+"\n";
			}
		}
		catch(Exception e)
		{
			
		}
		
		System.out.println("HERE");
		
		String g[] = {"TG","GA","TC","AG","GC","GT","GG","TT","CG"}; //"CC", "AC", "TA", "AA", "AT", "CA", "CT"
		
		FilterSolid ft = new FilterSolid(g, temp);
		
		ft.process();

		

	}

}
