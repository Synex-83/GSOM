/**
 * 
 */
package maps.Structures;

import java.util.StringTokenizer;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		User
 * @date		Jul 8, 2013 - 7:44:07 PM
 * @type        GSOMArray
 *
 */
public class GSOMArray {

	private GSOMArrayNode[][] GSOM;
	private int OFFSET;
	private int INPUT_DIMENSION;
	private int NUMBER_OF_NODES_IN_NETWORK;
	private int NUMBER_OF_ITERATIONS;
	private double INITIAL_ETA;
	private double INITIAL_NEIGHBORHOOD_RADIUS;
	private String INPUT_VECTORS;
	private double GROWTH_THRESHOLD;
	private double HIGHEST_ERROR;
	
	public GSOMArray(int dimension, int iteration, double learningRate, double radius, String inputVectors, DisplayLattice screen)
	{
		INPUT_DIMENSION = dimension;
		NUMBER_OF_ITERATIONS = iteration;
		INITIAL_NEIGHBORHOOD_RADIUS = radius;
		INITIAL_ETA = learningRate;
		INPUT_VECTORS = inputVectors;
				
		GSOM = new GSOMArrayNode[71][71];
		setOffset();
		
		initElements();
	}
	
	private void initElements() {
		// TODO Auto-generated method stub
		GSOM[OFFSET][OFFSET] = new GSOMArrayNode(INPUT_DIMENSION, 0, 0);
		GSOM[OFFSET][OFFSET + 1] = new GSOMArrayNode(INPUT_DIMENSION, 0, 1);
		GSOM[OFFSET - 1][OFFSET] = new GSOMArrayNode(INPUT_DIMENSION, 1, 0);
		GSOM[OFFSET - 1][OFFSET + 1] = new GSOMArrayNode(INPUT_DIMENSION, 1, 1);
		
		NUMBER_OF_NODES_IN_NETWORK = 4;
	}

	private void trainGSOM(String input)
	{
		String line = "";
		double temp[] = null;
		GSOMArrayNode winner = null;
		
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
					System.out.println(i);
					winner = presentSingleInput(new ArrayRealVector(temp)); //idea of a return value is to halt further execution of code until the method call has returned.
					calculateGrowthError(winner, new ArrayRealVector(temp)); //processes the QE and triggers node growth if required.
				//	adjustNeighbourhoodOfWinner(winner, new ArrayRealVector(temp));
				//	learningRateDecay();
				//	radiusDecay(i);
				}
			}
		}
	}
	
	private GSOMArrayNode presentSingleInput(ArrayRealVector input)
	{		
		GSOMArrayNode winner = getWinner(input);
		winner.incrementNumberOfHits();
		return winner;
	}
	
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
					tempValue = getEuclideanAccumulatedValue(input, GSOM[i][j]);
					
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
	
	private void growNodes(GSOMArrayNode winner) 
	{
		
		GSOMArrayNode left =null, right=null, up=null, down=null;
		GSOMNode[] temp = null;
		
		int X = winner.getX() + OFFSET;
		int Y = winner.getY() + OFFSET;
		
		
		if(!winner.isBoundry())
		{
		//	distributeErrorToNeighbors(winner);
			//temp = NUMBER_POLE.getNeighborNodes(winner.getX(), winner.getY());
			//calculateGrowthErrorPropergation(temp);
			//winner.setTotalError(GROWTH_THRESHOLD/2);
		}
		else
		{
			if(GSOM[X][Y - 1] == null)
			{
				GSOM[X][Y - 1] = new GSOMArrayNode(INPUT_DIMENSION, X - OFFSET, Y - 1 - OFFSET);
				
				setWeightsOfNewNode(GSOM[X][Y - 1], winner, true, false, false, false);
			}
			
			if(GSOM[X][Y + 1] == null)
			{
				GSOM[X][Y + 1] = new GSOMArrayNode(INPUT_DIMENSION, X - OFFSET, Y + 1 - OFFSET);
				
				setWeightsOfNewNode(GSOM[X][Y + 1], winner, false, true, false, false);

			}
			
			if(GSOM[X - 1][Y] == null)
			{
				GSOM[X - 1][Y] = new GSOMArrayNode(INPUT_DIMENSION, X - 1 - OFFSET, Y - OFFSET);

				setWeightsOfNewNode(GSOM[X - 1][Y], winner, false, false, true, false);

			}
			
			if(GSOM[X + 1][Y] == null)
			{
				GSOM[X + 1][Y] = new GSOMArrayNode(INPUT_DIMENSION, X - 1 - OFFSET, Y - OFFSET);
				
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
