import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * A class that has a main method to be executed from the command line
 * @author xuefeichen
 *
 */
public class FinderApplication {

	// a main method to be executed from the command line
	public static void main(String[] args){
		
		// make a new frame
		JFrame frame = new JFrame("Beloved One Finder");
	
		// set the size of the frame 
		frame.setSize(800, 800);
		
		// exit the game when closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set the layout of the frame to be border layout
		frame.setLayout(new BorderLayout());
		
		// add the instruction panel to the north of the layout
		frame.add(createInstructPanel(), BorderLayout.NORTH);
		
		// create a new finder GUI and add it to be center of the layout
		frame.add(new FinderGUI(), BorderLayout.CENTER);
		
		// set the frame to be visible
		frame.setVisible(true);
	}
	
	// create the instruction panel
	private static JPanel createInstructPanel(){
		
		// make a new panel
		JPanel instructP = new JPanel();
		
		// set its background color
		instructP.setBackground(new Color(255, 204, 204));
		
		// make an instruction label
		JLabel instructL = new JLabel();
		
		// set its text
		instructL.setText("Upload a photo and select the type of the lost to add people/pets to the lists.");
		
		// add the label to the panel
		instructP.add(instructL);
		
		// return the panel
		return instructP;
	}
}
