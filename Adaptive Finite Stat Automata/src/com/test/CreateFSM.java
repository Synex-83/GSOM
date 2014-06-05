/**
 * 
 */
package com.test;

import com.structures.FiniteStateMachine;
import com.util.FileProcessing;

/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 4:33:45 PM
 * @package com.test
 * 
 */
public class CreateFSM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String fileName = "E:\\workspace\\GSOM\\Sequence Modeling\\data\\mtDNA\\bonobo - D38116.txt";
		
		FileProcessing fp = new FileProcessing(fileName);
		
		String data = fp.readFile();		
		
		FiniteStateMachine FSM = new FiniteStateMachine(2,2,data);
		
		FSM.createFiniteStateMachine(data,800,1,0.0625, 0.25);
		
		System.out.println("==================== AFTER PRESENTATION ========================");
		//FSM.printStateMaching(1);
		//FSM.printStateMaching(2);

	}

}
