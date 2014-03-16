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
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\harbor seal - X63726.txt", 1); 
		String s1 = fp.readFileLine(); 
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\gray seal - X72004.txt", 1); 
		String s2 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cat - U20753.txt", 1); 
		String s3 = fp.readFileLine(); 
	
		String p = "ACT";
		
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
		
		ft.process();*/


		String[] longSequence_1 = {"AC", "TA", "AA", "AT", "CA",
				"TAA", "CAA", "AAC", "CAC", "CACA", "CACAA", "TAAC", "TAC", "TAACA",
				"TAACAA", "CACAC", "CACACA", "CACACAC", "AACA", "TACA", "AACAA", "TACAC", "AACAAC", "AAT",
				"AATA", "CAT", "CATA", "CATAA", "CATAAT", "CAAC", "TAAT", "AATAT", "AATATA", "AATATAA",
				"AATATAAT", "AACAT", "AACATA", "AACATAA", "AATAA", "CAAT", "CAACA", "CAACAA", "CAACAAT",
				"CAACAT", "CAACATA", "CAACATAA", "CAACATAAT", "CAACATAATA", "CAACATAATAC", "TAT", "TATA",
				"AACAAT", "TATAA", "TATAAC", "TAACAAC", "TAACAACA", "TAACAACAA", "TAATA", "TAATAC", "TAATACA",
				"TAATACAC", "AACAC", "AACACA", "AACACAA", "AATAAT", "AATAATA", "AATAATAA", "TATAAT", "TAATAT",
				"CAATA", "CAATAT", "AATAC", "TATAT", "AATACA", "AATACAC", "AACATAC", "TACAA", "TACAAT", "TACAATA",
				"TACAATAA", "TACAATAAC", "CACAT", "CACAAC", "AACAACA", "AACAACAT", "AACAACATA", "AACAACATAA", "TACAT",
				"TAATAA", "TAATAAT", "AATAAC", "AATAACA", "AATAACAT", "AATAACATA", "AATAACATAA", "CAATAA", "CAATAAT",
				"CATAC", "TACACA", "AATAACAA", "AATAACAAT", "AACACAC", "AATACAT", "AATACATA", "AATACATAC", "TAATAATA",
				"TAATAATAA", "TAATAATAAC", "TAATAATAACA", "TAACAAT", "AACAATA", "AACAATAT", "AACAATATA", "AATATAC",
				"AACACAT", "TATAC", "AATACAA", "AATACAAC", "AATACAACA", "AATACAACAT", "AATATAAC", "AATATAACA",
				"AATATAACAA", "AACAACAA", "AATAATAC", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT",
				"AACATACA"};
		
		System.out.println("========= X63726 ==========" + longSequence_1.length);
		
		
		String[] longSequence_2 = {"AC", "TA", "AA", "AT", "CA",
				"TAC", "TACA", "TACAC", "TACACA", "TACACAC", "TACACACA", "TACACACAC", "ACA", "ACAC",
				"ACACA", "ACACAC", "ACACACA", "CAC", "CACA", "CACAC", "CAA", "TAA", "TAAC", "TAACA", "TAACAA", "AAC",
				"AACA", "AACAA", "AACAAC", "TACAA", "TACAAC", "AACAC", "AACACA", "AACACAA", "CAAC", "CAACA", "CAACAC",
				"CAACACA", "CACAA", "CACAAC", "AACAACA", "TACAACA", "AAT", "AACACAC", "TAAT", "TACAT", "TAT", "AATA",
				"AATAC", "TATA", "TATAA", "TATAAT", "AACAT", "AACATA", "AATACA", "AATACAT", "AATACATA", "AATACATAC",
				"TATAT", "TAATA", "TAATAA", "TAATAAT", "TAATAATA", "TAATAATAA", "TAATAATAAC", "TAATAATAACA", "TAACAAT",
				"AATAA", "AATAAT", "AATAATA", "AATAATAT", "AATAATATA", "AATAT", "AATATA", "AATATAC", "AACACAT", "AATAAC",
				"AACAACAA", "AATAATAC", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "AACATAA",
				"AACATAT", "AACATATA", "AATAACA", "AATAACAA", "AACAAT", "AATACAC", "AACAACAT", "AACAACATA", "AACAACATAA",
				"AATAATATAA", "AATAACAAT"};
		
		System.out.println("========= X72004 ==========" + longSequence_2.length);
				
		String[] longSequence_3 = {"AC", "TA", "AA", "AT", "CA",
				"AAC", "AAT", "ATA", "ATAA", "ATAAT", "ATAATA", "ATAATAA", "ATAATAAT", "ATAATAATA", 
				"ATAATAATAA", "ATAT", "TAC", "AATA", "TAA", "TAAT", "TAAC", "ATAC", "AATAC", "ATAATAT", "AATAT", "ACA",
				"CAC", "TAT", "CAT", "CACA", "CAA", "AACA", "AACAT", "AACATA", "AACATAA", "AATAA", "CAAC", "AATACA", 
				"AATACAA", "AATACAAC", "CAAT", "CATA", "CATAA", "CATAAT", "CATAATA", "CATAATAA", "CATAATAAC", "CATAATAACA",
				"CATAATAACAA", "CATAATAACAAC", "AACAC", "AACACA", "AACACAA", "AACACAAC", "TACA", "CATAC", "CACAA", "AACAA",
				"AACAAC", "AACAACA", "TATA", "TATAA", "AATAAC", "TAATA", "TAATAT", "TACAC", "AACAACAT", "TATAC", "AACAAT",
				"TACAA", "TACAAT", "TACAATA", "TACAAC", "AACACAT", "TATAAC", "TAATAC", "AATACAT", "AATACATA", "AATACATAT",
				"AACATAAC", "AATACAC", "AATACACA", "AATACACAC", "AATACACACA", "AATACACACAC", "AATATA", "AATAAT", "AATAATA",
				"AATAATAC", "AATATAA", "AATATAAC", "AATATAACA", "AATATAACAA", "AATACAAT", "AACACAC", "AACACACA",
				"AACACACAA", "AACACACAAT", "AATATAT", "AACATAT", "AACATATA", "AACATATAA", "AATATAC"};
		
		System.out.println("========= U20753 ==========" + longSequence_3.length);
		
		String[] Test1 = {"ACB", "BC", "AA", "AB"};
		String[] Test2 = {"ACB", "BC"};
		
		String a = "ACBCAAAB";
		String b = "ACBCACBC";
		
		//AverageCommonSubstring av = new AverageCommonSubstring();
		AverageCommonSubstring av = new AverageCommonSubstring();
		
		System.out.println( s1.length()+"\t"+ s2.length() + "\t" + s3.length());
		System.out.println("VALUE =\t" + av.findValue(s3, s2, longSequence_3,  longSequence_2, s3.length(), s2.length()) );
		//System.out.println("VALUE =\t" + av.findValue(a,b, Test1, Test2,a.length(), b.length()) );
	}

}
