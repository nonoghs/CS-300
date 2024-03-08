//File header comes here
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    Iterator package for Immitating the Twitter datastructure
//Course:   CS 300 Spring 2023
//
//Author:   Zihan Xu
//Email:    zxu536@wisc.edu
//Lecturer: (Mouna Kacem)
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//NO Partner for this assignment.
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__Write-up states that pair programming is allowed for this assignment.
//__We have both read and understand the course Pair Programming Policy.
//__We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:   no
//Online Sources:  no
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This (very basic) data type models a Twitter user.
 * 
 * @author Zihan Xu
 *
 */
public class User extends Object{
  
  //The username this User tweets under
  private boolean isVerified;
  //The verified status of this User (whether they have a blue checkmark or not)
  private String username;
  
  /**
   * Constructs a new User object with a given username. All Users are unverified by default.
   * 
   * @param userName - the username of this User.
   * @throws IllegalArgumentException - if the given username contains an asterisk ("*") character, or is null, or is blank
   * 
   */
  public User(String username) throws IllegalArgumentException{
    //check conditions, whether contain '*', is null or is blank.
    if(username.contains("*") || username.equals(null) || username.isBlank()) {
      throw new IllegalArgumentException();      
    }
    this.username = username;
    this.isVerified = false;
  }
  
  /**
   * Accesses the username of this User
   * 
   * @return the username this User tweets under
   * 
   */
  public String getUsername() {
    return username;
  }
  
  /**
   * Determines whether the User is a verified user or not
   * 
   * @return - true if this User is verified
   * 
   */
  public boolean isVerified() {
    return isVerified;
  }
  
  /**
   * Gives this User an important-looking asterisk
   * 
   */
  public void verify() {
    isVerified = true;
  }
  
  /**
   * Takes this User's important-looking asterisk away
   * 
   */
  public void revokeVerification() {
    isVerified = false;
  }
  
  @Override
  /**
   * Creates a String representation of this User for display. For example, if this User's username is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's username is "dril" and they are not verified, this method will return "@dril" (with no asterisk).
   * 
   * @return a String representation of this User as described above
   * 
   */
  public String toString() {
      String atSymbol = "@";
      String userNameToShow = username;
      String verificationSymbol = "";

      if(isVerified) {
        verificationSymbol = "*";
      }
      
      String result = atSymbol + userNameToShow + verificationSymbol;
      return result;
  }
}
