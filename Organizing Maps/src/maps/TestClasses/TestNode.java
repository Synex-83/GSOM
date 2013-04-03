/**
 * @package		maps.TestClasses
 * @filename	TestNode.java
 */
package maps.TestClasses;

import org.apache.commons.math3.linear.ArrayRealVector;

import maps.Structures.Node;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 31, 2013 - 12:08:32 AM
 * @type		TestNode
 */
public class TestNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Node neuron = new Node(3,0,0);
		ArrayRealVector weights = neuron.getWEIGHTS();
		
		System.out.println(weights.getDimension());
		System.out.println(weights.getNorm());
		
		for(int i = 0; i  < weights.getDimension(); i++)
		{
			System.out.print(weights.getEntry(i)+ " ");
		}
	}

}
