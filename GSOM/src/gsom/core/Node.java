/**
 * 
 */
package gsom.core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import Jama.Matrix;

/**
 * @File 	Node.java
 * @author 	Manjusri Ishwara
 * @date   	Jul 28, 2012 - 3:32:46 PM
 * @package	gsom.core
 */
public class Node {

	private Node Neighbour_North = null;
	private Node Neighbour_East = null;
	private Node Neighbour_West = null;
	private Node Neighbour_South = null ;
	private int coordinate_X = 0;
	private int coordinate_Y = 0;	
	private double accumulated_error = 0.0;
	private double multiplicativeValue = 0.0;
	private Matrix weights;
	private Queue<String> winners = new LinkedList<String>();

	
	/**
	 * Default constructor only to be invoked by the EmptyNode class upon creating place holder nodes.
	 */
	public Node()
	{
		
	}
	
	/**
	 * @param Location_X
	 * @param Location_Y
	 * Initializes a Node object with specifed coordinates according to the map constructed.
	 */
	public Node(int Location_X, int Location_Y)
	{
		coordinate_X = Location_X;
		coordinate_Y = Location_Y;
		Neighbour_North = new EmptyNode();
		Neighbour_East = new EmptyNode();
		Neighbour_West = new EmptyNode();
		Neighbour_South = new EmptyNode();
	}
	
	/**	
	 * @param north
	 */
	public void setNeighbourNorth(Node north)
	{
		Neighbour_North = north;
	}
	
	/**
	 * @return
	 */
	public Node getNeighbourNorth()
	{
		return Neighbour_North;
	}
	
	/**
	 * @param east
	 */
	public void setNeighbourEast(Node east)
	{
		Neighbour_East = east;
	}
	
	/**
	 * @return
	 */
	public Node getNeighbourEast()
	{
		return Neighbour_East;
	}
	
	/**
	 * @param west
	 */
	public void setNeighbourWest(Node west)
	{
		Neighbour_West = west;
	}
	
	/**
	 * @return
	 */
	public Node getNeighbourWest()
	{
		return Neighbour_West;
	}
	
	/**
	 * @param south
	 */
	public void setNeighbourSouth(Node south)
	{
		Neighbour_South = south;
	}
	
	/**
	 * @return
	 */
	public Node getNeighbourSouth()
	{
		return Neighbour_South;
	}
	
	/**
	 * @param north
	 * @param east
	 * @param south
	 * @param west
	 */
	public void setNeighbours(Node north, Node east, Node south, Node west)
	{
		Neighbour_North = north;
		Neighbour_East = east;
		Neighbour_South = south;
		Neighbour_West = west;			
	}
	
	/**
	 * Overrides the default toString() implementation.
	 */
	public String toString()
	{
		return "\nLocation X = " + coordinate_X + "\nLocation Y = " + coordinate_Y +
				"\nAccumulated Error = " + accumulated_error ;
	}
	
	/**
	 * @param dimensions
	 * The weight vector dimensions are equal to the input dimensions of the GSOM. This method initializes weight vectors
	 * to the size of the input vectors in all the nodes in the map.
	 */
	public void setWeightVector(int dimensions)
	{
		Random random = new Random();
		double tempWeights[] = new double[dimensions];
		
		for (int i = 0 ; i < tempWeights.length; i++) 
		{
			tempWeights[i]= random.nextDouble();
		}
		
		weights = new Matrix(tempWeights, 1);
	}
	
	/**
	 * @return
	 * Returns the weight vector of the associated node.
	 */
	public String returnWeightVector()
	{	
		return weights.toString();
	}
	
	/**
	 * @return
	 * Returns the weight vector in a matrix format.
	 */
	public Matrix getWeights()
	{
		return weights.transpose();
	}
	
	
	/**
	 * @param weightVector
	 * Sets the weight vector of the node as specified by the parameter.
	 */
	public void setWeights(Matrix weightVector)
	{
		weights = weightVector.transpose();
	}
	
	/**
	 * @param value
	 * Records the hit input vectors of the trainning process in an internal queue representation.
	 */
	public void setHitVlaue(String value)
	{
		winners.add(value);
	}
	
	/**
	 * @param resultValue
	 * Set the value obtained by w.x sum using matrix multiplication.
	 */
	public void setMultiplicativeValue(double resultValue)
	{
		multiplicativeValue = resultValue;
	}
	
	/**
	 * @return
	 * Return the multiplicative value stored in the node.
	 */
	public double getMultiplicativeValue()
	{
		return multiplicativeValue;
	}
}
