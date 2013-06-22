/**
 * 
 */
package com.test.image;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 * @author 		User
 * @date		Jun 19, 2013 - 3:11:50 PM
 * @type        MainTest
 *
 */
public class MainTest extends JFrame { //implements ActionListener

	/**
	 * @param args
	 */
	ImageEditor img;
	JPanel panel;
	Timer t;
	
	public MainTest()
	{
		setSize(600,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		img = new ImageEditor();
		panel = img;
		panel.setBounds(0, 0, 510, 510);
		getContentPane().add(panel);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//img.render();
				test();
			}
		});
		btnStart.setBounds(0, 528, 89, 23);
		getContentPane().add(btnStart);
		
	/*	JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}
		});
		btnPause.setBounds(99, 528, 89, 23);
		getContentPane().add(btnPause);
		
		JButton btnBlueGradient = new JButton("Blue Gradient");
		btnBlueGradient.setBounds(198, 528, 107, 23);
		getContentPane().add(btnBlueGradient);
		
		JButton btnGreenGradient = new JButton("Green Gradient");
		btnGreenGradient.setBounds(315, 528, 107, 23);
		getContentPane().add(btnGreenGradient);*/
		
	//	test();
	}
	
	/**
	 * 
	 */
	private void test() {
		// TODO Auto-generated method stub
		Train tx = new Train(img);
		t = new Timer(100, tx);
		t.start();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MainTest frame = new MainTest();
				frame.setVisible(true);
			}
		});

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	/*	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		img.render();
	}*/
}
