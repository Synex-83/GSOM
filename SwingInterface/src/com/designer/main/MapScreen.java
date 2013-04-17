/**
 * 
 */
package com.designer.main;

import java.awt.Window.Type;

import javax.swing.JFrame;

/**
 * @author User
 *
 */
public class MapScreen extends JFrame {
	public MapScreen() {
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Map");
		setSize(600, 600);
		setLocation(150,150);
		getContentPane().setLayout(null);
	}

}
