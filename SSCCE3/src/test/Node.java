package test;

public class Node {
	
	private int DIMENSION = 0;	
	private int POSITION_X = 0;
 	private int POSITION_Y = 0; 
 	private double ACTIVATION_VALUE = 0.0; 
	
	public Node(int Dimensions, int x, int y)
	{
		DIMENSION = Dimensions;	
		setWeightVector();
		POSITION_X = x;
		POSITION_Y = y;
	}

	public int getX() {
		return POSITION_X;
	}
	
	public int getY() {
		return POSITION_Y;
	}
	
	public double getACTIVATION_VALUE() {
		return ACTIVATION_VALUE;
	}
	
	public void setPOSITION_X(int x) {
		POSITION_X = x;
	}
	
	public void setPOSITION_Y(int y) {
		POSITION_Y = y;
	}
	
	public void setACTIVATION_VALUE(double y) {
		ACTIVATION_VALUE= y;
	}
	
	private void setWeightVector()
	{
		double temp[] = new double[DIMENSION];
		
		for(int i = 0; i<temp.length ; i++)
		{
			temp[i] =  Math.random();
		}
		
	}

}
