package com.test.image;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class MainTest extends JFrame {
BufferedImage img ;
ImageEditor panel;
Timer to;

public MainTest()
{
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(265, 329);
    getContentPane().setLayout(null);
    img =  new BufferedImage(500, 500, 1);
    panel = new ImageEditor();
    panel.setBounds(10, 11, 152, 151);
    panel.setImage(img);
    getContentPane().add(panel);

    JButton btnIterate = new JButton("Iterate");
    btnIterate.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {


            ThirdClass t = new ThirdClass(img);
            to = new Timer(10,t);
            to.start();             
        }

    });
    btnIterate.setBounds(10, 230, 89, 23);
    getContentPane().add(btnIterate);

}

public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable()
    {
        public void run()
        {
            MainTest frame = new MainTest();
            //frame.getContentPane().add(imgx);
            frame.setVisible(true);
        }
    });

}
}