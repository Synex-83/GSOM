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
	
	private double EDGE_LENGTH = 0;
	private FSMNode ORIGIN = null;
	private FSMNode DESTINATION = null;
	private double INTENSITY = 0;
	private int EDGE_ID = 0;
	
	public Edge(FSMNode origin, FSMNode destination, int id)
	{
		ORIGIN = origin;
		DESTINATION = destination;
		EDGE_ID = id;
		EDGE_LENGTH = setInitialDistance(origin.getCurrentWinner(), destination.getCurrentWinner());
	}
	
	/**
	 * @return the eDGE_LENGTH
	 */
	public double getEdgeLength() {
		return EDGE_LENGTH;
	}
	/**
	 * @param eDGE_LENGTH the eDGE_LENGTH to set
	 */
	public void setEdgeLength(double d) {
		EDGE_LENGTH = d;
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
	
	private double setInitialDistance(Node origin, Node destination )
	{
		System.out.println("*****************************************" + origin.getX() );
		System.out.println("*****************************************" + destination.getX() );
		double diffX = Math.pow(origin.getX() - destination.getX(),2);
		double diffY = Math.pow(origin.getY() - destination.getY(),2);
	
		
		return Math.sqrt(diffX + diffY);
	}

}
