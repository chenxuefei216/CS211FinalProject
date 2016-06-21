import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 * A class representing a panel for manipulating and seeing a lost one's information 
 * @author xuefeichen
 *
 */
public class SingleLostPanel extends JPanel{

	// a lost individual or pet
	private LostOne lostOne;
	
	// a reader for getting the existing lost ones
	private LostInfoReaderWriter reader = new LostInfoReaderWriter("LostInfo.txt");	
	
	// a label to hold the image of the lost one
	private JLabel imageLabel = new JLabel("");
	
	// a text field for entering descriptions
	private JTextField addDesUserInputField = new JTextField(100);
	
	// a text field for entering the name
	private JTextField addNameUserInputField = new JTextField(100);
	
	// a text field for entering travel histories (locations)
	private JTextField enterHisUserInputField = new JTextField(100);
	
	// a text area for displaying travel histories
	private JTextArea travelHistoryDisplay = new JTextArea();
	
	// a combo box for choosing the hours
	private JComboBox hourBox;
	
	// a combo box for choosing the minutes
	private JComboBox minuteBox;
	
	// constructor, taking in a lost one object
	public SingleLostPanel(LostOne newLostOne){
		
		// extends the super class
		super();
		
		// let the lost one be the one passed in
		lostOne = newLostOne;
		
		// set the layout of this single panel to be a box layout
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));		

		// set the icon of the image label to be the image icon of the lost one
		imageLabel.setIcon(lostOne.getImage());
		
		// add the image label
		add(imageLabel);
		
		// add a series of buttons
		add(createButtonsPanel());
		
	}
	
	// create the panel that holds all the buttons
	private JPanel createButtonsPanel(){
		
		// make a new panel
		JPanel buttonsP = new JPanel();
		
		// set the layout to be a 1x3 grid layout
		buttonsP.setLayout(new GridLayout(3,1));
		
		// add the description button
		buttonsP.add(createAddDesButton());
		
		// add the name button
		buttonsP.add(createAddNameButton());
		
		// add the travel history button
		buttonsP.add(createTravelHisButton());
		
		// return the panel
		return buttonsP;
	}
	
	// create the button that let users add and see the descriptions
	private JButton createAddDesButton(){
		
		// make a new button
		JButton addDesB = new JButton("Add/See Description");
		
		// with no borders
		addDesB.setBorderPainted(false);
		
		// set background color
		addDesB.setBackground(new Color(204, 230, 255));
		
		// shows color
		addDesB.setOpaque(true);
		
		// add an action listener
		addDesB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// create a frame letting users enter or see descriptions
				createAddDesFrame().setVisible(true);
			
			}
		});	
		
		// return the button
		return addDesB;
	}
	
	// create the description frame
	private JFrame createAddDesFrame(){
		
		// make a new frame
		JFrame addDesFrame = new JFrame();
		
		//set size of the frame
		addDesFrame.setSize(300,200);
		
		//make a new panel
		JPanel panel=new JPanel();
		
		//set the layout to be a box layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		

		// make a new label		
		JLabel instructLabel = new JLabel();
		
		//set the instruction text
		instructLabel.setText("<html>Enter/See the description for the lost one <br> (<50 words):</html>");
			
		//add the instruction label
		panel.add(instructLabel);
				
		//add the user input text field
		panel.add(addDesUserInputField);
				
		// only when the description has been set 
		if(lostOne.getDescription()!=null && lostOne.getDescription().equals("null")==false){
			
			// set the text of the input field to be the description
			addDesUserInputField.setText(lostOne.getDescription());
			
			// make the input field no longer editable
			addDesUserInputField.setEditable(false);
		}
			
		//add the ok button
		panel.add(createAddDesOKButton());
						
		//add the panel to the frame
		addDesFrame.add(panel);
		
		//return the frame
		return addDesFrame;
	}
	
	// create the ok button for the description frame
	private JButton createAddDesOKButton(){
		
		// make a button
		JButton addDesOKB = new JButton("OK");
		
		// add an action listener
		addDesOKB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// only when the description has not been set
				if(lostOne.getDescription()==null){
					
					// set the description to be the user input
					lostOne.setDescription(addDesUserInputField.getText());
					
					// make the input field not editable 
					addDesUserInputField.setEditable(false);
					
					// let the input field display the description
					addDesUserInputField.setText(lostOne.getDescription());
					
					// modify the data in the file
					reader.modifyFile(lostOne.getID(),lostOne.personOrNot(),lostOne.getImgPath(),lostOne.getName(), lostOne.getDescription(), lostOne.getTravelHistory().toString());
				}
			}
		});	
		
		// return the button
		return addDesOKB;
	}
	
	// create a button to let users add or see the name
	private JButton createAddNameButton(){
		
		// make a new button
		JButton addNameB = new JButton("Add/See Name");
		
		// with no borders
		addNameB.setBorderPainted(false);
		
		// set background color
		addNameB.setBackground(new Color(255, 194, 153));
		
		// show the color
		addNameB.setOpaque(true);
		
		// add an action listener
		addNameB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				// create a frame to let users add or see the name
				createAddNameFrame().setVisible(true);
				
			}
		});	
		
		// return the button
		return addNameB;
	}
	
	// create the frame to add and see the name
	private JFrame createAddNameFrame(){
		
		// make a new frame
		JFrame addNameFrame = new JFrame();
		
		//set size of the frame
		addNameFrame.setSize(300,200);

		//make a new panel
		JPanel panel=new JPanel();
		
		//set the layout to be a box layout
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));		
		
		// make an instruction label
		JLabel instructLabel = new JLabel();
			
		//set the instruction text
		instructLabel.setText("<html>Enter/See the name of the lost one <br> (<50 words):</html>");
			
		//add the instruction label
		panel.add(instructLabel);
				
		//add the user input text field
		panel.add(addNameUserInputField);
				
		// only if the name has been set
		if(lostOne.getName()!=null && lostOne.getName().equals("null")==false){
			
			// let the user input field display the name
			addNameUserInputField.setText(lostOne.getName());
			
			// make the field no longer editable
			addNameUserInputField.setEditable(false);
		}
		
		//add the ok button
		panel.add(createAddNameOKButton());

		//add the panel to the frame
		addNameFrame.add(panel);

		//return the frame
		return addNameFrame;
	}
	
	// create the ok button for adding and seeing the name
	private JButton createAddNameOKButton(){
		
		// make a new button
		JButton addNameOKB = new JButton("OK");
		
		// add an action listener
		addNameOKB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// only when the name has not been set yet
				if(lostOne.getName()==null || lostOne.getName().equals("null")){
					
					// set the name of the lost one
					lostOne.setName(addNameUserInputField.getText());
					
					// make the input field not editable
					addNameUserInputField.setEditable(false);
					
					// let the input field display the name
					addNameUserInputField.setText(lostOne.getName());
					
					// modify the data in the file
					reader.modifyFile(lostOne.getID(),lostOne.personOrNot(),
							lostOne.getImgPath(),lostOne.getName(), lostOne.getDescription(), lostOne.getTravelHistory().toString());
				}
				
			}
		});
		
		// return the button
		return addNameOKB;
	}	
	
	// create the button for adding and seeing the travel histories
	private JButton createTravelHisButton(){
		
		// make a button
		JButton travelHisB = new JButton("See/Enter Travel History");
		
		// with no borders
		travelHisB.setBorderPainted(false);
		
		// set background color
		travelHisB.setBackground(new Color(238, 204, 255));
		
		// show the color
		travelHisB.setOpaque(true);
		
		// add an action listener
		travelHisB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// create a frame for adding and seeing travel histories
				createTravelHisFrame().setVisible(true);
			}
		});
		
		// return the button
		return travelHisB;
	}
	
	// create a frame for adding and seeing travel histories
	private JFrame createTravelHisFrame(){
		
		// make a new frame
		JFrame travelHisF = new JFrame("Travel History");
		
		// set the size of the frame
		travelHisF.setSize(600, 400);
		
		// set the layout to be border layout
		travelHisF.setLayout(new BorderLayout());
		
		// make a new label to show who the user is helping find
		JLabel topL = new JLabel("You are helping find: "+lostOne.getName());
		
		// set the background color
		topL.setBackground(new Color(255, 204, 128));
		
		// add the label to the north of the frame
		travelHisF.add(topL, BorderLayout.NORTH);
		
		// add the button for adding histories to the south of the frame
		travelHisF.add(createEnterHisButton(), BorderLayout.SOUTH);
		
		// let the text area for displaying the travel history wrap text automatically
		travelHistoryDisplay.setLineWrap(true);
		
		// make it not editable
		travelHistoryDisplay.setEditable(false);
		
		// set the background color
		travelHistoryDisplay.setBackground(new Color(255, 255, 230));
		
		// set the layout to be the flow layout
		travelHistoryDisplay.setLayout(new FlowLayout());
		
		// add the text area to the center of the frame
		travelHisF.add(travelHistoryDisplay, BorderLayout.CENTER);
		
		// let the text area display the travel history
		travelHistoryDisplay.setText(lostOne.getTravelHistory().toString());
		
		// return the frame
		return travelHisF;
	}
	
	// create a button letting users to enter the travel history
	private JButton createEnterHisButton(){
		
		// make a new button
		JButton enterHisB = new JButton("Enter Time and Location");
		
		// add an action listener
		enterHisB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// create a frame letting the users to add the times and locations
				createEnterHisFrame().setVisible(true);
			}
		});
		
		// return the button
		return enterHisB;
	}
	
	// create a frame letting the users to add the times and locations
	private JFrame createEnterHisFrame(){
		
		// make a new frame
		JFrame enterHisFrame = new JFrame();
		
		//set size of the frame
		enterHisFrame.setSize(500,200);

		// make a new panel
		JPanel enterHistoryPanel = new JPanel();
		
		//set the layout to be a box layout
		enterHistoryPanel.setLayout(new BoxLayout(enterHistoryPanel, BoxLayout.Y_AXIS));		

		// make a instruction label
		JLabel enterHistoryInstructLabel = new JLabel();
		
		//set the instruction text
		enterHistoryInstructLabel.setText("Select the time and enter the location of its occurance");
		
		//add the instruction label
		enterHistoryPanel.add(enterHistoryInstructLabel);
		
		// add the panel for selecting the time
		enterHistoryPanel.add(createTimeSelectionPanel());
		
		//add the user input text field
		enterHistoryPanel.add(enterHisUserInputField);
		
		//add the ok button
		enterHistoryPanel.add(createEnterHisOKButton());
		
		//add the panel to the frame
		enterHisFrame.add(enterHistoryPanel);
		
		//return the frame
		return enterHisFrame;
	}
	
	// create a panel for selecting the time
	private JPanel createTimeSelectionPanel(){
		
		// make a new panel
		JPanel timeP = new JPanel();
		
		// set the layout to be a 1x4 grid layout
		timeP.setLayout(new GridLayout(1,4));
		
		// add a label
		timeP.add(new JLabel("Hour"));
		
		// make a string array for all the hours
		String[] hours = {"00", "01", "02", "03", "04", "05", "06","07","08","09","10","11","12","13",
				"14","15","16","17","18","19","20","21","22","23"};
		
		// make a string array for all the minutes, with an increment of 5 
		String[] minutes  ={"00", "05", "10","15","20","25","30","35","40","45","50","55"};
		
		// make a new combo box for the hours
		hourBox = new JComboBox(hours);
		
		// make a new combo box for the minutes
		minuteBox = new JComboBox(minutes);
		
		// add the hours box to the panel
		timeP.add(hourBox);
		
		// add a new label
		timeP.add(new JLabel("Minute"));
		
		// add the minutes box to the panel
		timeP.add(minuteBox);
		
		// return the panel
		return timeP;
	}

	// create the ok button for entering the travel history
	private JButton createEnterHisOKButton(){
		
		// make a new button
		JButton enterHisOKB = new JButton("OK");
		
		// add an action listener
		enterHisOKB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// get the location from the input field
				String location = enterHisUserInputField.getText();
				
				// make a string of travel history using the time and location
				String travelHistory = (String) hourBox.getSelectedItem() + ":"+ (String)minuteBox.getSelectedItem()+" "+location;
				
				// add the time and location string to the travel history list
				lostOne.addHistory(travelHistory);
				
				// let the text area display the travel history
				travelHistoryDisplay.setText(lostOne.displayHistory());			
				
				// refresh the input field
				enterHisUserInputField.setText("");
				
				// modify the data in the file
				reader.modifyFile(lostOne.getID(),lostOne.personOrNot(),lostOne.getImgPath(),lostOne.getName(), lostOne.getDescription(), lostOne.getTravelHistory().toString());
			}
		});
		
		// return the button
		return enterHisOKB;
	}

	// get the lost one object
	public LostOne getLostOne(){
		
		// return the lost one
		return this.lostOne;
	}
	
	// get the image label
	public JLabel getImageLabel(){
		
		// return the image label
		return imageLabel;
	}
}
