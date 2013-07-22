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
	private boolean IS_BOUNDRY = true; // every node created in the GSOM is initially a boundary node. So set to true as the default value.
	private boolean IS_PATH_OF_SPREAD = false; // whether the node is on the POS or not
	private boolean IS_EXTERNAL_TO_POS = false;
	private int NUMBER_OF_HITS = 0; // number of times the node is selected as the winner
	private int GENERATIVE_NUMBER = 0; // the number when this node is selected for growth.	
	
	/**
	 * @param Dimensions
	 * @param x
	 * @param y
	 */
	public GSOMArrayNode(int Dimensions, int x, int y)
	{
		super(Dimensions, x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param Dimensions
	 * @param x
	 * @param y
	 * @param generativeNumber
	 */
	public GSOMArrayNode(int Dimensions, int x, int y, int generativeNumber)
	{
		super(Dimensions, x, y);
		GENERATIVE_NUMBER = generativeNumber;
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

	/**
	 * @return the gENERATIVE_NUMBER
	 */
	public int getGenerativeNumber() {
		return GENERATIVE_NUMBER;
	}

	/**
	 * @param gENERATIVE_NUMBER the gENERATIVE_NUMBER to set
	 */
	public void setGenerativeNumber(int number) {
		GENERATIVE_NUMBER = number;
	}

	/**
	 * @return the iS_PATH_OF_SPREAD
	 */
	public boolean isPOS() {
		return IS_PATH_OF_SPREAD;
	}

	/**
	 * @param iS_PATH_OF_SPREAD the iS_PATH_OF_SPREAD to set
	 */
	public void setPOS(boolean POS) {
		IS_PATH_OF_SPREAD = POS;
	}

	/**
	 * @return the iS_EXTERNAL_TO_POS
	 */
	public boolean isExternalToPos() {
		return IS_EXTERNAL_TO_POS;
	}

	/**
	 * @param iS_EXTERNAL_TO_POS the iS_EXTERNAL_TO_POS to set
	 */
	public void setExternalToPos(boolean isExternal) {
		IS_EXTERNAL_TO_POS = isExternal;
	}
	


}
