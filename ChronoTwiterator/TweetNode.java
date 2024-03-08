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
 * A container for a Tweet in a singly-linked list
 * 
 * @author Zihan Xu
 *
 */
public class TweetNode extends Object{
  
  //The next TweetNode in this linked list
  private TweetNode nextTweet;
  //The tweet contained in this node
  private Tweet tweet;
  
  /**
   * Constructs a singly-linked node containing a tweet
   * 
   * @param tweet - the tweet to put in this node
   * @param next - the next tweet in the linked list
   * 
   */
  public TweetNode(Tweet tweet, TweetNode next) {
    this.tweet = tweet;
    this.nextTweet = next;
  }
  
  /**
   * Constructs a singly-linked node containing a tweet, with no successor tweet
   * 
   * @param tweet - the tweet to put in this node
   * 
   */
  public TweetNode(Tweet tweet) {
    this.tweet = tweet;
    this.nextTweet = null;
  }

  /**
   * Accesses the tweet in this node
   * 
   * @return the tweet in this node
   * 
   */
  public Tweet getTweet() {
    return this.tweet;
  }
  
  /**
   * Accesses the next TweetNode in the list
   * 
   * @return the successor TweetNode
   * 
   */
  public TweetNode getNext() {
    return this.nextTweet;
  }
  
  /**
   * Links this node to another node
   * 
   * @param next - the successor TweetNode (can be null)
   * 
   */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }

}
