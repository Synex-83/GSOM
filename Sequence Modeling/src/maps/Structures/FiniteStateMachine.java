/**
 * 
 */
package maps.Structures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

import maps.Util.FileProcessing;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:58:32 PM
 * @type        FiniteStateMachine
 *
 */
public class FiniteStateMachine {
	
	private Vector<FSMNode> FSM = null;
	private Vector<Node> PREVIOUS = null;
	private Vector<Edge> LINKS = null;
	private int THRESHOLD = 0;
	private int ITERATION = 0;
	
	private int LINK_NUMBERS = 0;
	private int FILE_OPTION = 0;
	private int REPEAT= 0;
	
	public FiniteStateMachine(int threhold, int iteration, int fileOption)
	{
		FSM = new Vector<FSMNode>();
		LINKS = new Vector<Edge>();
		THRESHOLD = threhold;
		ITERATION = iteration;
		FILE_OPTION = fileOption;
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
	public FSMNode addUpdateNode(FSMNode current, FSMNode previous, Node winner)
	{
		Iterator<FSMNode> itr = FSM.iterator();
		Boolean addNewNode = true;
		FSMNode temp = null;
		
		while(itr.hasNext()) //averts the problem of repeating sequence. Better way available?
		{
			temp = itr.next();
			
			if((temp.getSequence().trim()).equals(current.getSequence().trim()) )
			{
				//System.out.println("NODE EXISTS:-" + temp.getSequence()); //should return temp upon finding
				current = temp; //object equivalence will fix the issue.				
				update(current,previous,winner); //trigger link update
				addNewNode = false;
				break;
			}
		}
		
		if(addNewNode)
		{
			//System.out.println("NEW NODE ADDED:-" + sequence);
			current.setWinner(winner);
			current.setFocus(true);
			FSM.add(current);
		
			
			if(previous != null && !previous.getSequence().equals(current.getSequence())) //prevents the AAA -> AAA -> AAA
			{
			//	System.out.println("LINK ADDED =============================");
				LINKS.add(new Edge(previous, current, LINK_NUMBERS));//no length init
				previous.addOutgoingLink(LINK_NUMBERS);
				current.addIncomingLink(LINK_NUMBERS);
			}
						
			LINK_NUMBERS++;
		}
		
		return current;
	}

	/**
	 * @param temp
	 * @param winner
	 */
	private void update(FSMNode current, FSMNode previous, Node winner) 
	{
		int distance = 0;
		current.setFocus(true);
		

		
		if(previous!=null &&  !(current.getCurrentWinner().equals(winner)) && !(previous.getSequence().equals(current.getSequence()))) //previous!=null && 
		{
			
			current.setCurrentWinner(winner);
			//once the node moves to the current winner every incoming and outgoing link for this particular
			//FSM node has to be recalculated.
			updateLinks(current,winner);
			
			if(!(linkExists(current, previous)) && !(current.getSequence().contains("XX")))
			{
				//System.out.println("LINK ADDED =============================");
				LINKS.add(new Edge(previous, current, LINK_NUMBERS));//no length init
				previous.addOutgoingLink(LINK_NUMBERS);
				current.addIncomingLink(LINK_NUMBERS);
				
				LINK_NUMBERS++;
				REPEAT = 0;
			}
		}
		else if((current.getCurrentWinner().equals(winner)) && (previous.getSequence().equals(current.getSequence())))
		{
			REPEAT++;
			current.addRepeat(REPEAT);
			//System.out.println("============================================================ADD REPEAT" + REPEAT);
		}
		else
		{
			//System.out.println("SAME WINNER");
			linkExists(current, previous); //goda beheth...should update the hits properly per each call.
			REPEAT = 0;
		}
		
		//System.out.println("######################################  Sequence " + current.getSequence() + " = " + current.getCurrentWinner().getNumberOfHits());
		
		if(current.getCurrentWinner().getNumberOfHits() >= THRESHOLD && current.isHollow())
		{
			
/*			if(previous==null)
			{
				current.setCurrentWinner(winner);
			}*/
			current.setHollow(false);
			//System.out.println("Sequence " + current.getSequence() + " IS CONVERTED TO SOLID");
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
		
		Collections.sort(incoming);
	
		Iterator<Integer> itr1 = incoming.iterator();

		
		while(itr1.hasNext())
		{
			temp1 = (Integer) itr1.next();
			 
			for(int i = 0; i < LINKS.size(); i++)
			{
				if(temp1 == LINKS.elementAt(i).getEdgeID())
				{
					fixDistances(LINKS.elementAt(i), winner); //only applicable in 2D case. put break after this
					
					if(itr1.hasNext())
					{
						temp1 = (Integer) itr1.next(); //fixes the issue of a break and multiple for loops.
					}
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
		// Wrong calculation.....becuase one is in origin and the other in destination
		
		double diffX = Math.pow(edge.getOrigin().getCurrentWinner().getX() - edge.getDestination().getCurrentWinner().getX(),2);
		double diffY = Math.pow(edge.getOrigin().getCurrentWinner().getY() - edge.getDestination().getCurrentWinner().getY(),2);
		
		
		edge.setEdgeLength(Math.sqrt(diffX + diffY));
		
	}

	/**
	 * @param current
	 * @param previous
	 * @param id
	 */
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
	
	/**
	 * 
	 * @param current
	 * @param previous
	 * @return
	 */
	private boolean linkExists(FSMNode current, FSMNode previous)
	{
		Iterator<Edge> ite = LINKS.iterator();
		Edge temp  = null;
		
		while(ite.hasNext())
		{
			temp = ite.next();
			
			if(previous != null &&  temp.getOrigin().getSequence().equals(previous.getSequence())) //previous != null && 
			{		
				if(temp.getDestination().getSequence().equals(current.getSequence()))
				{
					temp.incrementNumberOfHits();
					//System.out.println("HERE");
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public void printLinks()
	{
		Iterator<Edge> ite = LINKS.iterator();
		Edge temp  = null;
		
		while(ite.hasNext())
		{
			temp = ite.next();
			
			System.out.println("##############################################################################");
			System.out.println("EDGE ID = " + temp.getEdgeID());
			System.out.println("EDGE INTENSITY = " + temp.getIntensity());
			System.out.println("EDGE HITS = " + temp.getNumberOfHits());
			System.out.println("EDGE LENGTH = " + temp.getEdgeLength());
			System.out.println("ORIGIN SEQUENCE = " + temp.getOrigin().getSequence());
			System.out.println("ORIGIN X = " + temp.getOrigin().getCurrentWinner().getX() +" ORIGIN Y =" + temp.getOrigin().getCurrentWinner().getY());
			System.out.println("DESTINATION SEQUENCE = " + temp.getDestination().getSequence());
			System.out.println("DESTINATION X = " + temp.getDestination().getCurrentWinner().getX() +" DESTINATION Y =" + temp.getDestination().getCurrentWinner().getY());
			System.out.println("******************************************************************************");
		}
	}

	/**
	 * @param current
	 */
	public void updateEdgeIntensity(FSMNode current, FSMNode previous, Node winner) {
		
		ArrayList<Integer> incoming = current.getINCOMING_LINKS();
		//incoming.addAll(current.getOUTGOING_LINKS());
		
		int temp1 = 0;
		Edge edge = null;
		
		Collections.sort(incoming);
	
		Iterator<Integer> itr1 = incoming.iterator();

		
		while(itr1.hasNext())
		{
			temp1 = (Integer) itr1.next();
			 
			for(int i = 0; i < LINKS.size(); i++)
			{
				if(temp1 == LINKS.elementAt(i).getEdgeID())
				{
					edge = LINKS.elementAt(i);
					
					//(current.getCurrentWinner().equals(winner)) &&
					
					if( !(previous.getSequence().equals(current.getSequence())) && previous.isFocused())
					{
						//System.out.println("CORRECT EDGE");
						edge.setIntensity((edge.getOrigin().getCurrentWinner().getIntensity() + edge.getDestination().getCurrentWinner().getIntensity())/2);
						
						previous.setFocus(false);
						//think of intensity logic and solidifying is the loop correct??? is the modification correct???
					}
					
					if(itr1.hasNext())
					{
						temp1 = (Integer) itr1.next(); //fixes the issue of a break and multiple for loops.
					}
				}
			}
		}		
	}

	/**
	 * @param currentPresentationNumber
	 * @param presentationNumber
	 */
	public void edgeIntesityDecay(int currentPresentationNumber, int presentationNumber) {
		
		Iterator<Edge> ite = LINKS.iterator();
		double factor = (double)currentPresentationNumber/presentationNumber;
		Edge temp = null;
		double intensity = 0;
		
		while(ite.hasNext())
		{
			temp = ite.next();
			intensity = (temp.getIntensity())*Math.exp(-factor);
			//System.out.println("FACTOR = " + factor);
			//System.out.println("EDGE NUMBER = " + temp.getEdgeID() + " DECAYED INTENSITY = " + intensity );
			
			if(intensity <= 0.0)
			{
				temp.setDecayedIntensity(0.0);	
			}
			else
			{
				temp.setDecayedIntensity(intensity);	
			}	
		}
		
	}

	/**
	 * 
	 */
	public void printSummary() 
	{
		Iterator<Edge> ite = LINKS.iterator();
		Iterator<FSMNode> itr = FSM.iterator();
		Iterator<FSMNode> itr2 = FSM.iterator();
		Iterator<FSMNode> itr3 = FSM.iterator();
		Queue<FSMNode> solidNode = new LinkedList<FSMNode>();
		
		Edge temp = null;
		FSMNode temp1 = null;

		
		System.out.println("==============================================================");
		System.out.println("==============================================================");
		System.out.println("THRESHOLD NUMBER OF HITS =" + THRESHOLD);
		
		System.out.println("********             SOLID SEQUENCES                  ********");
		while(itr.hasNext())
		{
			temp1 = itr.next();

			if(!temp1.isHollow())
			{
				System.out.println("Sequence " + temp1.getSequence() + " X =" + temp1.getCurrentWinner().getX() + "\tY =" + temp1.getCurrentWinner().getY() + "\tHITS =" + temp1.getCurrentWinner().getNumberOfHits());
				solidNode.add(temp1);
				
			}
		}
		
		System.out.println("********             HOLLOW SEQUENCES                  ********");
		while(itr2.hasNext())
		{
			temp1 = itr2.next();

			if(temp1.isHollow())
			{
				System.out.println("Sequence " + temp1.getSequence() + " X =" + temp1.getCurrentWinner().getX() + "\tY =" + temp1.getCurrentWinner().getY() + "\tHITS =" + temp1.getCurrentWinner().getNumberOfHits());
			//	solidNode.add(temp1);
				
			}
		}
		
		System.out.println("********             REPEAT SEQUENCES                  ********");
		while(itr3.hasNext())
		{
			temp1 = itr3.next();

			if(temp1.isRepeat())
			{
				System.out.println("Sequence " + temp1.getSequence() + " X =" + temp1.getCurrentWinner().getX() + "\tY =" + temp1.getCurrentWinner().getY() + "\tHITS =" + temp1.getCurrentWinner().getNumberOfHits());
				temp1.printRepeat();
			//	solidNode.add(temp1);
				
			}
		}
		
		
		
		
		System.out.println("********         LINKS (ORIGIN -> DIRECTION)          ********");
		while(ite.hasNext())
		{
			temp = ite.next();		
			
			if(solidNode.contains(temp.getOrigin()) || solidNode.contains(temp.getDestination()))
			{
				System.out.println(temp.getOrigin().getSequence() +" --> " + temp.getDestination().getSequence());
			}	
		}
		
		System.out.println("********         PREVIOUS WINNERS        ********");
		Iterator<FSMNode> itr4 = solidNode.iterator();
		ArrayList<Node> tt = new ArrayList<Node>();
		
		while(itr4.hasNext())
		{
			temp1 = itr4.next();		
			
			tt = temp1.getPREVIOUS();
			
			
			System.out.println("++++++++++++++++++++++++++++  " + temp1.getSequence() + "  ++++++++++++++++++++++++++++");
			int size =tt.size();
            for(int j = 0; j < size ; j++)
            {
                System.out.println("X = " + tt.get(j).getX() + " Y = " + tt.get(j).getY());
            }
		}
		
	}
	

	/**
	 * 
	 */
	public void writeSummaryToFile() 
	{
		Iterator<Edge> ite = LINKS.iterator();
		Iterator<FSMNode> itr = FSM.iterator();
		Iterator<FSMNode> itr2 = FSM.iterator();
		Queue<FSMNode> solidNode = new LinkedList<FSMNode>();
		
		Edge temp = null;
		FSMNode temp1 = null;
		FileWriter write = null;
		
		try
		{
			
			
			if(FILE_OPTION == 1)
			{
				write = new FileWriter("ABC-Sequence.txt",true);
			}
			else if(FILE_OPTION == 2)
			{
				write = new FileWriter("ABC-8-Sequences.txt",true);
			}
			else if(FILE_OPTION == 3)
			{
				write = new FileWriter("ABCD-Sequences.txt",true);
			}
			else if(FILE_OPTION == 4)
			{
				write = new FileWriter("Game-Scaled.txt",true);
			}
			else if(FILE_OPTION == 5)
			{
				write = new FileWriter("Game.txt",true);
			}
			
			
			
			BufferedWriter writer = new BufferedWriter(write);
			
			writer.write("========================     " + ITERATION + "      =====================\n");
			writer.newLine();
			writer.write("==============================================================\n");
			writer.newLine();
			writer.write("==============================================================\n");
			writer.newLine();
			writer.write("THRESHOLD NUMBER OF HITS =" + THRESHOLD +"\n");
			writer.newLine();
			writer.write("********             SOLID SEQUENCES                  ********\n");
			writer.newLine();
			
			while(itr.hasNext())
			{
				temp1 = itr.next();

				if(!temp1.isHollow())
				{
					writer.write("Sequence " + temp1.getSequence() + " X =" + temp1.getCurrentWinner().getX() + " Y =" + temp1.getCurrentWinner().getY() + " HITS =" + temp1.getCurrentWinner().getNumberOfHits() + "\n");
					writer.newLine();
					solidNode.add(temp1);
				
				}
			}
		
		
			writer.write("********         LINKS (ORIGIN -> DIRECTION)          ********\n");
			writer.newLine();
			while(ite.hasNext())
			{
				temp = ite.next();		
			
				if(solidNode.contains(temp.getOrigin()) && solidNode.contains(temp.getDestination()))
				{
					writer.write(temp.getOrigin().getSequence() +" --> " + temp.getDestination().getSequence() + "\n");
					writer.newLine();
				}	
			}
			
			System.out.println("********         PREVIOUS WINNERS        ********");
			Iterator<FSMNode> itr4 = solidNode.iterator();
			ArrayList<Node> tt = new ArrayList<Node>();

			while(itr4.hasNext())
			{
				temp1 = itr4.next();		

				tt = temp1.getPREVIOUS();


				System.out.println("++++++++++++++++++++++++++++  " + temp1.getSequence() + "  ++++++++++++++++++++++++++++");
				int size =tt.size();
	            for(int j = 0; j < size ; j++)
	            {
	                System.out.println("X = " + tt.get(j).getX() + " Y = " + tt.get(j).getY());
	            }
			}
			
			writer.flush();
			write.close();
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			System.out.println("DONE");
		}
		
	}
}
