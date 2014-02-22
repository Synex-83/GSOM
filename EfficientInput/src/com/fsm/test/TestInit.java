/**
 * @package		com.fsm.test
 * @filename	TestInit.java
 */
package com.fsm.test;

import com.fsm.initialization.InputInitializtion;

/**
 * @author 		Manjusri Ishwara
 * @date   		Feb 23, 2014 - 12:43:25 AM
 * @type		TestInit
 */
public class TestInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputInitializtion input = new InputInitializtion("E:\\workspace\\GSOM\\Sequence Modeling\\data\\4sequences.txt");
		
		input.processFile();
	}

}
