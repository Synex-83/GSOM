/**
 * 
 */
package com.designer.main;

import javax.swing.ButtonGroup;
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
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

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
	private int WIDTH = 0;
	private int HEIGHT = 0;
	private int MAP_OPTION = -1;
	private JTextField txtWidth;
	private JTextField txtHeight;
	private JLabel lblWidth;
	private JLabel lblHeight;
	private JRadioButton rbtSquare;
	private JRadioButton rbtHex;
	private JRadioButton rbtRectangluar;
	private ButtonGroup mapTypeGroup;
	
	public ParameterScreen(int option) {

		OPTION = option;
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Configuration");
		setSize(389, 283);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		txtIterations = new JTextField();
		txtIterations.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtIterations.setBounds(132, 31, 69, 27);
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
		txtLearningRate.setBounds(303, 32, 69, 24);
		getContentPane().add(txtLearningRate);
		
		txtSpreadFactor = new JTextField();
		txtSpreadFactor.setEnabled(false);
		txtSpreadFactor.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSpreadFactor.setColumns(10);
		txtSpreadFactor.setBounds(132, 70, 69, 27);
		getContentPane().add(txtSpreadFactor);
		
		txtRadius = new JTextField();
		txtRadius.setEnabled(false);
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtRadius.setColumns(10);
		txtRadius.setBounds(303, 71, 69, 24);
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
					(new MapScreen(NUM_ITERATIONS,LEARNING_RATE,SPREAD_FACTOR,RADIUS,OPTION,WIDTH,HEIGHT,MAP_OPTION)).setVisible(true);
					dispose();
				}
			}
		});
		btnInitialize.setBounds(282, 225, 90, 28);
		getContentPane().add(btnInitialize);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(190, 225, 90, 28);
		getContentPane().add(btnClear);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 117, 377, 2);
		getContentPane().add(separator);
		
		rbtSquare = new JRadioButton("Square");
		rbtSquare.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				lblWidth.setVisible(true);
				txtWidth.setVisible(true);
				lblHeight.setVisible(false);
				txtHeight.setVisible(false);
			}
		});
		rbtSquare.setBounds(5, 135, 75, 18);
		getContentPane().add(rbtSquare);
		
		rbtHex = new JRadioButton("Hex");
		rbtHex.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				lblWidth.setVisible(true);
				txtWidth.setVisible(true);
				lblHeight.setVisible(false);
				txtHeight.setVisible(false);
			}
		});
		rbtHex.setBounds(178, 135, 75, 18);
		getContentPane().add(rbtHex);
		
		rbtRectangluar = new JRadioButton("Rectangluar");
		rbtRectangluar.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				lblWidth.setVisible(true);
				txtWidth.setVisible(true);
				lblHeight.setVisible(true);
				txtHeight.setVisible(true);
			}
		});
		rbtRectangluar.setBounds(80, 135, 98, 18);
		getContentPane().add(rbtRectangluar);
				
		mapTypeGroup = new ButtonGroup();
		mapTypeGroup.add(rbtSquare);
		mapTypeGroup.add(rbtHex);
		mapTypeGroup.add(rbtRectangluar);
		
		
		txtWidth = new JTextField();
		txtWidth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtWidth.setColumns(10);
		txtWidth.setBounds(54, 167, 69, 28);
		txtWidth.setVisible(false);
		getContentPane().add(txtWidth);
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtHeight.setColumns(10);
		txtHeight.setBounds(178, 167, 69, 28);
		txtHeight.setVisible(false);
		getContentPane().add(txtHeight);
		
		lblWidth = new JLabel("Width");
		lblWidth.setBounds(6, 172, 55, 16);
		lblWidth.setVisible(false);
		getContentPane().add(lblWidth);
		
		lblHeight = new JLabel("Height");
		lblHeight.setBounds(135, 172, 55, 16);
		lblHeight.setVisible(false);
		getContentPane().add(lblHeight);
		
		
		enableComponents();
	}

	private void clearFields()
	{
		txtIterations.setText("");
		txtLearningRate.setText("");
		txtRadius.setText("");
		txtSpreadFactor.setText("");
		txtWidth.setText("");
		txtHeight.setText("");
		txtWidth.setVisible(false);
		txtHeight.setVisible(false);
		lblWidth.setVisible(false);
		lblHeight.setVisible(false);
		mapTypeGroup.clearSelection();
		
	}
	
	private boolean validateInput() {
		// TODO Auto-generated method stub
		try
		{
			NUM_ITERATIONS = Integer.parseInt(txtIterations.getText().toString());
			LEARNING_RATE = Double.parseDouble(txtLearningRate.getText().toString());
			WIDTH = Integer.parseInt(txtWidth.getText().toString());
			
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
				JOptionPane.showMessageDialog(null, "Number of iterations should be greater than 0.0", "Idiot", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(LEARNING_RATE < 0 || LEARNING_RATE > 1.0)
			{
				JOptionPane.showMessageDialog(null, "Learning rate should be between 0.0 and 1.0 included", "Naughty", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			
			if(txtHeight.isVisible())
			{
				HEIGHT = Integer.parseInt(txtHeight.getText().toString());
				
				if(HEIGHT <= 0)
				{
					JOptionPane.showMessageDialog(null, "Height should be greater than 0.0", "Idiot", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
			else
			{
				HEIGHT = 0;
			}
			
			if(WIDTH <= 0)
			{
				JOptionPane.showMessageDialog(null, "Width should be greater than 0.0", "Idiot", JOptionPane.ERROR_MESSAGE);
				return false;
			}

			
			if(rbtSquare.isSelected())
			{
				MAP_OPTION = 0;
			}
			else if(rbtRectangluar.isSelected())
			{
				MAP_OPTION = 1;
			}
			else if(rbtHex.isSelected())
			{
				MAP_OPTION = 2;
			}
			
			if(MAP_OPTION == -1)
			{
				JOptionPane.showMessageDialog(null, "Please select a map type", "Missed Something?", JOptionPane.ERROR_MESSAGE);
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
