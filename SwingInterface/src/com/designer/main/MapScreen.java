/**
 * 
 */
package com.designer.main;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import maps.Structures.DisplayLattice;
import maps.Structures.SelfOrganizingMap;
import maps.Util.FileProcessing; 
/**
 * @author User
 *
 */
public class MapScreen extends JFrame {
	
	private double NUM_ITERATIONS = 0.0;
	private double ETA = 0.0;
	private double SPREAD_FACTOR = 0.0;
	private int LEARNING_OPTION = 0;
	private double RADIUS = 0.0;
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private int MAP_OPTION = 0;
	private SelfOrganizingMap SOM = null;
	private FileProcessing FILE_PROCESSING = null;
	private int NUM_NODES = 0;
	private Timer REFRESH_TIMER = null;
	
	
	private String FILE_PATH;
	private JPanel pnlMap;
	private JLabel lblIterations;
	private JLabel lblLearningRate;
	private JLabel lblSpreadfactor;
	private JLabel lblRadius;
	private JLabel lblIterationValue;
	private JLabel lblLearningRateValue; 
	private JLabel lblSpreadFactorValue;
	private JLabel lblRadiusValue;
	private JTextField txtFileSelection;
	private JButton btnLoadParameters;
	private JButton btnTrain;
	private DisplayLattice displayScreen;

	
	public MapScreen(double iterations, double learningRate, double spreadFactor, double radius, int option, int width, int height, int mapOption) {
		
		NUM_ITERATIONS = iterations;
		ETA = learningRate;
		SPREAD_FACTOR = spreadFactor;
		RADIUS = radius;
		LEARNING_OPTION = option; //SOM, GSOM, OTHER etc...
		WIDTH = width;
		HEIGHT = height;
		MAP_OPTION = mapOption;
		
		if(HEIGHT == 0)
		{
			NUM_NODES = WIDTH*WIDTH;
		}
		else
		{
			NUM_NODES = WIDTH*HEIGHT;
		}
		
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
		
		lblIterations = new JLabel("Iterations");
		lblIterations.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIterations.setBounds(6, 23, 84, 14);
		getContentPane().add(lblIterations);
		
		lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLearningRate.setBounds(6, 49, 95, 14);
		getContentPane().add(lblLearningRate);
		
		lblSpreadfactor = new JLabel("SpreadFactor");
		lblSpreadfactor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpreadfactor.setBounds(6, 75, 95, 14);
		getContentPane().add(lblSpreadfactor);
		
		lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRadius.setBounds(6, 101, 95, 14);
		getContentPane().add(lblRadius);
		
		lblIterationValue = new JLabel("");
		lblIterationValue.setForeground(Color.RED);
		lblIterationValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblIterationValue.setBounds(102, 24, 68, 14);
		getContentPane().add(lblIterationValue);
		
		lblLearningRateValue = new JLabel("");
		lblLearningRateValue.setForeground(new Color(255, 153, 51));
		lblLearningRateValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLearningRateValue.setBounds(102, 50, 68, 14);
		getContentPane().add(lblLearningRateValue);
		
		lblSpreadFactorValue = new JLabel("");
		lblSpreadFactorValue.setForeground(new Color(51, 204, 51));
		lblSpreadFactorValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpreadFactorValue.setBounds(102, 75, 68, 14);
		getContentPane().add(lblSpreadFactorValue);
		
		lblRadiusValue = new JLabel("");
		lblRadiusValue.setForeground(new Color(153, 51, 204));
		lblRadiusValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRadiusValue.setBounds(102, 101, 68, 14);
		getContentPane().add(lblRadiusValue);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPlay.setBounds(256, 98, 62, 23);
		getContentPane().add(btnPlay);
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInputFile.setBounds(192, 23, 76, 14);
		getContentPane().add(lblInputFile);
		
		txtFileSelection = new JTextField();
		txtFileSelection.setFont(new Font("Tahoma", Font.PLAIN, 9));
		txtFileSelection.setEditable(false);
		txtFileSelection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			     JFileChooser openFile = new JFileChooser();
	             openFile.showOpenDialog(null);
	             FILE_PATH = openFile.getSelectedFile().getAbsolutePath().toString();
				 txtFileSelection.setText(openFile.getSelectedFile().getName().toString());				
			}
		});
		txtFileSelection.setBounds(278, 23, 180, 16);
		getContentPane().add(txtFileSelection);
		txtFileSelection.setColumns(10);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPause.setBounds(192, 98, 63, 23);
		getContentPane().add(btnPause);
		
		btnLoadParameters = new JButton("Load Parameters");
		btnLoadParameters.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLoadParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				FILE_PROCESSING = new FileProcessing(FILE_PATH,1);
				SOM = new SelfOrganizingMap(NUM_NODES,HEIGHT,MAP_OPTION,FILE_PROCESSING.getDataDimension(),displayScreen);				
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
		
		initialize();
	}
	
	private void initialObjectSetUp()
	{
		SOM.initTrainSOM(FILE_PROCESSING.readFile(), (int)NUM_ITERATIONS, ETA);
		REFRESH_TIMER = new Timer(100, SOM);
		REFRESH_TIMER.start();
	}
	
	private void initialize()
	{
		lblIterationValue.setText(""+NUM_ITERATIONS);
		lblLearningRateValue.setText(""+ETA);
		lblSpreadFactorValue.setText(""+SPREAD_FACTOR);
		lblRadiusValue.setText(""+RADIUS);
	}
	
}
