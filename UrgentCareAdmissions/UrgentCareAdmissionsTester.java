//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    UrgentCareAdmissionsTesters to test each method in UrgentCareAdmissions
// Course:   CS 300 Spring 2023
//
// Author:   (Zihan Xu)
// Email:    (zxu536@wisc.edu)
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
// No partner.
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         No
// Online Sources:  Bro Code on Youtube
//
///////////////////////////////////////////////////////////////////////////////
// UrgentCareAdmissionsTester

import java.util.Arrays; // consider using Arrays.deepEquals() to test the contents of a 2D array

// Javadoc style class header comes here
public class UrgentCareAdmissionsTester {

  /**
   * This test method is provided for you in its entirety, to give you a model for the rest of this
   * class. This method tests the getAdmissionIndex() method on a non-empty, non-full array of
   * patient records which we create and maintain here.
   * 
   * This method tests three scenarios:
   * 
   *   1. Adding a patient with a HIGHER triage priority than any currently present in the array. 
   *   To do this, we create an array with no RED priority patients and get the index to add a RED
   *   priority patient. We expect this to be 0, so if we get any other value, the test fails.
   *   
   *   2. Adding a patient with a LOWER triage priority than any currently present in the array. 
   *   To do this, we create an array with no GREEN priority patients and get the index to add a 
   *   GREEN priority patient. We expect this to be the current size of the oversize array, so if 
   *   we get any other value, the test fails.
   *   
   *   3. Adding a patient with the SAME triage priority as existing patients. New patients at the
   *   same priority should be added AFTER any existing patients. We test this for all three triage
   *   levels on an array containing patients at all three levels.
   * 
   * @author hobbes
   * @return true if and only if all test scenarios pass, false otherwise
   */
  public static boolean testGetIndex() {

    // The non-empty, non-full oversize arrays to use in this test.
    // Note that we're using the UrgentCareAdmissions named constants to create these test records,
    // rather than their corresponding literal int values. 
    // This way if the numbers were to change in UrgentCareAdmissions, our test will still be valid.
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;

    int[][] patientsListOnlyYellow = new int[][] {
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      null, null, null, null, null
    };
    int onlyYellowSize = 3;

    // scenario 1: add a patient with a higher priority than any existing patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 2: add a patient with a lower priority than any existing patient
    {
      int expected = onlyYellowSize;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListOnlyYellow, onlyYellowSize);
      if (expected != actual) return false;
    }

    // scenario 3: verify that a patient with the same priority as existing patients gets
    // added after all of those patients
    {
      int expected = 1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = 4;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
      
      expected = allLevelsSize;
      actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, 
          patientsListAllLevels, allLevelsSize);
      if (expected != actual) return false;
    }

    // and finally, verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int[][] {
        {32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null, null
      };
      if (!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) return false;

      int[][] onlyYellowCopy = new int[][] {
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.YELLOW},
        {11901, 5, UrgentCareAdmissions.YELLOW},
        null, null, null, null, null
      };
      if (!Arrays.deepEquals(patientsListOnlyYellow, onlyYellowCopy)) return false;
    }

    return true;
  }
  
  // Tests the behavior of the addPatient method using a non-empty, non-full array. Each test 
  // should verify that the returned size is as expected and that the array has been updated 
  // (or not) appropriately
  public static boolean testAddPatient() {
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.GREEN},
      {11901, 5, UrgentCareAdmissions.GREEN},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;
    
    int[][] patientsListOnlyGreen = new int[][] {
      {21801, 2, UrgentCareAdmissions.GREEN},
      {22002, 4, UrgentCareAdmissions.GREEN},
      {11901, 5, UrgentCareAdmissions.GREEN},
      null, null, null, null, null
    };
    int onlyGreenSize = 3;
    
    // (1) add a patient to the END of the patientsList
    {
      int patientAtEnd[] = {11451, 6, UrgentCareAdmissions.GREEN};
      int expected = 4;
      int index = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.GREEN, patientsListOnlyGreen, onlyGreenSize);
      int actual = UrgentCareAdmissions.addPatient(patientAtEnd, index, patientsListOnlyGreen, onlyGreenSize);
      if (actual != expected) {
        return false;
      }
    }
    // (2) add a patient to the MIDDLE of the patientsList
    {
      int patientInMiddle [] = {66677, 3, UrgentCareAdmissions.YELLOW};
      int expected = 6;
      int index = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, patientsListAllLevels, allLevelsSize);
      int actual = UrgentCareAdmissions.addPatient(patientInMiddle, index, patientsListAllLevels, allLevelsSize);
      if (actual != expected) {
        return false;
      }
    }
    // (3) add a patient using an invalid (out-of-bounds) index
    {
      int patientInvalid [] = {55781, 11, UrgentCareAdmissions.RED};
      int expected = onlyGreenSize;
      int index = -1;
      int actual = UrgentCareAdmissions.addPatient(patientInvalid, index, patientsListOnlyGreen, onlyGreenSize);
      if (actual != expected) {
        return false;
      }
    }
    //verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int [][] {
        {32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {66677, 3, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.GREEN},
        {11901, 5, UrgentCareAdmissions.GREEN},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null
      };
      if(!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) {
        return false;
      }
      
      int[][] patientsListOnlyGreenCopy = new int[][] {
        {21801, 2, UrgentCareAdmissions.GREEN},
        {22002, 4, UrgentCareAdmissions.GREEN},
        {11901, 5, UrgentCareAdmissions.GREEN},
        {11451, 6, UrgentCareAdmissions.GREEN},
        null, null, null, null
      };
      if(!Arrays.deepEquals(patientsListOnlyGreenCopy, patientsListOnlyGreen)) {
        return false;
      }  
    }
    
    return true;
  }
  
  // Tests the behavior of the removeNextPatient method using a non-empty, non-full array. Each 
  // test should verify that the returned size is as expected and that the array has been updated
  // (or not) appropriately
  public static boolean testRemovePatient() {
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.GREEN},
      {11901, 5, UrgentCareAdmissions.GREEN},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;
    
    int[][] patientsListOnlyGreen = new int[][] {
      {21801, 2, UrgentCareAdmissions.GREEN},
      null, null, null, null, null, null, null
    };
    int onlyGreenSize = 1;
    // (1) remove a patient from a patientsList containing more than one record
    {
      int expected = 4;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListAllLevels, allLevelsSize);
      if (actual != expected) {
        return false;
      }
    }
    // (2) remove a patient from a patientsList containing only one record
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.removeNextPatient(patientsListOnlyGreen, onlyGreenSize);
      if (actual != expected) {
        return false;
      }
    }
    
  //verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int [][] {
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.GREEN},
        {11901, 5, UrgentCareAdmissions.GREEN},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null, null, null
      };
      if(!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) {
        return false;
      }
      
      int[][] patientsListOnlyGreenCopy = new int[][] {
        null, null, null, null, null, null, null, null
      };
      if(!Arrays.deepEquals(patientsListOnlyGreenCopy, patientsListOnlyGreen)) {
        return false;
    }
    
      return true;
    }
  }
  // Tests the behavior of the getPatientIndex method using a non-empty, non-full array.
  public static boolean testGetPatientIndex() {
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.GREEN},
      {11901, 5, UrgentCareAdmissions.GREEN},
      {31501, 1, UrgentCareAdmissions.GREEN},
      null, null, null
    };
    int allLevelsSize = 5;
    
    int[][] patientsListOnlyRed = new int[][] {
      {21801, 2, UrgentCareAdmissions.RED},
      {22002, 4, UrgentCareAdmissions.RED},
      {11901, 5, UrgentCareAdmissions.RED},
      null, null, null, null, null
    };
    int onlyRedSize = 3;
    // (1) look for a patient at the end of the list
    {
      int expected = 2;
      int actual = UrgentCareAdmissions.getPatientIndex(11901, patientsListOnlyRed, onlyRedSize);
      if (expected != actual) {
        return false;
      }
    }
    // (2) look for a patient in the middle of the list
    {
      int expected = 2;
      int actual = UrgentCareAdmissions.getPatientIndex(22002, patientsListAllLevels, allLevelsSize);
      if (expected != actual) {
        return false;
      }
    }
    // (3) look for a patient not present in the list
    {
      int expected = -1;
      int actual = UrgentCareAdmissions.getPatientIndex(12345, patientsListOnlyRed, onlyRedSize);
      if (expected != actual) {
        return false;
      }
    }
    //verify that the arrays were not changed at all
    {
      int[][] allLevelsCopy = new int [][] {
        {32702, 3, UrgentCareAdmissions.RED},
        {21801, 2, UrgentCareAdmissions.YELLOW},
        {22002, 4, UrgentCareAdmissions.GREEN},
        {11901, 5, UrgentCareAdmissions.GREEN},
        {31501, 1, UrgentCareAdmissions.GREEN},
        null, null, null
      };
      if(!Arrays.deepEquals(patientsListAllLevels, allLevelsCopy)) {
        return false;
      }
      int[][] patientsListOnlyRedCopy = new int[][] {
        {21801, 2, UrgentCareAdmissions.RED},
        {22002, 4, UrgentCareAdmissions.RED},
        {11901, 5, UrgentCareAdmissions.RED},
        null, null, null, null, null
      };
      if(!Arrays.deepEquals(patientsListOnlyRedCopy, patientsListOnlyRed)) {
        return false;
      }
    }
    return true;
  }
  
  // Tests the getLongestWaitingPatientIndex method using a non-empty, non-full array. When
  // designing these tests, recall that arrivalOrder values will all be unique!
  public static boolean testLongestWaitingPatient() {
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 6, UrgentCareAdmissions.RED},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      {18901, 2, UrgentCareAdmissions.GREEN}, 
      null, null
    };
    int allLevelsSize = 6;
    
    int[][] patientsListOnlyOnePatient = new int[][] {
      {21801, 1, UrgentCareAdmissions.RED},
      null, null, null, null, null, null, null
    };
    int onlyOnePatientSize = 1;
    // (1) call the method on a patientsList with only one patient
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListOnlyOnePatient, onlyOnePatientSize);
      if (expected != actual) {
        return false;
      }
    }
    // (2) call the method on a patientsList with at least three patients
    {
      int expected = 4;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(patientsListAllLevels, allLevelsSize);
      if (expected != actual) {
        return false;
      }
    }
    
    return true;
  }
  
  // Tests the edge case behavior of the UrgentCareAdmissions methods using empty and full arrays
  public static boolean testEmptyAndFullArrays() {
    // (1) test getAdmissionIndex using an empty patientsList array and any triage level
    int[][] noElementList = new int[][] {
      null, null, null, null, null, null, null, null
    };
    int noElementListSize = 0;
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.RED, noElementList, noElementListSize);
      if (expected != actual) {
        return false;
      }
      
     }
    // (2) test getAdmissionIndex using a full patientsList array and any triage level
    int[][] fullElementsList = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 6, UrgentCareAdmissions.RED},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      {18901, 2, UrgentCareAdmissions.GREEN}, 
      {19128, 7, UrgentCareAdmissions.GREEN},
      {12138, 8, UrgentCareAdmissions.GREEN}
    };
    int fullElementListSize = 8;
    {
      int expected = -1;
      int actual = UrgentCareAdmissions.getAdmissionIndex(UrgentCareAdmissions.YELLOW, fullElementsList, fullElementListSize);
      if(expected != actual) {
        return false;
      }
      
    }
    // (3) test addPatient using a full patientsList array
    int[][] fullAddPatientList = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 6, UrgentCareAdmissions.RED},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.YELLOW},
      {18901, 2, UrgentCareAdmissions.GREEN}, 
      {19128, 7, UrgentCareAdmissions.GREEN},
      {12138, 8, UrgentCareAdmissions.GREEN}
    };
    int fullAddPatientListSize = 8;
    {
      int [] patientRecord = new int[] {17920, 9, UrgentCareAdmissions.GREEN};
      int expected = fullAddPatientListSize;
      int index = 9;
      int actual = UrgentCareAdmissions.addPatient(patientRecord, index, fullAddPatientList, fullAddPatientListSize);
      if(expected != actual) {
        return false;
      }
      
    }
    // (4) test removeNextPatient using an empty patientsList array
    int[][] removeNextNoElementList = new int[][] {
      null, null, null, null, null, null, null, null
    };
    int removeNextNoElementSize = 0;
    {
      int expected = 0;
      int actual = UrgentCareAdmissions.removeNextPatient(removeNextNoElementList, removeNextNoElementSize);
      if (expected != actual) {
        return false;
      }
    }
    // (5) test getPatientIndex using an empty patientsList array
    int[][] getPatientIndexNoElementList = new int[][] {
      null, null, null, null, null, null, null, null
    };
    int getPatientIndexNoElementSize = 0;
    {
      int expected = -1;
      int actual = UrgentCareAdmissions.getPatientIndex(11451, getPatientIndexNoElementList, getPatientIndexNoElementSize);
      if (expected != actual) {
        return false;
      }
    }
    // (6) test getLongestWaitingPatientIndex using an empty patientsList array
    int[][] getLongestWaitingPatientNoElementList = new int[][] {
      null, null, null, null, null, null, null, null
    };
    int getLongestWaitingPatientsNoElementSize = 0;
    {
      int expected = -1;
      int actual = UrgentCareAdmissions.getLongestWaitingPatientIndex(getLongestWaitingPatientNoElementList, getLongestWaitingPatientsNoElementSize);
      if (expected != actual) {
        return false;
      }
    }
    return true;
  }
  
  // Tests the getSummary method using arrays in various states
  public static boolean testGetSummary() {
    // (1) test getSummary using an empty patientsList
    int[][] noElementList = new int[][] {
      null, null, null, null, null, null, null, null
    };
    int noElementListSize = 0;
    {
      String expected = "Total number of patients: " + "0\n" + "RED: 0" + "\n" + "YELLOW: 0" + "\n" + "GREEN: 0" + "\n";
      String actual = UrgentCareAdmissions.getSummary(noElementList, noElementListSize);
      if (!expected.equals(actual)) {
        return false;
      }
    }
    // (2) test getSummary using a patientsList with multiple patients at a single triage level
    int[][] patientsListOnlyYellow = new int[][] {
      {21801, 2, UrgentCareAdmissions.YELLOW},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      null, null, null, null, null
    };
    int onlyYellowSize = 3;
    {
      String expected = "Total number of patients: " + "3\n" + "RED: 0" + "\n" + "YELLOW: 3" + "\n" + "GREEN: 0" + "\n";
      String actual = UrgentCareAdmissions.getSummary(patientsListOnlyYellow, onlyYellowSize);
      if (!expected.equals(actual)) {
        return false;
      }
    }
    // (3) test getSummary using a patientsList with patients at all triage levels
    
    int[][] patientsListAllLevels = new int[][] {
      {32702, 3, UrgentCareAdmissions.RED},
      {21801, 6, UrgentCareAdmissions.RED},
      {22002, 4, UrgentCareAdmissions.YELLOW},
      {11901, 5, UrgentCareAdmissions.YELLOW},
      {31501, 1, UrgentCareAdmissions.GREEN},
      {18901, 2, UrgentCareAdmissions.GREEN}, 
      null, null
    };
    int allLevelsSize = 6;
    {
      String expected = "Total number of patients: " + "6\n" + "RED: 2" + "\n" + "YELLOW: 2" + "\n" + "GREEN: 2" + "\n";
      String actual = UrgentCareAdmissions.getSummary(patientsListAllLevels, allLevelsSize);
      if (!expected.equals(actual)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Runs each of the tester methods and displays their result
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("get index: "+testGetIndex());
    System.out.println("add patient: "+testAddPatient());
    System.out.println("remove patient: "+testRemovePatient());
    System.out.println("get patient: "+testGetPatientIndex());
    System.out.println("longest wait: "+testLongestWaitingPatient());
    System.out.println("empty/full: "+testEmptyAndFullArrays());
    System.out.println("get summary: "+testGetSummary());
  }

}
