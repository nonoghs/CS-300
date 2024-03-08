////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    Contains patient's information
//Course:   CS 300 Spring 2023
//
//Author:  Zhenghong Wang 
//Email:   zwang2654@wisc.edu
//Lecturer: Mouna Kacem 
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:   Zihan Xu
//Partner Email:   zxu536@wisc.edu
//Partner Lecturer's Name: Mouna Kacem 
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An instantiable class which models a patient's record for the admissions program. 
 * This class automatically generates a caseID number from provided patient data, 
 * which is not exactly HIPAA-compliant.
 * 
 * @author Zhenghong Wang
 * @author Zihan Xu
 *
 */
public class PatientRecord {

  static final int GREEN = 2; //One of the triage levels.
  static final int RED = 0;  //One of the triage levels.
  static final int YELLOW = 1;  //One of the triage levels.
  private int triage;  //This patient's triage level, one of [RED, YELLOW, GREEN].
  private static int patientCounter = 1; //Counts the number of patient case IDs created.
  private int orderOfArrival;  //The order in which this patient arrived; taken from the value of patientCounter when this record was created.
  private int age;  //This patient's age.

  private char gender;  //This patient's single-character gender marker.
  final int CASE_NUMBER; //The generated case number associated with this patient record.

  private boolean hasBeenSeen = false; //Whether this patient has been marked as "seen".

  /**
   * Creates a new patient record and assigns it a CASE_NUMBER using the provided information. Be careful to record the orderOfArrival before creating a CASE_NUMBER, 
   * as the counter will advance when the static helper method generateCaseNumber() is called.
   * @param gender - a single-character representation of this patient's reported gender
   * @param age - the age of this patient in years
   * @param triage - the triage level of this patient
   * @throws IllegalArgumentException - with a descriptive error message if the provided triage value is not one of the class constants
   */
  public PatientRecord(char gender, int age, int triage) throws IllegalArgumentException{
    if (triage != 0 && triage != 1 && triage != 2 ){throw new IllegalArgumentException("triage has in between 0 and 2");}
    this.triage = triage;
    this.gender =  gender;
    this.age = age;
    this.CASE_NUMBER = generateCaseNumber(gender, age);
    orderOfArrival = patientCounter;
  }

  /**
   * Generates a five-digit case number for this patient using their reported gender and age.
   The first digit of the case number is based on gender marker: F=1, M=2, X=3. Any other gender marker should be assigned the first digit of 4.
   The next two digits are the last two digits of the patient's age: 03 could mean a three-year-old or a 103-year-old.
   The last two digits increment according to the number of patients admitted during this run of ExceptionalCare; the first patient should be 01, counting up to 99, and then wrapping around to 00.
   Therefore, a 27-year-old nonbinary person who is the 20th patient of the day would be 32720.
   *
   * @param gender - a single-character representation of this patient's reported gender
   * @param age - the age of this patient in years
   * @return a five-digit case number for the patient
   */
  public static int generateCaseNumber(char gender, int age) {
    int firstDigit = 0;
    switch (gender) {
      case 'F':
        firstDigit = 1;
        break;
      case 'M':
        firstDigit = 2;
        break;
      case 'X':
        firstDigit = 3;
        break;
      default:
        firstDigit = 4;
    }
    int nextTwoDigitsOfAge = age % 100;
    int lastTwoDigitsOfCounter = ++patientCounter % 100;
    int generatedCaseNumber = firstDigit * 10000 + nextTwoDigitsOfAge * 100 + lastTwoDigitsOfCounter;
    return generatedCaseNumber;

  }

  /**
   * For tester class purposes only: resents PatientRecord.patientCounter to 1. 
   * This method should be called at the beginning of EACH tester method to ensure that the methods are 
   * not dependent on being called in a particular order.
   * 
   */
  public static void resetCounter(){
    patientCounter = 0;
  }
  
  /**
   * Accessor method for triage
   * 
   * @return - this PatientRecord's triage value
   */
  public int getAge(){
    return age;
  }
  
  /**
   * Accessor method for gender
   * 
   * @return - this PatientRecord's gender marker
   */
  public char getGender(){
    return gender;
  }
  
  /**
   * Accessor method for age
   * 
   * @return - this PatientRecord's age value
   */
  public int getTriage(){
    return triage;
  }
  
  /**
   * Accessor method for order of arrival
   * 
   * @return - this PatientRecord's orderOfArrival value
   */
  public int getArrivalOrder(){
    return this.orderOfArrival;
  }

  /**
   * Accessor method for hasBeenSeen
   * 
   * @return - true if this patient has been seen, false otherwise
   */
  public boolean hasBeenSeen(){
    return hasBeenSeen;
  }
  
  /**
   * Marks this patient as having been seen. There is no way to undo this action.
   */
  public void seePatient(){
    hasBeenSeen = true;
  }

  /**
   * Creates and returns a String representation of this PatientRecord using its data field values:
     [CASE_NUMBER]: [AGE][GENDER] ([TRIAGE])
     Note that the [] are not actually included in the result. For example, a 17-year-old male who was the first person to arrive and has triage level YELLOW would have the toString()
     21701: 17M (YELLOW)
   * @overrides - toString in class Object
   * @return - a String representation of this PatientRecord
   */
  public String toString(){
    String triageColor;
    switch (triage) {
      case 1:
        triageColor = "YELLOW";
        break;
      case 0:
        triageColor = "RED";
        break;
      case 2:
        triageColor = "GREEN";
        break;
      default:
        triageColor = "UNKNOWN";
        break;
    }
    String output = String.valueOf(CASE_NUMBER);
    output = output+": "+ this.age + this.gender + " (" + triageColor + ")";

    return output;
  }

}
