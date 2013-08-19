/**
 * 
 */
package maps.Structures;

/**
 * @author 		Manjusri Ishwara
 * @date		Jul 29, 2013 - 8:24:48 PM
 * @type        SkeletonNode
 *
 */
public class SkeletonNode extends Node {

	private SkeletonNode LEFT,RIGHT,UP,DOWN;
	/**
	 * @param Dimensions
	 * @param x
	 * @param y
	 */
	public SkeletonNode(int Dimensions, int x, int y) {
		super(Dimensions, x, y);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @return the lEFT
	 */
	public SkeletonNode getLEFT() {
		return LEFT;
	}

	/**
	 * @param lEFT the lEFT to set
	 */
	public void setLEFT(SkeletonNode left) {
		LEFT = left;
	}

	/**
	 * @return the rIGHT
	 */
	public SkeletonNode getRIGHT() {
		return RIGHT;
	}

	/**
	 * @param rIGHT the rIGHT to set
	 */
	public void setRIGHT(SkeletonNode right) {
		RIGHT = right;
	}

	/**
	 * @return the uP
	 */
	public SkeletonNode getUP() {
		return UP;
	}

	/**
	 * @param uP the uP to set
	 */
	public void setUP(SkeletonNode up) {
		UP = up;
	}

	/**
	 * @return the dOWN
	 */
	public SkeletonNode getDOWN() {
		return DOWN;
	}

	/**
	 * @param dOWN the dOWN to set
	 */
	public void setDOWN(SkeletonNode down) {
		DOWN = down;
	}

}
