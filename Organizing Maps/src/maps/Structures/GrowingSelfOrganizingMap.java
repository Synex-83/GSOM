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
	private int NUMBER_OF_NODES_IN_NETWORK = 0;
	private GSOMNode BASE_NODE = null; //node at (0,0) of the GSOM and one of the four fundamental nodes
	private double SPREAD_FACTOR = 0.0; //SF
	private double GROWTH_THRESHOLD = 0.0; //GT
	private double INITIAL_LEARNING_RATE = 0.0;
	private double INITIAL_NEIGHBORHOOD_RADIUS = 0.0;
	private double HIGHEST_ERROR = 0.0;
	private double LEARNING_RATE = 0.0;
	private double RADIUS = 0.0;
	private double ALPHA = 0.0; //discount factor of the learning rate.
	private Queue<GSOMNode> NODES_TO_VISIT = null;
	private NumberPole NUMBER_POLE = null;
	
	public GrowingSelfOrganizingMap(int inputDimension,double ETA, double spreadFactor, double radius, DisplayLattice screen)
	{
		INPUT_DIMENSION = inputDimension;
		SPREAD_FACTOR = spreadFactor;
		INITIAL_NEIGHBORHOOD_RADIUS = radius;
		INITIAL_LEARNING_RATE = ETA;
		LEARNING_RATE = INITIAL_LEARNING_RATE;
		NODES_TO_VISIT = new LinkedList<GSOMNode>();
		NUMBER_POLE = new NumberPole();
		
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
		addToNumberPole(BASE_NODE);
		
		
		BASE_NODE.setRIGHT(new GSOMNode(INPUT_DIMENSION, 0, 1)); // set 0, 1
		BASE_NODE.setUP(new GSOMNode(INPUT_DIMENSION, 1, 0)); // set 1, 0
		BASE_NODE.getUP().setLEFT(new GSOMNode(INPUT_DIMENSION, 1, 1)); // set 1, 1 and as the left of 1,0
		BASE_NODE.getLEFT().setUP(BASE_NODE.getUP().getLEFT()); //set up of  0,1
		
		
		
		NUMBER_OF_NODES_IN_NETWORK = 4; //initial number of nodes
	}
	
	/**
	 * @param bASE_NODE2
	 */
	private void addToNumberPole(GSOMNode node) {
		
		
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
								
				for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) //goes to 0...100 all inclusive
				{ 
					winner = presentSingleInput(new ArrayRealVector(temp)); //idea of a return value is to halt further execution of code until the method call has returned.
					calculateGrowthError(winner, new ArrayRealVector(temp)); //processes the QE and triggers node growth if required.
					adjustNeighbourhoodOfWinner(winner, new ArrayRealVector(temp));
					learningRateDecay();
					radiusDecay(i);
				}
			}
		}
	}
	
	/**
	 * @param winner
	 */
	private void calculateGrowthError(GSOMNode winner, ArrayRealVector inputVector) 
	{			
		double differenceInVectors = (inputVector.subtract(winner.getWEIGHTS())).getNorm();		
		winner.setTotalError(winner.getTotalError() + differenceInVectors);
		if(winner.getTotalError() >= GROWTH_THRESHOLD)
		{
			if(winner.getTotalError() < HIGHEST_ERROR)
			{
				HIGHEST_ERROR = winner.getTotalError();
			}
			growNodes(winner);			
		}		
	}

	/**
	 * @param winner
	 */
	private void growNodes(GSOMNode winner) 
	{
	
	}

	/**
	 * @param arrayRealVector
	 * @return
	 */
	private GSOMNode presentSingleInput(ArrayRealVector input)
	{		
		GSOMNode winner = getWinner(input);
		winner.incrementNumberOfHits();
		return winner;
	}

	/**
	 * @param winner
	 * @param input
	 */
	private void adjustNeighbourhoodOfWinner(GSOMNode winner, ArrayRealVector inputVector) 
	{
		adjustWeightVectors(winner, inputVector);
	}
	
	/**
	 * @param neighbours
	 */
	private void adjustWeightVectors(GSOMNode node, ArrayRealVector input) {
		GSOMNode winner = node;
		LinkedList<GSOMNode> neighbourList = new LinkedList<GSOMNode>();
		neighbourList.add(winner);
			
		Queue<GSOMNode> neighbourQueue = new LinkedList<GSOMNode>();
		neighbourQueue.add(winner);
		
		GSOMNode temp = null;
		ArrayRealVector tempWeights = null;
		
		while(!neighbourQueue.isEmpty())
		{
			temp = neighbourQueue.remove();
			tempWeights = temp.getWEIGHTS().add((input.subtract(temp.getWEIGHTS())).mapMultiplyToSelf(LEARNING_RATE));
			temp.setWEIGHTS(tempWeights);
			
			if(temp.getRIGHT() != null && !neighbourList.contains(temp.getRIGHT()) && getEculidianDistance(winner, temp.getRIGHT()) <= RADIUS)
			{
				neighbourQueue.add(temp.getRIGHT());
				neighbourList.add(temp.getRIGHT());
			}		
			
			if(temp.getLEFT() != null && !neighbourList.contains(temp.getLEFT()) && getEculidianDistance(winner, temp.getLEFT()) <= RADIUS)
			{
				neighbourQueue.add(temp.getLEFT());
				neighbourList.add(temp.getLEFT());
			}
			
			if(temp.getUP() != null && !neighbourList.contains(temp.getUP()) && getEculidianDistance(winner, temp.getUP()) <= RADIUS)
			{
				neighbourQueue.add(temp.getUP());
				neighbourList.add(temp.getUP());
			}
			
			if(temp.getDOWN() != null && !neighbourList.contains(temp.getDOWN()) && getEculidianDistance(winner, temp.getDOWN()) <= RADIUS)
			{
				neighbourQueue.add(temp.getDOWN());
				neighbourList.add(temp.getDOWN());
			}
		}
	}

	private double getEculidianDistance(GSOMNode a, GSOMNode b)
	{
		
		ArrayRealVector first = new ArrayRealVector(new double[]{a.getX(),a.getY()});
		ArrayRealVector seconed = new ArrayRealVector(new double[]{b.getX(), b.getY()});
		ArrayRealVector distance = first.subtract(seconed);		
		return distance.getNorm();		
	}
	
	private void learningRateDecay()
	{
		LEARNING_RATE = ALPHA*CHI(NUMBER_OF_NODES_IN_NETWORK)*LEARNING_RATE;
	}
	
	/**
	 * @param nUMBER_OF_NODES_IN_NETWORK2
	 * @return
	 */
	private double CHI(int numberOfNodes) {
		// TODO Auto-generated method stub		
		return (1 - (3.5/numberOfNodes));
	}

	private void radiusDecay(int currentIteration)
	{
		RADIUS = INITIAL_NEIGHBORHOOD_RADIUS*Math.exp(currentIteration/(NUMBER_OF_ITERATIONS/4));
	}

	private GSOMNode getWinner(ArrayRealVector input)
	{
		GSOMNode tempNode = null;
		double tempValue = 0.0;
		double minSeen = Double.POSITIVE_INFINITY;
		GSOMNode minNode = null;
		
		NODES_TO_VISIT.add(BASE_NODE);
		
		while(!NODES_TO_VISIT.isEmpty())
		{
			tempNode = NODES_TO_VISIT.remove();	
						
			addToQueue(tempNode);
			tempValue = getEuclideanAccumulatedValue(input, tempNode);
			
			if(tempValue < minSeen)
			{
				minNode = tempNode;
				minSeen = tempValue;						
			}		
			
			tempNode.setACTIVATION_VALUE(tempValue);
		}	
		
		NODES_TO_VISIT.clear();
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
			NODES_TO_VISIT.add(temp.getRIGHT());
			
		}
		if(temp.getUP() != null && !(temp.getUP().isVisited()))
		{
			NODES_TO_VISIT.add(temp.getUP());
			
		}
		if(temp.getLEFT() != null && !(temp.getLEFT().isVisited()))
		{
			NODES_TO_VISIT.add(temp.getLEFT());
			
		}
		if(temp.getDOWN() != null && !(temp.getDOWN().isVisited()))
		{
			NODES_TO_VISIT.add(temp.getDOWN());
			
		}
	}
	
	private void resetGSOMNodeVisit()
	{
		GSOMNode tempNode = null;
		
		NODES_TO_VISIT.add(BASE_NODE);
		
		while(!NODES_TO_VISIT.isEmpty())
		{
			tempNode = NODES_TO_VISIT.remove();
			tempNode.setVisited(false);
						
			if(tempNode.getRIGHT() != null && tempNode.getRIGHT().isVisited())
			{
				NODES_TO_VISIT.add(tempNode.getRIGHT());
				
			}
			if(tempNode.getUP() != null && tempNode.getUP().isVisited())
			{
				NODES_TO_VISIT.add(tempNode.getUP());
				
			}
			if(tempNode.getLEFT() != null && tempNode.getLEFT().isVisited())
			{
				NODES_TO_VISIT.add(tempNode.getLEFT());
				
			}
			if(tempNode.getDOWN() != null && tempNode.getDOWN().isVisited())
			{
				NODES_TO_VISIT.add(tempNode.getDOWN());
				
			}

		}			
		NODES_TO_VISIT.clear();
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
