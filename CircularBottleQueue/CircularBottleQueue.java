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
//I used to use the while loop to write this copy method, however, it doesn't work. I put into gradescope and the gradescope said it cannot be accessed.
//So I use chatGPT to help me finish this method
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models an circular-indexing array queue which stores elements of type Bottle.
 * 
 * @author Zihan Xu
 *
 */
public class CircularBottleQueue extends Object implements QueueADT<Bottle>, Iterable<Bottle>{

  //a private instance field of type Bottle[], is the array of bottles
  private Bottle[] bottles;
  //private instance fields of type int, indicting the earliest added bottle and recently added bottle respectively
  private int front;
  private int back;
  //a private instance field of type int, indicating the number of bottles in the queue.
  private int size;
  //a private instance field of type int, defining the max number of bottles the queue can hold
  private int capacity;
  
  /**
   * Constructs a CircularBottleQueue object, initializing its data fields as follows:the bottles oversize array to an empty array of Bottle objects whose length is the input capacity,
   * its size to zero, andboth its front and back to -1
   * 
   * @param capacity - defining the number of bottles the queue can hold
   * @throws IllegalArgumentException - when capacity is not positive
   * 
   */
  public CircularBottleQueue(int capacity) throws IllegalArgumentException{
    if(capacity <= 0) {
      throw new IllegalArgumentException();
    }
    
    this.capacity = capacity;
    this.front = -1;
    this.back = -1;
    this.size = 0;
    this.bottles = new Bottle[capacity];
    
  }
  
  /**
   * Returns an iterator to traverse the queue
   * 
   * @return An Iterator instance to traverse a deep copy of this CircularBottleQueue.
   * 
   */
  @Override
  public Iterator<Bottle> iterator() {
    return new BottleQueueIterator(this);
  }

  /**
   * Checks and returns true if the queue is empty
   * 
   * @return returns true if the queue is empty
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
   * @return returns true if the queue
   * 
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
   * @return size of the queue
   * 
   */
  @Override
  public int size() {  
    return size;
  }

  /**
   * Add a bottle to the end of the queue
   * 
   * @param bottle - Bottle to add to the queue
   * @throws IllegalStateException - when queue is full
   * @throws NullPointerException - when bottle to add is null
   * 
   */
  @Override
  public void enqueue(Bottle bottle) {
    //check whether queue is full
    if(isFull()) {
      throw new IllegalArgumentException();
    }
    //check whether bottle to add is null
    if(bottle == null) {
      throw new NullPointerException();
    }
    //get the new position and store into new position
    back = (back + 1) % capacity;
    bottles[back] = bottle;
    //if the front is -1, it means the queue was empty, set front to back position
    if(front == -1) {
      front = back;
    }
    size++;
  }

  /**
   * Removes and returns the first bottle in the queue.
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
    //get the bottle at the front of the queue
    Bottle bottle = bottles[front];
    bottles[front] = null;
    size--;
    
    //check if the queue is now empty
    if (isEmpty()) {
        //if the queue is empty, reset both the front and back indices to -1
        front = -1;
        back = -1;
    } else {
        //if the queue is not empty, update the front index using the modulo operator
        front = (front + 1) % bottles.length;
    }
    
    return bottle;
  }
  
  /**
   * Returns the first bottle in the queue without removing it
   * 
   * @return Top/First bottle in the queue
   * @throws NoSuchElementException - when queue is empty
   * 
   */
  @Override
  public Bottle peek() {
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    return bottles[front];
  }

  /**
   * Returns a deep copy of this Queue
   * Actually I don't really understand how this method works, I finished this method with the help from chatGPT
   * 
   * @return a deep copy of the queue
   * 
   */
  @Override
  public QueueADT<Bottle> copy() {
    CircularBottleQueue copyQueue = new CircularBottleQueue(capacity);
    for(int i = 0; i < size; i++) {
     int index = (front + i) % capacity;
     copyQueue.enqueue(bottles[index]);
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
      //check if the queue is empty
      if (isEmpty()) {
          return "";
      }

      //initialize an empty string to store the result and 
      String result = "";

      //iterate through the queue from the front to the back and update the index 
      int index = front;
      while (index != back) {
          result += bottles[index].toString() + "\n";
          index = (index + 1) % bottles.length;
      }
      
      result += bottles[back].toString();

      return result;
  }


}
