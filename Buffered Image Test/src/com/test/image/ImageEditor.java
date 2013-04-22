package com.test.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ImageEditor extends JPanel {
	
	/**
	 * 
	 */
	private BufferedImage img = new BufferedImage(100, 100, 1);
	Font arialFont = new Font("Arial", Font.BOLD, 12);

	boolean ready = false;
	
	/** Creates a new instance of LatticeRenderer */
	public ImageEditor() {
		setImage(getImage());
		render(100);
	}

	public void paint(Graphics g) {
		if (img == null)
			super.paint(g);
		else
			g.drawImage(img, 0, 0, this);
	}
	

	
	// Yeah, it's ugly.  But it works, and I didn't feel like commenting it. :)
	// All it does it slaps the given lattice's weight values up in a 2x2
	// grid as an image
	public void render( int iteration) {

	float cellWidth = 5;
	float cellHeight = 5;
		
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
							(int)cellWidth, (int)cellHeight);
			}
		}
		g2.setColor(Color.black);
		g2.setFont(arialFont);
		g2.dispose();
		repaint();
	}
	
	public BufferedImage getImage() {
		if (img == null)
			img = (BufferedImage)createImage(100, 100);
		
		return img;
	}
	
	public void setImage(BufferedImage bimg) {
		img = bimg;
	}
	


	
	
    public static void main( String [] args ) {
         JFrame frame = new JFrame();
         frame.add(new ImageEditor());
         frame.setVisible( true );

    }
}