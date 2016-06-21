/**
 * A list that holds all the lost people/pets
 * @author xuefeichen
 *
 */
public class LostList {

	// a doubly linked list to hold all the lost people and pets
	private DoublyLinkedList<SingleLostPanel> lostList = new DoublyLinkedList<SingleLostPanel>();
	
	// constructor
	public LostList(){
		
	}
	
	// add a new lost panel to the doubly linked list
	public void addLost(SingleLostPanel newLost){
		
		// insert it to as last of the list
		lostList.insertLast(newLost);
	}

	// get the first node of lost panels
	public DoublyLinkedListNode<SingleLostPanel> getFirstLost(){
		
		// return the first node in the linked list
		return (DoublyLinkedListNode<SingleLostPanel>) lostList.getFirstNode();
	}
	
	// check whether the lost list is empty
	public boolean isEmpty(){
		
		// check whether the DLL is empty
		return lostList.isEmpty();
	}
}
