/**
 * 
 */
package com.designer.main;

import java.awt.Window.Type;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;

/**
 * @author User
 *
 */
public class MapScreen extends JFrame {
	
	double NUM_ITERATIONS = 0.0;
	double ETA = 0.0;
	double SPREAD_FACTOR = 0.0;
	int LEARNING_OPTION = 0;
	double RADIUS = 0.0;
	private JPanel pnlMap;
	private JLabel lblIterations;
	private JLabel lblLearningRate;
	private JLabel lblSpreadfactor;
	private JLabel lblRadius;
	private JLabel lblIterationValue;
	private JLabel lblLearningRateValue; 
	private JLabel lblSpreadFactorValue;
	private JLabel lblRadiusValue;
	
	public MapScreen(double iterations, double learningRate, double spreadFactor, double radius, int option) {
		
		NUM_ITERATIONS = iterations;
		ETA = learningRate;
		SPREAD_FACTOR = spreadFactor;
		RADIUS = radius;
		LEARNING_OPTION = option;
		
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Map");
		setSize(600, 600);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		pnlMap = new JPanel();
		pnlMap.setBounds(6, 130, 582, 440);
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
		lblIterationValue.setBounds(102, 24, 84, 14);
		getContentPane().add(lblIterationValue);
		
		lblLearningRateValue = new JLabel("");
		lblLearningRateValue.setForeground(new Color(255, 153, 51));
		lblLearningRateValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLearningRateValue.setBounds(102, 50, 84, 14);
		getContentPane().add(lblLearningRateValue);
		
		lblSpreadFactorValue = new JLabel("");
		lblSpreadFactorValue.setForeground(new Color(51, 204, 51));
		lblSpreadFactorValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSpreadFactorValue.setBounds(102, 75, 84, 14);
		getContentPane().add(lblSpreadFactorValue);
		
		lblRadiusValue = new JLabel("");
		lblRadiusValue.setForeground(new Color(153, 51, 204));
		lblRadiusValue.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRadiusValue.setBounds(102, 101, 84, 14);
		getContentPane().add(lblRadiusValue);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(202, 20, 89, 23);
		getContentPane().add(btnPause);
		
		initialize();
	}
	
	private void initialize()
	{
		lblIterationValue.setText(""+NUM_ITERATIONS);
		lblLearningRateValue.setText(""+ETA);
		lblSpreadFactorValue.setText(""+SPREAD_FACTOR);
		lblRadiusValue.setText(""+RADIUS);
	}
}
