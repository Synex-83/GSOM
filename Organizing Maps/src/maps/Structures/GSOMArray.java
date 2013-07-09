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
 * @author 		Manjusri Wickramasignhe
 * @date		Jul 8, 2013 - 7:44:07 PM
 * @type        GSOMArray
 *
 * Contains the array based implementation of the Growing Self-Organizing Map (GSOM) algorithm. Some of the assumptions made in this
 * coding do not represent a one-to-one mapping of the Alahakoon et al. thesis. Most of these assumptions are in places where
 * there is no clear explanation is provided in the said thesis. Hence, this implementation could be marginally different from
 * the classic GSOM algorithm.
 */
public class GSOMArray {

	private GSOMArrayNode[][] GSOM; //The multidimensional array which holds the GSOM 
	
	private int OFFSET = 0; //The basic array offset of the multidimensional array
	private int INPUT_DIMENSION = 0; //Dimension of the input vector
	private int NUMBER_OF_NODES_IN_NETWORK = 0; //Total number of nodes in the network
	private int NUMBER_OF_ITERATIONS = 0; //Number of iteration an input needs to be presented
	
	private double INITIAL_ETA = 0.0; //Initial learning rate set by the user
	private double INITIAL_NEIGHBORHOOD_RADIUS = 0.0; //Initial neighborhood radius set by the user
	private double GROWTH_THRESHOLD = 0.0; // GT calculated using the formula GT = -Dimensions*ln(SpreadFactor)
	private double HIGHEST_ERROR = 0.0; //Highest accumulated quantization error observed in the network
	private double LEARNING_RATE = 0.0; //Learning rate of the current epoch
	private double RADIUS = 0.0; //Radius of the current epoch
	private double ALPHA = 0.0; //discount factor of the learning rate.
	private double FD = 0.5; //the Factor of Distribution (FD) 0 < gamma < 1
	
	private String INPUT_VECTORS = null; //Set of input vectors and a newline separated string
	
	/**
	 * @param dimension as the dimensionality of the input vector
	 * @param iteration as the number of iterations per input vector
	 * @param learningRate as the learning rate 
	 * @param radius as the effective area of weight adjustment
	 * @param inputVectors as the set of input vectors  
	 * @param screen as the JPanel used for producing the visual output
	 * 
	 * This the only constructor of the GSOMArray class. This constructor initializes all the basic variable values and sets
	 * up the basic four node GSOM structure. The basic GSOM array is 71 x 71 with a offset of 35. So the 0,0 element of the
	 * GSOM is placed in the 0+offset,0+offset location. When accessing elements and adjusting them the offset has to be
	 * used. The array could be expanded to a larger array if the map growth exceeds the basic allocation. The offset variable
	 * has to be recalculated each time.
	 */
	public GSOMArray(int dimension, int iteration, double learningRate, double radius, String inputVectors, DisplayLattice screen)
	{
		INPUT_DIMENSION = dimension;
		NUMBER_OF_ITERATIONS = iteration;
		INITIAL_NEIGHBORHOOD_RADIUS = radius;
		INITIAL_ETA = learningRate;
		INPUT_VECTORS = inputVectors;
		LEARNING_RATE = INITIAL_ETA;
		RADIUS = INITIAL_NEIGHBORHOOD_RADIUS;
				
		GSOM = new GSOMArrayNode[71][71];
		setOffset();
		
		initElements();
	}
	
	/**
	 * Creates the basic structure of the GSOM that contains four nodes.
	 */
	private void initElements()
	{
		GSOM[OFFSET][OFFSET] = new GSOMArrayNode(INPUT_DIMENSION, 0, 0);
		GSOM[OFFSET][OFFSET + 1] = new GSOMArrayNode(INPUT_DIMENSION, 0, 1);
		GSOM[OFFSET - 1][OFFSET] = new GSOMArrayNode(INPUT_DIMENSION, 1, 0);
		GSOM[OFFSET - 1][OFFSET + 1] = new GSOMArrayNode(INPUT_DIMENSION, 1, 1);
		
		NUMBER_OF_NODES_IN_NETWORK = 4;
	}

	/**
	 * @param input as the input vectors used for the training process
	 * 
	 * Conducts the training of the GSOM until all the input vectors are presented with to the network specified by the
	 * number of iterations. The training is different from the standard SOM algorithm of Kohonen et al. In the GSOM a single 
	 * input is presented and iterated for the specified number of times and then the next input is taken. When the input
	 * is changed the learning rate and the neighborhood radius is reset to the values specified by the user at the start of
	 * the GSOM execution.
	 */
	private void trainGSOM(String input)
	{
		String line = "";
		double temp[] = null; 
		GSOMArrayNode winner = null;
		
		StringTokenizer inputTokens = new StringTokenizer(input, "\n");
		inputTokens.nextToken();
		
		while(inputTokens.hasMoreTokens()) //the input is tokenized and iterated till the complete set is over.
		{
			line = inputTokens.nextToken();
			if(!line.contains("####")) // the data files used for this experiment had some records with these symbols which needs to be omitted. 
			{
				temp = new double[INPUT_DIMENSION]; //the getDimensions method of FileProcessing class returns the the number of elements - 1
				String[] inputVector = line.split("\t");
				
				/*
				 * The loop converts the numerical elements of the inputVector to a ArrayRealVector so it could be used to 
				 * conduct training of the GSOM. The only part missing of the input vector is the move tag.
				 */
				for(int i = 1; i < inputVector.length; i++) 
				{
					temp[i-1] = Double.parseDouble(inputVector[i]);					
				}
								
				/*
				 * The loop goes from 0...70 initially and could be different in the case a redefinition of the array has taken
				 * place due to lack of allocated array space. A single input sample is presented for the number of iterations
				 * provided by the user. Initially the winner is chosen for the given input vector. Then the winner nodes growth error is increased to 
				 * represent the differences in the nodes weight vector and the input vector. Increasing the error value will 
				 * trigger the growth of nodes if it suffices the growth conditions. Then the weights of the neighborhood nodes
				 * are adjusted. Finally the learning rate and the effective neighbourhood radius is decayed according to the
				 * specification given in the algorithm.
				 */
				for (int i = 0; i < NUMBER_OF_ITERATIONS; i++) 
				{ 
					System.out.println(i);
					winner = presentSingleInput(new ArrayRealVector(temp)); //idea of a return value is to halt further execution of code until the method call has returned.
					calculateGrowthError(winner, new ArrayRealVector(temp)); //processes the QE and triggers node growth if required.
					adjustNeighbourhoodOfWinner(winner, new ArrayRealVector(temp)); //adjust weights after the node growth has happened
					learningRateDecay();
					radiusDecay(i);
				}
				
				LEARNING_RATE = INITIAL_ETA; //Reseting the value for the next input
				RADIUS = INITIAL_NEIGHBORHOOD_RADIUS; //Reseting the value for the next input
			}
		}
	}
	
	/**
	 * @param winner as the winner node of the current input presentation
	 * @param inputVector as the input vector that corresponds to the winner node
	 * 
	 * This method is called by {@link #trainGSOM(String)}. Adjusts the weight vectors of the winner's neighboring nodes. The neighborhood nodes are selected based on the radius
	 * value given by the user. The radius value will diminish over time and eventually come down to only one node which is
	 * the winner itself.
	 */
	private void adjustNeighbourhoodOfWinner(GSOMArrayNode winner, ArrayRealVector inputVector) 
	{
		adjustWeightVectors(winner, inputVector);
	}
	
	/**
	 * @param node as the center node of the weight adjustment region
	 * @param input as the input vector that corresponds to the current iteration
	 * 
	 * This method is called by {@link #adjustWeightVectors(GSOMArrayNode, ArrayRealVector)}. This method contains the inner
	 * workings of the neighborhood weight adjustment.
	 */
	private void adjustWeightVectors(GSOMArrayNode node, ArrayRealVector input)
	{
		GSOMArrayNode winner = node; 
		
		int ceilingValue = (int)Math.ceil(RADIUS); //takes the ceiling value radius so no array index will be missed
		int x = node.getX() + OFFSET - ceilingValue; //find the leftmost X from the center node which is the winner
		int y = node.getY() + OFFSET - ceilingValue; //find the topmost Y from the center node which is the winner
		ArrayRealVector tempWeights = null; //will hold the weight vector after the adjustment

		/*
		 * The loops move from the top of the square selected starting from x and y above. The width of the square is two
		 * ceiling values. Upon iteration the Euclidian distance between the node chosen and the winner is checked to see 
		 * whether it falls under the radius at the iteration. If it does not fall under the area of the radius or the GSOM
		 * array posisiton is null the node in the position or the position is ignored.
		 */
		for(int i = 0 ; i <= 2*ceilingValue ; i++ )
		{
			for(int j = 0; j <= 2*ceilingValue ; j++)
			{
				if(GSOM[x+i][y+j] != null && getEculidianDistance(GSOM[x+i][y+j], winner) <= RADIUS) //null and radius check
				{
					//calculates the weights of the node by applying the Eta rule
					tempWeights = GSOM[x+i][y+j].getWEIGHTS().add((input.subtract(GSOM[x+i][y+j].getWEIGHTS())).mapMultiplyToSelf(LEARNING_RATE));
					GSOM[x+i][y+j].setWEIGHTS(tempWeights); //sets the calculated set of weights to the node
				}
			}
		}
	}
	
	/**
	 * This method is called by {@link #trainGSOM(String)} to decay the learning rate during each call. The decay depends on the current learning rate and the discount value
	 * presented by the user. Furthermore,  the number of nodes present in the network in also taken into consideration when
	 * calculating the new learning rate.
	 */
	private void learningRateDecay()
	{
		LEARNING_RATE = ALPHA*CHI(NUMBER_OF_NODES_IN_NETWORK)*LEARNING_RATE;
	}
	
	/**
	 * @param numberOfNodes as the number of nodes in the GSOM instance
	 * @return the value of CHI
	 * 
	 * This method is called by {@link #learningRateDecay()} to retrieve the CHI value. The function implemented in this
	 * method is specified in the original text of the GSOM which is the CHI(x) = (1 - (3.5/numberOfNodes)).
	 */
	private double CHI(int numberOfNodes) 
	{
		return (1 - (3.5/numberOfNodes));
	}

	/**
	 * @param currentIteration as the current iteration
	 * 
	 * This method is called by {@link #trainGSOM(String)} to decay the effective neighborhood radius during each call. The
	 * radius decay follows the form of exponential decay with the formula (current iteration/(Total iterations/4))
	 */
	private void radiusDecay(int currentIteration)
	{
		RADIUS = INITIAL_NEIGHBORHOOD_RADIUS*Math.exp(currentIteration/(NUMBER_OF_ITERATIONS/4));
	}
	
	/**
	 * @param a as GSOMArrayNode
	 * @param b as GSOMArrayNode
	 * @return the Eculidian distance between the GSOMArray nodes
	 */
	private double getEculidianDistance(GSOMArrayNode a, GSOMArrayNode b)
	{		
		ArrayRealVector first = new ArrayRealVector(new double[]{a.getX(),a.getY()});
		ArrayRealVector seconed = new ArrayRealVector(new double[]{b.getX(), b.getY()});
		ArrayRealVector distance = first.subtract(seconed);		
		return distance.getNorm();
	}
	
	/**
	 * @param input as the input vector
	 * @return the winner of the single presentation
	 * 
	 * This method is called by {@link #trainGSOM(String)} to present the input the current GSOM and find the winner of the
	 * presentation.
	 */
	private GSOMArrayNode presentSingleInput(ArrayRealVector input)
	{		
		GSOMArrayNode winner = getWinner(input);
		winner.incrementNumberOfHits();
		return winner;
	}
	
	
	/**
	 * @param input as the input vector
	 * @return the winner of the single presentation
	 * 
	 * This method is called by {@link #presentSingleInput(ArrayRealVector)} to find the winner of a given input vector.
	 */
	private GSOMArrayNode getWinner(ArrayRealVector input)
	{
		GSOMArrayNode tempNode = null;
		double tempValue = 0.0;
		double minSeen = Double.POSITIVE_INFINITY;
		GSOMArrayNode minNode = null;
		
		for(int i = 0; i < GSOM.length ; i++)
		{
			for(int j = 0; j < GSOM[0].length; j++)
			{
				if(GSOM[i][j] != null)
				{
					tempValue = getEuclideanAccumulatedValue(input, GSOM[i][j]); //finds the distance of the input and the weights
					
					if(tempValue < minSeen)
					{
						minNode = tempNode;
						minSeen = tempValue;						
					}	
					
					tempNode.setACTIVATION_VALUE(tempValue);
				}
			}
		}
		
		return minNode;		
	}
	
	/**
	 * @param winner as the winner node of the presentation of the input vector
	 * @param inputVector as the input vector
	 * 
	 * Calculates the growth error of the winner.
	 */
	private void calculateGrowthError(GSOMArrayNode winner, ArrayRealVector inputVector) 
	{			
		double differenceInVectors = (inputVector.subtract(winner.getWEIGHTS())).getNorm();		
		winner.setAccumulatedError(winner.getAccumulatedError() + differenceInVectors);

		if(winner.getAccumulatedError() >= GROWTH_THRESHOLD)
		{
			if(winner.getAccumulatedError() < HIGHEST_ERROR)
			{
				HIGHEST_ERROR = winner.getAccumulatedError();
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
	private void calculateGrowthErrorPropergation(GSOMArrayNode[] neighbours)
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
	
	private void growNodes(GSOMArrayNode winner) 
	{
		
		GSOMArrayNode left =null, right=null, up=null, down=null;
		GSOMNode[] temp = null;
		
		int X = winner.getX() + OFFSET;
		int Y = winner.getY() + OFFSET;
		
		
		if(!winner.isBoundry())
		{
			temp = NUMBER_POLE.getNeighborNodes(winner.getX(), winner.getY());
			calculateGrowthErrorPropergation(temp);
			winner.setAccumulatedError(GROWTH_THRESHOLD/2);
		}
		else
		{
			if(GSOM[X][Y - 1] == null)
			{
				GSOM[X][Y - 1] = new GSOMArrayNode(INPUT_DIMENSION, X - OFFSET, Y - 1 - OFFSET);
				
				NUMBER_OF_NODES_IN_NETWORK++;
				
				setWeightsOfNewNode(GSOM[X][Y - 1], winner, true, false, false, false);
			}
			
			if(GSOM[X][Y + 1] == null)
			{
				GSOM[X][Y + 1] = new GSOMArrayNode(INPUT_DIMENSION, X - OFFSET, Y + 1 - OFFSET);
				
				NUMBER_OF_NODES_IN_NETWORK++;
				
				setWeightsOfNewNode(GSOM[X][Y + 1], winner, false, true, false, false);

			}
			
			if(GSOM[X - 1][Y] == null)
			{
				GSOM[X - 1][Y] = new GSOMArrayNode(INPUT_DIMENSION, X - 1 - OFFSET, Y - OFFSET);
				
				NUMBER_OF_NODES_IN_NETWORK++;

				setWeightsOfNewNode(GSOM[X - 1][Y], winner, false, false, true, false);

			}
			
			if(GSOM[X + 1][Y] == null)
			{
				GSOM[X + 1][Y] = new GSOMArrayNode(INPUT_DIMENSION, X - 1 - OFFSET, Y - OFFSET);
				
				NUMBER_OF_NODES_IN_NETWORK++;
				
				setWeightsOfNewNode(GSOM[X + 1][Y], winner, false, false, false, true);
			}
			
			winner.setBoundry(false);
			winner.setAccumulatedError(GROWTH_THRESHOLD/2); //prejudeged according to the flow of text.
			//However, how to set this value is not specified in the thesis.
		}	
	}

	private void setWeightsOfNewNode(GSOMArrayNode newNode, GSOMArrayNode winner, boolean isLeft,
			boolean isRight, boolean isUp, boolean isDown) 
	{
		//case 2 first then 1 and 3, 4 unlikely
		
		GSOMArrayNode sequeceNodeToWinner = null; //for cases 1 and 3
		GSOMArrayNode oppositeToNewNode = null; //for case 2
				
		if(isLeft)
		{
			sequeceNodeToWinner = GSOM[winner.getX() + OFFSET][winner.getY() + OFFSET + 1];
			oppositeToNewNode = GSOM[newNode.getX() + OFFSET][newNode.getY() + OFFSET - 1];
			
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
			sequeceNodeToWinner = GSOM[winner.getX() + OFFSET][winner.getY() + OFFSET - 1];
			oppositeToNewNode = GSOM[newNode.getX() + OFFSET][newNode.getY() + OFFSET + 1];
			
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
			sequeceNodeToWinner = GSOM[winner.getX() + OFFSET - 1][winner.getY() + OFFSET];	
			oppositeToNewNode =  GSOM[newNode.getX() + OFFSET - 1][newNode.getY() + OFFSET];
			
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
			sequeceNodeToWinner = GSOM[winner.getX() + OFFSET + 1][winner.getY() + OFFSET];
			oppositeToNewNode = GSOM[newNode.getX() + OFFSET + 1][newNode.getY() + OFFSET];
			
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
	
	private void categoryOneGrowth(GSOMArrayNode newNode, GSOMArrayNode winner, GSOMArrayNode other)
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
	private void categoryTwoGrowth(GSOMArrayNode newNode, GSOMArrayNode winner, GSOMArrayNode other)
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
	private void categoryThreeGrowth(GSOMArrayNode newNode, GSOMArrayNode winner, boolean isLeft, boolean isRight, 
			boolean isUp, boolean isDown)
	{
		GSOMArrayNode candidateNode1 = null; // to check the possibility of up, down left and right node
		GSOMArrayNode candidateNode2 = null; // to check the possibility of up, down left and right node 
		
		if(isLeft || isRight)
		{
			candidateNode1 = GSOM[winner.getX() + OFFSET - 1][winner.getY() + OFFSET];
			candidateNode2 = GSOM[winner.getX() + OFFSET + 1][winner.getY() + OFFSET];
		}
		else if(isDown || isUp)
		{
			candidateNode1 = GSOM[winner.getX() + OFFSET][winner.getY() + OFFSET - 1];
			candidateNode2 = GSOM[winner.getX() + OFFSET][winner.getY() + OFFSET + 1];
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
	private void categoryFourGrowth(GSOMArrayNode newNode, GSOMArrayNode winner) {
		// TODO Auto-generated method stub
		double[] temp = new double[INPUT_DIMENSION];
		for(int i = 0; i < temp.length ; i++)
		{
			temp[i] = 0.5;
		}
		
		newNode.setWEIGHTS(new ArrayRealVector(temp));
	}
	
	
	private double getEuclideanAccumulatedValue(ArrayRealVector input, GSOMArrayNode node)
	{
		double temp = 0.0;

		temp = (node.getWEIGHTS().subtract(input)).getNorm(); // in case there is an error this would revert to zero		

		return temp;
	}
	
	public void setOffset()
	{
		OFFSET = (GSOM.length - 1)/2;
	}
	
	public void printGSOM()
	{
		for(int i = 0; i < GSOM.length ; i++){
			for(int j = 0; j < GSOM[0].length; j++)
			{
				if(GSOM[i][j] == null)
				{
					System.out.print("N ");
				}
				else
				{
					System.out.print(GSOM[i][j].getX()+","+GSOM[i][j].getY()+" ");
				}
			}
			System.out.println('\n');
		}
	}
}
