/**
 * @package		maps.Structures
 * @filename	SelfOrganizinMap.java
 */
package maps.Structures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;



/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:27:22 PM
 * @type		SelfOrganizinMap
 */

public class SelfOrganizingMap implements Serializable {
	

	// LAYERS
	private Node[][] SOM = null;
	public FiniteStateMachine FSM = null;
	private Node[][] ZERO_MAP = null;
	
	// VARIABLES
	private double[][] NORM_MAP = null; 			//holds the L2 norm of each vector in the SOM[][]
	private double[][] U_MATRIX = null;
	private double[][] U_MATRIX_SHRINK = null;
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
	private int PRESENTATION_NUMBER = 0;
	private int CURRENT_PRESENTATION_NUMBER = 0;
	private double VECTOR_WEIGHTS[] = null; 
	private int THRESHOLD = 0;
		
		//{0.5,0.20,0.15,0.10,0.05}; 
		
		//{0.5,0.33,0.17};
	//{0.19,0.13,0.1250,0.1150,0.1,0.090,0.080,0.065,0.050,0.025,0.015,0.010,0.005}; // values should be equivalent to the size of the covariance vectors considered for the calculation
	
	
	private double ALPHA = 0;
	private FSMNode PREVIOUS = null;
	private Queue<Array2DRowRealMatrix> PREV_COVARIANCE = new LinkedList<Array2DRowRealMatrix>();


	/**
	 * @param numberOfNodes as the total set of nodes in the final 2D map
	 * @param depth	as the depth or the number of rows in the map
	 * @param grid as the type of grid (0 = square, 1 = rectangle, 2 = hexagonal)
	 * @param inputDimension the dimension of the input data vector
	 * @param covarianceNumber the size of the covariance matrix
	 * @param threshold the solidification threshold
	 * @param iteration the number of iterations
	 * @param vector choice of weight vector.
	 * 
	 * Basic constructor for the self-organizing maps algorithms. 
	 * ENTRY POINT FOR THE ENTIRE ALGORITHM
	 */
	public SelfOrganizingMap(int numberOfNodes, int inputDimensison, boolean isMatrixMode, int covarianceNumber, int threshold, int iteration, int vector)
	{
		//The number of elements in the weight vector used in the weighted covariance matrix is directly proportional 
		//to the number of attributes in the input vector.
		
		if(vector ==0)
		{
			double temp[] =  {0.5,0.30,0.15,0.05};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 1)
		{
			double temp[] =  {0.6,0.4};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 2)
		{
			double temp[] =  {0.5,0.33,0.17};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 3)
		{
			double temp[] =  {0.5,0.3,0.15,0.05};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 4)
		{
			double temp[] =  {0.5,0.20,0.15,0.10,0.05};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 5)
		{
			double temp[] =  {0.19,0.13,0.1250,0.1150,0.1,0.090,0.080,0.065,0.050,0.025,0.015,0.010,0.005};
			VECTOR_WEIGHTS = temp;
		}
		else if(vector == 6) 
		{
			double temp[] =  {0.001,0.005,0.01,0.015,0.02,0.025,0.03,0.035,0.04,0.045,0.05,0.055,0.06,0.065,0.07,0.075,0.084,0.09,0.1,0.125};
			VECTOR_WEIGHTS = temp;
		}
		
		INPUT_DIMENSION = inputDimensison; //Input dimensions of the input vector
		IS_MATRIX_MODE = isMatrixMode; //whether the SOM weights are vector or matrix based
		COVARIANCE_NUMBER = covarianceNumber; //number of vectors participating in a covariance matrix
		ALPHA = setAlpha(); //calculates the constant of the weighted covariance equation
		THRESHOLD = threshold; ///for two
		
		int side = (int)Math.sqrt(numberOfNodes);
		SOM = new Node[side][side];
		U_MATRIX = new double[2*side - 1][2*side - 1];
		U_MATRIX_SHRINK = new double[side][side];
		NORM_MAP = new double[side][side];
		MAX_RADIUS = side/2;
		
		ZERO_MAP = new Node[side][side]; //initializes the 3d layer to contains sequence draws.		
		FSM = new FiniteStateMachine(threshold, iteration, vector,ZERO_MAP); //initiates a new FSM which essentially creates the V layer
		
		//The initialization of the SOM is different based on the type of structure used to hold node weights.
		if(IS_MATRIX_MODE)
		{
			initializeMatrix();		//initializes the matrix mode SOM
		}
		else
		{
			initialize();	//initializes the vector mode SOM - Classical SOM
		}
		
		RADIUS = MAX_RADIUS; //Sets the effective radius of the neighborhood 
	}
	
	/**
	 * @return the constant part of weighted covariance calculation
	 */
	private double setAlpha() {
		double squaredMean = 0;
		
		for(int i = 0; i < VECTOR_WEIGHTS.length; i++)
		{
			squaredMean += Math.pow(VECTOR_WEIGHTS[i],2);
		}
		
		return (1 / (1 - squaredMean));
	}

	/**
	 * @param a
	 * @param b
	 * @return the vector dot product
	 */
	private double multiply(ArrayRealVector a, ArrayRealVector b)
	{
		double value = a.dotProduct(b);
		
		return value;
	}
	
	
	/**
	 * @param weightMatrix
	 * @param covariance
	 * @return the norm between the weight matrix and the weighted covariance matrix
	 */
	private double multiply(Array2DRowRealMatrix weightMatrix,Array2DRowRealMatrix covariance, int option)
	{
		Array2DRowRealMatrix difference =	weightMatrix.subtract(covariance);
		double distanceValue = 0;
		
		if(option == 0)
		{
			distanceValue = difference.getNorm(); // 1 - norm => max column value
		}
		else  if(option == 1) // calculation of infinity - norm => max row value
		{
			double tempRowValue = 0;
			double maxRowValue = 0;
			
			for(int i = 0; i < difference.getRowDimension() ; i++)
			{				
				tempRowValue = 0;
				
				for(int j = 0; j < difference.getColumnDimension() ; j++)
				{
					tempRowValue += Math.abs(difference.getEntry(i, j));
				}
				
				if(tempRowValue > maxRowValue)
				{
					maxRowValue = tempRowValue;
				}
			}
			
			distanceValue = maxRowValue;
		}
		else  if(option == 2)
		{
			distanceValue = difference.getFrobeniusNorm(); // Frobenius norm 
		}
		else if(option == 3) // spectral norm
		{
			EigenDecomposition eig = new EigenDecomposition((difference.transpose()).multiply(difference));
			
			double eigenValues[] = eig.getRealEigenvalues();
			
			double maxEig = 0;
			
			for (int i = 0 ; i < eigenValues.length ; i++)
			{
				if(eigenValues[i] > maxEig)
				{
					maxEig = eigenValues[i];
				}
			}
			
			distanceValue = Math.sqrt(maxEig);		
		}
		
		return distanceValue;
	}

	/**
	 * @param input
	 * @param iterations
	 * @param learningRate
	 * 
	 * The memory efficient version of  {@link #initTrainSOM(String, int, double)}
	 */
	public void initTrainSOMMemoryEfficient(String fileLocation, int iterations, double learningRate)
	{

		NUMER_OF_ITERATIONS = iterations;
		INITIAL_LEARNING_RATE = learningRate;
		LEARNING_RATE = INITIAL_LEARNING_RATE;
		TIME_STEP = NUMER_OF_ITERATIONS/Math.log(MAX_RADIUS);

		System.out.println("HERE 2");
		trainSOMMemoryEfficient(fileLocation); 
		
		FSM.printSummary();
		extractSmallerUMatrix();
		//exportSmallUMatrixToCSV();
	}
	
	private void trainSOMMemoryEfficient(String fileLocation) 
	{
		String line = "";
		// The value of variable sequence has to be changed to XX when the covariance number is 2 and to XXX when
		// the covariance number is 3 or greater.
		String sequence = "XX";
		boolean skipZeroEntries = true; //switch to take off X entries from the subsequence generated
		double temp[][] = new double[COVARIANCE_NUMBER][INPUT_DIMENSION]; 
		Array2DRowRealMatrix covariance = null;
		Node winner = null;
		FSMNode current = null;
		int zeroCounter = 0;
		BufferedReader BR = null;
		int counter = 0;
		
		//System.out.println("LENGTH =============================== " + INPUT_SAMPLES.length());
		
		try{
		
		//================================================================================================
			for(int k = 0; k <= NUMER_OF_ITERATIONS; k++)
			{
				FileReader file = new FileReader(fileLocation);
				BR = new BufferedReader(file);
				String strLine = null;
				BR.readLine();
				counter = 0;
				sequence = "XX";
				EpochRadiusDecay(k);
				LearningRateDecay(k);
				while((strLine = BR.readLine()) != null)
				{
					//counter++;
					for(int j = 0; j < COVARIANCE_NUMBER; j++)
					{
						if( (j + 1) < COVARIANCE_NUMBER)
						{
							temp[j] = temp[j+1];
						}
						else
						{
							temp[j] = new double[INPUT_DIMENSION];
						}					
					}
					
					line = strLine;
					if(!line.contains("####"))
					{
						String[] inputVector = line.split("\t");

						sequence = sequence.substring(1).concat(inputVector[1].toString());

									
						//System.out.println("===========================SEQUENCE============================" + sequence);
						/* 
						 * The following loop fills in the last element of the sliding window with the latest input
						 * vector element encountered. All the past input vectors are shifted up by one element.
						 */
						for(int i = 2; i < inputVector.length; i++)
						{
							temp[COVARIANCE_NUMBER-1][i-2] = Double.parseDouble(inputVector[i]);					
						}
								
						if(!skipZeroEntries && !sequence.contains("X")) //to avoid the X in the middle checking breaks
						{
							covariance = generateCovarainceMatrix(temp);
							//System.out.println( sequence + "  " + covariance.toString());
							winner = setAccumulatedValue(covariance,sequence);
							adjustNeighbourhoodOfWinners(winner, covariance);
							current = FSM.addUpdateNode(new FSMNode(sequence), PREVIOUS, winner,LEARNING_RATE,RADIUS,CURRENT_ITERATION);
			
							PREVIOUS = current;
							current = null;				
							

							//System.out.println(counter);
						}
						else
						{
							zeroCounter++;
							
							/* Needs to skip 1 entry if the covaraince number is 1 else two if the number is 3. For any other
							 * number the value is set to n-1.
							 */
							
							if(zeroCounter >= 1) //2 for gt > 3
								skipZeroEntries = false;
						}

					}
				}
				System.out.println("===============" + k + " LEARNING RATE =" + LEARNING_RATE + "  RADIUS =" +RADIUS );
			}
		//===================================================================================================
		}
		catch(Exception e)
		{
			
		}
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
		PRESENTATION_NUMBER = 8*101;
		INPUT_SAMPLES = input;
		//DISPLAY_SCREEN.render();
		for(int i = 1; i <= NUMER_OF_ITERATIONS; i++) //if 100 iteration we go from 0...100
		{
			//System.out.println("HERE 2");
			//PREVIOUS = null;
			singleCompleteRun(); //executes a single iteration of the SOM
			CURRENT_ITERATION++;
			
			if((i%100)== 0)
			{
				extractSmallerUMatrix();
				exportSmallUMatrixToCSV(i);
							
				//extractSmallerUMatrixZERO();
				//exportSmallUMatrixToCSVZERO(i);
			}
		//	CURRENT_PRESENTATION_NUMBER++;
			System.out.println("Iteration = " + i + " Learning Rate = " + LEARNING_RATE + " Radius = " + RADIUS + " ******************");
		}			
		
		//FSM.printLinks();
		FSM.printSummary();
		//FSM.writeSummaryToFile();
		
	//	createUMatrix();
	//	extractSmallerUMatrix();
		
/*		for(int i = 0; i < U_MATRIX.length; i++)
		{
			for(int j = 0; j < U_MATRIX.length; j++)
			{
				//System.out.println("SOM I= "+i+" J= "+j);
				//printMatrix(SOM[i][j].getWeightMatrix());
				System.out.print(U_MATRIX[i][j] + " ");				
			}
			System.out.println();			
		}*/
		
	//	exportUMatrixToCSV();
	//	exportSmallUMatrixToCSV();
		
	//	extractSmallerUMatrixZERO();
	//	exportSmallUMatrixToCSVZERO();
	//	displayHitNodesAndSequences();
	//	testSOM();

	}


	/**
	 *  Executes a single iteration of the SOM algorithm. 
	 */
	private void singleCompleteRun()
	{
		//The invocation of the iterations are different based on the mode of the weight vectors.
		
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
		//System.out.println(CURRENT_ITERATION);
	}
	
	/**
	 * @param iterations
	 */
	@SuppressWarnings("unused")
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
	
	/**
	 * @return
	 */
	@SuppressWarnings("unused")
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
	 * Tests the generated SOM in matrix mode input.
	 */
	@SuppressWarnings("unused")
	private void testSOM()
	{
		String line = "";
		String sequence = "XXX";
		boolean skipZeroEntries = false;
		double temp[][] = new double[COVARIANCE_NUMBER][INPUT_DIMENSION]; 
		Array2DRowRealMatrix covariance = null;
		Node winner = null;
		int zeroCounter = 0;
		int tempcounter = 0;
		
		StringTokenizer first = new StringTokenizer(INPUT_SAMPLES, "\n");
		first.nextToken();	
		
		while(first.hasMoreTokens())
		{
			/* 
			 * The following loop shifts the array by one set. The values at the array position [0] is discarded 
			 * and is replaced with the next entry which is [1].  The last entry [N] is reset to all zeros which
			 * then will be filled by the input reading for-loop which is found immediately after this loop.
			 */
			for(int j = 0; j < COVARIANCE_NUMBER; j++)
			{
				if( (j + 1) < COVARIANCE_NUMBER)
				{
					temp[j] = temp[j+1];
				}
				else
				{
					temp[j] = new double[INPUT_DIMENSION];
				}					
			}
			
			line = first.nextToken();
			//System.out.println(line);
			if(!line.contains("####"))
			{
				String[] inputVector = line.split("\t");
				
				sequence = sequence.substring(1).concat(inputVector[1].toString());
				/* 
				 * The following loop fills in the last element of the sliding window with the latest input
				 * vector element encountered. All the past input vectors are shifted up by one element.
				 */
				for(int i = 2; i < inputVector.length; i++)
				{
					temp[COVARIANCE_NUMBER-1][i-2] = Double.parseDouble(inputVector[i]);					
				}
						
				if(!skipZeroEntries)
				{
					covariance = generateCovarainceMatrix(temp);
					
					winner = setAccumulatedValue(covariance,sequence);
					
					tempcounter++;
					
				//	System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				//	System.out.println("=============================== " + tempcounter);
				}
				else
				{
					zeroCounter++;
					
					if(zeroCounter >= 2)
						skipZeroEntries = false;
				}

			}
		}
	}
	
	/**
	 * Performs a single iteration of SOM training in matrix mode.
	 */
	private void trainSOM() 
	{
		String line = "";
		// The value of variable sequence has to be changed to XX when the covariance number is 2 and to XXX when
		// the covariance number is 3 or greater.
		String sequence = "XX";
		boolean skipZeroEntries = true; //switch to take off X entries from the subsequence generated
		double temp[][] = new double[COVARIANCE_NUMBER][INPUT_DIMENSION]; 
		Array2DRowRealMatrix covariance = null;
		Node winner = null;
		FSMNode current = null;
		int zeroCounter = 0;
		
		
		StringTokenizer first = new StringTokenizer(INPUT_SAMPLES, "\n");
		first.nextToken();	
		int tempcounter = 0;
		
		//System.out.println("LENGTH =============================== " + INPUT_SAMPLES.length());
		
		while(first.hasMoreTokens())
		{
			/* 
			 * The following loop shifts the array by one set. The values at the array position [0] is discarded 
			 * and is replaced with the next entry which is [1].  The last entry [N] is reset to all zeros which
			 * then will be filled by the input reading for-loop which is found immediately after this loop.
			 */
			for(int j = 0; j < COVARIANCE_NUMBER; j++)
			{
				if( (j + 1) < COVARIANCE_NUMBER)
				{
					temp[j] = temp[j+1];
				}
				else
				{
					temp[j] = new double[INPUT_DIMENSION];
				}					
			}
			
			line = first.nextToken();
			//System.out.println(line);
			if(!line.contains("####"))
			{
				String[] inputVector = line.split("\t");
				
				if(sequence.length() < 6)
				{
					sequence = sequence.substring(1).concat(inputVector[1].toString());
					//System.out.println(sequence);
				}
				else
				{
					sequence = sequence.substring(2).concat(inputVector[1].toString());
				}
				
				//System.out.println("===========================SEQUENCE============================" + sequence);
				/* 
				 * The following loop fills in the last element of the sliding window with the latest input
				 * vector element encountered. All the past input vectors are shifted up by one element.
				 */
				for(int i = 2; i < inputVector.length; i++)
				{
					temp[COVARIANCE_NUMBER-1][i-2] = Double.parseDouble(inputVector[i]);					
				}
						
				if(!skipZeroEntries && !sequence.contains("X")) //to avoid the X in the middle checking breaks
				{
					covariance = generateCovarainceMatrix(temp);
					//CURRENT_PRESENTATION_NUMBER++;
					
					//covarianceExists(covariance);
					//System.out.println( sequence + "  " + covariance.toString());
					winner = setAccumulatedValue(covariance,sequence);
					adjustNeighbourhoodOfWinners(winner, covariance);
					//calculateIntensityContribution(winner,1); // calculates the intensity values
										
					//creating a new node would be a problem if in case the sequence is already in the FSM system.
					
					current = FSM.addUpdateNode(new FSMNode(sequence), PREVIOUS, winner, LEARNING_RATE, RADIUS,CURRENT_ITERATION);
										
					//FSM.edgeIntesityDecay(CURRENT_PRESENTATION_NUMBER, PRESENTATION_NUMBER);
					//FSM.updateEdgeIntensity(current, PREVIOUS, winner);
					
					//PREV_COVARIANCE.add(covariance);
					PREVIOUS = current;
					current = null;
					
					tempcounter++;					
				}
				else
				{
					zeroCounter++;
					
					// this is to stop the solid compounds moving through iterations and creating longer compound sequences
					// than what is actually present in data.
					FSM.resertCurrentCompound(); 
					PREVIOUS = null; //resets the previous pointer when data set iteration is complete.
					
					/* Needs to skip 1 entry if the covaraince number is 1 else two if the number is 3. For any other
					 * number the value is set to n-1.
					 */				
					if(zeroCounter >= 1) //2 for gt > 3
						skipZeroEntries = false;
				}

			}
		}
	}
	
	/**
	 * @param covariance
	 */
	@SuppressWarnings("unused")
	private void covarianceExists(Array2DRowRealMatrix covariance) 
	{
	//	System.out.println(PREV_COVARIANCE.size());
			
		if(PREV_COVARIANCE.contains(covariance))
		{
			System.out.println("COVARIANCE MATRIXES EQUAL " + CURRENT_ITERATION );
		}
		else
		{
			System.out.println("COVARIANCE MATRIXES NOT EQUAL " + CURRENT_ITERATION );
		}

	}

	/**
	 * @param winner
	 */
	private void calculateIntensityContribution(Node winner, int radius) 
	{
		double distance = 0;
		double cummulativeValue = 0;
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
				try
				{
					distance = eculideanDistanceInNodes(winner, SOM[i][j]); //CHECK THE SQUARE LOGIC ABOVE
				}
				catch(Exception e)
				{
					System.out.println("Winner X =" + winner.getX() + " Winner Y =" + winner.getY());
					System.out.println("X Min " + effective_x_min);
					System.out.println("X Max " + effective_x_max);
					System.out.println("Y Min " + effective_y_min);
					System.out.println("Y Max " + effective_y_max);
					System.out.println("Radius " + RADIUS);
				}
				
				if(distance <= radius)
				{
					cummulativeValue += multiply(SOM[i][j].getWeightMatrix(), winner.getWeightMatrix(), 2);		
				}
			}
		}
		
		if(radius == 1)
		{
			winner.setIntensity((1 - ((Math.pow(cummulativeValue, 2))/27)));
		//	System.out.println("RADIUS 1 VALUE=========================== " + (1 - (Math.pow(cummulativeValue, 2)/27)));
		}
		else
		{
			winner.setIntensity((1 - ((Math.pow(cummulativeValue, 2))/125)));
		//	System.out.println("RADIUS 2 VALUE=========================== " + (1 - (Math.pow(cummulativeValue, 2)/16)));
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
				
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY() + (new ArrayRealVector(temp)).toString());
				System.out.println("=============================== " + tempcounter);
	/*			winner = setEuclideanAccumulatedValue(new ArrayRealVector(temp));
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("*******************************");*/
			}
		}
	}
	
	/**
	 * @param input
	 */
	public void testVectorSOM(String input)
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
				
				tempcounter++;
				
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY() + (new ArrayRealVector(temp)).toString());
				System.out.println("#### " + tempcounter);
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
				try
				{
					distance = eculideanDistanceInNodes(winner, SOM[i][j]); //CHECK THE SQUARE LOGIC ABOVE
				}
				catch(Exception e)
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
	 * @param winner as the winner node of the input presentation
	 * @param covariance as the covariance matrix recently presented to the network
	 */
	private void adjustNeighbourhoodOfWinners(Node winner, Array2DRowRealMatrix covariance) {
		int radius = (int)Math.ceil(RADIUS);
		double distance = 0.0;
		double theta = 0.0;
		RealMatrix tempWeightsMatrix = null;
		Array2DRowRealMatrix tempPart = null;
		
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
				if(CURRENT_ITERATION >=75)
				{
					//System.out.println("RADIUS =" + radius);
					//System.out.print("Neigbourhood ["+i+"],["+j+ "]->["+SOM[i][j].getX()+","+SOM[i][j].getY()+"]\t");
				}
				
				try
				{
					distance = eculideanDistanceInNodes(winner, SOM[i][j]); //CHECK THE SQUARE LOGIC ABOVE
				
				}
				catch(Exception e)
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
					tempPart = (Array2DRowRealMatrix)(covariance.subtract(SOM[i][j].getWeightMatrix())).scalarMultiply(theta*LEARNING_RATE);
					tempWeightsMatrix = SOM[i][j].getWeightMatrix().add(tempPart);
							

					SOM[i][j].setWeightMatrix((Array2DRowRealMatrix)tempWeightsMatrix);					
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
	 * @param covariance
	 * @return the winner of the presentation
	 */
	private Node setAccumulatedValue(Array2DRowRealMatrix covariance, String sequence) {
		
		
		double temp = 0.0;
		double minSeen = Double.POSITIVE_INFINITY;
		Node minNode = null;
		
		//System.out.println("Input Vector = " + input.toString());
		
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{
				temp = multiply(SOM[i][j].getWeightMatrix(), covariance, 2); // in case there is an error this would revert to zero
				
				if(temp < minSeen)
				{
					minNode = SOM[i][j];
					minSeen = temp;
				}
				
				SOM[i][j].setACTIVATION_VALUE(temp); 
			}
		}	
		

		
		minNode.incrementNumberOfHits();
		minNode.addMappingSequence(sequence);
		//printSOM();
			
		//System.out.println("Winner ["+minNode.getX() +","+ minNode.getY()+"]");
		return minNode;
		
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
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j,IS_MATRIX_MODE,0,false);
				System.out.print("["+i+"],["+j+ "]->["+SOM[i][j].getX()+","+SOM[i][j].getY()+"]\t");
			}
			System.out.println();
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
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j,IS_MATRIX_MODE,COVARIANCE_NUMBER,false);
				ZERO_MAP[i][j] = new Node(INPUT_DIMENSION,i,j,IS_MATRIX_MODE,COVARIANCE_NUMBER,true);
				
				System.out.print("["+i+"],["+j+ "]->["+SOM[i][j].getX()+","+SOM[i][j].getY()+"]\t");
			}
			
			System.out.println();
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
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
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
	
	/**
	 * @param vectors as the vectors in the moving window
	 * @return the covariance matrix of the vectors specified 
	 */
	private Array2DRowRealMatrix generateCovarainceMatrix(double[][] vectors) {
		
		 Array2DRowRealMatrix realData = new Array2DRowRealMatrix(INPUT_DIMENSION, COVARIANCE_NUMBER);
		 Array2DRowRealMatrix covarianceMatrix = new Array2DRowRealMatrix(COVARIANCE_NUMBER,COVARIANCE_NUMBER);
		 
		 for(int i  = 0; i < vectors.length; i++)
		 {
			 realData.setColumn(i, vectors[i]);
		 }
		
		covarianceMatrix = getWeightedCovarianceMatrix(realData);
		
		return covarianceMatrix;
	}

	/**
	 * @param realData
	 * @return the weighted covaraince matrix of the input data matrix
	 */
	private Array2DRowRealMatrix getWeightedCovarianceMatrix(Array2DRowRealMatrix dataMatrix)
	{
		
		Array2DRowRealMatrix covariance = new Array2DRowRealMatrix(dataMatrix.getColumnDimension(),dataMatrix.getColumnDimension());
		double value = 0d;
				
	//	covariance = calculateWeightedCovarianceCorrected(calculateWeightedMeanCorrect(dataMatrix), dataMatrix);
		
		for(int i = 0 ; i < dataMatrix.getColumnDimension() ; i++){
			for(int j=i ; j < dataMatrix.getColumnDimension() ; j++)
			{
				value = calculateWeightedCovariance(dataMatrix.getColumn(i),dataMatrix.getColumn(j),i,j); // /points
				covariance.setEntry(i, j, value);
				covariance.setEntry(j, i, value);		    
			}
			
		}
		
		return covariance;
	}
	
	/**
	 * @param weightedMean
	 * @param dataMatrix
	 * @return
	 */
	@SuppressWarnings("unused")
	private Array2DRowRealMatrix calculateWeightedCovarianceCorrected(double[] weightedMean, Array2DRowRealMatrix dataMatrix)
	{
		Array2DRowRealMatrix covariance = new Array2DRowRealMatrix(dataMatrix.getColumnDimension(),dataMatrix.getColumnDimension());
		double value = 0d;
		
		for(int i = 0; i < dataMatrix.getColumnDimension(); i++)
		{
			dataMatrix.setColumnVector(i, dataMatrix.getColumnVector(i).mapSubtract(weightedMean[i]));
		}
		
		for(int i = 0 ; i < dataMatrix.getColumnDimension() ; i++){
			for(int j=i ; j < dataMatrix.getColumnDimension() ; j++)
			{
				value = calculateWeightedCovarianceSimple(dataMatrix.getColumn(i),dataMatrix.getColumn(j)); // /points
				covariance.setEntry(i, j, value);
				covariance.setEntry(j, i, value);		    
			}
			
		}
		
		return covariance;
	}


	/**
	 * @param column
	 * @param column2
	 * @return
	 */
	private double calculateWeightedCovarianceSimple(double[] column, double[] column2) {
		
		double result = 0d;
		
		for(int i = 0; i < column.length ; i++)
		{
			result += VECTOR_WEIGHTS[i]*(column[i])*(column2[i]);
		}
		
		return (ALPHA*result);
	
	}

	/**
	 * @param vector first column considered for calculation
	 * @param vector2 second column considered for calculation
	 * @param i index of vector in first parameter with respect to the total matrix
	 * @param j index of vector2 the second parameter with respect to the total matrix
	 * @return the weighted covariance value between the two column vectors supplied in arguments
	 */
	private double calculateWeightedCovariance(double[] vector, double[] vector2, int index, int index2) {

		double result = 0d;
		double weightedMean[] = calculateWeightedMean(vector, vector2, index, index2);
		
		
		for(int i = 0; i < vector.length ; i++)
		{
			result += VECTOR_WEIGHTS[i]*(vector[i] - weightedMean[0])*(vector2[i] - weightedMean[1]);
		}
		
		return result; //(ALPHA*result);

	}
	

	/**
	 * THIS IS AN INCORRECT VERSION. HOWEVER, THIS METHOD DO SCALE VERY WELL WITH THE LEARNING HENCE NOT REMOVING
	 * USE THIS IF THE CORRECT MATHEMATICAL METHOD FAIL. THIS IS A SCALER ONLY NOT WHAT THE NAME SAYS.
	 * @param vector
	 * @param vector2
	 * @param index
	 * @param index2
	 * @return
	 */
	private double[] calculateWeightedMean(double[] vector, double[] vector2,int index, int index2) {
		
		double temp[] = new double[2];
		
		double mean[] = {0.6,0.4};// -- {0.5,0.33,0.17} for 3
		
		temp[0] = mean[index];
		temp[1] = mean[index2];
		
/*		for(int i = 0; i < vector.length; i++)
		{
			temp[0] += vector[i]*VECTOR_WEIGHTS[index];
			temp[1] += vector2[i]*VECTOR_WEIGHTS[index2];
		}*/
			
		return temp;
	}
	
	/**
	 * @param dataMatrix
	 * @return
	 */
	@SuppressWarnings("unused")
	private double[] calculateWeightedMeanCorrect(Array2DRowRealMatrix dataMatrix){
			
		double temp[][] = new double[dataMatrix.getColumnDimension()][dataMatrix.getColumnDimension()];
		double wMean[] = new double[dataMatrix.getColumnDimension()];
		
		for(int i = 0; i < temp.length; i++)
		{
			temp[i] = (dataMatrix.getColumnVector(i).mapMultiply(VECTOR_WEIGHTS[i])).toArray();
		}
		
		for(int i = 0; i < temp.length; i++)
		{
			for(int j = 0; j <temp[0].length; j++)
			{
				wMean[i] += temp[j][i];
			}

		}
		
		return wMean;
	}
	
	/**
	 * @param weightMatrix
	 */
	@SuppressWarnings("unused")
	private void printMatrix(Array2DRowRealMatrix weightMatrix)
	{
		for(int i = 0; i < weightMatrix.getRowDimension(); i++)
		{
			for(int j = 0; j < weightMatrix.getColumnDimension(); j++)
			{
				System.out.print(weightMatrix.getEntry(i, j));
			}
			System.out.println();
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private void createUMatrix()
	{	
		int a = 0; // 2x + 1
		int b = 0; // 2x
		int c = 0; // 2y + 1
		int d = 0; // 2y 
		boolean overdone = false;
		
		
		for(int i = 0; i< SOM.length ; i++) //first loop decides x
		{
			a = 2*i + 1;
			b = 2*i;
			for(int j = 0; j < SOM[0].length; j++) //second loop decides y
			{
				c = 2*j + 1;
				d = 2*j;
				
				
				if(a < SOM.length)
				{
					U_MATRIX[a][d] = ((SOM[i][j].getWeightMatrix()).subtract((SOM[i+1][j].getWeightMatrix()))).getFrobeniusNorm();
				}
				else
				{
					overdone = true;
				}
				
				if(c < SOM[0].length)
				{
					U_MATRIX[b][c] = ((SOM[i][j].getWeightMatrix()).subtract((SOM[i][j+1].getWeightMatrix()))).getFrobeniusNorm();;
				}
				else
				{
					overdone = true;
				}
				
				if(!overdone)
				{
					double temp1 = ((SOM[i][j].getWeightMatrix()).subtract((SOM[i+1][j+1].getWeightMatrix()))).getFrobeniusNorm();
					double temp2 = ((SOM[i][j+1].getWeightMatrix()).subtract((SOM[i+1][j].getWeightMatrix()))).getFrobeniusNorm();
					U_MATRIX[a][c] = (temp1 + temp2) / (2*Math.sqrt(2));
				}
				
				U_MATRIX[b][d] = getAverageUMatixNeighbour(b,d); //average of all surrounding
			}
		}
		
	}

	/**
	 * @param b
	 * @param d
	 * @return
	 */
	private double getAverageUMatixNeighbour(int b, int d) { //for vectors
		
		double value = 0;
		int numOfNeighbours = 0;
		double medianArray[] = new double[8];
		double retValue = 0;
		int index = 0;
		
		int counter = 0;
		
		for (int i = b - 1; i <= b + 1; i++ )
		{
			for(int j = d - 1; j <=d + 1; j++)
			{
				if(elementExists(i,j,false))
				{
					if(i != b && j != d)
					{
						medianArray[counter] = U_MATRIX[i][j];
						value += U_MATRIX[i][j];
						numOfNeighbours++;
					}
				}
			}
		}
		
		Arrays.sort(medianArray);
		
		if(numOfNeighbours%2 == 1)
		{
			index = numOfNeighbours/2;
			retValue = medianArray[index];
		}
		else if(numOfNeighbours%2 == 0)
		{
			retValue = (medianArray[3] + medianArray[4])/2;
		}
		
		return retValue;  //(value/numOfNeighbours); put this if average is needed
	}
	
	/**
	 * @param b
	 * @param d
	 * @return
	 */
	private double getAverageSOMNeighbour(int b, int d) //for matrices??
	{
		double value = 0;
		int numOfNeighbours = 0;
		double medianArray[] = new double[8];
		double retValue = 0;
		int index = 0;
		double temp = 0;
		
		int counter = 0;
		
		for (int i = b - 1; i <= b + 1; i++ )
		{
			for(int j = d - 1; j <=d + 1; j++)
			{
				if(elementExists(i,j,true))
				{
					if(i != b && j != d)
					{
						temp = SOM[b][d].getWEIGHTS().subtract(SOM[i][j].getWEIGHTS()).getNorm(); 
						//temp = ((SOM[b][d].getWeightMatrix()).subtract(SOM[i][j].getWeightMatrix())).getFrobeniusNorm();
						medianArray[counter] = temp;
						value += temp;
						numOfNeighbours++;
					}
				}
			}
		}
		
		Arrays.sort(medianArray);
		
		if(numOfNeighbours%2 == 1)
		{
			index = numOfNeighbours/2;
			retValue = medianArray[index];
		}
		else if(numOfNeighbours%2 == 0)
		{
			retValue = (medianArray[3] + medianArray[4])/2;
		}
		
		return (value/numOfNeighbours); //put this if average is needed	
	}

	/**
	 * @param b
	 * @param d
	 * @return
	 */
	private double getAverageSOMNeighbourZERO(int b, int d) //for matrices??
	{
		double value = 0;
		int numOfNeighbours = 0;
		double medianArray[] = new double[8];
		double retValue = 0;
		int index = 0;
		double temp = 0;
		
		int counter = 0;
		
		for (int i = b - 1; i <= b + 1; i++ )
		{
			for(int j = d - 1; j <=d + 1; j++)
			{
				if(elementExists(i,j,true))
				{
					if(i != b && j != d)
					{
						//temp = SOM[b][d].getWEIGHTS().subtract(SOM[i][j].getWEIGHTS()).getNorm(); 
						temp = ((ZERO_MAP[b][d].getWeightMatrix()).subtract(ZERO_MAP[i][j].getWeightMatrix())).getFrobeniusNorm();
						medianArray[counter] = temp;
						value += temp;
						numOfNeighbours++;
					}
				}
			}
		}
		
		Arrays.sort(medianArray);
		
		if(numOfNeighbours%2 == 1)
		{
			index = numOfNeighbours/2;
			retValue = medianArray[index];
		}
		else if(numOfNeighbours%2 == 0)
		{
			retValue = (medianArray[3] + medianArray[4])/2;
		}
		
		return (value/numOfNeighbours); //put this if average is needed	
	}
	
	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean elementExists(int i, int j, boolean isSOM) {

		try
		{
			
			@SuppressWarnings("unused")
			double temp = 0;
			
			if(isSOM)
			{
				temp = SOM[i][j].getX();
			}
			
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	/**
	 * 
	 */
	private void extractSmallerUMatrix()
	{
		for(int i = 0; i < SOM.length; i++)
		{
			for(int j = 0; j < SOM[0].length; j++)
			{
				U_MATRIX_SHRINK[i][j] = getAverageSOMNeighbour(i,j);
			}
		}
	}
	
	private void extractSmallerUMatrixZERO()
	{
		for(int i = 0; i < ZERO_MAP.length; i++)
		{
			for(int j = 0; j < ZERO_MAP[0].length; j++)
			{
				U_MATRIX_SHRINK[i][j] = ZERO_MAP[i][j].getWeightMatrix().getFrobeniusNorm();
				
				//getAverageSOMNeighbourZERO(i,j);
						
						//
				//
			}
		}
	}
	
	/**
	 * 
	 */
	private void exportSmallUMatrixToCSV(int iteration)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("E:\\workspace\\GSOM\\Sequence Modeling\\csv\\Zoo-"+iteration+".csv",false));
			
			for(int i = 0 ; i < U_MATRIX_SHRINK.length; i++){
				for(int j = 0; j < U_MATRIX_SHRINK[0].length; j++){
					bw.write(U_MATRIX_SHRINK[i][j] + ",");
				}
				bw.newLine();
			}
			bw.flush();
			System.out.println("File Exported - SMALL U.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	private void exportSmallUMatrixToCSVZERO(int iteration)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("E:\\workspace\\GSOM\\Sequence Modeling\\csv\\ZERO-"+iteration+".csv",false));
			
			for(int i = 0 ; i < U_MATRIX_SHRINK.length; i++){
				for(int j = 0; j < U_MATRIX_SHRINK[0].length; j++){
					bw.write(U_MATRIX_SHRINK[i][j] + ",");
				}
				bw.newLine();
			}
			bw.flush();
			System.out.println("File Exported - SMALL U.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
    private void exportUMatrixToCSV()
    {
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("csv\\SS-NX-3.csv",false));
			
			for(int i = 0 ; i < U_MATRIX.length; i++){
				for(int j = 0; j < U_MATRIX[0].length; j++){
					bw.write(U_MATRIX[i][j] + ",");
				}
				bw.newLine();
			}
			bw.flush();
			System.out.println("File Exported - BIG U.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
	
	/**
	 * 
	 */
    @SuppressWarnings("unused")
	private void displayHitNodesAndSequences()
	{
		
		BufferedWriter bw = null;
		BufferedWriter as = null;
		ArrayList<String> temp = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("csv\\SS-NX-NODE-DESCRIPTION-3.txt",false));
			as = new BufferedWriter(new FileWriter("csv\\SS-NX-NODE-3.csv",false));
			for(int i = 0; i < SOM.length; i++)
			{
				for(int j = 0; j<SOM[0].length; j++)
				{
					bw.write("Node "+ i +" " + j + " hits = " + SOM[i][j].getNumberOfHits());
					bw.newLine();
					
					temp = SOM[i][j].getMappedSequences();
					
					for(int k = 0; k < temp.size(); k++)
					{
						bw.write(temp.get(k) + " ");
					}
					bw.newLine();
					bw.write("================================================================================");
					bw.newLine();
					
					as.write(SOM[i][j].getNumberOfHits() + ",");
					
				}
				as.newLine();
			}
			as.flush();
			bw.flush();
			System.out.println("File Exported - NODE DESCRIPTION.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
}