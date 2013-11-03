/**
 * 
 */
package maps.Structures;

/**
 * @author 		Manjusri Ishwara
 * @date		Nov 2, 2013 - 4:45:58 PM
 * @type        Edge
 *
 */
public class Edge {
	
	private int EDGE_LENGTH = 0;
	private FSMNode ORIGIN = null;
	private FSMNode DESTINATION = null;
	private double INTENSITY = 0;
	private int EDGE_ID = 0;
	
	public Edge(FSMNode origin, FSMNode destination, int id)
	{
		ORIGIN = origin;
		DESTINATION = destination;
		EDGE_ID = id;
	}
	
	/**
	 * @return the eDGE_LENGTH
	 */
	public int getEdgeLength() {
		return EDGE_LENGTH;
	}
	/**
	 * @param eDGE_LENGTH the eDGE_LENGTH to set
	 */
	public void setEdgeLength(int length) {
		EDGE_LENGTH = length;
	}
	/**
	 * @return the oRIGIN
	 */
	public FSMNode getOrigin() {
		return ORIGIN;
	}
	/**
	 * @param oRIGIN the oRIGIN to set
	 */
	public void setOrigin(FSMNode start) {
		ORIGIN = start;
	}
	/**
	 * @return the dESTINATION
	 */
	public FSMNode getDestination() {
		return DESTINATION;
	}
	/**
	 * @param dESTINATION the dESTINATION to set
	 */
	public void setDestination(FSMNode end) {
		DESTINATION = end;
	}
	/**
	 * @return the iNTENSITY
	 */
	public double getIntensity() {
		return INTENSITY;
	}
	/**
	 * @param iNTENSITY the iNTENSITY to set
	 */
	public void setIntensity(double value) {
		INTENSITY = value;
	}
	/**
	 * @return the EDGE_ID
	 */
	public int getEdgeID() {
		return EDGE_ID;
	}
	/**
	 * @param id the EDGE_ID to set
	 */
	public void setEdgeID(int id) {
		EDGE_ID = id;
	}

}
