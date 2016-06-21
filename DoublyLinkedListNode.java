/**
 * a class that implements a Doubly Linked List Node
 * @author xuefeichen
 *
 * @param <T>
 */
public class DoublyLinkedListNode<T> extends LinkedListNode<T>{
	
	// an instance field that represents the previous node of the current node
	private DoublyLinkedListNode<T> prev;
	
	//private DoublyLinkedListNode<T> next;
	
	// constructor
	public DoublyLinkedListNode(){
		
		// inherits the super class constructor
		super();
	}
	
	// a method to set the previous node to be the node passed in
	public void setPrev(DoublyLinkedListNode<T> prevNode){
		
		// let prev point to the node passed in
		prev = prevNode;
	}
	
	// a method to get the previous node of the current node
	public DoublyLinkedListNode<T> getPrev(){
		
		
		// return prev, which points to the previous node
		return prev;
	}
	
}
