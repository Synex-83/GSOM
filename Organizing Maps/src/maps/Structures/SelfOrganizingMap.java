/**
 * @package		maps.Structures
 * @filename	SelfOrganizingMap.java
 */
package maps.Structures;

import java.util.StringTokenizer;

import org.apache.commons.math3.linear.ArrayRealVector;

/**
 * @author 		Manjusri Ishwara
 * @date   		Mar 31, 2013 - 12:34:39 AM
 * @type		SelfOrganizingMap
 */
public class SelfOrganizingMap {
	
	private Node[][] SOM = null;
	@SuppressWarnings("unused")
	private int GRID_OPTION = 0;
	private int INPUT_DIMENSION = 0;
	
	

	/**
	 * @param numberOfNodes as the total set of nodes in the final 2D map
	 * @param depth	as the depth or the number of rows in the map
	 * @param grid as the type of grid (0 = square, 1 = rectangle, 2 = hexagonal)
	 * @param inputDimension the dimension of the input data vector
	 */
	public SelfOrganizingMap(int numberOfNodes, int depth, int grid, int inputDimensison)
	{
		INPUT_DIMENSION = inputDimensison;
		
		if(grid == 0)
		{
			int side = (int)Math.sqrt(numberOfNodes);
			SOM = new Node[side][side];
			GRID_OPTION = grid;
			initialize();
		}
		else if(grid == 1)
		{
			int side = (int)numberOfNodes/depth;
			SOM = new Node[depth][side];
			GRID_OPTION = grid;
			initialize();
		}
		else if(grid == 2)
		{
			int side = (int)numberOfNodes/depth;
			SOM = new HexNode[depth][side];
			GRID_OPTION = grid;
			initialize();
		}
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
	 */
	public void trainSOM(String input)
	{
		String line = "";
		double temp[] = null;
		Node winner = null;
		StringTokenizer first = new StringTokenizer(input, "\n");		
		first.nextToken();		
		
		while(first.hasMoreTokens())
		{
			line = first.nextToken();
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
//				winner = setEuclideanAccumulatedValue(new ArrayRealVector(temp));
//				System.out.println("WINNER x =" + winner.getX() + " y= " + winner.getY());
//				System.out.println("*******************************");
			}
		}
	}
	
	/**
	 * @param input as the input vector presented to the network.
	 * @return the winner node of the iteration
	 * Similar to {@link #setAccumulatedValue(ArrayRealVector)} needs to use only one of these methods to select the
	 * winner. Euclidean measure takes the minimum value.
	 */
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
	 * Similar to {@link #setEuclideanAccumulatedValue(ArrayRealVector)} need to use only one of these methods to selec the
	 * winner. The multiplication based method takes the maximum value.
	 */ 
	private Node setAccumulatedValue(ArrayRealVector input) {
		
		double temp = 0.0;
		double maxSeen = 0.0;
		Node maxNode = null;
		
		System.out.println("Input Vector = " + input.toString());
		
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
		
		printSOM();
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
	
}







































