import java.util.Arrays;
/**
 * This class implements the six methods mentioned in javadoc. This is done by finding the index of a new patient, 
 * adding a new patient to the waiting list, removing a patient who has already been seen in the list, 
 * finding the patient who has waited the longest, and tallying triage information.
 * @author Zihan Xu
 * 
 */
public class UrgentCareAdmissions {
  
  final static int RED = 0;
  
  final static int YELLOW = 1;
  
  final static int GREEN = 2; 
  
  /**
   * read the triage level of a new patient and insert to the correct position
   * @param triage the urgency level of the next patient's issue, RED YELLOW or GREEN
   * @param patientsLists the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the index of inserted patient in the waiting list, return -1 if the waiting list if full
   */
    public static int getAdmissionIndex(int triage, int[][]patientsList, int size) {
      //check whether the patients waiting list if full, if it is full, return -1 which is not insert any more patient
      if(size == patientsList.length) {
        return -1;
      }
      //using for loop check what urgency the new patient is, comparing with final int represent the urgency level, and insert to correct position
      for(int i = 0; i < size; i++) {
        if(patientsList[i][2] > triage) {
          return i;
        }
      }
      return size;
    }
    
  /**
   * Adds the patient record, a three-element perfect size array of ints, to the patients list in the given position.
   * @param patientRecord a three-element perfect size array of ints, containing the patient's 5-digit case ID, their admission order number, and their triage level
   * @param index the index at which the patientRecord should be added to patientsList, assumed to correctly follow the requirements of getAdmissionIndex()
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the number of patients in patientsList after this method has finished running, return -1 if index is not valid
   */
    public static int addPatient(int[] patientRecord, int index, int[][] patientsList, int size) {
      //check whether the list if full and check whether the index is valid
      if (index < 0 || index > size) {
        return size;  
      }
      //insert the new patient in the patientsList, and move every element after new patient back
      for (int i = size - 1; i >= index; i--) {
        patientsList[i + 1] = patientsList[i]; 
      }
      patientsList[index] = patientRecord;
        return size + 1;
    }
    
  /**
   * Removes the patient record at index 0 of the patientsList, if there is one, and updates the 
   * rest of the list to maintain the overSize array in its current ordering.
   * 
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the number of patients in the patientsList after this method has finished running
   */
    public static int removeNextPatient(int[][] patientsList, int size) {
      //check whether the size if a empty size
      if (size <= 0) {
        return 0;
      }
      //move each element foward and return size - 1 to remove the first one in waiting list
      for(int i = 0; i < size; i++) {
        patientsList[i] = patientsList[i + 1];
      }
      return size - 1;
    }
    
  /**
   * Finds the index of a patient given their caseID number. This method must not modify patientsList in any way
   * @param caseID the five-digit case number assigned to the patient record to find
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the index of the patient record matching the given caseID number, or -1 if the list is empty or the caseID is not found
   */
    public static int getPatientIndex(int caseID, int[][] patientsList, int size) {
      //check whether it is an empty list
      if(size == 0) {
        return -1;
      }
      //use for loop to find the exact caseID to find its index
      for(int i = 0; i < size; i++) {
        if(patientsList[i][0] == caseID) {
          return i;
        }
      }
      return -1;
    }
    
    
  /**
   * Finds the patient who arrived earliest still currently present in the patientsList, and returns the index of their patient record within the patientsList. 
   * The arrival value is strictly increasing for each new patient, so you will not need to handle the case where two values are equal.
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return the index of the patient record with the smallest value in their arrival integer, or -1 if the list is empty
   */
    public static int getLongestWaitingPatientIndex(int[][] patientsList, int size) {
      //check whether it is a empty list
      if(size <= 0) {
        return -1;
      }
      //the volume for minimumOrder, try the biggest number in case out of bound
      int minimumOrder = Integer.MAX_VALUE;
      int index = -1;
      for(int i = 0; i < size; i++) {
        if(patientsList[i][1] < minimumOrder) {
          index = i;
          minimumOrder = patientsList[i][1];
        }
        
      }
      return index;
    }
  /**
   * The first line displays the current size of the array. The next three lines display counts of patients at each of the three triage levels currently in the patientsList. 
   * Any or all of these numbers may be 0.
   * @param patientsList the current, active list of patient records
   * @param size the number of patients currently in the list
   * @return a String summarizing the patientsList as shown in this comment
   */
    public static String getSummary(int[][] patientsList, int size) {
      int redPatient = 0;
      int yellowPatient = 0;
      int greenPatient = 0;
      //count the number for each order
      for (int i = 0; i < size; i++){
        if(patientsList[i][2] == RED) {
          ++redPatient;
        }
        if(patientsList[i][2] == YELLOW) {
          ++yellowPatient;
        }
        if(patientsList[i][2] == GREEN) {
          ++greenPatient;
        }
      }
      //String infoSum is the finally sum String would be visualized
      String infoSum = "Total number of patients: " + Integer.toString(size) + "\n" + "RED: " + redPatient + "\n" + "YELLOW: " + yellowPatient + "\n" + "GREEN: " + greenPatient + "\n";
      return infoSum;
    }
    
    
    
}
  
