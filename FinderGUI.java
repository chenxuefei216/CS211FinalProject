import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * A class representing the main panel of the application 
 * @author xuefeichen
 *
 */
public class FinderGUI extends JPanel{

	// a lost list to hold all the single lost panels
	private LostList lostList;
	
	// a new reader 
	private LostInfoReaderWriter reader = new LostInfoReaderWriter("LostInfo.txt");
	
	// a panel for displaying lost people
	private JPanel peoplePanel = new JPanel();
	
	// a panel for displaying lost pets
	private JPanel petsPanel = new JPanel();
	
	// a string array for the type of the lost one
	private String[] types = {"Person", "Pet"};
	
	// a combo box for selecting the type
	private JComboBox lostType = new JComboBox(types);
	
	//
	private JLabel instructLabel = new JLabel();
	
	// a temp lost object to hold the current object being added
	private LostOne newLostObject;
	
	// a temp single lost panel to hold the current panel being added
	private SingleLostPanel newLostPanel;
	
	// the path of the lost one's photo
	private String path;
	
	// id for the lost one
	private int id =0;
	
	// constructor
	public FinderGUI(){
		
		// extend the super class
		super();
		
		// get the lost list from the file reader
		lostList = reader.readFile();
		
		// set the layout to be border layout
		setLayout(new BorderLayout());
		
		// add the command panel to the north
		add(createMainCommandPanel(), BorderLayout.NORTH);
		
		// add the lists panel to the center
		add(createListPanel(), BorderLayout.CENTER);
		
		// display any existing single lost panels
		displaySingleLostPanels();
	}
	
	// create the main command panel
	private JPanel createMainCommandPanel(){
		
		// make a new panel
		JPanel mainP = new JPanel();
		
		// set the layout to be a 2x1 grid layout
		mainP.setLayout(new GridLayout(2,1));
		
		// create the top most panel, with all the commands
		mainP.add(createTopPanel());
		
		// create a panel for indicating the type of the lost ones
		mainP.add(createTypePanel());
		
		// return the panel
		return mainP;
	}
	
	// create the top most panel
	private JPanel createTopPanel(){
		
		// make a new panel
		JPanel topP = new JPanel();
		
		// set the layout to be a 1x3 grid layout
		topP.setLayout(new GridLayout(1,3));
	
		// add the button to upload photos
		topP.add(createUploadButton());
		
		// add the combo box for selecting the lost one's type
		topP.add(lostType);
		
		// add the button to add the lost one to the list
		topP.add(createAddButton());
		
		// return the panel
		return topP;
	}
	
	// create the panel indicating the type of the lost one
	private JPanel createTypePanel(){
		
		// make a new panel
		JPanel typeP = new JPanel();
		
		// set the layout to be a 1x2 grid layout
		typeP.setLayout(new GridLayout(1,2));
					
		// make a new label indicting the list is for lost people
		JLabel peopleL = new JLabel("Lost People:");
		
		// add the label
		typeP.add(peopleL);
		
		// make a new label indicting the list is for lost pets
		JLabel petsL = new JLabel("Lost Pets:");
		
		// add the label
		typeP.add(petsL);
		
		// return the panel
		return typeP;
	}
	
	
	// create a button for uploading photos
	private JButton createUploadButton(){
		
		// make a new button
		JButton uploadB = new JButton("Upload A Photo");
		
		// add an action listener
		uploadB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){
				
				// get the current id
				int id = reader.getId();
				
				// make a new lost one
				newLostObject = new LostOne(id,null, null, null, new DoublyLinkedList<String>(), false);
				
				// make a new lost panel
				newLostPanel = new SingleLostPanel(newLostObject);
				
				// increase the current id in the reader
				reader.increaseId();
				
				// should pop up a window for user to choose an image
				JFileChooser fileChooser = new JFileChooser();
				
				// get the result 
				int result = fileChooser.showOpenDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION){
					
					// get the selected file
					File file = fileChooser.getSelectedFile();
					
					// get the image path
					path = file.toString();
					
					try{
						
						// make an image of the file
						Image image = ImageIO.read(file);
						
						// make a resizable image
						Image resizableImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
						
						// make a image icon using the resizable image
						ImageIcon imageIcon = new ImageIcon(resizableImage);
						
						// set the icon label of the lost panel to be the image icon
						newLostPanel.getImageLabel().setIcon(imageIcon);
						
						// set the image icon of the lost one to be the image icon
						newLostObject.setImage(imageIcon);
						
						// set the image path of the lost one to be the path
						newLostObject.setImgPath(path);
						
					}catch(IOException ex){
						
					}
				}

			}
		});
		
		// return the button
		return uploadB;
	}
	
	// create a button for adding the lost one to the list
	private JButton createAddButton(){
		
		// make a new button
		JButton addB = new JButton("Add To List");
		
		// add an action listener
		addB.addActionListener(new ActionListener(){
			
			// when clicked
			public void actionPerformed(ActionEvent e){

				// if the user has not uploaded a photo yet
				if(newLostObject==null){
					
					//make a new frame
					JFrame popFrame=new JFrame();
					
					//set size of the frame
					popFrame.setSize(300,100);
			
					// make a label notifying the user to upload a photo
					JLabel label = new JLabel("Please upload a photo and select the type!");
					
					// add this label to the frame
					popFrame.add(label);
					
					// make the frame pop out
					popFrame.setVisible(true);
					
				}else{
					
					// get the selected type
					int selectedType = lostType.getSelectedIndex();
					
					switch(selectedType){
					
						// if selected "person"
						case 0:
							
							// make the lost one true for isPerson
							newLostObject.setPersonOrNot(true);
							
							// add the lost panel to the people's panel
							peoplePanel.add(newLostPanel);
							
							// refresh the people's panel
							peoplePanel.revalidate();
							
							break;
						
						// if selected "pet"
						case 1:
							
							// make the lost one false for isPerson
							newLostObject.setPersonOrNot(false);
	
							// add the lost panel to the pets' panel
							petsPanel.add(newLostPanel);
							
							// refresh the pets' panel
							petsPanel.revalidate();
							
							break;
					}
					
					// write the new object's information to the file
					// name, description, and travel history has not been set yet
					reader.writeFile(newLostObject.getID(), newLostObject.personOrNot(), path,null,null,null);
				}
			}
		});
		
		// return the button
		return addB;		
	}
	
	// display all the existing lost people and pets
	private void displaySingleLostPanels(){
		
		// make a temp node be the first node in the lost list
		DoublyLinkedListNode<SingleLostPanel> currentNode = lostList.getFirstLost();
		
		// while there is still an object in the lost list
		while(currentNode!=null){
			
			// if the lost one is a person
			if(currentNode.getData().getLostOne().personOrNot()){
				
				// refresh the people's panel
				peoplePanel.revalidate();
				
				// add the lost panel to the people's panel
				peoplePanel.add(currentNode.getData());
			
			// otherwise
			}else{
				
				// refresh the pets' panel
				petsPanel.revalidate();
				
				// add the lost panel to the pets' panel
				petsPanel.add(currentNode.getData());
				
			}
			
			// move on to the next object
			currentNode = (DoublyLinkedListNode<SingleLostPanel>) currentNode.getNext();
		}
	}
	
	// create the panel for the lost objects
	private JPanel createListPanel(){
		
		// make a new panel
		JPanel listP = new JPanel();
		
		// set the layout to be a 1x2 grid layout
		listP.setLayout(new GridLayout(1,2));
		
		// set the background color of the people panel
		peoplePanel.setBackground(new Color(255, 230, 230));
		
		// set the layout to a 1x8 grid layout
		peoplePanel.setLayout(new GridLayout(8,1));
		
		// set the background color of the pets panel
		petsPanel.setBackground(new Color(242, 255, 230));
		
		// set the layout to a 1x8 grid layout
		petsPanel.setLayout(new GridLayout(8,1));
		
		// add the people panel
		listP.add(peoplePanel);
		
		// add the pets panel
		listP.add(petsPanel);
		
		// return the panel
		return listP;
	}
}
