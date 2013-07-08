/**
 * 
 */
package maps.Structures;

/**
 * @author 		User
 * @date		Jul 8, 2013 - 7:11:31 PM
 * @type        GSOMArrayNode
 *
 */
public class GSOMArrayNode extends Node {

	private double ACCUMULATED_ERROR = 0.0; // accumulated error
	private boolean IS_BOUNDRY = true; //every node created in the GSOM is initially a boundary node. So set to true as the default value.
	private int NUMBER_OF_HITS = 0;
	
	/**
	 * @param Dimensions
	 * @param x
	 * @param y
	 */
	public GSOMArrayNode(int Dimensions, int x, int y) {
		super(Dimensions, x, y);
		// TODO Auto-generated constructor stub
	}

	public double getAccumulatedError() {
		return ACCUMULATED_ERROR;
	}

	public void setAccumulatedError(double error) {
		ACCUMULATED_ERROR = error;
	}
	
	public boolean isBoundry() {
		return IS_BOUNDRY;
	}

	public void setBoundry(boolean isBoundry) {
		IS_BOUNDRY = isBoundry;
	}

	/**
	 * @return the nUMBER_OF_HITS
	 */
	public int getNumberOfHits() {
		return NUMBER_OF_HITS;
	}

	/**
	 * @param nUMBER_OF_HITS the nUMBER_OF_HITS to set
	 */
	public void incrementNumberOfHits() {
		NUMBER_OF_HITS++;
	}
	


}
