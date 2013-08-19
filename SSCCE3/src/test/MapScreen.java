
package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import test.DisplayLattice;
import test.SelfOrganizingMap;

public class MapScreen extends JFrame {
	
	private double NUM_ITERATIONS = 0.0;
	private double ETA = 0.0;
	private double SPREAD_FACTOR = 0.0;
	private double RADIUS = 0.0;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private SelfOrganizingMap SOM = null;
	private Timer REFRESH_TIMER = null;	
	private JPanel pnlMap;
	private JButton btnLoadParameters;
	private JButton btnTrain;
	private DisplayLattice displayScreen;

	
	public MapScreen(double iterations, double learningRate, double spreadFactor, double radius, int option, int width, int height, int mapOption) {
		
		NUM_ITERATIONS = iterations;
		ETA = learningRate;
		SPREAD_FACTOR = spreadFactor;
		RADIUS = radius;
		WIDTH = width;
		HEIGHT = height;

		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Map");
		setSize(650, 800);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		displayScreen = new DisplayLattice();
		pnlMap = displayScreen;
		pnlMap.setBounds(6, 130, 600, 600);
		getContentPane().add(pnlMap);
		
	
		btnLoadParameters = new JButton("Load Parameters");
		btnLoadParameters.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLoadParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				SOM = new SelfOrganizingMap(10000,0,0,13,displayScreen);
			}
		});
		btnLoadParameters.setBounds(192, 46, 126, 23);
		getContentPane().add(btnLoadParameters);
		
		btnTrain = new JButton("Train");
		btnTrain.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnTrain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				initialObjectSetUp();
			}
		});
		btnTrain.setBounds(192, 72, 62, 23);
		getContentPane().add(btnTrain);
		
	}
	
	private void initialObjectSetUp()
	{
		SOM.initTrainSOM(null, 100, 0.25);
		REFRESH_TIMER = new Timer(100, SOM);
		REFRESH_TIMER.start();
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MapScreen frame = new MapScreen(100,0.25,0.0,0.0,1,100,0,0);
				frame.setVisible(true);
			}
		});

	}
	
}
