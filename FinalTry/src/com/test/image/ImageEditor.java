package com.test.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ImageEditor extends JPanel  { //implements ActionListener
	
	private BufferedImage img = new BufferedImage(100, 100, 1);	
	//Timer t; 
	
	public ImageEditor() {
		setLayout(null);
		
/*		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				render(); //same object so no issue of calling.
				test();
			}
		});
		btnNewButton.setBounds(10, 476, 89, 23);
		add(btnNewButton);
		
		JButton btnPause = new JButton("Pause");
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.stop();
			}
		});
		btnPause.setBounds(109, 476, 89, 23);
		add(btnPause);
		
		JButton btnNewButton_1 = new JButton("Restart");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				t.start();
			}
		});
		btnNewButton_1.setBounds(208, 476, 89, 23);
		add(btnNewButton_1);*/
		//render();
	}
	
	public void paintComponent(Graphics g) {
		if (img == null)
			super.paintComponents(g);
		else
			g.drawImage(img, 0, 0, this);
	}
	

	public void render() {

	float cellWidth = 10;
	float cellHeight = 10;
		
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		float r, g, b;
		Graphics2D g2 = img.createGraphics();
		g2.setBackground(Color.black);
		g2.clearRect(0,0,imgW,imgH);
		for (int x=0; x<100; x++) {
			for (int y=0; y<100; y++) {
				r = (float)Math.random();
				g = (float)Math.random();
				b = (float)Math.random();
				g2.setColor(new Color(r,g,b));
				g2.fillRect((int)(x*cellWidth), (int)(y*cellHeight),
							(int)cellWidth+1, (int)cellHeight+1);
			}
		}
		g2.setColor(Color.black);
		g2.dispose();
		repaint();
	}
	
	public void render2(BufferedImage buff)
	{
		setImage(buff);
		repaint();
	}
		
	public void renderb() {

	float cellWidth = 10;
	float cellHeight = 10;
		
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		float r, g, b;
		Graphics2D g2 = img.createGraphics();
		g2.setBackground(Color.black);
		g2.clearRect(0,0,imgW,imgH);
		for (int x=0; x<100; x++) {
			for (int y=0; y<100; y++) {
				r = (float)Math.random();
				g = (float)Math.random();
				b = (float)Math.random();
				g2.setColor(new Color(0,0,b));
				g2.fillRect((int)(x*cellWidth), (int)(y*cellHeight),
							(int)cellWidth+1, (int)cellHeight+1);
			}
		}
		g2.setColor(Color.black);
		g2.dispose();
		repaint();
	}
	
	public void renderg() {

	float cellWidth = 10;
	float cellHeight = 10;
		
		int imgW = img.getWidth();
		int imgH = img.getHeight();
		float r, g, b;
		Graphics2D g2 = img.createGraphics();
		g2.setBackground(Color.black);
		g2.clearRect(0,0,imgW,imgH);
		for (int x=0; x<100; x++) {
			for (int y=0; y<100; y++) {
				r = (float)Math.random();
				g = (float)Math.random();
				b = (float)Math.random();
				g2.setColor(new Color(0,g,0));
				g2.fillRect((int)(x*cellWidth), (int)(y*cellHeight),
							(int)cellWidth+1, (int)cellHeight+1);
			}
		}
		g2.setColor(Color.black);
		g2.dispose();
		repaint();
	}
	
	public BufferedImage getImage() {
		if (img == null)
			img = (BufferedImage)createImage(500, 500);
		
		return img;
	}
	
	public void setImage(BufferedImage bimg) {
		img = bimg;
	}
	
	
/*    public static void main( String [] args ) {
         JFrame frame = new JFrame();
         frame.getContentPane().add(new ImageEditor());
         frame.setSize(700, 700);
         frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
         frame.setVisible( true );

    }*/

/*    public void test()
    {
		t = new Timer(100,this);
		t.start();		
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		render();
	}*/
}