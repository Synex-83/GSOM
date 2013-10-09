/**
 * @package		maps.Structures
 * @filename	SelfOrganizinMap.java
 */
package maps.Structures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.StringTokenizer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:27:22 PM
 * @type		SelfOrganizinMap
 */

public class SelfOrganizingMap {
	
	private Node[][] SOM = null;
	private double[][] NORM_MAP = null; 			//holds the L2 norm of each vector in the SOM[][]
	private int INPUT_DIMENSION = 0;
	private int NUMER_OF_ITERATIONS = 0;
	private int CURRENT_ITERATION=0;
	private int SOM_HORIZONTAL_LENGTH = 0;
	private int SOM_VERTICAL_LENGTH = 0;
	private double INITIAL_LEARNING_RATE = 0.0;
	private double LEARNING_RATE = 0.0;
	private double MAX_RADIUS = 0.0; 				//radius at first epoch (t = 0)
	private double RADIUS = 0.0;
	private double TIME_STEP = 0.0; 				//lambda of X(t) = t0 * exp(-t/lambda)
	private String INPUT_SAMPLES = null;
	private boolean IS_MATRIX_MODE = false;
	private int COVARIANCE_NUMBER = 0;


	/**
	 * @param numberOfNodes as the total set of nodes in the final 2D map
	 * @param depth	as the depth or the number of rows in the map
	 * @param grid as the type of grid (0 = square, 1 = rectangle, 2 = hexagonal)
	 * @param inputDimension the dimension of the input data vector
	 */
	public SelfOrganizingMap(int numberOfNodes, int inputDimensison, boolean isMatrixMode, int covarianceNumber)
	{
		INPUT_DIMENSION = inputDimensison;
		IS_MATRIX_MODE = isMatrixMode;
		COVARIANCE_NUMBER = covarianceNumber;
		
		int side = (int)Math.sqrt(numberOfNodes);
		SOM = new Node[side][side];
		NORM_MAP = new double[side][side];
		MAX_RADIUS = side/2;
		
		if(IS_MATRIX_MODE)
		{
			initialize();
		}
		else
		{
			initializeMatrix();
		}

		RADIUS = MAX_RADIUS;
	}
	
	/**
	 * @param a
	 * @param b
	 * @return
	 */
	private double multiply(ArrayRealVector a, ArrayRealVector b)
	{
		double value = a.dotProduct(b);
		
		return value;
	}
	
	/**
	 * @param input
	 * @param iterations
	 * @param learningRate
	 */
	public void initTrainSOM(String input, int iterations, double learningRate)
	{

		NUMER_OF_ITERATIONS = iterations;
		INITIAL_LEARNING_RATE = learningRate;
		LEARNING_RATE = INITIAL_LEARNING_RATE;
		TIME_STEP = NUMER_OF_ITERATIONS/Math.log(MAX_RADIUS);
		INPUT_SAMPLES = input;
		//DISPLAY_SCREEN.render();
		for(int i = 0; i <= NUMER_OF_ITERATIONS; i++) //if 100 iteration we go from 0...100
		{
			singleCompleteRun();
			CURRENT_ITERATION++;
			System.out.println("Iteration = " + i + " Learning Rate = " + LEARNING_RATE + " Radius = " + RADIUS + " ***********");
		}			

	}
	
	private void singleCompleteRun()
	{
		if(IS_MATRIX_MODE)
		{
			trainSOM();
		}
		else
		{
			trainSOM(INPUT_SAMPLES);
		}
		
		EpochRadiusDecay(CURRENT_ITERATION);
		LearningRateDecay(CURRENT_ITERATION);
		System.out.println(CURRENT_ITERATION);
	}
	


	private void exportWeights(int iterations)
	{
		if(iterations == 100)
		{
			for(int i = 0; i < SOM.length; i++){
				for(int j = 0; j < SOM[0].length; j++){				
					System.out.println("X= "+SOM[i][j].getX() +" Y ="+SOM[i][j].getY() + " WEIGHTS= " + SOM[i][j].getWEIGHTS().toString());
				}
			}
		}
	}
	
	private BufferedImage exportImageNorm()
	{
		BufferedImage colorNodes = new BufferedImage(SOM[0].length, SOM.length, 1);
		double[][] normL2values = new double[SOM[0].length][SOM.length];
		double minL2 = 10.0;
		double maxL2 = 0.0;
		double temp = 0.0;	
		double scaledNorm = 0.0;
		for(int i = 0; i < SOM.length; i++){
			for(int j = 0; j < SOM[0].length; j++){			    
				 temp  = SOM[i][j].getWEIGHTS().getNorm();
				 normL2values[i][j] = temp;
				 if(temp > maxL2)
				 {
					 maxL2 = temp;
				 }			
				 if(temp < minL2)
				 {
					 minL2 = temp;
				 }
			}								
		}
		
		System.out.println(maxL2 + "\t" + minL2);		
		for(int i = 0; i < normL2values.length ; i++){
			for(int j = 0; j < normL2values[0].length; j++){
				scaledNorm  = (normL2values[i][j] - minL2)/(maxL2 - minL2);
				//System.out.println(scaledNorm);
				colorNodes.setRGB(i, j, (new Color((float)scaledNorm,(float)scaledNorm,(float)scaledNorm)).getRGB());
			}
		}
		return colorNodes;
	}
	
	/**
	 * Performs a single iteration of SOM training in matrix mode
	 */
	private void trainSOM() 
	{
		String line = "";
		double temp[] = null;
		Array2DRowRealMatrix covariance = null;
		Node winner = null;
		
		
		StringTokenizer first = new StringTokenizer(INPUT_SAMPLES, "\n");
		first.nextToken();	
		int tempcounter = 0;
		
		while(first.hasMoreTokens())
		{
			line = first.nextToken();
			//System.out.println(line);
			if(!line.contains("####"))
			{
				temp = new double[INPUT_DIMENSION];
				String[] inputVector = line.split("\t");
				
				for(int i = 1; i < inputVector.length; i++)
				{
					temp[i-1] = Double.parseDouble(inputVector[i]);					
				}
				winner = setAccumulatedValue(new ArrayRealVector(temp));
				adjustNeighbourhoodOfWinners(winner, new ArrayRealVector(temp));
				
				tempcounter++;
				
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("=============================== " + tempcounter);
			}
		}
	}
	
	/**
	 * @param input as the input vector
	 * Performs a single iteration of SOM training in vector mode
	 */
	public void trainSOM(String input)
	{
		String line = "";
		double temp[] = null;
		Node winner = null;
		
		
		StringTokenizer first = new StringTokenizer(input, "\n");
		first.nextToken();	
		int tempcounter = 0;
		
		while(first.hasMoreTokens())
		{
			line = first.nextToken();
			//System.out.println(line);
			if(!line.contains("####"))
			{
				temp = new double[INPUT_DIMENSION];
				String[] inputVector = line.split("\t");
				
				for(int i = 1; i < inputVector.length; i++)
				{
					temp[i-1] = Double.parseDouble(inputVector[i]);					
				}
				winner = setAccumulatedValue(new ArrayRealVector(temp));
				adjustNeighbourhoodOfWinners(winner, new ArrayRealVector(temp));
				
				tempcounter++;
				
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("=============================== " + tempcounter);
/*				winner = setEuclideanAccumulatedValue(new ArrayRealVector(temp));
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("*******************************");*/
			}
		}
	}
	
	/**
	 * @param winner as the winner node of the input presentation
	 * @param inputVector as the input vector recently presented to the network
	 */
	private void adjustNeighbourhoodOfWinners(Node winner, ArrayRealVector inputVector)
	{
		int radius = (int)Math.ceil(RADIUS);
		double distance = 0.0;
		double theta = 0.0;
		ArrayRealVector tempWeights = null;
		
		int effective_x_min = winner.getX() - radius;
		int effective_x_max = winner.getX() + radius;
		int effective_y_min = winner.getY() - radius;
		int effective_y_max = winner.getY() + radius;
		
		if(effective_x_min  < 0)
		{
			effective_x_min = 0;
		}
		
		if(effective_x_max >= SOM_HORIZONTAL_LENGTH)
		{
			effective_x_max = SOM_HORIZONTAL_LENGTH - 1;
		}
		
		if(effective_y_min < 0)
		{
			effective_y_min = 0;
		}
		
		if(effective_y_max >= SOM_VERTICAL_LENGTH)
		{
			effective_y_max = SOM_VERTICAL_LENGTH - 1;
		}
		
		
		for(int i = effective_y_min; i <= effective_y_max; i++)
		{
			for(int j = effective_x_min; j <= effective_x_max; j++)
			{
				try{
				distance = eculideanDistanceInNodes(winner, SOM[i][j]); //CHECK THE SQUARE LOGIC ABOVE
				}catch(Exception e)
				{
					System.out.println("Winner X =" + winner.getX() + " Winner Y =" + winner.getY());
					System.out.println("X Min " + effective_x_min);
					System.out.println("X Max " + effective_x_max);
					System.out.println("Y Min " + effective_y_min);
					System.out.println("Y Max " + effective_y_max);
					System.out.println("Radius " + RADIUS);
				}
				
				if(distance <= RADIUS)
				{
					//weight adjust
					theta = Math.exp(-(Math.pow(distance, 2)/(2*Math.pow(RADIUS, 2))));
					tempWeights = SOM[i][j].getWEIGHTS().add((inputVector.subtract(SOM[i][j].getWEIGHTS())).mapMultiplyToSelf(theta*LEARNING_RATE)) ;
					SOM[i][j].setWEIGHTS(tempWeights);					
				}
			}
		}
	}
	
	/**
	 * @param BMU
	 * @param Neighbour
	 * @return
	 */
	private  double eculideanDistanceInNodes(Node BMU, Node Neighbour)	
	{
		ArrayRealVector BMUnit = new ArrayRealVector(new double[]{BMU.getX(),BMU.getY()});
		ArrayRealVector neighbourUnit = new ArrayRealVector(new double[]{Neighbour.getX(),Neighbour.getY()});		
		ArrayRealVector subValue = BMUnit.subtract(neighbourUnit);
		
		return Math.sqrt(subValue.getNorm());
	}
	
	/**
	 * @param currentIteration
	 */
	private  void LearningRateDecay(int currentIteration)
	{
		LEARNING_RATE = INITIAL_LEARNING_RATE*Math.exp(-(double)currentIteration/NUMER_OF_ITERATIONS);
	}
	
	/**
	 * @param currentIteration
	 */
	private void EpochRadiusDecay(int currentIteration)
	{
		RADIUS = MAX_RADIUS*Math.exp(-((double)currentIteration/TIME_STEP));
		//System.out.println(RADIUS);
	}
	
	/**
	 * @param input as the input vector presented to the network.
	 * @return the winner node of the iteration
	 * Similar to {@link #setAccumulatedValue(ArrayRealVector)} needs to use only one of these methods to select the
	 * winner. Euclidean measure takes the minimum value.
	 */
	@SuppressWarnings("unused")
	private Node setEuclideanAccumulatedValue(ArrayRealVector input)
	{
		double temp = 0.0;
		double minSeen = Double.POSITIVE_INFINITY;
		Node minNode = null;
		
		System.out.println("Input Vector = " + input.toString());
		
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{
				temp = ((SOM[i][j].getWEIGHTS()).subtract(input)).getNorm(); // in case there is an error this would revert to zero
				
				if(temp < minSeen)
				{
					minNode = SOM[i][j];
					minSeen = temp;
				}
				
				SOM[i][j].setACTIVATION_VALUE(temp); 
			}
		}	
		
		printSOM();
		return minNode;
	}
	
	/**
	 * @param input as the input vector presented to the network
	 * @return the winner node of the iteration
	 * Similar to {@link #setEuclideanAccumulatedValue(ArrayRealVector)} need to use only one of these methods to select the
	 * winner. The multiplication based method takes the maximum value.
	 */ 
	private Node setAccumulatedValue(ArrayRealVector input) {
		
		double temp = 0.0;
		double maxSeen = 0.0;
		Node maxNode = null;
		
		//System.out.println("Input Vector = " + input.toString());
		
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{
				temp = multiply(SOM[i][j].getWEIGHTS(),input); // in case there is an error this would revert to zero
				
				if(temp > maxSeen)
				{
					maxNode = SOM[i][j];
					maxSeen = temp;
				}
				
				SOM[i][j].setACTIVATION_VALUE(temp); 
			}
		}	
		
		//printSOM();
		return maxNode;
	}

	/**
	 * Initializes the random weights of each node in the SOM according to the initial input dimensions.
	 */
	private void initialize()
	{
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{ 
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j,false,0);
			}
		}
		
		SOM_HORIZONTAL_LENGTH = SOM[0].length;
		SOM_VERTICAL_LENGTH = SOM.length;
	}
	
	/**
	 * Initializes the random weights of each node in the SOM according to the number of vectors considered for the 
	 * sliding window technique.
	 */
	private void initializeMatrix() {
		
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{ 
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j,IS_MATRIX_MODE,COVARIANCE_NUMBER);
			}
		}
		
		SOM_HORIZONTAL_LENGTH = SOM[0].length;
		SOM_VERTICAL_LENGTH = SOM.length;
		
	}
	
	/**
	 * Prints each nodes norm, X,Y location as a debug procedure.
	 */
	public void printSOM()
	{
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{				
				//System.out.println(i + " " + j);
				System.out.println("X =" + SOM[i][j].getX() + " Y =" + SOM[i][j].getY() + " ACTIVATION VALUE =" + SOM[i][j].getACTIVATION_VALUE());
				//System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
			}
			
		}
		
		//System.out.println("===============================");
	}
	
	private void getNormMap()
	{
		for(int i = 0; i < SOM.length; i++)
		{
			for(int j = 0; j<SOM[0].length; j++)
			{
				NORM_MAP[i][j] =  SOM[i][j].getWEIGHTS().getNorm();
			}
		}
	}	
}