/**
 * 
 */
package com.string.naive;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
/*		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\platypus - X83427.txt", 1); 
		String s1 = fp.readFileLine(); 
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\wallaroo - Y10524.txt", 1); 
		String s2 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\virginia opossum - Z29573.txt", 1); 
		String s3 = fp.readFileLine(); 
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\rat - X14848.txt", 1); 
		String s4 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\mouse - V00711.txt", 1); 
		String s5 = fp.readFileLine(); */
/*		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cat - U20753.txt", 1); 
		String s6 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\harbor seal - X63726.txt", 1); 
		String s7 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gray seal - X72004.txt", 1); 
		String s8 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\indian rhinoceros - X97336.txt", 1); 
		String s9 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\horse - X79547.txt", 1); 
		String s10 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\fin whale - X61145.txt", 1); 
		String s11 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\blue whale - X72204.txt", 1); 
		String s12 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cow - V00654.txt", 1); 
		String s13 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\common gibbon - X99256.txt", 1); 
		String s14 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gorilla - D38114.txt", 1); 
		String s15 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\human - D38112.txt", 1); 
		String s16 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\bonobo - D38116.txt", 1); 
		String s17 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\chimpanzee - D38113.txt", 1); 
		String s18 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\bornean orangutan - D38115.txt", 1); 
		String s19 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\sumatran orangutan - X97707.txt", 1); 
		String s20 = fp.readFileLine();*/
		String p = "AACCATAACCAATACTACCAATCAATACTCATCATTAATAATCATAATA";
		          
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\Other\\Thesis\\HumanCSVC.txt", 1); 
		String s1 = fp.readFileLine(); 
		
			NaiveStringMatching nv = null; 
		
				System.out.println("++++++++++++++++++++++++++++++++++" + p +  "++++++++++++++++++++++++++++++++++++++");
				System.out.println("========="+p.length() +  "==========");
				nv = new NaiveStringMatching(s1, p);
				nv.count();
	}
				

		
/*		
		//====================== SET OF LINKS FOR SOLIDIFICATION ===================================================
		try
		{
			FileReader file = new FileReader("C:\\Users\\User\\Desktop\\Extracts\\8.txt");
			BR = new BufferedReader(file);
			String strLine = null;
			System.out.println("HERE1");
			while((strLine = BR.readLine()) != null)
			{
				temp += strLine+"\n";
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("HERE");
		
		String g[] = {"TG","GA","AG","GT","GG","CG","GC"}; // "AC", "TA", "AA", "AT", "CA", "CC",  "CT","TC", "TT"
		
		FilterSolid ft = new FilterSolid(g, temp);
		
		ft.process();

//=========================================  Average Common Subsequence Calculation========================================================


	String[] longSequence_1_3 = (new Check()).retriveArray("15.txt");			
	System.out.println("========= 1 ==========\t" + longSequence_1_3.length + "\tS1\t" + s1.length());

	String[] longSequence_2_3 = (new Check()).retriveArray("19.txt");			
	System.out.println("========= 2 ==========\t" + longSequence_2_3.length+ "\tS2\t" + s2.length());

	String[] longSequence_3_3 = (new Check()).retriveArray("20.txt");	
	System.out.println("========= 3 ==========\t" + longSequence_3_3.length + "\tS3\t" + s3.length());

	String[] longSequence_4_3 = (new Check()).retriveArray("9.txt");			
	System.out.println("========= 4 ==========\t" + longSequence_4_3.length + "\tS4\t" + s4.length());

	String[] longSequence_5_3 = (new Check()).retriveArray("8.txt");
	System.out.println("========= 5 ==========\t" + longSequence_5_3.length + "\tS5\t" + s5.length());

	String[] longSequence_6_3 =(new Check()).retriveArray("6.txt");
	System.out.println("========= 6 ==========\t" + longSequence_6_3.length + "\tS6\t" + s6.length());
			
	String[] longSequence_7_3 =(new Check()).retriveArray("11.txt"); 
	System.out.println("========= 7 ==========\t" + longSequence_7_3.length + "\tS7\t" + s7.length());
			
	String[] longSequence_8_3 = (new Check()).retriveArray("12.txt");  
	System.out.println("========= 8 ==========\t" + longSequence_8_3.length + "\tS8\t" + s8.length());
	
	String[] longSequence_9_3 = (new Check()).retriveArray("16.txt");  
	System.out.println("========= 9 ==========\t" + longSequence_9_3.length + "\tS9\t" + s9.length());
	
	String[] longSequence_10_3 = (new Check()).retriveArray("14.txt");  
	System.out.println("========= 10 ==========\t" + longSequence_10_3.length + "\tS10\t" + s10.length());
	
	String[] longSequence_11_3 = (new Check()).retriveArray("10.txt");  
	System.out.println("========= 11 ==========\t" + longSequence_11_3.length + "\tS11\t" + s11.length());
	
	String[] longSequence_12_3 = (new Check()).retriveArray("13.txt");  
	System.out.println("========= 12 ==========\t" + longSequence_12_3.length + "\tS12\t" + s12.length());
	
	String[] longSequence_13_3 = (new Check()).retriveArray("7.txt");  
	System.out.println("========= 13 ==========\t" + longSequence_13_3.length + "\tS13\t" + s13.length());
	
	String[] longSequence_14_3 = (new Check()).retriveArray("18.txt");  
	System.out.println("========= 14 ==========\t" + longSequence_14_3.length + "\tS14\t" + s14.length());
	
	String[] longSequence_15_3 = (new Check()).retriveArray("3.txt");  
	System.out.println("========= 15 ==========\t" + longSequence_15_3.length + "\tS15\t" + s15.length());

	String[] longSequence_16_3 = (new Check()).retriveArray("1.txt");  
	System.out.println("========= 16 ==========\t" + longSequence_16_3.length + "\tS16\t" + s16.length());
	
	String[] longSequence_17_3 = (new Check()).retriveArray("5.txt");  
	System.out.println("========= 17 ==========\t" + longSequence_17_3.length + "\tS17\t" + s17.length());
	
	String[] longSequence_18_3 = (new Check()).retriveArray("2.txt");  
	System.out.println("========= 18 ==========\t" + longSequence_18_3.length + "\tS18\t" + s18.length());
	
	String[] longSequence_19_3 = (new Check()).retriveArray("4.txt");  
	System.out.println("========= 19 ==========\t" + longSequence_19_3.length + "\tS19\t" + s19.length());
	
	String[] longSequence_20_3 = (new Check()).retriveArray("17.txt");  
	System.out.println("========= 20 ==========\t" + longSequence_20_3.length + "\tS20\t" + s20.length());
//====================================================================================================================
		
		String[] Test1 = {"ACB", "BC", "AA", "AB"};
		String[] Test2 = {"ACB", "BC"};
		
		String a = "ACBCAAAB";
		String b = "ACBCACBC";
		
		//AverageCommonSubstring av = new AverageCommonSubstring();
		AverageCommonSubstring av = new AverageCommonSubstring();
		
		double[][] values = new double[20][20];
		double[][] acs = new double[20][20];
		//System.out.println( s1.length()+"\t"+ s2.length() + "\t" + s3.length());
		//System.out.println("VALUE =\t" + av.findValue(s5, s4, longSequence_5_2,  longSequence_4_2, s5.length(), s4.length()));
	//	System.out.println("VALUE =\t" + av.findValue(a ,b, Test1, Test2, a.length(), b.length()) );
		

 		System.out.println("LCS -> 5% -> 20 Species -> Have Exist Check -> TEST 2");

		for(int i = 1; i <= 20; i++)
		{

			System.out.println("=========================================================");
			if(i==1)
			{ 
				values[1][0] = av.findValue(s1, s2, longSequence_1_3,  longSequence_2_3, s1.length(), s2.length());
				System.out.println("S1 S2 VALUE =\t" + values[1][0]);
				 
				values[2][0] = av.findValue(s1, s3, longSequence_1_3,  longSequence_3_3, s1.length(), s3.length());
				System.out.println("S1 S3 VALUE =\t" + values[2][0]);
				 
				values[3][0] = av.findValue(s1, s4, longSequence_1_3,  longSequence_4_3, s1.length(), s4.length());
				System.out.println("S1 S4 VALUE =\t" + values[3][0] );
				
				values[4][0] = av.findValue(s1, s5, longSequence_1_3,  longSequence_5_3, s1.length(), s5.length());
				System.out.println("S1 S5 VALUE =\t" + values[4][0]);
				
				values[5][0] = av.findValue(s1, s6, longSequence_1_3,  longSequence_6_3, s1.length(), s6.length());
				System.out.println("S1 S6 VALUE =\t" + values[5][0]);
				
				values[6][0] = av.findValue(s1, s7, longSequence_1_3,  longSequence_7_3, s1.length(), s7.length());
				System.out.println("S1 S7 VALUE =\t" + values[6][0]);
				
				values[7][0] = av.findValue(s1, s8, longSequence_1_3,  longSequence_8_3, s1.length(), s8.length());
				System.out.println("S1 S8 VALUE =\t" + values[7][0]);
				
				values[8][0] = av.findValue(s1, s9, longSequence_1_3,  longSequence_9_3, s1.length(), s9.length());
				System.out.println("S1 S9 VALUE =\t" + values[8][0]);
				
				values[9][0] = av.findValue(s1, s10, longSequence_1_3,  longSequence_10_3, s1.length(), s10.length());
				System.out.println("S1 S10 VALUE =\t" + values[9][0]);
				
				values[10][0] = av.findValue(s1, s11, longSequence_1_3,  longSequence_11_3, s1.length(), s11.length());
				System.out.println("S1 S11 VALUE =\t" + values[10][0]);
				
				values[11][0] = av.findValue(s1, s12, longSequence_1_3,  longSequence_12_3, s1.length(), s12.length());
				System.out.println("S1 S12 VALUE =\t" + values[11][0]);
				
				values[12][0] = av.findValue(s1, s13, longSequence_1_3,  longSequence_13_3, s1.length(), s13.length());
				System.out.println("S1 S13 VALUE =\t" + values[12][0]);
				
				values[13][0] = av.findValue(s1, s14, longSequence_1_3,  longSequence_14_3, s1.length(), s14.length());
				System.out.println("S1 S14 VALUE =\t" + values[13][0]);
				
				values[14][0] = av.findValue(s1, s15, longSequence_1_3,  longSequence_15_3, s1.length(), s15.length());
				System.out.println("S1 S15 VALUE =\t" + values[14][0]);
				
				values[15][0] = av.findValue(s1, s16, longSequence_1_3,  longSequence_16_3, s1.length(), s16.length());
				System.out.println("S1 S16 VALUE =\t" + values[15][0]);
				
				values[16][0] = av.findValue(s1, s17, longSequence_1_3,  longSequence_17_3, s1.length(), s17.length());
				System.out.println("S1 S17 VALUE =\t" + values[16][0]);
				
				values[17][0] = av.findValue(s1, s18, longSequence_1_3,  longSequence_18_3, s1.length(), s18.length());
				System.out.println("S1 S18 VALUE =\t" + values[17][0]);
				
				values[18][0] = av.findValue(s1, s19, longSequence_1_3,  longSequence_19_3, s1.length(), s19.length());
				System.out.println("S1 S19 VALUE =\t" + values[18][0]);
				
				values[19][0] = av.findValue(s1, s20, longSequence_1_3,  longSequence_20_3, s1.length(), s20.length());
				System.out.println("S1 S20 VALUE =\t" + values[19][0]);

			}
			else if(i==2)
			{
				values[0][1] = av.findValue(s2, s1, longSequence_2_3,  longSequence_1_3, s2.length(), s1.length());
				System.out.println("S2 S1 VALUE =\t" + values[0][1]);
				
				values[2][1] = av.findValue(s2, s3, longSequence_2_3,  longSequence_3_3, s2.length(), s3.length());
				System.out.println("S2 S3 VALUE =\t" + values[2][1]);
				
				values[3][1] = av.findValue(s2, s4, longSequence_2_3,  longSequence_4_3, s2.length(), s4.length());
				System.out.println("S2 S4 VALUE =\t" + values[3][1]);
				
				values[4][1] = av.findValue(s2, s5, longSequence_2_3,  longSequence_5_3, s2.length(), s5.length());
				System.out.println("S2 S5 VALUE =\t" + values[4][1]);
				
				values[5][1] = av.findValue(s2, s6, longSequence_2_3,  longSequence_6_3, s2.length(), s6.length());
				System.out.println("S2 S6 VALUE =\t" + values[5][1]);
				
				values[6][1] = av.findValue(s2, s7, longSequence_2_3,  longSequence_7_3, s2.length(), s7.length());
				System.out.println("S2 S7 VALUE =\t" + values[6][1]);
				
				values[7][1] = av.findValue(s2, s8, longSequence_2_3,  longSequence_8_3, s2.length(), s8.length());
				System.out.println("S2 S8 VALUE =\t" + values[7][1]);
				
				values[8][1] = av.findValue(s2, s9, longSequence_2_3,  longSequence_9_3, s2.length(), s9.length());
				System.out.println("S2 S9 VALUE =\t" + values[8][1]);
				
				values[9][1] = av.findValue(s2, s10, longSequence_2_3,  longSequence_10_3, s2.length(), s10.length());
				System.out.println("S2 S10 VALUE =\t" + values[9][1]);
				
				values[10][1] = av.findValue(s2, s11, longSequence_2_3,  longSequence_11_3, s2.length(), s11.length());
				System.out.println("S2 S11 VALUE =\t" + values[10][1]);
				
				values[11][1] = av.findValue(s2, s12, longSequence_2_3,  longSequence_12_3, s2.length(), s12.length());
				System.out.println("S2 S12 VALUE =\t" + values[11][1]);
				
				values[12][1] = av.findValue(s2, s13, longSequence_2_3,  longSequence_13_3, s2.length(), s13.length());
				System.out.println("S2 S13 VALUE =\t" + values[12][1]);
				
				values[13][1] = av.findValue(s2, s14, longSequence_2_3,  longSequence_14_3, s2.length(), s14.length());
				System.out.println("S2 S14 VALUE =\t" + values[13][1]);
				
				values[14][1] = av.findValue(s2, s15, longSequence_2_3,  longSequence_15_3, s2.length(), s15.length());
				System.out.println("S2 S15 VALUE =\t" + values[14][1]);
				
				values[15][1] = av.findValue(s2, s16, longSequence_2_3,  longSequence_16_3, s2.length(), s16.length());
				System.out.println("S2 S16 VALUE =\t" + values[15][1]);
				
				values[16][1] = av.findValue(s2, s17, longSequence_2_3,  longSequence_17_3, s2.length(), s17.length());
				System.out.println("S2 S17 VALUE =\t" + values[16][1]);
				
				values[17][1] = av.findValue(s2, s18, longSequence_2_3,  longSequence_18_3, s2.length(), s18.length());
				System.out.println("S2 S18 VALUE =\t" + values[17][1]);
				
				values[18][1] = av.findValue(s2, s19, longSequence_2_3,  longSequence_19_3, s2.length(), s19.length());
				System.out.println("S2 S19 VALUE =\t" + values[18][1]);
				
				values[19][1] = av.findValue(s2, s20, longSequence_2_3,  longSequence_20_3, s2.length(), s20.length());
				System.out.println("S2 S20 VALUE =\t" + values[19][1]);
			}
			else if(i==3)
			{
				values[0][2] = av.findValue(s3, s1, longSequence_3_3,  longSequence_1_3, s3.length(), s1.length());
				System.out.println("S3 S1 VALUE =\t" + values[0][2]);
				
				values[1][2] = av.findValue(s3, s2, longSequence_3_3,  longSequence_2_3, s3.length(), s2.length());
				System.out.println("S3 S2 VALUE =\t" + values[1][2]);
				
				values[3][2] = av.findValue(s3, s4, longSequence_3_3,  longSequence_4_3, s3.length(), s4.length());
				System.out.println("S3 S4 VALUE =\t" + values[3][2]);
				
				values[4][2] = av.findValue(s3, s5, longSequence_3_3,  longSequence_5_3, s3.length(), s5.length());
				System.out.println("S3 S5 VALUE =\t" + values[4][2]);
					
				values[5][2] = av.findValue(s3, s6, longSequence_3_3,  longSequence_6_3, s3.length(), s6.length());
				System.out.println("S3 S6 VALUE =\t" + values[5][2]);
				
				values[6][2] = av.findValue(s3, s7, longSequence_3_3,  longSequence_7_3, s3.length(), s7.length());
				System.out.println("S3 S7 VALUE =\t" + values[6][2]);
				
				values[7][2] = av.findValue(s3, s8, longSequence_3_3,  longSequence_8_3, s3.length(), s8.length());
				System.out.println("S3 S8 VALUE =\t" + values[7][2]);
				
				values[8][2] = av.findValue(s3, s9, longSequence_3_3,  longSequence_9_3, s3.length(), s9.length());
				System.out.println("S3 S9 VALUE =\t" + values[8][2]);
				
				values[9][2] = av.findValue(s3, s10, longSequence_3_3,  longSequence_10_3, s3.length(), s10.length());
				System.out.println("S3 S10 VALUE =\t" + values[9][2]);
				
				values[10][2] = av.findValue(s3, s11, longSequence_3_3,  longSequence_11_3, s3.length(), s11.length());
				System.out.println("S3 S11 VALUE =\t" + values[10][2]);
				
				values[11][2] = av.findValue(s3, s12, longSequence_3_3,  longSequence_12_3, s3.length(), s12.length());
				System.out.println("S3 S12 VALUE =\t" + values[11][2]);
				
				values[12][2] = av.findValue(s3, s13, longSequence_3_3,  longSequence_13_3, s3.length(), s13.length());
				System.out.println("S3 S13 VALUE =\t" + values[12][2]);
				
				values[13][2] = av.findValue(s3, s14, longSequence_3_3,  longSequence_14_3, s3.length(), s14.length());
				System.out.println("S3 S14 VALUE =\t" + values[13][2]);
				
				values[14][2] = av.findValue(s3, s15, longSequence_3_3,  longSequence_15_3, s3.length(), s15.length());
				System.out.println("S3 S15 VALUE =\t" + values[14][2]);
				
				values[15][2] = av.findValue(s3, s16, longSequence_3_3,  longSequence_16_3, s3.length(), s16.length());
				System.out.println("S3 S16 VALUE =\t" + values[15][2]);
				
				values[16][2] = av.findValue(s3, s17, longSequence_3_3,  longSequence_17_3, s3.length(), s17.length());
				System.out.println("S3 S17 VALUE =\t" + values[16][2]);
				
				values[17][2] = av.findValue(s3, s18, longSequence_3_3,  longSequence_18_3, s3.length(), s18.length());
				System.out.println("S3 S18 VALUE =\t" + values[17][2]);
				
				values[18][2] = av.findValue(s3, s19, longSequence_3_3,  longSequence_19_3, s3.length(), s19.length());
				System.out.println("S3 S19 VALUE =\t" + values[18][2]);
				
				values[19][2] = av.findValue(s3, s20, longSequence_3_3,  longSequence_20_3, s3.length(), s20.length());
				System.out.println("S3 S20 VALUE =\t" + values[19][2]);
			}
			else if(i==4)
			{
				values[0][3] = av.findValue(s4, s1, longSequence_4_3,  longSequence_1_3, s4.length(), s1.length());
				System.out.println("S4 S1 VALUE =\t" + values[0][3]);
				
				values[1][3] = av.findValue(s4, s2, longSequence_4_3,  longSequence_2_3, s4.length(), s2.length());
				System.out.println("S4 S2 VALUE =\t" + values[1][3]);
				
				values[2][3] = av.findValue(s4, s3, longSequence_4_3,  longSequence_3_3, s4.length(), s3.length());
				System.out.println("S4 S3 VALUE =\t" + values[2][3]);
				
				values[4][3] = av.findValue(s4, s5, longSequence_4_3,  longSequence_5_3, s4.length(), s5.length());
				System.out.println("S4 S5 VALUE =\t" + values[4][3]);
				
				values[5][3] = av.findValue(s4, s6, longSequence_4_3,  longSequence_6_3, s4.length(), s6.length());
				System.out.println("S4 S6 VALUE =\t" + values[5][3]);
				
				values[6][3] = av.findValue(s4, s7, longSequence_4_3,  longSequence_7_3, s4.length(), s7.length());
				System.out.println("S4 S7 VALUE =\t" + values[6][3]);
			
				values[7][3] = av.findValue(s4, s8, longSequence_4_3,  longSequence_8_3, s4.length(), s8.length());
				System.out.println("S4 S8 VALUE =\t" + values[7][3]);
				
				values[8][3] = av.findValue(s4, s9, longSequence_4_3,  longSequence_9_3, s4.length(), s9.length());
				System.out.println("S4 S9 VALUE =\t" + values[8][3]);
				
				values[9][3] = av.findValue(s4, s10, longSequence_4_3,  longSequence_10_3, s4.length(), s10.length());
				System.out.println("S4 S10 VALUE =\t" + values[9][3]);
				
				values[10][3] = av.findValue(s4, s11, longSequence_4_3,  longSequence_11_3, s4.length(), s11.length());
				System.out.println("S4 S11 VALUE =\t" + values[10][3]);
				
				values[11][3] = av.findValue(s4, s12, longSequence_4_3,  longSequence_12_3, s4.length(), s12.length());
				System.out.println("S4 S12 VALUE =\t" + values[11][3]);
				
				values[12][3] = av.findValue(s4, s13, longSequence_4_3,  longSequence_13_3, s4.length(), s13.length());
				System.out.println("S4 S13 VALUE =\t" + values[12][3]);
				
				values[13][3] = av.findValue(s4, s14, longSequence_4_3,  longSequence_14_3, s4.length(), s14.length());
				System.out.println("S4 S14 VALUE =\t" + values[13][3]);
				
				values[14][3] = av.findValue(s4, s15, longSequence_4_3,  longSequence_15_3, s4.length(), s15.length());
				System.out.println("S4 S15 VALUE =\t" + values[14][3]);
				
				values[15][3] = av.findValue(s4, s16, longSequence_4_3,  longSequence_16_3, s4.length(), s16.length());
				System.out.println("S4 S16 VALUE =\t" + values[15][3]);
				
				values[16][3] = av.findValue(s4, s17, longSequence_4_3,  longSequence_17_3, s4.length(), s17.length());
				System.out.println("S4 S17 VALUE =\t" + values[16][3]);
				
				values[17][3] = av.findValue(s4, s18, longSequence_4_3,  longSequence_18_3, s4.length(), s18.length());
				System.out.println("S4 S18 VALUE =\t" + values[17][3]);
				
				values[18][3] = av.findValue(s4, s19, longSequence_4_3,  longSequence_19_3, s4.length(), s19.length());
				System.out.println("S4 S19 VALUE =\t" + values[18][3]);
				
				values[19][3] = av.findValue(s4, s20, longSequence_4_3,  longSequence_20_3, s4.length(), s20.length());
				System.out.println("S4 S20 VALUE =\t" + values[19][3]);
			}
			else if(i==5)
			{
				values[0][4] = av.findValue(s5, s1, longSequence_5_3,  longSequence_1_3, s5.length(), s1.length());
				System.out.println("S5 S1 VALUE =\t" + values[0][4]);
				
				values[1][4] = av.findValue(s5, s2, longSequence_5_3,  longSequence_2_3, s5.length(), s2.length());
				System.out.println("S5 S2 VALUE =\t" + values[1][4]);
				
				values[2][4] = av.findValue(s5, s3, longSequence_5_3,  longSequence_3_3, s5.length(), s3.length());
				System.out.println("S5 S3 VALUE =\t" + values[2][4]);
				
				values[3][4] = av.findValue(s5, s4, longSequence_5_3,  longSequence_4_3, s5.length(), s4.length());
				System.out.println("S5 S4 VALUE =\t" + values[3][4]);

				values[5][4] = av.findValue(s5, s6, longSequence_5_3,  longSequence_6_3, s5.length(), s6.length());
				System.out.println("S5 S6 VALUE =\t" + values[5][4]);
				
				values[6][4] = av.findValue(s5, s7, longSequence_5_3,  longSequence_7_3, s5.length(), s7.length());
				System.out.println("S5 S7 VALUE =\t" + values[6][4]);
				
				values[7][4] = av.findValue(s5, s8, longSequence_5_3,  longSequence_8_3, s5.length(), s8.length());
				System.out.println("S5 S8 VALUE =\t" + values[7][4]);
				
				values[8][4] = av.findValue(s5, s9, longSequence_5_3,  longSequence_9_3, s5.length(), s9.length());
				System.out.println("S5 S9 VALUE =\t" + values[8][4]);
				
				values[9][4] = av.findValue(s5, s10, longSequence_5_3,  longSequence_10_3, s5.length(), s10.length());
				System.out.println("S5 S10 VALUE =\t" + values[9][4]);
				
				values[10][4] = av.findValue(s5, s11, longSequence_5_3,  longSequence_11_3, s5.length(), s11.length());
				System.out.println("S5 S11 VALUE =\t" + values[10][4]);
				
				values[11][4] = av.findValue(s5, s12, longSequence_5_3,  longSequence_12_3, s5.length(), s12.length());
				System.out.println("S5 S12 VALUE =\t" + values[11][4]);
				
				values[12][4] = av.findValue(s5, s13, longSequence_5_3,  longSequence_13_3, s5.length(), s13.length());
				System.out.println("S5 S13 VALUE =\t" + values[12][4]);
				
				values[13][4] = av.findValue(s5, s14, longSequence_5_3,  longSequence_14_3, s5.length(), s14.length());
				System.out.println("S5 S14 VALUE =\t" + values[13][4]);
				
				values[14][4] = av.findValue(s5, s15, longSequence_5_3,  longSequence_15_3, s5.length(), s15.length());
				System.out.println("S5 S15 VALUE =\t" + values[14][4]);
				
				values[15][4] = av.findValue(s5, s16, longSequence_5_3,  longSequence_16_3, s5.length(), s16.length());
				System.out.println("S5 S16 VALUE =\t" + values[15][4]);
				
				values[16][4] = av.findValue(s5, s17, longSequence_5_3,  longSequence_17_3, s5.length(), s17.length());
				System.out.println("S5 S17 VALUE =\t" + values[16][4]);
				
				values[17][4] = av.findValue(s5, s18, longSequence_5_3,  longSequence_18_3, s5.length(), s18.length());
				System.out.println("S5 S18 VALUE =\t" + values[17][4]);
				
				values[18][4] = av.findValue(s5, s19, longSequence_5_3,  longSequence_19_3, s5.length(), s19.length());
				System.out.println("S5 S19 VALUE =\t" + values[18][4]);
				
				values[19][4] = av.findValue(s5, s20, longSequence_5_3,  longSequence_20_3, s5.length(), s20.length());
				System.out.println("S5 S20 VALUE =\t" + values[19][4]);
			}
			else if(i==6)
			{
				values[0][5] = av.findValue(s6, s1, longSequence_6_3,  longSequence_1_3, s6.length(), s1.length());
				System.out.println("S6 S1 VALUE =\t" + values[0][5]);
				
				values[1][5] = av.findValue(s6, s2, longSequence_6_3,  longSequence_2_3, s6.length(), s2.length());
				System.out.println("S6 S2 VALUE =\t" + values[1][5]);
				
				values[2][5] = av.findValue(s6, s3, longSequence_6_3,  longSequence_3_3, s6.length(), s3.length());
				System.out.println("S6 S3 VALUE =\t" + values[2][5]);
				
				values[3][5] = av.findValue(s6, s4, longSequence_6_3,  longSequence_4_3, s6.length(), s4.length());
				System.out.println("S6 S4 VALUE =\t" + values[3][5]);

				values[4][5] = av.findValue(s6, s5, longSequence_6_3,  longSequence_5_3, s6.length(), s5.length());
				System.out.println("S6 S5 VALUE =\t" + values[4][5]);
				
				values[6][5] = av.findValue(s6, s7, longSequence_6_3,  longSequence_7_3, s6.length(), s7.length());
				System.out.println("S6 S7 VALUE =\t" + values[6][5]);
				
				values[7][5] = av.findValue(s6, s8, longSequence_6_3,  longSequence_8_3, s6.length(), s8.length());
				System.out.println("S6 S8 VALUE =\t" + values[7][5]);
				
				values[8][5] = av.findValue(s6, s9, longSequence_6_3,  longSequence_9_3, s6.length(), s9.length());
				System.out.println("S6 S9 VALUE =\t" + values[8][5]);
				
				values[9][5] = av.findValue(s6, s10, longSequence_6_3,  longSequence_10_3, s6.length(), s10.length());
				System.out.println("S6 S10 VALUE =\t" + values[9][5]);
				
				values[10][5] = av.findValue(s6, s11, longSequence_6_3,  longSequence_11_3, s6.length(), s11.length());
				System.out.println("S6 S11 VALUE =\t" + values[10][5]);
				
				values[11][5] = av.findValue(s6, s12, longSequence_6_3,  longSequence_12_3, s6.length(), s12.length());
				System.out.println("S6 S12 VALUE =\t" + values[11][5]);
				
				values[12][5] = av.findValue(s6, s13, longSequence_6_3,  longSequence_13_3, s6.length(), s13.length());
				System.out.println("S6 S13 VALUE =\t" + values[12][5]);
				
				values[13][5] = av.findValue(s6, s14, longSequence_6_3,  longSequence_14_3, s6.length(), s14.length());
				System.out.println("S6 S14 VALUE =\t" + values[13][5]);
				
				values[14][5] = av.findValue(s6, s15, longSequence_6_3,  longSequence_15_3, s6.length(), s15.length());
				System.out.println("S6 S15 VALUE =\t" + values[14][5]);
				
				values[15][5] = av.findValue(s6, s16, longSequence_6_3,  longSequence_16_3, s6.length(), s16.length());
				System.out.println("S6 S16 VALUE =\t" + values[15][5]);
				
				values[16][5] = av.findValue(s6, s17, longSequence_6_3,  longSequence_17_3, s6.length(), s17.length());
				System.out.println("S6 S17 VALUE =\t" + values[16][5]);
				
				values[17][5] = av.findValue(s6, s18, longSequence_6_3,  longSequence_18_3, s6.length(), s18.length());
				System.out.println("S6 S18 VALUE =\t" + values[17][5]);
				
				values[18][5] = av.findValue(s6, s19, longSequence_6_3,  longSequence_19_3, s6.length(), s19.length());
				System.out.println("S6 S19 VALUE =\t" + values[18][5]);
				
				values[19][5] = av.findValue(s6, s20, longSequence_6_3,  longSequence_20_3, s6.length(), s20.length());
				System.out.println("S6 S20 VALUE =\t" + values[19][5]);
			}
			else if(i==7)
			{
				values[0][6] = av.findValue(s7, s1, longSequence_7_3,  longSequence_1_3, s7.length(), s1.length());
				System.out.println("S7 S1 VALUE =\t" + values[0][6]);
				
				values[1][6] = av.findValue(s7, s2, longSequence_7_3,  longSequence_2_3, s7.length(), s2.length());
				System.out.println("S7 S2 VALUE =\t" + values[1][6]);
				
				values[2][6] = av.findValue(s7, s3, longSequence_7_3,  longSequence_3_3, s7.length(), s3.length());
				System.out.println("S7 S3 VALUE =\t" + values[2][6]);
				
				values[3][6] = av.findValue(s7, s4, longSequence_7_3,  longSequence_4_3, s7.length(), s4.length());
				System.out.println("S7 S4 VALUE =\t" + values[3][6]);
				
				values[4][6] = av.findValue(s7, s5, longSequence_7_3,  longSequence_5_3, s7.length(), s5.length());
				System.out.println("S7 S5 VALUE =\t" + values[4][6]);
				
				values[5][6] = av.findValue(s7, s6, longSequence_7_3,  longSequence_6_3, s7.length(), s6.length());
				System.out.println("S7 S6 VALUE =\t" + values[5][6]);
				
				values[7][6] = av.findValue(s7, s8, longSequence_7_3,  longSequence_8_3, s7.length(), s8.length());
				System.out.println("S7 S8 VALUE =\t" + values[7][6]);
				
				values[8][6] = av.findValue(s7, s9, longSequence_7_3,  longSequence_9_3, s7.length(), s9.length());
				System.out.println("S7 S9 VALUE =\t" + values[8][6]);
				
				values[9][6] = av.findValue(s7, s10, longSequence_7_3,  longSequence_10_3, s7.length(), s10.length());
				System.out.println("S7 S10 VALUE =\t" + values[9][6]);
				
				values[10][6] = av.findValue(s7, s11, longSequence_7_3,  longSequence_11_3, s7.length(), s11.length());
				System.out.println("S7 S11 VALUE =\t" + values[10][6]);
				
				values[11][6] = av.findValue(s7, s12, longSequence_7_3,  longSequence_12_3, s7.length(), s12.length());
				System.out.println("S7 S12 VALUE =\t" + values[11][6]);
				
				values[12][6] = av.findValue(s7, s13, longSequence_7_3,  longSequence_13_3, s7.length(), s13.length());
				System.out.println("S7 S13 VALUE =\t" + values[12][6]);
				
				values[13][6] = av.findValue(s7, s14, longSequence_7_3,  longSequence_14_3, s7.length(), s14.length());
				System.out.println("S7 S14 VALUE =\t" + values[13][6]);
				
				values[14][6] = av.findValue(s7, s15, longSequence_7_3,  longSequence_15_3, s7.length(), s15.length());
				System.out.println("S7 S15 VALUE =\t" + values[14][6]);
				
				values[15][6] = av.findValue(s7, s16, longSequence_7_3,  longSequence_16_3, s7.length(), s16.length());
				System.out.println("S7 S16 VALUE =\t" + values[15][6]);
				
				values[16][6] = av.findValue(s7, s17, longSequence_7_3,  longSequence_17_3, s7.length(), s17.length());
				System.out.println("S7 S17 VALUE =\t" + values[16][6]);
				
				values[17][6] = av.findValue(s7, s18, longSequence_7_3,  longSequence_18_3, s7.length(), s18.length());
				System.out.println("S7 S18 VALUE =\t" + values[17][6]);
				
				values[18][6] = av.findValue(s7, s19, longSequence_7_3,  longSequence_19_3, s7.length(), s19.length());
				System.out.println("S7 S19 VALUE =\t" + values[18][6]);
				
				values[19][6] = av.findValue(s7, s20, longSequence_7_3,  longSequence_20_3, s7.length(), s20.length());
				System.out.println("S7 S20 VALUE =\t" + values[19][6]);
			}
			else if(i==8)
			{
				values[0][7] = av.findValue(s8, s1, longSequence_8_3,  longSequence_1_3, s8.length(), s1.length());
				System.out.println("S8 S1 VALUE =\t" + values[0][7]);
				
				values[1][7] = av.findValue(s8, s2, longSequence_8_3,  longSequence_2_3, s8.length(), s2.length());
				System.out.println("S8 S2 VALUE =\t" + values[1][7]);
				
				values[2][7] = av.findValue(s8, s3, longSequence_8_3,  longSequence_3_3, s8.length(), s3.length());
				System.out.println("S8 S3 VALUE =\t" + values[2][7]);
				
				values[3][7] = av.findValue(s8, s4, longSequence_8_3,  longSequence_4_3, s8.length(), s4.length());
				System.out.println("S8 S4 VALUE =\t" + values[3][7]);
				
				values[4][7] = av.findValue(s8, s5, longSequence_8_3,  longSequence_5_3, s8.length(), s5.length());
				System.out.println("S8 S5 VALUE =\t" + values[4][7]);
				
				values[5][7] = av.findValue(s8, s6, longSequence_8_3,  longSequence_6_3, s8.length(), s6.length());
				System.out.println("S8 S6 VALUE =\t" + values[5][7]);
				
				values[6][7] = av.findValue(s8, s7, longSequence_8_3,  longSequence_7_3, s8.length(), s7.length());
				System.out.println("S8 S7 VALUE =\t" + values[6][7]);
				
				values[8][7] = av.findValue(s8, s9, longSequence_8_3,  longSequence_9_3, s8.length(), s9.length());
				System.out.println("S8 S9 VALUE =\t" + values[8][7]);
				
				values[9][7] = av.findValue(s8, s10, longSequence_8_3,  longSequence_10_3, s8.length(), s10.length());
				System.out.println("S8 S10 VALUE =\t" + values[9][7]);
				
				values[10][7] = av.findValue(s8, s11, longSequence_8_3,  longSequence_11_3, s8.length(), s11.length());
				System.out.println("S8 S11 VALUE =\t" + values[10][7]);
				
				values[11][7] = av.findValue(s8, s12, longSequence_8_3,  longSequence_12_3, s8.length(), s12.length());
				System.out.println("S8 S12 VALUE =\t" + values[11][7]);
				
				values[12][7] = av.findValue(s8, s13, longSequence_8_3,  longSequence_13_3, s8.length(), s13.length());
				System.out.println("S8 S13 VALUE =\t" + values[12][7]);
				
				values[13][7] = av.findValue(s8, s14, longSequence_8_3,  longSequence_14_3, s8.length(), s14.length());
				System.out.println("S8 S14 VALUE =\t" + values[13][7]);
				
				values[14][7] = av.findValue(s8, s15, longSequence_8_3,  longSequence_15_3, s8.length(), s15.length());
				System.out.println("S8 S15 VALUE =\t" + values[14][7]);
				
				values[15][7] = av.findValue(s8, s16, longSequence_8_3,  longSequence_16_3, s8.length(), s16.length());
				System.out.println("S8 S16 VALUE =\t" + values[15][7]);
				
				values[16][7] = av.findValue(s8, s17, longSequence_8_3,  longSequence_17_3, s8.length(), s17.length());
				System.out.println("S8 S17 VALUE =\t" + values[16][7]);
				
				values[17][7] = av.findValue(s8, s18, longSequence_8_3,  longSequence_18_3, s8.length(), s18.length());
				System.out.println("S8 S18 VALUE =\t" + values[17][7]);
				
				values[18][7] = av.findValue(s8, s19, longSequence_8_3,  longSequence_19_3, s8.length(), s19.length());
				System.out.println("S8 S19 VALUE =\t" + values[18][7]);
				
				values[19][7] = av.findValue(s8, s20, longSequence_8_3,  longSequence_20_3, s8.length(), s20.length());
				System.out.println("S8 S20 VALUE =\t" + values[19][7]);
			}
			else if(i==9)
			{
				values[0][8] = av.findValue(s9, s1, longSequence_9_3,  longSequence_1_3, s9.length(), s1.length());
				System.out.println("S9 S1 VALUE =\t" + values[0][8]);
				
				values[1][8] = av.findValue(s9, s2, longSequence_9_3,  longSequence_2_3, s9.length(), s2.length());
				System.out.println("S9 S2 VALUE =\t" + values[1][8]);
				
				values[2][8] = av.findValue(s9, s3, longSequence_9_3,  longSequence_3_3, s9.length(), s3.length());
				System.out.println("S9 S3 VALUE =\t" + values[2][8]);
				
				values[3][8] = av.findValue(s9, s4, longSequence_9_3,  longSequence_4_3, s9.length(), s4.length());
				System.out.println("S9 S4 VALUE =\t" + values[3][8]);
				
				values[4][8] = av.findValue(s9, s5, longSequence_9_3,  longSequence_5_3, s9.length(), s5.length());
				System.out.println("S9 S5 VALUE =\t" + values[4][8]);
				
				values[5][8] = av.findValue(s9, s6, longSequence_9_3,  longSequence_6_3, s9.length(), s6.length());
				System.out.println("S9 S6 VALUE =\t" + values[5][8]);
				
				values[6][8] = av.findValue(s9, s7, longSequence_9_3,  longSequence_7_3, s9.length(), s7.length());
				System.out.println("S9 S7 VALUE =\t" + values[6][8]);
				
				values[7][8] = av.findValue(s9, s8, longSequence_9_3,  longSequence_8_3, s9.length(), s8.length());
				System.out.println("S9 S8 VALUE =\t" + values[7][8]);
				
				values[9][8] = av.findValue(s9, s10, longSequence_9_3,  longSequence_10_3, s9.length(), s10.length());
				System.out.println("S9 S10 VALUE =\t" + values[9][8]);
				
				values[10][8] = av.findValue(s9, s11, longSequence_9_3,  longSequence_11_3, s9.length(), s11.length());
				System.out.println("S9 S11 VALUE =\t" + values[10][8]);
				
				values[11][8] = av.findValue(s9, s12, longSequence_9_3,  longSequence_12_3, s9.length(), s12.length());
				System.out.println("S9 S12 VALUE =\t" + values[11][8]);
				
				values[12][8] = av.findValue(s9, s13, longSequence_9_3,  longSequence_13_3, s9.length(), s13.length());
				System.out.println("S9 S13 VALUE =\t" + values[12][8]);
				
				values[13][8] = av.findValue(s9, s14, longSequence_9_3,  longSequence_14_3, s9.length(), s14.length());
				System.out.println("S9 S14 VALUE =\t" + values[13][8]);
				
				values[14][8] = av.findValue(s9, s15, longSequence_9_3,  longSequence_15_3, s9.length(), s15.length());
				System.out.println("S9 S15 VALUE =\t" + values[14][8]);
				
				values[15][8] = av.findValue(s9, s16, longSequence_9_3,  longSequence_16_3, s9.length(), s16.length());
				System.out.println("S9 S16 VALUE =\t" + values[15][8]);
				
				values[16][8] = av.findValue(s9, s17, longSequence_9_3,  longSequence_17_3, s9.length(), s17.length());
				System.out.println("S9 S17 VALUE =\t" + values[16][8]);
				
				values[17][8] = av.findValue(s9, s18, longSequence_9_3,  longSequence_18_3, s9.length(), s18.length());
				System.out.println("S9 S18 VALUE =\t" + values[17][8]);
				
				values[18][8] = av.findValue(s9, s19, longSequence_9_3,  longSequence_19_3, s9.length(), s19.length());
				System.out.println("S9 S19 VALUE =\t" + values[18][8]);
				
				values[19][8] = av.findValue(s9, s20, longSequence_9_3,  longSequence_20_3, s9.length(), s20.length());
				System.out.println("S9 S20 VALUE =\t" + values[19][8]);
			}
			else if(i==10)
			{
				values[0][9] = av.findValue(s10, s1, longSequence_10_3,  longSequence_1_3, s10.length(), s1.length());
				System.out.println("S10 S1 VALUE =\t" + values[0][9]);
				
				values[1][9] = av.findValue(s10, s2, longSequence_10_3,  longSequence_2_3, s10.length(), s2.length());
				System.out.println("S10 S2 VALUE =\t" + values[1][9]);
				
				values[2][9] = av.findValue(s10, s3, longSequence_10_3,  longSequence_3_3, s10.length(), s3.length());
				System.out.println("S10 S3 VALUE =\t" + values[2][9]);
				
				values[3][9] = av.findValue(s10, s4, longSequence_10_3,  longSequence_4_3, s10.length(), s4.length());
				System.out.println("S10 S4 VALUE =\t" + values[3][9]);
				
				values[4][9] = av.findValue(s10, s5, longSequence_10_3,  longSequence_5_3, s10.length(), s5.length());
				System.out.println("S10 S5 VALUE =\t" + values[4][9]);
				
				values[5][9] = av.findValue(s10, s6, longSequence_10_3,  longSequence_6_3, s10.length(), s6.length());
				System.out.println("S10 S6 VALUE =\t" + values[5][9]);
				
				values[6][9] = av.findValue(s10, s7, longSequence_10_3,  longSequence_7_3, s10.length(), s7.length());
				System.out.println("S10 S7 VALUE =\t" + values[6][9]);
				
				values[7][9] = av.findValue(s10, s8, longSequence_10_3,  longSequence_8_3, s10.length(), s8.length());
				System.out.println("S10 S8 VALUE =\t" + values[7][9]);
				
				values[8][9] = av.findValue(s10, s9, longSequence_10_3,  longSequence_10_3, s10.length(), s9.length());
				System.out.println("S10 S9 VALUE =\t" + values[8][9]);
				
				values[10][9] = av.findValue(s10, s11, longSequence_10_3,  longSequence_11_3, s10.length(), s11.length());
				System.out.println("S10 S11 VALUE =\t" + values[10][9]);
				
				values[11][9] = av.findValue(s10, s12, longSequence_10_3,  longSequence_12_3, s10.length(), s12.length());
				System.out.println("S10 S12 VALUE =\t" + values[11][9]);
				
				values[12][9] = av.findValue(s10, s13, longSequence_10_3,  longSequence_13_3, s10.length(), s13.length());
				System.out.println("S10 S13 VALUE =\t" + values[12][9]);
				
				values[13][9] = av.findValue(s10, s14, longSequence_10_3,  longSequence_14_3, s10.length(), s14.length());
				System.out.println("S10 S14 VALUE =\t" + values[13][9]);
				
				values[14][9] = av.findValue(s10, s15, longSequence_10_3,  longSequence_15_3, s10.length(), s15.length());
				System.out.println("S10 S15 VALUE =\t" + values[14][9]);
				
				values[15][9] = av.findValue(s10, s16, longSequence_10_3,  longSequence_16_3, s10.length(), s16.length());
				System.out.println("S10 S16 VALUE =\t" + values[15][9]);
				
				values[16][9] = av.findValue(s10, s17, longSequence_10_3,  longSequence_17_3, s10.length(), s17.length());
				System.out.println("S10 S17 VALUE =\t" + values[16][9]);
				
				values[17][9] = av.findValue(s10, s18, longSequence_10_3,  longSequence_18_3, s10.length(), s18.length());
				System.out.println("S10 S18 VALUE =\t" + values[17][9]);
				
				values[18][9] = av.findValue(s10, s19, longSequence_10_3,  longSequence_19_3, s10.length(), s19.length());
				System.out.println("S10 S19 VALUE =\t" + values[18][9]);
				
				values[19][9] = av.findValue(s10, s20, longSequence_10_3,  longSequence_20_3, s10.length(), s20.length());
				System.out.println("S10 S20 VALUE =\t" + values[19][9]);
			}
			
			else if(i==11)
			{
				values[0][10] = av.findValue(s11, s1, longSequence_11_3,  longSequence_1_3, s11.length(), s1.length());
				System.out.println("S11 S1 VALUE =\t" + values[0][10]);
				
				values[1][10] = av.findValue(s11, s2, longSequence_11_3,  longSequence_2_3, s11.length(), s2.length());
				System.out.println("S11 S2 VALUE =\t" + values[1][10]);
				
				values[2][10] = av.findValue(s11, s3, longSequence_11_3,  longSequence_3_3, s11.length(), s3.length());
				System.out.println("S11 S3 VALUE =\t" + values[2][10]);
				
				values[3][10] = av.findValue(s11, s4, longSequence_11_3,  longSequence_4_3, s11.length(), s4.length());
				System.out.println("S11 S4 VALUE =\t" + values[3][10]);
				
				values[4][10] = av.findValue(s11, s5, longSequence_11_3,  longSequence_5_3, s11.length(), s5.length());
				System.out.println("S11 S5 VALUE =\t" + values[4][10]);
				
				values[5][10] = av.findValue(s11, s6, longSequence_11_3,  longSequence_6_3, s11.length(), s6.length());
				System.out.println("S11 S6 VALUE =\t" + values[5][10]);
				
				values[6][10] = av.findValue(s11, s7, longSequence_11_3,  longSequence_7_3, s11.length(), s7.length());
				System.out.println("S11 S7 VALUE =\t" + values[6][10]);
				
				values[7][10] = av.findValue(s11, s8, longSequence_11_3,  longSequence_8_3, s11.length(), s8.length());
				System.out.println("S11 S8 VALUE =\t" + values[7][10]);
				
				values[8][10] = av.findValue(s11, s9, longSequence_11_3,  longSequence_9_3, s11.length(), s9.length());
				System.out.println("S11 S9 VALUE =\t" + values[8][10]);
				
				values[9][10] = av.findValue(s11, s10, longSequence_11_3,  longSequence_10_3, s11.length(), s10.length());
				System.out.println("S11 S10 VALUE =\t" + values[9][10]);
				
				values[11][10] = av.findValue(s11, s12, longSequence_11_3,  longSequence_12_3, s11.length(), s12.length());
				System.out.println("S11 S12 VALUE =\t" + values[11][10]);
				
				values[12][10] = av.findValue(s11, s13, longSequence_11_3,  longSequence_13_3, s11.length(), s13.length());
				System.out.println("S11 S13 VALUE =\t" + values[12][10]);
				
				values[13][10] = av.findValue(s11, s14, longSequence_11_3,  longSequence_14_3, s11.length(), s14.length());
				System.out.println("S11 S14 VALUE =\t" + values[13][10]);
				
				values[14][10] = av.findValue(s11, s15, longSequence_11_3,  longSequence_15_3, s11.length(), s15.length());
				System.out.println("S11 S15 VALUE =\t" + values[14][10]);
				
				values[15][10] = av.findValue(s11, s16, longSequence_11_3,  longSequence_16_3, s11.length(), s16.length());
				System.out.println("S11 S16 VALUE =\t" + values[15][10]);
				
				values[16][10] = av.findValue(s11, s17, longSequence_11_3,  longSequence_17_3, s11.length(), s17.length());
				System.out.println("S11 S17 VALUE =\t" + values[16][10]);
				
				values[17][10] = av.findValue(s11, s18, longSequence_11_3,  longSequence_18_3, s11.length(), s18.length());
				System.out.println("S11 S18 VALUE =\t" + values[17][10]);
				
				values[18][10] = av.findValue(s11, s19, longSequence_11_3,  longSequence_19_3, s11.length(), s19.length());
				System.out.println("S11 S19 VALUE =\t" + values[18][10]);
				
				values[19][10] = av.findValue(s11, s20, longSequence_11_3,  longSequence_20_3, s11.length(), s20.length());
				System.out.println("S11 S20 VALUE =\t" + values[19][10]);
			}
			else if(i==12)
			{
				values[0][11] = av.findValue(s12, s1, longSequence_12_3,  longSequence_1_3, s12.length(), s1.length());
				System.out.println("S12 S1 VALUE =\t" + values[0][11]);
				
				values[1][11] = av.findValue(s12, s2, longSequence_12_3,  longSequence_2_3, s12.length(), s2.length());
				System.out.println("S12 S2 VALUE =\t" + values[1][11]);
				
				values[2][11] = av.findValue(s12, s3, longSequence_12_3,  longSequence_3_3, s12.length(), s3.length());
				System.out.println("S12 S3 VALUE =\t" + values[2][11]);
				
				values[3][11] = av.findValue(s12, s4, longSequence_12_3,  longSequence_4_3, s12.length(), s4.length());
				System.out.println("S12 S4 VALUE =\t" + values[3][11]);
				
				values[4][11] = av.findValue(s12, s5, longSequence_12_3,  longSequence_5_3, s12.length(), s5.length());
				System.out.println("S12 S5 VALUE =\t" + values[4][11]);
				
				values[5][11] = av.findValue(s12, s6, longSequence_12_3,  longSequence_6_3, s12.length(), s6.length());
				System.out.println("S12 S6 VALUE =\t" + values[5][11]);
				
				values[6][11] = av.findValue(s12, s7, longSequence_12_3,  longSequence_7_3, s12.length(), s7.length());
				System.out.println("S12 S7 VALUE =\t" + values[6][11]);
				
				values[7][11] = av.findValue(s12, s8, longSequence_12_3,  longSequence_8_3, s12.length(), s8.length());
				System.out.println("S12 S8 VALUE =\t" + values[7][11]);
				
				values[8][11] = av.findValue(s12, s9, longSequence_12_3,  longSequence_9_3, s12.length(), s9.length());
				System.out.println("S12 S9 VALUE =\t" + values[8][11]);
				
				values[9][11] = av.findValue(s12, s10, longSequence_12_3,  longSequence_10_3, s12.length(), s10.length());
				System.out.println("S12 S10 VALUE =\t" + values[9][11]);
				
				values[10][11] = av.findValue(s12, s11, longSequence_12_3,  longSequence_11_3, s12.length(), s11.length());
				System.out.println("S12 S11 VALUE =\t" + values[10][11]);
				
				values[12][11] = av.findValue(s12, s13, longSequence_12_3,  longSequence_13_3, s12.length(), s13.length());
				System.out.println("S12 S13 VALUE =\t" + values[12][11]);
				
				values[13][11] = av.findValue(s12, s14, longSequence_12_3,  longSequence_14_3, s12.length(), s14.length());
				System.out.println("S12 S14 VALUE =\t" + values[13][11]);
				
				values[14][11] = av.findValue(s12, s15, longSequence_12_3,  longSequence_15_3, s12.length(), s15.length());
				System.out.println("S12 S15 VALUE =\t" + values[14][11]);
				
				values[15][11] = av.findValue(s12, s16, longSequence_12_3,  longSequence_16_3, s12.length(), s16.length());
				System.out.println("S12 S16 VALUE =\t" + values[15][11]);
				
				values[16][11] = av.findValue(s12, s17, longSequence_12_3,  longSequence_17_3, s12.length(), s17.length());
				System.out.println("S12 S17 VALUE =\t" + values[16][11]);
				
				values[17][11] = av.findValue(s12, s18, longSequence_12_3,  longSequence_18_3, s12.length(), s18.length());
				System.out.println("S12 S18 VALUE =\t" + values[17][11]);
				
				values[18][11] = av.findValue(s12, s19, longSequence_12_3,  longSequence_19_3, s12.length(), s19.length());
				System.out.println("S12 S19 VALUE =\t" + values[18][11]);
				
				values[19][11] = av.findValue(s12, s20, longSequence_12_3,  longSequence_20_3, s12.length(), s20.length());
				System.out.println("S12 S20 VALUE =\t" + values[19][11]);
			}
			else if(i==13)
			{
				values[0][12] = av.findValue(s13, s1, longSequence_13_3,  longSequence_1_3, s13.length(), s1.length());
				System.out.println("S13 S1 VALUE =\t" + values[0][12]);
				
				values[1][12] = av.findValue(s13, s2, longSequence_13_3,  longSequence_2_3, s13.length(), s2.length());
				System.out.println("S13 S2 VALUE =\t" + values[1][12]);
				
				values[2][12] = av.findValue(s13, s3, longSequence_13_3,  longSequence_3_3, s13.length(), s3.length());
				System.out.println("S13 S3 VALUE =\t" + values[2][12]);
				
				values[3][12] = av.findValue(s13, s4, longSequence_13_3,  longSequence_4_3, s13.length(), s4.length());
				System.out.println("S13 S4 VALUE =\t" + values[3][12]);
				
				values[4][12] = av.findValue(s13, s5, longSequence_13_3,  longSequence_5_3, s13.length(), s5.length());
				System.out.println("S13 S5 VALUE =\t" + values[4][12]);
				
				values[5][12] = av.findValue(s13, s6, longSequence_13_3,  longSequence_6_3, s13.length(), s6.length());
				System.out.println("S13 S6 VALUE =\t" + values[5][12]);
				
				values[6][12] = av.findValue(s13, s7, longSequence_13_3,  longSequence_7_3, s13.length(), s7.length());
				System.out.println("S13 S7 VALUE =\t" + values[6][12]);
				
				values[7][12] = av.findValue(s13, s8, longSequence_13_3,  longSequence_8_3, s13.length(), s8.length());
				System.out.println("S13 S8 VALUE =\t" + values[7][12]);
				
				values[8][12] = av.findValue(s13, s9, longSequence_13_3,  longSequence_9_3, s13.length(), s9.length());
				System.out.println("S13 S9 VALUE =\t" + values[8][12]);
				
				values[9][12] = av.findValue(s13, s10, longSequence_13_3,  longSequence_10_3, s13.length(), s10.length());
				System.out.println("S13 S10 VALUE =\t" + values[9][12]);
				
				values[10][12] = av.findValue(s13, s11, longSequence_13_3,  longSequence_11_3, s13.length(), s11.length());
				System.out.println("S13 S11 VALUE =\t" + values[10][12]);
				
				values[11][12] = av.findValue(s13, s12, longSequence_13_3,  longSequence_12_3, s13.length(), s12.length());
				System.out.println("S13 S12 VALUE =\t" + values[11][12]);
				
				values[13][12] = av.findValue(s13, s14, longSequence_13_3,  longSequence_14_3, s13.length(), s14.length());
				System.out.println("S13 S14 VALUE =\t" + values[13][12]);
				
				values[14][12] = av.findValue(s13, s15, longSequence_13_3,  longSequence_15_3, s13.length(), s15.length());
				System.out.println("S13 S15 VALUE =\t" + values[14][12]);
				
				values[15][12] = av.findValue(s13, s16, longSequence_13_3,  longSequence_16_3, s13.length(), s16.length());
				System.out.println("S13 S16 VALUE =\t" + values[15][12]);
				
				values[16][12] = av.findValue(s13, s17, longSequence_13_3,  longSequence_17_3, s13.length(), s17.length());
				System.out.println("S13 S17 VALUE =\t" + values[16][12]);
				
				values[17][12] = av.findValue(s13, s18, longSequence_13_3,  longSequence_18_3, s13.length(), s18.length());
				System.out.println("S13 S18 VALUE =\t" + values[17][12]);
				
				values[18][12] = av.findValue(s13, s19, longSequence_13_3,  longSequence_19_3, s13.length(), s19.length());
				System.out.println("S13 S19 VALUE =\t" + values[18][12]);
				
				values[19][12] = av.findValue(s13, s20, longSequence_13_3,  longSequence_20_3, s13.length(), s20.length());
				System.out.println("S13 S20 VALUE =\t" + values[19][12]);
			}
			else if(i==14)
			{
				values[0][13] = av.findValue(s14, s1, longSequence_14_3,  longSequence_1_3, s14.length(), s1.length());
				System.out.println("S14 S1 VALUE =\t" + values[0][13]);
				
				values[1][13] = av.findValue(s14, s2, longSequence_14_3,  longSequence_2_3, s14.length(), s2.length());
				System.out.println("S14 S2 VALUE =\t" + values[1][13]);
				
				values[2][13] = av.findValue(s14, s3, longSequence_14_3,  longSequence_3_3, s14.length(), s3.length());
				System.out.println("S14 S3 VALUE =\t" + values[2][13]);
				
				values[3][13] = av.findValue(s14, s4, longSequence_14_3,  longSequence_4_3, s14.length(), s4.length());
				System.out.println("S14 S4 VALUE =\t" + values[3][13]);
				
				values[4][13] = av.findValue(s14, s5, longSequence_14_3,  longSequence_5_3, s14.length(), s5.length());
				System.out.println("S14 S5 VALUE =\t" + values[4][13]);
				
				values[5][13] = av.findValue(s14, s6, longSequence_14_3,  longSequence_6_3, s14.length(), s6.length());
				System.out.println("S14 S6 VALUE =\t" + values[5][13]);
				
				values[6][13] = av.findValue(s14, s7, longSequence_14_3,  longSequence_7_3, s14.length(), s7.length());
				System.out.println("S14 S7 VALUE =\t" + values[6][13]);
				
				values[7][13] = av.findValue(s14, s8, longSequence_14_3,  longSequence_8_3, s14.length(), s8.length());
				System.out.println("S14 S8 VALUE =\t" + values[7][13]);
				
				values[8][13] = av.findValue(s14, s9, longSequence_14_3,  longSequence_9_3, s14.length(), s9.length());
				System.out.println("S14 S9 VALUE =\t" + values[8][13]);
				
				values[9][13] = av.findValue(s14, s10, longSequence_14_3,  longSequence_10_3, s14.length(), s10.length());
				System.out.println("S14 S10 VALUE =\t" + values[9][13]);
				
				values[10][13] = av.findValue(s14, s11, longSequence_14_3,  longSequence_11_3, s14.length(), s11.length());
				System.out.println("S14 S11 VALUE =\t" + values[10][13]);
				
				values[11][13] = av.findValue(s14, s12, longSequence_14_3,  longSequence_12_3, s14.length(), s12.length());
				System.out.println("S14 S12 VALUE =\t" + values[11][13]);
				
				values[12][13] = av.findValue(s14, s13, longSequence_14_3,  longSequence_13_3, s14.length(), s13.length());
				System.out.println("S14 S13 VALUE =\t" + values[12][13]);
				
				values[14][13] = av.findValue(s14, s15, longSequence_14_3,  longSequence_15_3, s14.length(), s15.length());
				System.out.println("S14 S15 VALUE =\t" + values[14][13]);
				
				values[15][13] = av.findValue(s14, s16, longSequence_14_3,  longSequence_16_3, s14.length(), s16.length());
				System.out.println("S14 S16 VALUE =\t" + values[15][13]);
				
				values[16][13] = av.findValue(s14, s17, longSequence_14_3,  longSequence_17_3, s14.length(), s17.length());
				System.out.println("S14 S17 VALUE =\t" + values[16][13]);
				
				values[17][13] = av.findValue(s14, s18, longSequence_14_3,  longSequence_18_3, s14.length(), s18.length());
				System.out.println("S14 S18 VALUE =\t" + values[17][13]);
				
				values[18][13] = av.findValue(s14, s19, longSequence_14_3,  longSequence_19_3, s14.length(), s19.length());
				System.out.println("S14 S19 VALUE =\t" + values[18][13]);
				
				values[19][13] = av.findValue(s14, s20, longSequence_14_3,  longSequence_20_3, s14.length(), s20.length());
				System.out.println("S14 S20 VALUE =\t" + values[19][13]);
			}
			else if(i==15)
			{
				values[0][14] = av.findValue(s15, s1, longSequence_15_3,  longSequence_1_3, s15.length(), s1.length());
				System.out.println("S15 S1 VALUE =\t" + values[0][14]);
				
				values[1][14] = av.findValue(s15, s2, longSequence_15_3,  longSequence_2_3, s15.length(), s2.length());
				System.out.println("S15 S2 VALUE =\t" + values[1][14]);
				
				values[2][14] = av.findValue(s15, s3, longSequence_15_3,  longSequence_3_3, s15.length(), s3.length());
				System.out.println("S15 S3 VALUE =\t" + values[2][14]);
				
				values[3][14] = av.findValue(s15, s4, longSequence_15_3,  longSequence_4_3, s15.length(), s4.length());
				System.out.println("S15 S4 VALUE =\t" + values[3][14]);
				
				values[4][14] = av.findValue(s15, s5, longSequence_15_3,  longSequence_5_3, s15.length(), s5.length());
				System.out.println("S15 S5 VALUE =\t" + values[4][14]);
				
				values[5][14] = av.findValue(s15, s6, longSequence_15_3,  longSequence_6_3, s15.length(), s6.length());
				System.out.println("S15 S6 VALUE =\t" + values[5][14]);
				
				values[6][14] = av.findValue(s15, s7, longSequence_15_3,  longSequence_7_3, s15.length(), s7.length());
				System.out.println("S15 S7 VALUE =\t" + values[6][14]);
				
				values[7][14] = av.findValue(s15, s8, longSequence_15_3,  longSequence_8_3, s15.length(), s8.length());
				System.out.println("S15 S8 VALUE =\t" + values[7][14]);
				
				values[8][14] = av.findValue(s15, s9, longSequence_15_3,  longSequence_9_3, s15.length(), s9.length());
				System.out.println("S15 S9 VALUE =\t" + values[8][14]);
				
				values[9][14] = av.findValue(s15, s10, longSequence_15_3,  longSequence_10_3, s15.length(), s10.length());
				System.out.println("S15 S10 VALUE =\t" + values[9][14]);
				
				values[10][14] = av.findValue(s15, s11, longSequence_15_3,  longSequence_11_3, s15.length(), s11.length());
				System.out.println("S15 S11 VALUE =\t" + values[10][14]);
				
				values[11][14] = av.findValue(s15, s12, longSequence_15_3,  longSequence_12_3, s15.length(), s12.length());
				System.out.println("S15 S12 VALUE =\t" + values[11][14]);
				
				values[12][14] = av.findValue(s15, s13, longSequence_15_3,  longSequence_13_3, s15.length(), s13.length());
				System.out.println("S15 S13 VALUE =\t" + values[12][14]);
				
				values[13][14] = av.findValue(s15, s14, longSequence_15_3,  longSequence_14_3, s15.length(), s14.length());
				System.out.println("S15 S14 VALUE =\t" + values[13][14]);
				
				values[15][14] = av.findValue(s15, s16, longSequence_15_3,  longSequence_16_3, s15.length(), s16.length());
				System.out.println("S15 S16 VALUE =\t" + values[15][14]);
				
				values[16][14] = av.findValue(s15, s17, longSequence_15_3,  longSequence_17_3, s15.length(), s17.length());
				System.out.println("S15 S17 VALUE =\t" + values[16][14]);
				
				values[17][14] = av.findValue(s15, s18, longSequence_15_3,  longSequence_18_3, s15.length(), s18.length());
				System.out.println("S15 S18 VALUE =\t" + values[17][14]);
				
				values[18][14] = av.findValue(s15, s19, longSequence_15_3,  longSequence_19_3, s15.length(), s19.length());
				System.out.println("S15 S19 VALUE =\t" + values[18][14]);
				
				values[19][14] = av.findValue(s15, s20, longSequence_15_3,  longSequence_20_3, s15.length(), s20.length());
				System.out.println("S15 S20 VALUE =\t" + values[19][14]);
			}
			
			else if(i==16)
			{
				values[0][15] = av.findValue(s16, s1, longSequence_16_3,  longSequence_1_3, s16.length(), s1.length());
				System.out.println("S16 S1 VALUE =\t" + values[0][15]);
				
				values[1][15] = av.findValue(s16, s2, longSequence_16_3,  longSequence_2_3, s16.length(), s2.length());
				System.out.println("S16 S2 VALUE =\t" + values[1][15]);
				
				values[2][15] = av.findValue(s16, s3, longSequence_16_3,  longSequence_3_3, s16.length(), s3.length());
				System.out.println("S16 S3 VALUE =\t" + values[2][15]);
				
				values[3][15] = av.findValue(s16, s4, longSequence_16_3,  longSequence_4_3, s16.length(), s4.length());
				System.out.println("S16 S4 VALUE =\t" + values[3][15]);
				
				values[4][15] = av.findValue(s16, s5, longSequence_16_3,  longSequence_5_3, s16.length(), s5.length());
				System.out.println("S16 S5 VALUE =\t" + values[4][15]);
				
				values[5][15] = av.findValue(s16, s6, longSequence_16_3,  longSequence_6_3, s16.length(), s6.length());
				System.out.println("S16 S6 VALUE =\t" + values[5][15]);
				
				values[6][15] = av.findValue(s16, s7, longSequence_16_3,  longSequence_7_3, s16.length(), s7.length());
				System.out.println("S16 S7 VALUE =\t" + values[6][15]);
				
				values[7][15] = av.findValue(s16, s8, longSequence_16_3,  longSequence_8_3, s16.length(), s8.length());
				System.out.println("S16 S8 VALUE =\t" + values[7][15]);
				
				values[8][15] = av.findValue(s16, s9, longSequence_16_3,  longSequence_9_3, s16.length(), s9.length());
				System.out.println("S16 S9 VALUE =\t" + values[8][15]);
				
				values[9][15] = av.findValue(s16, s10, longSequence_16_3,  longSequence_10_3, s16.length(), s10.length());
				System.out.println("S16 S10 VALUE =\t" + values[9][15]);
				
				values[10][15] = av.findValue(s16, s11, longSequence_16_3,  longSequence_11_3, s16.length(), s11.length());
				System.out.println("S16 S11 VALUE =\t" + values[10][15]);
				
				values[11][15] = av.findValue(s16, s12, longSequence_16_3,  longSequence_12_3, s16.length(), s12.length());
				System.out.println("S16 S12 VALUE =\t" + values[11][15]);
				
				values[12][15] = av.findValue(s16, s13, longSequence_16_3,  longSequence_13_3, s16.length(), s13.length());
				System.out.println("S16 S13 VALUE =\t" + values[12][15]);
				
				values[13][15] = av.findValue(s16, s14, longSequence_16_3,  longSequence_14_3, s16.length(), s14.length());
				System.out.println("S16 S14 VALUE =\t" + values[13][15]);
				
				values[14][15] = av.findValue(s16, s15, longSequence_16_3,  longSequence_15_3, s16.length(), s15.length());
				System.out.println("S16 S15 VALUE =\t" + values[14][15]);
				
				values[16][15] = av.findValue(s16, s17, longSequence_16_3,  longSequence_17_3, s16.length(), s17.length());
				System.out.println("S16 S17 VALUE =\t" + values[16][15]);
				
				values[17][15] = av.findValue(s16, s18, longSequence_16_3,  longSequence_18_3, s16.length(), s18.length());
				System.out.println("S16 S18 VALUE =\t" + values[17][15]);
				
				values[18][15] = av.findValue(s16, s19, longSequence_16_3,  longSequence_19_3, s16.length(), s19.length());
				System.out.println("S16 S19 VALUE =\t" + values[18][15]);
				
				values[19][15] = av.findValue(s16, s20, longSequence_16_3,  longSequence_20_3, s16.length(), s20.length());
				System.out.println("S16 S20 VALUE =\t" + values[19][15]);
			}
			
			else if(i==17)
			{
				values[0][16] = av.findValue(s17, s1, longSequence_17_3,  longSequence_1_3, s17.length(), s1.length());
				System.out.println("S17 S1 VALUE =\t" + values[0][16]);
				
				values[1][16] = av.findValue(s17, s2, longSequence_17_3,  longSequence_2_3, s17.length(), s2.length());
				System.out.println("S17 S2 VALUE =\t" + values[1][16]);
				
				values[2][16] = av.findValue(s17, s3, longSequence_17_3,  longSequence_3_3, s17.length(), s3.length());
				System.out.println("S17 S3 VALUE =\t" + values[2][16]);
				
				values[3][16] = av.findValue(s17, s4, longSequence_17_3,  longSequence_4_3, s17.length(), s4.length());
				System.out.println("S17 S4 VALUE =\t" + values[3][16]);
				
				values[4][16] = av.findValue(s17, s5, longSequence_17_3,  longSequence_5_3, s17.length(), s5.length());
				System.out.println("S17 S5 VALUE =\t" + values[4][16]);
				
				values[5][16] = av.findValue(s17, s6, longSequence_17_3,  longSequence_6_3, s17.length(), s6.length());
				System.out.println("S17 S6 VALUE =\t" + values[5][16]);
				
				values[6][16] = av.findValue(s17, s7, longSequence_17_3,  longSequence_7_3, s17.length(), s7.length());
				System.out.println("S17 S7 VALUE =\t" + values[6][16]);
				
				values[7][16] = av.findValue(s17, s8, longSequence_17_3,  longSequence_8_3, s17.length(), s8.length());
				System.out.println("S17 S8 VALUE =\t" + values[7][16]);
				
				values[8][16] = av.findValue(s17, s9, longSequence_17_3,  longSequence_9_3, s17.length(), s9.length());
				System.out.println("S17 S9 VALUE =\t" + values[8][16]);
				
				values[9][16] = av.findValue(s17, s10, longSequence_17_3,  longSequence_10_3, s17.length(), s10.length());
				System.out.println("S17 S10 VALUE =\t" + values[9][16]);
				
				values[10][16] = av.findValue(s17, s11, longSequence_17_3,  longSequence_11_3, s17.length(), s11.length());
				System.out.println("S17 S11 VALUE =\t" + values[10][16]);
				
				values[11][16] = av.findValue(s17, s12, longSequence_17_3,  longSequence_12_3, s17.length(), s12.length());
				System.out.println("S17 S12 VALUE =\t" + values[11][16]);
				
				values[12][16] = av.findValue(s17, s13, longSequence_17_3,  longSequence_13_3, s17.length(), s13.length());
				System.out.println("S17 S13 VALUE =\t" + values[12][16]);
				
				values[13][16] = av.findValue(s17, s14, longSequence_17_3,  longSequence_14_3, s17.length(), s14.length());
				System.out.println("S17 S14 VALUE =\t" + values[13][16]);
				
				values[14][16] = av.findValue(s17, s15, longSequence_17_3,  longSequence_15_3, s17.length(), s15.length());
				System.out.println("S17 S15 VALUE =\t" + values[14][16]);
				
				values[15][16] = av.findValue(s17, s16, longSequence_17_3,  longSequence_16_3, s17.length(), s16.length());
				System.out.println("S17 S16 VALUE =\t" + values[15][16]);
				
				values[17][16] = av.findValue(s17, s18, longSequence_17_3,  longSequence_18_3, s17.length(), s18.length());
				System.out.println("S17 S18 VALUE =\t" + values[17][16]);
				
				values[18][16] = av.findValue(s17, s19, longSequence_17_3,  longSequence_19_3, s17.length(), s19.length());
				System.out.println("S17 S19 VALUE =\t" + values[18][16]);
				
				values[19][16] = av.findValue(s17, s20, longSequence_17_3,  longSequence_20_3, s17.length(), s20.length());
				System.out.println("S17 S20 VALUE =\t" + values[19][16]);
			}
			
			else if(i==18)
			{
				values[0][17] = av.findValue(s18, s1, longSequence_18_3,  longSequence_1_3, s18.length(), s1.length());
				System.out.println("S18 S1 VALUE =\t" + values[0][17]);
				
				values[1][17] = av.findValue(s18, s2, longSequence_18_3,  longSequence_2_3, s18.length(), s2.length());
				System.out.println("S18 S2 VALUE =\t" + values[1][17]);
				
				values[2][17] = av.findValue(s18, s3, longSequence_18_3,  longSequence_3_3, s18.length(), s3.length());
				System.out.println("S18 S3 VALUE =\t" + values[2][17]);
				
				values[3][17] = av.findValue(s18, s4, longSequence_18_3,  longSequence_4_3, s18.length(), s4.length());
				System.out.println("S18 S4 VALUE =\t" + values[3][17]);
				
				values[4][17] = av.findValue(s18, s5, longSequence_18_3,  longSequence_5_3, s18.length(), s5.length());
				System.out.println("S18 S5 VALUE =\t" + values[4][17]);
				
				values[5][17] = av.findValue(s18, s6, longSequence_18_3,  longSequence_6_3, s18.length(), s6.length());
				System.out.println("S18 S6 VALUE =\t" + values[5][17]);
				
				values[6][17] = av.findValue(s18, s7, longSequence_18_3,  longSequence_7_3, s18.length(), s7.length());
				System.out.println("S18 S7 VALUE =\t" + values[6][17]);
				
				values[7][17] = av.findValue(s18, s8, longSequence_18_3,  longSequence_8_3, s18.length(), s8.length());
				System.out.println("S18 S8 VALUE =\t" + values[7][17]);
				
				values[8][17] = av.findValue(s18, s9, longSequence_18_3,  longSequence_9_3, s18.length(), s9.length());
				System.out.println("S18 S9 VALUE =\t" + values[8][17]);
				
				values[9][17] = av.findValue(s18, s10, longSequence_18_3,  longSequence_10_3, s18.length(), s10.length());
				System.out.println("S18 S10 VALUE =\t" + values[9][17]);
				
				values[10][17] = av.findValue(s18, s11, longSequence_18_3,  longSequence_11_3, s18.length(), s11.length());
				System.out.println("S18 S11 VALUE =\t" + values[10][17]);
				
				values[11][17] = av.findValue(s18, s12, longSequence_18_3,  longSequence_12_3, s18.length(), s12.length());
				System.out.println("S18 S12 VALUE =\t" + values[11][17]);
				
				values[12][17] = av.findValue(s18, s13, longSequence_18_3,  longSequence_13_3, s18.length(), s13.length());
				System.out.println("S18 S13 VALUE =\t" + values[12][17]);
				
				values[13][17] = av.findValue(s18, s14, longSequence_18_3,  longSequence_14_3, s18.length(), s14.length());
				System.out.println("S18 S14 VALUE =\t" + values[13][17]);
				
				values[14][17] = av.findValue(s18, s15, longSequence_18_3,  longSequence_15_3, s18.length(), s15.length());
				System.out.println("S18 S15 VALUE =\t" + values[14][17]);
				
				values[15][17] = av.findValue(s18, s16, longSequence_18_3,  longSequence_16_3, s18.length(), s16.length());
				System.out.println("S18 S16 VALUE =\t" + values[15][17]);
				
				values[16][17] = av.findValue(s18, s17, longSequence_18_3,  longSequence_17_3, s18.length(), s17.length());
				System.out.println("S18 S17 VALUE =\t" + values[16][17]);
				
				values[18][17] = av.findValue(s18, s19, longSequence_18_3,  longSequence_19_3, s18.length(), s19.length());
				System.out.println("S18 S19 VALUE =\t" + values[18][17]);
				
				values[19][17] = av.findValue(s18, s20, longSequence_18_3,  longSequence_20_3, s18.length(), s20.length());
				System.out.println("S18 S20 VALUE =\t" + values[19][17]);
			}
			
			else if(i==19)
			{
				values[0][18] = av.findValue(s19, s1, longSequence_19_3,  longSequence_1_3, s19.length(), s1.length());
				System.out.println("S19 S1 VALUE =\t" + values[0][18]);
				
				values[1][18] = av.findValue(s19, s2, longSequence_19_3,  longSequence_2_3, s19.length(), s2.length());
				System.out.println("S19 S2 VALUE =\t" + values[1][18]);
				
				values[2][18] = av.findValue(s19, s3, longSequence_19_3,  longSequence_3_3, s19.length(), s3.length());
				System.out.println("S19 S3 VALUE =\t" + values[2][18]);
				
				values[3][18] = av.findValue(s19, s4, longSequence_19_3,  longSequence_4_3, s19.length(), s4.length());
				System.out.println("S19 S4 VALUE =\t" + values[3][18]);
				
				values[4][18] = av.findValue(s19, s5, longSequence_19_3,  longSequence_5_3, s19.length(), s5.length());
				System.out.println("S19 S5 VALUE =\t" + values[4][18]);
				
				values[5][18] = av.findValue(s19, s6, longSequence_19_3,  longSequence_6_3, s19.length(), s6.length());
				System.out.println("S19 S6 VALUE =\t" + values[5][18]);
				
				values[6][18] = av.findValue(s19, s7, longSequence_19_3,  longSequence_7_3, s19.length(), s7.length());
				System.out.println("S19 S7 VALUE =\t" + values[6][18]);
				
				values[7][18] = av.findValue(s19, s8, longSequence_19_3,  longSequence_8_3, s19.length(), s8.length());
				System.out.println("S19 S8 VALUE =\t" + values[7][18]);
				
				values[8][18] = av.findValue(s19, s9, longSequence_19_3,  longSequence_9_3, s19.length(), s9.length());
				System.out.println("S19 S9 VALUE =\t" + values[8][18]);
				
				values[9][18] = av.findValue(s19, s10, longSequence_19_3,  longSequence_10_3, s19.length(), s10.length());
				System.out.println("S19 S10 VALUE =\t" + values[9][18]);
				
				values[10][18] = av.findValue(s19, s11, longSequence_19_3,  longSequence_11_3, s19.length(), s11.length());
				System.out.println("S19 S11 VALUE =\t" + values[10][18]);
				
				values[11][18] = av.findValue(s19, s12, longSequence_19_3,  longSequence_12_3, s19.length(), s12.length());
				System.out.println("S19 S12 VALUE =\t" + values[11][18]);
				
				values[12][18] = av.findValue(s19, s13, longSequence_19_3,  longSequence_13_3, s19.length(), s13.length());
				System.out.println("S19 S13 VALUE =\t" + values[12][18]);
				
				values[13][18] = av.findValue(s19, s14, longSequence_19_3,  longSequence_14_3, s19.length(), s14.length());
				System.out.println("S19 S14 VALUE =\t" + values[13][18]);
				
				values[14][18] = av.findValue(s19, s15, longSequence_19_3,  longSequence_15_3, s19.length(), s15.length());
				System.out.println("S19 S15 VALUE =\t" + values[14][18]);
				
				values[15][18] = av.findValue(s19, s16, longSequence_19_3,  longSequence_16_3, s19.length(), s16.length());
				System.out.println("S19 S16 VALUE =\t" + values[15][18]);
				
				values[16][18] = av.findValue(s19, s17, longSequence_19_3,  longSequence_17_3, s19.length(), s17.length());
				System.out.println("S19 S17 VALUE =\t" + values[16][18]);
				
				values[17][18] = av.findValue(s19, s18, longSequence_19_3,  longSequence_18_3, s19.length(), s18.length());
				System.out.println("S19 S18 VALUE =\t" + values[17][18]);
				
				values[19][18] = av.findValue(s19, s20, longSequence_19_3,  longSequence_20_3, s19.length(), s20.length());
				System.out.println("S19 S20 VALUE =\t" + values[19][18]);
			}
			else if(i==20)
			{
				values[0][19] = av.findValue(s20, s1, longSequence_20_3,  longSequence_1_3, s20.length(), s1.length());
				System.out.println("S20 S1 VALUE =\t" + values[0][19]);
				
				values[1][19] = av.findValue(s20, s2, longSequence_20_3,  longSequence_2_3, s20.length(), s2.length());
				System.out.println("S20 S2 VALUE =\t" + values[1][19]);
				
				values[2][19] = av.findValue(s20, s3, longSequence_20_3,  longSequence_3_3, s20.length(), s3.length());
				System.out.println("S20 S3 VALUE =\t" + values[2][19]);
				
				values[3][19] = av.findValue(s20, s4, longSequence_20_3,  longSequence_4_3, s20.length(), s4.length());
				System.out.println("S20 S4 VALUE =\t" + values[3][19]);
				
				values[4][19] = av.findValue(s20, s5, longSequence_20_3,  longSequence_5_3, s20.length(), s5.length());
				System.out.println("S20 S5 VALUE =\t" + values[4][19]);
				
				values[5][19] = av.findValue(s20, s6, longSequence_20_3,  longSequence_6_3, s20.length(), s6.length());
				System.out.println("S20 S6 VALUE =\t" + values[5][19]);
				
				values[6][19] = av.findValue(s20, s7, longSequence_20_3,  longSequence_7_3, s20.length(), s7.length());
				System.out.println("S20 S7 VALUE =\t" + values[6][19]);
				
				values[7][19] = av.findValue(s20, s8, longSequence_20_3,  longSequence_8_3, s20.length(), s8.length());
				System.out.println("S20 S8 VALUE =\t" + values[7][19]);
				
				values[8][19] = av.findValue(s20, s9, longSequence_20_3,  longSequence_9_3, s20.length(), s9.length());
				System.out.println("S20 S9 VALUE =\t" + values[8][19]);
				
				values[9][19] = av.findValue(s20, s10, longSequence_20_3,  longSequence_10_3, s20.length(), s10.length());
				System.out.println("S20 S10 VALUE =\t" + values[9][19]);
				
				values[10][19] = av.findValue(s20, s11, longSequence_20_3,  longSequence_11_3, s20.length(), s11.length());
				System.out.println("S20 S11 VALUE =\t" + values[10][19]);
				
				values[11][19] = av.findValue(s20, s12, longSequence_20_3,  longSequence_12_3, s20.length(), s12.length());
				System.out.println("S20 S12 VALUE =\t" + values[11][19]);
				
				values[12][19] = av.findValue(s20, s13, longSequence_20_3,  longSequence_13_3, s20.length(), s13.length());
				System.out.println("S20 S13 VALUE =\t" + values[12][19]);
				
				values[13][19] = av.findValue(s20, s14, longSequence_20_3,  longSequence_14_3, s20.length(), s14.length());
				System.out.println("S20 S14 VALUE =\t" + values[13][19]);
				
				values[14][19] = av.findValue(s20, s15, longSequence_20_3,  longSequence_15_3, s20.length(), s15.length());
				System.out.println("S20 S15 VALUE =\t" + values[14][19]);
				
				values[15][19] = av.findValue(s20, s16, longSequence_20_3,  longSequence_16_3, s20.length(), s16.length());
				System.out.println("S20 S16 VALUE =\t" + values[15][19]);				
				
				values[16][19] = av.findValue(s20, s17, longSequence_20_3,  longSequence_17_3, s20.length(), s17.length());
				System.out.println("S20 S17 VALUE =\t" + values[16][19]);
				
				values[17][19] = av.findValue(s20, s18, longSequence_20_3,  longSequence_18_3, s20.length(), s18.length());
				System.out.println("S20 S18 VALUE =\t" + values[17][19]);
				
				values[18][19] = av.findValue(s20, s19, longSequence_20_3,  longSequence_19_3, s20.length(), s19.length());
				System.out.println("S20 S19 VALUE =\t" + values[18][19]);
			}
		}
*/
/*		double[] lengthString = {16559, 16554, 16364, 16389, 16563, 17009, 16338, 16295, 16300, 16398, 
				16826, 16797, 16402, 16660, 17019, 16829, 16499, 16472, 16896, 17084};				
		double[] logString = {9.7147, 9.7144, 9.7028, 9.7044, 9.7149, 9.7415, 9.7012, 9.6986, 9.6989, 9.7049, 
				9.7307, 9.7290, 9.7052, 9.7208, 9.7421, 9.7309, 9.7111, 9.7094, 9.7348, 9.7459};			
		double[] norm = {0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0011, 0.0012, 0.0012, 0.0012, 0.0012,
				0.0012, 0.0012, 0.0012, 0.0012, 0.0011, 0.0012, 0.0012, 0.0012, 0.0012, 0.0011};*/

		
/*		double[] lengthString = {17019, 16896, 17084, 16300, 16295, 17009, 16826, 16797, 16829, 16660, 
				16398, 16402, 16338, 16472, 16364, 16559, 16563, 16554, 16389, 16499};				
		double[] logString = {9.7421, 9.7348, 9.7459, 9.6989, 9.6986, 9.7415, 9.7307, 9.7290, 9.7309, 9.7208, 
				9.7049, 9.7052, 9.7012, 9.7094, 9.7028, 9.7147, 9.7149, 9.7144, 9.7044, 9.7111};			
		double[] norm = {0.0011, 0.0012, 0.0011, 0.0012, 0.0012, 0.0011, 0.0012, 0.0012, 0.0012, 0.0012,
				0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0012};
		
		
		for(int i = 0 ; i < values.length; i++)
		{
			for(int j = 0; j < values[0].length; j++)
			{
				if(!(i == j))
				{
					values[j][i] = (logString[j]/(values[j][i]/lengthString[i])) - norm[i] ;
				}
			}
		}
		
		for(int i = 0 ; i < values.length; i++)
		{
			double tmp = 0.0;
			
			for(int j = 0; j < values[0].length; j++)
			{
				if(!(i == j))
				{
					tmp = (values[j][i] + values[i][j])/2;
					acs[j][i] = tmp;
					acs[i][j] = tmp;
				}
			}
		}
		
		for(int i = 0 ; i < acs.length; i++)
		{
			//double tmp = 0.0;
			
			for(int j = 0; j < acs[0].length; j++)
			{
				System.out.print(acs[j][i]+"\t");
			}
			
			System.out.println();
		}
	}*/

	private String[] retriveArray(String location) {
		Scanner sc = null;
		
		try 
		{
			sc = new Scanner(new File("E:\\workspace\\GSOM\\Results\\20 Species - Same Weight\\Sequence Only\\5%\\" + location));
		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		List<String> lines = new ArrayList<String>();
		
		while (sc.hasNextLine())
		{
		  lines.add(sc.nextLine());
		}

		sc.close();
		String[] arr = lines.toArray(new String[0]);
		
		
		return arr;
	}
}