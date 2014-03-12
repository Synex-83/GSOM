/**
 * 
 */
package maps.Structures;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 5:58:32 PM
 * @type        FiniteStateMachine
 *
 */
public class FiniteStateMachine {
	
	private Node[][] ZERO_MAP = null;
	private Vector<FSMNode> FSM = null;
	private Vector<Node> PREVIOUS = null;
	private Vector<Edge> LINKS = null;
	private Vector<FSMNode> COMPOUND_NODES = null;
	private FSMNode CURRENT_COMPOUND = null;
	private int THRESHOLD = 0;
	private int ITERATION = 0;
	private int COMPOUND_COUNTER = 0; //zero means not a compound match > 0 means the compound node has to be used.
	
	private int LINK_NUMBERS = 0;
	private int FILE_OPTION = 0;
	private int REPEAT= 0;
	
	public FiniteStateMachine(int threhold, int iteration, int fileOption, Node[][] map)
	{
		FSM = new Vector<FSMNode>();
		LINKS = new Vector<Edge>();
		COMPOUND_NODES = new Vector<FSMNode>();
		THRESHOLD = threhold;
		ITERATION = iteration;
		FILE_OPTION = fileOption;
		ZERO_MAP = map;
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
	 * 
	 */
	public void resertCurrentCompound()
	{
		CURRENT_COMPOUND = null;
		COMPOUND_COUNTER = 0;
	}

	/**
	 * @param node
	 * @param winner
	 * 
	 * Searches the whole vector to verify there is no node with the same sequence. If non is found the new node is appended to the end of the 
	 * list. If the sequence exists the data in the sequence is updated to suite the new position of the winner.
	 */
	public FSMNode addUpdateNode(FSMNode current, FSMNode previous, Node winner, double learningRate, double radius)
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
				update(current,previous,winner,learningRate,radius); //trigger link update
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
	private void update(FSMNode current, FSMNode previous, Node winner, double learningRate, double radius) 
	{
		int distance = 0;
		current.setFocus(true);
		

		
		if(previous!=null &&  !(current.getCurrentWinner().equals(winner)) && !(previous.getSequence().equals(current.getSequence()))) //previous!=null && 
		{
			current.setCurrentWinner(winner);
			resetCompoundArrays(current);
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
		else if(previous!=null && (current.getCurrentWinner().equals(winner)) && (previous.getSequence().equals(current.getSequence())))
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
		
		//Connection with zero layer should happen here. The DDA should be in the SOM class
		//The condition for the trigger is that both current and previous has to be solid.
		//Another variable has to be introduced as mark to check whether the node is already represented in the
		//the zero map and if both are marked then the current learning rate and the radius should be sent to the 
		//zero map for processing.
		
		if(previous!=null  && !current.isHollow() && !previous.isHollow() && linkExists(current, previous))
		{
			processCompound(previous,current);
			processZeroMap(previous, current, learningRate, radius);
			COMPOUND_COUNTER++;
		}
		else
		{
			COMPOUND_COUNTER = 0;
		}
		
	}

	
	/**
	 * @param winner
	 */
	private void resetCompoundArrays(FSMNode winner)
	{
		
		for(int i = COMPOUND_NODES.size() -1 ; i >= 0 ; i--)
		{
			if(COMPOUND_NODES.get(i).getSequence().contains(winner.getSequence()))
			{
				//System.out.println("IDENTIFIED FOR REMOVAL\t" + COMPOUND_NODES.get(i).getSequence() + 
				//		" (" + COMPOUND_NODES.get(i).getEffectiveSequence() + 
				//		") [" + COMPOUND_NODES.get(i).getCompoundCount() + "] "  + "\tWINNER IS\t" + winner.getSequence());
				//System.out.println("BEFORE =\t" + COMPOUND_NODES.size());
				COMPOUND_NODES.remove(i);
				//System.out.println("AFTER =\t" + COMPOUND_NODES.size());
			}
		}
		
		COMPOUND_COUNTER = 0 ;
		CURRENT_COMPOUND = null;
	}
	
	/**
	 * @param previous2
	 * @param current
	 */
	private void processCompound(FSMNode prev, FSMNode curr)
	{
		boolean exists = false;
		FSMNode  temp = null;
		String compoundSequence = "";
		
		if(COMPOUND_COUNTER >= 1)
		{
			compoundSequence = CURRENT_COMPOUND.getSequence() + " -> " +curr.getSequence();
		}
		else
		{
			compoundSequence  = prev.getSequence() + " -> " + curr.getSequence();
		}
		
		
		for(int i = 0; i < COMPOUND_NODES.size(); i++)
		{
			if(COMPOUND_NODES.get(i).getSequence().equals(compoundSequence))
			{
				exists = true;
				CURRENT_COMPOUND = COMPOUND_NODES.get(i);
				CURRENT_COMPOUND.incrementCompoundCount();
				//System.out.println("HERE");
				break;
			}
			else
			{
				//System.out.println("NOT HERE");
			}
		}
		
		if(!exists)
		{
			temp = new FSMNode(compoundSequence);
			temp.setCOMPOUND(true);
			temp.setEffectiveSequence(getEffectiveSequence(prev, curr));
			COMPOUND_NODES.add(temp);
			CURRENT_COMPOUND = temp;
			CURRENT_COMPOUND.incrementCompoundCount();
		}
	}

	/**
	 * @param prev
	 * @param curr
	 * @return
	 */
	private String getEffectiveSequence(FSMNode prev, FSMNode curr) 
	{
		String temp = "";
		int length = curr.getSequence().length() - 1;
		
		if(COMPOUND_COUNTER >=1)
		{
			temp = CURRENT_COMPOUND.getEffectiveSequence() + curr.getSequence().substring(length);
		}
		else
		{
			temp = prev.getSequence() + curr.getSequence().substring(length);
		}
		
		return temp;
	}

	/**
	 * @param prev
	 * @param cur
	 * @param learningRate
	 * @param radius
	 */
	private void processZeroMap(FSMNode prev, FSMNode cur, double learningRate, double radius)
	{
		// TODO Auto-generated method stub
		int Xp = prev.getCurrentWinner().getX();
		int Xc = cur.getCurrentWinner().getX();
		int Yp = prev.getCurrentWinner().getY();
		int Yc = cur.getCurrentWinner().getY();
		//System.out.println("SQ 1 =" + prev.getSequence() + " SQ 2 =" + cur.getSequence() + " ^^^^^^^^^^^^^^^^^^^^^^^");
		AdaptZeroMap(Xp, Yp, Xc, Yc,
				(Array2DRowRealMatrix) (prev.getCurrentWinner().getWeightMatrix()).add(cur.getCurrentWinner().getWeightMatrix()).scalarMultiply(0.5*learningRate)
);
	}

	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @param adaptation
	 */
	private void AdaptZeroMap(int x1, int y1, int x2, int y2, Array2DRowRealMatrix adaptation)
	{
		// TODO Auto-generated method stub
		float m = (float)(y2-y1)/(float)(x2-x1);
		float x0 = 0;
		float x_1 = 0;
		float y0 = 0;
		float y_1 = 0;
		
		
		
		//System.out.println("X1=" + x1 + " Y1=" + y1 + "\tX2=" + x2 + " Y2=" + y2 + "\tm=" +m + " %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		
		if( Double.isInfinite(m) || m > 1 )
		{
			if(y1 < y2)
			{
				x0 = x1;
				y0 = y1;
				x_1 = x2;
				y_1 = y2;
			}
			else
			{
				x0 = x2;
				y0 = y2;
				x_1 = x1;
				y_1 = y1;
			}
			
			float tempX = x0;
			
			for(float tempY = y0; tempY <= y_1; tempY++,tempX += 1/m)
			{
				//System.out.println(Math.round(tempX) + " " + tempY);
				int i = (int)tempX;
				int j = (int)tempY;
				//System.out.println("PRE  ZERO_MAP["+i+"]["+j+"] ->" + ZERO_MAP[i][j].getWeightMatrix().toString());
				//System.out.println("ADAPTATION ->" + adaptation.toString());
				ZERO_MAP[i][j].setWeightMatrix(ZERO_MAP[i][j].getWeightMatrix().add(adaptation));
				//System.out.println("POST ZERO_MAP["+i+"]["+j+"] ->" + ZERO_MAP[i][j].getWeightMatrix().toString());
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}
		}
		else if(m < 1 || m == 1)
		{
			if(x1 < x2)
			{
				x0 = x1;
				y0 = y1;
				x_1 = x2;
				y_1 = y2;
			}
			else
			{
				x0 = x2;
				y0 = y2;
				x_1 = x1;
				y_1 = y1;
			}
			
			float tempY = y0;
			
			for(float tempX = x0; tempX <= x_1; tempX++,tempY += m)
			{
				//System.out.println(tempX + " " + Math.round(tempY));
				
				int i = (int)tempX;
				int j = (int)tempY;
				//System.out.println("PRE  ZERO_MAP["+i+"]["+j+"] ->" + ZERO_MAP[i][j].getWeightMatrix().toString());
				//System.out.println("ADAPTATION ->" + adaptation.toString());
				ZERO_MAP[i][j].setWeightMatrix(ZERO_MAP[i][j].getWeightMatrix().add(adaptation));
				//System.out.println("POST ZERO_MAP["+i+"]["+j+"] ->" + ZERO_MAP[i][j].getWeightMatrix().toString());
				//System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
			}
		}
		 
	}

	/**
	 * @param current
	 * @param winner
	 */
	private void updateLinks(FSMNode current, Node winner)
	{
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
		Iterator<FSMNode> itr5 = COMPOUND_NODES.iterator();
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
				//the use of Y values to display X and vice verse is to compensate for the intial array problem 
				//encountered when developing. Refer Node class comment on line 68.
				System.out.println("Sequence " + temp1.getSequence() + " X =" + (temp1.getCurrentWinner().getY()+1) + " Y =" + (temp1.getCurrentWinner().getX()+1) + " HITS =" + temp1.getCurrentWinner().getNumberOfHits() + "\n");
				solidNode.add(temp1);
				
			}
		}
		System.out.println("********             LONG SEQUENCES                  ********");
		while(itr5.hasNext())
		{
			temp1 = itr5.next();
			System.out.println("Sequence " + temp1.getSequence() +  " (" + temp1.getEffectiveSequence() + ")  [COUNT " + temp1.getCompoundCount() + "]" );
		}
		
		System.out.println("********             HOLLOW SEQUENCES                  ********");
		while(itr2.hasNext())
		{
			temp1 = itr2.next();

			if(temp1.isHollow())
			{
				//the use of Y values to display X and vice verse is to compensate for the intial array problem 
				//encountered when developing. Refer Node class comment on line 68.
				System.out.println("Sequence " + temp1.getSequence() + " X =" + (temp1.getCurrentWinner().getY()+1) + " Y =" + (temp1.getCurrentWinner().getX()+1) + " HITS =" + temp1.getCurrentWinner().getNumberOfHits() + "\n");
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
                //System.out.println("X = " + tt.get(j).getX() + " Y = " + tt.get(j).getY());
                System.out.println("X=\t" + tt.get(j).getX() + "\tY=\t" + tt.get(j).getY());
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
					
					//the use of Y values to display X and vice verse is to compensate for the intial array problem 
					//encountered when developing. Refer Node class comment on line 68.
					writer.write("Sequence " + temp1.getSequence() + " X =" + (temp1.getCurrentWinner().getY()+1) + " Y =" + (temp1.getCurrentWinner().getX()+1) + " HITS =" + temp1.getCurrentWinner().getNumberOfHits() + "\n");
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
			
			System.out.println("********         PREVIOUS WINNERS hh    ********");
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
	                System.out.println("X=\t" + tt.get(j).getX() + "\tY=\t" + tt.get(j).getY());
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
