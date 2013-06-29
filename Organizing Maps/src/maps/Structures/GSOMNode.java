/**
 * 
 */
package maps.Structures;

/**
 * @author 		Manjusri Ishwara
 * @date   		Apr 29, 2013 - 02:49:53 PM
 * @type		GSOMNode
 *
 */
public class GSOMNode extends Node {

	private double TOTAL_ERROR = 0.0;
	private boolean IS_BOUNDRY = true; //every node created in the GSOM is initially a boundry node. So set to true as the default value.
	private GSOMNode LEFT,RIGHT,UP,DOWN;
	private boolean VISITED = false;
	private int NUMBER_OF_HITS = 0;
	

	public GSOMNode(int Dimensions, int x, int y) {
		super(Dimensions, x, y);
	}
	
	public double getTotalError() {
		return TOTAL_ERROR;
	}

	public void setTotalError(double error) {
		TOTAL_ERROR = error;
	}
	
	public boolean isBoundry() {
		return IS_BOUNDRY;
	}

	public void setBoundry(boolean isBoundry) {
		IS_BOUNDRY = isBoundry;
	}

	/**
	 * @return the lEFT
	 */
	public GSOMNode getLEFT() {
		return LEFT;
	}

	/**
	 * @param lEFT the lEFT to set
	 */
	public void setLEFT(GSOMNode left) {
		LEFT = left;
	}

	/**
	 * @return the rIGHT
	 */
	public GSOMNode getRIGHT() {
		return RIGHT;
	}

	/**
	 * @param rIGHT the rIGHT to set
	 */
	public void setRIGHT(GSOMNode right) {
		RIGHT = right;
	}

	/**
	 * @return the uP
	 */
	public GSOMNode getUP() {
		return UP;
	}

	/**
	 * @param uP the uP to set
	 */
	public void setUP(GSOMNode up) {
		UP = up;
	}

	/**
	 * @return the dOWN
	 */
	public GSOMNode getDOWN() {
		return DOWN;
	}

	/**
	 * @param dOWN the dOWN to set
	 */
	public void setDOWN(GSOMNode down) {
		DOWN = down;
	}

	/**
	 * @return the vISITED
	 */
	public boolean isVisited() {
		return VISITED;
	}

	/**
	 * @param vISITED the vISITED to set
	 */
	public void setVisited(boolean visited) {
		VISITED = visited;
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
