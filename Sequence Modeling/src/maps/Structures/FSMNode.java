/**
 * 
 */
package maps.Structures;

import java.util.ArrayList;
import java.util.Vector;

import org.omg.CORBA.Current;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:07:34 PM
 * @type        FSMNode
 *
 */
public class FSMNode {

	private ArrayList<Integer> OUTGOING_LINKS = null;
	private ArrayList<Integer> INCOMING_LINKS = null;
	private ArrayList<Node> PREVIOUS_WINNERS = null;
	
	private String SEQUENCE = "";
	private int GROWTH_RADIUS = 0;
	private boolean FOCUS = false;
	private boolean HOLLOW = true;
	
	private Node CURRENT_WINNER = null;
	
	/**
	 * @return the OUTGOING_LINKS
	 */
	public ArrayList<Integer> getOUTGOING_LINKS() {
		return OUTGOING_LINKS;
	}

	/**
	 * @return the INCOMING_LINKS
	 */
	public ArrayList<Integer> getINCOMING_LINKS() {
		return INCOMING_LINKS;
	}
	
	/**
	 * @param sequence
	 */
	public FSMNode(String sequence)
	{
		SEQUENCE = sequence;
		PREVIOUS_WINNERS = new ArrayList<Node>();
		INCOMING_LINKS = new ArrayList<Integer>();
		OUTGOING_LINKS = new ArrayList<Integer>();
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
		setHollow(true);
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

	/**
	 * @return the FOCUS
	 */
	public boolean isFocused() {
		return FOCUS;
	}

	/**
	 * @param FOCUS the fOCUS to set
	 */
	public void setFocus(boolean focus) {
		FOCUS = focus;
	}

	/**
	 * @return the HOLLOW
	 */
	public boolean isHollow() {
		return HOLLOW;
	}

	/**
	 * @param HOLLOW the hOLLOW to set
	 */
	public void setHollow(boolean hollow) {
		HOLLOW = hollow;
	}
	
}
