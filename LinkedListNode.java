
public class LinkedListNode<T> {

	// data of type T.
	protected T data;
	
	// a pointer to the next node.
	protected LinkedListNode<T> next;
	
	//constructor
	public LinkedListNode()
	{
		
	}
	
	// set the data stored at this node.
	public void setData(T newData)
	{
		// let the current data be the new data passed in.
		data=newData;
	}
	
	// get the data stored at this node.
	public T getData()
	{
		// return the current data at this node.
		return data;
	}
	
	// set the next pointer to be the passed node.
	public void setNext( LinkedListNode<T> node )
	{
		// let the next pointer be the node passed in.
		next=node;
	}
	
	// get the pointer to the next node.
	public LinkedListNode<T> getNext()
	{
		// return the pointer to the next node.
		return next;
	}
	
	// return a string representation of this node
	public String toString(){
		
		//if there is no data
		if(data==null){
					
			//return null
			return null;
			
		// otherwise
		}else{
			
			//return the string representation of the data.
			return data.toString();
		}	
	}
}
