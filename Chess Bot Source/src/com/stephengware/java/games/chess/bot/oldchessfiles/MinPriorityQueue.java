package com.stephengware.java.games.chess.bot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * A priority queue is a collection of things which, after being put in (or
 * "pushed") can be taken out ("popped) in the order defined by the numeric
 * key associated with each thing.  This is a min priority queue, meaning that
 * the thing with the smallest key will always be removed next.
 * 
 * @author Stephen G. Ware
 * @param <T> the type of object to be stored in the queue
 */
public class MinPriorityQueue<T> implements Iterable<T> {
	
	/**
	 * An object for associating an object with its key.
	 * 
	 * @author Stephen G. Ware
	 */
	private final class Node {
		
		/** A unique ID assigned to each object, where a higher ID indicates
		 *  that the object has been in the queue longer */
		public final int id = nextID++;
		
		/** The object */
		public final T element;
		
		/** The object's key */
		public final double key;
		
		/**
		 * Associates an object with its key.
		 * 
		 * @param element the object
		 * @param key its key
		 */
		private Node(T element, double key) {
			this.element = element;
			this.key = key;
		}
	}
	
	/** The underlying priority queue in which object / key pairs are stored */
	private final java.util.PriorityQueue<Node> queue;
	
	/** A set of all the things currently in the queue for fast lookup */
	private final HashSet<T> members = new HashSet<>();
	
	/** The ID number to assign to the next object */
	private int nextID = 0;
	
	/**
	 * Creates a new min priority queue.
	 */
	public MinPriorityQueue() {
		this.queue = new java.util.PriorityQueue<>(new Comparator<Node>(){
			@Override
			public int compare(Node n1, Node n2) {
				double difference = n1.key - n2.key;
				if(difference == 0)
					difference = n1.id - n2.id;
				if(difference < 0)
					return -1;
				else if(difference > 0)
					return 1;
				else
					return 0;
			}
		});
	}

	@Override
	public Iterator<T> iterator() {
		ArrayList<T> list = new ArrayList<>(size());
		forEach(element -> list.add(element));
		return list.iterator();
	}
	
	@Override
	public void forEach(Consumer<? super T> consumer) {
		queue.forEach(node -> consumer.accept(node.element));
	}
	
	/**
	 * Returns the number of elements current stored in the queue.
	 * 
	 * @return the number of elements
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * Tests whether or not a given object is stored in the queue.
	 * 
	 * @param object the object to test
	 * @return true if the object is in the queue, false otherwise
	 */
	public boolean contains(Object object) {
		return members.contains(object);
	}
	
	/**
	 * Returns the next object to be removed from the queue.
	 * 
	 * @return the next (minimum) object, or null if the queue is empty
	 */
	public T peek() {
		return queue.peek().element;
	}
	
	/**
	 * Adds an object to the queue and associates the object with a numeric
	 * key that will be used to determine in what order the object comes out.
	 * 
	 * @param element the object to add
	 * @param key the numeric key associated with the object
	 */
	public void push(T element, double key) {
		if(members.contains(element))
			return;
		members.add(element);
		queue.add(new Node(element, key));
	}
	
	/**
	 * Removes the object with the lowest key from the queue and returns it.
	 * 
	 * @return the object with the lowest key
	 */
	public T pop() {
		members.remove(peek());
		return queue.poll().element;
	}
}
