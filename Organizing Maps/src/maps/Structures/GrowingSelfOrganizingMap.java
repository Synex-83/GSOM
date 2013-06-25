/**
 * 
 */
package maps.Structures;

import java.util.StringTokenizer;

import org.apache.commons.math3.linear.ArrayRealVector;

import sun.util.locale.StringTokenIterator;

/**
 * @author 		Manjusri Ishwara
 * @date		Apr 29, 2013 - 4:35:45 PM
 * @type        GrowingSelfOrganizingMap
 *
 */
public class GrowingSelfOrganizingMap {

	private int INPUT_DIMENSION = 0;
	private GSOMNode BASE_NODE = null; //node at (0,0) of the GSOM and one of the four fundamental nodes
	private double SPREAD_FACTOR = 0.0; //SF
	private double GROWTH_THRESHOLD = 0.0; //GT
	
	public GrowingSelfOrganizingMap(int inputDimension, double spreadFactor, DisplayLattice screen)
	{
		INPUT_DIMENSION = inputDimension;
		SPREAD_FACTOR = spreadFactor;
		
		initGSOM();
		initGrowthThreshold(SPREAD_FACTOR, INPUT_DIMENSION);
	}

	/**
	 * @param spreadFactor
	 * @param inputDimension
	 */
	private void initGrowthThreshold(double spreadFactor, int inputDimension)
	{
		GROWTH_THRESHOLD = -inputDimension*Math.log(spreadFactor);
	}

	/**
	 * 
	 */
	private void initGSOM() 
	{
		BASE_NODE = new GSOMNode(INPUT_DIMENSION, 0, 0); //set 0, 0 		
		BASE_NODE.setRIGHT(new GSOMNode(INPUT_DIMENSION, 0, 1)); // set 0, 1
		BASE_NODE.setUP(new GSOMNode(INPUT_DIMENSION, 1, 0)); // set 1, 0
		BASE_NODE.getUP().setLEFT(new GSOMNode(INPUT_DIMENSION, 1, 1)); // set 1, 1 and as the left of 1,0
		BASE_NODE.getLEFT().setUP(BASE_NODE.getUP().getLEFT()); //set up of  0,1
	}
	
	/**
	 * @param input as input vector
	 * Performs a single iteration of SOM training
	 */
	private void trainGSOM(String input)	
	{
		String line = "";
		double temp[] = null;
		GSOMNode winner = null;
		
		StringTokenizer inputTokens = new StringTokenizer(input, "\n");
		inputTokens.nextToken();
		
		while(inputTokens.hasMoreTokens())
		{
			line = inputTokens.nextToken();
			if(!line.contains("####"))
			{
				temp = new double[INPUT_DIMENSION];
				String[] inputVector = line.split("\t");
			}
		}
	}
	
	private GSOMNode setTotalError(ArrayRealVector input)
	{
		return null;
	}
}
