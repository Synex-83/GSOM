

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


public class DisplayLattice extends JPanel {

	private BufferedImage IMAGE = null;
	private BufferedImage DISPLAY_IMAGE = null;
	
	public DisplayLattice()
	{
		IMAGE = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		render();
	}
	
	public DisplayLattice(BufferedImage map) {
		IMAGE = map;
		render();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(IMAGE, 0, 0, this);
	}
	
	public void render() {

	int cellWidth = 5;
	int cellHeight = 5;
		
		int imgW = IMAGE.getWidth();
		int imgH = IMAGE.getHeight();
		DISPLAY_IMAGE = new BufferedImage(imgW*cellWidth, imgH*cellHeight, 1);

		
		Graphics2D g2 = IMAGE.createGraphics();
		g2.setBackground(Color.red);
		g2.clearRect(0,0,DISPLAY_IMAGE.getWidth(),DISPLAY_IMAGE.getHeight());
		
		for (int x=0; x<imgW; x++) {
			for (int y=0; y<imgH; y++) {
				g2.setColor(new Color(IMAGE.getRGB(x, y)));
				g2.fillRect((int)(x*cellWidth), (int)(y*cellHeight),
							(int)cellWidth, (int)cellHeight);
			}
		}
		//g2.setColor(Color.black);
		g2.dispose();
		repaint();
		//revalidate();
		System.out.println("XX");
	}	
	
	public void setImage(BufferedImage image)
	{
		IMAGE = image;
		render();
		//repaint();
	}
}
