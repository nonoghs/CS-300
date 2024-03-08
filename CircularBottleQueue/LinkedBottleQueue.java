import java.util.Iterator;
import java.util.NoSuchElementException;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    Bottle Queue
//Course:   CS 300 Spring 2023
//
//Author:   Zihan Xu
//Email:    zxu536@wisc.edu
//Lecturer: Mouna Kacem 
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:  no
//Partner Email:  no
//Partner Lecturer's Name:
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__Write-up states that pair programming is allowed for this assignment.
//__We have both read and understand the course Pair Programming Policy.
//__We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//https://stackoverflow.com/questions/16433397/difference-between-enqueue-and-dequeue; this website helps me get a better understand of enqueue and dequeue
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class implements a linked queue storing objects of type Bottle. Bottle are added and removed with respect to the First In First Out (FIFO) scheduling policy.
 * I use the IDE auto-filled overrides methods so the order could be different from javadoc
 * 
 * @author Zihan Xu
 *
 */
public class LinkedBottleQueue extends Object implements QueueADT<Bottle>, Iterable<Bottle>{
  
  //head node
  private LinkedNode front;
  //tail node
  private LinkedNode back;
  //a private instance field of type int, indicating the number of bottles in the queue
  private int size;
  //a private instance field of type int, defining the max number of bottles the queue can hold
  private int capacity;
  
  /**
   * Initializes the fields of this queue including its capacity. A newly created queue must be empty, meaning that both its front and back are null and its size is zero.
   * 
   * @param capacity - Positive integer defining the max number of bottles the queue can hold
   * @throws IllegalArgumentException - when the capacity is not positive (meaning less or equal to zero
   * 
   */
  public LinkedBottleQueue(int capacity) {
    //check capacity
    if(capacity <= 0) {
      throw new IllegalArgumentException();
    }
    //initialize the fields
    this.front = null;
    this.back = null;
    this.size = 0;
    this.capacity = capacity;
  }
  
  /**
   * Returns an iterator for traversing the queue's items
   * 
   * @return - iterator to traverse the LinkedListQueue
   * 
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this);
  }
  
  /**
   * Checks and returns true if the queue is empty
   * 
   * @return - true if queue is empty
   * 
   */
  @Override
  public boolean isEmpty() {
    if(size == 0) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Checks and returns true if the queue is full
   * 
   * @return - true if the queue is full
   */
  @Override
  public boolean isFull() {
    if(size == capacity) {
      return true;
    }else {
      return false;
    }
  }
  
  /**
   * Returns the number of bottles in the queue
   * 
   * @return - size of bottles
   * 
   */
  @Override
  public int size() {
    return size;
  }
  
  /**
   * Add a bottle to the end of the queue
   * 
   * @param bottle - bottle to add to the queue
   * @throws IllegalStateException - when queue is full
   * @throws NullPointerException - when bottle to add is null
   * 
   */
  @Override
  public void enqueue(Bottle bottle) {
    //Check the queue and input argument
    if(isFull()) {
      throw new IllegalStateException();
    }
    if(bottle == null) {
      throw new NullPointerException();
    }
    //Constuct a new node
    LinkedNode<Bottle> newNode = new LinkedNode<>(bottle);
    //check if is empty, if queue is empty, headnode = tailnode = newNode
    if(isEmpty()) {
      front = newNode;
      back = newNode;
    } else {
      //if queue is not empty, set the newNode to the end of the queue and update the reference
      back.setNext(newNode);
      back = newNode;
    }
    size++;
  }
  
  /**
   * Removes and returns the first bottle in the queue
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException - when queue is empty
   * 
   */
  @Override
  public Bottle dequeue() {
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    //get top bottle data field
    Bottle topBottle = (Bottle) front.getData();
    //If there is only one node
    if(front == back) {
      front = null;
      back = null;
    } else {
      //more than one nodes, set the current pointer to the next from head
      front = front.getNext();
    }
    size--;
    return topBottle;
  }
  
  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException - - When queue is empty
   * 
   */
  @Override
  public Bottle peek() {
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    Bottle firstBottle = (Bottle) front.getData();
    return firstBottle;
  }
  
  /**
   * Returns a deep copy of this queue.
   * 
   * @return deep copy of this queue
   * 
   */
  @Override
  public QueueADT<Bottle> copy() {
    LinkedBottleQueue copyQueue = new LinkedBottleQueue(capacity);
    LinkedNode current = front;
    // Iterate through the original queue until the end
    while(current != null) {
      copyQueue.enqueue((Bottle) current.getData());
      current = current.getNext();
    }
    return copyQueue;
  }
  
  /**
   * Returns a string representation of the queue from the front to its back with the string representation of each Bottle in a separate line. 
   * 
   * @return String in expected format, empty string when queue is empty
   * 
   */
  @Override
  public String toString() {
    
    String queueString = "";
    // check the status of queue we just create
    if(isEmpty()) {
      return queueString;
    }
    
    // create an iterator for the queue
    Iterator<Bottle> iterator = iterator();
    
    // iterate through the queue using the iterator
    while(iterator.hasNext()) {
      Bottle currentBottle = iterator.next();
      queueString += currentBottle;
      
      if(iterator.hasNext()) {
        queueString += "\n";
      }
    }
    
    return queueString;
  }
  

}
