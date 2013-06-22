package thread;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author John B. Matthews
 */
public class ThreadWatch {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            //@Override
            public void run() {
                new ThreadWatch().create();
            }
        });
    }

    private void create() {
        JFrame f = new JFrame("Thread Watch");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Model model = new Model();
        View view = new View(model);
        Control control = new Control(model);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(view, BorderLayout.CENTER);
        panel.add(control, BorderLayout.SOUTH);
        f.add(panel);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        new Thread(model).start();
    }
}

class Control extends JPanel implements ActionListener {

    private static final String RESET = "Reset";
    private Model model;

    public Control(Model model) {
        this.model = model;
        this.addButton(RESET);
    }

    private void addButton(String name) {
        JButton button = new JButton(name);
        button.setAlignmentX(JButton.CENTER_ALIGNMENT);
        add(button);
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (RESET.equals(cmd)) {
            model.reset();
        }
    }
}

class View extends JPanel implements Observer {

    private static final int WIDE = 600;
    private static final int HIGH = WIDE * Model.ASPH / Model.ASPW;
    private Model model;

    public View(Model model) {
        this.model = model;
        this.setPreferredSize(new Dimension(WIDE, HIGH));
        model.addObserver(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setComposite(AlphaComposite.Src);
        g.drawImage(model.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }

    public void update(Observable o, Object arg) {
        this.repaint();
    }
}

/**
 * A stochastic model that simulates the accumulation of image data.
 *
 * The model runs on a separate thread. A javax.swing.Timer is used for interim
 * updates so that the actionPerformed() method executes on the
 * event-dispatching thread. As this relies on the Observable's call to
 * update(), avoid calling notifyObservers() from any other thread. Note also
 * that image is final [JLS 17.5].
 */
class Model extends Observable implements ActionListener, Runnable {

    public static final int RATE = 25; // 25 Hz
    public static final int ASPW = 5;  // Aspect ratio width
    public static final int ASPH = 3;  // Aspect ratio height
    private static final int WIDE = 256;
    private static final int HIGH = WIDE * ASPH / ASPW;
    private static final Random random = new Random();
    private static final Color[] clut = new Color[16];

    static {
        for (int i = 0; i < clut.length; i++) {
            int v = i * 16;
            clut[i] = new Color(v, v, v);
        }
    }
    private final Timer timer = new Timer(1000 / RATE, this);
    private final BufferedImage image = new BufferedImage(WIDE, HIGH, BufferedImage.TYPE_INT_RGB);
    private final Graphics2D g2d = image.createGraphics();
    private final WritableRaster raster = image.getRaster();
    private final int[] ia = new int[3];
    private int index;

    public void run() {
        reset();
        while (true) {
            next();
            Thread.yield();
        }
    }

    private void next() {
          double dx = Math.abs(random.nextGaussian());
            double dy = Math.abs(random.nextGaussian());
            int x = (int) (0.5 * WIDE / 2.123d);
            int y = (int) (0.5 * HIGH / 2.123d);
            if (x < WIDE && y < HIGH) {
                raster.getPixel(x, y, ia);
                adjust(0);
                adjust(1);
                adjust(2);
               // adjust(3);
                raster.setPixel(x, y, ia);
            }
                 

    }

    private void adjust(int i) {
        ia[i]++;
        if (ia[i] > 255) {
            ia[i] = 128;
        }
    }

    public synchronized BufferedImage getImage() {
        return image;
    }

    public synchronized Timer getTimer() {
        return timer;
    }

    public synchronized void reset() {
        timer.stop();
        g2d.setComposite(AlphaComposite.Clear);
        g2d.fillRect(0, 0, WIDE, HIGH);
        g2d.setComposite(AlphaComposite.Src);
        Color c = Color.getHSBColor(random.nextFloat(), 0.9f, 0.9f);
        float[] fa = c.getRGBComponents(null);
        g2d.setPaint(new Color(fa[0], fa[1], fa[2], 0.75f));
        g2d.fillRect(0, 0, WIDE, HIGH);
        timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timer) {
            this.setChanged();
            this.notifyObservers();
        }
    }
}