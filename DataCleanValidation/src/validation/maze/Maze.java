/**
 * 
 */
package validation.maze;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author   Manjusri Ishwara
 * @date     Aug 27, 2012 8:57:39 PM
 * @file     Maze.java
 * @package  validation.maze
 */
public class Maze {

	final int NUMBER_OF_JUNCTIONS = 56; //number junctions in the outer maze
	final int NUMBER_OF_INTERNALS = 8; //number of junctions in the inner maze
	
	final int[] junctionNumber = {1,5,2,1,5,2,
								  7,9,5,6,6,5,9,8,
								  3,8,3,2,1,4,7,4,
								  9,9,
								  1,9,6,2,1,6,9,2,
								  3,2,7,5,6,6,5,8,1,4,
								  1,6,4,3,2,1,4,3,6,2,
								  3,6,6,4}; //represents the type of each outer junction in the maze
	
	final int[] internalNodeNumbers = {1,6,6,2,8,7,7,8}; //Front 9 represent and internal node which is represented using an I in the data set.
	
	
	private static Stack<Decision> stk = new Stack<Decision>(); //stack keeps track of the previous moves made by the player.
	private int[][] copyOfNodeRelationships = null;
	
	Node allNodes[] = new Node[56];
	Node internal[] = new Node[8];
	/*
	 * Since the maze is not dynamic relationships can be defined using a multi-dimensional array with zeroth index being 
	 * unused to make consistent with the junction types defined.
	 */ 
	int[][] nodeRelationships = new int[NUMBER_OF_INTERNALS + NUMBER_OF_JUNCTIONS+1][5]; 
	
	String lineNumber = "";
	int junctionType = 0;
	int numberOfTheJunction = 0;
	String decision = "";
	
	public Maze()
	{
		createAllJunctions();
		createGraph("E:\\workspace\\GSOM\\DataCleanValidation\\src\\validation\\test\\graph.txt");
		createRelationships();
	}
	
	/**
	 * Creates nodes for each junction in the maze with there associated junction number.
	 */
	public void createAllJunctions()
	{
		for(int i = 0; i < NUMBER_OF_JUNCTIONS; i++)
		{
			allNodes[i] = new Node(i + 1);
			allNodes[i].setJunctionType(junctionNumber[i]);
		}
		
		for(int i = 0; i < NUMBER_OF_INTERNALS; i++)
		{
			//91 is used becuase of the zero based counter. The real deficit is 90 for internal junctions.
			internal[i] = new Node(i + 91);
			internal[i].setJunctionType(internalNodeNumbers[i]);
		}
	}
	
	/**
	 * Creates a graph structure with the relevant relationships specified in the input file.
	 * @param filename
	 */
	public void createGraph(String filename)
	{
		/*
		 * The junction numbers start from number 1. Since java arrays are being used note that 
		 * the arrays are zero based. Therefore an adjustment of +1 or -1 is required when accessing the arrays. 
		 */
		
		try
		{
			FileInputStream fstream = new FileInputStream(filename);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			StringTokenizer tokens;
			String strLine;
			int lineNumber = 0;

			while ((strLine = br.readLine()) != null)
			{
				lineNumber++;		
				tokens = new StringTokenizer(strLine, "\t");
				
				int i = 0;

				if(tokens.countTokens() == 5){
					while(tokens.hasMoreTokens())
					{
						nodeRelationships[lineNumber][i] = Integer.parseInt(tokens.nextToken().toString());
						i++;
					}
				}
				else
				{
					System.out.println("The data entry record not complete in line number " + lineNumber + ".");
				}
			}
			
			in.close();
			
			copyOfNodeRelationships = nodeRelationships;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Extrapolates the graph structure created in the junctions nodes created by the createAllJunctions() method.
	 */
	public void createRelationships()
	{
		Node temp = null;
		int value = 0;
		
		for(int i = 1;  i < nodeRelationships.length; i++)
		{
			if(nodeRelationships[i][0] >= 57)
			{
				temp = internal[i-57];
			}
			else
			{
				temp = allNodes[i-1];
			}
			
			for(int j = 1; j < nodeRelationships[i].length ; j++)
			{
				value = nodeRelationships[i][j];
				
				if(value > 0 && value <= 56)
				{
					switch (j) {
					case 1:temp.setNeighbourUp(allNodes[value-1]);
						break;
					case 2:temp.setNeighbourRight(allNodes[value-1]);
						break;
					case 3:temp.setNeighbourLeft(allNodes[value-1]);
						break;
					case 4:temp.setNeighbourDown(allNodes[value-1]);
						break;
					}
				}
				else if(value > 0 && value > 57) //91 because the numbering starts with 91 for internal junctions.
				{
					switch (j) {
					case 1:temp.setNeighbourUp(internal[value-91]);
						break;
					case 2:temp.setNeighbourRight(internal[value-91]);
						break;
					case 3:temp.setNeighbourLeft(internal[value-91]);
						break;
					case 4:temp.setNeighbourDown(internal[value-91]);
						break;
					}
				}
				else if(value == 0){
					switch (j) {
					case 1:temp.setNeighbourUp(null);
						break;
					case 2:temp.setNeighbourRight(null);
						break;
					case 3:temp.setNeighbourLeft(null);
						break;
					case 4:temp.setNeighbourDown(null);
						break;
					}
				}
			}
		}
		
		//System.out.println("Success");
	}
	
	/**
	 * Validate the data file presented conforms to the valid moves in the Pac-man maze.
	 * @param filename
	 */
	public void validateGameData(String filename, String parent)
	{
		boolean inGame = false; //the variable is used to identify whether the processing is in game.
		String temp;
		Decision decisionNode = null;
		int counter = 0;
		BufferedReader br  = null;
		
		try
		{	    
			FileInputStream fstream = new FileInputStream(parent +"\\"+filename);
			DataInputStream in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			StringTokenizer tokens;
			String strLine;
			
			while ((strLine = br.readLine()) != null)
			{
				tokens = new StringTokenizer(strLine);
				if(tokens.countTokens() == 1 && tokens.nextToken().toString().contains("##########"))
				{
					inGame = true;
					counter++;
					//counter = 0;
				}
				else if(tokens.countTokens() == 3)
				{
					int i = 0;
					while(tokens.hasMoreTokens())
					{
						temp = tokens.nextToken();
						++i;
						
						if(i%3 == 0 && temp.toString().contains("DONE"))
						{
							inGame =  false;
							allNodesVisited();
							System.out.println("LINK VALIDATION  " + allImportantLinksVisited());
							System.out.println("============================================");
							System.out.println("=========== One Game Complete ==============");
							System.out.println("============================================");
							stk.removeAllElements();
							resetVisitStatus();
							counter++;
							//counter = 0;
						}
						else if(i%3 == 0 && temp.toString().contains("LEVEL"))
						{
							//inGame =  true;
							//allNodesVisited();
							//System.out.println("One Game Complete ");
							stk.removeAllElements();
							//resetVisitStatus();
							counter++;
						}
					}
				}
				else if(tokens.countTokens() == 4 && inGame)
				{
					counter++;
					while(tokens.hasMoreTokens())
					{
						temp = tokens.nextToken();
						lineNumber = temp.toString();
						temp = tokens.nextToken();
						junctionType = Integer.parseInt(temp.toString());
						temp = tokens.nextToken();
						numberOfTheJunction = Integer.parseInt(temp.toString());
						temp = tokens.nextToken();
						decision = temp.toString();
						
						decisionNode = new Decision(lineNumber, junctionType, numberOfTheJunction, decision);

						//checks whether the move is possible
						if(isPossibleMove(decisionNode))
						{
							// checks whether the current move is a neighbor of the previous junction.
							if(!isNeighbour(decisionNode))
							{
								System.out.println("Invalid Move on Context" + " " + lineNumber+" " + 
													junctionType+" " + numberOfTheJunction + " " + decision + "\n" +
										 "Counter = " + counter );
							}
						}
						else
						{
							System.out.println("Impossible Move" + " " + lineNumber+" " +
													junctionType+" " + numberOfTheJunction + " " + decision+ "\n" +
													 "Counter = " + counter);
						}

					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println( "  " + counter); //error
			//Null pointer exceptions can result from having a lower case letter as the start of the decision string.
		}
		finally
		{
			try
			{
				br.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Checks whether the current decision is possible on the context of previous decisions.
	 * @param item
	 * @return
	 */
	public boolean isNeighbour(Decision item)
	{
		Decision current = item;
		Decision temp = null;
		Node neighbour = null;
		
		if(current.getLineNumber().contains("I"))
		{
			internal[current.getJunctionNumber() - 91].setVisited(true); //sets the node to have been visited in this game session
		}
		else
		{
			allNodes[current.getJunctionNumber()-1].setVisited(true); //sets the node to have been visited in this game session
		}
		
		
		if(stk.isEmpty())
		{
			stk.push(item);
			return true;
		}
		else
		{
			temp = stk.pop();
			
			//This if statement handles internal nodes or junction in the maze. However, its important to note that internal
			//junctions and the outer junctions are linked together via graph links.
			if(temp.getLineNumber().contains("I"))
			{
				//internal[temp.getJunctionNumber()-91] because of zero index.			
				
				switch (temp.getDecision()) {
				case 1:
					neighbour = internal[temp.getJunctionNumber()-91].getNeighbourRight();
					break;
				case 2:
					neighbour = internal[temp.getJunctionNumber()-91].getNeighbourLeft();
					break;
				case 3:
					neighbour = internal[temp.getJunctionNumber()-91].getNeighbourUp();
					break;
				case 4:
					neighbour = internal[temp.getJunctionNumber()-91].getNeighbourDown();
					break;
				}
				
				//Checks whether the previous junction is neighbor of the current junction
				if(neighbour.getJunctionNumber() == current.getJunctionNumber())
				{
					setLinkVisit(temp.getJunctionNumber(),current.getJunctionNumber());
					stk.push(current);
					return true;
				}
				else if(temp.getJunctionNumber() == current.getJunctionNumber())
				{
					//This if block is invoked if the player changes direction after he has passed a certain junction
					//and returns to the same junction before reaching its neighbor junctions.
					stk.push(current);
					return true;
				}
			}
			else
			{
				//This code block handles the outer maze junctions all the other comments mentioned in the if block remains 
				//valid.			
						
				switch (temp.getDecision()) {
				case 1:
					neighbour = allNodes[temp.getJunctionNumber()-1].getNeighbourRight();
					break;
				case 2:
					neighbour = allNodes[temp.getJunctionNumber()-1].getNeighbourLeft();
					break;
				case 3:
					neighbour = allNodes[temp.getJunctionNumber()-1].getNeighbourUp();
					break;
				case 4:
					neighbour = allNodes[temp.getJunctionNumber()-1].getNeighbourDown();
					break;
				}
				
				if(neighbour.getJunctionNumber() == current.getJunctionNumber())
				{
					setLinkVisit(temp.getJunctionNumber(),current.getJunctionNumber() );
					stk.push(current);
					return true;
				}
				else if(temp.getJunctionNumber() == current.getJunctionNumber())
				{
					stk.push(current);
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * @param origin
	 * @param end
	 */
	private void setLinkVisit(int origin, int end) 
	{
		// TODO Auto-generated method stub
		int real = origin;
		if(origin >= 90)
		{
			real = (origin - 90) + 56;
		}

		
		for(int i = 1; i < 5; i++)
		{
			if(copyOfNodeRelationships[real][i] == end)
			{
				copyOfNodeRelationships[real][i] = 0;
			}
		}
		
		
		int reale = end;
		if(end >= 90)
		{
			reale = (end - 90) + 56;
		}
		
		for(int i = 1; i < 5; i++)
		{
			if(copyOfNodeRelationships[reale][i] == origin)
			{
				copyOfNodeRelationships[reale][i] = 0;
			}
		}
		
	}

	/**
	 * 
	 */
	private boolean allImportantLinksVisited()
	{
		for(int i = 0; i < copyOfNodeRelationships.length ; i++)
		{
			for(int j = 1; j < 5; j++)
			{
				if(copyOfNodeRelationships[i][j] == 0 || copyOfNodeRelationships [i][j] >= 90)
				{
					continue;
				}
				else
				{
					return false;
				}
			}
		}	
		return true;
	}
	

	
	
	/**
	 * Checks whether the decision recorded is possible according to its corresponding junction type.
	 * @param item
	 * @return
	 */
	public boolean isPossibleMove(Decision item)
	{
		int decision = item.getDecision();
		int typeOfJunction = item.getJunctionType();
		
		switch (typeOfJunction) {
		
		case 1:
			if(decision == 3 || decision == 2)
			{
				return false;
			}
			break;
		case 2:
			if(decision == 3 || decision == 1)
			{
				return false;
			}
			break;
		case 3:
			if(decision == 4 || decision == 2)
			{
				return false;
			}
			break;
		case 4:
			if(decision == 4 || decision == 1)
			{
				return false;
			}
			break;
		case 5:
			if(decision == 3)
			{
				return false;
			}
			break;
		case 6:
			if(decision == 4)
			{
				return false;
			}
			break;		
		case 7:
			if(decision == 2)
			{
				return false;
			}
			break;
		case 8:
			if(decision == 1)
			{
				return false;
			}			
			break;
		}
		return true;
	}
	
	/**
	 * Checks whether a player has visited all the nodes/junctions in a completed game.
	 * @return
	 */
	public boolean allNodesVisited()
	{
		boolean majorJunction = true;

		for(int i = 0;  i < allNodes.length; i++)			
		{
			if(!allNodes[i].getVisited())
			{
				majorJunction = majorJunction & allNodes[i].getVisited();
				System.out.println(allNodes[i].getJunctionNumber() + " is not visited at least once");
			}
				
		}
		
		for(int i = 0;  i < internal.length; i++)			
		{
			if(!internal[i].getVisited())
			{
				//System.out.println(internal[i].getJunctionNumber() - 91 + " is not visited at least once [NO ISSUE]");
			}
				
		}
		
		return majorJunction;
	}
	
	/**
	 * Resets the visited status for all junctions.
	 */
	public void resetVisitStatus()
	{
		for(int i = 0;  i < allNodes.length; i++)			
		{
				allNodes[i].setVisited(false);
		}
		
		for(int i = 0;  i < internal.length; i++)			
		{
				internal[i].setVisited(false);
		}
		
		//System.out.println("Visit status reverted.");
	}
	
}
