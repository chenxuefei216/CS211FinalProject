//Received help from My and Ella.
public class LinkedList<T> {

	// the head of the LinkedList
	protected LinkedListNode<T> head;
	
	// a counter that keep track of the number of nodes in the list
	protected int counter;
	
	// the current node
	protected LinkedListNode<T> current=head;
	
	//constructor
	public LinkedList()
	{
		// make the counter equal to 0 first
		counter=0;
	}
	
	// get the data stored in head node of list
	public T getFirst()
	{
	
		// return the data in the head
		return head.getData();
			
	}
	
	// get the head node of the list
	public LinkedListNode<T> getFirstNode()
	{
		//return the head node
		return head;
	}
	
	//get the data stored in tail node of list
	public T getLast()
	{
		//make head the current node
		current=head;
		
		// while the current node is not the tail
		while(current.getNext()!=null)
		{
			// let the current node be the next node
			current=current.getNext();
		}
		
		// return the data in the current node
		return current.getData();
		 
	}
	
	//get the tail node of the list
	public LinkedListNode<T> getLastNode()
	{
		// make head the current node
		current=head;
		
		// while the current node is not the tail
		while(current.getNext()!=null)
		{
			// let the current node be the next node
			current=current.getNext();
			
		}
		
		//return the current node
		return current;				
	}
	
	//insert a new node with data at the head of the list.
	public void insertFirst(T data)
	{
		// make a new node
		LinkedListNode<T> insertedFirst=new LinkedListNode<T>();
		
		// set the data of this new node to be data
		insertedFirst.setData(data);
		
		// make the next node of this new node be the head
		insertedFirst.setNext(head);
		
		// update the head by letting head be this new node
		head=insertedFirst;

		// add 1 to the size
		counter++;
	}
	
	//insert a new node with the data after currentNode
	public void insertAfter(LinkedListNode<T> currentNode, T data)
	{
		// if currentNode is not the tail
		if(currentNode!=getLastNode()){
			
			// make a new node
			LinkedListNode<T> insertedAfterCurrent=new LinkedListNode<T>();
			
			// set the data of this node to be data
			insertedAfterCurrent.setData(data);
			
			//set the next node of this new node to be the next node of the currentNode
			insertedAfterCurrent.setNext(currentNode.getNext());
			
			// set the next node of the currentNode to be this new node
			currentNode.setNext(insertedAfterCurrent);
			
			//add 1 to the size
			counter++;
			
		// if currentNode is the tail
		}else{
			
			// use insertLast instead
			insertLast(data);
		}		
	}
	
	//insert a new node with the data at the tail of the list.
	public void insertLast(T data)
	{
		//make a new node
		LinkedListNode<T> insertedLast=new LinkedListNode<T>();
		
		//set the data of the new node to be data
		insertedLast.setData(data);
		
		//if the list is empty
		if(isEmpty()){
			
			//make the head this new node
			head=insertedLast;
			
		//otherwise
		}else{
			
			// set the next node of the last node to be this new node
			getLastNode().setNext(insertedLast);
		}
			
		//add 1 to the size
		counter++;
	}
	
	//remove the first node.
	public void deleteFirst()
	{
		//only if the list is not empty
		if(!isEmpty()){
			
			//let the head be the node after the current head
			head=head.getNext();
		
			//minus 1 from the size
			counter--;
		}		
	}
	
	//remove the last node. 
	public void deleteLast()
	{
		//only if the list is not empty
		if(!isEmpty()){
			
			// if head is the only node in the list
			if(head.getNext()==null)
			{
				// make head null
				head=null;
			}
		
			// if head is not the only node, make current node to be head
			current=head;
		
			// while the current node is not the second last node
			while(current.getNext().getNext()!=null)
			{
			
				//make the current node the next node
				current=current.getNext();
			}
		
			//set the next node of the second last node to be null
			current.setNext(null);
		
			// minus 1 from the size
			counter--;
		}	
	}
	
	//remove node following currentNode. If no node exists (currentNode is the tail), do nothing.
	public void deleteNext(LinkedListNode<T> currentNode)
	{
		// if currentNode is not the tail
		if(currentNode.getNext()!=null)
		{
			// if the currentNode is the second last node
			if (currentNode.getNext().getNext()==null){
				
				// use deleteLast method
				deleteLast();
				
			// if the currentNode is not the second last node
			}else{
				
				// set the next node of currentNode to be the one after the next node of currentNode
				currentNode.setNext(currentNode.getNext().getNext());
			
				// minus 1 from the size
				counter--;
			}				
		}
		
	}
	
	//return the number of nodes in this list.
	public int size()
	{
		
		//return the number of nodes in this list.
		return counter;
	}
	
	//check if the list is empty. Return true if the list contains no items.
	public boolean isEmpty()
	{
		// list is empty by default
		boolean empty=true;

		// if the head is not null
		if(head!=null)
		{
			// then the list is not empty
			empty=false;
		}
		
		//return whether list is empty
		return empty;
	}
	
	//return a String representation of the list.
	public String toString()
	{
		// make an empty string
		String linkedList="";
		
		//make head the current node 
		current=head;
		
		// while the current node is not null
		while(current!=null)
		{
			// copy the data to the string
			linkedList+=current.getData();
			
			// if the current node is not the last node
			if (current.getNext()!=null)
			{
				// add an arrow behind it
				linkedList+="-->";
			}
			
			// move the current node to the next node
			current=current.getNext();
		}
		
		//return the string
		return linkedList;
	}
}
