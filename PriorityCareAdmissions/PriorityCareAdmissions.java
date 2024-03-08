// FILE HEADER COMES HERE
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    PriorityCareAdmission
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
//https://www.cs.wcupa.edu/rkline/ds/heaps.html
//https://cs.stackexchange.com/questions/87154/why-does-the-formula-2n-1-find-the-child-node-in-a-binary-heap
//https://stackoverflow.com/questions/11509459/determining-the-lowest-child-node-descendant-with-greatest-index-in-an-array-b
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * Array-based min-heap implementation of a priority queue storing PatientRecords. Guarantees the
 * min-heap invariant, so that the PatientRecord at the root should be the smallest PatientRecord,
 * which corresponds to the element having the highest priority to be dequeued first, and children
 * always are greater than their parent. We rely on the PatientRecord.compareTo() method to compare
 * PatientRecords. 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class PriorityCareAdmissions {
  private PatientRecord[] queue; // array min-heap of PatientRecords representing this priority
                                 // queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty PriorityCareAdmissions queue with the given capacity
   * 
   * @param capacity Capacity of this PriorityCareAdmissions queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public PriorityCareAdmissions(int capacity) {
    if (capacity <= 0) {
        throw new IllegalArgumentException("Should be positive");
    }
    this.queue = new PatientRecord[capacity];
    this.size = 0;
  }


  /**
   * Checks whether this PriorityCareAdmissions queue is empty
   * 
   * @return {@code true} if this PriorityCareAdmissions queue is empty
   */
  public boolean isEmpty() {
    // TODO complete this implementation

    return size == 0; // default return statement added to resolve compile errors
  }

  /**
   * Returns the size of this PriorityCareAdmissions queue
   * 
   * @return the total number of PatientRecords stored in this PriorityCareAdmissions queue
   */
  public int size() {
    // TODO complete this implementation

    return this.size; // default return statement added to resolve compile errors
  }

  /**
   * Returns the capacity of this PriorityCareAdmissions queue
   * 
   * @return the capacity of this PriorityCareAdmissions queue
   */
  public int capacity() {
    // TODO complete this implementation

    return this.queue.length; // default return statement added to resolve compile errors
  }


  /**
   * Removes all the elements from this PriorityCareAdmissions queue
   */
  public void clear() {
    for(int i = 0; i < size; i++) {
      queue[i] = null;
    }
    this.size = 0;
    // TODO complete this implementation
  }

  /**
   * Returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e. the
   * PatientRecord having the the highest priority.
   * 
   * @return the PatientRecord at the root of this PriorityCareAdmissions queue
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord peek() {
    // TODO complete this implementation
    if(isEmpty()) {
      throw new NoSuchElementException("peek, empty");
    }
    return queue[0]; // default return statement added to resolve compile errors
  }


  /**
   * Adds the given PatientRecord to this PriorityCareAdmissions queue at the correct position based
   * on the min-heap ordering. This queue should maintain the min-heap invariant, so that the
   * PatientRecord at each index is less than or equal to than the PatientRecords in its child
   * nodes. PatientRecords should be compared using the PatientRecord.compareTo() method.
   * 
   * @param p PatientRecord to add to this PriorityCareAdmissions queue
   * @throws NullPointerException  if the given PatientRecord is null
   * @throws IllegalStateException with a the exact error message "Warning: Full Admissions Queue!"
   *                               if this PriorityCareAdmissions queue is full
   */
  public void addPatient(PatientRecord p) {

    // TODO complete this implementation
    if(p == null) {
      throw new NullPointerException();
    }
    if(size >= capacity()) {
      throw new IllegalStateException("Queue is full");
    }
    
    queue[size] = p;
    int currentIndex = size;
    int parentIndex = (currentIndex - 1) / 2;
    
    while(currentIndex > 0 && queue[currentIndex].compareTo(queue[parentIndex]) < 0) {
      PatientRecord  tempQueue = queue[currentIndex];
      queue[currentIndex] = queue[currentIndex];
      queue[parentIndex] = tempQueue;
      
      currentIndex = parentIndex;
      parentIndex = (currentIndex - 1) / 2; 
    }
    size++;
  }
  
  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of this
   * priority queue by percolating a leaf up the heap. If the element at the given index does not
   * violate the min-heap invariant (it is greater than its parent), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, swap the element with its parent and
   * continue percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateUp(int i) {
    // TODO complete this implementation. To get more practice on recursive thinking, implemented
    // this method recursively
    if(i < 0 || i >= size) {
      throw new IndexOutOfBoundsException("PercolateUp out of bound");
    }
    
    while(i > 0) {
      int parentIndex = (i - 1) / 2;
      
      if(queue[i].compareTo(queue[parentIndex]) >= 0) {
        break;
      }
      
      PatientRecord tempQueue = queue[i];
      queue[i] = queue[parentIndex];
      queue[parentIndex] = tempQueue;
      
      i = parentIndex;
    }
  }

  /**
   * Removes and returns the PatientRecord at the root of this PriorityCareAdmissions queue, i.e.
   * the PatientRecord having the highest priority (the minimum one).
   * 
   * @return the PatientRecord in this PriorityCareAdmissions queue at the root of this priority
   *         queue.
   * @throws NoSuchElementException with the exact error message "Warning: Empty Admissions Queue!"
   *                                if this PriorityCareAdmissions queue is empty
   */
  public PatientRecord removeBestRecord() {
    // TODO complete this implementation
    if(isEmpty()) {
      throw new NoSuchElementException("removeBest empty");
    }
    
    PatientRecord best = queue[0];
    queue[0] = queue[size - 1];
    size--;
    
    if(size > 0) {
      percolateDown(0);
    }
    
    return best; // default return statement added to resolve compile errors
  }


  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap of the priority queue
   * by percolating its root down the tree. If the element at the given index does not violate the
   * min-heap ordering property (it is smaller than its smallest child), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds (out of the range 0..size()-1
   *                                   inclusive)
   */
  protected void percolateDown(int i) {
    // TODO complete this implementation. To get more practice on recursive thinking, implemented
    // this method recursively
    if(i < 0 || i >= size) {
      throw new IndexOutOfBoundsException("percolateDown outofbound");
    }
    
    while(true) {
      int leftChildIndex = 2 * i + 1;
      int rightChildIndex = 2 * i + 2;
      int smallestChildIndex = i;
      
      if(leftChildIndex < size && queue[leftChildIndex].compareTo(queue[smallestChildIndex]) < 0){
        smallestChildIndex = leftChildIndex;
      }
      
      if(rightChildIndex < size && queue[rightChildIndex].compareTo(queue[smallestChildIndex]) < 0) {
        smallestChildIndex = rightChildIndex;
      }
      
      if(smallestChildIndex == i) {
        break;
      }
      
      PatientRecord tempQueue = queue[i];
      queue[i] = queue[smallestChildIndex];
      queue[smallestChildIndex] = tempQueue;
      
      i = smallestChildIndex;
    }
  }


  /**
   * Returns a deep copy of this PriorityCareAdmissions queue containing all of its elements in the
   * same order. This method does not return the deepest copy, meaning that you do not need to
   * duplicate PatientRecords. Only the instance of the heap (including the array and its size) will
   * be duplicated.
   * 
   * @return a deep copy of this PriorityCareAdmissions queue. The returned new priority care
   *         admissions queue has the same length and size as this queue.
   */
  public PriorityCareAdmissions deepCopy() {
    PriorityCareAdmissions deepCopy = new PriorityCareAdmissions(this.capacity());
    deepCopy.queue = Arrays.copyOf(this.queue, this.queue.length);
    deepCopy.size = this.size;
    return deepCopy;
  }

  /**
   * Returns a deep copy of the array-heap of this PriorityCareAdmissions queue <BR/>
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the ParientRecords in this queue
   */
  protected PatientRecord[] arrayHeapCopy() {
    return Arrays.copyOf(this.queue, this.queue.length);
    
  }
  /**
   * Returns a String representing this PriorityCareAdmissions queue, where each element
   * (PatientRecord) of the queue is listed on a separate line, in order from smallest to greatest.
   * 
   * @return a String representing this PriorityCareAdmissions queue, and an empty String "" if this
   *         queue is empty.
   */
  public String toString() {
    // TODO complete this implementation

    // [HINT] use a loop to traverse a deepCopy of this priority queue by removing the best patient
    // record until the queue is empty.
    if(isEmpty()) {
      return "";
    }
    
    PriorityCareAdmissions copiedQueue = this.deepCopy();
    
    String output = "";
    while(!copiedQueue.isEmpty()) {
      PatientRecord best = copiedQueue.removeBestRecord();
      output += best.toString() + "\n";
    }
    
    return output; // default return statement added to resolve compile errors

  }


}
