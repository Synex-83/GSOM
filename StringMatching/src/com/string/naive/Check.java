/**
 * 
 */
package com.string.naive;

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
		
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\human - D38112.txt", 1); 
		String s1 = fp.readFileLine(); 
				
	
		fp = new FileProcessing("E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\chimpanzee - D38113.txt", 1); 
		String s2 = fp.readFileLine(); 
		
		String p = "ACCTATC";
		
		NaiveStringMatching nv = null; 
		
		System.out.println("========= D38112 ==========");
		nv = new NaiveStringMatching(s1, p);
		nv.count();
		
		System.out.println("========= D38113 ==========");
		nv = new NaiveStringMatching(s2, p);
		nv.count();
	}

}
