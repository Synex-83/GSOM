/**
 * 
 */
package maps.Structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;


/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:07:34 PM
 * @type        FSMNode
 *
 */
public class FSMNode implements Serializable {

	class repeatNode{
		public int repeatNumber = 0;
		public int hitCount = 0;
		
		repeatNode(int number, int count)
		{
			repeatNumber = number;
			hitCount = count;
		}
	}
	
	private ArrayList<Integer> OUTGOING_LINKS = null;
	private ArrayList<Integer> INCOMING_LINKS = null;
	private ArrayList<Node> PREVIOUS_WINNERS = null;
	private ArrayList<repeatNode> REPEAT = new ArrayList<FSMNode.repeatNode>();
	
	private String SEQUENCE = "";
	private String EFFECTIVE_SEQUENCE = "";
	private int GROWTH_RADIUS = 0;
	private boolean FOCUS = false;
	private boolean HOLLOW = true;
	private boolean IS_REPEAT = false;
	private boolean IS_COMPOUND = false;
	private boolean IS_RUN = false;
	private int COMPOUND_COUNT = 0;
	private int NUMBER_OF_HITS = 0;
	
	private Node CURRENT_WINNER = null;
	private int INTIAL_SOLID_ITERATION = 0;
	
	/**
	 *
	 */
	public void addRepeat(int repeat)
	{
		setIsRepeat(true);
		
		Iterator<repeatNode> ite = REPEAT.iterator();
		repeatNode temp = null;
		repeatNode newNode = null;
		
		
		
		while(ite.hasNext())
		{
			temp = ite.next();
			
			if(temp.repeatNumber == repeat)
			{
				temp.hitCount++;
				newNode = temp;
			}
		}
		
		if(newNode == null)
		{
			REPEAT.add(new repeatNode(repeat, 1));
		}
	}
	
	/**
	 * @return
	 */
	public int getHits()
	{
		return NUMBER_OF_HITS;
		
	}
	
	/**
	 * 
	 */
	public void incrementHitCount()
	{
		NUMBER_OF_HITS++;
	}
	
	/**
	 * 
	 */
	public void resetNumberOfHits()
	{
		NUMBER_OF_HITS = 1;
	}
	
	/**
	 * @param current
	 */
	public void setIteration(int current)
	{
		INTIAL_SOLID_ITERATION = current;
	}
	
	/**
	 * @return
	 */
	public int getIteration()
	{
		return INTIAL_SOLID_ITERATION;
	}
	
	/**
	 * @return the PREVIOUS_WINNERS
	 */
	public ArrayList<Node> getPREVIOUS() {
		return PREVIOUS_WINNERS;
	}
	
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
	
	public String getEffectiveSequence() {
		return EFFECTIVE_SEQUENCE;
	}

	public void setEffectiveSequence(String sequence) {
		EFFECTIVE_SEQUENCE = sequence;
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
	public void setCurrentWinner(Node winner) 
	{
		//System.out.println("PREVIOUS WINNER:-X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
		PREVIOUS_WINNERS.add(CURRENT_WINNER);
		CURRENT_WINNER = winner;
		//System.out.println("CURRENT WINNER:-X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
		setHollow(true);
		IS_REPEAT = false; //reinitilizes the repeater with the movement of the node to new location.
		IS_COMPOUND = false;
		REPEAT.clear();
		resetNumberOfHits();
	}

	public void setIsRun(boolean value)
	{
		IS_RUN = value;
	}
	
	public boolean IsRun()
	{
		return IS_RUN;
	}

	/**
	 * @param winner
	 */
	public void setWinner(Node winner)
	{
		CURRENT_WINNER = winner;
		//System.out.println("WINNER SET :- X " + CURRENT_WINNER.getX() + " Y " + CURRENT_WINNER.getY());
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

	/**
	 * @return the iS_REPEAT
	 */
	public boolean isRepeat() {
		return IS_REPEAT;
	}

	/**
	 * @return
	 */
	public boolean isCOMPOUND() {
		return IS_COMPOUND;
	}

	/**
	 * @param value
	 */
	public void setCOMPOUND(boolean value) {
		IS_COMPOUND = value;
	}

	/**
	 * @param  value the iS_REPEAT to set
	 */
	public void setIsRepeat(boolean value) {
		IS_REPEAT = value;
	}
	
	public void printRepeat()
	{
		
		Iterator<repeatNode> itr = REPEAT.iterator();
		repeatNode temp1 = null;
		
		
		while(itr.hasNext())
		{
			temp1 = itr.next();

			System.out.println("Number " + temp1.repeatNumber + " Hit Count =" + temp1.hitCount);
		
		}
	}

	/**
	 * @return the COMPOUND_COUNT
	 */
	public int getCompoundCount() {
		return COMPOUND_COUNT;
	}

	/**
	 * @param cOMPOUND_COUNT the cOMPOUND_COUNT to set
	 */
	public void incrementCompoundCount() {
		
		if(this.IS_COMPOUND)
		{
			COMPOUND_COUNT++;
		}
		else
		{
			System.out.println("NON - COMPOUND");
		}
	}
	
}
