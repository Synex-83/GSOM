/**
 * 
 */


import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author User
 *
 */
public class MapScreen extends JFrame {
	

	private int WIDTH = 0;
	private int HEIGHT = 0;

	private DisplayLattice pnlMap;

	
	public MapScreen(int mapOption) {
		
		
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Map");
		setSize(600, 600);
		setLocation(150,150);
		getContentPane().setLayout(null);
		
		pnlMap = new DisplayLattice();
		pnlMap.setBounds(6, 130, 582, 440);
		getContentPane().add(pnlMap);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new SelfOrganizingMap().methodTrain(pnlMap);
			}
		});
		btnNewButton.setBounds(10, 81, 89, 23);
		getContentPane().add(btnNewButton);
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				MapScreen frame = new MapScreen(5);
				frame.setVisible(true);
			}
		});

	}
}
