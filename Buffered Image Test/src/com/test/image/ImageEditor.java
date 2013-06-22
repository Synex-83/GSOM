package com.test.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class ImageEditor extends JPanel {

private static BufferedImage img = new BufferedImage(100, 100, 1);  
public ImageEditor() {
    render();
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

public BufferedImage getImage() {
    if (img == null)
        img = (BufferedImage)createImage(100, 100);

    return img;
}

public void setImage(BufferedImage bimg) {
    img = bimg;
}
}