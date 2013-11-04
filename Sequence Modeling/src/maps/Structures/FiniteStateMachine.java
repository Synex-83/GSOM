/**
 * 
 */
package maps.Structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:58:32 PM
 * @type        FiniteStateMachine
 *
 */
public class FiniteStateMachine {
	
	private Vector<FSMNode> FSM = null;
	private Vector<Edge> LINKS = null;
	
	private int LINK_NUMBERS = 0;
	
	public FiniteStateMachine()
	{
		FSM = new Vector<FSMNode>();
		LINKS = new Vector<Edge>();
	}

	/**
	 * @return the fSM
	 */
	public Vector<FSMNode> getFSM() 
	{
		return FSM;
	}

	/**
	 * @param fSM the fSM to set
	 */
	public void setFSM(Vector<FSMNode> fSM)
	{
		FSM = fSM;
	}


	/**
	 * @param node
	 * @param winner
	 * 
	 * Searches the whole vector to verify there is no node with the same sequence. If non is found the new node is appended to the end of the 
	 * list. If the sequence exists the data in the sequence is updated to suite the new position of the winner.
	 */
	public void addUpdateNode(FSMNode current, FSMNode previous, Node winner)
	{
		Iterator<FSMNode> itr = FSM.iterator();
		Boolean addNewNode = true;
		FSMNode temp = null;
		
		while(itr.hasNext())
		{
			temp = itr.next();
			
			if((temp.getSequence().trim()).equals(current.getSequence().trim()) )
			{
				System.out.println("NODE EXISTS:-" + temp.getSequence());
				update(temp,previous,winner); //trigger link update
				addNewNode = false;
				break;
			}
		}
		
		if(addNewNode)
		{
			//System.out.println("NEW NODE ADDED:-" + sequence);
			current.setWinner(winner); 
			FSM.add(current);
			
			if(previous != null && !previous.getSequence().equals(current.getSequence())) //prevents the AAA -> AAA -> AAA
			{
				System.out.println("LINK ADDED =============================");
				LINKS.add(new Edge(previous, current, LINK_NUMBERS));//no length init
				previous.addOutgoingLink(LINK_NUMBERS);
				current.addIncomingLink(LINK_NUMBERS);
			}
						
			LINK_NUMBERS++;
		}
	}

	/**
	 * @param temp
	 * @param winner
	 */
	private void update(FSMNode current, FSMNode previous, Node winner) 
	{
		int distance = 0;
		if(!(current.getCurrentWinner().equals(winner)) && !(previous.getSequence().equals(current.getSequence())))
		{
			
			current.setCurrentWinner(winner);
			//once the node moves to the current winner every incoming and outgoing link for this particular
			//FSM node has to be recalculated.
			updateLinks(current,winner);

		}
		else
		{
			System.out.println("SAME WINNER");
		}
		
	}
	

	/**
	 * @param current
	 * @param winner
	 */
	private void updateLinks(FSMNode current, Node winner) {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> incoming = current.getINCOMING_LINKS();
		incoming.addAll(current.getOUTGOING_LINKS());
		
		int temp1 = 0;
	
		Iterator<Integer> itr1 = incoming.iterator();

		
		while(itr1.hasNext())
		{
			temp1 = (Integer) itr1.next();
			 
			for(int i = 0; i < LINKS.size(); i++)
			{
				if(temp1 == LINKS.elementAt(i).getEdgeID())
				{
					fixDistances(LINKS.elementAt(i), winner); //only applicable in 2D case.
				}
			}
		}
		
		
	}

	/**
	 * @param elementAt
	 * @param winner
	 */
	private void fixDistances(Edge edge, Node winner)
	{
		// TODO Auto-generated method stub
		
		double diffX = Math.pow(edge.getOrigin().getCurrentWinner().getX() - edge.getDestination().getCurrentWinner().getX(),2);
		double diffY = Math.pow(edge.getOrigin().getCurrentWinner().getY() - edge.getDestination().getCurrentWinner().getY(),2);
		
		
		edge.setEdgeLength(Math.sqrt(diffX + diffY));
		
	}

	public void addUpdateEdge(FSMNode current, FSMNode previous, int id)
	{
		Iterator<Edge> itr = LINKS.iterator();
		Boolean addNewLink = true;
		Edge temp  = null;
		
		
		while(itr.hasNext())
		{
			temp = itr.next();
			
			if((temp.getOrigin().getSequence().trim()).equals(previous.getSequence().trim()) 
					&& (temp.getDestination().getSequence().trim()).equals(current.getSequence().trim()) )
			{
				System.out.println("LINK EXISTS:-" + temp.getEdgeID());
				update(temp,previous); 
				addNewLink = false;
				break;
			}
		}
		
		if(addNewLink)
		{
		//	System.out.println("NEW NODE ADDED:-" + sequence);
		//	node.setWinner(winner);

		}
	}

	/**
	 * @param temp
	 * @param previous
	 */
	private void update(Edge edge, FSMNode previous) {
		
		//if(edge.getDestination())
		// TODO Auto-generated method stub
		
	}
}
