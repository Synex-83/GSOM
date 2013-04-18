/**
 * 
 */
package com.designer.main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Manjusri Ishwara
 *
 */
public class ParameterScreen extends JFrame {
	private JTextField txtIterations;
	private JTextField txtLearningRate;
	private JTextField txtSpreadFactor;
	private JTextField txtRadius;
	private int OPTION = 0;
	private int NUM_ITERATIONS = 0;
	private double LEARNING_RATE = 0.0;
	private double SPREAD_FACTOR = 0.0;
	private double RADIUS = 0.0;
	
	public ParameterScreen(int option) {

		OPTION = option;
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Configuration");
		setSize(389, 183);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		txtIterations = new JTextField();
		txtIterations.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIterations.setBounds(132, 34, 69, 20);
		getContentPane().add(txtIterations);
		txtIterations.setColumns(10);
		
		JLabel lblNumberOfIterations = new JLabel("Number of Iterations");
		lblNumberOfIterations.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNumberOfIterations.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNumberOfIterations.setBounds(6, 37, 114, 15);
		getContentPane().add(lblNumberOfIterations);
		
		txtLearningRate = new JTextField();
		txtLearningRate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtLearningRate.setColumns(10);
		txtLearningRate.setBounds(303, 34, 69, 20);
		getContentPane().add(txtLearningRate);
		
		txtSpreadFactor = new JTextField();
		txtSpreadFactor.setEnabled(false);
		txtSpreadFactor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpreadFactor.setColumns(10);
		txtSpreadFactor.setBounds(132, 73, 69, 20);
		getContentPane().add(txtSpreadFactor);
		
		txtRadius = new JTextField();
		txtRadius.setEnabled(false);
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRadius.setColumns(10);
		txtRadius.setBounds(303, 73, 69, 20);
		getContentPane().add(txtRadius);
		
		JLabel lblLearningRate = new JLabel("Learning Rate");
		lblLearningRate.setHorizontalAlignment(SwingConstants.TRAILING);
		lblLearningRate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLearningRate.setBounds(216, 37, 75, 15);
		getContentPane().add(lblLearningRate);
		
		JLabel lblSpreadFactor = new JLabel("Spread Factor");
		lblSpreadFactor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSpreadFactor.setBounds(6, 76, 76, 15);
		getContentPane().add(lblSpreadFactor);
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRadius.setBounds(216, 76, 34, 15);
		getContentPane().add(lblRadius);
		
		JButton btnInitialize = new JButton("Initialize");
		btnInitialize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(validateInput())
				{
					(new MapScreen(NUM_ITERATIONS,LEARNING_RATE,SPREAD_FACTOR,RADIUS,OPTION)).setVisible(true);
					dispose();
				}
			}
		});
		btnInitialize.setBounds(282, 114, 90, 28);
		getContentPane().add(btnInitialize);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(190, 114, 90, 28);
		getContentPane().add(btnClear);
				
		
		enableComponents();
	}

	private void clearFields()
	{
		txtIterations.setText("");
		txtLearningRate.setText("");
		txtRadius.setText("");
		txtSpreadFactor.setText("");
	}
	
	private boolean validateInput() {
		// TODO Auto-generated method stub
		try
		{
			NUM_ITERATIONS = Integer.parseInt(txtIterations.getText().toString());
			LEARNING_RATE = Double.parseDouble(txtLearningRate.getText().toString());
			
			if(OPTION == 2)
			{
				RADIUS = Double.parseDouble(txtRadius.getText().toString());
				SPREAD_FACTOR = Double.parseDouble(txtSpreadFactor.getText().toString());
				
				if(SPREAD_FACTOR < 0 || SPREAD_FACTOR > 1.0)
				{
					JOptionPane.showMessageDialog(null, "Spread Factor  should be between 0.0 and 1.0 included", "Naughty", JOptionPane.ERROR_MESSAGE);
					return false;
				}
				
				if(RADIUS < 1)
				{
					JOptionPane.showMessageDialog(null, "Radius should be greater than 1.0", "Naughty", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			
			if(NUM_ITERATIONS <= 0 )
			{
				JOptionPane.showMessageDialog(null, "Number of iterations should be greater than 0.0", "Naughty", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(LEARNING_RATE < 0 || LEARNING_RATE > 1.0)
			{
				JOptionPane.showMessageDialog(null, "Learning rate should be between 0.0 and 1.0 included", "Naughty", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			

		}
		catch(NumberFormatException nfe)
		{
			JOptionPane.showMessageDialog(null, "Please enter numbers only", "Naughty", JOptionPane.ERROR_MESSAGE);
			
			return false;
		}
		
		return true;
	}

	private void enableComponents() {
		// TODO Auto-generated method stub
		if(OPTION==1)
		{

		}
		else if(OPTION==2)
		{
			txtSpreadFactor.setEnabled(true);
			txtRadius.setEnabled(true);
		}
		else if(OPTION==3)
		{
			
		}
	}
}
