/**
 * @package		maps.Structures
 * @filename	SelfOrganizingMap.java
 */
package maps.Structures;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 31, 2013 - 12:34:39 AM
 * @type		SelfOrganizingMap
 */
public class SelfOrganizingMap{
	
	private Node[][] SOM = null;
	private double[][] NORM_MAP = null; //holds the L2 norm of each vector in the SOM[][].
	private int INPUT_DIMENSION = 0;
	private int NUMER_OF_ITERATIONS = 0;
	private int CURRENT_ITERATION=0;
	private int SOM_HORIZONTAL_LENGTH = 0;
	private int SOM_VERTICAL_LENGTH = 0;
	private double INITIAL_LEARNING_RATE = 0.0;
	private double LEARNING_RATE = 0.0;
	private double MAX_RADIUS = 0.0; //radius at first epoch (t = 0)
	private double RADIUS = 0.0;
	private double TIME_STEP = 0.0; //lambda of X(t) = t0 * exp(-t/lambda)
	private String INPUT_SAMPLES = null;
	private double[][] U_MATRIX = null;
	private double[][] U_MATRIX_SHRINK = null;
	

	/**
	 * @param numberOfNodes as the total set of nodes in the final 2D map
	 * @param depth	as the depth or the number of rows in the map
	 * @param grid as the type of grid (0 = square, 1 = rectangle, 2 = hexagonal)
	 * @param inputDimension the dimension of the input data vector
	 */
	public SelfOrganizingMap(int numberOfNodes, int depth, int inputDimensison)
	{
		INPUT_DIMENSION = inputDimensison;
		
		int side = (int)Math.sqrt(numberOfNodes);
		SOM = new Node[side][side];
		NORM_MAP = new double[side][side];
		MAX_RADIUS = side/2;
		initialize();

		U_MATRIX = new double[2*side - 1][2*side - 1];
		U_MATRIX_SHRINK = new double[side][side];
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
	}
	
	private void singleCompleteRun()
	{
		trainSOM(INPUT_SAMPLES); 		
		EpochRadiusDecay(CURRENT_ITERATION);
		LearningRateDecay(CURRENT_ITERATION);
		System.out.println(CURRENT_ITERATION + " LR " + LEARNING_RATE + " RAD " + RADIUS);
	}
	
	public void trainSOM()
	{
		while(CURRENT_ITERATION < NUMER_OF_ITERATIONS)
		{
			singleCompleteRun();	
			CURRENT_ITERATION++;
			//System.out.println(CURRENT_ITERATION);
		}
		
		if(CURRENT_ITERATION%10 == 0)
		{
			extractSmallerUMatrix(true);
			exportSmallUMatrixToCSV(CURRENT_ITERATION);
		}
		
		printSOMHits();
		//extractSmallerUMatrix(false);
		//exportSmallUMatrixToCSV(0);
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
	

	
	/**
	 * @param input as the input vector
	 * Performs a single iteration of SOM training
	 */
	private void trainSOM(String input)
	{
		String line = "";
		double temp[] = null;
		Node winner = null;
		
		
		StringTokenizer first = new StringTokenizer(input, "\n");
		first.nextToken();		
		
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
				
				adjustHits(winner, inputVector[0]);
				
				adjustNeighbourhoodOfWinners(winner, new ArrayRealVector(temp));
							
				System.out.println( inputVector[0] +  "\tWINNER x =" + winner.getX() + " y= " + winner.getY() );
				System.out.println("===============================");
/*				winner = setEuclideanAccumulatedValue(new ArrayRealVector(temp));
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("*******************************");*/
			}
		}
	}
	
	
	/**
	 * @param input as the input vector
	 * Performs a single iteration of SOM training
	 */
	public void testSOM(String input)
	{
		String line = "";
		double temp[] = null;
		Node winner = null;
		
		
		StringTokenizer first = new StringTokenizer(input, "\n");
		first.nextToken();		
		
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
							
				
				
				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
				System.out.println("===============================");

			}
		}
	}
	
	
	private void adjustHits(Node winner, String string) {
		// TODO Auto-generated method stub
		Iterator<HitHolder> ite = winner.hitList.iterator();
		HitHolder temp = null;
		boolean exists = false;
		
		
		while(ite.hasNext())
		{
			temp = ite.next();
			
			if(temp.element.equalsIgnoreCase(string))
			{
				if(temp.getFirstIteration() == -1)
				{
					temp.firstIteration = CURRENT_ITERATION;
				}
				
				temp.lastIteration = CURRENT_ITERATION;
				temp.numberOfHits++;
				exists = true;
			}
		}
		
		if(!exists)
		{
			winner.hitList.add(new HitHolder(string, CURRENT_ITERATION));
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
				distance = eculideanDistanceInNodes(winner, SOM[j][i]); //CHECK THE SQUARE LOGIC ABOVE -- FIXED the case of misguided indexes
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
					tempWeights = SOM[j][i].getWEIGHTS().add((inputVector.subtract(SOM[j][i].getWEIGHTS())).mapMultiplyToSelf(theta*LEARNING_RATE)) ;
					SOM[j][i].setWEIGHTS(tempWeights);					
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
		
		return subValue.getNorm(); //No need to square root since the norm is already square rooted.
	}
	
	/**
	 * @param currentIteration
	 */
	private  void LearningRateDecay(int currentIteration)
	{
		LEARNING_RATE = INITIAL_LEARNING_RATE*Math.exp(-(double)currentIteration/TIME_STEP); //NUMER_OF_ITERATIONS
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
				SOM[i][j] = new Node(INPUT_DIMENSION,i,j);
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
	
	public void printSOMHits()
	{
		HitHolder temp = null;
		Iterator<HitHolder> ite = null;
		System.out.println("===========================================================");
		for(int i = 0 ; i < SOM.length; i++)
		{
			for(int j=0; j < SOM[0].length; j++)
			{				
				if(SOM[i][j].hitList.size() != 0)
				{
					System.out.println("******************************************************");
					System.out.println("X =" + SOM[i][j].getX() + " Y =" + SOM[i][j].getY());
					ite = SOM[i][j].hitList.iterator();
					
					while(ite.hasNext())
					{
						temp = ite.next();
						System.out.println("Element\t"+temp.element + "\tHits=\t" + temp.numberOfHits
							+ "\tFirst\t" + temp.firstIteration + "\tLast\t" + temp.lastIteration);
					}
				}
				//System.out.println(i + " " + j);
				//System.out.println("X =" + SOM[i][j].getX() + " Y =" + SOM[i][j].getY() + " ACTIVATION VALUE =" + SOM[i][j].getACTIVATION_VALUE());
				//System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
			}
			
		}
		
		//System.out.println("===============================");
	}
		
	/**
	 * Extracts a smaller U-Matrix Using norm values
	 * 
	 * @param average set true if the neighborhood calculation needs to be averaged else make it false. 
	 * The calculation would then default to median.
	 */
	private void extractSmallerUMatrix(boolean average)
	{
		for(int i = 0; i < SOM.length; i++)
		{
			for(int j = 0; j < SOM[0].length; j++)
			{
				U_MATRIX_SHRINK[i][j] = getAverageSOMNeighbour(i,j,average);
				//System.out.print("[ "+ i +" ][ " + j +  " ] = " + U_MATRIX_SHRINK[i][j]+"\t");
			}
			
			//System.out.println();
		}
	}
	
	/**
	 * 
	 * @param iteration
	 * 
	 * exports U-matrix to a csv
	 */
	private void exportSmallUMatrixToCSV(int iteration)
	{
		BufferedWriter bw = null;
		
		try
		{
			bw = new BufferedWriter(new FileWriter("C:\\Users\\Manjusri\\Desktop\\Hit Count Expr\\Zoo-"+iteration+".csv",false));
			
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
	 * @param b
	 * @param d
	 * @return
	 */
	private double getAverageSOMNeighbour(int b, int d, boolean average) //for matrices??
	{
		double value = 0;
		int numOfNeighbours = 0;
		double medianArray[] = new double[9];
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
					//if(!(i == b && j == d)) //removal of this condition would ensure that all nine elements are considered
					//{
						temp = SOM[b][d].getWEIGHTS().subtract(SOM[i][j].getWEIGHTS()).getNorm(); 
						medianArray[counter] = temp;
						value += temp;
						numOfNeighbours++;
						counter++;
					//}
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
			if(numOfNeighbours == 4)
			{
				retValue = (medianArray[1] + medianArray[2])/2;
			}
			else if(numOfNeighbours%2 == 6)
			{
				retValue = (medianArray[2] + medianArray[3])/2;
			}
		}
		
		if(average)
		{
			return (value/numOfNeighbours); //put this if average is needed
		}
		else
		{
			return retValue; // put if median is needed
		}		
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
	
}







































