package test;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelfOrganizingMap implements ActionListener {
	
	private Node[][] SOM = null;
	private double[][] NORM_MAP = null; //holds the L2 norm of each vector in the SOM[][].
	@SuppressWarnings("unused")
	private int GRID_OPTION = 0;
	private int INPUT_DIMENSION = 0;
	private int NUMER_OF_ITERATIONS = 0;
	private int CURRENT_ITERATION=0;
	private int SOM_HORIZONTAL_LENGTH = 0;
	private int SOM_VERTICAL_LENGTH = 0;
	private double INITIAL_LEARNING_RATE = 0.0;
	private double LEARNING_RATE = 0.0;
	private double MAX_RADIUS = 0.0; //radius at first epoch (t = 0)
	private double RADIUS = 0.0;
	private double TIME_STEP = 0.0; //lambda of X(t) = t0 * exp(-t/lambda)
	private String INPUT_SAMPLES = null;
	private DisplayLattice DISPLAY_SCREEN = null;
	

	public SelfOrganizingMap(int numberOfNodes, int depth, int grid, int inputDimensison, DisplayLattice screen)
	{
		INPUT_DIMENSION = inputDimensison;
		
		if(grid == 0)
		{
			int side = (int)Math.sqrt(numberOfNodes);
			SOM = new Node[side][side];
			NORM_MAP = new double[side][side];
			GRID_OPTION = grid;
			MAX_RADIUS = side/2;
			DISPLAY_SCREEN = screen;
		}

		
		RADIUS = MAX_RADIUS;
	}

	public void initTrainSOM(String input, int iterations, double learningRate)
	{

		NUMER_OF_ITERATIONS = iterations;
		INITIAL_LEARNING_RATE = learningRate;
		LEARNING_RATE = INITIAL_LEARNING_RATE;
		TIME_STEP = NUMER_OF_ITERATIONS/Math.log(MAX_RADIUS);
		INPUT_SAMPLES = input;

	}
	
	private void singleCompleteRun()
	{
		DISPLAY_SCREEN.render();
		System.out.println(CURRENT_ITERATION);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(CURRENT_ITERATION <= NUMER_OF_ITERATIONS)
		{
			singleCompleteRun();	
			CURRENT_ITERATION++;
		}
		
	}
	
}







































