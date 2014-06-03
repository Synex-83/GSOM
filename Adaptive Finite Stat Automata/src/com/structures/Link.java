/**
 * 
 */
package com.structures;

/**
 * @author  Manjusri Ishwara
 * @date    May 30, 2014 - 2:13:17 PM
 * @package com.structures
 * 
 */
public class Link {

	private int LINK_ID = 0;
	
	private double INTENSITY = 0.0; //Should decided on a initial value
	
	private boolean IS_RECURSIVE = false;
	private boolean IS_INITIALIZED = false;
	
	private FSMNode ORIGINATOR = null;
	private FSMNode DESTINATION = null;
	
	//=============== CONSTRUCTOR ===============
	
	public Link(int link_id, FSMNode origin, FSMNode destination)
	{
		LINK_ID = link_id;
		ORIGINATOR = origin;
		DESTINATION = destination;
	}

	//=============== ACCESSOR METHODS ===============
	
	/**
	 * @return the LINK_ID
	 */
	public int getLinkId() {
		return LINK_ID;
	}

	/**
	 * @return the INTENSITY
	 */
	public double getIntensity() {
		return INTENSITY;
	}
	
	/**
	 * @param value the INTENSITY to set
	 */
	public void setIntensity(double value){
		INTENSITY = value;
	}


	/**
	 * @return the iS_RECURSIVE
	 */
	public boolean isRecursive() {
		return IS_RECURSIVE;
	}

	/**
	 * @param value the iS_RECURSIVE to set
	 */
	public void setIsRecursive(boolean value) {
		IS_RECURSIVE = value;
	}

	/**
	 * @return the ORIGINATOR
	 */
	public FSMNode getOriginator() {
		return ORIGINATOR;
	}


	/**
	 * @return the DESTINATION
	 */
	public FSMNode getDestination() {
		return DESTINATION;
	}	
	
	/**
	 * @return the IS_INITIALIZED
	 */
	public boolean isInitialized()
	{
		return IS_INITIALIZED;
	}
	
	/**
	 * @param value the IS_INITIALIZED to set
	 */
	public void setIsInitialized(boolean value)
	{
		IS_INITIALIZED = value;
	}
}
