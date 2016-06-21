/**
 * a class that implements Doubly Linked List
 * @author xuefeichen
 *
 * @param <T>
 */
public class DoublyLinkedList<T> extends LinkedList<T> {
	
	// instance fields to represent the head and the tail
	
	private DoublyLinkedListNode<T> tail;
	
	//constructor
	public DoublyLinkedList(){
		
		//inherit the superclass
		super();
	}
	

	//get the tail node of the list
	public DoublyLinkedListNode<T> getLastNode()
	{		
		//return the tail
		return tail;				
	}
	
	// get the data in the tail 
	public T getLast(){
		
		//if the list is empty
		if(isEmpty()){
			
			//return null
			return null;
		
		// otherwise
		}else{
			
			// return the data of the tail
			return tail.getData();
		}		
	}
	
	public void insertFirst(T data){
		
		// make a new node
		DoublyLinkedListNode<T> insertedFirst=new DoublyLinkedListNode<T>();
				
		// set the data of this new node to be data
		insertedFirst.setData(data);
			
		// if the list is empty
		if(isEmpty()){
			
			// make the head the node to be inserted
			head = insertedFirst;
			
			// the tail should also be this node
			tail = insertedFirst;
		
		// if the list is not empty
		}else{
			
			// make the next node of this new node be the head
			insertedFirst.setNext((DoublyLinkedListNode<T>)head);
			
			//DoublyLinkedListNode<T> nextNode = (DoublyLinkedListNode<T>)insertedFirst.getNext();
			// update the previous node of the head to be the new node
			((DoublyLinkedListNode<T>)insertedFirst.getNext()).setPrev(insertedFirst);
		
			// update the head by letting head be this new node
			head=insertedFirst;			
		}
		
		// add 1 to the size
		counter++;
	}
	
	//insert a new node with the data after currentNode
	public void insertAfter(DoublyLinkedListNode<T> currentNode, T data)
	{
		// only execute when the node passed in is not null
		if(currentNode!=null){
			
			// if currentNode is not the tail
			if(currentNode!=getLastNode()){
				
				// make a new node
				DoublyLinkedListNode<T> insertedAfterCurrent=new DoublyLinkedListNode<T>();
				
				// set the data of this node to be data
				insertedAfterCurrent.setData(data);
				
				//set the next node of this new node to be the next node of the currentNode
				insertedAfterCurrent.setNext(currentNode.getNext());
				
				
				// update the previous node of the next node to be the new node
				((DoublyLinkedListNode<T>)currentNode.getNext()).setPrev(insertedAfterCurrent);
				
				// set the next node of the currentNode to be this new node
				currentNode.setNext(insertedAfterCurrent);	
				
				// update the previous node of the new node to be currentNode
				insertedAfterCurrent.setPrev(currentNode);
			
				//add 1 to the size
				counter++;
				
			// if currentNode is the tail
			}else{
				
				// use insertLast instead
				insertLast(data);
			}
		}
	}

	//insert a new node with the data at the tail of the list.
	public void insertLast(T data)
	{
		//make a new node
		DoublyLinkedListNode<T> insertedLast=new DoublyLinkedListNode<T>();
		
		//set the data of the new node to be data
		insertedLast.setData(data);
		
		//if the list is empty
		if(isEmpty()){
			
			//make the head and tail this new node
			head=insertedLast;
			tail=insertedLast;
		//otherwise
		}else{
			
			// update the previous node of the inserted node to be the originally last node
			insertedLast.setPrev(getLastNode());
			
			// set the next node of the last node to be this new node
			getLastNode().setNext(insertedLast);
			
			// update the tail
			tail = insertedLast;
			
		}
			
		//add 1 to the size
		counter++;
	}

	//remove the first node.
	public void deleteFirst()
	{
		//only if the list is not empty
		if(!isEmpty()){
			
			//if head is the only node in the list
			if(head.getNext()==null){
				
				//make head and tail null
				head = null;
				tail = null;
				
			//otherwise
			}else{
				
				//let the head be the node after the current head
				DoublyLinkedListNode<T> newhead=(DoublyLinkedListNode<T>)head.getNext();
		
				// update the previous node of the new head to be null
				newhead.setPrev(null);
				
				//update the head
				head = newhead;
			}
						
			//minus 1 from the size
			counter--;
		}		
	}
	
	//remove the last node
	public void deleteLast(){
		
		//only if the list is not empty
		if(!isEmpty()){
			
			// if there is only one node
			if(head.getNext()==null){
				
				//make the head and tail null
				head = null;
				tail = null;
				
			//otherwise
			}else{
				
				// let the tail be the node before the current tail
				tail = tail.getPrev();
				
				// update the next node of the new tail to be null
				tail.setNext(null);
				
			}	
			
			//minus 1 from the size
			counter--;
		}
	}

	//remove node following currentNode. If no node exists (currentNode is the tail), do nothing.
	public void deleteNext(DoublyLinkedListNode<T> currentNode)
	{
		// only execute when the node passed in is not empty
		if(currentNode!=null){
			
			// if currentNode is not the tail
			if(currentNode.getNext()!=null)
			{
				// if the currentNode is the second last node
				if (currentNode.getNext().getNext()==null){
					
					// use deleteLast method
					deleteLast();
										
				// if the currentNode is not the second last node
				}else{
					
					DoublyLinkedListNode<T> nextNextNode = (DoublyLinkedListNode<T>)currentNode.getNext().getNext();
					
					// update the previous node of the next next node of currentNode to be currentNode
					nextNextNode.setPrev(currentNode);
					
					// set the next node of currentNode to be the one after the next node of currentNode
					currentNode.setNext(currentNode.getNext().getNext());						
					
					// minus 1 from the size
					counter--;
				}				
			}
		}
	}
	
	// directly delete the node passed in
	public void deleteNode(DoublyLinkedListNode<T> node){
		
		// execute only when the node passed in is not null
		if(node!=null){
			
			//if the node to be deleted is the head
			if(node.getPrev()==null){
				
				//directly call deleteFirst() method
				deleteFirst();
			
			//if the node to be deleted is the tail
			}else if(node.getNext()==null){
				
				//directly call deleteLast() method
				deleteLast();
				
			//if the node is neither head nor tail
			}else{
				
				//store its previous node
				DoublyLinkedListNode<T> prevNode = node.getPrev();
				
				//store its next node
				DoublyLinkedListNode<T> nextNode = (DoublyLinkedListNode<T>)node.getNext();
				
				//set the previous node of its next node to be its previous node
				nextNode.setPrev(prevNode);
				
				//set the next node of its previous node to be its next node
				prevNode.setNext(nextNode);
				
				//minus 1 from the counter
				counter--;
			}
		}
	}
	
	// search a node that contains the data passed in
	public DoublyLinkedListNode<T> search(T data){
		
		//make a new DLL  node that starts from the head
		DoublyLinkedListNode<T> tempNode = (DoublyLinkedListNode<T>)head;
		
		//while the node is still in the list and the its data does not match the target data
		while(tempNode!=null && !tempNode.getData().equals(data)){
			
			//move to the next node in the list
			tempNode = (DoublyLinkedListNode<T>)tempNode.getNext();
		}
		
		//return the searched node
		return tempNode;
	}
}
