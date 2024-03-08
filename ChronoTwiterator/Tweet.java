import java.util.Date;
import java.util.Calendar;

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

public class Tweet extends Object{
  //A shared Calendar object for this class to use to generate consistent dates.
  private static Calendar dateGenerator;
  //A value determining the maximum length of a tweet. Set to 280 by default.
  private static final int MAX_LENGTH = 280;
  //The User associated with this tweet
  private User user;
  //The text of this tweet
  private String text;
  //The number of likes this tweet has
  private int numLikes;
  //The number of retweets this tweet has
  private int numRetweets;
  //The date and time this tweet was posted, a Date object created by calling dateGenerator.getTime() at construction time
  private Date timestamp;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its timestamp should be set to the current time.
   * 
   * @param user - the User posting this tweet
   * @param text - the text of the tweet
   * @throws IllegalArgumentException - if the tweet's text exceeds MAX_LENGTH characters
   * @throws NullPointerException -  if the provided text or user are null
   * @throws IllegalStateException - if the dateGenerator field has not yet been initialized
   * 
   */
  public Tweet(User user, String text) throws IllegalArgumentException, NullPointerException, IllegalStateException{
    
    if(text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("Text excedes max length!");
    }
    
    if(user == null || text == null) {
      throw new NullPointerException("The user or text should not be null");
    }
    
    if(dateGenerator == null) {
      throw new IllegalStateException("You should initialize dateGenerator first");
    }
    
    numLikes = 0;
    numRetweets = 0;
    
    this.user = user;
    this.text = text;
    
    
    this.timestamp = dateGenerator.getTime();
    
    
  }
  
  /**
   * Initializes the dateGenerator static field to the provided Calendar object. For tests which do not require a consistent date, 
   * you can use Calendar.getInstance() to get a Calendar set to the current time. 
   * 
   * @param c - the Calendar to use for date generation for this run of the program
   * 
   */
  public static void setCalendar(Calendar c) {
    if(dateGenerator == null) {
      dateGenerator = c;
    }
  }
  
  /**
   * Accesses the text of this tweet
   * 
   * @return - the text of this tweet
   * 
   */
  public String getText() {
    return text;
  }
  
  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * 
   * @return - the total engagement of this tweet
   * 
   */
  public int getTotalEngagement() {
    return numLikes + numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * 
   * @return - true if this tweet's User is verified, false otherwise
   * 
   */
  public boolean isUserVerified() {
    return user.isVerified();
  }
  
  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * 
   * @return - the total engagement of this tweet
   * 
   */
  public double getLikesRatio() {
    int totalEngagement = getTotalEngagement();

    if (totalEngagement == 0) {
        return -1;
    } else {
        double likesRatio = (double) numLikes / totalEngagement;
        return likesRatio;
    }
  }
  
  /**
   * Add one (1) to the number of likes for this tweet
   * 
   */
  public void like() {
    numLikes++;
  }
  
  /**
   * Add one (1) to the number of retweets for this tweet
   * 
   */
  public void retweet() {
    numRetweets++;
  }
  
  @Override
  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet that contains the same text posted at the same time by the same User 
   * (use the toString() method from User to compare these!) 
   * then the two Tweets are considered equal regardless of their respective likes/retweets.
   * 
   * @param o - the object to compare this Tweet to
   * @return true if this Tweet is equivalent to the provided object, false otherwise
   * 
   */
  public boolean equals(Object o) {
    if(o instanceof Tweet) {
      Tweet other = (Tweet) o ;
      
      boolean usersEqual = this.user.toString().equals(other.user.toString());
      boolean textsEqual = this.text.equals(other.text);
      boolean timestampsEqual = this.timestamp.equals(other.timestamp);
      
      if (usersEqual && textsEqual && timestampsEqual) {
         return true;
       } else {
         return false;
      }
      
    } else {
      return false;
    }
    
  }
  
  @Override
  /**
   * A string representation of this tweet. See writeup for examples.
   * 
   * @return a formatted string representation of this tweet
   * 
   */
  public String toString() {
      String tweetInfo = "tweet from " + user.toString() + " at " + timestamp + ":\n";
      String tweetText = "-- " + text + "\n";
      String likesInfo = "-- likes: " + numLikes + "\n";
      String retweetsInfo = "-- retweets: " + numRetweets;

      String result = tweetInfo + tweetText + likesInfo + retweetsInfo;
      return result;
  }

}
