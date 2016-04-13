package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = null;
		tail = null;
		size =0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> newnode = new LLNode<E>(element);
		LLNode<E> current = head;
		
		if(size==0){
			head = newnode;
			tail = newnode;
			size++;
		}

		else{
		while(current.next!= null){
			current = current.next;
		}
		current.next = newnode;
		newnode.prev = current;
		tail = newnode;
		size++;
		}
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.

		E data =null;
		LLNode<E> current = null;
		
		if((size ==0)||(index<0 || index>(size-1))) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		else{
		/*if(index<0 || index>(size-1)) {
			throw new ArrayIndexOutOfBoundsException();
		}*/
		
		//else {
			if(index<= (size/2)){
				current = head;
				for(int i=0; i<index; i++){
					current = current.next;
				}
			}
			
			else{
				current = tail;
				for(int i=size; i>index+1; i--){
					current = current.prev;
				}
			}
		//}
		data = current.data;
		}
		return data;
	}
	

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		LLNode<E> newnode = new LLNode<E>(element);
		LLNode<E> current = head;
		LLNode<E> previousnode = null;
		
		if(element == null) {
			throw new NullPointerException();
		}
		
		else if((size==0 && index!=0) || (size!=0 && (index<0 || index>(size-1)))){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		else{
		// If size is 0 and index is 0	
			if(size==0){
				head = newnode;
				tail = newnode;
			}
		// if size > 0 
			else{
				// if insertion is at start
				if(index==0){
					current.prev = newnode;
					newnode.next = current;
				}
			// if insertion is anywhere inbtw or end
				else {	
					current = head;
					for(int i=0; i<index; i++){
						previousnode = current;
						current = current.next;
					}
					current.prev = newnode;
					newnode.next = current;
					
					previousnode.next = newnode;
					newnode.prev = previousnode;
				}
			
			}
			size++;
		}
	}

	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		LLNode<E> current = head;
		LLNode<E> previousnode = null;
		LLNode<E> nextnode = null;
		
		if((size==0) || (size!=0 && (index<0 || index>(size-1)))){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		else{
			
			 if((index == 0)&& size ==1){
				current.next =null;
				
			}
			
			// remove the first element
			 else if(index == 0){
				nextnode = current.next;
				
				nextnode.prev = null;
				current.next =null;
			}
			
			// remove the last element
			else if(index == size-1){
				current = head;
				for(int i=0; i<index; i++){
					previousnode = current;
					current = current.next;
				}
				previousnode.next =null;
				current.prev =null;
			}
			
			// remove the element inbtw
			else {	
				current = head;
				for(int i=0; i<index; i++){
					previousnode = current;
					current = current.next;
					nextnode = current.next;
				}
				current.prev = null;
				current.next = null;
			
				previousnode.next = nextnode;
				nextnode.prev = previousnode;
			}
			size--;
		}
		return null;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		
		E data =null;
		LLNode<E> current = null;
		
		if((size ==0)||(index<0 || index>(size-1))) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		else{
		
			if(element == null) {
			throw new NullPointerException();
		}
		else {
			if(index<= (size/2)){
				current = head;
				for(int i=0; i<index; i++){
					current = current.next;
				}
			}
			
			else{
				current = tail;
				for(int i=size; i>index+1; i--){
					current = current.prev;
				}
			}
		}
		data = current.data;
		current.data =element;
		}
		
		
		return data	;
	}   
	
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedList<String> shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		System.out.println(shortList.size());
	}*/
}

class LLNode<E> 
{
	
	// Properties
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	// Constructors 
	public LLNode(E data) 
	{
		this.data = data;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode (E data, LLNode<E> previ, LLNode<E> nexto) {
		this.data = data;
		this.prev = previ;
		this.next = nexto;
	}

}
