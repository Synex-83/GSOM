/**
 * 
 */
package maps.Structures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class DisplayLattice extends JPanel  {
	
	private BufferedImage img = new BufferedImage(500, 500, 1);	
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null)
		{
			g.drawImage(img, 0, 0, this);
		}
	}
	

	public void render() {

	float cellWidth = 100;
	float cellHeight = 100;
		
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
}