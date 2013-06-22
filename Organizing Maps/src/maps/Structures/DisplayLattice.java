/**
 * 
 */
package maps.Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * @author User
 *
 */
public class DisplayLattice extends JPanel {

	private BufferedImage IMAGE = IMAGE = new BufferedImage(400, 400, 1);;
	
	public DisplayLattice()
	{
		
		//repaint();
	}
	

	public void paintComponent(Graphics g) {	
		if (IMAGE == null)
			super.paintComponents(g);
		else
			g.drawImage(IMAGE, 0, 0, this);
	}
	
	public BufferedImage getImage()
	{
		if(IMAGE == null)
				IMAGE = (BufferedImage)createImage(getWidth(), getHeight());
		
		return IMAGE;
	}
	
	public void render() {

	int cellWidth = 50;
	int cellHeight = 50;
		
		int imgW = IMAGE.getWidth();
		int imgH = IMAGE.getHeight();
		
		Graphics2D g2 = IMAGE.createGraphics();
		g2.setBackground(Color.black);
		g2.clearRect(0,0,IMAGE.getWidth(),IMAGE.getHeight());
		
		for (int x=0; x<imgW; x++) {
			for (int y=0; y<imgH; y++) {
				//g2.setColor(new Color(IMAGE.getRGB(x, y)));
				g2.setColor(new Color((float)Math.random(),(float)Math.random(),(float)Math.random()));
				g2.fillRect((int)(x*cellWidth), (int)(y*cellHeight),
							(int)cellWidth, (int)cellHeight);
			}
		}
		g2.setColor(Color.black);
		g2.dispose();
		repaint();
	}	
	
	public void setImage(BufferedImage image)
	{
		IMAGE = image;
		repaint();
	}
}
