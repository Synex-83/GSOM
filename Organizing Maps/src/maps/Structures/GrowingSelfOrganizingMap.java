/**
 * 
 */
package maps.Structures;

import java.util.LinkedList;
import java.util.Queue;
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
	private int NUMBER_OF_ITERATIONS = 0;
	private GSOMNode BASE_NODE = null; //node at (0,0) of the GSOM and one of the four fundamental nodes
	private double SPREAD_FACTOR = 0.0; //SF
	private double GROWTH_THRESHOLD = 0.0; //GT
	private double INITIAL_LEARNING_RATE = 0.0;
	private double INITIAL_NEIGHBORHOOD_RADIUS = 0.0;
	private Queue<GSOMNode> nodesToVisit = null;
	
	public GrowingSelfOrganizingMap(int inputDimension, double spreadFactor, DisplayLattice screen)
	{
		INPUT_DIMENSION = inputDimension;
		SPREAD_FACTOR = spreadFactor;
		nodesToVisit = new LinkedList<GSOMNode>();
		
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
				
				for(int i = 1; i < inputVector.length; i++)
				{
					temp[i-1] = Double.parseDouble(inputVector[i]);					
				}
				
				winner = presentSingleInput(new ArrayRealVector(temp));
			}

		}
	}
	
	/**
	 * @param arrayRealVector
	 * @return
	 */
	private GSOMNode presentSingleInput(ArrayRealVector input)
	{		
		
		return null;
	}

	private GSOMNode getWinner(ArrayRealVector input)
	{
		GSOMNode tempNode = null;
		double tempValue = 0.0;
		double minSeen = Double.POSITIVE_INFINITY;
		GSOMNode minNode = null;
		
		nodesToVisit.add(BASE_NODE);
		
		while(!nodesToVisit.isEmpty())
		{
			tempNode = nodesToVisit.remove();	
						
			addToQueue(tempNode);
			tempValue = getEuclideanAccumulatedValue(input, tempNode);
			
			if(tempValue < minSeen)
			{
				minNode = tempNode;
				minSeen = tempValue;						
			}		
			
			tempNode.setACTIVATION_VALUE(tempValue);
		}	
		
		nodesToVisit.clear();
		resetGSOMNodeVisit();
		return minNode;		
	}
	
	
	private double getEuclideanAccumulatedValue(ArrayRealVector input, GSOMNode node)
	{
		double temp = 0.0;

		temp = (node.getWEIGHTS().subtract(input)).getNorm(); // in case there is an error this would revert to zero		

		return temp;
	}
	
	/**
	 * @param temp
	 */
	private void addToQueue(GSOMNode temp) {
		// TODO Auto-generated method stub
		temp.setVisited(true);
		
		if(temp.getRIGHT() != null && !(temp.getRIGHT() .isVisited()))
		{
			nodesToVisit.add(temp.getRIGHT());
			
		}
		if(temp.getUP() != null && !(temp.getUP().isVisited()))
		{
			nodesToVisit.add(temp.getUP());
			
		}
		if(temp.getLEFT() != null && !(temp.getLEFT().isVisited()))
		{
			nodesToVisit.add(temp.getLEFT());
			
		}
		if(temp.getDOWN() != null && !(temp.getDOWN().isVisited()))
		{
			nodesToVisit.add(temp.getDOWN());
			
		}
	}
	
	private void resetGSOMNodeVisit()
	{
		GSOMNode tempNode = null;
		
		nodesToVisit.add(BASE_NODE);
		
		while(!nodesToVisit.isEmpty())
		{
			tempNode = nodesToVisit.remove();
			tempNode.setVisited(false);
						
			if(tempNode.getRIGHT() != null && tempNode.getRIGHT().isVisited())
			{
				nodesToVisit.add(tempNode.getRIGHT());
				
			}
			if(tempNode.getUP() != null && tempNode.getUP().isVisited())
			{
				nodesToVisit.add(tempNode.getUP());
				
			}
			if(tempNode.getLEFT() != null && tempNode.getLEFT().isVisited())
			{
				nodesToVisit.add(tempNode.getLEFT());
				
			}
			if(tempNode.getDOWN() != null && tempNode.getDOWN().isVisited())
			{
				nodesToVisit.add(tempNode.getDOWN());
				
			}

		}			
		nodesToVisit.clear();
	}

	/**
	 * @param input as the current input vector.
	 * @return the winner after the input is presented to all the nodes.
	 */
	private GSOMNode setTotalError(ArrayRealVector input)
	{
		return null;
	}
}
