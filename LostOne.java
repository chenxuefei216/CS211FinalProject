import javax.swing.ImageIcon;
/**
 * A class representing a lost individual or pet
 * @author xuefeichen
 *
 */
public class LostOne {

	// image icon of the lost one
	private ImageIcon image;
	
	// the path of the image
	private String imgPath;
	
	// the name of the lost one
	private String name;
	
	// the description of the lost one
	private String description;
	
	// a doubly linked list to hold all its time and location of being found
	private DoublyLinkedList<String> travelHistory;

	// the id of the lost one
	private int id;
	
	// a boolean, true if is a person; false if is a pet
	private boolean isPerson;
	
	// constructor, taking in all the properties of the lost one
	public LostOne(int newID, ImageIcon newImg, String newName, String newDes, 
			DoublyLinkedList<String> newTravelHis, boolean newIsPerson){
		
		// set all the properties
		id = newID;
		
		image = newImg;
		
		name = newName;
		
		description = newDes;
		
		travelHistory = newTravelHis;
		
		isPerson = newIsPerson;
	}
	
	// get its id
	public int getID(){
		
		// return the id
		return id;
	}
	
	// set its id
	public void setID(int newID){
		
		// set the id
		id = newID;
	}
	
	// change whether it is a person or a pet
	public void setPersonOrNot(boolean personTrueOrFalse){
		
		// change the isPerson property
		isPerson = personTrueOrFalse;
	}
	
	// check whether it is a person or not
	public boolean personOrNot(){
		
		// return the isPerson property
		return isPerson;
	}
	
	// set its image icon
	public void setImage(ImageIcon newImage){
		
		// set the image icon
		image = newImage;
	}
	
	// get its image icon
	public ImageIcon getImage(){
		
		// return the image icon
		return image;
	}
	
	// set its image path
	public void setImgPath(String newPath){
		
		// set the image path 
		imgPath = newPath;
	}
	
	// get its image path
	public String getImgPath(){
		
		// return the image path
		return imgPath;
	}
	// get its travel history
	public DoublyLinkedList<String> getTravelHistory(){
		
		// return the travel history
		return travelHistory;
	}
	
	// get the string representation of the travel history
	public String displayHistory(){
		
		// return the string representation of the travel history
		return travelHistory.toString();
	}
	
	// get its name 
	public String getName(){
		
		// return the name
		return name;
	}
	
	// set its name
	public void setName(String newName){
		
		// set the name
		name = newName;
	}
	
	// set its description
	public void setDescription(String newDes){
		
		// set the description
		description = newDes;
	}
	
	// get its description
	public String getDescription(){
		
		// return the description
		return description;
	}
	
	// add the time and location, in a string, to its travel history
	public void addHistory(String newRecord){
		
		// make a new DLL node to start from the head of the list
		DoublyLinkedListNode<String> tempNode = (DoublyLinkedListNode<String>)travelHistory.getFirstNode();
		
		// if the list is empty
		if(travelHistory.isEmpty()){
			
			// add it as the first node
			travelHistory.insertFirst(newRecord);
			
		// if the list is not empty, and if the new time is later 
		}else if(newRecord.compareTo(travelHistory.getLast())>=0){
			
			// add it as the last node
			travelHistory.insertLast(newRecord);
			
		// if the list is not empty, and if the new time is earlier
		}else if(newRecord.compareTo(travelHistory.getFirst())<=0){
			
			// add it as the first node
			travelHistory.insertFirst(newRecord);
		
		// otherwise
		}else{
			
			// loop through every node but the last one in the list to find the proper position to insert the new history
			for(int i=0; i<travelHistory.size()-1;i++){
				
				// if the time is between the time in tempNode
				// and that in the next node of tempNode
				if(newRecord.compareTo(tempNode.getData())>=0 &&
						newRecord.compareTo(tempNode.getNext().getData())<=0){
					
					// add it right after the tempNode
					travelHistory.insertAfter(tempNode, newRecord);
					
					// no need to loop through the remaining nodes
					break;
					
				// otherwise
				}else{
					
					// let tempNode be the next node
					tempNode = (DoublyLinkedListNode<String>)tempNode.getNext();
				}
			}
		}
	}
}
