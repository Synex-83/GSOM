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
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\indian rhinoceros - X97336.txt", 1); 
		String s4 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\horse - X79547.txt", 1); 
		String s5 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\fin whale - X61145.txt", 1); 
		String s6 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\blue whale - X72204.txt", 1); 
		String s7 = fp.readFileLine(); 
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\cow - V00654.txt", 1); 
		String s8 = fp.readFileLine(); 
		
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
		
		String g[] = {"TG","GA","AG","GT","GG","CG","GC","TC"}; // "AC", "TA", "AA", "AT", "CA", "TT", "CC",  "CT"
		
		FilterSolid ft = new FilterSolid(g, temp);
		
		ft.process();
*/
//=========================================  Average Commom Subsequence Calculation========================================================

		String[] longSequence_1 = {"AC", "TA", "AA", "AT", "CA", "CC",
				"TAA", "CCA", "CCAA", "CAA", "AAC", "AACC", "CAC", "CACA", "CACAA", "TAAC", "TAACC",
		"TAC", "TAACA", "TAACAA", "CACAC", "CACACA", "CACACAC", "CACACACC", "CACC", "AACCA", "AACCAA", "AACCAAC", "AACCAACA",
		"AACCAACAC", "AACCAACACA", "AACCAACACAA", "AACA", "TACA", "AACAA", "TACAC", "TACACC", "CACACC", "AACAAC", "AAT", 
		"TACC", "AATA", "CAT", "CATA", "CATAA", "CATAAT", "CAAC", "TAAT", "AATAT", "AATATA", "AATATAA", "AATATAAT", "AACAT",
		"AACATA", "AACATAA", "AATAA", "CCAT", "CAAT", "CAACA", "CAACAA", "CAACAAT", "CAACAT", "CAACATA", "CAACATAA", 
		"CAACATAAT", "CAACATAATA", "CAACATAATAC", "CAACATAATACC", "CAACATAATACCA", "CAACATAATACCAA", "TAT", "TATA", "AACAAT",
		"TATAA", "TATAAC", "TATAACC", "CCAC", "TAACAAC", "TAACAACA", "TAACAACAA", "TAATA", "TAATAC", "TAATACA", "TAATACAC", 
		"TAATACACC", "CCAAC", "CCAACA", "CCAACAC", "CCAACACA", "AACAC", "AACACA", "AACACAA", "TACCA", "TACCAA", "CCACA", 
		"CCACAC", "CCAAT", "AATAAT", "AATAATA", "AATAATAA", "CAACC", "CAACCA", "CAACCAA", "CAACCAAC", "CAACCAACA", "AACAACC", 
		"CCACAT", "CCATA", "CCATAT", "TAACCA", "CCACC", "TATAAT", "TAATAT", "CCAACAT", "CCATAC", "CCATACC", "CCATACCA", 
		"CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "CAATA", "CAATAT", "AATAC", "CCATAA", "CCATAAT", "CCATAATA",
		"CCATAATAT", "AACCAACC", "CCATATA", "CCATATAT", "CCATATATA", "CCATATATAC", "CCATATATACC", "TATAT", "AATACA", "AATACAC",
		"AATACACC", "AATACACCA", "AATACACCAT", "AATACACCATA", "AATACACCATAT", "AACATAC", "AACCAT", "AACCATA", "AACCATAC", 
		"AACCATACC", "CCAACC", "TAACCAT", "CACCA", "CACCAC", "TACCAT", "TACCATA", "TACAA", "TACAAT", "TACAATA", "TACAATAA",
		"TACAATAAC", "TACAATAACC", "CACAT", "CACAAC", "AACAACA", "AACAACAT", "AACAACATA", "AACAACATAA", "CCATAAC", "CCATAACC",
		"CCAATA", "CCAATAA", "CCAATAAT", "CCAATAATA", "CCAATAATAT", "TACAT", "TAATAA", "TAATAAT", "AATAAC", "AATAACA", "AATAACAT",
		"AATAACATA", "AATAACATAA", "CAATAA", "CAATAAT", "CCACCA", "CCACCAT", "CCACCATA", "CCACCATAT", "CCACCATATA", "CCACCATATAT",
		"CATAC", "CCACACA", "CCACACAA", "TACACA", "AATAACAA", "AATAACAAT", "AACCAC", "AACCACA", "CCAACAA", "AACACAC", "CCACCAA",
		"AATACAT", "AATACATA", "AATACATAC", "TAATAATA", "TAATAATAA", "TAATAATAAC", "TAATAATAACA", "TAACAAT", "CCACCATAA",
		"CCACCATAAT", "CCACCATAATA", "CCACCATAATAC", "AACAATA", "AACAATAT", "AACAATATA", "AATACC", "AATATAC", "AATATACC",
		"CCACAA", "CCACAAT", "CCACAATA", "CCACAATAT", "AACCACC", "AACCACCA", "AACCACCAT", "CCACAAC", "CCACAACA", "CCACAACAA",
		"CCACAACAAT", "CCACAACAATA", "CCACAACAATAT", "AACCATAA", "AACACAT", "CCACCAC", "CCACCACA", "CCACCACAA", "CCACCACAAC",
		"CCACCACAACA", "CCACCACAACAA", "CCACCACAACAAC", "CCACCACAACAACC", "AATATACCA", "AATATACCAC", "AACAACCA", "AACAACCAC",
		"AACAACCACC", "TATAC", "TATACC", "TATACCA", "TATACCAA", "TATACCAAC", "AATAACC", "AATAACCA", "AATAACCAT", "AATACAA",
		"AATACAAC", "AATACAACA", "AATACAACAT", "AATATAAC", "AATATAACA", "AATATAACAA", "CCATAACA", "CCATAACAA", "AACAACAA",
		"AATAATAC", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "CCACACC", "CCACACCA", "CCACACCAT",
		"CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACCACC", "CCACCACCA", "CCATAACAT", "CCAATAAC", "CCAATAACC", "AACATACA"};

		System.out.println("========= X63726 ==========\t" + longSequence_1.length + "\tS1\t" + s1.length());


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

		System.out.println("========= X72004 ==========\t" + longSequence_2.length+ "\tS2\t" + s2.length());
				
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

		System.out.println("========= U20753 ==========\t" + longSequence_3.length + "\tS3\t" + s3.length());

		String[] longSequence_4 = {"AC", "TA", "AA", "AT", "CA", "CC",
				"ACC", "AAT", "AAC", "AACC", "TAC", "TACC", "TAT", "TAA", "TAAT", "TAATA", "TAATAA",
				"TAATAAT", "TAAC", "TAACC", "ATA", "ATAC", "AATA", "AATAT", "AATATA", "AATATAC", "ATAA", "ATAAT", "AATAA",
				"AATAAC", "AATAC", "ATAT", "AATAAT", "AATAATA", "AATAATAT", "ATAATA", "ATAATAA", "ATAATAAC", "AACA",
				"AACAT", "CCA", "CCAA", "CCAAC", "ACA", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "ACAACACC", "CCAC", "CCACC",
				"CCACCA", "CCACCAA", "CAT", "CATA", "CATAT", "CAC", "CACA", "CACAT", "CATAA", "CATAAT", "CCAACA",
				"CCAACAC", "CCAACACC", "CCACA", "CCACCAC", "CCACCACA", "CAA", "CAAT", "AACATA", "AACATAT", "AACATATA",
				"AACATATAC", "CCAAT", "CCAATA", "AACAA", "AACAAC", "AACCA", "AACCAA", "CCAT", "AACAACA", "AACAACAT",
				"AACAACATA", "AACAACATAA", "AACAACATAAT", "AATACC", "AACAC", "AACACC", "AACACCA", "AACACCAT", "CCAACAA",
				"CCAACAAT", "AACCAT", "CCATA", "CCATAT", "CCACAA", "CCACAAT", "CCACAATA", "CCACAATAA", "CCACAATAAT",
				"CCACAATAATA", "CCACAATAATAT", "CCACAC", "CCACACC", "CCACACCA", "AATAACA", "AATAACAA", "AACAAT",
				"AACAATA", "AACAATAC", "AACAATACC", "CCAACC", "AATAACC", "CCAACAAC", "CCAACAACC", "CCAACAACCA",
				"CCAACAACCAC", "CCAACAACCACC", "AATAACCA", "AATAACCAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACC",
				"AACAACC", "AACAACCA", "AACAACCAA", "CCATAA", "CCATAAC", "AACCAC", "AACCACC", "AACACA", "AACACAA",
				"AACACAAC", "AACACAACC", "AACATATAT", "AACATATATA", "AACATATATAA", "AACCAAC", "AACCAACA", "AACCAACAT",
				"AACCAACAA", "AATACA", "AATACAA", "AATACAAT", "AATACAATA", "AATACAATAC", "AACCACA", "AACCACAA",
				"AACACAT", "AACACATA", "AACCACAAC", "AACCACAACA", "AACCACAACAC", "AACCACAACACA", "AACCACAACACAA",
				"AACCACAACACAAC", "AACCACAACACAACA", "AACCACAACACAACAT", "AACCACAACACAACATA", "AACCACAACACAACATAT",
				"AACACAAT", "CCAACACA", "AACATAA", "AACCAAT", "AACCAATA", "AACCAACC", "CCATAC", "CCATACC", "CCACAT",
				"CCACATA", "CCACACA", "CCACACAT", "AATAATATA", "AATAATATAA", "AACCACCA", "AACACCAC", "AACACCACC",
				"CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACAAC", "CCACAACA", "CCACAACAT", "AATACCA", "AATACCAT",
				"AACCATA", "CCAACAATA", "CCAACAATAA", "CCAACAATAAT", "CCAACAATAATA", "CCATAAT", "CCATAATA", "CCATAATAA",
				"CCATAATAAC", "CCATAATAACA", "CCATAATAACAA", "CCAATAC"};

		System.out.println("========= X97336 ==========\t" + longSequence_4.length + "\tS4\t" + s4.length());

		String[] longSequence_5 = {"AC", "TA", "AA", "AT", "CA", "CC", "CT",
				"CAA", "AAC", "AACA", "ACA", "ACAC", "CAAC", "CAACA", "CAACAC", "CAACACT", "ACT", "AACT",
				"CAC", "CACT", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "CACA", "CACAC", "CACACA", "CACAA", "CAACT", "ACACT",
				"AACAC", "AACACA", "CACAAC", "CACAACT", "AACAA", "AACAAC", "AACAACT", "AACACT", "CACACT", "TAC", "TACT",
				"TACA", "ACTA", "TAA", "CAACACTA", "CAACACTAC", "CAACACTACA", "CAACACTACAA", "CAACACTACAAC", "CTA", "CTAA",
				"CAACAA", "CACTA", "CACTAA", "ACTAC", "ACTACT", "CTAC", "CTACA", "CTAAC", "AACTA", "AACTAA", "CAACACTAA",
				"CTACT", "CTACAC", "CTACACA", "CTACACAC", "CTACACACT", "CTACACACTA", "CTACACACTAA", "CTAACA", "CACTAC",
				"CTACTA", "CTACTAA", "TACTA", "TACTAC", "TACAA", "TACAAC", "TACAACA", "TACAACAA", "TACTAA", "TAAC",
				"TAACT", "TAACTA", "TACAC", "TACACA", "TACACAC", "TACACACA", "TACACACAC", "CTACTAC", "CTACTACT", "TAACA",
				"TAACAA", "AACACTA", "CTACAA", "CTAACAA", "CTAACAAC", "CAACACC", "CCT", "TAACC", "CCA", "ACC", "ACCT",
				"CCAA", "CCAAC", "CCAACC", "CCAACCT", "TACC", "TACCA", "TACCAA", "TACCAAC", "TACCAACC", "TACCAACCT", 
				"CCTA", "CCTAC", "CCTACA", "CCTACAC", "CCTACACC", "CCTACACCA", "CCTACT", "AACAACA", "AACAACAA", "CCAACT",
				"CCAACTA", "TAACCA", "CCTACC", "CCTACCT", "CCTACCTA", "CCTAA", "AACC", "CCAACA", "CCAACAC", "CCAACACA",
				"TACACC", "TACACCA", "TACACCAA", "TACACCAAC", "CCTACTA", "CCTACTAA", "CCAC", "CCACA", "CAACC", "CCACAA",
				"CCACAAC", "CCACAACC", "TACCT", "TACCTA", "TACCTAC", "TACCTACT", "TACACT", "TACACTA", "TACTACC", "CCAACAA",
				"CCACC", "CTAACC", "CTAACCT", "CTAACCTA", "AACCT", "TACTACT", "TAACCT", "CCACAC", "CCACACC", "CCACACCA",
				"CCACACCAC", "CCACACCACT", "CCACACCACTA", "CCACACCACTAA", "AACACTAC", "AACACTACC", "CCACT", "AACCA",
				"AACCAA", "AACCAAC", "AACCAACA", "ACACC", "TAACCAA", "AACAACC", "AACAACCA", "AACAACCAA", "AACAACCAAC",
				"AACAACCAACA", "AACACC", "AACACCA", "TACCAC", "TACCACA", "TACCACAA", "TACCACAAC", "TACCACAACC",
				"TACCACAACCT", "TACCACAACCTA", "TACCACAACCTAC", "TACCACAACCTACC", "AACTAAC", "AACTAACA", "AACTAACAA",
				"AACTAACAAC", "AACTAACAACA", "AACTAACAACAA", "TAACAC", "TAACACT", "AACACAA", "ACCA", "ACCAA", "AACACCAA",
				"CCACTA", "CCACTAA", "ACCTA", "ACCTAC", "ACCTACC", "ACCTACCA", "TACACTAC", "TACACTACA", "TACACTACAC", 
				"TACACTACACA", "ACAACT", "TAACTAC", "CTACC", "CTACCT", "ACAACTA", "ACAACTAC", "ACAACTACA", "ACAACTACAC",
				"ACAACTACACC", "ACACTA", "ACACTAA", "ACACTAAC", "ACACTAACA", "TACCACT", "CACC", "CACCA", "ACAACAA",
				"TAACACC", "TAACACCA", "CCACCT", "CCAACTAC", "ACTAA", "CAACCA", "CACCT", "CACCTA", "CACCTAC", "CACCTACC",
				"CCAACCA", "TACCAACA", "CACACTA", "TAACACCT", "ACACA", "CTACAAC", "CTACAACC", "CTACAACCA", "CTACAACCAA",
				"ACTACA", "ACTACAC", "CCACCA", "CACACAC", "CACACACC", "TACCTAA", "CTACCTA", "AACCAACT", "AACTAC", 
				"AACTACT", "AACTAACT", "AACAACAC", "AACAACACA", "AACAACACAC", "AACAACACACA", "AACAACACACAA", "AACAACCT",
				"ACTAAC", "ACTAACC", "ACTAACCA", "TAAT", "AACCAT", "ATA", "ATAA", "ATAAC", "ATAACA", "CAAT", "ACAAT",
				"ACAT", "CTAT", "AACTAAT", "AACTAATA", "ACATA", "ACATAA", "ACATAAT", "AAT", "CAT", "CATA", "CATAA", 
				"CCTAAC", "CCTAACA", "CCTAACAA", "CCTAACAAC", "CCTAACAACA", "CCTAACAACAT", "CCAAT", "CTATA", "CTATAT", 
				"CCAACAT", "CCTAT", "CCTACTAC", "CCTACTACA", "CCTACTACAA", "CCTACTACAAC", "CCTACTACAACC", "CCTACTACAACCT",
				"CCTACTACAACCTA", "CCTACTACAACCTAT", "AACTAT", "CCACTAC", "CCACTACA", "CCACTACAA", "CCACTACAAC",
				"CCACTACAACC", "CCACTACAACCA", "CCACTACAACCAC", "CCACTACAACCACT", "CCACTACAACCACTA", "CCACTACAACCACTAA",
				"CCACTACAACCACTAAC", "CCACTACAACCACTAACA", "CCACTACAACCACTAACAT", "CACCAA", "CACCAAT", "CCTAACC", "CCAT",
				"CCATA", "CCATAC", "CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC",
				"CCACTAAT", "AACCTA", "AATA", "AATAC", "CCTAAT", "AATACC", "AATACCT", "CCATAA", "CCATAAT", "AACCAACC",
				"ACCTAA", "ACCTAAC", "ACCTAACA", "AACAT", "ATAT", "ACCAAC", "ACCAACT", "CATAT", "CCACCAC", "CCACCACA",
				"CCACCACAA", "AACATA", "AATAT", "AACTACA", "AATAA", "AACTATA", "AACTATAA", "AACTATAAT", "ACTAAT", "ACTAATA",
				"ACTAATAA", "ACTAATAAT", "AACCATA", "AACAAT", "CCAATA", "CCAATAT", "CATAC", "CATACT", "CATATA", "AATAAC",
				"AATAACA", "CCAACACT", "CCAACACTA", "CCAACACTAA", "CCAACACTAAC", "CCAACACTAACA", "CCAACACTAACAA",
				"CCAACACTAACAAT", "CCAACACTAACAATA", "CCAACACTAACAATAC", "CCAACACTAACAATACT", "CCAACACTAACAATACTA",
				"CCAACACTAACAATACTAA", "AACATAT", "CCACACA", "CCACACAT", "CCACTAT", "AATAAT", "CCACACT", "CCACACTA",
				"CCACACTAA", "CCACACTAAT", "CCACACTAATA", "CACTACT", "ACTAACCT", "ACTAACCTA", "ACTAACCTAT", "ACCAT",
				"ACCATA", "ACCATAT", "AACAACAT", "AACAACATA", "AACAACATAA", "CCATACT", "CCATACTA", "CCATACTAT", "CATACTA",
				"CATACTAA", "CTACCA", "CTACCAT", "ACTAT", "ATAAT", "ATATA", "AACCAC", "AACCACC", "AACCACCA", "AACACTACT",
				"CTACAT", "CTATAA", "CTATAAT", "CTATAATA", "CTATAATAT", "ACACAC", "CTACCATA", "CTACCATAA", "CTACCATAAT",
				"CTATAC", "CTATACT", "CTAACT", "ATAC", "ATACT", "CACAT", "ATACA", "ATACAC", "ATACACA", "ATACACAC",
				"ATACACACT", "AATATA", "AATATAA", "AATATAAC", "AATATAACC", "AATATAACCT", "CCACAACA", "CCACAACAT",
				"CATATAC", "CATATACA", "CATATACAA", "CATATACAAC", "CATATACAACA", "CATATACAACAT", "AATACCA", "AATACCAT",
				"ATACTA", "ATACTAA", "ATACTAAT", "ATACTAATA", "ATACTAATAA", "ATACTAATAAT", "CCACCAT", "CCACCATA",
				"CCACCATAC", "CCACCATACC", "CCACCATACCA", "CCACCATACCAC", "CCACCATACCACA", "CCACCATACCACAC", 
				"CCACCATACCACACA", "CCACCATACCACACAT", "CCACCTA", "CCACCTAC", "AACCTAA", "CCTATA", "CCTATAT", "CCTATATA",
				"CCTATATAC", "CCTATATACC", "CCTATATACCT", "CAACAT", "ACCAC", "ACCACA", "ACCACAC", "ACCACACA",
				"ACCACACAC", "ACCACACACT", "ACCACACACTA", "ACCACACACTAA", "ACCACACACTAAT", "ACCACACACTAATA",
				"ACCACACACTAATAA", "ACCACACACTAATAAT", "CACCAT", "CACCATA", "AACAATA", "CCACCAA", "CCACCAAT", "CCACAT",
				"AATAACC", "AATAACCA", "AATAACCAT", "CTAAT", "AACCACA", "ATACAT", "ACACACC", "ACAACC", "ACAACCA",
				"ACAACCAA", "AATAACAA", "CCAACAAT", "CCAACAATA", "AATAATA", "CCTACTACC", "CCTACTACCT", "CTAACAT", 
				"ACACCT", "ACAACACC", "ACAACACCT", "ACAACACCTA", "ACAACACCTAA", "ACAACACCTAAT", "ACCACT", "ATAACAT", 
				"CATAAT", "ACCACC", "CCTAATA", "AATACT", "AATACTA", "AATACTAT", "CCAACTAA", "CCACCACT", "AATAATAA", 
				"ACTACC", "ACTACCA", "ACTACCAC", "ACTACCACA", "ACTACCACAA", "ACTACCACAAC", "ACTACCACAACT", "ACTACCACAACTA",
				"ACTACCACAACTAA", "CCATAT", "AACAACTA", "AACAACTAA", "AACAACTAAC", "AACAACTAACC", "AACAACTAACCT",
				"AACAACTAACCTA", "AACAACTAACCTAA", "AACACTAT", "AACACTATA", "AACACTATAC", "AACACTATACT", "CCTACTAAT",
				"CCACCTAA", "CCACCTAAT", "CCACCTAATA", "CCACCTAATAT", "AATAATAC", "AATAATACT", "AATAATACTA", "AATAATACTAT",
				"CAACCAT", "CAACCATA", "CAACCATAA", "CAACCATAAT", "CAACCATAATA", "CCACAAT", "AATATAAT", "AACCAACCT", 
				"AACCAACCTA", "CAACAAT", "CAACAATA", "ACAACAT", "CCACTAATA", "CCACTAATAC", "CCACTAATACT", "CCAACACC",
				"CCAACACCA", "CCAACACCAT", "AACCACT", "AACCACTA", "AACCACTAA", "AACCACTAAT", "CTAACCA", "CTAACCAT",
				"CTAACCATA", "CTAACCATAC", "CTAACCATACT", "CAACCAA", "CACTAAT", "AACCTAAC", "AACCTAACA", "CACTACC",
				"AATACTAC", "CCACATA", "AACACCAT", "CCTAATAA", "CCTAATAAT", "CCTAACCA", "CCTAACCAA", "CCTAACCAAC",
				"CCTAACCAACC", "CCTAACCAACCT", "CCTATAC", "CCTATACA", "CCTATACAT", "CCTATACATA", "CCTATACATAC", 
				"CCTATACATACT", "CCTATACATACTA", "CCTATACATACTAA", "CCTATACATACTAAT", "AATACA", "AATACAC", "AATACACA",
				"AATACACAC", "AATACACACA", "AATACACACAC", "AATACACACACC", "AATACACACACCA", "AATACACACACCAT",
				"AATACACACACCATA", "AATACACACACCATAT", "CCTACTACT", "CCACTATA", "CCACTATAA", "CCACTATAAT",
				"CCACTATAATA", "CCACTATAATAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACA", "AATAACCATACAA",
				"CTAACAACC", "CTAACAACCT", "CACAAT", "CACAATA", "CTATACTA", "CTATACTAT", "CAATA", "CAATAC",
				"CAATACC", "CCAACACAT", "CCTACTAATA", "CCTACTAATAA", "CCTACTAATAAC", "CCTACTAATAACT", "AATAACCATACC",
				"AATAACCATACCT", "AATAACCATACCTA", "AATAACCATACCTAC", "AATAACCATACCTACT", "ATAACC", "CCATAAC", "CCATAACT",
				"ATACTAC", "ATACTACC", "CTAACCTAT", "CATACTAC", "CATACTACT", "CCATAACTA", "CCATAACTAT", "CCATAACTATA", 
				"CCATAACTATAC", "CCATAACTATACA", "CCATAACTATACAA", "CCATAACTATACAAT", "CCTATAA", "CCTATAAT", "AACATATA",
				"AACATATAA", "AACCAACAT", "AACCAACATA", "AACCAACATAA", "AACCAACATAAC", "AACCAACATAACC", "AACCAACATAACCT",
				"CCATAACA", "CCATACCT", "AACAACCAACAT", "AATAACAC", "AATAACACT", "AACACAAT", "AACACAATA", "AACACAATAT", 
				"AACACAATATA", "AACACAATATAT", "ACCAAT", "AACACCAAT", "CCATACA", "CCATACAC", "CCATACACT", "CCATACACTA",
				"CCATACACTAC", "CCATACACTACA", "CCATACACTACAC", "CCATACACTACACA", "CCATACACTACACAT", "CCTACAT", "CATAATA",
				"CATAATAT", "ACACTAACAT", "ACAACAAT", "CCACATAA", "CCACATAAC", "CCACATAACA", "CCACATAACAC", "CCACATAACACC",
				"CCACATAACACCA", "CCACATAACACCAT", "CCACATAACACCATA", "CCACATAACACCATAC", "CCACATAACACCATACC", "CAATAT",
				"CATACC", "AACTATAC", "AACTATACC", "AACTATACCT", "CACAATAC", "CACAATACT", "CCAACCAT", "CCAACCATA", 
				"CCAACCATAA", "CCAACCATAAT", "CCAACCATAATA", "CCAACCATAATAA", "CCAACCATAATAAC", "CCAACCATAATAACT", 
				"AACACAT", "AACACATA", "ACTAATAC", "ACTAATACC", "ACTAATACCA", "ACTAATACCAA", "AACAATAC", "AACAATACC",
				"AACAATACCT", "AACAATACCTA", "AACAATACCTAA", "AACAATACCTAAT", "CTACCTAT", "CAATAA", "CAATAAT", "AATATAT",
				"AATATATA", "AATATATAA", "AATATATAAT"};

		System.out.println("========= X79547 ==========\t" + longSequence_5.length + "\tS5\t" + s5.length());

		String[] longSequence_6 = {"TA", "AA", "AT", "AC", "CT", "CA", "CC",
				"AAC", "AACC", "TAA", "ACC", "TAAC", "TAC", "TACC", "CCA", "CCAC", "CCACA", "TAACC", "CAA", "CAC", "CACA", "CACAA", "CACAAC", "CACAACC", "CCACC", "CCACCA", "CCACCAC", "CCAA", "TACCA", "TACCAA", "TACCAAC", "TACCAACA", "CAAC", "CAACC", "CCACAA", "CACAC", "CACACC", "CACACCA", "CACACCAA", "TACA", "CACC", "CACCA", "CACCAC", "CACCACA", "CAACCA", "CAACCAA", "CAACCAAC", "CAACCAACC", "CAACCAACCA", "CAACCAACCAC", "CAACCAACCACA", "CAACCAACCACAA", "TAACA", "CACAACCA", "CAACA", "AACCA", "AACCAC", "AACCACC", "TACAA", "TACAAC", "TACCAACAC", "TACCAACACC", "CACACA", "TACAC", "CCACAAC", "CCACAACA", "CCACAACAC", "CCAAC", "AACA", "AACAA", "CACCAA", "CCAACA", "TAACAA", "AACCAA", "AACAAC", "AACAACC", "CAACAC", "AACCACA", "AACAC", "AACACA", "AACACAA", "AACAACA", "AACAACAA", "AACACC", "AACACCA", "AACACCAC", "AACACCACA", "CAT", "CCAT", "AAT", "CAACAA", "CAACAAC", "CAACAACC", "CCAACAA", "CCAACAAT", "CACAT", "TACACA", "CCACCACA", "CCACCACAC", "CCACCACACA", "CCACCACACAA", "CCACCACACAAC", "CAAT", "CAATA", "CAATAA", "AATA", "AATAT", "CCATA", "CCATAT", "TAAT", "CACAAT", "TAT", "CCACCAA", "CATA", "CATAC", "CATACC", "CATACCA", "CATACCAC", "CATACCACA", "CATACCACAT", "CATACCACATA", "CAATAC", "CATACA", "CATACAC", "CATACACC", "CATACACCA", "CATACACCAA", "CATACACCAAC", "CATACACCAACC", "CAACACC", "CCACCAT", "CCACCATA", "AACACAT", "AACACATA", "AACACATAC", "AACCACCA", "AACCACCAC", "CCAAT", "AATAC", "AATACA", "AATACAA", "AATACAAC", "AATACAACA", "AATACAACAC", "TATA", "TATAC", "CCATAA", "AACACAAT", "AACACAATA", "AACACAATAC", "CACAATA", "CCATATA", "CCATATAA", "AACAT", "TATAA", "TATAAT", "TATAATA", "TATAATAC", "AATAA", "AATAAC", "AATAACC", "CATAT", "ACT", "CTA", "CTAT", "CTATA", "CTATAC", "CTATACA", "CTATACAC", "CTATACACT", "CTATACACTA", "CCACT", "CCACTA", "CCACTAC", "CCACTACT", "CCACTACTA", "TATAT", "CCT", "CCTA", "CCTAA", "AACT", "CTAC", "CTACT", "CCTAC", "CCTACA", "CCTACAA", "CCTACAAC", "CCTACAACA", "CCTACAACAC", "CCTACAACACT", "CAACCAT", "CCAACAT", "AATACC", "AATACCT", "TACT", "TACTA", "TACTAC", "TACTACT", "TACTACTA", "TACTACTAA", "CCATAC", "CCATACT", "CATAA", "CATAAC", "CATAACC", "CATAACCA", "CACT", "CACTA", "CACTAA", "CACTAAT", "CACATA", "CCTAAT", "CCTAATA", "CCTAATAA", "CCTAATAAT", "CCATACTA", "CCATACTAT", "CTACC", "CTACCA", "CTACCAC", "CTACCACT", "CTACCACTA", "CTACCACTAA", "CTACCACTAAT", "CTACCACTAATA", "CCAACC", "CCAACCA", "CCAACCAT", "CAACCT", "CAACCTA", "CAACCTAA", "CAACCTAAT", "CCACTAT", "CTATAT", "CTATATA", "CTATATAT", "CTATATATA", "CTATATATAC", "CTATATATACT", "CTATATATACTA", "CTATATATACTAA", "CTATATATACTAAT", "CATAACA", "CATAACAC", "CATAACACA", "CATAACACAA", "CATAACACAAC", "AACACAC", "AACACACA", "AACACACAC", "AACACACACA", "AACACACACAC", "AACACACACACC", "AACACACACACCA", "AACACACACACCAC", "AACACACACACCACA", "AACACACACACCACAT", "CAATAAT", "CAATAATA", "CAATAATAT", "CCTACAC", "CCTACACA", "CCTACACAT", "CCTAT", "AACTA", "AACTAA", "AACTAAC", "AACTAACA", "AACTAACAA", "AACTAACAAT", "AACTAACAATA", "CCACAC", "CCACACC", "CCACACCT", "CCACACCTA", "CCACACCTAA", "CCACACCTAAC", "CCACACCTAACA", "TAATA", "TAATAA", "AACCT", "AACCTA", "AACCTAT", "CCTACT", "CACATAC", "CACATACA", "CACACAA", "AACACT", "AACTAACAC", "AACTAACACT", "CAATAT", "CCTACAT", "CAACCAAT", "AATACT", "CACCAT", "CACCATA", "CACCATAC", "CACCATACT", "CACCATACTA", "CACCATACTAA", "CACCATACTAAT", "CCAACT", "CCTATA", "CCTATAC", "CCTATACA", "CCTATACAA", "CCTATACAAT", "CTAA", "CTAAT", "CTAATA", "CTAATAT", "CTAATATA", "CTAATATAA", "CCTACC", "AATAACA", "AATAACAA", "TAACAAT", "AATAAT", "CCAATA", "CCAATAA", "CCAATAAC", "CCAATAACT", "CCAATAACTA", "CCAATAACTAC", "CCAATAACTACA", "CCAATAACTACAC", "CCAATAACTACACC", "CCAATAACTACACCT", "CCACAAT", "CCTACTA", "AACTAT", "AACACCAT", "AACACCATA", "AACACCATAA", "AACACCATAAC", "AACACCATAACT", "AACACCATAACTA", "AACACCATAACTAT", "AACACCATAACTATA", "AACACCATAACTATAT", "AACACCATAACTATATA", "AACACCATAACTATATAA", "AACACCATAACTATATAAT", "AACATA", "AACATAA", "AACATAAT", "TACAACT", "TATAAC", "TATAACC", "TATAACCA", "TATAACCAA", "AATACTA", "AATACTAC", "AATACTACC", "AATACTACCA", "AATAACT", "AACTAAT", "AATAATA", "AATAATAC", "AATAATACT", "AACAAT", "AACAATA", "AACAATAT", "AACAATATA", "AACAATATAC", "AACAATATACA", "AACAATATACAT", "AACACACC", "CCACTAA", "CCACTAAT", "CCACTAATA", "CCACTAATAA", "CCATACA", "CCATACAT", "TACCAC", "TACCACC", "CCTACCA", "CCTACCAC", "CCTACCACA", "CCTACCACAC", "CCTACCACACA", "CCTACCACACAA", "CCTACCACACAAT", "CCTACTAC", "CCTACTACT", "CCAATAC", "CCAATACT", "CCTAAC", "CCTAACA", "CCTAACAT", "CCTACATA", "CCTACATAA", "CCTACATAAT", "TAACT", "TAACTA", "TACAAT", "TACAATA", "TACAATAA", "TACAATAAC", "TACAATAACC", "TACAATAACCA", "TACAATAACCAC", "TACAATAACCACA", "TACTAT", "CCAATAAT", "AATACAT", "AACTAC", "CCTATACT", "CCACCT", "AACCTAA", "TACCT", "AATAATACTA", "AATAATACTAA", "TACAACC", "TACAACCT", "AATATA", "CCAACAC", "CCAACACT", "CCAACACTA", "CCAACACTAA", "CCAACACA", "AACCAT", "AACCATA", "AACCATAA", "AACCATAAC", "AACCATAACC", "AACCATAACCT", "AACCACCAA", "TACAATAT", "CCACAT", "CCTAACC", "CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "TAACAC", "TAACACT", "TAACACTA", "CCATACTAA", "CCATACTAAT", "CCATACTAATA", "CCATACTAATAA", "TACAATAAT", "TAACACA", "TAACACAA", "CCTAACCA", "CCTAACCAC", "CCTAACCACA", "AACACAAC", "AACACAACT", "CCACATA", "CCACCACT", "CCACCACTA", "CCACCACTAT", "TACCAACAT", "AATAATAT", "AATAATATA", "AATAATATAA", "CCACTAATAAT", "CCTACACT", "AATACAACT", "TACCAACACCT", "TACCAACACCTA", "TACCAACACCTAT", "TATATA", "TATATAT", "TAACAT", "CCTAATAT", "TATACA", "TATACAC", "TATACACT", "AACCTAAC", "AACCTAACA", "AACCTAACAT", "AATACTAT", "TATACC", "AATAACAAT", "AACAACCT", "AACACCACAT", "AACAACT", "AACAACTA", "AACAACTAA", "AACAACTAAT", "AACAACTAATA", "AACAACTAATAA", "CCACCACACAACT", "AACACATACT", "AACCACCACT", "AATACAACACT", "AATACAACACTA", "AACAACCTA", "AACAACCTAA", "AACACAATACT", "CCACCTA", "CCACCTAA", "CCACCTAAT", "CCACCTAATA", "CCACCTAATAT", "TATAATACT", "TATAATACTA", "TATAATACTAT", "CCTAACCT", "AATAACCT", "AATAACCTA", "AATAACCTAA", "AATAACCTAAT"};

		System.out.println("========= X61145 ==========\t" + longSequence_6.length + "\tS6\t" + s6.length());
		
		String[] longSequence_7 = {"TA", "AA", "AT", "AC", "CA", "CC",
				"TAA", "CAA", "CCA", "CCAA", "ACC", "ACCA", "TAC", "ACA", "ACAA", "AAC", "AACC", "AACCA", "TACC", "TACCA", "TACCAA", "TAAC", "TAACA", "CCAC", "CCACA", "CCACAA", "ACAC", "ACACA", "CAAC", "CAACA", "TAACC", "CCAAC", "CAC", "AACA", "AACAC", "AACACA", "AACACAA", "AACAA", "AACAAC", "AACAACA", "AACAACAA", "CACA", "CACC", "CACCA", "CACCAA", "CAACAA", "CAACAAC", "CAACAACC", "CCAACA", "CCAACAA", "CACAC", "CACACC", "TACA", "TACAC", "TACACA", "TACACAA", "TACACAAC", "TACCAC", "TACCACA", "TACCACAA", "CAACC", "CAACCA", "AACAACC", "TAACCA", "TAACAC", "TAACACA", "CCACC", "CCACCA", "CCACCAA", "TAACAA", "TACACC", "TACACCA", "TACACCAA", "TACACCAAC", "TACACCAACC", "ACCAC", "ACCACC", "ACCACCA", "ACCACCAA", "ACCACCAAC", "ACCACCAACA", "CAACAC", "CACAA", "TACAA", "AACACAAC", "AACACAACA", "AACACAACAC", "CACAAC", "CCAACAC", "CCAACACA", "TACAAC", "TACAACA", "TACAACAC", "AACCAC", "CCAACC", "CCAACCA", "TAACACAA", "TAACACAAC", "AACACAC", "AACACACA", "AACACACAC", "AACACACACA", "AACACACACAC", "AACACACACACC", "AACACACACACCA", "AACACACACACCAC", "AACACACACACCACA", "CCACAC", "CCACACC", "AACCAA", "CAAT", "CAATA", "CACAAT", "CACAATA", "AAT", "AATA", "AATAA", "AATAAC", "AATAACA", "AATAACAA", "CCAT", "CAT", "CACACA", "CACACAC", "CCATA", "CCATAT", "AATAACC", "AATAT", "AATATA", "AATATAC", "AATATACA", "AATATACAC", "AATATACACA", "AATATACACAC", "AATATACACACC", "CCACAAT", "CATA", "CATAA", "CATAAT", "TAACCAA", "AACACC", "AACACCA", "AACACCAT", "AACACCATA", "AACACCATAA", "AACACCATAAC", "TAT", "TATA", "TATAT", "TATATA", "CCAAT", "AACACAAT", "TAAT", "TAATA", "TAATAC", "TAATACA", "TAATACAA", "TAATACAAC", "TAACAT", "AATAC", "CACCAC", "CACCACC", "CACCACCA", "CACCACCAC", "CACCACCACC", "AATAAT", "AATAATA", "AATAATAC", "AACAAT", "AACACACC", "TAATAA", "AACAT", "CAATAC", "CAATACA", "CAATACAC", "TATAC", "TATACA", "TATACAC", "TATACACC", "TATACACCA", "CCACAT", "CCATAC", "CCATACA", "CCATACAT", "TACACAAT", "TAATAT", "CACACAT", "CATAT", "CCAATA", "CCAATAC", "CATAATA", "CATAATAT", "AATACC", "AACCACA", "AACCACAC", "AACCACACC", "TATATAA", "CCATAA", "CCATAAC", "CCATAACC", "CCATAACCA", "CACAACC", "CACAACCA", "CACAACCAA", "CACAACCAAC", "CACAACCAACA", "CACAACCAACAT", "CCAATAA", "CCAATAAT", "AATACA", "AATACAT", "TACAT", "TATAA", "CCATAAT", "TATATAC", "TATATACC", "TACATA", "TACATAA", "TATACACA", "TATACACAC", "TACCAT", "TACCATA", "AACATA", "AACATAC", "AACATACA", "AACATACAC", "AACATACACC", "AACATACACCA", "AACATACACCAT", "AACATACACCATA", "TAATAAT", "AACCACC", "AACCACCA", "AACCACCAA", "CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "AACCAAT", "AACATAT", "AACATAA", "CCACATA", "CCACCAC", "AACAACAT", "AACAACATA", "AACAACATAA", "AATAACAT", "AATAACATA", "AATAACATAA", "CCAATAT", "AATACAA", "AATACAAC", "AATACAACC", "CCACCAT", "CCACCATA", "CCACCATAT", "AACAATA", "AATAACAAT", "AACCAT", "AACCATA", "AACACAATA", "AACACAATAC", "CCAACCAT", "AACACACACACCACAT", "AATACCA", "AATACCAA", "AATACCAAT", "AACACAT"};
		
		System.out.println("========= X72204 ==========\t" + longSequence_7.length + "\tS7\t" + s7.length());
		
		String[] longSequence_8 = {"AC", "TA", "AA", "AT", "CA",
				"TAC", "TAT", "TATA", "TATAT", "ATA", "ATAT", "ATATA", "TATAC", "TATATA", "TATATAC", "CAT", "CATA", "TACA", "TACAC", "CAC", "CACA", "CATAT", "CACAT", "CATAC", "CATACA", "CATACAC", "CATACACA", "CACAC", "CACACA", "CACACAT", "TATACA", "CAA", "CAAC", "CAACA", "CAACAA", "AAT", "AATA", "AATAT", "AAC", "AACA", "AACAA", "AACAC", "TAA", "TAAT", "TAAC", "CAAT", "AATAA", "AATAAT", "AACAT", "CAATA", "CAATAA", "CATATA", "AATATA", "AATATAC", "CAACAC", "CATATAT", "CACAA", "AACACA", "AACACAA", "AACACAAT", "CATAA", "CATAAT", "TATACAT", "TATACATA", "TATACATAA", "CATAATA", "CATAATAC", "TAATA", "AATAC", "AATACA", "AATACAT", "AATACATA", "AATACATAT", "TACAA", "AATAATA", "AATAATAT", "AATAATATA", "AATAATATAA", "AATAATATAAT", "TACAAC", "CATAAC", "TATAA", "TATAAT", "TATAATA", "TAATAA", "AACATA", "AACATAT", "AACAAC", "AACATAC", "CACAAT", "CACAATA", "AACAAT", "AACAATA", "AACAATAA", "CAATAT", "AATAAC", "AACAACA", "TAATAT", "TAATATA", "TAATATAA", "TAATATAAC", "AACACAAC", "TAACA", "TAACAA", "TATAAC", "AATACAA", "AATACAAT", "CAATAC", "CAATACA", "CAATACAC", "CATAATAT", "TACAT", "TACATA", "TACATAA", "TACATAAC", "TACATAACA", "TACATAACAT", "CATATAA", "TACATAAT", "TACATAATA", "TACATAATAC", "TACATAATACA", "TACATAATACAT", "TACATAATACATA", "TACATAATACATAT", "TACATAATACATATA", "TACATAATACATATAA", "TACATAATACATATAAT", "TATATAT", "AACACAT", "AACACATA", "TACACA", "TACACAT", "CACACAC", "TAACAC", "CAATAAT", "TAACAT", "TAATAC", "CAATAATA", "CAATAATAT", "AACATAA", "TAACACA", "AATAACA", "AACAACAT", "AACAACATA", "AACAACATAA", "TACAAT", "TAATAAT", "AATACAAC", "AATAACAA", "AATAACAAT"};
		
		System.out.println("========= V00654 ==========\t" + longSequence_8.length + "\tS8\t" + s8.length());
//====================================================================================================================
		
	/*	String[] Test1 = {"ACB", "BC", "AA", "AB"};
		String[] Test2 = {"ACB", "BC"};
		
		String a = "ACBCAAAB";
		String b = "ACBCACBC";*/
		
		//AverageCommonSubstring av = new AverageCommonSubstring();
		AverageCommonSubstring av = new AverageCommonSubstring();
		
		//System.out.println( s1.length()+"\t"+ s2.length() + "\t" + s3.length());
		//System.out.println("VALUE =\t" + av.findValue(s5, s4, longSequence_5_2,  longSequence_4_2, s5.length(), s4.length()));
	//	System.out.println("VALUE =\t" + av.findValue(a ,b, Test1, Test2, a.length(), b.length()) );
		
		System.out.println("80000-Longest Seq-NO Exist");
		
		for(int i = 6; i <= 8; i++)
		{

			System.out.println("=========================================================");
			if(i==1)
			{
				//System.out.println("S1 S2 VALUE =\t" + av.findValue(s1, s2, longSequence_1,  longSequence_2, s1.length(), s2.length()));
				//System.out.println("S1 S3 VALUE =\t" + av.findValue(s1, s3, longSequence_1,  longSequence_3, s1.length(), s3.length()));
				//System.out.println("S1 S4 VALUE =\t" + av.findValue(s1, s4, longSequence_1,  longSequence_4, s1.length(), s4.length()));
				//System.out.println("S1 S5 VALUE =\t" + av.findValue(s1, s5, longSequence_1,  longSequence_5, s1.length(), s5.length()));
				System.out.println("S1 S6 VALUE =\t" + av.findValue(s1, s6, longSequence_1,  longSequence_6, s1.length(), s6.length()));
				System.out.println("S1 S7 VALUE =\t" + av.findValue(s1, s7, longSequence_1,  longSequence_7, s1.length(), s7.length()));
				System.out.println("S1 S8 VALUE =\t" + av.findValue(s1, s8, longSequence_1,  longSequence_8, s1.length(), s8.length()));
			}
			else if(i==2)
			{
				//System.out.println("S2 S1 VALUE =\t" + av.findValue(s2, s1, longSequence_2,  longSequence_1, s2.length(), s1.length()));
				//System.out.println("S2 S3 VALUE =\t" + av.findValue(s2, s3, longSequence_2,  longSequence_3, s2.length(), s3.length()));
				//System.out.println("S2 S4 VALUE =\t" + av.findValue(s2, s4, longSequence_2,  longSequence_4, s2.length(), s4.length()));
				//System.out.println("S2 S5 VALUE =\t" + av.findValue(s2, s5, longSequence_2,  longSequence_5, s2.length(), s5.length()));
				System.out.println("S2 S6 VALUE =\t" + av.findValue(s2, s6, longSequence_2,  longSequence_6, s2.length(), s6.length()));
				System.out.println("S2 S7 VALUE =\t" + av.findValue(s2, s7, longSequence_2,  longSequence_7, s2.length(), s7.length()));
				System.out.println("S2 S8 VALUE =\t" + av.findValue(s2, s8, longSequence_2,  longSequence_8, s2.length(), s8.length()));
			}
			else if(i==3)
			{
				//System.out.println("S3 S1 VALUE =\t" + av.findValue(s3, s1, longSequence_3,  longSequence_1, s3.length(), s1.length()));
				//System.out.println("S3 S2 VALUE =\t" + av.findValue(s3, s2, longSequence_3,  longSequence_2, s3.length(), s2.length()));
				//System.out.println("S3 S4 VALUE =\t" + av.findValue(s3, s4, longSequence_3,  longSequence_4, s3.length(), s4.length()));
				//System.out.println("S3 S5 VALUE =\t" + av.findValue(s3, s5, longSequence_3,  longSequence_5, s3.length(), s5.length()));
				System.out.println("S3 S6 VALUE =\t" + av.findValue(s3, s6, longSequence_3,  longSequence_6, s3.length(), s6.length()));
				System.out.println("S3 S7 VALUE =\t" + av.findValue(s3, s7, longSequence_3,  longSequence_7, s3.length(), s7.length()));
				System.out.println("S3 S8 VALUE =\t" + av.findValue(s3, s8, longSequence_3,  longSequence_8, s3.length(), s8.length()));			
			}
			else if(i==4)
			{
				//System.out.println("S4 S1 VALUE =\t" + av.findValue(s4, s1, longSequence_4,  longSequence_1, s4.length(), s1.length()));
				//System.out.println("S4 S2 VALUE =\t" + av.findValue(s4, s2, longSequence_4,  longSequence_2, s4.length(), s2.length()));
				//System.out.println("S4 S3 VALUE =\t" + av.findValue(s4, s3, longSequence_4,  longSequence_3, s4.length(), s3.length()));
				//System.out.println("S4 S5 VALUE =\t" + av.findValue(s4, s5, longSequence_4,  longSequence_5, s4.length(), s5.length()));
				System.out.println("S4 S6 VALUE =\t" + av.findValue(s4, s6, longSequence_4,  longSequence_6, s4.length(), s6.length()));
				System.out.println("S4 S7 VALUE =\t" + av.findValue(s4, s7, longSequence_4,  longSequence_7, s4.length(), s7.length()));
				System.out.println("S4 S8 VALUE =\t" + av.findValue(s4, s8, longSequence_4,  longSequence_8, s4.length(), s8.length()));
			
			}
			else if(i==5)
			{
				//System.out.println("S5 S1 VALUE =\t" + av.findValue(s5, s1, longSequence_5,  longSequence_1, s5.length(), s1.length()));
				//System.out.println("S5 S2 VALUE =\t" + av.findValue(s5, s2, longSequence_5,  longSequence_2, s5.length(), s2.length()));
				//System.out.println("S5 S3 VALUE =\t" + av.findValue(s5, s3, longSequence_5,  longSequence_3, s5.length(), s3.length()));
				//System.out.println("S5 S4 VALUE =\t" + av.findValue(s5, s4, longSequence_5,  longSequence_4, s5.length(), s4.length()));
				System.out.println("S5 S6 VALUE =\t" + av.findValue(s5, s6, longSequence_5,  longSequence_6, s5.length(), s6.length()));
				System.out.println("S5 S7 VALUE =\t" + av.findValue(s5, s7, longSequence_5,  longSequence_7, s5.length(), s7.length()));
				System.out.println("S5 S8 VALUE =\t" + av.findValue(s5, s8, longSequence_5,  longSequence_8, s5.length(), s8.length()));
			}
			else if(i == 6)
			{
				System.out.println("S6 S1 VALUE =\t" + av.findValue(s6, s1, longSequence_6,  longSequence_1, s6.length(), s1.length()));
				System.out.println("S6 S2 VALUE =\t" + av.findValue(s6, s2, longSequence_6,  longSequence_2, s6.length(), s2.length()));
				System.out.println("S6 S3 VALUE =\t" + av.findValue(s6, s3, longSequence_6,  longSequence_3, s6.length(), s3.length()));
				System.out.println("S6 S4 VALUE =\t" + av.findValue(s6, s4, longSequence_6,  longSequence_4, s6.length(), s4.length()));
				System.out.println("S6 S5 VALUE =\t" + av.findValue(s6, s5, longSequence_6,  longSequence_5, s6.length(), s5.length()));
				System.out.println("S6 S7 VALUE =\t" + av.findValue(s6, s7, longSequence_6,  longSequence_7, s6.length(), s7.length()));
				System.out.println("S6 S8 VALUE =\t" + av.findValue(s6, s8, longSequence_6,  longSequence_8, s6.length(), s8.length()));
			}
			else if(i == 7)
			{
				System.out.println("S7 S1 VALUE =\t" + av.findValue(s7, s1, longSequence_7,  longSequence_1, s7.length(), s1.length()));
				System.out.println("S7 S2 VALUE =\t" + av.findValue(s7, s2, longSequence_7,  longSequence_2, s7.length(), s2.length()));
				System.out.println("S7 S3 VALUE =\t" + av.findValue(s7, s3, longSequence_7,  longSequence_3, s7.length(), s3.length()));
				System.out.println("S7 S4 VALUE =\t" + av.findValue(s7, s4, longSequence_7,  longSequence_4, s7.length(), s4.length()));
				System.out.println("S7 S5 VALUE =\t" + av.findValue(s7, s5, longSequence_7,  longSequence_5, s7.length(), s5.length()));
				System.out.println("S7 S6 VALUE =\t" + av.findValue(s7, s6, longSequence_7,  longSequence_6, s7.length(), s6.length()));
				System.out.println("S7 S8 VALUE =\t" + av.findValue(s7, s8, longSequence_7,  longSequence_8, s7.length(), s8.length()));
			}
			else if(i == 8)
			{
				System.out.println("S8 S1 VALUE =\t" + av.findValue(s8, s1, longSequence_8,  longSequence_1, s8.length(), s1.length()));
				System.out.println("S8 S2 VALUE =\t" + av.findValue(s8, s2, longSequence_8,  longSequence_2, s8.length(), s2.length()));
				System.out.println("S8 S3 VALUE =\t" + av.findValue(s8, s3, longSequence_8,  longSequence_3, s8.length(), s3.length()));
				System.out.println("S8 S4 VALUE =\t" + av.findValue(s8, s4, longSequence_8,  longSequence_4, s8.length(), s4.length()));
				System.out.println("S8 S5 VALUE =\t" + av.findValue(s8, s5, longSequence_8,  longSequence_5, s8.length(), s5.length()));
				System.out.println("S8 S6 VALUE =\t" + av.findValue(s8, s6, longSequence_8,  longSequence_6, s8.length(), s6.length()));
				System.out.println("S8 S7 VALUE =\t" + av.findValue(s8, s7, longSequence_8,  longSequence_7, s8.length(), s7.length()));	
			}
		}
	}

}


/*String[] longSequence_1_2 = {"AC", "TA", "AA", "AT", "CA", "CC", "CT",
		"CCT", "CTA", "TAA", "CCTA", "CTAA", "CCTAA", "CCA", "CCAA", "CAA", "AAC", "AACC", "CAC", "CACA", "CACAA",
		"CTAC", "CTACA", "TAAC", "TAACC", "TAACCT", "TAACCTA", "TAC", "TAACA", "TAACAA", "AACT", "AACTA", "AACTAA",
		"CACAC", "CACACA", "CACACAC", "CACACACC", "CACC", "CTACAA", "AACCA", "AACCAA", "AACCAAC", "AACCAACA",
		"AACCAACAC", "AACCAACACA", "AACCAACACAA", "AACA", "TACA", "TACT", "AACAA", "TACAC", "TACACC", "CACACC",
		"AACAAC", "AACAACT", "AACAACTA", "AACAACTAA", "AACTAC", "AACTACA", "AACTACAA", "AAT", "TAACT", "CTAT",
		"CTATA", "TACC", "AATA", "CTACC", "CTACCT", "CAT", "CATA", "CATAA", "CATAAT", "CTACCTA", "CTACCTAC",
		"CCTAAC", "CAAC", "CAACT", "TACCT", "TACCTA", "TACCTAA", "TAAT", "AATAT", "AATATA", "AATATAA", "AATATAAT",
		"AACAT", "AACATA", "AACATAA", "AATAA", "CCAT", "CAAT", "CAACA", "CAACAA", "CAACAAT", "CAACAT", "CAACATA",
		"CAACATAA", "CAACATAAT", "CAACATAATA", "CAACATAATAC", "CAACATAATACC", "CAACATAATACCA", "CAACATAATACCAA", 
		"TACTA", "TACTAC", "TACTACT", "TAT", "TATA", "AACAAT", "CTAAT", "CTAATA", "CTAATAT", "AACCT", "TATAA",
		"TATAAC", "TATAACC", "CCAC", "CCACT", "TAACAAC", "TAACAACA", "TAACAACAA", "TAACTA", "TAACTAA", "TAACTAAC",
		"TAACTAACC", "TAACTAACCA", "TAACTAACCAA", "TAACTAACCAAT", "TAATA", "TAATAC", "TAATACA", "TAATACAC",
		"TAATACACC", "TAATACACCT", "TAATACACCTA", "TAATACACCTAT", "AACTAAT", "CCAAC", "CCAACA", "CCAACAC", 
		"CCAACACA", "AACAC", "AACACA", "AACACAA", "TACCA", "TACCAA", "CACCT", "CACT", "CTATAA", "CCACA", "CCACAC",
		"CCAAT", "AATAAT", "AATAATA", "AATAATAA", "CCTAT", "TAACTAACT", "CAACC", "CAACCA", "CAACCAA", "CAACCAAC",
		"CAACCAACA", "CTACAAT", "AACAACC", "AACAACCT", "CCACAT", "CACTA", "CACTAA", "CACTAAT", "CCATA", "CCATAT",
		"TAACCA", "CCACC", "CCACCT", "CCTAACA", "CCTAACAC", "CCTAACACT", "CCTAACACTA", "CCTAACACTAT", "TATAAT",
		"TAATAT", "CTACAT", "CTACATA", "CTACATAC", "CTACATACA", "CTACATACAA", "CTACATACAAC", "CTACATACAACT", 
		"CCAACAT", "CCTAC", "AACTAT", "CCTAACT", "CCTAACTA", "CCTAACTAT", "CCTAACTATA", "CCTAACTATAT", "CCATAC",
		"CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "AACCTA", "CAATA",
		"CAATAT", "AATAC", "CCTAAT", "TACTAA", "TACTAAT", "TACTAATA", "TACTAATAA", "CCATAA", "CCATAAT", "CCATAATA",
		"CCATAATAT", "AACCAACC", "CTAAC", "CTAACA", "CTAACAT", "CCATATA", "CCATATAT", "CCATATATA", "CCATATATAC",
		"CCATATATACC", "CCATATATACCT", "TACACT", "TACACTA", "TATAT", "CCTACC", "AATACA", "AATACAC", "AATACACC", 
		"AATACACCA", "AATACACCAT", "AATACACCATA", "AATACACCATAT", "CTACT", "AACATAC", "AACATACT", "AACATACTA", 
		"AACCAT", "AACCATA", "AACCATAC", "AACCATACC", "CCAACC", "TAACCAT", "CACCA", "CACCAC", "CACCACT", "CACCACTA",
		"CACCACTAT", "TACCAT", "TACCATA", "CCAACT", "CCTACT", "CCTAATA", "TACAA", "TACAAT", "TACAATA", "TACAATAA",
		"TACAATAAC", "TACAATAACC", "CACAT", "CACAAC", "CACAACT", "AACTACC", "CACTAT", "CACTAAC", "CACTAACC",
		"CACTAACCA", "CACTAACCAT", "CACTAACCATA", "CACTAACCATAT", "AACAACA", "AACAACAT", "AACAACATA", "AACAACATAA",
		"CCATAAC", "CCATAACC", "CCAATA", "CCAATAA", "CCAATAAT", "CCAATAATA", "CCAATAATAT", "TACTACTA", "TACTACTAA",
		"TACTACTAAC", "TACTACTAACT", "TACAT", "CCTACTA", "TAATAA", "TAATAAT", "CCTAATAA", "CCTAATAAT", "AATAAC",
		"AATAACA", "AATAACAT", "AATAACATA", "AATAACATAA", "CAATAA", "CAATAAT", "CCACCA", "CCACCAT", "CCACCATA",
		"CCACCATAT", "CCACCATATA", "CCACCATATAT", "CATAC", "CATACT", "CCACTA", "CCACTAT", "CCACTATA", "CCACTATAA",
		"CCACTATAAT", "CTATAT", "CTATATA", "CTATATAC", "CTATATACT", "CCAACACT", "CCTACA", "CCTACAC", "CCACCTA",
		"CCACCTAC", "CCTACAA", "CCACACA", "CCACACAA", "CCTACCA", "CCTACCAT", "AACTATA", "TACACA", "AATAACAA",
		"AATAACAAT", "CCTAATAAC", "CCTAATAACC", "CCTAATAACCA", "CCTAATAACCAT", "CCTAATAACCATA", "CCTAATAACCATAC",
		"AACCAC", "AACCACA", "AACACT", "AACACTA", "AACACTAC", "AACACTACT", "CCAACAA", "AACACAC", "CCTACAAT", 
		"CCTACAATA", "CCTACAATAA", "CCTACAATAAT", "CCTACAATAATA", "CCTACCAC", "CCTACCACC", "CCAACTA", "CCAACTAT",
		"CCACCAA", "CCTATA", "AATACT", "AATACAT", "AATACATA", "AATACATAC", "AATACATACT", "TAATAATA", "TAATAATAA",
		"TAATAATAAC", "TAATAATAACA", "TAACAAT", "CCACCATAA", "CCACCATAAT", "CCACCATAATA", "CCACCATAATAC", 
		"CCACCATAATACT", "AACCTAA", "AACCTAAC", "AACCTAACA", "AACCTAACAT", "CCTACTAT", "TACCTAC", "TACCTACT",
		"AACAATA", "AACAATAT", "AACAATATA", "CCTACCAA", "AATACC", "CCTATAC", "CCACTAA", "CCACTAAC", "CCACTAACA",
		"CCACTAACAA", "CCTATACT", "AATATAC", "AATATACC", "AATATACCT", "AATATACCTA", "AATATACCTAT", "CCTACTAC",
		"CCTACTACT", "CCTACTACTA", "CCTACTACTAC", "CCTACTACTACT", "CCTACTACTACTA", "CCTACTACTACTAT", "CCACAA", 
		"CCACAAT", "CCACAATA", "CCACAATAT", "AACCACC", "AACCACCA", "AACCACCAT", "CCACAAC", "CCACAACA", "CCACAACAA",
		"CCACAACAAT", "CCACAACAATA", "CCACAACAATAT", "AACCATAA", "AATACCT", "AATACCTA", "AATACCTAC", "AATACCTACT",
		"AATACCTACTA", "AATACCTACTAA", "AATACCTACTAAT", "AATACCTACTAATA", "AATACCTACTAATAT", "AACAACCTA",
		"AACAACCTAT", "AACACAT", "CCACCAC", "CCACCACA", "CCACCACAA", "CCACCACAAC", "CCACCACAACA", "CCACCACAACAA",
		"CCACCACAACAAC", "CCACCACAACAACC", "CCACCACAACAACCT", "CCACCACAACAACCTA", "CCACCACAACAACCTAA", "AATATACCA",
		"AATATACCAC", "AATATACCACT", "TACTAT", "AACAACCA", "AACAACCAC", "AACAACCACC", "TACTACC", "TACTACCA", 
		"TACTACCAT", "TATAC", "TATACC", "TATACCA", "TATACCAA", "TATACCAAC", "AATAACC", "AATAACCA", "AATAACCAT", 
		"AACCTAT", "AACTATAC", "AACTATACT", "CCTAATAATA", "CCTAATAATAA", "CCTAATAATAAT", "AATACAA", "AATACAAC",
		"AATACAACA", "AATACAACAT", "AATATAAC", "AATATAACA", "AATATAACAA", "CCATAACA", "CCATAACAA", "AACAACAA", 
		"AACTAAC", "AACTAACA", "AACTAACAA", "AACTAACAAC", "AACTAACAACA", "AACTAACAACAA", "AATAATAC", "AATAATACT",
		"AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "CCACACC", "CCACACCA", "CCACACCAT",
		"CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACCACC", "CCACCACCA", "CCATAACAT", "ACT", "ACTA", "ACTAA", 
		"ACTAAT", "AATAACT", "CCAATAAC", "CCAATAACC", "AACTAATA", "CCACACT", "CCACACTA", "CCACACTAA", "AACCTAACC",
		"AACCTAACCA", "AACCTAACCAA", "AACATACA"};

System.out.println("========= X63726 ==========\t" + longSequence_1_2.length + "\tS1\t" + s1.length());

String[] longSequence_2_2 = {"AC", "TA", "AA", "AT", "CA", "CT", "GC", "CC",
		"CTA", "GCT", "GCTA", "ACT", "ACTA", "ACTAC", "TAC", "TACT", "CTAC", "CTACT", "TACTA", "CTACTA", "TACA", "TACAC", "TACACA", "TACACAC", "TACACACA", "TACACACAC", "ACA", "GCA", "GCAC", "ACAC", "ACACA", "ACACAC", "ACACACA", "CAC", "CACA", "CACAC", "CACT", "GCACT", "CACACT", "CACACTA", "CACTA", "ACTACT", "ACTACTA", "GCTAC", "GCTACA", "ACACT", "ACACTA", "ACACTAC", "ACACTACA", "CAA", "TAA", "TAAC", "TAACA", "TAACAA", "AAC", "AACA", "AACAA", "AACAAC", "AACAACT", "AACAACTA", "AACAACTAA", "AACT", "AACTA", "AACTAC", "AACTACA", "AACTACAA", "GCAA", "CTAA", "CTAAC", "TACAA", "TACAAC", "AACAC", "AACACA", "AACACAA", "GCTAA", "AACTAA", "AACTAAC", "CAAC", "CAACA", "CAACAC", "CAACACA", "GCAAC", "CTAACA", "CTAACAC", "CTAACACT", "CTAACACTA", "TACAACT", "CTACA", "CTACAA", "CTACAAC", "CACTAA", "CACTAAC", "CACTAACA", "CACAA", "CACAAC", "CTAACT", "CTAACTA", "CTACTAA", "CACACTAA", "CCT", "TACC", "CCTA", "TAACC", "CCTAC", "CCTACC", "CCA", "AACC", "GCTACC", "CCAA", "TACACC", "TACACCA", "TACTAA", "AACCA", "AACCAC", "AACCACA", "AACCACAC", "AACCACACC", "CCAC", "CCTAA", "CCTAAC", "CCTAACC", "TAACCA", "GCC", "GCACC", "GCACCA", "GCACCAC", "GCACCACT", "GCACCACTA", "AACTACT", "AACTACTA", "AACTACTAA", "CCAAC", "CCAACT", "TACAACC", "CCACC", "AACCT", "GCACA", "GCACAA", "GCACAAC", "GCACAACT", "AACTACC", "CCACCT", "AACCTA", "TACCA", "TACCAC", "TACCACA", "AACAACA", "GCCA", "GCCAC", "GCCACC", "GCCACCA", "GCCACCAC", "GCCACCACT", "GCCACCACTA", "GCCACCACTAA", "TACTAC", "TACTACT", "CCTACT", "CCTACTA", "GCCT", "GCCTA", "TACCAA", "GCTACT", "TACAACA", "CCACCA", "TACACT", "CCAACA", "CCAACAC", "CCAACACT", "TACCT", "CCACCTA", "CCACCTAC", "TAACT", "CCTACA", "CCTACAA", "GCAACC", "GCAACCT", "GCTAAC", "GCTAACC", "GCTAACCA", "GCTAACCAC", "CCACA", "CCACAC", "CCACACA", "CCACACAA", "CCTACCA", "CCTACAC", "CCTACACA", "CCTACACAA", "AACCAA", "AACAACC", "TACCTA", "CCACT", "CCACTA", "AAT", "GCCACA", "AACACT", "AACACTA", "AACACTAC", "AACACTACT", "CCAACAA", "AACACAC", "GCTAT", "CCTACAAT", "CCTACAATA", "CCTACAATAA", "CCTACAATAAT", "CCTACAATAATA", "TAAT", "CCTACCAC", "CCTACCACC", "CCAACTA", "CCAACTAT", "AACTAT", "CCAT", "CCATA", "CCACCAA", "GCAT", "GCATA", "GCATAC", "GCATACC", "GCATACCA", "GCATACCAC", "GCATACCACA", "GCATACCACAT", "GCATACCACATA", "CCTAACA", "TACAT", "TAT", "CCTAT", "CCTATA", "AATA", "AATAC", "AATACT", "TATA", "TATAA", "TATAAT", "AACAT", "AACATA", "TACTAAT", "AATACA", "AATACAT", "AATACATA", "AATACATAC", "AATACATACT", "TATAT", "GCCAA", "GCCAAC", "GCCAACA", "GCCAACAT", "TAATA", "TAATAA", "TAATAAT", "TAATAATA", "TAATAATAA", "TAATAATAAC", "TAATAATAACA", "TAACAAT", "CCACCAT", "CCACCATA", "CCACCATAA", "CCACCATAAT", "CCACCATAATA", "CCACCATAATAC", "CCACCATAATACT", "AACCTAA", "AACCTAAC", "AACCTAACA", "AACCTAACAT", "GCATAA", "CCTACTAT", "TACCTAC", "TACCTACT", "AATAA", "AATAAT", "AATAATA", "AATAATAT", "AATAATATA", "CCAACC", "CCTACCAA", "AATACC", "CCTATAC", "GCCTAA", "CCACTAA", "CCACTAAC", "CCACTAACA", "CCACTAACAA", "CCTAAT", "GCATACT", "AACACC", "AACACCA", "AACACCAT", "CCTAATA", "CCTAATAA", "CCTAATAAT", "CCTATAT", "AATAT", "AATATA", "AATATAC", "AATATACC", "AATATACCT", "AATATACCTA", "AATATACCTAC", "AATATACCTACC", "AATATACCTACCA", "AATATACCTACCAC", "AATATACCTACCACA", "AATATACCTACCACAT", "CCTAATAAC", "CCTACTAC", "CCTACTACT", "CCTACTACTA", "CCTACTACTAC", "CCTACTACTACT", "CCTACTACTACTA", "CCTACTACTACTAT", "GCTAACT", "GCAACT", "CCAAT", "CCACAA", "CCACAAC", "CCACAACA", "CCACAACAA", "CCACAACAAT", "CCACAACAATA", "CCACAACAATAT", "AACCAT", "AACCATA", "AACCATAA", "AATACCT", "AATACCTA", "AATACCTAC", "AATACCTACT", "AATACCTACTA", "AATACCTACTAA", "AATACCTACTAAT", "AATACCTACTAATA", "AATACCTACTAATAT", "AACAACCT", "AACAACCTA", "AACAACCTAT", "AACACAT", "CCACCAC", "CCACCACA", "CCACCACAA", "CCACCACAAC", "CCACCACAACA", "CCACCACAACAA", "CCACCACAACAAC", "CCACCACAACAACC", "CCACCACAACAACCT", "CCACCACAACAACCTA", "CCACCACAACAACCTAA", "AACAACCA", "AACAACCAC", "AACAACCACC", "CCATAA", "CCATAAT", "AATAAC", "AATAACT", "AATAACTA", "AATAACTAT", "AATAACTATA", "AATAACTATAC", "AATAACTATACC", "AACCTAT", "CCACTAT", "GCAAT", "AACTATA", "AACTATAC", "AACTATACT", "CCTAATAATA", "CCTAATAATAA", "CCTAATAATAAT", "CCAATA", "CCAATAA", "CCATAC", "CCATACC", "CCATACCA", "CCATACCAT", "CCATAAC", "CCAACACA", "AACAACAA", "CCACTAAT", "CCACTAATA", "CCACTAATAA", "CCACTAATAAT", "AACTAAT", "AACTAATA", "AACTAATAA", "AACTAATAAC", "AACTAATAACA", "AACTAATAACAA", "AATAATAC", "AATAATACT", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "CCACAT", "CCACACC", "GCCTAAT", "GCCTAT", "GCCTATA", "GCCTATAC", "GCCTATACA", "GCCTATACAT", "GCCAAT", "CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACCACC", "CCACCACCA", "GCCACT", "CCATAACA", "CCATAACAT", "ACTAA", "ACTAAT", "AACCAAC", "AACCAACC", "AACCAACCA", "AACCAACCAA", "AACCAACCAAC", "AACCAACCAACC", "AACCAACCAACCT", "AACCAACCAACCTA", "AACCAACCAACCTAA", "CCATACCAA", "CCATACCAAC", "CCATACCAACA", "CCAACAAC", "CCAACAACC", "CCACACT", "CCACACTA", "CCACACTAA", "AACCTAACC", "AACCTAACCA", "AACCTAACCAA", "AACATAA", "AACTAACA", "AACTAACAA", "AACACTACA", "AACACTACAA", "AACCAACA", "AACCAACAC", "AACCAACACA", "AACCAACACAA", "AACATAT", "AACATATA", "AATAACA", "AATAACAA", "AACTAACC", "AACTAACCA", "AACTAACCAA", "AACTAACCAAC", "AACTAACCAACC", "AACTAACCAACCA", "AACTAACCAACCAA", "AACTAACCAACCAAT", "CCTAACAC", "CCTAACACT", "CCTAACACTA", "CCTAACACTAT", "CCAACAT", "CCTAACT", "CCTAACTA", "CCTAACTAT", "CCATACCATA", "CCATACCATAT", "AACAAT", "CCATACA", "CCATACAT", "CCATACATA", "CCATACATAC", "CCATACATACC", "CCATACATACCT", "AATACAC", "AATACACC", "AATACACCA", "AATACACCAT", "AATACACCATA", "AATACACCATAT", "AACTACTAAT", "CCATAT", "CCATATA", "AACCTATA", "AACCTATAC", "AACCTATACT", "AACAACAT", "AACAACATA", "AACAACATAA", "CCATAACC", "CCAATAAT", "CCAATAATA", "CCAATAATAT", "AATAATATAA", "CCACCATAT", "CCACCATATA", "CCACCATATAT", "CCTACCAT", "AATAACAAT"}; 

System.out.println("========= X72004 ==========\t" + longSequence_2_2.length+ "\tS2\t" + s2.length());

String[] longSequence_3_2 = {"AC", "TA", "AA", "AT", "CA", "CT", "CC", "TT",
		"AAC", "AACC", "ACC", "ACCT", "CCT", "ACT", "AACT", "AACCT", "CTT", "ACTT", "AACCTT", "CCTT", "ACCTT", "AACTT", "ATT", "AAT", "AATT", "ATA", "ATAA", "ATAAT", "ATAATA", "ATAATAA", "ATAATAAT", "ATAATAATA", "ATAATAATAA", "CCTA", "CCTAT", "TTA", "TTAA", "TTAAT", "TTAATT", "TTAATTA", "TTAATTAA", "TTAATTAAC", "TTAATTAACC", "ATAT", "CTA", "CTAT", "CTATA", "CTATAT", "TAC", "TACT", "TACTA", "TACTAC", "ACTTA", "ACTTAT", "ACTTATT", "TTAC", "TTACC", "CCTATT", "AATA", "CCTAC", "TAA", "TAAT", "CTATT", "TTAATA", "TTAATAA", "TTAATAAC", "TTAATAACT", "TTAATAACTT", "TTAAC", "TTAACC", "CTTA", "CTTAA", "CTTAAT", "TTAT", "ACTA", "ACTAC", "ACTACC", "TAAC", "TAACT", "AACTTA", "AACTTAA", "TTATT", "TTATTA", "TTATTAT", "CCTTA", "CCTTAA", "CCTTAAC", "TTATA", "TTATAA", "TTATAAT", "TTATAATT", "TTATAATTA", "TTATAATTAA", "TTATAATTAAT", "ACTAA", "ACTAAT", "ACTAATT", "ACTAATTA", "ACTAATTAT", "ACTAATTATT", "CCTAA", "CCTAAC", "CCTAACC", "ATAC", "AACCTA", "AACCTAT", "CCTTAC", "ATATT", "ATATTA", "ATATTAA", "ATATTAAT", "ATATTAATA", "ATATTAATAT", "ATATTAATATT", "ATACC", "ATTA", "ATTAA", "ATTAAC", "TACTAT", "TACTATT", "AATAC", "CCTAAT", "TACTACT", "TACTACTA", "TACTACTAA", "TACTACTAAT", "TACTACTAATA", "TACTACTAATAA", "ATACT", "ATACTA", "ATACTAA", "ATACTAAT", "ATAATAT", "AATAT", "ACA", "CCTTAT", "CCTTATA", "CCTTATAT", "CCTTATATA", "CCTTATATAC", "CCTTATATACC", "CCTTATATACCA", "TTACA", "CCTAACA", "CCTAACAA", "CCTAACAAC", "CCTAACAACT", "CCTAACAACTA", "CCTAACAACTAC", "CCTAACAACTACT", "CCTAACAACTACTT", "CTTAT", "CCTACC", "CCA", "CCAC", "CAC", "CACC", "TAT", "CCTACCT", "CCTACCTA", "CCTACCTAT", "CAT", "CACA", "CCACC", "CCACCT", "CAA", "AACA", "AACAT", "AACATA", "AACATAA", "TTACT", "TTACTT", "CTTATT", "CCAA", "CTAA", "AATAA", "CCAT", "CCATA", "CCATAC", "CCATACC", "TTATAC", "TTATACC", "CCATACT", "CCATACTA", "CCATACTAA", "CCATACTAAT", "CAAC", "CAACC", "TTATTATT", "TTATTATTA", "TTATTATTAT", "TTATTATTATT", "TTATTATTATTA", "TTATTATTATTAT", "TTATTATTATTATA", "TTATTATTATTATAT", "TTATTATTATTATATT", "TTATTATTATTATATTA", "TTATTATTATTATATTAA", "TTATTATTATTATATTAAC", "TTATTATTATTATATTAACC", "CCACT", "AATACA", "AATACAA", "AATACAAC", "AATACAACC", "AATATT", "CTTAAC", "CTTAACA", "CTTAACAC", "CTTAACACA", "CTTAACACAA", "CCATAA", "CCATAAT", "CCTAATA", "CCTAATAA", "CCTAATAAT", "CCTAATAATA", "CCTAATAATAT", "CATT", "CATTA", "CATTAT", "CAAT", "CTAC", "CTACT", "TACTAA", "CATA", "CATAA", "CATAAT", "CATAATA", "CATAATAA", "CATAATAAC", "CATAATAACA", "CATAATAACAA", "CATAATAACAAC", "CATAATAACAACC", "AACTA", "CCATT", "CCACTT", "TACC", "TTACAC", "TTACACA", "TTACACAA", "CTACTA", "CTACTAA", "CTACTAAC", "CTACTAACA", "CTACTAACAT", "CTACC", "CTACCA", "CTACCAA", "CCACCA", "CCACCAT", "CCACCATT", "CCACCATTA", "CCACCATTAA", "CCACCATTAAC", "CCACCATTAACC", "CCTAACCT", "CCTAACCTA", "CCTAACCTAA", "CCTAACCTAAT", "CCTAACAAT", "CCTAACAATA", "CCTAACAATAT", "AACCA", "AACCAA", "AACAC", "AACACA", "AACACAA", "AACACAAC", "AACACAACT", "AACACAACTA", "AACACAACTAC", "CCATAT", "CCATATA", "TACA", "CCACA", "CCACAA", "CCACAAT", "CCACAATA", "CCACAATAA", "CCACAATAAT", "CCACAATAATA", "CCACAATAATAA", "CCACAATAATAAT", "AACCTAA", "AACCTAAC", "AACCTAACT", "AACCTAACTA", "AACCTAACTAT", "CATTATA", "CATTATAA", "CATTATAAC", "CATTATAACA", "CATTATAACAC", "CATTATAACACT", "CATTATAACACTA", "CATTATAACACTAA", "CATTATAACACTAAC", "CATTATAACACTAACC", "CATTATAACACTAACCA", "CATTATAACACTAACCAC", "CATTATAACACTAACCACC", "CATTATAACACTAACCACCT", "CATTATAACACTAACCACCTT", "CATAC", "CATACT", "CATACTA", "CATACTAT", "CATACTATT", "TTATACA", "TTATACAC", "TTATACACA", "TTATACACAA", "TTATACACAAC", "TTATACACAACT", "CCACCAC", "CCACCACA", "CCACCACAA", "CCACCACAAC", "CCACCACAACA", "CCACCACAACAA", "CCACCACAACAAC", "CCACCACAACAACA", "CACAA", "CTAAT", "CACCT", "CACT", "TATT", "TATTA", "TATTAA", "TATTAAT", "TATTAATA", "TATTAATAA", "TATTAATAAT", "TATTAATAATA", "TATTAATAATAT", "CAACCT", "CAACCTA", "CAACCTAT", "CAACCTATA", "CAACCTATAC", "CAACCTATACT", "CAACCTATACTT", "CTACA", "CTACAT", "CTACATA", "CTACATAC", "AACAA", "AACAAC", "AACAACA", "TATA", "TATAA", "AATAAC", "AATAACC", "AATAACCT", "AATAACCTT", "TAATA", "TAATAT", "CAACCA", "CAACCAT", "CAACCATA", "CAACCATAC", "CAACCATACT", "CAACCATACTA", "CAACCATACTAC", "CAACCATACTACT", "CAACCATACTACTT", "AACCAT", "AACCATT", "AACACT", "AACACTT", "TTAACA", "TTACCT", "TTACCTA", "TTACCTAT", "TTACCTT", "TACAC", "TACACT", "TACACTA", "TACACTAC", "TACACTACT", "TACACTACTA", "TTACAA", "TTACAAT", "CCATTA", "CCATTAA", "CCATTAAT", "CCATTAATA", "CCATTAATAA", "CCATTAATAAT", "CCATTAATAATT", "AACAACAT", "TATAC", "TATACC", "CCAAT", "CCAATA", "CCAATAT", "AACACC", "AACACCT", "AACACCA", "AACACCAC", "AACACCACA", "AACACCACAT", "AACACCACATT", "TTACAT", "TTACATT", "TTACCTATT", "TTACCTATTA", "TTACCTATTAT", "TTACCTATTATT", "TAATATT", "TAATATTA", "TAATATTAA", "TACCA", "TACCAC", "TACCACA", "TACCACAC", "TACCACACA", "TACCACACAT", "TACCACACATT", "TACTATTA", "TACTATTAA", "TTAACT", "AACAAT", "AACAATT", "AACAATTA", "AACAATTAC", "AACAATTACA", "AACAATTACAT", "AACAATTACATA", "AACAATTACATAA", "AACAATTACATAAC", "AACAATTACATAACT", "AACAATTACATAACTT", "AATTA", "AATTAT", "AATTATA", "CCATTAT", "CCATTATA", "TTATAT", "TTATATT", "TTATATTA", "TTATATTAT", "TACAA", "TACAAT", "TACAATA", "AACCATA", "TTACCA", "TTACCAA", "TTACCAAT", "TTACCAATA", "TTACACT", "AACAACT", "CCACTA", "CCACTAA", "CCACTAAC", "TACTT", "CCTTAAT", "AATACT", "AATACTT", "AATACTTA", "AATACTTAT", "AATACTTATA", "AATACTTATAT", "CCAAC", "CCAACA", "CCAACAA", "CCAACAAT", "CCAACAATA", "CCAACAATAA", "CCAACAATAAT", "CCAACAATAATA", "TTAATTAT", "TTAATTATT", "TTAATTATTA", "TTAATTATTAT", "TTAATTATTATA", "TTAATTATTATAT", "TTAATTATTATATT", "TACAAC", "TACAACT", "TACAACTA", "TACAACTAA", "TACAACTAAC", "TACAACTAACA", "TACAACTAACAT", "CCTAATAC", "CCTAATACT", "CCTAATACTA", "CCTAATACTAA", "CCTAATACTAAT", "CCTAATT", "AACTTAT", "AACTTATT", "AACTTATTA", "CCAACT", "CCAACTA", "CCAACTAC", "CCAACTACC", "CCAATT", "CCAATTA", "CCAATTAT", "TAATT", "TAATTA", "TAATTAC", "TAATTACC", "AACATT", "AACATTA", "TTACCAT", "TTACCATT", "TTACTA", "TTACTAA", "TTACTAAC", "TTACTAACA", "TTACTAACAA", "TTACTAACAAT", "CCACCAA", "CCAACC", "AACACAT", "TTATTAC", "TTATTACA", "TTATTACAA", "TTATTACAAT", "TAATTAT", "TAATTATT", "TATAAC", "TATAACC", "TAATAC", "TAATACT", "TAATACTT", "CCAACTAA", "AACTAC", "AACTACC", "AACTACCA", "AACTACCAA", "AACTACCAAC", "AACTACCAACC", "AACTACCAACCA", "AACTACCAACCAT", "AACTACCAACCATA", "AACTACCAACCATAC", "AACTACCAACCATACT", "CCTATA", "CCTATAA", "CCTATAAT", "CCTATAATT", "CCTATAATTA", "CCTATAATTAT", "CCACTTA", "CCACTTAA", "CCACTTAAT", "AACAACC", "AACAACCA", "AACAACCAT", "AATACAT", "AATACATA", "AATACATAT", "AATTATT", "AATTATTA", "AATTATTAT", "AACCTAACA", "AACCTAACAT", "AACCTAACATA", "AACCTAACATAA", "AACCTAACATAAT", "AACCAAC", "AACCAACT", "AACCAACTA", "TACTAAC", "TACTAACA", "TACTAACAA", "TACTAACAAC", "TACTAACAACA", "TACTAACAACAT", "AACTAT", "AACTATA", "AACTATAC", "AACTATACA", "AACTATACAT", "CCACCTT", "CCACCTTA", "CCACCTTAA", "CCACCTTAAT", "AATACC", "AATACCT", "TTATAATA", "TTATAATAC", "TTATAATACT", "TTATAATACTA", "TTATAATACTAT", "CCTACCA", "CCTACCAC", "CCTACCACC", "CCACAATT", "CCACAATTA", "CCACAATTAA", "CCACAATTAAT", "AACATAAC", "AACATAACC", "AACATAACCA", "AACATAACCAT", "AACATAACCATT", "AACATAACCATTA", "AACATAACCATTAT", "CCTATAC", "CCTATACT", "AATACAC", "AATACACA", "AATACACAC", "AATACACACA", "AATACACACAC", "AATACACACACC", "AATACACACACCA", "AATACACACACCAC", "AATACACACACCACA", "AATACACACACCACAT", "AATACACACACCACATT", "AATACACACACCACATTA", "AATACACACACCACATTAA", "AATATA", "AACCTATT", "CCACTAT", "AACCAC", "AACCACA", "AACCACAA", "AACCACAAT", "CCAACTAT", "CCAACTATA", "CCAACTATAA", "CCAACTATAAT", "CCAACTATAATA", "CCAACTATAATAT", "CCAACTATAATATT", "CCATACA", "CCATACAT", "CCTACA", "CCTACAA", "CCAACAC", "CCTACT", "AACTATACAA", "AACCAAT", "CCTACTA", "CCTACTAA", "CCTACTAAT", "CCTACTAATT", "CCTACTAATTA", "CCTACTAATTAC", "CCTACTAATTACT", "CCTTACA", "AATAAT", "AATAATA", "AATAATAC", "AATAATACC", "CCAACAAC", "CCAACAACT", "CCAACAACTA", "CCAACAACTAT", "AATAACT", "AATAACTA", "AATAACTAT", "AATAACTATA", "AATAACTATAC", "AATAACTATACC", "CCTACTAT", "AACTAA", "AACTAAC", "AACTAACT", "TTATATA", "TTATATAC", "TTATATACC", "AATATAA", "AATATAAC", "AATATAACA", "AATATAACAA", "AACACTA", "AACACTAC", "AATTAA", "AACCATTA", "AACCATTAA", "CCACCATA", "CCACCATAA", "AACTTAC", "AACTTACA", "AACTTACAA", "AATACAAT", "TTAACCA", "TTAACCAT", "AATTATTAA", "AATTATTAAT", "CCACAT", "CCTAACAC", "CCATACTAT", "CCATACTATA", "CCATACTATAC", "CCATACTATACA", "CCATACTATACAA", "CCATACTATACAAT", "TTAACAC", "TTAACACT", "CCATATT", "CCATATTA", "CCATATTAA", "CCTAACAT", "AACTATT", "CCATAAC", "CCATAACT", "CCATAACTT", "AACACCACAA", "AACACCACAAC", "AACACCACAACC", "AACACAC", "AACACACA", "AACACACAA", "AACACACAAT", "CCATATAC", "CCATATACA", "CCATATACAT", "CCATATACATA", "CCATATACATAA", "TTATATAT", "TTATATATA", "TTATATATAT", "TTATATATATT", "TTATATATATTA", "TTATATATATTAC", "TTATATATATTACA", "TTATATATATTACAT", "TTATATATATTACATA", "TTATATATATTACATAA", "AATATAT", "AATATATT", "CCTTACC", "TTATAATAA", "TTATAATAAT", "TTATAATAATA", "TTATAATAATAC", "CCTATTA", "CCTATTAT", "CCTATTATT", "CCTATTATTA", "CCTATTATTAC", "CCTATTATTACT", "CCTATTATTACTA", "AACATAT", "AACATATA", "AACATATAA", "AACTAAT", "AACTAATA", "AATATAC", "AATATACT", "AATATACTA", "AATATACTAA", "TTAACTA", "TTAACACC", "TTAACACCT", "AACACAACTAA", "AACACAACTAAC", "AACACAACTAACA", "AACACAACTAACAC", "AACACAACTAACACT", "AACACAACTAACACTA", "TTAACTT", "AACTTACC", "AACTTACCT", "TTAATAAT", "TTAATAATA", "AATTAAT", "AATTAATT", "CCAACACA", "AACAACCT", "TTAACCAA", "AACACACT", "AACACACTA", "AACACACTAC", "AACACACTACC", "CCTTACAT", "CCTAACCA", "CCTAACCAT", "CCTAACCATA", "CCTAACCATAT", "CCACTAC", "CCACTACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC"};

System.out.println("========= U20753 ==========\t" + longSequence_3_2.length + "\tS3\t" + s3.length());

String[] longSequence_4_2 = {"AC", "TA", "AA", "AT", "CA", "CC", "CT", "TC",
		"CTC", "TCT", "TCTC", "CTCT", "CTCTC", "CTCTCT", "CTCTCTC", "ATC", "ATCT", "ATCTC", "ATCTCT", "TCTCT", "TCTCTC", "TCC", "TCCT", "TCCTC", "CCT", "CCTC", "CCTCT", "CCTCTC", "CTCC", "TCCTCC", "ATCC", "CCTCC", "CCTCCT", "ACC", "ACT", "ATCCT", "ACCT", "ACTC", "ACTCT", "ACCTC", "ACTCC", "ACTCCT", "TCTCC", "TCTCCT", "ACCTCT", "ACCTCC", "ACTCCTC", "ACTCCTCT", "TCTCCTC", "TCTCCTCC", "TCTCCTCCT", "AAT", "AATC", "AAC", "AACT", "AACC", "ATCCTC", "ATCCTCT", "AACCT", "AACTC", "AACTCT", "TCTCTCC", "AATCC", "ATCTCC", "ATCTCCT", "AATCT", "AATCCT", "AACCTC", "AACTCC", "AACTCCT", "AACTCCTC", "ATCTCTC", "ATCTCTCT", "ATCTCTCTC", "ATCTCTCTCC", "ACTCCTCC", "AACCTCT", "ATCTCCTC", "AATCTC", "TCTCTCT", "TCTCTCTC", "CTA", "CTAA", "CTAAC", "AACTA", "AACTAA", "AACTAAC", "ACTA", "ACTAT", "ACTATA", "TAC", "TACC", "CTAT", "TAT", "TATC", "TATCC", "TATCCT", "TATCCTA", "TATCCTAA", "TATCCTAAT", "TAA", "TAAT", "CTCTCC", "CTAC", "AATCCTC", "AATCCTCT", "AATCCTCTA", "AATCCTCTAT", "AATCCTCTATA", "AATCCTCTATAT", "AATCCTCTATATA", "AATCCTCTATATAA", "AATCCTCTATATAAT", "AATCCTCTATATAATA", "TAATA", "TAATAA", "TAATAAT", "TAATAATC", "TAATAATCC", "TAATAATCCT", "TAAC", "TAACC", "ATA", "ATAC", "ATACT", "AATA", "AATAT", "AATATA", "AATATAC", "ACCTA", "ACCTAA", "ACCTAAC", "ACCTAACC", "ACCTAACCT", "ACTCCTA", "ACTCCTAC", "ATAA", "ATAAT", "ATCTA", "ATCTAA", "AATAA", "AATAAC", "CTAAT", "CTAATC", "CTAATCT", "CTAATCTC", "CCTA", "CCTAA", "CCTAAT", "CCTAATC", "CCTAATCT", "CCTAATCTC", "CCTAATCTCT", "CCTAATCTCTA", "CCTAATCTCTAC", "CCTAT", "CCTATC", "ACTAA", "ATCTAC", "AATAC", "AATACT", "AATACTC", "AATACTCT", "AATACTCTA", "AATACTCTAA", "AATCTCT", "AATCTCTC", "AATCTCTCC", "AATCTCTCCT", "ATAT", "AATAACT", "AATAACTA", "AATAACTAT", "ACTCCTACC", "CCTCTA", "CCTCTAA", "CCTCTAAC", "CCTCTAACC", "ACTAC", "ACTACC", "AATAAT", "AATAATA", "ACTACCT", "ACTACCTA", "ACTACCTAT", "ACCTAAT", "ACCTAATC", "ACTAAT", "ACTAATC", "ACTACT", "AATAATAT", "AATAATATC", "AATAATATCT", "AATAATATCTA", "AATAATATCTAC", "AATAATATCTACC", "ATAATA", "ATAATAA", "ATAATAAC", "ACTAATA", "ACTAATAC", "ACTAATACT", "ATCCTA", "ATCCTAT", "AATCTA", "AATCTAC", "AATCTACT", "AATCTACTA", "ACCTAC", "ACCTACC", "AACCTA", "AATACTA", "CCTAATA", "CCTAATAA", "AACA", "AACAT", "CCA", "CCAA", "CCAAC", "CCAACT", "CCAACTA", "CCAACTAC", "CCAACTACA", "CCAACTACAT", "CCAACTACATC", "CCAACTACATCC", "CCAACTACATCCT", "CCTAC", "ACA", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "ACAACACC", "ACAACACCT", "ACAACACCTA", "ACAACACCTAA", "ACAACACCTAAT", "CCAC", "CCACC", "CCACCA", "CCACCAA", "CAT", "CATA", "CATAT", "CATATC", "CATATCA", "CATATCAC", "CATATCACA", "CATATCACAT", "CATATCACATA", "CCTAAC", "CCTAACA", "CTCTA", "CTCTAT", "CTCTATC", "CTCTATCA", "CAC", "CACA", "CACAT", "CATAA", "CATAAT", "CATAATC", "CATAATCT", "CCAACA", "CCAACAC", "CCAACACC", "CCACA", "CATC", "CATCC", "CATCCA", "CATCCAC", "CATCCACC", "CCTATA", "CATCA", "CCACCAC", "CCACCACA", "CAA", "CAAT", "CAATC", "AACATA", "AACATAT", "AACATATA", "AACATATAC", "AACATATACT", "CCAACTAA", "AATATC", "AATATCT", "AATATCTA", "AATATCTAT", "AATATCTATA", "AATATCTATAC", "AATATCTATACA", "AATCCA", "CCAAT", "CCAATA", "AACAA", "AACAAC", "AACAACT", "AACAACTA", "AACAACTAA", "AACAACTAAC", "AACAACTAACC", "AACAACTAACCT", "AACAACTAACCTA", "AACAACTAACCTAA", "AACTAT", "AACTATA", "AACTATAC", "AACTATACT", "AACCA", "AACCAA", "CCACT", "AACTCTC", "AACTCTCA", "AACTCTCAC", "AACTCTCACT", "CCAT", "CCTACT", "CCTACTA", "AATCA", "AATCAT", "CCACCT", "AACAACA", "AACAACAT", "AACAACATA", "AACAACATAA", "AACAACATAAT", "AACAACATAATC", "AACAACATAATCT", "AATCAA", "AATCAAT", "AATCTACTAC", "AATCTACTACT", "AATCTACTACTA", "AATCTACTACTAA", "AATCTACTACTAAT", "AATCTACTACTAATC", "AATCTACTACTAATCC", "AATCTACTACTAATCCA", "AATCTACTACTAATCCAA", "AATCTACTACTAATCCAAT", "AATCTACTACTAATCCAATA", "AATCTACTACTAATCCAATAC", "AATCTACTACTAATCCAATACT", "AATCTACTACTAATCCAATACTC", "AATCTACTACTAATCCAATACTCA", "AATACC", "CCTCTAT", "CCTAACAA", "AATCAC", "AATCACT", "AACAC", "AACACC", "AACACCA", "AACACCAT", "AACTCA", "AACTCAA", "AACTAC", "AACACT", "CCACTA", "CCACTAA", "CCACTAAT", "CCACTAATA", "CCTACC", "CCAACAA", "CCAACAAT", "CCAACAATC", "CCAACAATCA", "CCAACAATCAA", "CCAACAATCAAC", "CCAACAATCAACC", "CCAACAATCAACCT", "CCAACAATCAACCTC", "CCTATAC", "CCTATACT", "CCTATACTC", "CCTATACTCC", "CCTCTATA", "CCTCTATAT", "CCTCTATATA", "CCTCTATATAT", "AACCAT", "AACCATC", "AACCATCA", "AACCATCAT", "AATATACT", "AATATACTA", "AATATACTAA", "AATATACTAAT", "AATATACTAATA", "CCTCCA", "CCTCCAC", "CCTCCACC", "CCTCCACCT", "CCTCCACCTC", "CCTCCACCTCC", "CCTCCACCTCCT", "CCTCCACCTCCTA", "CCTCCACCTCCTAC", "CCTCCACCTCCTACC", "CCATA", "CCATAT", "CCTCTCT", "CCTCTCTC", "CCTCTCTCA", "CCTCTCTCAT", "CCTCTCTCATA", "CCTCTCTCATAC", "CCTCTCTCATACT", "CCTCTCTCATACTA", "CCTCTCTCATACTAA", "CCTCTCTCATACTAAC", "CCTCTCTCATACTAACC", "CCTCTCTCATACTAACCT", "CCTCTCTCATACTAACCTC", "CCTCTCTCATACTAACCTCA", "CCTCTCTCATACTAACCTCAT", "CCTCA", "CCTCAA", "CCTCAAC", "CCTCAACA", "CCTCAACAT", "CCTCAACATC", "CCTCAACATCC", "CCTCAACATCCA", "CCTCAACATCCAC", "CCTCAACATCCACA", "CCTCAACATCCACAA", "AATATCA", "AATATCAT", "AATATCATC", "AATATCATCT", "AATATCATCTC", "AATATCATCTCA", "AATATCATCTCAT", "CCACAA", "CCACAAT", "CCACAATA", "CCACAATAA", "CCACAATAAT", "CCACAATAATA", "CCACAATAATAT", "AACTCTA", "AACTCTAA", "AACTATC", "AACTATCA", "AACTATCAC", "AACTATCACT", "CCATATC", "CCATATCA", "CCATATCAC", "CCATATCACC", "CCTACA", "CCTACAA", "AACTCAT", "CCTAACC", "CCTAACCA", "CCTAACCAC", "CCTAACCACA", "CCTAACCACAC", "CCTAACCACACA", "CCTAACCACACAA", "AACCTCC", "CCACAC", "CCACACC", "CCACACCA", "AATAACA", "AATAACAA", "AACACTC", "AACACTCA", "AACACTCAC", "AACACTCACA", "AACACTCACAC", "AACACTCACACT", "AACACTCACACTA", "AACACTCACACTAT", "AATCATC", "CCACCTA", "AACAAT", "AACAATA", "AACAATAC", "AACAATACC", "CCTACTAA", "CCTACTAAT", "CCTACTAATA", "CCTACTAATAA", "CCTACTAATAAC", "CCTACTAATAACA", "CCTACTAATAACAC", "CCTACTAATAACACT", "CCTACTAATAACACTC", "CCTACTAATAACACTCA", "CCTACTAATAACACTCAT", "CCTACTAATAACACTCATC", "CCAACC", "AATAACC", "CCACCTAA", "CCACCTAAC", "CCACCTAACA", "CCACCTAACAA", "AACTCTAT", "CCAACAAC", "CCAACAACC", "CCAACAACCA", "CCAACAACCAC", "CCAACAACCACC", "AATAACCA", "AATAACCAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACC", "AACATC", "AACATCC", "CCAACAATCT", "AACATCT", "AATCCTCA", "AATCCTCAA", "AATCCTCAAC", "AATCCTCAACA", "AATCCTCAACAT", "AACAACC", "AACAACCA", "AACAACCAA", "AACACCT", "AACACCTA", "AACACCTAC", "AACACCTACA", "AACACCTACAA", "CCATAA", "CCATAAC", "CCATAACT", "CCATAACTA", "CCATAACTAT", "CCATAACTATA", "CCATAACTATAC", "CCATAACTATACA", "CCATAACTATACAA", "CCATAACTATACAAC", "CCACAATC", "CCACAATCT", "CCACAATCTC", "CCACAATCTCC", "CCACAATCTCCT", "CCACAATCTCCTC", "CCACAATCTCCTCA", "CCACAATCTCCTCAT", "AACCAC", "AACCACC", "CCAATC", "CCAATCC", "CCTATACC", "CCTATACCA", "CCTATACCAC", "CCTATACCACT", "CCTATACCACTA", "CCTATACCACTAA", "AACACA", "AACACAA", "AACACAAC", "AACACAACC", "AACACAACCT", "AACACAACCTC", "AACACAACCTCT", "AACACAACCTCTA", "AACACAACCTCTAC", "AACACAACCTCTACC", "AACACAACCTCTACCT", "AACACAACCTCTACCTC", "AACACAACCTCTACCTCA", "AACACAACCTCTACCTCAC", "AACACAACCTCTACCTCACC", "AACACAACCTCTACCTCACCA", "AACACAACCTCTACCTCACCAT", "AACATATAT", "AACATATATA", "AACATATATAA", "AACCAAC", "AACCAACA", "AACCAACAT", "CCACACCT", "CCACACCTC", "CCACACCTCA", "AACTACT", "AACTACTA", "AACTACTAA", "AACTACTAAC", "AACTACTAACA", "AACTACTAACAT", "AACTACTAACATC", "AACTACTAACATCC", "AACTCATC", "AACTCATCA", "AACTCATCAC", "CCACCACT", "AACCAACAA", "AACTAAT", "AATACA", "AATACAA", "AATACAAT", "AATACAATA", "AATACAATAC", "AATACAATACT", "AATACAATACTC", "AATACAATACTCA", "AATACAATACTCAA", "AATCCAA", "AATCCAAT", "AATCCAATA", "AATCCAATAT", "AATCCAATATA", "AATCCAATATAA", "AATCATCA", "AATCATCAC", "AATCATCACT", "AATCATCACTA", "AATCATCACTAT", "AATCTCA", "AATCTCAC", "AATCTCACC", "AATCATCAA", "AATCATCAAC", "AATCATCAACC", "AATCATCAACCA", "AATCATCAACCAC", "AATCATCAACCACT", "AATCATCAACCACTC", "AATCATCAACCACTCA", "AATCATCAACCACTCAT", "CCATC", "CCATCA", "CCATCAA", "AACATCTC", "AACATCTCA", "AACATCTCAT", "AACATCTCATC", "AACATCTCATCT", "CCATATCT", "AATATCC", "AATATCCT", "AACCTCCT", "AACCTCCTC", "AACCTCCTCT", "AACCTCCTCTC", "AACCTCCTCTCA", "CCTATAT", "CCTCAC", "CCTCACC", "CCTCCACA", "CCTCCACAT", "CCTCCACATA", "CCTCCACATAT", "CCTCCACATATC", "CCTCCACATATCA", "CCTCCACATATCAA", "CCTACCT", "CCTACCTA", "CCTACCTAC", "CCTACCTACA", "CCTACCTACAT", "CCTACCTACATA", "CCTACCTACATAC", "CCTACCTACATACA", "CCTACCTACATACAT", "CCTACCTACATACATC", "CCTACCTACATACATCC", "CCTACCTACATACATCCA", "CCTACCTACATACATCCAA", "AACAACCT", "AACAACCTC", "AACAACCTCT", "CCTAACT", "AACCACA", "AACCACAA", "CCAACTAT", "CCTCTCA", "CCTCTCAC", "CCTCTCACC", "AACTATACC", "AACTATACCT", "CCACTC", "CCACTCT", "CCTACAC", "CCTACACC", "CCTACACCA", "CCTACACCAC", "CCTACACCACA", "CCTACACCACAT", "CCTACACCACATA", "CCTACACCACATAT", "CCTACACCACATATA", "CCTACACCACATATAC", "CCTACACCACATATACT", "CCACTAT", "CCACTATA", "CCACTATAC", "CCACTATACA", "CCACTATACAC", "CCACTATACACT", "CCTCAT", "AACTCTCAA", "AACTCTCAAT", "AACTCTCAATC", "AACTCTCAATCA", "AACTCTCAATCAC", "AACTCTCAATCACC", "AACACAT", "AACACATA", "AATCACC", "CCACACT", "CCACACTA", "CCACACTAC", "CCACACTACA", "AATATCATA", "AATATCATAA", "AACCACAAC", "AACCACAACA", "AACCACAACAC", "AACCACAACACA", "AACCACAACACAA", "AACCACAACACAAC", "AACCACAACACAACA", "AACCACAACACAACAT", "AACCACAACACAACATA", "AACCACAACACAACATAT", "AATCCTA", "AATCCTAA", "AATCCTAAC", "AATCCTAACC", "CCAACTACC", "CCAACTACCA", "CCAACTACCAC", "CCAACTACCACT", "CCAACTACCACTA", "CCAACTACCACTAA", "CCAACAACT", "CCAACAACTC", "CCAACAACTCT", "CCAACAACTCTA", "CCAACAACTCTAA", "CCAACAACTCTAAT", "AACACAAT", "AACACAATC", "AACACAATCC", "AACACAATCCA", "AACACAATCCAA", "AACACAATCCAAC", "AACACAATCCAACA", "AACACAATCCAACAA", "AACACAATCCAACAAC", "AACACAATCCAACAACA", "AACACAATCCAACAACAA", "AACTACC", "AACTACCT", "AACTACCTA", "AACTACCTAT", "AACTACCTATC", "AACTACCTATCA", "AACTACCTATCAA", "CCAACACA", "AACATAA", "AACATCA", "AACATCAC", "AACATCACC", "AACATCACCT", "AACATCACCTC", "AACATCACCTCT", "AACATCACCTCTA", "AACAACCTCC", "AACCAAT", "AACCAATA", "CCAATCCT", "CCAATCCTA", "CCAATCCTAC", "CCAATCCTACT", "CCAATCA", "CCAATCAT", "CCAATCATA", "CCTAACAAT", "CCTAACAATA", "CCTAACAATAT", "CCATCC", "CCATCCT", "CCATCCTC", "CCATCCTCT", "CCATCCTCTA", "CCATCCTCTAA", "CCATCCTCTAAT", "CCATCCTCTAATC", "CCATCCTCTAATCA", "CCATCCTCTAATCAA", "CCATCCTCTAATCAAC", "CCATCCTCTAATCAACA", "CCATCCTCTAATCAACAT", "CCATCCTCTAATCAACATA", "CCATCCTCTAATCAACATAA", "CCATCAT", "AACCAACC", "CCTACAT", "CCTACATA", "CCTACATAC", "CCTACATACC", "CCTACATACCA", "CCTACTAC", "CCTACTACT", "CCTACTACTA", "CCTACTACTAA", "CCTACTACTAAC", "CCTACTACTAACC", "CCTACTACTAACCA", "CCTACTACTAACCAT", "CCTACTACTAACCATC", "CCTACTACTAACCATCT", "CCTACTACTAACCATCTC", "CCTACTACTAACCATCTCC", "CCTACTACTAACCATCTCCT", "CCATAC", "CCATACC", "AACCTAT", "CCTCATC", "CCTCATCA", "CCTCATCAT", "CCTCATCATA", "CCTCATCATAA", "CCTCAAT", "CCTCAATC", "CCTCAATCA", "CCTCAATCAA", "CCTCAATCAAC", "CCTCAATCAACC", "CCTCAATCAACCT", "CCTCAATCAACCTA", "CCTCCTA", "CCTCCTAT", "CCTCCTATC", "CCTCCTATCC", "CCTCCTATCCA", "CCTCCTATCCAT", "CCTCCTATCCATC", "CCTCCTATCCATCC", "CCTCCTATCCATCCT", "CCTCCTATCCATCCTA", "AATCATA", "CCACAT", "CCACATA", "CCACACA", "CCACACAT", "CCTAATCA", "CCTAATCAC", "CCTAATCACC", "CCTAATCACCT", "CCTAATCACCTC", "CCTAATCACCTCC", "CCTACTC", "CCTACTCA", "CCTACTCAA", "CCTACTCAAC", "CCTACTCAACC", "CCTACTCAACCT", "CCTACTCAACCTA", "CCTACTCAACCTAT", "CCTACTCAACCTATA", "CCTACTCAACCTATAC", "CCTACTCAACCTATACT", "CCACACTAA", "CCACACTAAT", "CCACACTAATC", "CCACACTAATCA", "CCACACTAATCAT", "CCACACTAATCATC", "CCACACTAATCATCA", "CCACACTAATCATCAT", "CCACACTAATCATCATA", "CCACACTAATCATCATAT", "CCACACTAATCATCATATC", "CCACACTAATCATCATATCA", "CCACACTAATCATCATATCAA", "CCACACTAATCATCATATCAAC", "CCACACTAATCATCATATCAACA", "CCACACTAATCATCATATCAACAC", "CCACACTAATCATCATATCAACACT", "CCACACTAATCATCATATCAACACTA", "CCACACTAATCATCATATCAACACTAC", "CCACACTAATCATCATATCAACACTACT", "CCACACTAATCATCATATCAACACTACTC", "CCACACTAATCATCATATCAACACTACTCC", "CCACACTAATCATCATATCAACACTACTCCT", "CCACACTAATCATCATATCAACACTACTCCTC", "CCACACTAATCATCATATCAACACTACTCCTCC", "CCACTAATAC", "CCACTAATACC", "CCACTAATACCA", "CCACTAATACCAA", "CCACTAATACCAAC", "CCACTAATACCAACC", "CCACTAATACCAACCA", "CCACTAATACCAACCAT", "CCACTAATACCAACCATA", "CCACTAATACCAACCATAT", "CCACTAATACCAACCATATC", "CCACTAATACCAACCATATCC", "CCACTAATACCAACCATATCCA", "CCACTAATACCAACCATATCCAT", "CCTAATCAA", "CCTAATCAAC", "CCTAATCAACT", "AATCTACA", "AATCTACAA", "AATCTACAAT", "AATAATATA", "AATAATATAA", "CCTACACCT", "CCTACACCTA", "AACCACCA", "AACACCAC", "AACACCACC", "AACACCACCT", "CCTATCC", "CCTATCCT", "CCTATCCTA", "CCTATCCTAT", "CCTATCCTATA", "CCTATCCTATAC", "CCTATCCTATACC", "CCTATCCTATACCA", "CCTATCCTATACCAA", "CCTATCCTATACCAAC", "CCTATCCTATACCAACA", "CCTATCCTATACCAACAT", "CCTATCCTATACCAACATC", "CCTATCCTATACCAACATCT", "CCTATCCTATACCAACATCTC", "CCTATCCTATACCAACATCTCT", "CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACAAC", "CCACAACA", "CCACAACAT", "AATACCA", "AATACCAT", "CCTCATCT", "CCTCATCTC", "CCTCCAT", "CCTCCATA", "CCTCCATAT", "CCTCCATATC", "CCTCCATATCA", "CCTCCATATCAT", "CCTCCATATCATA", "CCTCCATATCATAC", "CCTCCATATCATACA", "CCTCCATATCATACAT", "CCTATATA", "CCTATATAT", "CCTATATATC", "CCTATATATCT", "CCTATATATCTC", "CCTATATATCTCC", "CCTATATATCTCCA", "CCTATATATCTCCAT", "AACTAACA", "AACTAACAC", "AACTAACACA", "AACTAACACAC", "AACTAACACACA", "AACTAACACACAC", "AACTAACACACACA", "AACTAACACACACAA", "AACCATA", "CCACATC", "CCACATCA", "CCTAATCTCTACA", "CCTAATCTCTACAC", "AACACTCC", "AACACTCCT", "AACACTCCTA", "AACACTCCTAC", "AACACTCCTACC", "AACCAATC", "AACCAATCA", "AACCAATCAA", "AACCAATCAAT", "AACCAATCAATA", "AACCAATCAATAC", "AACCAATCAATACA", "AACCAATCAATACAT", "AACCAATCAATACATC", "AACCAATCAATACATCA", "AACCAATCAATACATCAA", "AACCAATCAATACATCAAT", "CCAACAATA", "CCAACAATAA", "CCAACAATAAT", "CCAACAATAATA", "CCATAAT", "CCATAATA", "CCATAATAA", "CCATAATAAC", "CCATAATAACA", "CCATAATAACAA", "CCTCATCC", "CCTCATCCT", "CCTCATCCTA", "CCTCATCCTAT", "CCACTCA", "CCACTCAT", "CCAATAC", "CCAATACT", "CCAATACTA"};

System.out.println("========= X97336 ==========\t" + longSequence_4_2.length + "\tS4\t" + s4.length());

String[] longSequence_5_2 = {"AC", "TA", "AA", "AT", "CA", "CC", "CT", "TC",
		"CAA", "AAC", "AACA", "ACA", "ACAC", "CAAC", "CAACA", "CAACAC", "CAACACT", "ACT", "AACT", "CAC", "CACT", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "CACA", "CACAC", "CACACA", "CACAA", "CAACT", "ACACT", "AACAC", "AACACA", "CACAAC", "CACAACT", "AACAA", "AACAAC", "AACAACT", "AACACT", "CACACT", "TAC", "TACT", "TACA", "ACTA", "TAA", "CAACACTA", "CAACACTAC", "CAACACTACA", "CAACACTACAA", "CAACACTACAAC", "CTA", "CTAA", "CAACAA", "CACTA", "CACTAA", "ACTAC", "ACTACT", "CTAC", "CTACA", "CTAAC", "AACTA", "AACTAA", "CAACACTAA", "CTACT", "CTACAC", "CTACACA", "CTACACAC", "CTACACACT", "CTACACACTA", "CTACACACTAA", "CTAACA", "CACTAC", "CTACTA", "CTACTAA", "TACTA", "TACTAC", "TACAA", "TACAAC", "TACAACA", "TACAACAA", "TACTAA", "TAAC", "TAACT", "TAACTA", "TCA", "TCAC", "CTC", "CTCT", "CTCTA", "CTCTAC", "CTCTACT", "CTCTACTC", "TCACA", "TCACAA", "TCACAAC", "TCACAACA", "TCACAACAC", "TCACAACACA", "TCACAACACAA", "TCACAACACAAC", "TACAC", "TACACA", "TACACAC", "TACACACA", "TACACACAC", "TCAA", "TCAAC", "TCAACA", "CACTC", "CACTCA", "ACTC", "CTACTAC", "CTACTACT", "CTACTACTC", "TCACT", "TAACA", "TAACAA", "AACACTA", "TCT", "TCTA", "CTCTC", "CTCTCT", "CTCTCTC", "CTCTCTCA", "CTCTCTCAA", "CAACTC", "TCTC", "CTCA", "TCACTA", "CTACAA", "TCTCA", "TCACTC", "TCACTCT", "TCTCAA", "CTCAA", "TACTC", "CTAACAA", "CTAACAAC", "CAACACC", "CCT", "TCC", "TCCT", "TAACC", "CCA", "TCCTA", "TCAACAC", "TCAACACC", "TCAACACCA", "TCAACACCAA", "TCAACACCAAC", "TCAACACCAACA", "TCAACACCAACAC", "TCAACACCAACACA", "ACC", "ACCT", "ACCTC", "ACCTCC", "ACCTCCA", "ACCTCCAA", "ACCTCCAAC", "ACCTCCAACA", "ACCTCCAACAA", "CCAA", "CCAAC", "CCAACC", "CCAACCT", "TACC", "TACCA", "TACCAA", "TACCAAC", "TACCAACC", "TACCAACCT", "TACCAACCTC", "TACCAACCTCC", "CTCC", "CTCCT", "CTCCTA", "CCTC", "CCTCC", "CCTCCT", "CCTCCTC", "CCTCCTCC", "CCTCCTCCT", "CCTCCTCCTA", "ACTCC", "ACTCCA", "ACTCCAC", "ACTCCACC", "CCTA", "CCTAC", "CCTACA", "CCTACAC", "CCTACACC", "CCTACACCA", "CCTACT", "CCTACTC", "CCTACTCC", "CCTACTCCA", "CCTACTCCAC", "CCTACTCCACT", "CCTACTCCACTC", "CCTACTCCACTCC", "CCTACTCCACTCCA", "TCCA", "TCCAC", "TCCACT", "TCCACTA", "TCCACTAA", "AACAACA", "AACAACAA", "TCACC", "TCACCA", "TCACCAC", "TCACCACA", "TCACCACAC", "TCACCACACT", "TCACCACACTA", "CACTCAC", "CACTCACT", "CACTCACTC", "CACTCACTCA", "CACTCACTCAA", "TCTCC", "TCTCCA", "TCTCCAC", "TCTCCACC", "TCTCCACCT", "TCTCCACCTC", "TCTCCACCTCC", "TCTCCACCTCCA", "CCAACT", "CCAACTA", "TAACCA", "TCAACC", "CCTACC", "CCTACCT", "CCTACCTA", "TCCTC", "TCCTCC", "TCCTCCA", "TCCTCCAC", "TCCTCCACA", "TCCACA", "CCTAA", "TCACCACC", "TCACCACCA", "TCACCACCAC", "TCACCACCACA", "TCTCT", "TCTCTA", "TCTCTAA", "CACTCACC", "TCCTCA", "TCCTCAC", "TCCTCACA", "TCTAC", "TCTACT", "TCTACTC", "TCTACTCC", "TCTACTCCA", "TCTACTCCAA", "ACCTCA", "AACC", "CCAACA", "CCAACAC", "CCAACACA", "TACACC", "TACACCA", "TACACCAA", "TACACCAAC", "CCTACTA", "CCTACTAA", "TAACTC", "TAACTCT", "TAACTCTC", "TAACTCTCA", "CCAC", "CCACA", "CCTCA", "CCTCAC", "CCTCACA", "TCTACA", "CACTCC", "CACTCCT", "CACTCCTA", "CAACC", "CCTCT", "ACCTCAA", "TCTAA", "TAACTCC", "TAACTCCA", "TCTCCAA", "TCTCCAAC", "TCTCCAACA", "TCTCCAACAA", "TCTACC", "CCACAA", "CCACAAC", "CCACAACC", "TACCT", "TACCTA", "TACCTAC", "TACCTACT", "AACTC", "AACTCA", "AACTCAC", "AACTCACC", "TACACT", "TACACTA", "AACACTC", "AACACTCC", "AACACTCCA", "AACACTCCAC", "AACACTCCACC", "AACACTCCACCA", "AACACTCCACCAA", "AACACTCCACCAAC", "TCTCCAACC", "TCTCCAACCT", "TCTCCAACCTC", "TCTCCAACCTCC", "TCTCCAACCTCCT", "TCTCCAACCTCCTA", "TACTACC", "CCAACAA", "CCACC", "CTAACC", "CTAACCT", "CTAACCTA", "TCTACTA", "ACTCA", "ACTCAA", "TCCAA", "AACCT", "AACCTC", "TCCTAA", "TCCACC", "TCCTCT", "TCCTCTC", "TCCTCTCA", "TACTACT", "TAACCT", "TAACCTC", "TAACCTCT", "TAACCTCTA", "TAACCTCTAA", "TCAACCA", "TCACAACC", "TCCTCACT", "TCCTCACTA", "TCCTCACTAA", "CCACAC", "CCACACC", "CCACACCA", "CCACACCAC", "CCACACCACT", "CCACACCACTA", "CCACACCACTAA", "AACACTAC", "AACACTACC", "CCACT", "TCCTCACTC", "TCCTCACTCT", "AACCA", "AACCAA", "AACCAAC", "AACCAACA", "TAACCTCC", "TAACCTCCA", "TCAACAA", "TCAACAAC", "TCAACAACC", "ACACC", "TACCTC", "TACCTCA", "TAACCAA", "AACAACC", "AACAACCA", "AACAACCAA", "AACAACCAAC", "AACAACCAACA", "AACACC", "AACACCA", "CCTCCA", "CCTCCAA", "TACCAC", "TACCACA", "TACCACAA", "TACCACAAC", "TACCACAACC", "TACCACAACCT", "TACCACAACCTA", "TACCACAACCTAC", "TACCACAACCTACC", "CCTCCAC", "CCTCCACT", "AACTAAC", "AACTAACA", "AACTAACAA", "AACTAACAAC", "AACTAACAACA", "AACTAACAACAA", "TAACAC", "TAACACT", "TAACACTC", "TAACACTCA", "TAACACTCAA", "AACACAA", "TCTAAC", "TCTAACC", "TCTAACCA", "TCTAACCAC", "ACCA", "ACCAA", "TCAACT", "TCAACTA", "AACACCAA", "TCTCAC", "TCTCACC", "CCACTA", "CCACTAA", "ACCTA", "ACCTAC", "ACCTACC", "ACCTACCA", "CCTCAA", "CCTCCTA", "TCCTCCAA", "TACACTAC", "TACACTACA", "TACACTACAC", "TACACTACACA", "ACAACT", "TCACTCA", "TCACTCAC", "TCACTCACA", "TAACTAC", "CTACC", "CTACCT", "CTACCTC", "CTACCTCC", "CTACCTCCA", "CCTCTA", "CCTCTAC", "CCTCTACT", "CCTCTACTA", "CCTCTACTAC", "TCCTAC", "TCCTACT", "TCCTACC", "TCCTACCA", "AACCTCC", "AACCTCCT", "AACCTCCTA", "ACTCCT", "ACTCCTC", "ACTCCTCC", "ACTCCTCCT", "ACTCCTCCTC", "ACTCCTCCTCC", "ACTCCTCCTCCT", "CTCCTAC", "CTCCTACT", "CTCCTACTA", "CTCCTACTAA", "CTCCTACTAAC", "CTCCTACTAACT", "CTCCTACTAACTC", "CTCCTACTAACTCT", "CTCCTACTAACTCTA", "ACCTCCT", "ACCTCCTA", "ACAACTA", "ACAACTAC", "ACAACTACA", "ACAACTACAC", "ACAACTACACC", "CCTCTC", "CCTCTCA", "CTCCA", "TCCTCTCC", "TCCTCTCCA", "CCTCCACA", "ACTCT", "ACACTA", "ACACTAA", "ACACTAAC", "ACACTAACA", "TCCTCTA", "TCCTCTAC", "TCCTCTACT", "TACCACT", "TACCACTC", "CACC", "CACCA", "CTCCAC", "CTCCACC", "CTCCACCA", "ACAACAA", "TAACACC", "TAACACCA", "CCACCT", "TCACCAA", "CCAACTAC", "CCAACCTC", "ACTAA", "CTCAC", "CTCACA", "CTCACAC", "CTCACACA", "CAACCA", "CACCT", "CACCTA", "CACCTAC", "CACCTACC", "AACTCC", "AACTCCA", "AACTCCAC", "AACTCCACA", "CCAACCA", "TAACTCA", "TAACTCAA", "TAACTCAAC", "TAACTCAACA", "TAACTCAACAC", "TAACTCAACACA", "TAACTCAACACAC", "TAACTCAACACACC", "TAACTCAACACACCT", "TAACTCAACACACCTA", "TAACTCAACACACCTAA", "TAACTCAACACACCTAAC", "TAACTCAACACACCTAACA", "TAACTCAACACACCTAACAA", "TACCAACA", "CCTCTAA", "CACACTA", "TAACACCT", "ACACA", "CTACAAC", "CTACAACC", "CTACAACCA", "CTACAACCAA", "TACCTCT", "ACTACA", "ACTACAC", "AACTCAA", "CCACCA", "TCCAAC", "TCCAACA", "CACACAC", "CACACACC", "TACCTAA", "CTACCTA", "AACCAACT", "AACCAACTC", "AACCAACTCA", "AACTAC", "AACTACT", "AACTACTC", "AACTACTCA", "AACTACTCAA", "CTCAAC", "AACTAACT", "AACTAACTC", "AACTAACTCC", "AACTAACTCCT", "AACTAACTCCTA", "AACTAACTCCTAA", "TCAACACA", "TACTCA", "TACTCAC", "TACTCACT", "TCACCT", "TCACCTC", "TCACCTCT", "TCACCTCTA", "TCCTAAC", "TCCTAACC", "AACAACAC", "AACAACACA", "AACAACACAC", "AACAACACACA", "AACAACACACAA", "AACAACCT", "AACAACCTC", "AACAACCTCC", "ACTAAC", "ACTAACC", "ACTAACCA", "TAAT", "TAATC", "TAATCA", "TAATCAC", "TAATCACT", "ATC", "ATCC", "ATCCA", "ATCCAA", "AACCAT", "ATCA", "ATCAA", "ATCAAC", "ATA", "ATAA", "ATAAC", "ATAACA", "CAAT", "CAATC", "CAATCC", "CAATCCT", "CAATCCTA", "CAATCCTAT", "ACAAT", "ACAT", "ACATC", "ACATCC", "ACATCCT", "ACATCCTA", "ACATCCTAA", "ACATCCTAAT", "CTAT", "ATCT", "AACTAAT", "AACTAATA", "ACATA", "ACATAA", "ACATAAT", "ACATAATC", "ACATAATCT", "ACATAATCTA", "ACATAATCTAA", "AAT", "AATC", "AATCT", "AATCTA", "AATCTAA", "AATCTAAC", "AATCTAACT", "AATCTAACTA", "AATCTAACTAA", "AATCTAACTAAT", "CAT", "CATA", "CATAA", "CCTAAC", "CCTAACA", "CCTAACAA", "CCTAACAAC", "CCTAACAACA", "CCTAACAACAT", "CCAAT", "CCAATC", "CCAATCT", "CTATA", "CTATAT", "CCAACAT", "CCAACATC", "CCTAT", "CCTACTAC", "CCTACTACA", "CCTACTACAA", "CCTACTACAAC", "CCTACTACAACC", "CCTACTACAACCT", "CCTACTACAACCTA", "CCTACTACAACCTAT", "AACTAT", "CCACTAC", "CCACTACA", "CCACTACAA", "CCACTACAAC", "CCACTACAACC", "CCACTACAACCA", "CCACTACAACCAC", "CCACTACAACCACT", "CCACTACAACCACTA", "CCACTACAACCACTAA", "CCACTACAACCACTAAC", "CCACTACAACCACTAACA", "CCACTACAACCACTAACAT", "CCACTACAACCACTAACATC", "CCACTACAACCACTAACATCA", "CCACTACAACCACTAACATCAT", "CCACTACAACCACTAACATCATC", "ACATCCA", "ACATCCAT", "ACATCCATA", "ACATCCATAT", "CACCAA", "CACCAAT", "CACCAATC", "CACCAATCC", "CACCAATCCT", "CACCAATCCTA", "CCTAACC", "AATCC", "CCAT", "CCATA", "CCATAC", "CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "CCACTAAT", "CCACTAATC", "CCACTAATCA", "CCACTAATCAA", "CCACTAATCAAC", "CCACTAATCAACA", "CCACTAATCAACAT", "CCACTAATCAACATA", "CCACTAATCAACATAA", "AACCTA", "AATA", "AATAC", "CCTAAT", "AACCATC", "AACCATCT", "AACCATCTC", "AACCATCTCA", "AACCATCTCAT", "AACCATCTCATA", "AACCATCTCATAC", "CAATCA", "CAATCAT", "CAATCATC", "CAATCATCC", "CAATCATCCT", "CAATCATCCTA", "CAATCATCCTAC", "CAATCATCCTACT", "CAATCATCCTACTC", "CAATCATCCTACTCT", "CAATCATCCTACTCTC", "CAATCATCCTACTCTCA", "ATCAT", "AATACC", "AATACCT", "AATACCTC", "AATACCTCT", "CCATC", "CCATCA", "CCATCAT", "CCATAA", "CCATAAT", "AACCAACC", "CTCCAT", "ACCTAA", "ACCTAAC", "ACCTAACA", "AATCA", "CTCTAT", "AACAT", "AACATC", "AACATCA", "AACATCAT", "AACATCATC", "AACATCATCA", "AACATCATCAT", "AACATCT", "AACTCT", "AACTCTA", "AACTCTAC", "AACTCTACT", "AACTCTACTC", "AACTCTACTCA", "AACTCTACTCAA", "AACTCTACTCAAT", "CTCTCC", "CTCTCCT", "CATC", "CATCC", "CATCCT", "CATCCTA", "CATCCTAC", "CATCCTACC", "ATAT", "ACCAAC", "ACCAACT", "CATAT", "CCACCAC", "CCACCACA", "CCACCACAA", "AACATA", "AATAT", "AACTACA", "AATCCT", "AATCCTC", "AATCCTCC", "AATAA", "CTATC", "AACTATA", "AACTATAA", "AACTATAAT", "AACTATAATC", "CTCACACT", "ACTAAT", "ACTAATA", "ACTAATAA", "ACTAATAAT", "ACTAATAATC", "ACTAATAATCT", "AATCTAC", "AATCTACT", "AATCTACTA", "CCTATC", "CCTATCC", "CCTATCCT", "CCTATCCTA", "CCTATCCTAA", "CCTATCCTAAT", "CCTATCCTAATA", "CCTATCCTAATAA", "AACCATA", "CCTCCACC", "CCTCCACCA", "CCTCCACCAA", "CATCA", "CATCAA", "CATCAAT", "CATCAATA", "CATCAATAC", "CATCAATACT", "CATCAATACTC", "CATCAATACTCC", "CATCAATACTCCT", "CATCAATACTCCTC", "CATCAATACTCCTCA", "CATCAATACTCCTCAT", "CATCAATACTCCTCATA", "CATCAATACTCCTCATAA", "CATCAATACTCCTCATAAT", "CATCAATACTCCTCATAATA", "ATCATC", "ATCATCA", "ATCATCAT", "AACAAT", "AACAATC", "AACAATCA", "AACAATCAC", "AACAATCACA", "AACAATCACAA", "ACTCAC", "ACTCACA", "ACTCACAC", "ACTCACACC", "ACTCACACCA", "ACTCACACCAT", "CATCT", "CATCTC", "CATCTCA", "CATCTCAT", "CCAATA", "CCAATAT", "CCAATATC", "CCAATATCA", "CCAATATCAA", "CCAATATCAAT", "CCAATATCAATC", "CCAATATCAATCC", "CCAATATCAATCCT", "CCAATATCAATCCTA", "CCAATATCAATCCTAT", "CCAATATCAATCCTATA", "CCAATATCAATCCTATAT", "CCAATATCAATCCTATATC", "CCAATATCAATCCTATATCA", "CCAATATCAATCCTATATCAA", "AATCTC", "AATCTCA", "AATCTCAC", "AATCTCACC", "CCTCAAT", "AATATC", "AATATCT", "AATCAT", "AATCATA", "CATAC", "CATACT", "CATACTC", "CATATA", "AATAAC", "AATAACA", "CCAACACT", "CCAACACTA", "CCAACACTAA", "CCAACACTAAC", "CCAACACTAACA", "CCAACACTAACAA", "CCAACACTAACAAT", "CCAACACTAACAATA", "CCAACACTAACAATAC", "CCAACACTAACAATACT", "CCAACACTAACAATACTA", "CCAACACTAACAATACTAA", "AACATAT", "CTCCTC", "CTCCTCT", "CTCCTCTA", "CTCCTCTAC", "CTCCTCTACT", "CTCCTCTACTA", "CTCCTCTACTAC", "CTCCTCTACTACA", "CTCCTCTACTACAA", "CTCCTCTACTACAAC", "CTCCTCTACTACAACA", "CTCCTCTACTACAACAC", "CTCCTCTACTACAACACT", "CTCCTCTACTACAACACTA", "CTCCTCTACTACAACACTAT", "CTCCTCTACTACAACACTATC", "CTCCTCTACTACAACACTATCA", "CTCCTCTACTACAACACTATCAC", "CTCCTCTACTACAACACTATCACT", "CTCCTCTACTACAACACTATCACTC", "CTCCTCTACTACAACACTATCACTCT", "CTCCTCTACTACAACACTATCACTCTC", "CTCCTCTACTACAACACTATCACTCTCC", "CCACACA", "CCACACAT", "AATACCTCTA", "AATACCTCTAA", "AATACCTCTAAC", "AATACCTCTAACC", "AATACCTCTAACCA", "AATACCTCTAACCAC", "AATACCTCTAACCACT", "AATACCTCTAACCACTA", "AATACCTCTAACCACTAC", "AATACCTCTAACCACTACA", "AATACCTCTAACCACTACAC", "AATACCTCTAACCACTACACT", "AATACCTCTAACCACTACACTA", "AATACCTCTAACCACTACACTAA", "AATACCTCTAACCACTACACTAAT", "AATACCTCTAACCACTACACTAATC", "AATACCTCTAACCACTACACTAATCT", "CCACTAT", "CCACTATC", "CCACTATCA", "AATAAT", "AATAATC", "AATAATCA", "AATAATCAT", "CTCACC", "CTCACCA", "CTCACCAA", "CATCAT", "CATCATC", "CATCATCC", "CATCATCCT", "CATCATCCTC", "CATCATCCTCC", "CCACACT", "CCACACTA", "CCACACTAA", "CCACACTAAT", "CCACACTAATA", "CACTACT", "CACTACTC", "CACTACTCA", "CACTACTCAA", "CACTACTCAAC", "CACTACTCAACC", "CACTACTCAACCT", "CACTACTCAACCTC", "CACTACTCAACCTCT", "CACTACTCAACCTCTA", "CACTACTCAACCTCTAC", "CACTACTCAACCTCTACT", "ACTAACCT", "ACTAACCTA", "ACTAACCTAT", "ACCAT", "ACCATA", "ACCATAT", "CCATCC", "CCATCCA", "CCATCCAC", "CCATCCACA", "CCATCCACAA", "AACAACAT", "AACAACATA", "AACAACATAA", "CCTACTCCT", "CCTACTCCTC", "CCTACTCCTCC", "CCATACT", "CCATACTA", "CCATACTAT", "CCATACTATC", "CCATACTATCA", "CCATACTATCAA", "CCATACTATCAAT", "CTCTAA", "AATCCA", "AATCCAC", "AATCCACT", "CATACTA", "CATACTAA", "ACTCTA", "ACTCTAT", "ACTCTATC", "ACTCTATCT", "ACTCTATCTC", "ACTCTATCTCA", "ACTCTATCTCAC", "ACTCTATCTCACA", "ACTCTATCTCACAT", "ACTCTATCTCACATC", "ACTCTATCTCACATCA", "ACTCTATCTCACATCAA", "ACTCTATCTCACATCAAT", "AATCAA", "CTACCA", "CTACCAT", "CTACCATC", "CTACCATCC", "CCTAATC", "CCTAATCA", "CCTAATCAA", "CCTAATCAAC", "CCTAATCAACT", "CCATCT", "ACTAT", "CACTCT", "CCTCCTAA", "CCTCCTAAT", "CCTCCTAATC", "CCTCCTAATCC", "ATCTA", "ATCTAC", "ATCTACA", "ATCTACAA", "ATCTACAAT", "ATAAT", "ATATA", "CCATCAA", "CCATCAAC", "CCATCAACT", "AACCAC", "AACCACC", "AACCACCA", "AACACTACT", "ATCCAAT", "ATCCAATC", "ATCCAATCC", "ATCCAATCCT", "CTACAT", "CTATAA", "CTATAAT", "CTATAATA", "CTATAATAT", "CTATAATATC", "CTATAATATCC", "CTATAATATCCA", "CTATAATATCCAT", "CTCACCAC", "CTCACCACA", "CTCACCACAT", "ACACAC", "CTACCATA", "CTACCATAA", "CTACCATAAT", "CTACCATAATC", "CTACCATAATCA", "CTACCATAATCAT", "CTACCATAATCATC", "CTATCC", "AATATCA", "AATATCAA", "ATCTC", "ATCTCC", "ATCTCCA", "CTATAC", "CTATACT", "CTATACTC", "CTATACTCT", "CTAACT", "CTAACTC", "CTAACTCA", "CTAACTCAT", "CTAACTCATC", "CTAACTCATCC", "ATAC", "ATACT", "CACAT", "CCTCTAT", "ATACA", "ATACAC", "ATACACA", "ATACACAC", "ATACACACT", "ATACACACTC", "ATACACACTCA", "ATACACACTCAA", "ATACACACTCAAC", "ATACACACTCAACC", "ATACACACTCAACCA", "ATACACACTCAACCAA", "AATATA", "AATATAA", "AATATAAC", "AATATAACC", "AATATAACCT", "CCACAACA", "CCACAACAT", "CATATAC", "CATATACA", "CATATACAA", "CATATACAAC", "CATATACAACA", "CATATACAACAT", "AATACCA", "AATACCAT", "AATACCATC", "AATACCATCT", "AATACCATCTC", "AATACCATCTCA", "AATACCATCTCAT", "AATACCATCTCATC", "AATACCATCTCATCC", "AATACCATCTCATCCA", "AATACCATCTCATCCAT", "AATACCATCTCATCCATA", "ATACTA", "ATACTAA", "ATACTAAT", "ATACTAATA", "ATACTAATAA", "ATACTAATAAT", "CATCCA", "CATCCAA", "CCACCAT", "CCACCATA", "CCACCATAC", "CCACCATACC", "CCACCATACCA", "CCACCATACCAC", "CCACCATACCACA", "CCACCATACCACAC", "CCACCATACCACACA", "CCACCATACCACACAT", "CCACCTA", "CCACCTAC", "AACCTAA", "CCTCTAAC", "CCTCTAACT", "CCAATATCAT", "CCAATATCATA", "CCAATATCATAA", "CCAATATCATAAC", "CCAATATCATAACC", "CCAATATCATAACCA", "CCAATATCATAACCAC", "CCAATATCATAACCACT", "CCAATATCATAACCACTA", "CCAATATCATAACCACTAT", "CCTATA", "CCTATAT", "CCTATATA", "CCTATATAC", "CCTATATACC", "CCTATATACCT", "CCTATATACCTC", "CCTATATACCTCT", "CCTATATACCTCTA", "CCTATATACCTCTAT", "CAACAT", "CAACATC", "CAACATCC", "AACTCCT", "AACTCCTA", "AACTCCTAC", "AACTCCTACA", "AACTCCTACAC", "AACTCCTACACT", "ACCAC", "ACCACA", "ACCACAC", "ACCACACA", "ACCACACAC", "ACCACACACT", "ACCACACACTA", "ACCACACACTAA", "ACCACACACTAAT", "ACCACACACTAATA", "ACCACACACTAATAA", "ACCACACACTAATAAT", "ACCACACACTAATAATC", "CTCTCTA", "CACCAT", "CACCATA", "CCATCATC", "CCATCATCC", "CCATCATCCT", "AATCAAT", "AATCAATA", "AATCAATAA", "AATCAATAAT", "AATCAATAATC", "AATCAATAATCC", "AACAATA", "CCACCAA", "CCACCAAT", "ACTCCTA", "ACTCCTAC", "ACTCCTACA", "ACTCCTACAT", "CCACAT", "CCACATC", "CCACATCA", "ACAATC", "AATAACC", "AATAACCA", "AATAACCAT", "AATAACCATC", "AATAACCATCC", "CTAAT", "ACAACTC", "ACAACTCT", "ACAACTCTC", "AACCACA", "CATCAATAT", "ATACAT", "ATACATC", "ATACATCA", "ATACATCAA", "ATACATCAAC", "ATACATCAACA", "ATACATCAACAT", "AATCTCAA", "CACTCCTAT", "CACTCCTATC", "CACTCCTATCC", "ACACACC", "ACAACC", "ACAACCA", "ACAACCAA", "AATAACAA", "AACACTCT", "AATCTAT", "CCAACAAT", "CCAACAATA", "ATCATCATA", "ATCATCATAT", "CATCCTAT", "CCTCACC", "ACTAATC", "ACTAATCA", "ACTAATCAA", "ACTAATCAAC", "ACTAATCAACA", "ACTAATCAACAA", "ACTAATCAACAAT", "ACTAATCAACAATC", "CCTAATCT", "CCTAATCTC", "CCTAATCTCA", "CCTAATCTCAA", "CCTAATCTCAAT", "AATAATA", "CTATCCA", "CTATCCAT", "CTATCCATA", "CTATCCATAA", "CTATCCATAAC", "CTATCCATAACA", "ATCCT", "ATCCTA", "ATCCTAT", "CTCAACA", "CTCAACAA", "CCTACTACC", "CCTACTACCT", "CCTACTACCTC", "CCTACTACCTCA", "CCTACTACCTCAC", "CCTACTACCTCACT", "CCTACTACCTCACTC", "CCTACTACCTCACTCA", "CCTACTACCTCACTCAT", "ACTATC", "ACTATCA", "CTAACAT", "ACACCT", "ACACCTC", "ACACCTCC", "ACACCTCCT", "ACACCTCCTA", "ACACCTCCTAA", "ACACCTCCTAAT", "ACACCTCCTAATA", "ACACCTCCTAATAC", "ACACCTCCTAATACA", "ACACCTCCTAATACAC", "ACACCTCCTAATACACC", "ACACCTCCTAATACACCT", "ACACCTCCTAATACACCTC", "ACACCTCCTAATACACCTCA", "ACACCTCCTAATACACCTCAT", "ACACCTCCTAATACACCTCATC", "CCTCAT", "CCTCATA", "CCTCATAA", "CCTCAAC", "CCTCAACA", "CTATAATC", "CTATAATCC", "CTATAATCCA", "CTATAATCCAA", "ACAACACC", "ACAACACCT", "ACAACACCTA", "ACAACACCTAA", "ACAACACCTAAT", "ACCACT", "CCTATCA", "ATAACAT", "ATAACATC", "ATAACATCA", "ACATCA", "ACATCAT", "ACATCATC", "ACATCATCC", "CCATCAC", "CCATCACA", "CCATCACAC", "CCATCACACA", "CCATCACACAT", "CCATCACACATC", "CCATCACACATCA", "CCATCACACATCAA", "CCATCACACATCAAT", "CCATCACACATCAATC", "CATAAT", "CATAATC", "CATAATCC", "CATAATCCT", "ACCACC", "CCTAATA", "CTCCAA", "AATACT", "AATACTA", "AATACTAT", "ATCAACA", "ATCAACAT", "ATCTACC", "ATCTACCT", "CCAACTAA", "CCACCACT", "AATAATAA", "AACCTCA", "AACCTCAT", "AACCTCATA", "AACCTCATAC", "AACCTCATACT", "ACACTC", "ACACTCC", "ACACTCCT", "ACACTCCTC", "ACACTCCTCA", "ACACTCCTCAC", "ACACTCCTCACT", "ACACTCCTCACTA", "ACACTCCTCACTAA", "ACACTCCTCACTAAC", "ACACTCCTCACTAACA", "ACACTCCTCACTAACAC", "ACACTCCTCACTAACACA", "ACACTCCTCACTAACACAT", "CTACTC", "ACTACC", "ACTACCA", "ACTACCAC", "ACTACCACA", "ACTACCACAA", "ACTACCACAAC", "ACTACCACAACT", "ACTACCACAACTA", "ACTACCACAACTAA", "AACATCTA", "AACATCTAT", "CCATAT", "CCTCCTAT", "AACAACTA", "AACAACTAA", "AACAACTAAC", "AACAACTAACC", "AACAACTAACCT", "AACAACTAACCTA", "AACAACTAACCTAA", "AACACTAT", "AACACTATA", "AACACTATAC", "AACACTATACT", "ACTCAT", "CCTACTAAT", "CCACCTAA", "CCACCTAAT", "CCACCTAATA", "CCACCTAATAT", "CCACCTAATATC", "CCACCTAATATCC", "CCACCTAATATCCT", "CCACCTAATATCCTC", "CCACCTAATATCCTCA", "CCACCTAATATCCTCAC", "CCACCTAATATCCTCACT", "CCACCTAATATCCTCACTC", "CCACCTAATATCCTCACTCC", "CCACCTAATATCCTCACTCCT", "CCACCTAATATCCTCACTCCTA", "CCACCTAATATCCTCACTCCTAT", "AATAATAC", "AATAATACT", "AATAATACTA", "AATAATACTAT", "AATAATACTATC", "AATAATACTATCA", "AATAATACTATCAC", "AATAATACTATCACT", "AATAATACTATCACTA", "AATAATACTATCACTAT", "CAACCAT", "CAACCATA", "CAACCATAA", "CAACCATAAT", "CAACCATAATA", "CCACAAT", "CCACAATC", "CCACAATCA", "CCACAATCAT", "CCACAATCATA", "CCACAATCATAC", "CCACAATCATACT", "ACTATCAA", "AATATAAT", "AATATAATC", "AATATAATCT", "AATCAAC", "AATCAACA", "AATCAACAC", "AATCAACACT", "AATCAACACTA", "AATCAACACTAC", "AATCAACACTACA", "AATCAACACTACAA", "AATCAACACTACAAC", "AATCAACACTACAACC", "AATCAACACTACAACCT", "AATCAACACTACAACCTA", "AATCAACACTACAACCTAT", "AATCAACACTACAACCTATA", "AACCAACCT", "AACCAACCTA", "CAACAAT", "CAACAATA", "ACAACAT", "CCACTAATA", "CCACTAATAC", "CCACTAATACT", "CCACTAATACTC", "CCACTAATACTCA", "CCACTAATACTCAT", "CCACTAATACTCATA", "CCAACACC", "CCAACACCA", "CCAACACCAT", "CCAACACCATC", "CCAACACCATCT", "CCAACACCATCTA", "CCAACACCATCTAT", "CCAACACCATCTATC", "CCAACACCATCTATCT", "CCAACACCATCTATCTA", "CCAACACCATCTATCTAA", "AACCACT", "AACCACTA", "AACCACTAA", "AACCACTAAT", "AACCACTAATC", "AACCACTAATCC", "AACTCTACA", "AACTCTACAT", "AACTCTACATC", "AACTCTACATCA", "AACTCTACATCAC", "AACTCTACATCACC", "AACTCTACATCACCA", "AACTCTACATCACCAT", "CTAACCA", "CTAACCAT", "CTAACCATA", "CTAACCATAC", "CTAACCATACT", "AACTAATC", "AACTAATCT", "AACTAATCTC", "AACTAATCTCC", "AACTAATCTCCT", "CAACCAA", "CCACTC", "CCACTCT", "CACTAAT", "CACTAATC", "CACTAATCT", "CACTAATCTC", "CACTAATCTCT", "CACTAATCTCTA", "CACTAATCTCTAT", "CACTAATCTCTATC", "CACTAATCTCTATCC", "CACTAATCTCTATCCA", "CACTAATCTCTATCCAA", "AACCTAAC", "AACCTAACA", "CTCACT", "CTCACTA", "CTCACTAA", "CACTACC", "ATCCAATA", "ATCCAATAT", "ATCCAT", "ATCCATA", "AATACTAC", "AATAATCATA", "AATAATCATAA", "AATAATCATAAC", "AATAATCATAACT", "AATAATCATAACTA", "ATCTAA", "AATCAC", "AATCACT", "CATACTCC", "CATACTCCT", "CATACTCCTC", "CATACTCCTCT", "CCACATA", "AACACCAT", "CCTAATAA", "CCTAATAAT", "CCTAATAATC", "AACTATAATCC", "AACTATAATCCT", "AACTATAATCCTA", "CCTAACCA", "CCTAACCAA", "CCTAACCAAC", "CCTAACCAACC", "CCTAACCAACCT", "CCAATATCAC", "CCAATATCACC", "CCAATATCACCA", "CCAATATCACCAT", "CCAATATCACCATC", "CCAATATCACCATCA", "CCAATATCACCATCAC", "CCAATATCACCATCACC", "CCTCTACTC", "CCTCTACTCC", "CCTATAC", "CCTATACA", "CCTATACAT", "CCTATACATA", "CCTATACATAC", "CCTATACATACT", "CCTATACATACTA", "CCTATACATACTAA", "CCTATACATACTAAT", "CCTATACATACTAATC", "CCTATACATACTAATCA", "CCTATACATACTAATCAC", "CCTATACATACTAATCACA", "CCTATACATACTAATCACAA", "CCTATACATACTAATCACAAC", "CCTATACATACTAATCACAACA", "CCTATACATACTAATCACAACAC", "CCTATACATACTAATCACAACACA", "CCTATACATACTAATCACAACACAA", "CCTATACATACTAATCACAACACAAC", "AATACA", "AATACAC", "AATACACA", "AATACACAC", "AATACACACA", "AATACACACAC", "AATACACACACC", "AATACACACACCA", "AATACACACACCAT", "AATACACACACCATA", "AATACACACACCATAT", "AATACACACACCATATC", "AATACACACACCATATCA", "AATACACACACCATATCAA", "AATACACACACCATATCAAC", "AATACACACACCATATCAACA", "CACTCAT", "CCTCCACAT", "CCTACTACT", "CCTACTACTC", "CCTACTACTCC", "CCTACTACTCCT", "CCTACTACTCCTA", "CCTACTACTCCTAT", "CCTACTACTCCTATC", "CCTACTACTCCTATCA", "CCTACTACTCCTATCAC", "CCTACTACTCCTATCACT", "CAACTCC", "CAACTCCA", "CAACTCCAA", "CCTCATAC", "CCTCATACT", "CCTCATACTA", "CCAATCA", "CCAATCAT", "CCAATCATA", "CCAATCATAT", "AACACTATC", "AACACTATCT", "AACACTATCTC", "AACACTATCTCA", "AACACTATCTCAT", "AACACTATCTCATA", "AACACTATCTCATAT", "CCACTATA", "CCACTATAA", "CCACTATAAT", "CCACTATAATA", "CCACTATAATAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACA", "AATAACCATACAA", "AACTATC", "AACTATCC", "ATCTAT", "CTAACAACC", "CTAACAACCT", "ATATC", "CTCAAT", "CACAAT", "CACAATA", "CTAATC", "CTAATCC", "AACAATCC", "AACAATCCA", "CCATCACC", "CCATCACCA", "CCATCACCAC", "CCATCACCACA", "CCATCACCACAC", "CCATCACCACACT", "CCATCACCACACTA", "CCATCACCACACTAT", "CAATCT", "ATATCA", "ATATCAA", "ATAATC", "CTATACTA", "CTATACTAT", "ATATCC", "CAATA", "CAATAC", "CAATACC", "ACCTCAT", "ACCTCATC", "ACCTCATCA", "ACCTCATCAT", "ACCTCATCATC", "CCAACACAT", "CCAACACATC", "CCTACTAATA", "CCTACTAATAA", "CCTACTAATAAC", "CCTACTAATAACT", "CCTACTAATAACTC", "CCTACTAATAACTCT", "CCTACTAATAACTCTC", "CCTACTAATAACTCTCA", "CCTACTAATAACTCTCAT", "CCACATCC", "AATCATC", "AATCATCT", "ACCTCAAT", "ACCTCAATC", "ACCTCAATCA", "ACCTCAATCAA", "ACCTCAATCAAC", "AATAACCATACC", "AATAACCATACCT", "AATAACCATACCTA", "AATAACCATACCTAC", "AATAACCATACCTACT", "ATAACC", "CCATAAC", "CCATAACT", "ATACTAC", "ATACTACC", "CTAACCTAT", "CTAACCTATC", "CTAACCTATCA", "CTAACCTATCAA", "CTAACCTATCAAT", "CTAACCTATCAATA", "CTAACCTATCAATAA", "CATCAC", "CATCACT", "ACTCAAT", "ACTCAATC", "ACTCAATCT", "AACATCC", "AACATCCT", "AATCTATC", "AATCTATCT", "AATCTATCTC", "AATCTATCTCC", "ATCCTAA", "ATCCTAAT", "CATACTAC", "CATACTACT", "ATCAACC", "ATCAACCA", "CCATAACTA", "CCATAACTAT", "CCATAACTATA", "CCATAACTATAC", "CCATAACTATACA", "CCATAACTATACAA", "CCATAACTATACAAT", "CCTATAA", "CCTATAAT", "AATCTCC", "AATCTCCA", "AACATATA", "AACATATAA", "AACCAACAT", "AACCAACATA", "AACCAACATAA", "AACCAACATAAC", "AACCAACATAACC", "AACCAACATAACCT", "AACCAACATAACCTC", "AACCAACATAACCTCC", "AACCAACATAACCTCCA", "AACCAACATAACCTCCAT", "AACCAACATAACCTCCATC", "AACCAACATAACCTCCATCA", "AACCAACATAACCTCCATCAA", "AACCAACATAACCTCCATCAAC", "AACCAACATAACCTCCATCAACA", "AACCAACATAACCTCCATCAACAA", "AACCAACATAACCTCCATCAACAAC", "AACCAACATAACCTCCATCAACAACC", "CCATAACA", "CCATACCT", "CCATACCTC", "CCATACCTCA", "AACAACCAACAT", "AACACCATC", "AACACCATCA", "AACACCATCAA", "AACACCATCAAC", "AACACCATCAACC", "AATAACAC", "AATAACACT", "AATAACACTC", "AATAACACTCA", "AATAACACTCAA", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "AATCTAACC", "AATCTAACCA", "AATCTAACCAC", "ACCAAT", "AACACCAAT", "AATCATCA", "AATCATCAA", "AATCATCAAT", "AATCATCAATC", "AATCATCAATCA", "AATCATCAATCAC", "AATCATCAATCACT", "AATCATCAATCACTC", "AATCATCAATCACTCT", "CCTAATCC", "CCTAATCCT", "CCTAATCCTC", "CCTAATCCTCC", "CCTAATCCTCCA", "CCTAATCCTCCAA", "CCATACA", "CCATACAC", "CCATACACT", "CCATACACTA", "CCATACACTAC", "CCATACACTACA", "CCATACACTACAC", "CCATACACTACACA", "CCATACACTACACAT", "CCATACACTACACATC", "CCATACACTACACATCA", "CTACCTCCAT", "AATCATCC", "AATCATCCT", "AATCATCCTA", "AATCATCCTAC", "AATCATCCTACT", "AATATCC", "AATATCCT", "AACCTCCTAT", "AACCTCCTATC", "AACCTCCTATCA", "CCTACAT", "CCTACATC", "ATCTAAT", "ATCTAATA", "ATCTAATAA", "ATCTAATAAC", "ATCTAATAACC", "CCTCATAT", "CCATCCT", "CCATCCTA", "CCATCCTAC", "CCTAATCCTCT", "CCTAATCCTCTC", "CCTAATCCTCTCC", "CCTAATCCTCTCCA", "CCTAATCCTCTCCAT", "CCTAATCCTCTCCATC", "CCTAATCCTCTCCATCC", "CCTAATCCTCTCCATCCT", "CACTCATC", "CACTCATCC", "CCTCCACATA", "CCTCCACATAT", "CCTCCACATATC", "CCTCCACATATCA", "CCTCCACATATCAA", "CATAATA", "CATAATAT", "ACACTAACAT", "CCTCAATC", "CCTCAATCC", "CCTCAATCCT", "CCTCAATCCTC", "CCTCAATCCTCT", "CCTCAATCCTCTA", "CCTCAATCCTCTAC", "CCTCAATCCTCTACT", "CACCATC", "AACAATCT", "CTCCACCAT", "CTCCACCATC", "CTCCACCATCA", "CTCCACCATCAA", "CTCCACCATCAAC", "CTCCACCATCAACA", "CTCCACCATCAACAC", "CTCCACCATCAACACC", "ACAACAAT", "CCACATAA", "CCACATAAC", "CCACATAACA", "CCACATAACAC", "CCACATAACACC", "CCACATAACACCA", "CCACATAACACCAT", "CCACATAACACCATA", "CCACATAACACCATAC", "CCACATAACACCATACC", "CAATAT", "CAATATC", "CAATATCT", "CATACC", "CCATCCAA", "CATATC", "CATATCA", "CATATCAC", "CATATCACA", "CCAATCC", "CCAATCCT", "CCAATCCTC", "AACTATAC", "AACTATACC", "AACTATACCT", "ACATCT", "ACATCTC", "CTCACACAT", "CTCACACATA", "CTCACACATAA", "CTCACACATAAC", "CTCACACATAACT", "AACTCCACAT", "AACTCCACATA", "AACTCCACATAT", "CACAATAC", "CACAATACT", "CCAACCAT", "CCAACCATA", "CCAACCATAA", "CCAACCATAAT", "CCAACCATAATA", "CCAACCATAATAA", "CCAACCATAATAAC", "CCAACCATAATAACT", "CCAACCATAATAACTC", "CCAACCATAATAACTCA", "CCAACCATAATAACTCAA", "CCAACCATAATAACTCAAC", "CCAACCATAATAACTCAACA", "CCAACCATAATAACTCAACAC", "CCAACCATAATAACTCAACACA", "CCAACCATAATAACTCAACACAC", "CCAACCATAATAACTCAACACACC", "CCAACCATAATAACTCAACACACCT", "CCAACCATAATAACTCAACACACCTA", "CCAACCATAATAACTCAACACACCTAA", "CCAACCATAATAACTCAACACACCTAAC", "CCAACCATAATAACTCAACACACCTAACA", "CCAACCATAATAACTCAACACACCTAACAA", "CCAACCATAATAACTCAACACACCTAACAAT", "CCAACCATAATAACTCAACACACCTAACAATC", "CCAACCATAATAACTCAACACACCTAACAATCT", "AACACAT", "AACACATA", "CTCAT", "CTCATA", "CTCATAA", "CTCATAAC", "CTCATAACA", "CTCATAACAC", "CTCATAACACC", "CTCATAACACCT", "ACTAATAC", "ACTAATACC", "ACTAATACCA", "ACTAATACCAA", "CCACCATC", "CCACCATCC", "AATATCCA", "AATATCCAA", "AATATCCAAC", "AATATCCAACA", "AATATCCAACAT", "AATATCCAACATA", "AATATCCAACATAA", "AATATCAC", "AATATCACA", "AATATCACAA", "AATCATAA", "AATCATAAC", "AATCATAACA", "AATCATAACAT", "AATCATAACATA", "AATCATAACATAA", "AATCATAACATAAC", "AATCATAACATAACA", "AATCATAACATAACAT", "AATCATAACATAACATA", "AATCATAACATAACATAA", "AACAATAC", "AACAATACC", "AACAATACCT", "AACAATACCTA", "AACAATACCTAA", "AACAATACCTAAT", "CTACCTAT", "AACTACTCAAT", "CCATCAAT", "CCAACAATC", "CCAACAATCA", "CCAACAATCAA", "CCAACAATCAAC", "CCAACAATCAACC", "AACTAACTCCTAAT", "AACTAACTCCTAATC", "AACTAACTCCTAATCT", "AACTAACTCCTAATCTC", "AACTAACTCCTAATCTCA", "AACTAACTCCTAATCTCAT", "AACTAACTCCTAATCTCATA", "AACTAACTCCTAATCTCATAC", "AACTAACTCCTAATCTCATACT", "CAATAA", "CAATAAT", "AACTAATCA", "AACTAATCAT", "AACTAATCATC", "AACTAATCATCT", "AACTAATCATCTA", "AACTAATCATCTAT", "CATCTA", "CATCTAT", "CATCTATA", "CATCTATAA", "AACATCAC", "AACATCACC", "AACATCACCT", "AACATCACCTC", "AACATCACCTCT", "AACATCACCTCTA", "CATAATCA", "CATAATCAC", "CATAATCACT", "AATATAT", "AATATATA", "AATATATAA", "AATATATAAT", "AATATATAATC", "AATATATAATCA", "AATATATAATCAC", "AATATATAATCACT"};

System.out.println("========= X79547 ==========\t" + longSequence_5_2.length + "\tS5\t" + s5.length());

System.out.println("70000-Longest Seq");

for(int i = 1; i <= 5; i++)
{
	if(i==1)
	{
		System.out.println("S1 S2 VALUE =\t" + av.findValue(s1, s2, longSequence_1_2,  longSequence_2_2, s1.length(), s2.length()));
		System.out.println("S1 S3 VALUE =\t" + av.findValue(s1, s3, longSequence_1_2,  longSequence_3_2, s1.length(), s3.length()));
		System.out.println("S1 S4 VALUE =\t" + av.findValue(s1, s4, longSequence_1_2,  longSequence_4_2, s1.length(), s4.length()));
		System.out.println("S1 S5 VALUE =\t" + av.findValue(s1, s5, longSequence_1_2,  longSequence_5_2, s1.length(), s5.length()));
	}
	else if(i==2)
	{
		System.out.println("S2 S1 VALUE =\t" + av.findValue(s2, s1, longSequence_2_2,  longSequence_1_2, s2.length(), s1.length()));
		System.out.println("S2 S3 VALUE =\t" + av.findValue(s2, s3, longSequence_2_2,  longSequence_3_2, s2.length(), s3.length()));
		System.out.println("S2 S4 VALUE =\t" + av.findValue(s2, s4, longSequence_2_2,  longSequence_4_2, s2.length(), s4.length()));
		System.out.println("S2 S5 VALUE =\t" + av.findValue(s2, s5, longSequence_2_2,  longSequence_5_2, s2.length(), s5.length()));
	}
	else if(i==3)
	{
		System.out.println("S3 S1 VALUE =\t" + av.findValue(s3, s1, longSequence_3_2,  longSequence_1_2, s3.length(), s1.length()));
		System.out.println("S3 S2 VALUE =\t" + av.findValue(s3, s2, longSequence_3_2,  longSequence_2_2, s3.length(), s2.length()));
		System.out.println("S3 S4 VALUE =\t" + av.findValue(s3, s4, longSequence_3_2,  longSequence_4_2, s3.length(), s4.length()));
		System.out.println("S3 S5 VALUE =\t" + av.findValue(s3, s5, longSequence_3_2,  longSequence_5_2, s3.length(), s5.length()));
	}
	else if(i==4)
	{
		System.out.println("S4 S1 VALUE =\t" + av.findValue(s4, s1, longSequence_4_2,  longSequence_1_2, s4.length(), s1.length()));
		System.out.println("S4 S2 VALUE =\t" + av.findValue(s4, s2, longSequence_4_2,  longSequence_2_2, s4.length(), s2.length()));
		System.out.println("S4 S3 VALUE =\t" + av.findValue(s4, s3, longSequence_4_2,  longSequence_3_2, s4.length(), s3.length()));
		System.out.println("S4 S5 VALUE =\t" + av.findValue(s4, s5, longSequence_4_2,  longSequence_5_2, s4.length(), s5.length()));
	}
	else if(i==5)
	{
		System.out.println("S5 S1 VALUE =\t" + av.findValue(s5, s1, longSequence_5_2,  longSequence_1_2, s5.length(), s1.length()));
		System.out.println("S5 S2 VALUE =\t" + av.findValue(s5, s2, longSequence_5_2,  longSequence_2_2, s5.length(), s2.length()));
		System.out.println("S5 S3 VALUE =\t" + av.findValue(s5, s3, longSequence_5_2,  longSequence_3_2, s5.length(), s3.length()));
		System.out.println("S5 S4 VALUE =\t" + av.findValue(s5, s4, longSequence_5_2,  longSequence_4_2, s5.length(), s4.length()));
	}
}*/

//================ 80000 ===============================================================
/*
String[] longSequence_1 = {"AC", "TA", "AA", "AT", "CA", "CC",
		"TAA", "CCA", "CCAA", "CAA", "AAC", "AACC", "CAC", "CACA", "CACAA", "TAAC", "TAACC",
"TAC", "TAACA", "TAACAA", "CACAC", "CACACA", "CACACAC", "CACACACC", "CACC", "AACCA", "AACCAA", "AACCAAC", "AACCAACA",
"AACCAACAC", "AACCAACACA", "AACCAACACAA", "AACA", "TACA", "AACAA", "TACAC", "TACACC", "CACACC", "AACAAC", "AAT", 
"TACC", "AATA", "CAT", "CATA", "CATAA", "CATAAT", "CAAC", "TAAT", "AATAT", "AATATA", "AATATAA", "AATATAAT", "AACAT",
"AACATA", "AACATAA", "AATAA", "CCAT", "CAAT", "CAACA", "CAACAA", "CAACAAT", "CAACAT", "CAACATA", "CAACATAA", 
"CAACATAAT", "CAACATAATA", "CAACATAATAC", "CAACATAATACC", "CAACATAATACCA", "CAACATAATACCAA", "TAT", "TATA", "AACAAT",
"TATAA", "TATAAC", "TATAACC", "CCAC", "TAACAAC", "TAACAACA", "TAACAACAA", "TAATA", "TAATAC", "TAATACA", "TAATACAC", 
"TAATACACC", "CCAAC", "CCAACA", "CCAACAC", "CCAACACA", "AACAC", "AACACA", "AACACAA", "TACCA", "TACCAA", "CCACA", 
"CCACAC", "CCAAT", "AATAAT", "AATAATA", "AATAATAA", "CAACC", "CAACCA", "CAACCAA", "CAACCAAC", "CAACCAACA", "AACAACC", 
"CCACAT", "CCATA", "CCATAT", "TAACCA", "CCACC", "TATAAT", "TAATAT", "CCAACAT", "CCATAC", "CCATACC", "CCATACCA", 
"CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC", "CAATA", "CAATAT", "AATAC", "CCATAA", "CCATAAT", "CCATAATA",
"CCATAATAT", "AACCAACC", "CCATATA", "CCATATAT", "CCATATATA", "CCATATATAC", "CCATATATACC", "TATAT", "AATACA", "AATACAC",
"AATACACC", "AATACACCA", "AATACACCAT", "AATACACCATA", "AATACACCATAT", "AACATAC", "AACCAT", "AACCATA", "AACCATAC", 
"AACCATACC", "CCAACC", "TAACCAT", "CACCA", "CACCAC", "TACCAT", "TACCATA", "TACAA", "TACAAT", "TACAATA", "TACAATAA",
"TACAATAAC", "TACAATAACC", "CACAT", "CACAAC", "AACAACA", "AACAACAT", "AACAACATA", "AACAACATAA", "CCATAAC", "CCATAACC",
"CCAATA", "CCAATAA", "CCAATAAT", "CCAATAATA", "CCAATAATAT", "TACAT", "TAATAA", "TAATAAT", "AATAAC", "AATAACA", "AATAACAT",
"AATAACATA", "AATAACATAA", "CAATAA", "CAATAAT", "CCACCA", "CCACCAT", "CCACCATA", "CCACCATAT", "CCACCATATA", "CCACCATATAT",
"CATAC", "CCACACA", "CCACACAA", "TACACA", "AATAACAA", "AATAACAAT", "AACCAC", "AACCACA", "CCAACAA", "AACACAC", "CCACCAA",
"AATACAT", "AATACATA", "AATACATAC", "TAATAATA", "TAATAATAA", "TAATAATAAC", "TAATAATAACA", "TAACAAT", "CCACCATAA",
"CCACCATAAT", "CCACCATAATA", "CCACCATAATAC", "AACAATA", "AACAATAT", "AACAATATA", "AATACC", "AATATAC", "AATATACC",
"CCACAA", "CCACAAT", "CCACAATA", "CCACAATAT", "AACCACC", "AACCACCA", "AACCACCAT", "CCACAAC", "CCACAACA", "CCACAACAA",
"CCACAACAAT", "CCACAACAATA", "CCACAACAATAT", "AACCATAA", "AACACAT", "CCACCAC", "CCACCACA", "CCACCACAA", "CCACCACAAC",
"CCACCACAACA", "CCACCACAACAA", "CCACCACAACAAC", "CCACCACAACAACC", "AATATACCA", "AATATACCAC", "AACAACCA", "AACAACCAC",
"AACAACCACC", "TATAC", "TATACC", "TATACCA", "TATACCAA", "TATACCAAC", "AATAACC", "AATAACCA", "AATAACCAT", "AATACAA",
"AATACAAC", "AATACAACA", "AATACAACAT", "AATATAAC", "AATATAACA", "AATATAACAA", "CCATAACA", "CCATAACAA", "AACAACAA",
"AATAATAC", "AACACAAT", "AACACAATA", "AACACAATAT", "AACACAATATA", "AACACAATATAT", "CCACACC", "CCACACCA", "CCACACCAT",
"CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACCACC", "CCACCACCA", "CCATAACAT", "CCAATAAC", "CCAATAACC", "AACATACA"};

System.out.println("========= X63726 ==========\t" + longSequence_1.length + "\tS1\t" + s1.length());


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

System.out.println("========= X72004 ==========\t" + longSequence_2.length+ "\tS2\t" + s2.length());
		
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

System.out.println("========= U20753 ==========\t" + longSequence_3.length + "\tS3\t" + s3.length());

String[] longSequence_4 = {"AC", "TA", "AA", "AT", "CA", "CC",
		"ACC", "AAT", "AAC", "AACC", "TAC", "TACC", "TAT", "TAA", "TAAT", "TAATA", "TAATAA",
		"TAATAAT", "TAAC", "TAACC", "ATA", "ATAC", "AATA", "AATAT", "AATATA", "AATATAC", "ATAA", "ATAAT", "AATAA",
		"AATAAC", "AATAC", "ATAT", "AATAAT", "AATAATA", "AATAATAT", "ATAATA", "ATAATAA", "ATAATAAC", "AACA",
		"AACAT", "CCA", "CCAA", "CCAAC", "ACA", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "ACAACACC", "CCAC", "CCACC",
		"CCACCA", "CCACCAA", "CAT", "CATA", "CATAT", "CAC", "CACA", "CACAT", "CATAA", "CATAAT", "CCAACA",
		"CCAACAC", "CCAACACC", "CCACA", "CCACCAC", "CCACCACA", "CAA", "CAAT", "AACATA", "AACATAT", "AACATATA",
		"AACATATAC", "CCAAT", "CCAATA", "AACAA", "AACAAC", "AACCA", "AACCAA", "CCAT", "AACAACA", "AACAACAT",
		"AACAACATA", "AACAACATAA", "AACAACATAAT", "AATACC", "AACAC", "AACACC", "AACACCA", "AACACCAT", "CCAACAA",
		"CCAACAAT", "AACCAT", "CCATA", "CCATAT", "CCACAA", "CCACAAT", "CCACAATA", "CCACAATAA", "CCACAATAAT",
		"CCACAATAATA", "CCACAATAATAT", "CCACAC", "CCACACC", "CCACACCA", "AATAACA", "AATAACAA", "AACAAT",
		"AACAATA", "AACAATAC", "AACAATACC", "CCAACC", "AATAACC", "CCAACAAC", "CCAACAACC", "CCAACAACCA",
		"CCAACAACCAC", "CCAACAACCACC", "AATAACCA", "AATAACCAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACC",
		"AACAACC", "AACAACCA", "AACAACCAA", "CCATAA", "CCATAAC", "AACCAC", "AACCACC", "AACACA", "AACACAA",
		"AACACAAC", "AACACAACC", "AACATATAT", "AACATATATA", "AACATATATAA", "AACCAAC", "AACCAACA", "AACCAACAT",
		"AACCAACAA", "AATACA", "AATACAA", "AATACAAT", "AATACAATA", "AATACAATAC", "AACCACA", "AACCACAA",
		"AACACAT", "AACACATA", "AACCACAAC", "AACCACAACA", "AACCACAACAC", "AACCACAACACA", "AACCACAACACAA",
		"AACCACAACACAAC", "AACCACAACACAACA", "AACCACAACACAACAT", "AACCACAACACAACATA", "AACCACAACACAACATAT",
		"AACACAAT", "CCAACACA", "AACATAA", "AACCAAT", "AACCAATA", "AACCAACC", "CCATAC", "CCATACC", "CCACAT",
		"CCACATA", "CCACACA", "CCACACAT", "AATAATATA", "AATAATATAA", "AACCACCA", "AACACCAC", "AACACCACC",
		"CCACCACAT", "CCACCACATA", "CCACCACATAT", "CCACAAC", "CCACAACA", "CCACAACAT", "AATACCA", "AATACCAT",
		"AACCATA", "CCAACAATA", "CCAACAATAA", "CCAACAATAAT", "CCAACAATAATA", "CCATAAT", "CCATAATA", "CCATAATAA",
		"CCATAATAAC", "CCATAATAACA", "CCATAATAACAA", "CCAATAC"};

System.out.println("========= X97336 ==========\t" + longSequence_4.length + "\tS4\t" + s4.length());

String[] longSequence_5 = {"AC", "TA", "AA", "AT", "CA", "CC", "CT",
		"CAA", "AAC", "AACA", "ACA", "ACAC", "CAAC", "CAACA", "CAACAC", "CAACACT", "ACT", "AACT",
		"CAC", "CACT", "ACAA", "ACAAC", "ACAACA", "ACAACAC", "CACA", "CACAC", "CACACA", "CACAA", "CAACT", "ACACT",
		"AACAC", "AACACA", "CACAAC", "CACAACT", "AACAA", "AACAAC", "AACAACT", "AACACT", "CACACT", "TAC", "TACT",
		"TACA", "ACTA", "TAA", "CAACACTA", "CAACACTAC", "CAACACTACA", "CAACACTACAA", "CAACACTACAAC", "CTA", "CTAA",
		"CAACAA", "CACTA", "CACTAA", "ACTAC", "ACTACT", "CTAC", "CTACA", "CTAAC", "AACTA", "AACTAA", "CAACACTAA",
		"CTACT", "CTACAC", "CTACACA", "CTACACAC", "CTACACACT", "CTACACACTA", "CTACACACTAA", "CTAACA", "CACTAC",
		"CTACTA", "CTACTAA", "TACTA", "TACTAC", "TACAA", "TACAAC", "TACAACA", "TACAACAA", "TACTAA", "TAAC",
		"TAACT", "TAACTA", "TACAC", "TACACA", "TACACAC", "TACACACA", "TACACACAC", "CTACTAC", "CTACTACT", "TAACA",
		"TAACAA", "AACACTA", "CTACAA", "CTAACAA", "CTAACAAC", "CAACACC", "CCT", "TAACC", "CCA", "ACC", "ACCT",
		"CCAA", "CCAAC", "CCAACC", "CCAACCT", "TACC", "TACCA", "TACCAA", "TACCAAC", "TACCAACC", "TACCAACCT", 
		"CCTA", "CCTAC", "CCTACA", "CCTACAC", "CCTACACC", "CCTACACCA", "CCTACT", "AACAACA", "AACAACAA", "CCAACT",
		"CCAACTA", "TAACCA", "CCTACC", "CCTACCT", "CCTACCTA", "CCTAA", "AACC", "CCAACA", "CCAACAC", "CCAACACA",
		"TACACC", "TACACCA", "TACACCAA", "TACACCAAC", "CCTACTA", "CCTACTAA", "CCAC", "CCACA", "CAACC", "CCACAA",
		"CCACAAC", "CCACAACC", "TACCT", "TACCTA", "TACCTAC", "TACCTACT", "TACACT", "TACACTA", "TACTACC", "CCAACAA",
		"CCACC", "CTAACC", "CTAACCT", "CTAACCTA", "AACCT", "TACTACT", "TAACCT", "CCACAC", "CCACACC", "CCACACCA",
		"CCACACCAC", "CCACACCACT", "CCACACCACTA", "CCACACCACTAA", "AACACTAC", "AACACTACC", "CCACT", "AACCA",
		"AACCAA", "AACCAAC", "AACCAACA", "ACACC", "TAACCAA", "AACAACC", "AACAACCA", "AACAACCAA", "AACAACCAAC",
		"AACAACCAACA", "AACACC", "AACACCA", "TACCAC", "TACCACA", "TACCACAA", "TACCACAAC", "TACCACAACC",
		"TACCACAACCT", "TACCACAACCTA", "TACCACAACCTAC", "TACCACAACCTACC", "AACTAAC", "AACTAACA", "AACTAACAA",
		"AACTAACAAC", "AACTAACAACA", "AACTAACAACAA", "TAACAC", "TAACACT", "AACACAA", "ACCA", "ACCAA", "AACACCAA",
		"CCACTA", "CCACTAA", "ACCTA", "ACCTAC", "ACCTACC", "ACCTACCA", "TACACTAC", "TACACTACA", "TACACTACAC", 
		"TACACTACACA", "ACAACT", "TAACTAC", "CTACC", "CTACCT", "ACAACTA", "ACAACTAC", "ACAACTACA", "ACAACTACAC",
		"ACAACTACACC", "ACACTA", "ACACTAA", "ACACTAAC", "ACACTAACA", "TACCACT", "CACC", "CACCA", "ACAACAA",
		"TAACACC", "TAACACCA", "CCACCT", "CCAACTAC", "ACTAA", "CAACCA", "CACCT", "CACCTA", "CACCTAC", "CACCTACC",
		"CCAACCA", "TACCAACA", "CACACTA", "TAACACCT", "ACACA", "CTACAAC", "CTACAACC", "CTACAACCA", "CTACAACCAA",
		"ACTACA", "ACTACAC", "CCACCA", "CACACAC", "CACACACC", "TACCTAA", "CTACCTA", "AACCAACT", "AACTAC", 
		"AACTACT", "AACTAACT", "AACAACAC", "AACAACACA", "AACAACACAC", "AACAACACACA", "AACAACACACAA", "AACAACCT",
		"ACTAAC", "ACTAACC", "ACTAACCA", "TAAT", "AACCAT", "ATA", "ATAA", "ATAAC", "ATAACA", "CAAT", "ACAAT",
		"ACAT", "CTAT", "AACTAAT", "AACTAATA", "ACATA", "ACATAA", "ACATAAT", "AAT", "CAT", "CATA", "CATAA", 
		"CCTAAC", "CCTAACA", "CCTAACAA", "CCTAACAAC", "CCTAACAACA", "CCTAACAACAT", "CCAAT", "CTATA", "CTATAT", 
		"CCAACAT", "CCTAT", "CCTACTAC", "CCTACTACA", "CCTACTACAA", "CCTACTACAAC", "CCTACTACAACC", "CCTACTACAACCT",
		"CCTACTACAACCTA", "CCTACTACAACCTAT", "AACTAT", "CCACTAC", "CCACTACA", "CCACTACAA", "CCACTACAAC",
		"CCACTACAACC", "CCACTACAACCA", "CCACTACAACCAC", "CCACTACAACCACT", "CCACTACAACCACTA", "CCACTACAACCACTAA",
		"CCACTACAACCACTAAC", "CCACTACAACCACTAACA", "CCACTACAACCACTAACAT", "CACCAA", "CACCAAT", "CCTAACC", "CCAT",
		"CCATA", "CCATAC", "CCATACC", "CCATACCA", "CCATACCAT", "CCATACCATA", "CCATACCATAC", "CCATACCATACC",
		"CCACTAAT", "AACCTA", "AATA", "AATAC", "CCTAAT", "AATACC", "AATACCT", "CCATAA", "CCATAAT", "AACCAACC",
		"ACCTAA", "ACCTAAC", "ACCTAACA", "AACAT", "ATAT", "ACCAAC", "ACCAACT", "CATAT", "CCACCAC", "CCACCACA",
		"CCACCACAA", "AACATA", "AATAT", "AACTACA", "AATAA", "AACTATA", "AACTATAA", "AACTATAAT", "ACTAAT", "ACTAATA",
		"ACTAATAA", "ACTAATAAT", "AACCATA", "AACAAT", "CCAATA", "CCAATAT", "CATAC", "CATACT", "CATATA", "AATAAC",
		"AATAACA", "CCAACACT", "CCAACACTA", "CCAACACTAA", "CCAACACTAAC", "CCAACACTAACA", "CCAACACTAACAA",
		"CCAACACTAACAAT", "CCAACACTAACAATA", "CCAACACTAACAATAC", "CCAACACTAACAATACT", "CCAACACTAACAATACTA",
		"CCAACACTAACAATACTAA", "AACATAT", "CCACACA", "CCACACAT", "CCACTAT", "AATAAT", "CCACACT", "CCACACTA",
		"CCACACTAA", "CCACACTAAT", "CCACACTAATA", "CACTACT", "ACTAACCT", "ACTAACCTA", "ACTAACCTAT", "ACCAT",
		"ACCATA", "ACCATAT", "AACAACAT", "AACAACATA", "AACAACATAA", "CCATACT", "CCATACTA", "CCATACTAT", "CATACTA",
		"CATACTAA", "CTACCA", "CTACCAT", "ACTAT", "ATAAT", "ATATA", "AACCAC", "AACCACC", "AACCACCA", "AACACTACT",
		"CTACAT", "CTATAA", "CTATAAT", "CTATAATA", "CTATAATAT", "ACACAC", "CTACCATA", "CTACCATAA", "CTACCATAAT",
		"CTATAC", "CTATACT", "CTAACT", "ATAC", "ATACT", "CACAT", "ATACA", "ATACAC", "ATACACA", "ATACACAC",
		"ATACACACT", "AATATA", "AATATAA", "AATATAAC", "AATATAACC", "AATATAACCT", "CCACAACA", "CCACAACAT",
		"CATATAC", "CATATACA", "CATATACAA", "CATATACAAC", "CATATACAACA", "CATATACAACAT", "AATACCA", "AATACCAT",
		"ATACTA", "ATACTAA", "ATACTAAT", "ATACTAATA", "ATACTAATAA", "ATACTAATAAT", "CCACCAT", "CCACCATA",
		"CCACCATAC", "CCACCATACC", "CCACCATACCA", "CCACCATACCAC", "CCACCATACCACA", "CCACCATACCACAC", 
		"CCACCATACCACACA", "CCACCATACCACACAT", "CCACCTA", "CCACCTAC", "AACCTAA", "CCTATA", "CCTATAT", "CCTATATA",
		"CCTATATAC", "CCTATATACC", "CCTATATACCT", "CAACAT", "ACCAC", "ACCACA", "ACCACAC", "ACCACACA",
		"ACCACACAC", "ACCACACACT", "ACCACACACTA", "ACCACACACTAA", "ACCACACACTAAT", "ACCACACACTAATA",
		"ACCACACACTAATAA", "ACCACACACTAATAAT", "CACCAT", "CACCATA", "AACAATA", "CCACCAA", "CCACCAAT", "CCACAT",
		"AATAACC", "AATAACCA", "AATAACCAT", "CTAAT", "AACCACA", "ATACAT", "ACACACC", "ACAACC", "ACAACCA",
		"ACAACCAA", "AATAACAA", "CCAACAAT", "CCAACAATA", "AATAATA", "CCTACTACC", "CCTACTACCT", "CTAACAT", 
		"ACACCT", "ACAACACC", "ACAACACCT", "ACAACACCTA", "ACAACACCTAA", "ACAACACCTAAT", "ACCACT", "ATAACAT", 
		"CATAAT", "ACCACC", "CCTAATA", "AATACT", "AATACTA", "AATACTAT", "CCAACTAA", "CCACCACT", "AATAATAA", 
		"ACTACC", "ACTACCA", "ACTACCAC", "ACTACCACA", "ACTACCACAA", "ACTACCACAAC", "ACTACCACAACT", "ACTACCACAACTA",
		"ACTACCACAACTAA", "CCATAT", "AACAACTA", "AACAACTAA", "AACAACTAAC", "AACAACTAACC", "AACAACTAACCT",
		"AACAACTAACCTA", "AACAACTAACCTAA", "AACACTAT", "AACACTATA", "AACACTATAC", "AACACTATACT", "CCTACTAAT",
		"CCACCTAA", "CCACCTAAT", "CCACCTAATA", "CCACCTAATAT", "AATAATAC", "AATAATACT", "AATAATACTA", "AATAATACTAT",
		"CAACCAT", "CAACCATA", "CAACCATAA", "CAACCATAAT", "CAACCATAATA", "CCACAAT", "AATATAAT", "AACCAACCT", 
		"AACCAACCTA", "CAACAAT", "CAACAATA", "ACAACAT", "CCACTAATA", "CCACTAATAC", "CCACTAATACT", "CCAACACC",
		"CCAACACCA", "CCAACACCAT", "AACCACT", "AACCACTA", "AACCACTAA", "AACCACTAAT", "CTAACCA", "CTAACCAT",
		"CTAACCATA", "CTAACCATAC", "CTAACCATACT", "CAACCAA", "CACTAAT", "AACCTAAC", "AACCTAACA", "CACTACC",
		"AATACTAC", "CCACATA", "AACACCAT", "CCTAATAA", "CCTAATAAT", "CCTAACCA", "CCTAACCAA", "CCTAACCAAC",
		"CCTAACCAACC", "CCTAACCAACCT", "CCTATAC", "CCTATACA", "CCTATACAT", "CCTATACATA", "CCTATACATAC", 
		"CCTATACATACT", "CCTATACATACTA", "CCTATACATACTAA", "CCTATACATACTAAT", "AATACA", "AATACAC", "AATACACA",
		"AATACACAC", "AATACACACA", "AATACACACAC", "AATACACACACC", "AATACACACACCA", "AATACACACACCAT",
		"AATACACACACCATA", "AATACACACACCATAT", "CCTACTACT", "CCACTATA", "CCACTATAA", "CCACTATAAT",
		"CCACTATAATA", "CCACTATAATAT", "AATAACCATA", "AATAACCATAC", "AATAACCATACA", "AATAACCATACAA",
		"CTAACAACC", "CTAACAACCT", "CACAAT", "CACAATA", "CTATACTA", "CTATACTAT", "CAATA", "CAATAC",
		"CAATACC", "CCAACACAT", "CCTACTAATA", "CCTACTAATAA", "CCTACTAATAAC", "CCTACTAATAACT", "AATAACCATACC",
		"AATAACCATACCT", "AATAACCATACCTA", "AATAACCATACCTAC", "AATAACCATACCTACT", "ATAACC", "CCATAAC", "CCATAACT",
		"ATACTAC", "ATACTACC", "CTAACCTAT", "CATACTAC", "CATACTACT", "CCATAACTA", "CCATAACTAT", "CCATAACTATA", 
		"CCATAACTATAC", "CCATAACTATACA", "CCATAACTATACAA", "CCATAACTATACAAT", "CCTATAA", "CCTATAAT", "AACATATA",
		"AACATATAA", "AACCAACAT", "AACCAACATA", "AACCAACATAA", "AACCAACATAAC", "AACCAACATAACC", "AACCAACATAACCT",
		"CCATAACA", "CCATACCT", "AACAACCAACAT", "AATAACAC", "AATAACACT", "AACACAAT", "AACACAATA", "AACACAATAT", 
		"AACACAATATA", "AACACAATATAT", "ACCAAT", "AACACCAAT", "CCATACA", "CCATACAC", "CCATACACT", "CCATACACTA",
		"CCATACACTAC", "CCATACACTACA", "CCATACACTACAC", "CCATACACTACACA", "CCATACACTACACAT", "CCTACAT", "CATAATA",
		"CATAATAT", "ACACTAACAT", "ACAACAAT", "CCACATAA", "CCACATAAC", "CCACATAACA", "CCACATAACAC", "CCACATAACACC",
		"CCACATAACACCA", "CCACATAACACCAT", "CCACATAACACCATA", "CCACATAACACCATAC", "CCACATAACACCATACC", "CAATAT",
		"CATACC", "AACTATAC", "AACTATACC", "AACTATACCT", "CACAATAC", "CACAATACT", "CCAACCAT", "CCAACCATA", 
		"CCAACCATAA", "CCAACCATAAT", "CCAACCATAATA", "CCAACCATAATAA", "CCAACCATAATAAC", "CCAACCATAATAACT", 
		"AACACAT", "AACACATA", "ACTAATAC", "ACTAATACC", "ACTAATACCA", "ACTAATACCAA", "AACAATAC", "AACAATACC",
		"AACAATACCT", "AACAATACCTA", "AACAATACCTAA", "AACAATACCTAAT", "CTACCTAT", "CAATAA", "CAATAAT", "AATATAT",
		"AATATATA", "AATATATAA", "AATATATAAT"};

System.out.println("========= X79547 ==========\t" + longSequence_5.length + "\tS5\t" + s5.length());

for(int i = 1; i <= 5; i++)
{

	System.out.println("=========================================================");
	if(i==1)
	{
		System.out.println("S1 S2 VALUE =\t" + av.findValue(s1, s2, longSequence_1,  longSequence_2, s1.length(), s2.length()));
		System.out.println("S1 S3 VALUE =\t" + av.findValue(s1, s3, longSequence_1,  longSequence_3, s1.length(), s3.length()));
		System.out.println("S1 S4 VALUE =\t" + av.findValue(s1, s4, longSequence_1,  longSequence_4, s1.length(), s4.length()));
		System.out.println("S1 S5 VALUE =\t" + av.findValue(s1, s5, longSequence_1,  longSequence_5, s1.length(), s5.length()));
	}
	else if(i==2)
	{
		System.out.println("S2 S1 VALUE =\t" + av.findValue(s2, s1, longSequence_2,  longSequence_1, s2.length(), s1.length()));
		System.out.println("S2 S3 VALUE =\t" + av.findValue(s2, s3, longSequence_2,  longSequence_3, s2.length(), s3.length()));
		System.out.println("S2 S4 VALUE =\t" + av.findValue(s2, s4, longSequence_2,  longSequence_4, s2.length(), s4.length()));
		System.out.println("S2 S5 VALUE =\t" + av.findValue(s2, s5, longSequence_2,  longSequence_5, s2.length(), s5.length()));
	}
	else if(i==3)
	{
		System.out.println("S3 S1 VALUE =\t" + av.findValue(s3, s1, longSequence_3,  longSequence_1, s3.length(), s1.length()));
		System.out.println("S3 S2 VALUE =\t" + av.findValue(s3, s2, longSequence_3,  longSequence_2, s3.length(), s2.length()));
		System.out.println("S3 S4 VALUE =\t" + av.findValue(s3, s4, longSequence_3,  longSequence_4, s3.length(), s4.length()));
		System.out.println("S3 S5 VALUE =\t" + av.findValue(s3, s5, longSequence_3,  longSequence_5, s3.length(), s5.length()));
	}
	else if(i==4)
	{
		System.out.println("S4 S1 VALUE =\t" + av.findValue(s4, s1, longSequence_4,  longSequence_1, s4.length(), s1.length()));
		System.out.println("S4 S2 VALUE =\t" + av.findValue(s4, s2, longSequence_4,  longSequence_2, s4.length(), s2.length()));
		System.out.println("S4 S3 VALUE =\t" + av.findValue(s4, s3, longSequence_4,  longSequence_3, s4.length(), s3.length()));
		System.out.println("S4 S5 VALUE =\t" + av.findValue(s4, s5, longSequence_4,  longSequence_5, s4.length(), s5.length()));
	}
	else if(i==5)
	{
		System.out.println("S5 S1 VALUE =\t" + av.findValue(s5, s1, longSequence_5,  longSequence_1, s5.length(), s1.length()));
		System.out.println("S5 S2 VALUE =\t" + av.findValue(s5, s2, longSequence_5,  longSequence_2, s5.length(), s2.length()));
		System.out.println("S5 S3 VALUE =\t" + av.findValue(s5, s3, longSequence_5,  longSequence_3, s5.length(), s3.length()));
		System.out.println("S5 S4 VALUE =\t" + av.findValue(s5, s4, longSequence_5,  longSequence_4, s5.length(), s4.length()));
	}
}
*/