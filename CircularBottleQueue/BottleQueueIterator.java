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
//I imitate the Iterator method from P07 Twitter Feed
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models an iterator to iterate over a queue of Bottle objects. When the queue is not empty, 
 * Bottle objects are iterated over from the front to the back of the queue. 
 * No more Bottle objects are returned by this iterator when all the Bottle objects are traversed (returned). 
 * This Iterator iterates over any queue which implements the QueueADT<Bottle> interface. 
 * It uses the QueueADT.isEmpty() and QueueADT.dequeue() methods to iterate over a deep copy of the queue.
 * 
 * @author Zihan Xu
 *
 */
public class BottleQueueIterator extends Object implements Iterator<Bottle>{

  //a private instance field of type Bottle[], is the array of bottles.
  private QueueADT<Bottle> bottleQueue;
  
  /**
   * Initializes the instance fields. The bottle queue of this iterator MUST be initialized to a deep copy of the queue passed as input.
   * 
   * @param queue - The queue to iterate over, should implement the QueueADT interface.
   * @throws IllegalArgumentException - when queue is null
   * 
   */
  public BottleQueueIterator(QueueADT<Bottle> queue) throws IllegalArgumentException{
    if(queue == null) {
      throw new IllegalArgumentException();
    }
    
    this.bottleQueue = queue.copy();
  }
  
  /**
   * Returns true if there is the iteration is not yet exhausted, meaning at least one bottle is not iterated over
   * 
   * @return true if there is the iteration is not yet exhausted
   * 
   */
  @Override
  public boolean hasNext() {
    if(bottleQueue.isEmpty()) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Returns the next bottle in the iteration
   * 
   * @return Bottle The next bottle in the iteration
   * 
   */
  @Override
  public Bottle next() {
    if(!hasNext()) {
      throw new NoSuchElementException();
    }
    return bottleQueue.dequeue();
  }

}
