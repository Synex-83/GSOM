/**
 * 
 */
package maps.Structures;

import java.util.Vector;

import org.omg.CORBA.Current;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:07:34 PM
 * @type        FSMNode
 *
 */
public class FSMNode {

	private Vector<Integer> OUTGOING_LINKS = null;
	private Vector<Integer> INCOMING_LINKS = null;
	private Vector<Node> PREVIOUS_WINNERS = null;
	
	private String SEQUENCE = "";
	private int GROWTH_RADIUS = 0;
	
	private Node CURRENT_WINNER = null;
	
	/**
	 * @param sequence
	 */
	public FSMNode(String sequence)
	{
		SEQUENCE = sequence;
		PREVIOUS_WINNERS = new Vector<Node>();
		INCOMING_LINKS = new Vector<Integer>();
		OUTGOING_LINKS = new Vector<Integer>();
	}

	/**
	 * @return the sEQUENCE
	 */
	public String getSequence() {
		return SEQUENCE;
	}
	
	/**
	 * @param s the SEQUENCE to set
	 */
	public void setSequence(String s) {
		SEQUENCE = s;
	}
	
	/**
	 * @return the GROWTH_RADIUS
	 */
	public int getGrowthRadius() {
		return GROWTH_RADIUS;
	}
	
	/**
	 * @param radius the GROWTH_RADIUS to set
	 */
	public void setGrowthRadius(int radius) {
		GROWTH_RADIUS = radius;
	}

	/**
	 * @return the CURRENT_WINNER
	 */
	public Node getCurrentWinner() {
		return CURRENT_WINNER;
	}

	/**
	 * @param cURRENT_WINNER the winner to set
	 */
	public void setCurrentWinner(Node winner) {
		System.out.println("PREVIOUS WINNER:-X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
		PREVIOUS_WINNERS.add(CURRENT_WINNER);
		CURRENT_WINNER = winner;
		System.out.println("CURRENT WINNER:-X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
	}
	
	/**
	 * @param winner
	 */
	public void setWinner(Node winner)
	{
		CURRENT_WINNER = winner;
		System.out.println("WINNER SET :- X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
	}
	
	public void addIncomingLink(int id)
	{
		INCOMING_LINKS.add(id);
	}
	
	public void addOutgoingLink(int id)
	{
		OUTGOING_LINKS.add(id);
	}

}
