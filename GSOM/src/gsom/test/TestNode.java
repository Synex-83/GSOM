/**
 * 
 */
package gsom.test;

import gsom.core.*;

/**
 * @File 	TestNode.java
 * @author 	Manjusri Ishwara
 * @date   	Jul 28, 2012 - 5:39:02 PM
 * @package	gsom.test
 */
public class TestNode {

	public static void main(String args[]){
		//Node node = new Node(5,5);
		//System.out.println(node.toString());
		
		Map gsom = new Map();
		gsom.setInputDimension(4);
		gsom.setWeights();
		//gsom.printNetwork();
		
		gsom.train();
		gsom.printNetwork();
	}
}
