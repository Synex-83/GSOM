/**
 * 	Copyright 2013 Manjusri Wickramasinghe. All rights reserved.
 *
 *	Redistribution and use in source and binary forms, with or without modification, are
 *	permitted provided that the following conditions are met:
 *	
 *	   1. Redistributions of source code must retain the above copyright notice, this list of
 *	      conditions and the following disclaimer.
 *
 *	   2. Redistributions in binary form must reproduce the above copyright notice, this list
 *	      of conditions and the following disclaimer in the documentation and/or other materials
 *	      provided with the distribution.
 *
 *	THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *	WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *	FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR
 *	CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *	CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *	SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *	ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *	NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *	ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package maps.Structures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		Manjusri Ishwara
 * @date		Apr 29, 2013 - 4:35:45 PM
 * @type        GrowingSelfOrganizingMap
 * 
 * Contains the implementation of the Growing Self-Organizing Map algorithm with the associated visualization methods.
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
	private double FD = 0.5; //the Factor of Distribution (FD) 0 < gamma < 1
	private Queue<GSOMNode> NODES_TO_VISIT = null;
	private NumberPole NUMBER_POLE = null;
	
	/**
	 * @param inputDimension as the number of dimensions
	 * @param ETA as the learing rate
	 * @param spreadFactor as the desired level of spread of the resulting GSOM
	 * @param radius as the neighbourhood radius
	 * @param screen as the JPanel instance
	 * 
	 * This method initializes all the variables in the GSOM and initilizes the basic structure of the GSOM. This method also
	 * initilizes the number pole object which keeps all the references to the GSOMNodes.
	 */
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
	 * @param spreadFactor as the desired level of spread of the resulting GSOM
	 * @param inputDimension as the number of dimensions
	 * 
	 * Called by the method {@link #GrowingSelfOrganizingMap(int, double, double, double, DisplayLattice)} to initialize the 
	 * growth threshold which decides whether a node grows or not. The value is calculated according the standard function 
	 * provided with the algorithm which is GT = -D*ln(SF)
	 */
	private void initGrowthThreshold(double spreadFactor, int inputDimension)
	{
		GROWTH_THRESHOLD = -inputDimension*Math.log(spreadFactor);
	}

	/**
	 * Called by the method {@link #GrowingSelfOrganizingMap(int, double, double, double, DisplayLattice)} to initialize the 
	 * structure of the GSOM with initial four node structures. This created the  GSOMNodes with all the initial relationships.
	 * The NumberPole object too initialized to keep reference of each GSOMNode created.
	 */
	private void initGSOM() 
	{
		BASE_NODE = new GSOMNode(INPUT_DIMENSION, 0, 0); //set 0, 0		
		BASE_NODE.setRIGHT(new GSOMNode(INPUT_DIMENSION, 0, 1)); // set 0, 1
		BASE_NODE.getRIGHT().setLEFT(BASE_NODE); //references adding
		
		BASE_NODE.setUP(new GSOMNode(INPUT_DIMENSION, 1, 0)); // set 1, 0
		BASE_NODE.getUP().setDOWN(BASE_NODE); //references adding
		
		BASE_NODE.getUP().setRIGHT(new GSOMNode(INPUT_DIMENSION, 1, 1)); // set 1, 1 and as the left of 1,0
		BASE_NODE.getRIGHT().setUP(BASE_NODE.getUP().getRIGHT()); //set up of  0,1
		BASE_NODE.getUP().getRIGHT().setLEFT(BASE_NODE.getUP());
		BASE_NODE.getUP().getRIGHT().setDOWN(BASE_NODE.getRIGHT());
		
		addToNumberPole(BASE_NODE); //0,0
		addToNumberPole(BASE_NODE.getUP()); //0,1
		addToNumberPole(BASE_NODE.getRIGHT()); // 1,0
		addToNumberPole(BASE_NODE.getUP().getRIGHT()); //1,1
		
		NUMBER_OF_NODES_IN_NETWORK = 4; //initial number of nodes
		
		System.out.println("TEST");
	}
	

	/**
	 * @param node as the new GSOMNode to be added
	 * 
	 * Add the new node to the NumberPole object according to its X and Y location for further referencing.
	 */
	private void addToNumberPole(GSOMNode node) 
	{	
		if(!NUMBER_POLE.nodeExists(node))
		{
			NUMBER_POLE.add(node);
		}	
	}

	/**
	 * @param input as the input string
	 * 
	 * Completes a single iteration of the GSOM process. This is different from the SOM learning process
	 * since all the inputs are not presented in a single iteration. A single input is put to the complete number of 
	 * iterations and then set back to the intial values for the next new input.
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
	 * @param winner as the winning GSOMNode
	 * 
	 * Calculates the growth error of the GSOMNode. If the accumulated error is greater than the growth threshold value
	 * this method will trigger node growth and adjust the highest error of the network if it is greater than the previously
	 * observed network error.
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
	 * @param neighbours
	 * 
	 * This method will be triggered with {@link #growNodes(GSOMNode)} when a non boundary node is selected as the winner
	 * and it will be called recursively subsequently if it triggers growth in a non-boundary node.
	 */
	private void calculateGrowthErrorPropergation(GSOMNode[] neighbours)
	{
		for(int i = 0; i < neighbours.length; i++)
		{
			neighbours[i].setTotalError(neighbours[i].getTotalError() + FD*neighbours[i].getTotalError());
			if(neighbours[i].getTotalError() >= GROWTH_THRESHOLD)
			{
				if(neighbours[i].getTotalError() < HIGHEST_ERROR)
				{
					HIGHEST_ERROR = neighbours[i].getTotalError();
				}		
				growNodes(neighbours[i]);
			}
		}
	}
	
	/**
	 * @param winner as the winning GSOMNode
	 * 
	 * Grows nodes according to the structure and the placement of the winner node.
	 */
	private void growNodes(GSOMNode winner) 
	{
		
		GSOMNode left =null, right=null, up=null, down=null;
		GSOMNode[] temp = null;
		
		if(!winner.isBoundry())
		{
		//	distributeErrorToNeighbors(winner);
			temp = NUMBER_POLE.getNeighborNodes(winner.getX(), winner.getY());
			calculateGrowthErrorPropergation(temp);
			winner.setTotalError(GROWTH_THRESHOLD/2);
		}
		else
		{
			if(winner.getLEFT() == null)
			{
				left = new GSOMNode(INPUT_DIMENSION,winner.getX() - 1, winner.getY());
				winner.setLEFT(left);
				addToNumberPole(left);
				temp = NUMBER_POLE.getNeighborNodes(left.getX(), left.getY());
				
				left.setLEFT(temp[0]);
				if(temp[0] != null)
				{
					left.getLEFT().setRIGHT(left);
				}
				
				left.setRIGHT(temp[1]);
			
				left.setUP(temp[2]);
				if(temp[2] != null)
				{
					left.getUP().setDOWN(left);
				}
				
				left.setDOWN(temp[3]);
				if(temp[3] != null)
				{
					left.getDOWN().setUP(left);
				}
						
				setWeightsOfNewNode(left, winner, true, false, false, false);
			}
			
			if(winner.getRIGHT() == null)
			{
				right = new GSOMNode(INPUT_DIMENSION,winner.getX() + 1, winner.getY()); 
				winner.setRIGHT(right);
				addToNumberPole(right);
				temp = NUMBER_POLE.getNeighborNodes(right.getX(), right.getY());
				
				right.setLEFT(temp[0]);
				
				right.setRIGHT(temp[1]);
				if(temp[1] != null)
				{
					right.getRIGHT().setLEFT(right);
				}
				
				right.setUP(temp[2]);
				if(temp[2] != null)
				{
					right.getUP().setDOWN(right);
				}
				
				right.setDOWN(temp[3]);
				if(temp[3] != null)
				{
					right.getDOWN().setUP(right);
				}
				
				setWeightsOfNewNode(right, winner, false, true, false, false);

			}
			
			if(winner.getUP() == null)
			{
				up = new GSOMNode(INPUT_DIMENSION,winner.getX(), winner.getY() + 1);
				winner.setUP(up);
				addToNumberPole(up);
				temp = NUMBER_POLE.getNeighborNodes(up.getX(), up.getY());
				
				up.setLEFT(temp[0]);
				if(temp[0] != null)
				{
					up.getLEFT().setRIGHT(up);
				}
				
				up.setRIGHT(temp[1]);
				if(temp[1] != null)
				{
					up.getRIGHT().setLEFT(up);
				}
				
				up.setUP(temp[2]);
				if(temp[2] != null)
				{
					up.getUP().setDOWN(up);
				}
				
				up.setDOWN(temp[3]);
				
				setWeightsOfNewNode(up, winner, false, false, true, false);

			}
			
			if(winner.getDOWN() == null)
			{
				down = new GSOMNode(INPUT_DIMENSION,winner.getX(), winner.getY() - 1);
				winner.setLEFT(down);
				addToNumberPole(down);
				temp = NUMBER_POLE.getNeighborNodes(down.getX(), down.getY());
				
				down.setLEFT(temp[0]);
				if(temp[0] != null)
				{
					down.getLEFT().setRIGHT(down);
				}
				
				down.setRIGHT(temp[1]);
				if(temp[1] != null)
				{
					down.getRIGHT().setLEFT(down);
				}
				
				down.setUP(temp[2]);
				
				down.setDOWN(temp[3]);
				if(temp[3] != null)
				{
					down.getDOWN().setUP(down);
				}
				
				setWeightsOfNewNode(down, winner, false, false, false, true);
			}
			
			winner.setBoundry(false);
			winner.setTotalError(GROWTH_THRESHOLD/2); //prejudeged according to the flow of text. However, how to set this 
			//value is not specified in the thesis.
		}	
	}


	/**
	 * @param newNode as the newly inserted GSOMNode
	 * @param winner as the winner node of the GSOM
	 * @param isLeft the new node is the left of the winner
	 * @param isRight the new node is the right of the winner
	 * @param isUp the new node is placed above the winner
	 * @param isDown the new node is placed below the winner
	 * 
	 * This method is called by {@link #growNodes(GSOMNode)} when a new node is created. The weights of the node is 
	 * adjusted by the classical four cases of the GSOM.
	 */
	private void setWeightsOfNewNode(GSOMNode newNode, GSOMNode winner, boolean isLeft,
			boolean isRight, boolean isUp, boolean isDown) 
	{
		//case 2 first then 1 and 3, 4 unlikely
		
		GSOMNode sequeceNodeToWinner = null; //for cases 1 and 3
		GSOMNode oppositeToNewNode = null; //for case 2
				
		if(isLeft)
		{
			sequeceNodeToWinner = winner.getRIGHT();
			oppositeToNewNode = newNode.getLEFT();
			
			if(oppositeToNewNode != null)
			{
				categoryTwoGrowth(newNode, winner, oppositeToNewNode);
			}
			else if(sequeceNodeToWinner != null)
			{
				categoryOneGrowth(newNode, winner, sequeceNodeToWinner);
			}
			else if(sequeceNodeToWinner == null)
			{
				categoryThreeGrowth(newNode, winner, true, false, false, false);
			}
		}
		else if(isRight)
		{
			sequeceNodeToWinner = winner.getLEFT();
			oppositeToNewNode = newNode.getRIGHT();
			
			if(oppositeToNewNode != null)
			{
				categoryTwoGrowth(newNode, winner,  oppositeToNewNode);
			}
			else if(sequeceNodeToWinner != null)
			{
				categoryOneGrowth(newNode, winner, sequeceNodeToWinner);
			}
			else if(sequeceNodeToWinner == null)
			{
				categoryThreeGrowth(newNode, winner, false, true, false, false);
			}
		}
		else if(isUp)
		{
			sequeceNodeToWinner = winner.getDOWN();	
			oppositeToNewNode = newNode.getUP();
			
			if(oppositeToNewNode != null)
			{
				categoryTwoGrowth(newNode, winner,  oppositeToNewNode);
			}
			else if(sequeceNodeToWinner != null)
			{
				categoryOneGrowth(newNode, winner, sequeceNodeToWinner);
			}
			else if(sequeceNodeToWinner == null)
			{
				categoryThreeGrowth(newNode, winner, false, false, true, false);
			}	
		}
		else if(isDown)
		{
			sequeceNodeToWinner = winner.getUP();
			oppositeToNewNode = newNode.getDOWN();
			
			if(oppositeToNewNode != null)
			{
				categoryTwoGrowth(newNode, winner,  oppositeToNewNode);
			}
			else if(sequeceNodeToWinner != null)
			{
				categoryOneGrowth(newNode, winner, sequeceNodeToWinner);
			}
			else if(sequeceNodeToWinner == null)
			{
				categoryThreeGrowth(newNode, winner, false, false, false, true);
			}			
		}
	}
	
	/**
	 * @param newNode
	 * @param winner
	 * @param other
	 */
	private void categoryOneGrowth(GSOMNode newNode, GSOMNode winner, GSOMNode other)
	{
		double w1=0, w2=0;
		
		w1 = winner.getWEIGHTS().getNorm();
		w2 = other.getWEIGHTS().getNorm();
		
		if(w1 > w2)
		{
			newNode.setWEIGHTS(winner.getWEIGHTS().add(winner.getWEIGHTS().subtract(other.getWEIGHTS())));
		}
		else if(w2 > w1)
		{
			newNode.setWEIGHTS(winner.getWEIGHTS().subtract(other.getWEIGHTS().subtract(winner.getWEIGHTS())));
		}
		else if(w1 == w2)
		{
			System.out.println("A highly unlikely case has happened.");
			newNode.setWEIGHTS(winner.getWEIGHTS());
		}
	}
	
	/**
	 * @param newNode
	 * @param winner
	 * @param other
	 */
	private void categoryTwoGrowth(GSOMNode newNode, GSOMNode winner, GSOMNode other)
	{
		newNode.setWEIGHTS((ArrayRealVector)((winner.getWEIGHTS().add(other.getWEIGHTS()).mapDivideToSelf(2.0))));
	}

	/**
	 * @param newNode
	 * @param winner
	 * @param isLeft
	 * @param isRight
	 * @param isUp
	 * @param isDown
	 */
	private void categoryThreeGrowth(GSOMNode newNode, GSOMNode winner, boolean isLeft, boolean isRight, 
			boolean isUp, boolean isDown)
	{
		GSOMNode candidateNode1 = null; // to check the possiblity of up, down left and right node
		GSOMNode candidateNode2 = null; // to check the possiblity of up, down left and right node 
		
		if(isLeft || isRight)
		{
			candidateNode1 = winner.getUP();
			candidateNode2 = winner.getDOWN();
		}
		else if(isDown || isUp)
		{
			candidateNode1 = winner.getLEFT();
			candidateNode2 = winner.getRIGHT();
		}
		
		if(candidateNode1 != null)
		{
			categoryOneGrowth(newNode, winner, candidateNode1);
		}
		else if(candidateNode2 !=null)
		{
			categoryOneGrowth(newNode, winner, candidateNode2);
		}
		else if(candidateNode1 == null & candidateNode2 == null)
		{
			categoryFourGrowth(newNode, winner);
		}
	}

	/**
	 * @param newNode
	 * @param winner
	 */
	private void categoryFourGrowth(GSOMNode newNode, GSOMNode winner) {
		// TODO Auto-generated method stub
		double[] temp = new double[INPUT_DIMENSION];
		for(int i = 0; i < temp.length ; i++)
		{
			temp[i] = 0.5;
		}
		
		newNode.setWEIGHTS(new ArrayRealVector(temp));
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
	 * @param node
	 * @param input
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

	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private double getEculidianDistance(GSOMNode a, GSOMNode b)
	{
		
		ArrayRealVector first = new ArrayRealVector(new double[]{a.getX(),a.getY()});
		ArrayRealVector seconed = new ArrayRealVector(new double[]{b.getX(), b.getY()});
		ArrayRealVector distance = first.subtract(seconed);		
		return distance.getNorm();		
	}
	
	/**
	 *
	 */
	private void learningRateDecay()
	{
		LEARNING_RATE = ALPHA*CHI(NUMBER_OF_NODES_IN_NETWORK)*LEARNING_RATE;
	}
	
	/**
	 * @param numberOfNodes
	 * @return
	 */
	private double CHI(int numberOfNodes) {
		// TODO Auto-generated method stub		
		return (1 - (3.5/numberOfNodes));
	}

	/**
	 * @param currentIteration
	 */
	private void radiusDecay(int currentIteration)
	{
		RADIUS = INITIAL_NEIGHBORHOOD_RADIUS*Math.exp(currentIteration/(NUMBER_OF_ITERATIONS/4));
	}

	/**
	 * @param input
	 * @return
	 */
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
	
	/**
	 * @param input
	 * @param node
	 * @return
	 */
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
	
	/**
	 * 
	 */
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
	 * @param input
	 * @return
	 */
	private GSOMNode setTotalError(ArrayRealVector input)
	{
		return null;
	}
}
