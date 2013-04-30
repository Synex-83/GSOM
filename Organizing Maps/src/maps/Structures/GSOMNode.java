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
	private boolean IS_BOUNDRY = false;
	

	public GSOMNode(int Dimensions, int x, int y) {
		super(Dimensions, x, y);
		// TODO Auto-generated constructor stub
	}
	
	public double getTOTAL_ERROR() {
		return TOTAL_ERROR;
	}

	public void setTOTAL_ERROR(double total_error) {
		TOTAL_ERROR = total_error;
	}
	
	public boolean isBoundry() {
		return IS_BOUNDRY;
	}

	public void setBoundry(boolean isBoundry) {
		IS_BOUNDRY = isBoundry;
	}
}
