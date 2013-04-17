/**
 * 
 */
package com.designer.main;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Manjusri Ishwara
 * @since 
 *
 */
public class MainApp extends JFrame{
	
	public MainApp()
	{
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Organizing Maps");
		setSize(261, 284);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		JLabel lblSelectTrainingMethod = new JLabel("Select Training Method");
		lblSelectTrainingMethod.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelectTrainingMethod.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTrainingMethod.setBounds(34, 38, 181, 24);
		getContentPane().add(lblSelectTrainingMethod);
		
		final JRadioButton rbtGSOM = new JRadioButton("Growing Self-Organizing Map");
		rbtGSOM.setBounds(44, 110, 191, 23);
		getContentPane().add(rbtGSOM);
		
		final JRadioButton rbtSOM = new JRadioButton("Self-Organizing Map");
		rbtSOM.setBounds(44, 74, 148, 23);
		getContentPane().add(rbtSOM);
		
		final JRadioButton rbtNewVar1 = new JRadioButton("New Variant 1");
		rbtNewVar1.setBounds(44, 143, 109, 23);
		getContentPane().add(rbtNewVar1);
				
		final ButtonGroup menuRadioButton = new ButtonGroup();
		menuRadioButton.add(rbtSOM);
		menuRadioButton.add(rbtGSOM);
		menuRadioButton.add(rbtNewVar1);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				
				if(rbtSOM.isSelected())
				{
					setVisible(false);		
					(new ParameterScreen(1)).setVisible(true);
				}
				else if(rbtGSOM.isSelected())
				{
					setVisible(false);
					(new ParameterScreen(2)).setVisible(true);
				}
				else if(rbtNewVar1.isSelected())
				{
					System.out.println("New");
				}
			}
		});
		
				
		
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(183, 222, 48, 23);
		getContentPane().add(btnNewButton);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MainApp frame = new MainApp();
				frame.setVisible(true);
			}
		});

	}
}
