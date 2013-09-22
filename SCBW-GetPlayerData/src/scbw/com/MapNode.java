/**
 * 
 */
package scbw.com;

/**
 * @author 		Manjusri Ishwara
 * @date		Sep 22, 2013 - 1:55:18 AM
 * @type        MapNode
 *
 */
public class MapNode {
	
	String MAP_NAME = "";
	int NUMBER_OF_GAMES = 0;
	int NUMBER_OF_START_POSITIONS = 0;
	public MapNode next;
	
	/**
	 * 
	 */
	public MapNode(String mapName) 
	{
		MAP_NAME = mapName;
		NUMBER_OF_GAMES = 1;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the mAP_NAME
	 */
	public String getMAP_NAME() {
		return MAP_NAME;
	}
	/**
	 * @param mAP_NAME the mAP_NAME to set
	 */
	public void setMAP_NAME(String mAP_NAME) {
		MAP_NAME = mAP_NAME;
	}
	/**
	 * @return the nUMBER_OF_GAMES
	 */
	public int getNUMBER_OF_GAMES() {
		return NUMBER_OF_GAMES;
	}
	/**
	 * @param nUMBER_OF_GAMES the nUMBER_OF_GAMES to set
	 */
	public void setNUMBER_OF_GAMES(int nUMBER_OF_GAMES) {
		NUMBER_OF_GAMES = nUMBER_OF_GAMES;
	}
	/**
	 * @return the nUMBER_OF_START_POSITIONS
	 */
	public int getNUMBER_OF_START_POSITIONS() {
		return NUMBER_OF_START_POSITIONS;
	}
	/**
	 * @param nUMBER_OF_START_POSITIONS the nUMBER_OF_START_POSITIONS to set
	 */
	public void setNUMBER_OF_START_POSITIONS(int nUMBER_OF_START_POSITIONS) {
		NUMBER_OF_START_POSITIONS = nUMBER_OF_START_POSITIONS;
	}

}
