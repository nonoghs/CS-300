import java.util.Iterator;
import java.util.NoSuchElementException;

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
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default, new tweets are added at the head of the list. 
 * Note that while we CALL this "reverse chronological" order, this is NOT enforced. 
 * You can add Tweets anywhere in the list relative to each other, regardless of their respective timestamps.
 * 
 * @author xuzihan
 *
 */
public class TwitterFeed extends Object implements ListADT<Tweet>, Iterable<Tweet> {
  
  //The node containing the most recent tweet
  private TweetNode head;
  //The iteration mode for the timeline display
  private TimelineMode mode;
  //The ratio of likes to retweets that we want to see; set to .5 by default
  private static double ratio = 0.5;
  //The number of tweets in this linked list
  private int size;
  //The node containing the oldest tweet
  private TweetNode tail;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default values, and the timeline mode to CHRONOLOGICAL.
   * 
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
  }
  
  /**
   * Accessor for the size of the feed
   * 
   * @return the number of TweetNodes in this TwitterFeed
   * 
   */
  public int size() {
    return size;
  }
  
  /**
   * Determines whether this feed is empty. Recommend checking MORE than just the size field to get this information, though if all methods are implemented correctly the size field's value will be sufficient.
   * 
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   * 
   */
  public boolean isEmpty() {
    if(size == 0) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's equals() method!
   * 
   * @param findObject - the Tweet to search for
   * @return true if the Tweet is present, false otherwise
   * 
   */
  public boolean contains(Tweet findObject) {
    TweetNode currentNode = head;
    while (currentNode != null) {
        if (currentNode.getTweet().equals(findObject)) {
            return true;
        }
        currentNode = currentNode.getNext();
    }
    return false;
  }


  @Override
  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   * 
   * @param - findObject - the Tweet to search for
   * @return - the index of the Tweet in the TwitterFeed if present, -1 if not
   * 
   */
  public int indexOf(Tweet findObject) {
    int index = 0;
    TweetNode currentNode = head;
    while (currentNode != null) {
        if (currentNode.getTweet().equals(findObject)) {
            return index;
        }
        currentNode = currentNode.getNext();
        index++;
    }
    return -1;
  }

  @Override
  /**
   * Accessor method for the Tweet at a given index
   * 
   * @param index - the index of the Tweet in question
   * @return - the Tweet object at that index (NOT its TweetNode!)
   * @throws - IndexOutOfBoundsException - if the index is negative or greater than the largest index of the TwitterFeed
   * 
   */
  public Tweet get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    TweetNode currentNode = head;
    for (int i = 0; i < index; i++) {
      currentNode = currentNode.getNext();
    }
    return currentNode.getTweet();
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   * 
   * @return - the Tweet object at the head of the linked list
   * @throws NoSuchElementException - if the TwitterFeed is empty
   * 
   */
  public Tweet getHead() throws NoSuchElementException {
    if (isEmpty()) {
        throw new NoSuchElementException("TwitterFeed is empty.");
    }
    return head.getTweet();
  }
  
  /**
   * Accessor method for the last Tweet in the TwitterFeed
   * 
   * @return - the Tweet object at the tail of the linked list
   * @throws NoSuchElementException - if the TwitterFeed is empty
   * 
   */
  public Tweet getTail() throws NoSuchElementException {
    if (isEmpty()) {
        throw new NoSuchElementException("TwitterFeed is empty.");
    }
    return tail.getTweet();
  }
  
  @Override
  /**
   * Adds the given Tweet to the tail of the linked list
   * 
   * @param newObject - the Tweet to add
   * 
   */
  public void addLast(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
        head = newNode;
        tail = newNode;
    } else {
        tail.setNext(newNode);
        tail = newNode;
    }
    size++;
    
  }

  @Override
  /**
   * Adds the given Tweet to the head of the linked list
   * 
   * @param newObject - the Tweet to add
   * 
   */
  public void addFirst(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
        head = newNode;
        tail = newNode;
    } else {
        newNode.setNext(head);
        head = newNode;
    }
    size++;
    
  }

  @Override
  /**
   * Adds the given Tweet to a specified position in the linked list
   * 
   * @param index - the position at which to add the new Tweet
   * @param newObject - the Tweet to add
   * @throws IndexOutOfBoundsException - if the index is negative or greater than the size of the linked list
   * 
   */
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }
    if (index == 0) {
      addFirst(newObject);
    } else if (index == size) {
      addLast(newObject);
    } else {
      TweetNode newNode = new TweetNode(newObject);
      TweetNode currentNode = head;
      for (int i = 1; i < index; i++) {
          currentNode = currentNode.getNext();
      }
      newNode.setNext(currentNode.getNext());
      currentNode.setNext(newNode);
      size++;
    }
    
  }

  @Override
  /**
   * Removes and returns the Tweet at the given index
   * 
   * @param index - the position of the Tweet to remove
   * @return - the Tweet object that was removed from the list
   * @throws IndexOutOfBoundsException - if the index is negative or greater than the largest index currently present in the linked list
   * 
   */
  public Tweet delete(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Invalid index");
    }
    Tweet deletedTweet;
    if (index == 0) {
        deletedTweet = head.getTweet();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
    } else {
        TweetNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        deletedTweet = current.getNext().getTweet();
        current.setNext(current.getNext().getNext());
        if (current.getNext() == null) {
            tail = current;
        }
    }
    size--;
    return deletedTweet;
}

  /**
   * Sets the iteration mode of this TwitterFeed
   * 
   * @param m - the iteration mode; any value from the TimelineMode enum
   * 
   */
  public void setMode(TimelineMode m) {
    mode = m;
  }
  
  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   * 
   * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the head of this TwitterFeed list
   * 
   */
  public Iterator<Tweet> iterator() {
    switch (mode) {
        case CHRONOLOGICAL:
            return new ChronoTwiterator(head);
        case VERIFIED_ONLY:
            return new VerifiedTwiterator(head);
        case LIKE_RATIO:
            return new RatioTwiterator(head, ratio);
        default:
            return null;
    }
  }
  
  
  
}
