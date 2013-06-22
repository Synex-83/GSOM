import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.border.EmptyBorder;

public class Try_Panel {
    // start attributes

    private JButton jButton1 = new JButton("jButton1");
    private JButton jButton2 = new JButton("jButton2");
    private JLabel imageView;
    BufferedImage image;
    int x = 300;
    int y = 50;
    // end attributes

    public Component getGui() {
        JPanel gui = new JPanel(new BorderLayout(5, 5));
        gui.setBorder(new EmptyBorder(3, 3, 3, 3));

        image = new BufferedImage(300, 100, BufferedImage.TYPE_INT_RGB);
        imageView = new JLabel(new ImageIcon(image));

        gui.add(imageView, BorderLayout.CENTER);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        gui.add(buttons, BorderLayout.PAGE_END);

        jButton1.setMargin(new Insets(2, 2, 2, 2));
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                jButton1_ActionPerformed(evt);
            }
        });
        buttons.add(jButton1);

        jButton2.setMargin(new Insets(2, 2, 2, 2));
        jButton2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                jButton2_ActionPerformed(evt);
            }
        });
        buttons.add(jButton2);
        // end components

        return gui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                // Frame-Init
                JFrame f = new JFrame("Try Panel");
                f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                Container cp = f.getContentPane();
                cp.setLayout(new BorderLayout(3, 3));

                Try_Panel tp = new Try_Panel();
                cp.add(tp.getGui());

                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    } // end of public Try_Panel

    // start methods
    public void jButton1_ActionPerformed(ActionEvent evt) {
    	int i = 0;
    	while(i < 10)
    	{
    	Graphics graphics = image.getGraphics();
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.RED);
        g.drawLine(0, 0, x, y);
        x -= 4;
        y += 2;
        g.dispose();
    	
        imageView.repaint();
        imageView.revalidate();
        
        i++;
    	}

    } // end of jButton1_ActionPerformed

    public void jButton2_ActionPerformed(ActionEvent evt) {
        // TODO add your code here
    } // end of jButton2_ActionPerformed
} // end of class Try_Panel