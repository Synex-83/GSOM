/**
 * 
 */
package com.structures;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 2:13:04 PM
 * @package com.structures
 * 
 */
public class FiniteStateMachine {

	private int NUMBER_OF_STATE = 0;
	private int PF = 0; // participant factor how many characters are considered for a given instance
	
	private double LEARNING_RATE = 0.0;
	
	private ArrayList<FSMNode> SET_OF_NODES = null;
	private ArrayList<FSMNode> MATURE_STATES = null;
	private ArrayList<Link> SET_OF_LINKS = null;
	private ArrayList<Link> INITIALIZED_LINKS = null;
	
	//=================== CONSTRUCTOR ===================
	
	/**
	 * Constructs the finite state machine. Creates the states, links and maintains lists of incoming and 
	 * outgoing links from a given state. This is only pre-processing. No learning is taking place. This is 
	 * done to reduce the computational overhead of creating the states and links.
	 * 
	 * @param participantFactor
	 * @param option
	 * @param data
	 */
	public FiniteStateMachine(int participantFactor, int option, String data)
	{
		PF = participantFactor;
		SET_OF_NODES = new ArrayList<FSMNode>();
		SET_OF_LINKS = new ArrayList<Link>();
		MATURE_STATES = new ArrayList<FSMNode>();
		INITIALIZED_LINKS = new ArrayList<Link>();
		
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
				if(SET_OF_NODES.size() == 0)
				{
					current.setStartNode(true);
				}
				
				SET_OF_NODES.add(current);							
			}
			else
			{
				current = getExistingState(current);
			}
			
			//check the availability of the link  and if it is not already discovered it is created as a new link and 
			//added to the links list of the finite state machine
			if(previous!=null)
			{
				if(!linkExists(previous, current))
				{
					tempLink = new Link(linkCounter, previous, current);
					SET_OF_LINKS.add(tempLink);
					previous.setOutgoingLink(tempLink.getLinkId()); //sets the link as an outgoing link of previous
					current.setIncomingLink(tempLink.getLinkId()); //sets the link as an incoming link of previous
					linkCounter++;
				}
			}			
			
			previous = current;			
		}
				
		//printListsOfStateMachine(option);	
	}
	
	//=================== ACCESSOR METHODS =======================
	
	/**
	 * @return the NUMBER_OF_STATE
	 */
	public int getNumberOfStates() {
		return NUMBER_OF_STATE;
	}

	/**
	 * @return the SET_OF_NODES
	 */
	public ArrayList<FSMNode> getStates() {
		return SET_OF_NODES;
	}

	/**
	 * @return the SET_OF_LINKS
	 */
	public ArrayList<Link> getTransitions() {
		return SET_OF_LINKS;
	}
	
	/**
	 * @return the MATURE_STATES
	 */
	public ArrayList<FSMNode> getMatureStates()
	{
		return MATURE_STATES;
	}
	
	/**
	 * @return
	 */
	public double getLearningRate()
	{
		return LEARNING_RATE;
	}
	
	/**
	 * @param value
	 */
	public void setLearningRate(double value)
	{
		LEARNING_RATE = value;
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
	 * Retrieves the node from the set of states once existence is established.
	 * @param node
	 * @return
	 */
	private FSMNode getExistingState(FSMNode node)
	{
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		FSMNode temp = null;
		
		
		while(nodeIter.hasNext())
		{
			temp = nodeIter.next();
			
			if(temp.getMappedSequence().equalsIgnoreCase(node.getMappedSequence()))
			{
				break;
			}
		}
		
		return temp;
	}
	
	
	/**
	 * Retrieves the node from the set of states once existance is established using the subsequence represented
	 * by the state.
	 * @param sequence
	 * @return
	 */
	private FSMNode getExistingStateFromSequence(String  sequence)
	{
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		FSMNode temp = null;
		
		
		while(nodeIter.hasNext())
		{
			temp = nodeIter.next();
			
			if(temp.getMappedSequence().equalsIgnoreCase(sequence))
			{
				break;
			}
		}
		
		return temp;
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
	 * 
	 * option 	= 1 (Minimal Print)
	 * 			= 2 (Node with incoming outgoing link print. No link print.)
	 * 			= 3
	 * @param option
	 */
	private void printListsOfStateMachine(int option)
	{
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		Iterator<Link> linkIter = SET_OF_LINKS.iterator();
		
		int totalHits = 0;
		
		Link temp = null;
		FSMNode tempNode = null;
		
		while(nodeIter.hasNext())
		{
			tempNode = nodeIter.next();
			
			if(option == 1)
			{
				System.out.println("State: "+tempNode.getMappedSequence().toString() + "\tis start state = "+ tempNode.isStartNode()
						+ " \tHit Count = " + tempNode.getHits() + " \tIntensity = " + tempNode.getIntensity() 
						+ " \tMature Iteration = " +tempNode.getMaturePresentation() +" \t" + tempNode.isMature());
				
				totalHits += tempNode.getHits();
			}
			else if(option == 2)
			{
				System.out.println(tempNode.getMappedSequence().toString());
				tempNode.printIncomingOutgoingLinks();
			}
			else if(option == 3)
			{
				
				System.out.println("Number of States =\t" + SET_OF_NODES.size());
				System.out.println("Number of Transitions =\t" + SET_OF_LINKS.size());
			}
		}
		
		if(option == 1)
		{
			System.out.println("TOTAL HITS (M-metrices) =\t" + totalHits);
		}
		else if(option == 2)
		{
			while(linkIter.hasNext())
			{
				temp = linkIter.next();
				System.out.println(temp.getLinkId() + " \t"+ temp.getOriginator().getMappedSequence().toString() + "  -->  " 
					+ temp.getDestination().getMappedSequence().toString() + " \t" +temp.getIntensity());
			}
		}
	}
	
	/**
	 * Given the previous and the current states initializes the link between the states for learning.
	 * @param current
	 * @param previous
	 * @return 
	 */
	private Link processCurrentLink(FSMNode current, FSMNode previous)
	{
		FSMNode cur = current;
		FSMNode prev = previous;
		Link currentLink = null;
		double tempIntensity = 0;
		
		if(MATURE_STATES.size() >= 2 && cur.isMature() && prev.isMature())
		{	
			 currentLink = getLink(prev.getOutgoingLink(),cur.getMappedSequence());
		}
		
		if(currentLink != null &&  !currentLink.isInitialized())
		{
			currentLink.setIsInitialized(true);
			
			tempIntensity = LEARNING_RATE*((currentLink.getOriginator().getIntensity() + currentLink.getDestination().getIntensity()));
			currentLink.setIntensity(tempIntensity/2);
			
			INITIALIZED_LINKS.add(currentLink);
		}
		
		return currentLink;
	}
	
	/**
	 * Return if a link exists in the outlinks that has the destination subsequence specified.
	 * @param outLinks
	 * @param destinationSequence
	 * @return
	 */
	private Link getLink(ArrayList<Integer> outLinks, String destinationSequence)
	{
		Link temp = null;
		
		for(int i = 0; i < outLinks.size(); i++ )
		{
			temp = SET_OF_LINKS.get(outLinks.get(i) - 1);
			if(temp.getDestination().getMappedSequence().equalsIgnoreCase(destinationSequence))
			{
				return temp;
			}
		}
		
		return null;
	}
	//================== PUBLIC METHODS =============================
	
	/**
	 * Prints the items included in the state machine
	 * 
	 * option 	= 1 (Minimal Print with state information)
	 * 			= 2 (Node with incoming outgoing link print. No link print.)
	 * 			= 3 (Basic statistics. Number of states and the number of lists.)
	 * @param option
	 */
	public void printStateMaching(int option)
	{
		printListsOfStateMachine(option);
	}
	
	/**
	 * @param data
	 * @param threshold
	 * @param history
	 * @param decayFactor
	 */
	public void createFiniteStateMachine(String data, int threshold, int history, double decayFactor, double eta)
	{
		String currentSubSequence = null;
		FSMNode selectedState = null;
		FSMNode previous = null;
		Link activeLink = null;
				
		LEARNING_RATE = eta;
		int counter = data.length() - (PF) + 1;
		for(int i = 0; i < counter; i++)
		{
			currentSubSequence = data.substring(i,i+PF);
			selectedState = getExistingStateFromSequence(currentSubSequence);			
			selectedState.incremetHitCounter();
			
			if(selectedState.getHits() >= threshold && !selectedState.isMature())
			{
				selectedState.setMature(true);
				selectedState.setMaturePresentation(i);
				
				MATURE_STATES.add(selectedState);
			}
			
			activeLink = processCurrentLink(selectedState,previous); //adds newly initialized links
			adjustStateIntensity(currentSubSequence, decayFactor,threshold, history); //adjusts the intensity of nodes
			
			adjustLinkIntensity(activeLink,i);
			
			
			
			previous = selectedState;
		}
	}
	
	/**
	 * Adjust the intensity of the links using learning. 
	 * @param activeLink
	 */
	private void adjustLinkIntensity(Link activeLink, int presentation)
	{
		// TODO Auto-generated method stub
		Iterator<Link> linkIter = INITIALIZED_LINKS.iterator();
		Link temp = null;
		double intensityContribution = 0.0;
		
		while(linkIter.hasNext())
		{
			 temp = linkIter.next();
			 
			 if(temp.equals(activeLink))
			 {
				// System.out.println("SKIP");
				 intensityContribution = (LEARNING_RATE*(temp.getOriginator().getIntensity() + temp.getDestination().getIntensity()))/2;
				 temp.setIntensity((temp.getIntensity() + intensityContribution));
			 }
			 else
			 {
				 //temp.setIntensity(temp.getIntensity()/20000);
			 }
		}
	}

	/**
	 * Increments the intensity value of the current state by a specified fixed value and reduces the intensity of
	 * other states by a fixed value. Rule of thumb increment value > decrement value.
	 * @param sequence
	 * @param decayValue
	 * @param skips number of skips before intensity decay takes place
	 */
	public void adjustStateIntensity(String sequence, double decayValue, int threshold, int skips)
	{
		Iterator<FSMNode> nodeIter = SET_OF_NODES.iterator();
		FSMNode tempNode = null;
		
		while(nodeIter.hasNext())
		{			
			tempNode = nodeIter.next();
			
			if(tempNode.getMappedSequence().equalsIgnoreCase(sequence)) //&& tempNode.getHits() < threshold used to check intensity at threshold 
			{
				tempNode.increaseIntensity(1.0);
				tempNode.setSkips(skips);
				
				System.out.println(sequence+"\t"+tempNode.getIntensity());
			}
			else if(tempNode.getSkips() > 0) //  && tempNode.getHits() < threshold
			{
				tempNode.decreseSkipCount();
			}
			else if(tempNode.getSkips() == 0)// && tempNode.getHits() < threshold
			{
				tempNode.decayIntensity(decayValue);
			}
			
		}
	}

}
