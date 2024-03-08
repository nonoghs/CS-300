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

import java.util.NoSuchElementException;

/**
 * This utility class implements tester methods to check the correctness of the implementation of
 * classes defined in p08 Bottle Factory program.
 *
 */
public class BottleFactoryTester extends Object{

  
  /**
   * Ensures the correctness of the constructor and methods defined in the Bottle class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleTester() {
    //test constructor, test null, empty, blank, and error message
    
      //test null color
      try {
        new Bottle(null);
        return false;
      } catch(IllegalArgumentException e) {
        if(!e.getMessage().equals("color cannot be empty")) {
          return false;
        }
      }
      
      //test color is empty, which means ""
      try {
        new Bottle("");
        return false;
      } catch(IllegalArgumentException e) {
        if(!e.getMessage().equals("color cannot be empty")) {
          return false;
        }
      }
      
      //test color is blank, which means " "
      try {
        new Bottle(" ");
        return false;
      } catch(IllegalArgumentException e) {
        if(!e.getMessage().equals("color cannot be empty")) {
          return false;
        }
      }        
    
	// test equals method 
      Bottle.resetBottleCounter();
      Bottle bottleA = new Bottle("Red");
      Bottle bottleB = new Bottle("Blue");
      Bottle bottleC = new Bottle("BLue");
      
      if(bottleA.equals(bottleB)) {
        return false;
      }
      
      if(bottleB.equals(bottleC)) {
        return false;
      }
	// test toString method
	  Bottle bottleD = new Bottle("Yellow");
	  Bottle bottleE = new Bottle("Pink");
	  
	  String expectedYellow = "SN4Yellow:Empty:Open";
	  if(!bottleD.toString().equals(expectedYellow)) {
	    return false;
	  }
	  
	  String expectedPink = "SN5Pink:Filled:Capped";
	  bottleE.fillBottle();
	  bottleE.sealBottle();
	  if(!bottleE.toString().equals(expectedPink)) {
	    return false;
	  }
	  
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the LinkedBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean linkedBottleQueueTester() {
	//reset the counter
    Bottle.resetBottleCounter();
    try {
      // test constructor - verify fields and exception behavior (when capacity is invalid)
      try {
        LinkedBottleQueue invalidQueue = new LinkedBottleQueue(-100);
        return false;
      } catch (IllegalArgumentException e) {
      }
      
      LinkedBottleQueue queue = new LinkedBottleQueue(4);
      
      // 1) all methods on an empty queue 
      if(!queue.isEmpty() || queue.isFull() || queue.size() != 0) {
        return false;
      }
      
      try {
        queue.dequeue();
        return false;
      } catch (NoSuchElementException e) {
        //System.out.println("3rd try");
      }
      
      try {
        queue.peek();
        return false;
      } catch(NoSuchElementException e) {
        
      }
      
      
      //2) all methods on a full queue
      Bottle bottleA = new Bottle("Pink");
      Bottle bottleB = new Bottle("Pink");
      Bottle bottleC = new Bottle("Pink");
      Bottle bottleD = new Bottle("Pink");
      Bottle bottleE = new Bottle("Pink");
      
      //Add bottles into queue until it's full
      queue.enqueue(bottleA);
      queue.enqueue(bottleB);
      queue.enqueue(bottleC);
      queue.enqueue(bottleD);
      
      //System.out.println(queue.isEmpty());
      //System.out.println(queue.size());
      
      //check the queue status
      if(queue.isEmpty() || !queue.isFull() || queue.size() != 4) {
        return false;
      }
      
      //try to add a new bottle into queue
      try {
        queue.enqueue(bottleE);
        return false;
      } catch (IllegalStateException e) {
        
      }
           
      //all methods on a partially filled queue 
      queue.dequeue();
      
      //check queue status
      if(queue.isEmpty() || queue.isFull() || queue.size() != 3) {
        return false;
      }
      //check whether dequeue follows FIFO(first in first out)
      if(queue.peek() != bottleB) {
        return false;
      }
      
      
      //4) Verify queue contents (using peek and size) after a sequence of enqueue-dequeue and dequeue-enqueue 
      queue.enqueue(bottleA);
      queue.dequeue();
      queue.dequeue();
      
      if(queue.peek() != bottleD || queue.size() != 2) {
        return false;
      }
      
      
      //5) Enqueue until queue is full and dequeue until queue is empty
      queue.enqueue(bottleB);
      queue.enqueue(bottleC);
      
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();
      queue.dequeue();
      
      if(!queue.isEmpty() || queue.isFull() || queue.size() != 0) {
        return false;
      }
      
      
      //test copy method
      Bottle.resetBottleCounter();
      LinkedBottleQueue copiedQueue = new LinkedBottleQueue(2);
      Bottle b1 = new Bottle("Pink");
      Bottle b2 = new Bottle("Pink");
      b1.fillBottle();
      b2.fillBottle();
      b1.sealBottle();
      b2.sealBottle();
      
      copiedQueue.enqueue(b1);
      copiedQueue.enqueue(b2);
      LinkedBottleQueue newCopy = (LinkedBottleQueue) copiedQueue.copy();
      //check the elements and order
      if(!newCopy.toString().equals(copiedQueue.toString()) || newCopy.peek() != copiedQueue.peek()) {
        return false;
      }
      
      
      //test toString method
      String expected = "SN1Pink:Filled:Capped\nSN2Pink:Filled:Capped";
      if(!newCopy.toString().equals(expected)) {
        return false;
      }     
      
    }catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the CircularBottleQueue class
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean circularBottleQueueTester() {
      //reset the counter
      Bottle.resetBottleCounter();
      try {
        // test constructor - verify fields and exception behavior
        try {
          CircularBottleQueue invalidQueue = new CircularBottleQueue(-100);
          return false;
        } catch (IllegalArgumentException e) {

        }
        
        CircularBottleQueue queue = new CircularBottleQueue(4);
        
        Bottle bottleA = new Bottle("Pink");
        Bottle bottleB = new Bottle("Purple");
        Bottle bottleC = new Bottle("Gold");
        Bottle bottleD = new Bottle("Silver");
        Bottle bottleE = new Bottle("Black");
        
        //1) all 3 methods on an empty queue 
        if(!queue.isEmpty() || queue.size() != 0) {
          return false;
        }
        
        try {
          queue.dequeue();
          return false;
        } catch(NoSuchElementException e) {
          
        }
        
        try {
          queue.peek();
          return false;
        } catch(NoSuchElementException e) {
          
        }
        
        
        //all 3 methods on a full queue
        queue.enqueue(bottleA);
        queue.enqueue(bottleB);
        queue.enqueue(bottleC);
        queue.enqueue(bottleD);
        
        //System.out.println(queue.isFull());
        //System.out.println(queue.size());
        if(!queue.isFull() || queue.size() != 4) {
          return false;
        }
        
        try {
          queue.enqueue(bottleE);
          return false;
        } catch (IllegalStateException e) {

        }
        
        
        //all 3 methods on a partially filled queue
        queue.dequeue();
        //System.out.println(queue.peek());
        //System.out.println(queue.size());
        if(!queue.peek().equals(bottleB) || queue.size() != 3) {
          return false;
        }
        
        
        //Verify queue contents and size after a sequence of enqueue-dequeue and dequeue-enqueue 
        queue.enqueue(bottleA);
        queue.dequeue();
        if(!queue.peek().equals(bottleC) || queue.size() != 3) {
          return false;
        }
        
        queue.enqueue(bottleB);
        queue.dequeue();
        if(!queue.peek().equals(bottleD) || queue.size() != 3) {
          return false;
        }
        
        
        
        //Enqueue until queue is full and dequeue until queue is empty 
        queue.enqueue(bottleC);
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        
        if(!queue.isEmpty() || queue.size() != 0) {
          return false;
        }
        
        
        // test copy method
        queue.enqueue(bottleA);
        queue.enqueue(bottleB);
        queue.enqueue(bottleC);
        queue.enqueue(bottleD);
        
        CircularBottleQueue copiedQueue = (CircularBottleQueue) queue.copy();
        if(!queue.toString().equals(copiedQueue.toString())) {
          return false;
        }
        
        
        //test toString method
        String expected = "SN1Pink:Empty:Open\nSN2Purple:Empty:Open\nSN3Gold:Empty:Open\nSN4Silver:Empty:Open\nSN5Black:Empty:Open";
        if(!queue.toString().equals(expected)){
          return false;
        }
        
      } catch(Exception e) {
        return false;
      }
    return true;
  }

  /**
   * Ensures the correctness of the constructor and methods defined in the BottleQueueIterator class
   * Actually I don't really get the point:with at least n bottles and use the bottleQueueIterator to traverse the queue
   * I just create a queue with capacity 3 and try to test it.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean bottleQueueIteratorTester() {
    //reset the counter
    Bottle.resetBottleCounter();
    try {
      // Test 01: LinkedBottleQueue
      LinkedBottleQueue linkedBottleQueue = new LinkedBottleQueue(3);
      Bottle b1 = new Bottle("Blue");
      Bottle b2 = new Bottle("Red");
      Bottle b3 = new Bottle("Green");
      linkedBottleQueue.enqueue(b1);
      linkedBottleQueue.enqueue(b2);
      linkedBottleQueue.enqueue(b3);

      BottleQueueIterator linkedQueueIterator = new BottleQueueIterator(linkedBottleQueue);
      int linkedQueueCounter = 0;
      while (linkedQueueIterator.hasNext()) {
          Bottle currentBottle = linkedQueueIterator.next();
          linkedQueueCounter++;
      }
      if (linkedQueueCounter != linkedBottleQueue.size()) {
          return false;
      }

      // Test 02: CircularBottleQueue
      CircularBottleQueue circularBottleQueue = new CircularBottleQueue(3);
      circularBottleQueue.enqueue(b1);
      circularBottleQueue.enqueue(b2);
      circularBottleQueue.enqueue(b3);

      BottleQueueIterator circularQueueIterator = new BottleQueueIterator(circularBottleQueue);
      int circularQueueCounter = 0;
      while (circularQueueIterator.hasNext()) {
          Bottle currentBottle = circularQueueIterator.next();
          circularQueueCounter++;
      }
      if (circularQueueCounter != circularBottleQueue.size()) {
          return false;
      }

    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  /**
   * Runs all the tester methods defined in this class.
   * 
   * @return true if no bugs are detected.
   */
  public static boolean runAllTests() {
    System.out.println("bottleTester: " + (bottleTester() ? "Pass" : "Failed!"));
    System.out.println("bottleQueueIterator: " + (bottleQueueIteratorTester() ? "Pass" : "Failed!"));
    System.out
        .println("linkedBottleQueueTester: " + (linkedBottleQueueTester() ? "Pass" : "Failed!"));
    System.out.println(
        "circularBottleQueueTester: " + (circularBottleQueueTester() ? "Pass" : "Failed!"));

    return bottleTester() && bottleQueueIteratorTester() && linkedBottleQueueTester()
        && circularBottleQueueTester();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
  }

}
