import java.util.Calendar;
import java.util.Iterator;
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
 * Several tester for all classes to check whether they are working properly. Unluckily, the last tester still return false when I turn in this assignment in last minute.
 * 
 * @author Zihan Xu
 *
 */
public class TwiteratorTester {


  /**
   * Test for User class working properly.
   * 
   * @return
   */
  public static boolean testUser() {
    //Initialize two new user objects
    User userVerified = new User("uwmadison");
    User userNotVerified = new User("dril");
    
    // Test constructor for invalid user names
    {
      try {
        User userStar = new User("Star*");
        return false;
      } catch (IllegalArgumentException e) {
        System.out.println("Invalid userName!");
      }
    }
    
    //Test toString() method with 
    {
      userVerified.verify();
      String expectedVerified = "@uwmadison*";
      String expectedNotVerified = "@dril";
      if(!expectedVerified.equals(userVerified.toString()) || !expectedNotVerified.equals(expectedNotVerified.toString())) {
        return false;
      }
    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * Test Tweet class and check whether this class working properly
   * 
   * @return - true if all cases pass
   * 
   */
  public static boolean testTweet() {
    //Initialize a full twitter message
    User user = new User("dril");
    String tweetText = "Don't want to use the given text cause that is too long.";
    //Initialize the calendar
    Calendar testCalendar = Calendar.getInstance();
    testCalendar.set(2012, Calendar.MAY, 22, 14, 46, 03);
    Tweet.setCalendar(testCalendar);
    TwitterFeed feed = new TwitterFeed();
    Tweet tweet = new Tweet(user, tweetText);
    //Add some like and retweet
    tweet.like();
    tweet.retweet();
    
    //check for invalid user
    {
      try {
        User nullUser = new User("");
        return false;
      }catch(IllegalArgumentException e) {
        
      }
    }
    
    //Test for total Engagement
    {
      int expectedForEngagement = 2;
      int actualTotalEngagement = tweet.getTotalEngagement();
      //System.out.println(tweet.getTotalEngagement());
      if(!(actualTotalEngagement == expectedForEngagement)) {
        return false;
      }
    }
    
    //Test for get text
    {
      String expectedText = "Don't want to use the given text cause that is too long.";
      String actualText = tweet.getText();
      //System.out.println(actualText);
      if(!expectedText.equals(actualText)) {
        return false;
      }
    }
    
    //Test for equals
    {
      if(!tweet.getText().equals(tweetText)) {
        return false;
      }
    }
    
    //Test for likes ratio
    {
      double expectedRatio = 0.5;
      double actualRatio = tweet.getLikesRatio();
      //System.out.println(actualRatio);
      if(!(expectedRatio == actualRatio)) {
        return false;
      }
    }
    
    //Test for to String
    {
      String expectedString = "tweet from @dril at Tue May 22 14:46:03 CDT 2012:\n" + "-- Don't want to use the given text cause that is too long.\n" + "-- likes: 1\n" + "-- retweets: 1";
      String actualString =  tweet.toString();
      //System.out.println(expectedString);
      //System.out.println(actualString);
      if(!expectedString.equals(actualString)) {
        return false;
      }
    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * Test whether TweetNode class working properly
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testNode() {
    //Initialize the calendar
    Calendar testNodeCalendar = Calendar.getInstance();
    Tweet.setCalendar(testNodeCalendar);
    
    //Create some user instance || create some twitter message
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    //Create some Tweet instance filled with text from given pdf
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    //Create TweetNode instance
    TweetNode node1 = new TweetNode(t1);
    TweetNode node2 = new TweetNode(t2);
    
    //Test the setNext() and getTweet() methods
    {
      node1.setNext(node2);
      if(!node1.getTweet().equals(t1) || !node2.getTweet().equals(t2)) {
        return false;
      }
    }
    
    // Test the two-argument constructor
    TweetNode node3 = new TweetNode(t1, node2);
    {
      if (!node3.getTweet().equals(t1) || node3.getNext() != node2) {
          return false;
      }
    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * test Addtweet working properly
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testAddTweet() {
    //Initialize the calendar
    Calendar testAddCalendar = Calendar.getInstance();
    Tweet.setCalendar(testAddCalendar);
    
    //test for create a TwitterFeed and verify that it is empty and has size 0
    TwitterFeed feedAddTweet = new TwitterFeed();
    {
      if(!feedAddTweet.isEmpty() || feedAddTweet.size() != 0) {
        return false;
      }
    }
    
    //Create the user instance
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    //Create some Tweet instance filled with text from given pdf
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    
    //Test Verify that it is no longer empty, has size 1, contains() the Tweet you just added, and that get(0) matches that Tweet.
    feedAddTweet.addFirst(t1);
    {
      if(feedAddTweet.isEmpty() || feedAddTweet.size() != 1 || !feedAddTweet.contains(t1) || !feedAddTweet.get(0).equals(t1)) {
        return false;
      }
    }
    
    //Test if feed is not empty, has size 2, contains t2, and get(1) matches t2
    feedAddTweet.addLast(t2);
    {
      if(feedAddTweet.isEmpty() || feedAddTweet.size() != 2 || !feedAddTweet.contains(t2) || !feedAddTweet.get(1).equals(t2)) {
        return false;
      }
    }
    
    //Test if getHead() and getTail() return the correct values
    {
      if(!feedAddTweet.getHead().equals(t1) || !feedAddTweet.getTail().equals(t2)) {
        return false;
      }
    }
    
    //If all tests passes, return true
    return true;
  }
  
  /**
   * Check whether insert function working properly
   * 
   * @return
   */
  public static boolean testInsertTweet() {
    //Initialize the calendar
    TwitterFeed feedInsertTweet = new TwitterFeed();
    Calendar testInsertCalendar = Calendar.getInstance();
    Tweet.setCalendar(testInsertCalendar);
    //Create the user instance
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    User u3 = new User("Zihan");
    //Create some Tweet instance filled with text from given pdf
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    Tweet t3 = new Tweet(u3, "FutureBadger!");
    
    //Test inserting a Tweet at the beginning of the feed
    feedInsertTweet.add(0, t1);
    {
      if(feedInsertTweet.size() != 1 || !feedInsertTweet.get(0).equals(t1)) {
        return false;
      }
    }
    
    //Test inserting a Tweet at the end of the feed
    feedInsertTweet.add(1, t2);
    {
      if(feedInsertTweet.size() != 2 || !feedInsertTweet.get(1).equals(t2)) {
        return false;
      }
    }
    
    //Test inserting a tweet in the middle of the feed
    feedInsertTweet.add(1, t3);
    {
      if(feedInsertTweet.size() != 3 || !feedInsertTweet.get(1).equals(t3) || !feedInsertTweet.get(2).equals(t2)) {
        return false;
      }
    }
    
    //Test getHead() and getTail()
    {
      if(!feedInsertTweet.getHead().equals(t1) || !feedInsertTweet.getTail().equals(t2)) {
        return false;
      }
    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * Check whether delete function working properly
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testDeleteTweet() {
    //Initialize the calendar
    TwitterFeed feedDelete = new TwitterFeed();
    Calendar testDeleteCalendar = Calendar.getInstance();
    Tweet.setCalendar(testDeleteCalendar);
    //Create the user instance
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    User u3 = new User("Zihan");
    User u4 = new User("Xu");
    User u5 = new User("zihanxu");
    //Create some Tweet instance filled with text from given pdf
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    Tweet t3 = new Tweet(u3, "FutureBadger!");
    Tweet t4 = new Tweet(u4, "I dont want to take math class anymore");
    Tweet t5 = new Tweet(u5, "Fxxk Calculas!");
    
    //Add tweets
    feedDelete.addFirst(t1);
    feedDelete.addLast(t2);
    feedDelete.addLast(t3);
    feedDelete.addLast(t4);
    feedDelete.addLast(t5);
    
    //Test deleting the last one
    //System.out.println(feedDelete.size());
    feedDelete.delete(feedDelete.size() - 1);
    //System.out.println(feedDelete.size());
    {
      if(feedDelete.size() != 4 || !feedDelete.getTail().equals(t4)) {
        //System.out.println(feedDelete.size());
        //System.out.println(feedDelete.getTail());
        return false;
      }
    }
    
    //Test deleting the first one
    feedDelete.delete(0);
    {
      if(feedDelete.size() != 3 || !feedDelete.getHead().equals(t2)) {
        //System.out.println(feedDelete.size());
        //System.out.println(feedDelete.getHead());
        return false;
      }
    }
    
    //Test deleting from middle
    feedDelete.delete(1);
    {
      if(feedDelete.size() != 2 || !feedDelete.get(1).equals(t4)) {
        //System.out.println(feedDelete.size());
        //System.out.println(feedDelete.get(1));
        return false;
      }
    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * Check whether ChronoTwiterator working properly
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testChronoTwiterator() {
    //Initialize the calendar
    TwitterFeed feedChrono = new TwitterFeed();
    Calendar testChronoCalendar = Calendar.getInstance();
    Tweet.setCalendar(testChronoCalendar);
    // Create User instances 
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    User u3 = new User("Zihan");
    // Create Tweet instances
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    Tweet t3 = new Tweet(u3, "FutureBadger!");
    // Add tweets
    feedChrono.addFirst(t1);
    feedChrono.addFirst(t2);
    feedChrono.addFirst(t3);
    
    // Iterate through the TwitterFeed using an enhanced-for loop
    int index = 0;
    for (Tweet tweet : feedChrono) {
        // Check if the iteration order matches the expected order
        if (index == 0 && !tweet.equals(t3)) {
            return false;
        } else if (index == 1 && !tweet.equals(t2)) {
            return false;
        } else if (index == 2 && !tweet.equals(t1)) {
            return false;
        }
        index++;
    }

    // Check if all tweets were iterated
    if (index != 3) {
        return false;
    }
    
    //If all cases passes, return true
    return true;
    
    
  }
  
  /**
   * test whether VerifiedTwiterator working properly
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testVerifiedTwiterator() {
    //Initialize the calendar
    TwitterFeed feedVerified = new TwitterFeed();
    Calendar testVerifiedCalendar = Calendar.getInstance();
    Tweet.setCalendar(testVerifiedCalendar);
    // Create User instances 
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    User u3 = new User("Zihan");
    //make u1 verify for setMode
    u1.verify();
    // Create Tweet instances
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    Tweet t3 = new Tweet(u3, "FutureBadger!");
    // Add tweets
    feedVerified.addFirst(t1);
    feedVerified.addFirst(t2);
    feedVerified.addFirst(t3);
    //setmode to verified only and check its index
    feedVerified.setMode(TimelineMode.VERIFIED_ONLY);
    
    {
      // Iterate through the TwitterFeed using an enhanced-for loop
      int index = 0;
      for (Tweet tweet : feedVerified) {
          if (index == 0 && !tweet.equals(t1)) {
              return false;
          }
          index++;
      }
      //System.out.println(index);
      // Check if only verified tweets were iterated
      if (index != 1) {
          return false;
      }

    }
    
    //If all cases passes, return true
    return true;
  }
  
  /**
   * check whether Ratio Twiterator working properply
   * 
   * @return true if all cases pass
   * 
   */
  public static boolean testRatioTwiterator() {
    //Initialize the calendar
    TwitterFeed feedRatio = new TwitterFeed();
    Calendar testRatioCalendar = Calendar.getInstance();
    Tweet.setCalendar(testRatioCalendar);
    //Create User instances 
    User u1 = new User("uwmadison");
    User u2 = new User("dril");
    User u3 = new User("Zihan");
    // Create Tweet instances
    Tweet t1 = new Tweet(u1, "Join us for Bucky's Big Event next Wednesday!");
    Tweet t2 = new Tweet(u2, "Welcome to the University of Wisconsin-Madison");
    Tweet t3 = new Tweet(u3, "FutureBadger!");
    //make some likes and retweets
    t1.like();
    t1.like();
    t1.retweet();
    t2.like();
    t3.like();
    t3.like();
    t3.retweet();
    // Add tweets to the TwitterFeed
    feedRatio.addFirst(t1);
    feedRatio.addFirst(t2);
    feedRatio.addFirst(t3);
    // Set the threshold for the like ratio
    double threshold = 0.5;
    // Set the timeline mode to LIKE_RATIO
    feedRatio.setMode(TimelineMode.LIKE_RATIO);
    
    {
      // Initialize an index variable for tracking the iteration order
      int index = 0;
      Iterator<Tweet> ratioIterator = feedRatio.iterator();
      while (ratioIterator.hasNext()) {
          Tweet tweet = ratioIterator.next();
          // Check if the iteration order matches the expected order based on the like ratio
          if (index == 0 && !tweet.equals(t3)) {
              return false;
          } else if (index == 1 && !tweet.equals(t1)) {
              return false;
          }
          index++;
      }
      // Check if only tweets with the required likes ratio were iterated
      if (index != 2) {
          return false;
      }

    }
    
    //true if all cases pass
    return true;
}

  
  
  /**
   * Call the multiple testers
   * 
   * @param args
   * 
   */
  public static void main(String[] args) {
    
    System.out.println("testUser " + testUser());
    System.out.println("testTweet " + testTweet());
    
    System.out.println("testNode: " + testNode());
    System.out.println("testAddTweet: " + testAddTweet());
    System.out.println("testInsertTweet: " + testInsertTweet());
    System.out.println("testDeleteTweet: " + testDeleteTweet());
    
    System.out.println("testChronoTwiterator: " + testChronoTwiterator());
    System.out.println("testVerifiedTwiterator: " + testVerifiedTwiterator());
    //For last tester, It will still output fail even at the last minute before ddl
    System.out.println("testRatioTwiterator: " + testRatioTwiterator());
    
    
  }
}
