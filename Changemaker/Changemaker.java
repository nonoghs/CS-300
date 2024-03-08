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
 * The Changemaker class provides utility methods to perform various operations
 * related to making change using given denominations and the available number
 * of coins for each denomination.
 */
public class Changemaker {
  /**
   * Returns the number of ways to make change for the given value using the
   * specified denominations and the available coins.
   *
   * @param denominations  an array of the coin denominations
   * @param coinsRemaining an array of the available coins for each denomination
   * @param value          the target value to make change for
   * @return the number of ways to make change for the given value
   */
  public static int count(int[] denominations, int[] coinsRemaining, int value) {
    int ret = 0;
    if (value == 0) {
      return 1;
    }
    if (value < 0){
      return 0;
    }
    for (int i = 0; i < denominations.length; i++) {
      // each i is the index of category of coin
      // if there is coin left
      if (coinsRemaining[i] > 0) {
        int[] copiedCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        // coin left over minus one
        int coincount = copiedCoinsRemaining[i];
        copiedCoinsRemaining[i] = coincount-1;
        int copiedValue = value;
        // deduct the value
        copiedValue -= denominations[i];
        ret += count(denominations, copiedCoinsRemaining,copiedValue);
      }
    }
    return ret;

  }

  /**
   * Returns the minimum number of coins required to make change for the given value
   * using the specified denominations and the available coins.
   *
   * @param denominations  an array of the coin denominations
   * @param coinsRemaining an array of the available coins for each denomination
   * @param value          the target value to make change for
   * @return the minimum number of coins required to make change for the given value
   */

  public static int minCoins(int[] denominations, int[] coinsRemaining, int value) {
    if (value == 0) {
      return 0;
    }
    if (value < 0) {
      return -1;
    }
    int minCoins = Integer.MAX_VALUE;
    boolean found = false;
    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] > 0) {
        int[] copiedCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        copiedCoinsRemaining[i] = copiedCoinsRemaining[i] - 1;
        int copiedValue = value - denominations[i];
        int subResult = minCoins(denominations, copiedCoinsRemaining, copiedValue);
        if (subResult != -1) {
          minCoins = Math.min(minCoins, subResult + 1);
          found = true;
        }
      }
    }
    return found ? minCoins : -1;
  }

  /**
   * Returns the optimal solution to make change for the given value using the
   * specified denominations and the available coins.
   *
   * @param denominations  an array of the coin denominations
   * @param coinsRemaining an array of the available coins for each denomination
   * @param value          the target value to make change for
   * @return an array containing the number of coins used for each denomination
   *         to make change for the given value, or null if no solution exists
   */
  public static int[] makeChange(int[] denominations, int[] coinsRemaining, int value) {
    if (value == 0) {
      return new int[denominations.length];
    }
    if (value < 0) {
      return null;
    }

    int minCoins = Integer.MAX_VALUE;
    int[] bestSolution = null;

    for (int i = 0; i < denominations.length; i++) {
      if (coinsRemaining[i] > 0) {
        int[] copiedCoinsRemaining = Arrays.copyOf(coinsRemaining, coinsRemaining.length);
        copiedCoinsRemaining[i]--;

        int copiedValue = value - denominations[i];
        int[] subSolution = makeChange(denominations, copiedCoinsRemaining, copiedValue);

        if (subSolution != null) {
          subSolution[i]++;
          int sum = 0;
          for (int coin : subSolution) {
            sum += coin;
          }
          int subResult = sum;

          if (subResult < minCoins) {
            minCoins = subResult;
            bestSolution = subSolution;
          }
        }
      }
    }

    return bestSolution;
  }

}
