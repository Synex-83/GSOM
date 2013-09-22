/**
 * 
 */
package scbw.com;

/**
 * @author 		Manjusri Ishwara
 * @date		Sep 21, 2013 - 3:57:17 PM
 * @type        PlayerNode
 *
 */
public class PlayerNode {

	String PLAYER_NAME = "";
	int NUMBER_OF_GAMES = 0;
	int PROTOSS = 0;
	int ZERG = 0;
	int TERRAN = 0;
	PlayerNode next;
	
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
	 * @return the pROTOSS
	 */
	public int getPROTOSS() {
		return PROTOSS;
	}

	/**
	 * @param pROTOSS the pROTOSS to set
	 */
	public void setPROTOSS(int pROTOSS) {
		PROTOSS = pROTOSS;
	}

	/**
	 * @return the zERG
	 */
	public int getZERG() {
		return ZERG;
	}

	/**
	 * @param zERG the zERG to set
	 */
	public void setZERG(int zERG) {
		ZERG = zERG;
	}

	/**
	 * @return the tERRAN
	 */
	public int getTERRAN() {
		return TERRAN;
	}

	/**
	 * @param tERRAN the tERRAN to set
	 */
	public void setTERRAN(int tERRAN) {
		TERRAN = tERRAN;
	}
	
	/**
	 * @param playername
	 */
	public PlayerNode(String playername)
	{
		PLAYER_NAME = playername;
		NUMBER_OF_GAMES = 1;
	}
	
	
}
