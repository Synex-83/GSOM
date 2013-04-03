/**
 * 
 */
package validation.maze;

/**
 * @author   Manjusri Ishwara
 * @date     Aug 27, 2012 7:53:53 PM
 * @file     Node.java
 * @package  validation.maze
 */

public class Node {

	private Node Neighbour_Up = null;
	private Node Neighbour_Right = null;
	private Node Neighbour_Left = null;
	private Node Neighbour_Down = null ;
	private int junction_Number = 0;
	private int junction_type = 0;
	private boolean visited = false;


	
	/**
	 * Default constructor only to be invoked by the EmptyNode class upon creating place holder nodes.
	 */
	public Node()
	{
		
	}

	/**
	 * @param junctionNumber
	 * Initializes a junction point in the Pac-Man maze with its associated junction number.
	 */
	public Node(int junctionNumber)
	{
		junction_Number = junctionNumber;
		Neighbour_Up = null;
		Neighbour_Right = null;
		Neighbour_Left = null;
		Neighbour_Down = null;
	}
	
	/**	
	 * @param right
	 * Sets the right neighbor of the corresponding junction
	 */
	public void setNeighbourRight(Node right)
	{
		Neighbour_Right = right;
	}
	
	/**
	 * @return
	 * Gets the right neighbor of the corresponding junction
	 */
	public Node getNeighbourRight()
	{
		return Neighbour_Right;
	}
	
	/**
	 * @param Left
	 * Sets the left neighbor of the corresponding junction
	 */
	public void setNeighbourLeft(Node left)
	{
		Neighbour_Left = left;
	}
	
	/**
	 * @return
	 * Gets the left neighbor of the corresponding junction
	 */
	public Node getNeighbourLeft()
	{
		return Neighbour_Left;
	}
	
	/**
	 * @param Up
	 * Sets the above neighbor of the corresponding junction
	 */
	public void setNeighbourUp(Node up)
	{
		Neighbour_Up = up;
	}
	
	/**
	 * @return
	 * Gets the above neighbor of the corresponding junction
	 */
	public Node getNeighbourUp()
	{
		return Neighbour_Up;
	}
	
	/**
	 * @param Down
	 * Sets the below neighbor of the corresponding junction
	 */
	public void setNeighbourDown(Node down)
	{
		Neighbour_Down = down;
	}
	
	/**
	 * @return
	 * Gets the below neighbor of the corresponding junction
	 */
	public Node getNeighbourDown()
	{
		return Neighbour_Down;
	}
	

	/**
	 * @param Up
	 * @param Right
	 * @param Down
	 * @param Left
	 */
	public void setNeighbours(Node Up, Node Right, Node Down, Node Left)
	{
		Neighbour_Up = Up;
		Neighbour_Right = Right;
		Neighbour_Down = Down;
		Neighbour_Left = Left;			
	}
	
	/**
	 * @return
	 * Returns the junction number of the decision
	 */
	public int getJunctionNumber()
	{
		return junction_Number;
	}
	
	/**
	 * @return
	 * Returns the type of junctions associated with the decision
	 */
	public int getJunctionType()
	{
		return junction_type;
	}
	
	/**
	 * @param junctionType
	 * Set the junction type of the decision
	 */
	public void setJunctionType(int junctionType )
	{
		junction_type = junctionType;
	}
	
	/**
	 * @return
	 * Returns the visited value of the current junction.
	 */
	public boolean getVisited()
	{
		return visited;
	}
	/**
	 * Set the boolean parameter which checks the junction is being visited.
	 * @param value
	 */
	public void setVisited(boolean value)
	{
		visited = value;
	}
}
