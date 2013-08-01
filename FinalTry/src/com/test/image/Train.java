/**
 * 
 */
package com.test.image;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/**
 * @author 		User
 * @date		Jun 21, 2013 - 12:57:58 PM
 * @type        Train
 *
 */
public class Train implements ActionListener {

	ImageEditor temp;
	BufferedImage tempImg;
	static boolean status = true;
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	Train(ImageEditor img)
	{
		temp = img;
	}
	
	Train(ImageEditor img, BufferedImage buff)
	{
		temp = img;
		tempImg = buff;
	}
	
	private BufferedImage exportImageNorm(boolean option)
	{
		BufferedImage colorNodes = new BufferedImage(500, 500, 1);
		double[][] normL2values = new double[500][500];
	
		if(option)
		{
			for(int i = 0; i < normL2values.length ; i++){
				for(int j = 0; j < normL2values[0].length; j++){
					colorNodes.setRGB(i, j, (new Color((float)0,(float)0,(float)Math.random())).getRGB());
				}
			}
		}
		else
		{
			for(int i = 0; i < normL2values.length ; i++){
				for(int j = 0; j < normL2values[0].length; j++){
					colorNodes.setRGB(i, j, (new Color((float)0,(float)Math.random(),(float)0)).getRGB());
				}
			}
		}

		return colorNodes;
	}
	
	
	
	public void train()
	{
		temp.renderg();	
	}
	
	public void train2()
	{
		status = !status;
		temp.render2(exportImageNorm(status));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		train();
	}

}
