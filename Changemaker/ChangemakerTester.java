//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:   Changemaker class provides operations related to making change
// Course:   CS 300 Spring 2023
//
// Author:   zhenghong (chris) Wang
// Email:    zwang2654@wisc.edu
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Zihan Xu
// Partner Email:  zxu536@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   no
// Online Sources: no
//
///////////////////////////////////////////////////////////////////////////////
import java.util.Arrays;
/**
 * The ChangemakerTester class contains test methods to verify the functionality
 * of the Changemaker class. This class should include test methods for each
 * method in the Changemaker class, ensuring that they produce the expected
 * results under various test scenarios.
 */
public class ChangemakerTester {

  /**
   * This method tests the count() method of the Changemaker class in the following scenarios:
   * Normal case when input a given case in pdf and check whether the value is the expected value mentioned in pdf.
   * Case where value is negative
   * Case where there is not enough money to make change
   * Case where value is zero
   *
   * @return true if all test cases pass, false otherwise.
   *
   */
  public static boolean testCountBase() {

    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {3, 0, 0, 3};
    //Normal case
    {
      int valueNormal = 27;
      int expectedResult = 3;
      int result = Changemaker.count(denominations, coinsRemaining, valueNormal);
      //System.out.println(result);
      if(result != expectedResult) {
        return false;
      }
    }
    //Case when value is negative
    {
      int valueNegative = -1;
      int negativeResult = Changemaker.count(denominations, coinsRemaining, valueNegative);
      int expectedNegative = 0;
      //System.out.println(negativeResult);
      if(negativeResult != expectedNegative) {
        return false;
      }
    }
    //Case when there is no money enough to make change
    {
      int valueLarge = 1000;
      int largeResult = Changemaker.count(denominations, coinsRemaining, valueLarge);
      int expectedLarge = 0;
      //System.out.println(largeResult);
      if(largeResult != expectedLarge) {
        return false;
      }
    }
    //Case when value is 0
    {
      int valueNull = 0;
      int nullResult = Changemaker.count(denominations, coinsRemaining, valueNull);
      int expectedNull = 1;
      //System.out.println(nullResult);
      if(nullResult != expectedNull) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the count() method of the Changemaker class in three different scenarios.
   * There are three different coins used to make change.
   * There are two different optimal ways to make change using the same number of coins.
   * Greedy approach does not produce a result with the minimum number of coins used.
   *
   * @return true if all test cases pass, false otherwise.
   *
   */
  public static boolean testCountRecursive() {

    //Test scenario 1
    {
      int[] denominations1 = {1, 5, 10, 25};
      int[] coinsRemaining1 = {1, 1, 1, 1};
      int value1 = 36;
      int expected1 = 6;
      int result1 = Changemaker.count(denominations1, coinsRemaining1, value1);
      if(expected1 != result1) {
        return false;
      }
    }

    //Test scenario 2
    {
      int[] denominations2 = {2, 5, 7, 10};
      int[] coinsRemaining2 = {1, 1, 1, 1};
      int value2 = 12;
      int expected2 = 4;
      int result2 = Changemaker.count(denominations2, coinsRemaining2, value2);
      if(expected2 != result2) {
        return false;
      }
    }

    //Test scenario 3
    {
      int[] denominations3 = {1, 5, 6, 9};
      int[] coinsRemaining3 = {2, 1, 1, 1};
      int value3 = 11;
      int expected3 = 5;
      int result3 = Changemaker.count(denominations3, coinsRemaining3, value3);
      if(expected3 != result3) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method test the minCoins() method in the Changemaker class with following four cases.
   * Normal case when input a given case in pdf and check whether the value is the expected value mentioned in pdf.
   * Case where value is negative.
   * Case where there is not enough money to make change.
   * Case where value is zero.
   *
   * @return true if all test cases pass, false otherwise.
   */
  public static boolean testMinCoinsBase() {
    int[] denominations = {1, 5, 10, 25};
    int[] coinsRemaining = {5, 1, 3, 1};

    //Normal case
    {
      int value = 59;
      int expected = 8;
      int result = Changemaker.minCoins(denominations, coinsRemaining, value);
      //System.out.println(result);
      if(expected != result) {
        return false;
      }
    }
    //Case when value is negative
    {
      int valueNegative = -1;
      int negativeResult = Changemaker.minCoins(denominations, coinsRemaining, valueNegative);
      int expectedNegative = -1;
      //System.out.println(negativeResult);
      if(negativeResult != expectedNegative) {
        return false;
      }
    }
    //Case when there is no enough money to make change
    {
      int valueLarge = 1000;
      int largeResult = Changemaker.minCoins(denominations, coinsRemaining, valueLarge);
      int expectedLarge = -1;
      //System.out.println(largeResult);
      if(largeResult != expectedLarge) {
        return false;
      }
    }
    //Case when value is 0
    {
      int valueNull = 0;
      int nullResult = Changemaker.minCoins(denominations, coinsRemaining, valueNull);
      int expectedNull = 0;
      //System.out.println(nullResult);
      if(nullResult != expectedNull) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the minCoins() method of the Changemaker class in three different scenarios.
   * There are three different coins used to make change.
   * There are two different optimal ways to make change using the same number of coins.
   * Greedy approach does not produce a result with the minimum number of coins used.
   *
   * @return true if all test cases pass, false otherwise.
   */
  public static boolean testMinCoinsRecursive() {
    //Test scenario 1
    {
      int[] denominations1 = {1, 5, 10, 25};
      int[] coinsRemaining1 = {1, 1, 1, 1};
      int value1 = 36;
      int expected1 = 3;
      int result1 = Changemaker.minCoins(denominations1, coinsRemaining1, value1);
      //System.out.println(result1);
      if(expected1 != result1) {
        return false;
      }
    }

    //Test scenario 2
    {
      int[] denominations2 = {2, 5, 7, 10};
      int[] coinsRemaining2 = {1, 1, 1, 1};
      int value2 = 12;
      int expected2 = 2;
      int result2 = Changemaker.minCoins(denominations2, coinsRemaining2, value2);
      //System.out.println(result2);
      if(expected2 != result2) {
        return false;
      }
    }

    //Test scenario 3
    {
      int[] denominations3 = {1, 5, 6, 9};
      int[] coinsRemaining3 = {2, 1, 1, 1};
      int value3 = 11;
      int expected3 = 2;
      int result3 = Changemaker.minCoins(denominations3, coinsRemaining3, value3);
      //System.out.println(result3);
      if(expected3 != result3) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method tests the makeChange() method of the Changemaker class in three different scenarios.
   * Normal case when input a given case in pdf and check whether the value is the expected value mentioned in pdf.
   * Case where value is negative.
   * Case where there is not enough money to make change.
   * Case where value is zero.
   *
   * @return true if all test cases pass, false otherwise.
   */
  public static boolean testMakeChangeBase() {
    int[] denominations = {1, 5, 7, 11};
    int[] coinsRemaining = {12, 3, 2, 2};

    //Base case
    {
      int value = 12;
      int[] expectedNormal1 = {1, 0, 0, 1};
      int[] expectedNormal2 = {0, 1, 1, 0};
      int[] result = Changemaker.makeChange(denominations, coinsRemaining, value);
      if(!(!Arrays.equals(result, expectedNormal1) ^ !Arrays.equals(result, expectedNormal2))) {
        return false;
      }
    }

    //Case when value is negative
    {
      int valueNegative = -1;
      int[] negativeResult = Changemaker.makeChange(denominations, coinsRemaining, valueNegative);
      int[] expectedNegative = null;
      //System.out.println(negativeResult);
      if(!Arrays.equals(negativeResult, expectedNegative)) {
        return false;
      }
    }
    //Case when there is no enough money to make change
    {
      int valueLarge = 1000;
      int[] largeResult = Changemaker.makeChange(denominations, coinsRemaining, valueLarge);
      int[] expectedLarge = null;
      //System.out.println(negativeResult);
      if(!Arrays.equals(largeResult, expectedLarge)) {
        return false;
      }
    }
    //Case when value is 0
    {
      int valueNull = 1000;
      int[] nullResult = Changemaker.makeChange(denominations, coinsRemaining, valueNull);
      int[] expectedNull = null;
      //System.out.println(negativeResult);
      if(!Arrays.equals(nullResult, expectedNull)) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method tests the makeChange() method of the Changemaker class in three different scenarios.
   * There are three different coins used to make change.
   * There are two different optimal ways to make change using the same number of coins.
   * Greedy approach does not produce a result with the minimum number of coins used.
   *
   * @return
   */
  public static boolean testMakeChangeRecursive() {
    // Test scenario 1
    {
      int[] denominations1 = {1, 5, 10, 25};
      int[] coinsRemaining1 = {1, 1, 1, 1};
      int value1 = 36;
      int[] expected1 = {1, 0, 1, 1};
      int[] result1 = Changemaker.makeChange(denominations1, coinsRemaining1, value1);
      if(!Arrays.equals(expected1, result1)) {
        return false;
      }
    }

    //Test scenario 2
    {
      int[] denominations2 = {2, 5, 7, 10};
      int[] coinsRemaining2 = {1, 1, 1, 1};
      int value2 = 12;
      int[] expected2A = {1, 0, 0, 1};
      int[] expected2B = {0, 1, 1, 0};
      int[] result2 = Changemaker.makeChange(denominations2, coinsRemaining2, value2);
      if(!(!Arrays.equals(expected2A, result2) ^ !Arrays.equals(expected2B, result2))) {
        return false;
      }
    }
    //Test scenario 3
    {
      int[] denominations3 = {1, 5, 6, 9};
      int[] coinsRemaining3 = {2, 1, 1, 1};
      int value3 = 11;
      int[] expected3 = {0, 1, 1, 0};
      int[] result3 = Changemaker.makeChange(denominations3, coinsRemaining3, value3);
      if(!Arrays.equals(expected3, result3)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Call each test method and check their result.
   *
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("testCountBase: " + testCountBase());
    System.out.println("testCountRecursive: " + testCountRecursive());
    System.out.println("testMinCoinsBase: " + testMinCoinsBase());
    System.out.println("testMinCoinsRecursive: " + testMinCoinsRecursive());

    System.out.println("testMakeChangeBase: " + testMakeChangeBase());

    System.out.println("testMakeChangeRecursive: " + testMakeChangeRecursive());

  }
}
