/**
 * @package		maps.Structures
 * @filename	Node.java
 */
package maps.Structures;

import org.apache.commons.math3.linear.*;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 30, 2013 - 11:46:53 PM
 * @type		Node
 */
public class Node {
	
	private int DIMENSION = 0;	//Contains the dimensionality of data
	private ArrayRealVector WEIGHTS = null;	//Contains the set of weights associated with a node
	private int POSITION_X = 0; //x position in the 2D grid
 	private int POSITION_Y = 0; //y position in the 2D grid
 	private double ACTIVATION_VALUE = 0.0; //value after weight vector * input vector
	
	//========================= CONSTRUCTORS ==========================================
 	/**
 	 * @param Dimensions as the input dimension
 	 * @param x as the X location of the 2D grid
 	 * @param y as the Y location of the 2D grid
 	 */
	public Node(int Dimensions, int x, int y)
	{
		DIMENSION = Dimensions;	
		setWeightVector();
		POSITION_X = x;
		POSITION_Y = y;
	}
	//========================= GETTERS & SETTERS =====================================

	/**
	 * @return the weight vector 
	 */
	public ArrayRealVector getWEIGHTS() {
		return WEIGHTS;
	}
	
	/**
	 * @param weights to set the weight vector of a node
	 */
	public void setWEIGHTS(ArrayRealVector weights)
	{
		WEIGHTS = weights;
	}
	
	/**
	 * @return the x coordinate
	 */
	public int getX() {
		return POSITION_X;
	}
	
	/**
	 * @return the y coordinate
	 */
	public int getY() {
		return POSITION_Y;
	}
	
	/**
	 * @return the activation value of the node (weight vector * input vector)
	 */
	public double getACTIVATION_VALUE() {
		return ACTIVATION_VALUE;
	}
	
	/**
	 * @param x to set the value of the x coordinate
	 */
	public void setPOSITION_X(int x) {
		POSITION_X = x;
	}
	
	/**
	 * @param y to set the value of the y coordinate
	 */
	public void setPOSITION_Y(int y) {
		POSITION_Y = y;
	}
	
	/**
	 * @param y to set the activation value per node iteration
	 */
	public void setACTIVATION_VALUE(double y) {
		ACTIVATION_VALUE= y;
	}
	
	//========================= METHODS ===============================================
	
	/**
	 * Set the weight vector according to the dimensions specified by the constructor and initializes
	 * each value to a random double.
	 */
	private void setWeightVector()
	{
		double temp[] = new double[DIMENSION];
		
		for(int i = 0; i<temp.length ; i++)
		{
			temp[i] =  Math.random();
		}
		
		WEIGHTS = new ArrayRealVector(temp);
	}
	
}
