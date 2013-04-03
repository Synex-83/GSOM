/**
 * 
 */
package gsom.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import Jama.*;

/**
 * @File 	Map.java
 * @author 	Manjusri Ishwara
 * @date   	Jul 28, 2012 - 5:52:29 PM
 * @package	gsom.core
 */
public class Map {

	private Node startNode = null; 			//zero-zero position of the Map
	private int inputDimensions = 0; 		//dimensions of the input data set
	private double growthThreshold = 0; 	//GT value of the map.
	private LinkedList<Node> setOfNodes = new LinkedList<Node>(); //Keeps the complete list of nodes available in the map.
	
	/**
	 * Initializes the map creation process
	 */
	public Map()
	{
		initializeStartingMap();	
	}
	
	/**
	 * Initializes a basic GSOM with four nodes.
	 */
	private void initializeStartingMap()
	{
		// The basic four nodes with relevant coordinates is initiated.
		Node node1 = new Node(0, 0);
		Node node2 = new Node(0, 1);
		Node node3 = new Node(1, 0);
		Node node4 = new Node(1, 1);
		
		//0,0 is considered the starting node and its neighbors are set according to their placement directions.
		startNode = node1;
		startNode.setNeighbourEast(node2);
		startNode.setNeighbourNorth(node3);
		
		node2.setNeighbourWest(node1);
		node2.setNeighbourNorth(node4);
		
		node3.setNeighbourSouth(node1);
		node3.setNeighbourEast(node4);
		
		node4.setNeighbourSouth(node2);
		node4.setNeighbourWest(node3);		
		
		setOfNodes.add(node1);
		setOfNodes.add(node2);
		setOfNodes.add(node3);
		setOfNodes.add(node4);
	}
	
	/**
	 * @param spreadFactor
	 * Set by the data analyst. This will control the growth of the overall map when the accumulated error
	 * of a give node in the map exceeds the growth threshold (GT). GT value is calculated using the formula
	 * GT = -D*ln(spreadFactor)  where D is the input dimensions.
	 */
	public void setGrowthThreshold(double spreadFactor)
	{
		growthThreshold = -(inputDimensions) * Math.log(spreadFactor);
	}
	
	/**
	 * Prints the current nodes in the map with their respective location, weight vector  and the accumulated error.
	 */
	public void printNetwork()
	{
		Queue<Node> foundNodes = new LinkedList<Node>();
		Queue<Node> visited = new LinkedList<Node>();
		Node north, east, south, west;
				
		foundNodes.add(startNode);
		
		while(!foundNodes.isEmpty())
		{
			Node node = foundNodes.peek();
			north = node.getNeighbourNorth();
			east = node.getNeighbourEast();
			south = node.getNeighbourSouth();
			west = node.getNeighbourWest();
			
			System.out.println(node.toString());
			System.out.println(node.returnWeightVector());
			
			if(!foundNodes.contains(north) && !visited.contains(north) && !(north instanceof EmptyNode))
			{
				foundNodes.add(north);
			}
				
			if(!foundNodes.contains(east) && !visited.contains(east)&& !(east instanceof EmptyNode))
			{
				foundNodes.add(east);
			}
			
			if(!foundNodes.contains(south) && !visited.contains(south) && !(south instanceof EmptyNode))
			{
				foundNodes.add(south);
			}
			
			if(!foundNodes.contains(west) && !visited.contains(west) && !(west instanceof EmptyNode))
			{
				foundNodes.add(west);
			}
					
			foundNodes.remove(node);
			
			if(!(node instanceof EmptyNode))
			{					
				visited.add(node);
			}
		}		
	}
	
	/**
	 * @param inputDimension
	 * Initializes the training process of the GSOM map. Input dimension represent the dimensionality of the input
	 * vector presented to each node.
	 */
	public void setInputDimension(int inputDimension)
	{
		inputDimensions = inputDimension;
		setWeights();
	}
	
	
	/**
	 * Sets up weight vectors for the initial set of nodes in the map. The dimensionality of the weight vector is
	 * determined by the dimensionality of the input vector.
	 */
	public void setWeights()
	{
		for (Node node : setOfNodes)
		{
			node.setWeightVector(inputDimensions);
		}
	}
	
	
	/**
	 * This method takes a data file and trains the GSOM using the data records.
	 * @param filename
	 * The filename of the data set used to train the GSOM.
	 */
	public void trainFromFile(String filename)
	{
		String inputVector;
		StringTokenizer dataPoints;
		String inputVectorElements[];
		DataRecord currentInputVector;
		Matrix inputVectorMatrix;
		double multiplicationResult;
		
		int counter = 0;
		
		if(checkCompatibleFile(filename)){
			try {
				FileInputStream fstream = new FileInputStream(filename);		
				DataInputStream in = new DataInputStream(fstream);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				inputVectorElements = new String[inputDimensions + 1];
				
				br.readLine(); //removes the heading line from data.
				
				while((inputVector = br.readLine()) != null)
				{
					dataPoints = new StringTokenizer(inputVector);
					counter = 0;
					
					while(dataPoints.hasMoreTokens())
					{
						inputVectorElements[counter] = dataPoints.nextToken();
						counter++;
					}					
					
					currentInputVector = createDataObject(inputVectorElements);
					inputVectorMatrix = new Matrix(currentInputVector.getDataValues(),1);
					
					for (Node node : setOfNodes)
					{
						multiplicationResult = Double.parseDouble((inputVectorMatrix.times(node.getWeights())).toString()); //Problem Line
						node.setMultiplicativeValue(multiplicationResult);
					}	
					
			
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	
	/**
	 * Examines the data file before it is used to train a GSOM. This method auto identifies the dimensions of the input vector and 
	 * makes sure that each data record is well formed.
	 * @param filename
	 * @return
	 * a boolean value to indicate that either the file is compatible with the trainning process or not.
	 */
	private boolean checkCompatibleFile(String filename) {
		// TODO Auto-generated method stub
		FileInputStream fstream;
		String line;
		StringTokenizer token;
		int headingCount = 0; //number of items in the heading
		int elementCount = 0; //number of items in the elements
		int counter = 1; // line counter
		try {
			fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			if((line = br.readLine()) != null)
			{
				token = new StringTokenizer(line, "\t");
				headingCount = token.countTokens();
			}
			
			while((line = br.readLine()) != null)
			{
				counter++;
				token = new StringTokenizer(line, "\t");
				elementCount = token.countTokens();
				
				if(headingCount != elementCount)
				{
					System.out.println("Map:checkCompatibleFile() -> Error has occured in line "+counter+ " where the number " +
							"of elements in the heading does not match the data.");
					return false;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		

		if(counter <= 1)
		{
			return false;
		}	
		
		this.setInputDimension(headingCount-1); // automatically set the input dimensions.
		
		
		
		return true;
	}

	public void train()
	{
		double[][] inputs = {{1, 0, 0, 0},
						  {0, 1, 0, 0},
						  {0, 0, 1, 0},
						  {0, 0, 0, 1}};
		
		Matrix[] input = {new Matrix(inputs[0],1), new Matrix(inputs[1],1), new Matrix(inputs[2],1), new Matrix(inputs[3],1)};
		
		for (Matrix element : input) 
		{
			for (Node node : setOfNodes)
			{
				 (element.times(node.getWeights())).print(2, 10);
			}
			
			break;
		}		
	}
	
	public DataRecord createDataObject(String inputVector[])
	{
		String element =  inputVector[0];
		double dataPart[] = new double[inputVector.length - 1];
		
		for(int i = 0 ; i < inputVector.length-1;  i++)
		{
			dataPart[i] = Double.parseDouble(inputVector[i+1]);
		}
		return new DataRecord(element, dataPart);
	}
	
	public boolean train(DataRecord inputVector)
	{
		Matrix currentInput = new Matrix(inputVector.getDataValues(), 1);
		
		return false;
	}
	
}
