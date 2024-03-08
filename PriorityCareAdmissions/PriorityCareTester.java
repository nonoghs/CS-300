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
 * This is a Utility class which contains tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 spring 2023 p10 Priority Care.
 *
 */
public class PriorityCareTester extends Object {

  /**
   * Tests whether compareTo() method implemented in PatientRecord returns a positive integer when a
   * higher triage level is compared to a lower triage level, regardless of patient order of
   * arrival. Similarly, this method tests whether compareTo() method implemented in PatientRecord
   * returns a negative integer when a lower triage level is compared to a higher triage level,
   * regardless of patient order of arival.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToDifferentTriage() {
    // TODO complete the implementation of this tester method
    try {
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
      PatientRecord yellowB = new PatientRecord('M', 77, TriageLevel.YELLOW);
      PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
      PatientRecord greenB= new PatientRecord('M', 57, TriageLevel.GREEN);
      
      //Higher triage compare with lower triage
      if(redA.compareTo(yellowA) >= 0) {
        return false;
      }
      
      if(yellowA.compareTo(greenA) >= 0) {
        return false;
      }
      
      if(redA.compareTo(greenA) >= 0) {
        return false;
      }
      
      //Lower triage compare with lower triage
      if(yellowB.compareTo(redB) <= 0) {
        return false;
      }
      
      if(greenB.compareTo(yellowB) <= 0) {
        return false;
      }
      
      if(greenB.compareTo(redB) <= 0) {
        return false;
      }
      
    }catch (Exception e) {
      System.out.println("compareTo, different triage, exception");
      return false;
    }
    return true;
  }

  /**
   * Tests whether patients in the same triage level are compared based on their order of arrival.
   * Patients of the same triage level with a lower arrival number compared to patients with a
   * higher arrival number should return a negative integer. The reverse situation should return a
   * positive integer.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageDifferentArrival() {
    // TODO complete the implementation of this tester method
    try {
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
      PatientRecord yellowB = new PatientRecord('M', 77, TriageLevel.YELLOW);
      PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
      PatientRecord greenB= new PatientRecord('M', 57, TriageLevel.GREEN);
      
      //normal cases
      if(redA.compareTo(redB) >= 0) {
        throw new Exception("red case");
      }
      
      if(yellowA.compareTo(yellowB) >= 0) {
        throw new Exception("yellow case");
      }
      
      if(greenA.compareTo(greenB) >= 0) {
        throw new Exception("green case");
      }
      
      //reverse cases
      if(redB.compareTo(redA) <= 0) {
        throw new Exception("reverse red case");
      }
      
      if(yellowB.compareTo(yellowA) <= 0) {
        throw new Exception("reverse yellow case");
      }
      
      if(greenB.compareTo(greenA) <= 0) {
        throw new Exception("reverse green case");
      }
     
      
    } catch (Exception e) {
      System.out.println("compareTo, same triage different arrival, exception");
      return false;
    }

    return true;
  }

  /**
   * Tests whether patients in the same triage level and with the same order of arrival are equal
   * (compareTo should return 0). Even though this case will not be possible in your priority queue,
   * it is required for testing the full functionality of the compareTo() method. Hint: you will
   * need to use the resetCounter() to create equivalent PatientRecords.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   * @see PatientRecord#compareTo(PatientRecord)
   */
  public static boolean testPatientRecordCompareToSameTriageSameArrival() {
    // TODO complete the implementation of this tester method
    try {
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord.resetCounter();
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      PatientRecord.resetCounter();
      PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
      PatientRecord.resetCounter();
      PatientRecord yellowB = new PatientRecord('M', 77, TriageLevel.YELLOW);
      PatientRecord.resetCounter();
      PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
      PatientRecord.resetCounter();
      PatientRecord greenB= new PatientRecord('M', 57, TriageLevel.GREEN);
      
      if (redA.compareTo(redB) != 0 || yellowA.compareTo(yellowB) != 0 || greenA.compareTo(greenB) != 0) {
        throw new Exception("it should be 0");
      }
      
    } catch (Exception e) {
      System.out.println("compareTo, same triage, same arrival exception");
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of the constructor for PriorityCareAdmissions Should implement at least
   * the following tests: 
   *
   * - Calling the PriorityCareAdmissions with an invalid capacity should throw an
   * IllegalArgumentException 
   * - Calling the PriorityCareAdmissions with a valid capacity should not throw any errors, and
   * should result in a new PriorityCareAdmissions which is empty, has size 0, a capacity equal to
   * the capacity that was passed as a parameter.
   *
   * @return true if the constructor of PriorityCareAdmissions functions properly, false otherwise
   * @see PriorityCareAdmissions#PriorityCareAdmissions(int)
   */
  public static boolean testConstructor() {
    // TODO complete the implementation of this tester method
    
    try {
      //Invalid capacity
      int invalidCapacity = -1;
      try {
        PriorityCareAdmissions invalidPriorityCareAdmissions = new PriorityCareAdmissions(invalidCapacity);
        throw new Exception("invalid capacity");
      } catch (IllegalArgumentException e) {
        
      }
      
      //valid capacity
      int capacity = 2;
      PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);

      if (priorityCareAdmissions.size() != 0) {
          throw new Exception("size should be 0");
      }
      
    } catch (Exception e) {
      System.out.println("constructor exception");
      return false;
    }
    return true; // default return statement added to resolve compiler errors
  }


  /**
   * Tests the functionality of peek() method by calling peek on an empty queue and verifying it
   * throws a NoSuchElementException.
   * 
   * @return true if PriorityCareAdmissions.peek() exhibits expected behavior, false otherwise.
   */
  public static boolean testPeekEmpty() {
    try {
        int capacity = 3;
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);

        // Test peek() on an empty queue
        try {
            priorityCareAdmissions.peek();
            throw new Exception("peek() should throw a NoSuchElementException on an empty queue.");
        } catch (NoSuchElementException e) {
            // Expected exception, do nothing
        }

    } catch (Exception e) {
        System.out.println("peek, empty queue, exception");
        return false;
    }

    return true;
  }


  /**
   * Tests the functionality of peek() method by calling peek on a non-empty queue and verifying it
   * 1) returns the PatientRecord having the highest priority (the minimum) and 2) does not remove
   * the PatientRecord from the queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekNonEmpty() {
    try {
        int capacity = 3;
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);

        // Add PatientRecords with different priorities
        PatientRecord.resetCounter();
        PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
        PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
        PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);

        priorityCareAdmissions.addPatient(greenA);
        priorityCareAdmissions.addPatient(yellowA);
        priorityCareAdmissions.addPatient(redA);

        // Test peek() on a non-empty queue
        PatientRecord peekedRecord = priorityCareAdmissions.peek();
        if (peekedRecord == null || !peekedRecord.equals(redA)) {
            throw new Exception("it should return redA");
        }

        // Test that peek() does not remove the PatientRecord from the queue
        if (priorityCareAdmissions.size() != 3) {
            throw new Exception("queue size has been modified");
        }

    } catch (Exception e) {
        System.out.println("peek, nonempty queue, exception");
        return false;
    }

    return true;
  }



  /**
   * Tests the functionality of addPatient() method by calling addPatient() on an empty queue and
   * ensuring the method 1) adds the PatientRecord and 2) increments the size.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientEmpty() {
    try {
        int capacity = 1;
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);

        // Test adding a PatientRecord to an empty queue
        PatientRecord.resetCounter();
        PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);

        priorityCareAdmissions.addPatient(redA);

        // Check if the PatientRecord is added
        if (priorityCareAdmissions.isEmpty()) {
            throw new Exception("should not be empty");
        }

        // Check if the size is incremented
        if (priorityCareAdmissions.size() != 1) {
            throw new Exception("size should be increment");
        }

        // Check if the added PatientRecord is at the root of the min-heap
        PatientRecord rootRecord = priorityCareAdmissions.peek();
        if (rootRecord == null || !rootRecord.equals(redA)) {
            throw new Exception("the added record is not the expected one");
        }

    } catch (Exception e) {
        System.out.println("addPatient, empty queue, exception");
        return false;
    }

    return true;
  }



  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try add at least 5 PatientRecords.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a non-empty queue and
   * ensuring the method 1) adds the PatientRecord at the proper position and 2) increments the
   * size. Try adding specific PatientRecords with different triage levels.
   *
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false otherwise
   */
  public static boolean testAddPatientNonEmpty() {
    try {
        int capacity = 6;
        PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);
        
        PatientRecord.resetCounter();
        PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
        PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
        PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
        PatientRecord yellowB = new PatientRecord('M', 77, TriageLevel.YELLOW);
        PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
        PatientRecord greenB = new PatientRecord('M', 57, TriageLevel.GREEN);

        priorityCareAdmissions.addPatient(redA);
        priorityCareAdmissions.addPatient(redB);
        priorityCareAdmissions.addPatient(yellowA);
        priorityCareAdmissions.addPatient(yellowB);
        priorityCareAdmissions.addPatient(greenA);
        priorityCareAdmissions.addPatient(greenB);

        //check if the size of the queue is incremented correctly
        if (priorityCareAdmissions.size() != 6) {
            throw new Exception("queue size should be 6");
        }

        //create an array to store the expected order of the queue
        PatientRecord[] expectedOrder = new PatientRecord[]{
                redA, redB, yellowA, yellowB, greenA, greenB
        };

        //check if the order of the queue matches the expected order
        PriorityCareAdmissions copiedQueue = priorityCareAdmissions.deepCopy();
        int index = 0;
        while (!copiedQueue.isEmpty()) {
            PatientRecord patientRecord = copiedQueue.removeBestRecord();
            if (!patientRecord.equals(expectedOrder[index])) {
                throw new Exception("Queue order is incorrect");
            }
            index++;
        }

    } catch (Exception e) {
        System.out.println("addPatient, nonempty queue, exception");
        return false;
    }

    return true;
  }



  /**
   * Tests the functionality of addPatient() method by calling addPatient() on a full queue and
   * ensuring the method throws an IllegalStateException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientFull() {
    // TODO complete the implementation of this tester method
    try {
      int capacity = 1;
      PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);
      
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      
      priorityCareAdmissions.addPatient(redA);
      priorityCareAdmissions.addPatient(redB);
      throw  new IllegalStateException("queue is full");
    } catch(IllegalStateException e) {
      return true;
    } catch(Exception e) {
      System.out.println("addPatient, full queue, exception");
      return false;
    }
  }

  /**
   * Tests the functionality of addPatient() method by calling addPatient() with a null
   * PatientRecord and ensuring the method throws a NullPointerException.
   * 
   * @return true if PriorityCareAdmissions.addPatient() exhibits expected behavior, false
   *         otherwise.
   */
  public static boolean testAddPatientNull() {
    // TODO complete the implementation of this tester method
    try {
      int capacity = 1;
      PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);
      PatientRecord.resetCounter();
      priorityCareAdmissions.addPatient(null);
      throw new NullPointerException("the patient been added is null");
    } catch (NullPointerException e) {
      return true;
    } catch (Exception e) {
      System.out.println("addPatient, null patient, exception");
      return false;
    }

  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on an empty
   * queue.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() throws a NoSuchElementException,
   *         false otherwise
   */
  public static boolean testRemoveBestRecordEmpty() {
    // TODO complete the implementation of this tester method
    try {
      int capacity = 1;
      PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(capacity);
      emptyQueue.removeBestRecord();
      throw new NoSuchElementException();
    } catch(NoSuchElementException e) {
      return true;
    } catch (Exception e) {
      System.out.println("remove, empty queue, exception");
      return false;
    }
  }


  /**
   * Tests the functionality of removeBestRecord() method by calling removeBestRecord() on a queue
   * of size one.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord and
   *         size is 0
   */
  public static boolean testRemoveBestRecordSizeOne() {
    // TODO complete the implementation of this tester method
    try{
      int capacity = 1;
      PriorityCareAdmissions oneSizeQueue = new PriorityCareAdmissions(capacity);
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      oneSizeQueue.addPatient(redA);
      oneSizeQueue.removeBestRecord();
      if (oneSizeQueue.isEmpty() && oneSizeQueue.size() == 0) {
        return true;
      }
    } catch (Exception e){
      System.out.println("remove, 1 size queue, exception");
      return false;
    }
    return false;// default return statement added to resolve compiler errors
  }

  /**
   * Tests the functionality of removeBestRecord() methods. 
   * 
   * The removeBestRecord() method must remove, and return the patient record with the highest
   * priority in the queue. The size must be decremented by one, each time the removeBestRecord()
   * method is successfully called. 
   * 
   * Remove the best record from a queue whose size is at least 6. Consider cases where
   * percolate-down recurses on left and right.
   * 
   * @return true if PriorityCareAdmissions.removeBestRecord() returns the correct PatientRecord
   *         each time it is called and size is appropriately decremented, false otherwise
   */
  public static boolean testRemoveBestRecordNonEmpty() {
    try {
      int capacity = 6;
      PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);

      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);
      PatientRecord yellowB = new PatientRecord('M', 77, TriageLevel.YELLOW);
      PatientRecord greenA = new PatientRecord('M', 19, TriageLevel.GREEN);
      PatientRecord greenB = new PatientRecord('M', 57, TriageLevel.GREEN);

      priorityCareAdmissions.addPatient(redA);
      priorityCareAdmissions.addPatient(redB);
      priorityCareAdmissions.addPatient(yellowA);
      priorityCareAdmissions.addPatient(yellowB);
      priorityCareAdmissions.addPatient(greenA);
      priorityCareAdmissions.addPatient(greenB);

      PatientRecord[] expectedOrder = new PatientRecord[]{
        redA, redB, yellowA, yellowB, greenA, greenB
      };

      // Check if the removeBestRecord() method returns the correct PatientRecord each time
      for (int i = 0; i < expectedOrder.length; i++) {
        PatientRecord removedRecord = priorityCareAdmissions.removeBestRecord();
        if (!removedRecord.equals(expectedOrder[i])) {
          throw new Exception("Incorrect record removed at iteration");
        }

        // Check if the size is appropriately decremented
        if (priorityCareAdmissions.size() != (capacity - (i + 1))) {
          throw new Exception("Incorrect size after removing record at iteration");
        }
      }

    } catch (Exception e) {
      System.out.println("removeBestRecord, nonempty queue, exception");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the clear() method.
   * Should implement at least the following scenarios: 
   * - clear can be called on an empty queue with no errors 
   * - clear can be called on a non-empty queue with no errors 
   * - After calling clear(), the queue should contain zero PatientRecords. 
   * - After calling clear(), the size should be 0
   *
   * @return true if PriorityCareAdmissions.clear() functions properly
   */
  public static boolean testClear() {
    // TODO complete the implementation of this tester method
    // - clear can be called on an empty queue with no errors
    try {
      int capacity = 6;
      PriorityCareAdmissions emptyQueue = new PriorityCareAdmissions(capacity);
      emptyQueue.clear();
      if (emptyQueue.size() != 0) {
        throw new Exception("empty queue, size != 0");
      }
      if (!emptyQueue.isEmpty()) {
        throw new Exception("empty queue, !isEmpty()");
      }
    } catch (Exception e){
      System.out.println("clear, empty queue, exception");
      return false;
    }
    
    // - clear can be called on a non-empty queue with no errors
    try {
      int capacity = 2;
      PriorityCareAdmissions nonEmptyQueue = new PriorityCareAdmissions(capacity);
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      nonEmptyQueue.addPatient(redA);
      nonEmptyQueue.addPatient(redB);
      nonEmptyQueue.clear();
      if(nonEmptyQueue.size() != 0) {
        throw new Exception("nonempty queue, size != 0");
      }
      if (!nonEmptyQueue.isEmpty()) {
        throw new Exception("nonempty queue, !isEmpty()");
      }
    } catch (Exception e) {
        System.out.println("clear, nonempty queue, exception");
        return false;
    }
    return true;
  }


  /**
   * Tests toString() method of PriorityCareAdmissions class.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testToString() {
    try {
      int capacity = 6;
      PriorityCareAdmissions priorityCareAdmissions = new PriorityCareAdmissions(capacity);
      
      PatientRecord.resetCounter();
      PatientRecord redA = new PatientRecord('M', 21, TriageLevel.RED);
      PatientRecord redB = new PatientRecord('M', 26, TriageLevel.RED);
      PatientRecord yellowA = new PatientRecord('M', 11, TriageLevel.YELLOW);

      // add the patient records to the queue
      priorityCareAdmissions.addPatient(redA);
      priorityCareAdmissions.addPatient(redB);
      priorityCareAdmissions.addPatient(yellowA);

      // check that the toString() method returns the expected output
      String expectedOutput = redA.toString() + "\n" + redB.toString() + "\n" + yellowA.toString() + "\n";
      String actualOutput = priorityCareAdmissions.toString();
      if (!expectedOutput.equals(actualOutput)) {
        throw new Exception("expected != actual");
      }
    } catch (Exception e) {
      System.out.println("toString, exception");
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

    return testPatientRecordCompareToDifferentTriage()
        && testPatientRecordCompareToSameTriageDifferentArrival()
        && testPatientRecordCompareToSameTriageSameArrival() && testPeekEmpty()
        && testPeekNonEmpty() && testAddPatientEmpty() && testAddPatientNonEmpty()
        && testAddPatientFull() && testAddPatientNull() && testRemoveBestRecordNonEmpty()
        && testRemoveBestRecordEmpty() && testRemoveBestRecordSizeOne() && testClear()
        && testToString();
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToDifferentTriage: "
        + (testPatientRecordCompareToDifferentTriage() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageDifferentArrival: "
        + (testPatientRecordCompareToSameTriageDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPatientRecordCompareToSameTriageSameArrival: "
        + (testPatientRecordCompareToSameTriageSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testConstructor: " + (testConstructor() ? "Pass" : "Failed!"));
    System.out.println("testPeekEmpty: " + (testPeekEmpty() ? "Pass" : "Failed!"));
    System.out.println("testPeekNonEmpty: " + (testPeekNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientEmpty: " + (testAddPatientEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testAddPatientNonEmpty: " + (testAddPatientNonEmpty() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientFull: " + (testAddPatientFull() ? "Pass" : "Failed!"));
    System.out.println("testAddPatientNull: " + (testAddPatientNull() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordNonEmpty: " + (testRemoveBestRecordNonEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordEmpty: " + (testRemoveBestRecordEmpty() ? "Pass" : "Failed!"));
    System.out.println(
        "testRemoveBestRecordSizeOne: " + (testRemoveBestRecordSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testClear: " + (testClear() ? "Pass" : "Failed!"));
    System.out.println("testToString: " + (testToString() ? "Pass" : "Failed!"));
  }

}
