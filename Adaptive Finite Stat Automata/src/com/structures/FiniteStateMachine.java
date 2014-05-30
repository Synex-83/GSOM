/**
 * 
 */
package com.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 2:13:04 PM
 * @package com.structures
 * 
 */
public class FiniteStateMachine {

	private int NUMBER_OF_STATE = 0;
	private int PF = 0; // participant factor how many characters are considered for a given instance
	
	private ArrayList<FSMNode> SET_OF_NODES = null;
	private ArrayList<Link> SET_OF_LINKS = null;
	
	//=================== CONSTRUCTOR ===================
	
	
	public FiniteStateMachine(int participantFactor, String data)
	{
		PF = participantFactor;
		SET_OF_NODES = new ArrayList<FSMNode>();
		SET_OF_LINKS = new ArrayList<Link>();
		
		FSMNode previous = null;
		FSMNode current = null;
		Link tempLink = null;
		
		int linkCounter = 1;
		
		int counter = data.length() - (PF) + 1;
		for(int i = 0; i < counter; i++)
		{
			current = new FSMNode(data.substring(i,i+PF));
			
			//checks the availability of the state and if the state is not already found the subsequences is added
			//as a new state to the finite state machine.
			if(!stateExists(current))
			{
				SET_OF_NODES.add(current);
			}
			
			//check the availablity of the link  and if it is not already discovered it is created as a new link and 
			//added to the links list of the finite state machine
			if(previous!=null)
			{
				if(!linkExists(previous, current))
				{
					tempLink = new Link(linkCounter, previous, current);
					SET_OF_LINKS.add(tempLink);
					linkCounter++;
				}
			}
			
			previous = current;
			
		}
				
		System.out.println(SET_OF_NODES.size());
		System.out.println(SET_OF_LINKS.size());
		printListsOfStateMachine();
	
	}
	
	//=================== ACCESSOR METHODS =======================
	
	/**
	 * @return the NUMBER_OF_STATE
	 */
	public int getNumberOfStates() {
		return NUMBER_OF_STATE;
	}

	/**
	 * @return the sET_OF_NODES
	 */
	public ArrayList<FSMNode> getStates() {
		return SET_OF_NODES;
	}

	/**
	 * @return the sET_OF_LINKS
	 */
	public ArrayList<Link> getSET_OF_LINKS() {
		return SET_OF_LINKS;
	}
	
	//================== CLASS METHODS ============================
	
	/**
	 * Verifies whether a state with a given sequence is already in the set of nodes of the state machine.
	 * @param node
	 * @return
	 */
	private boolean stateExists(FSMNode node)
	{
		
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		FSMNode temp = null;
		
		
		while(nodeIter.hasNext())
		{
			temp = nodeIter.next();
			
			if(temp.getMappedSequence().equalsIgnoreCase(node.getMappedSequence()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Verifies whether a link between two states already exist in the state machine.
	 * @param link
	 * @return
	 */
	private boolean linkExists(FSMNode prev, FSMNode curr)
	{
		
		Iterator<Link> linkIter = SET_OF_LINKS.iterator();
		Link temp = null;
		boolean cummulativeDecision = false;
		
		//If size is zero there is no links identified. Therefore, a link can be added without any other check.
		if(SET_OF_LINKS.size() == 0)
		{
			return false;
		}
		
		while(linkIter.hasNext())
		{
			temp = linkIter.next();
			
			if(!(temp.getOriginator().getMappedSequence().equalsIgnoreCase(prev.getMappedSequence())
					&& temp.getDestination().getMappedSequence().equalsIgnoreCase(curr.getMappedSequence())))
			{
				cummulativeDecision = cummulativeDecision || false;
			}
			else
			{
				cummulativeDecision = cummulativeDecision ||true;
			}
		}
		
		
		return cummulativeDecision;
	}
	
	/**
	 * Prints the set of nodes and the set of links available in the finite state machine. No thresholding or 
	 * learning information is considered.
	 */
	private void printListsOfStateMachine()
	{
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		Iterator<Link> linkIter = SET_OF_LINKS.iterator();
		Link temp = null;
		
		while(nodeIter.hasNext())
		{
			System.out.println(nodeIter.next().getMappedSequence().toString());
		}
		
		while(linkIter.hasNext())
		{
			temp = linkIter.next();
			System.out.println(temp.getLinkId() + "  "+ temp.getOriginator().getMappedSequence().toString() + "  -->  " 
					+ temp.getDestination().getMappedSequence().toString());
		}
	}
	//================== PUBLIC METHODS =============================
	
	/**
	 * @param data 
	 */
	public void createFiniteStateMachine(String data)
	{

	}
}