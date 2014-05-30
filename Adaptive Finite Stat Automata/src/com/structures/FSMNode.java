/**
 * 
 */
package com.structures;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 2:11:47 PM
 * @package com.structures
 * 
 */
public class FSMNode {

	private int NUMBER_OF_HITS = 0;
	
	private String MAPPED_SEQUENCE = null;
	
	private boolean IS_MATURE = false;
	private boolean IS_REPEAT = false;
	private boolean IS_START_NODE = false;
	
	private ArrayList<Integer> INCOMING_LINK = null;
	private ArrayList<Integer> OUTGOING_LINK = null;
	
	private int[] REPEAT_LIST = null;

	
	//=================== CONSTRUCTOR ===================
	
	/**
	 * @param sequence
	 */
	public FSMNode(String sequence)
	{
		MAPPED_SEQUENCE = sequence;
		
		INCOMING_LINK = new ArrayList<Integer>();
		OUTGOING_LINK = new ArrayList<Integer>();
	}
	
	//========================  ACCESSOR METHODS  ==========================

	/**
	 * @return the NUMBER_OF_HITS
	 */
	public int getHits() {
		return NUMBER_OF_HITS;
	}

	/**
	 * @param value the NUMBER_OF_HITS to set
	 */
	public void setHits(int value) {
		NUMBER_OF_HITS = value;
	}

	/**
	 * @return the MAPPED_SEQUENCE
	 */
	public String getMappedSequence() {
		return MAPPED_SEQUENCE;
	}

	/**
	 * @param value the MAPPED_SEQUENCE to set
	 */
	public void setSequence(String value) {
		MAPPED_SEQUENCE = value;
	}

	/**
	 * @return the IS_MATURE
	 */
	public boolean isMature() {
		return IS_MATURE;
	}

	/**
	 * @param value the IS_MATURE to set
	 */
	public void setMature(boolean value) {
		IS_MATURE = value;
	}

	/**
	 * @return the  IS_REPEAT
	 */
	public boolean isRepeat() {
		return IS_REPEAT;
	}

	/**
	 * @param value the iS_REPEAT to set
	 */
	public void setRepeat(boolean value) {
		IS_REPEAT = value;
	}

	/**
	 * @return the IS_START_NODE
	 */
	public boolean isStartNode() {
		return IS_START_NODE;
	}

	/**
	 * @param value the IS_START_NODE to set
	 */
	public void setStartNode(boolean value) {
		IS_START_NODE = value;
	}

	/**
	 * @return the INCOMING_LINK
	 */
	public ArrayList<Integer> getIncomingLink() {
		return INCOMING_LINK;
	}

	/**
	 * @return the OUTGOING_LINK
	 */
	public ArrayList<Integer> getOutgoingLink() {
		return OUTGOING_LINK;
	}


	/**
	 * @return the REPEAT_LIST
	 */
	public int[] getRepeatList() {
		return REPEAT_LIST;
	}
	
	//========== CLASS METHODS ================
	//========== PUBLIC METHODS =================
	
	/**
	 * Sets an outgoing link from the give state. Only the link id is added.
	 * @param id
	 */
	public void setOutgoingLink(int id)
	{
		OUTGOING_LINK.add(id);
	}
	
	/**
	 * Sets an incoming link from the give state. Only the link id is added.
	 * @param id
	 */
	public void setIncomingLink(int id)
	{
		INCOMING_LINK.add(id);
	}
	
	/**
	 * 
	 */
	public void printIncomingOutgoingLinks()
	{
		Iterator<Integer> in = INCOMING_LINK.iterator();
		Iterator<Integer> out = OUTGOING_LINK.iterator();
		
		System.out.println("INCOMING");
		
		while(in.hasNext())
		{
			System.out.print(in.next().toString() + "\t");
		}
		
		System.out.println();
		System.out.println("OUTCOMING");
		
		while(out.hasNext())
		{
			System.out.print(out.next().toString() + "\t");
		}
		
		System.out.println("\n");
	}
}























