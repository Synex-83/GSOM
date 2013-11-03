/**
 * 
 */
package maps.Structures;

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
			
			if(previous != null)
			{
				LINKS.add(new Edge(previous, current, LINK_NUMBERS));
				previous.addOutgoingLink(LINK_NUMBERS);
				current.addOutgoingLink(LINK_NUMBERS);
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
		if(!(current.getCurrentWinner().equals(winner)))
		{
			
			current.setCurrentWinner(winner);
			//once the node moves to the current winner every incomming and outgoing link for this particular
			//FSM node has to be recalculated.

		}
		else
		{
			System.out.println("SAME WINNER");
		}
		
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
