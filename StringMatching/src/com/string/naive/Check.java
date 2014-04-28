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
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\human - D38112.txt", 1); 
		String s1 = fp.readFileLine(); 
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\chimpanzee - D38113.txt", 1); 
		String s2 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gorilla - D38114.txt", 1); 
		String s3 = fp.readFileLine(); 
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\bornean orangutan - D38115.txt", 1); 
		String s4 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\bonobo - D38116.txt", 1); 
		String s5 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cat - U20753.txt", 1); 
		String s6 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cow - V00654.txt", 1); 
		String s7 = fp.readFileLine(); 
		
		/*fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gray seal - X72004.txt", 1); 
		String s8 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\indian rhinoceros - X97336.txt", 1); 
		String s9 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\horse - X79547.txt", 1); 
		String s10 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\fin whale - X61145.txt", 1); 
		String s11 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\blue whale - X72204.txt", 1); 
		String s12 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\"harbor seal - X63726.txt, 1); 
		String s13 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\common gibbon - X99256.txt", 1); 
		String s14 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\virginia opossum - Z29573.txt", 1); 
		String s15 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\platypus - X83427.txt", 1); 
		String s16 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\sumatran orangutan - X97707.txt", 1); 
		String s17 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\wallaroo - Y10524.txt", 1); 
		String s18 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\rat - X14848.txt", 1); 
		String s19 = fp.readFileLine();
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\mouse - V00711.txt", 1); 
		String s20 = fp.readFileLine();*/
		//String p = "ACT";
		
/*			NaiveStringMatching nv = null; 
		
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
/*		try
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
		
		ft.process();*/

//=========================================  Average Common Subsequence Calculation========================================================


	String[] longSequence_1_3 = (new Check()).retriveArray("1.txt");
			
	System.out.println("========= 1 ==========\t" + longSequence_1_3.length + "\tS1\t" + s1.length());

	String[] longSequence_2_3 = (new Check()).retriveArray("2.txt");
			
	System.out.println("========= 2 ==========\t" + longSequence_2_3.length+ "\tS2\t" + s2.length());

	String[] longSequence_3_3 = (new Check()).retriveArray("3.txt");
	
	System.out.println("========= 3 ==========\t" + longSequence_3_3.length + "\tS3\t" + s3.length());

	String[] longSequence_4_3 = (new Check()).retriveArray("4.txt");
			
	System.out.println("========= 4 ==========\t" + longSequence_4_3.length + "\tS4\t" + s4.length());

	String[] longSequence_5_3 = (new Check()).retriveArray("5.txt");
	
	System.out.println("========= 5 ==========\t" + longSequence_5_3.length + "\tS5\t" + s5.length());

	String[] longSequence_6_3 =(new Check()).retriveArray("6.txt");
	System.out.println("========= 6 ==========\t" + longSequence_6_3.length + "\tS6\t" + s6.length());
			
	String[] longSequence_7_3 =(new Check()).retriveArray("7.txt"); 
	System.out.println("========= 7 ==========\t" + longSequence_7_3.length + "\tS7\t" + s7.length());
			
	/*String[] longSequence_8_3 = {}; 
	System.out.println("========= V00654 ==========\t" + longSequence_8_3.length + "\tS8\t" + s8.length());
*/
//====================================================================================================================
		
	/*	String[] Test1 = {"ACB", "BC", "AA", "AB"};
		String[] Test2 = {"ACB", "BC"};
		
		String a = "ACBCAAAB";
		String b = "ACBCACBC";*/
		
		//AverageCommonSubstring av = new AverageCommonSubstring();
		AverageCommonSubstring av = new AverageCommonSubstring();
		
		double[][] values = new double[7][7];
		double[][] acs = new double[7][7];
		//System.out.println( s1.length()+"\t"+ s2.length() + "\t" + s3.length());
		//System.out.println("VALUE =\t" + av.findValue(s5, s4, longSequence_5_2,  longSequence_4_2, s5.length(), s4.length()));
	//	System.out.println("VALUE =\t" + av.findValue(a ,b, Test1, Test2, a.length(), b.length()) );
		

 		System.out.println("LCS -> 5% -> 5 Species -> Have Exist Check -> TEST 2");

		for(int i = 1; i <= 8; i++)
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
			}
			else if(i==6)
			{
				values[0][5] = av.findValue(s6, s1, longSequence_6_3,  longSequence_1_3, s6.length(), s1.length());
				System.out.println("S6 S1 VALUE =\t" + values[0][4]);
				
				values[1][5] = av.findValue(s6, s2, longSequence_6_3,  longSequence_2_3, s6.length(), s2.length());
				System.out.println("S6 S2 VALUE =\t" + values[1][4]);
				
				values[2][5] = av.findValue(s6, s3, longSequence_6_3,  longSequence_3_3, s6.length(), s3.length());
				System.out.println("S6 S3 VALUE =\t" + values[2][4]);
				
				values[3][5] = av.findValue(s6, s4, longSequence_6_3,  longSequence_4_3, s6.length(), s4.length());
				System.out.println("S6 S4 VALUE =\t" + values[3][4]);

				values[4][5] = av.findValue(s6, s5, longSequence_6_3,  longSequence_5_3, s6.length(), s5.length());
				System.out.println("S6 S5 VALUE =\t" + values[5][4]);
				
				values[6][5] = av.findValue(s6, s7, longSequence_6_3,  longSequence_7_3, s6.length(), s7.length());
				System.out.println("S6 S7 VALUE =\t" + values[6][5]);
			}
			else if(i==7)
			{
				values[0][6] = av.findValue(s7, s1, longSequence_7_3,  longSequence_1_3, s7.length(), s1.length());
				System.out.println("S7 S1 VALUE =\t" + values[0][1]);
				
				values[1][6] = av.findValue(s7, s2, longSequence_7_3,  longSequence_2_3, s7.length(), s2.length());
				System.out.println("S7 S2 VALUE =\t" + values[2][1]);
				
				values[2][6] = av.findValue(s7, s3, longSequence_7_3,  longSequence_3_3, s7.length(), s3.length());
				System.out.println("S7 S3 VALUE =\t" + values[3][1]);
				
				values[3][6] = av.findValue(s7, s4, longSequence_7_3,  longSequence_4_3, s7.length(), s4.length());
				System.out.println("S7 S4 VALUE =\t" + values[4][1]);
				
				values[4][6] = av.findValue(s7, s5, longSequence_7_3,  longSequence_5_3, s7.length(), s5.length());
				System.out.println("S7 S5 VALUE =\t" + values[5][1]);
				
				values[5][6] = av.findValue(s7, s6, longSequence_7_3,  longSequence_6_3, s7.length(), s6.length());
				System.out.println("S7 S6 VALUE =\t" + values[6][1]);
			}
		}

		double[] lengthString = {16559, 16554, 16364, 16389, 16563, 17009, 16338, 16295, 16300, 16398, 
				16826, 16797, 16402, 16660, 17019, 16829, 16499, 16472, 16896, 17084};				
		double[] logString = {9.7147, 9.7144, 9.7028, 9.7044, 9.7149, 9.7415, 9.7012, 9.6986, 9.6989, 9.7049, 
				9.7307, 9.7290, 9.7052, 9.7208, 9.7421, 9.7309, 9.7111, 9.7094, 9.7348, 9.7459};			
		double[] norm = {0.0012, 0.0012, 0.0012, 0.0012, 0.0012, 0.0011, 0.0012, 0.0012, 0.0012, 0.0012,
				0.0012, 0.0012, 0.0012, 0.0012, 0.0011, 0.0012, 0.0012, 0.0012, 0.0012, 0.0011};

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
	}

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