/**
 * @package		maps.Structures
 * @filename	Node.java
 */
package maps.Structures;

import java.util.ArrayList;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:17:15 PM
 * @type		Node
 */

public class Node {
	
	private int DIMENSION = 0;					//Contains the dimensionality of data
	private ArrayRealVector WEIGHTS = null;		//Contains the set of weights associated with a node
	private Array2DRowRealMatrix WEIGHT_MATRIX = null; //Contains the weights of the associated node in the matrix case
	private int POSITION_X = 0; 				//x position in the 2D grid
 	private int POSITION_Y = 0; 				//y position in the 2D grid
 	private double ACTIVATION_VALUE = 0.0; 		//value after weight vector * input vector
	private boolean IS_MATRIX = false;
	private int COVARIANCE_NUMBER = 0;		//number of vectors considered for the covariance matrix
	private int NUMBER_OF_HITS = 0;			//number of inputs mapped to the unit
	private ArrayList<String> MAPPED_SEQUENCES = null; //the mapped sequences will be put here for each node 
 	
	//========================= CONSTRUCTORS ==========================================
 	/**
 	 * @param Dimensions as the input dimension
 	 * @param x as the X location of the 2D grid
 	 * @param y as the Y location of the 2D grid
 	 * @param isMatrix as the switch to recognize between the vector based weights and the matrix based weights
 	 * @param covarianceNumber is the number of vectors used to calculate covariance
 	 */
	public Node(int Dimensions, int x, int y, boolean isMatrix, int covarianceNumber)
	{
		DIMENSION = Dimensions;
		IS_MATRIX = isMatrix;
		COVARIANCE_NUMBER = covarianceNumber;
		MAPPED_SEQUENCES = new ArrayList<String>();
		
		if(isMatrix)
		{
			setWeightVectorMatrix();
		}
		else
		{
			setWeightVector();
		}
		
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

	/**
	 * @return the IS_MATRIX
	 */
	public boolean isMatrix() {
		return IS_MATRIX;
	}

	/**
	 * @param isMatrix the iS_MATRIX to set
	 */
	public void setIsMatrix(boolean isMatrix) {
		IS_MATRIX = isMatrix;
	}

	/**
	 * @return the COVARIANCE_NUMBER
	 */
	public int getCovarianceNumber() {
		return COVARIANCE_NUMBER;
	}

	/**
	 * @param covarianceNumber the cOVARIANCE_NUMBER to set
	 */
	public void setCovarianceNumber(int covarianceNumber) {
		COVARIANCE_NUMBER = covarianceNumber;
	}
	
	/**
	 * @return the WEIGHT_MATRIX
	 */
	public Array2DRowRealMatrix getWeightMatrix() {
		return WEIGHT_MATRIX;
	}

	/**
	 * @param weightMatrix the wEIGHT_MATRIX to set
	 */
	public void setWeightMatrix(Array2DRowRealMatrix weightMatrix) {
		WEIGHT_MATRIX = weightMatrix;
	}

	/**
	 * @return the number of hits for that particular node
	 */
	public int getNumberOfHits() {
		return NUMBER_OF_HITS;
	}

	/**
	 * @return the sequence list mapped to the particular node
	 */
	public ArrayList<String> getMappedSequences() {
		return MAPPED_SEQUENCES;
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
	
	/**
	 * Set the weight vector according to the number of vectors used to generated the covariance matrix. Initializes 
	 * each matrix element to a random value.
	 */
	private void setWeightVectorMatrix()
	{
		double temp[][] = new double[COVARIANCE_NUMBER][COVARIANCE_NUMBER];
		
		for(int i = 0 ; i < COVARIANCE_NUMBER; i++)
		{
			for(int j = 0; j < COVARIANCE_NUMBER; j++)
			{
				temp[i][j] = Math.random();
			}
		}

		setWeightMatrix(new Array2DRowRealMatrix(temp));
	}
	
	/**
	 * Increments the nodes hit amount by one each time the method in called.
	 */
	public void incrementNumberOfHits()
	{
		NUMBER_OF_HITS++;
	}
	
	/**
	 * @param sequence the input sequence which mapped to this node whilst training
	 */
	public void addMappingSequence(String sequence)
	{
		if(!MAPPED_SEQUENCES.contains(sequence))
		{
			MAPPED_SEQUENCES.add(sequence);
		}
	}
	
}
