/**
 * @package		maps.Structures
 * @filename	SelfOrganizinMap.java
 */
package maps.Structures;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.StringTokenizer;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealMatrix;

import sun.font.CreatedFontTracker;

/**
 * @author 		Manjusri Ishwara
 * @date   		Oct 8, 2013 - 11:27:22 PM
 * @type		SelfOrganizinMap
 */

public class SelfOrganizingMap {
	
	private Node[][] SOM = null;
	private double[][] NORM_MAP = null; 			//holds the L2 norm of each vector in the SOM[][]
	private double[][] U_MATRIX = null;
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
	private double VECTOR_WEIGHTS[] = {0.5,0.33,0.17}; // values should be equivalent to the size of the covariance vectors considered for the calculation
	private double ALPHA = 0;


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
		ALPHA = setAlpha();
		
		int side = (int)Math.sqrt(numberOfNodes);
		SOM = new Node[side][side];
		U_MATRIX = new double[2*side - 1][2*side - 1];
		NORM_MAP = new double[side][side];
		MAX_RADIUS = side/2;
		
		if(IS_MATRIX_MODE)
		{
			initializeMatrix();			
		}
		else
		{
			initialize();
		}

		RADIUS = MAX_RADIUS;
	}
	
	/**
	 * @return
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
	 * @return
	 */
	private double multiply(ArrayRealVector a, ArrayRealVector b)
	{
		double value = a.dotProduct(b);
		
		return value;
	}
	
	
	/**
	 * @param weightMatrix
	 * @param covariance
	 * @return
	 */
	private double multiply(Array2DRowRealMatrix weightMatrix,Array2DRowRealMatrix covariance, int option)
	{
		Array2DRowRealMatrix difference =	weightMatrix.subtract(covariance);
		double distanceValue = 0;
		
		if(option == 0)
		{
			distanceValue = difference.getNorm(); // 1 - norm => max column value
		}
		else  if(option == 1)
		{
			double tempRowValue = 0;
			double maxRowValue = 0;
			
			for(int i = 0; i < difference.getRowDimension() ; i++)
			{
				// calculation of infinity - norm => max row value
				
				tempRowValue = 0;
				
				for(int j = 0; j < difference.getColumnDimension() ; j++)
				{
					tempRowValue += difference.getEntry(i, j);
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
		
		createUMatrix();
		
		for(int i = 0; i < U_MATRIX.length; i++)
		{
			for(int j = 0; j < U_MATRIX.length; j++)
			{
				//System.out.println("SOM I= "+i+" J= "+j);
				//printMatrix(SOM[i][j].getWeightMatrix());
				System.out.print(U_MATRIX[i][j] + " ");				
			}
			System.out.println();			
		}
		
		exportUMatrixToCSV();

	}
	
	/**
	 * 
	 */
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
	
	/**
	 * @param iterations
	 */
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
		double temp[][] = new double[COVARIANCE_NUMBER][INPUT_DIMENSION]; 
		Array2DRowRealMatrix covariance = null;
		Node winner = null;
		
		
		StringTokenizer first = new StringTokenizer(INPUT_SAMPLES, "\n");
		first.nextToken();	
		int tempcounter = 0;
		
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
				
				
				/* 
				 * The following loop fills in the last element of the sliding window with the latest input
				 * vector element encountered. All the past input vectors are shifted up by one element.
				 */
				for(int i = 1; i < inputVector.length; i++)
				{
					temp[COVARIANCE_NUMBER-1][i-1] = Double.parseDouble(inputVector[i]);					
				}
				
				covariance = generateCovarainceMatrix(temp);
				
				winner = setAccumulatedValue(covariance);
				adjustNeighbourhoodOfWinners(winner, covariance);
				
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
	private Node setAccumulatedValue(Array2DRowRealMatrix covariance) {
		
		
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
		
		//printSOM();
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
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j,IS_MATRIX_MODE,0);
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
	
	/**
	 * 
	 */
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
		
		return (ALPHA*result);

	}

	/**
	 * @param vector
	 * @param vector2
	 * @param index
	 * @param index2
	 * @return
	 */
	private double[] calculateWeightedMean(double[] vector, double[] vector2,int index, int index2) {
		
		double temp[] = new double[2];
		
		for(int i = 0; i < vector.length; i++)
		{
			temp[0] += vector[i]*VECTOR_WEIGHTS[index];
			temp[1] += vector2[i]*VECTOR_WEIGHTS[index2];
		}
			
		return temp;
		
	}
	
	/**
	 * @param weightMatrix
	 */
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
	private double getAverageUMatixNeighbour(int b, int d) {
		
		double value = 0;
		double numOfNeighbours = 0;
		
		for (int i = b - 1; i <= b + 1; i++ )
		{
			for(int j = d - 1; j <=d + 1; j++)
			{
				if(elementExists(i,j))
				{
					if(i != b && j != d)
					{
						value += U_MATRIX[i][j];
						numOfNeighbours++;
					}
				}
			}
		}
		return (value/numOfNeighbours);
	}

	/**
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean elementExists(int i, int j) {

		try
		{
			double temp = U_MATRIX[i][j];
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
    private void exportUMatrixToCSV()
    {
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("csv\\100x100-1000.csv",false));
			
			for(int i = 0 ; i < U_MATRIX.length; i++){
				for(int j = 0; j < U_MATRIX[0].length; j++){
					bw.write(U_MATRIX[i][j] + ",");
				}
				bw.newLine();
			}
			bw.flush();
			System.out.println("File Exported.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}