import java.util.Iterator;
import java.util.NoSuchElementException;

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
 * This is an iterator that moves through tweets in reverse chronological order by verified users only
 * 
 * @author Zihan Xu
 *
 */
public class VerifiedTwiterator extends Object implements Iterator<Tweet> {
  
    //The TweetNode containing the next tweet to be returned in the iteration
    private TweetNode next;

    /**
     * Constructs a new twiterator at the given starting node
     * 
     * @param startNode - the node to begin the iteration at
     * 
     */
    public VerifiedTwiterator(TweetNode startNode) {
        next = startNode;
        while (next != null && !next.getTweet().isUserVerified()) {
            next = next.getNext();
        }
    }

    @Override
    /**
     * Checks whether there is a next tweet to return
     * 
     * @return - true if there is a next tweet, false if the value of next is null
     * 
     */
    public boolean hasNext() {
      if(next != null) {
        return true;
      } else {
        return false;
      }
    }

    @Override
    /**
     * Returns the next tweet in the iteration if one exists, and advances next to the next tweet by a verified user
     * 
     * @throws NoSuchElementException - if there is not a next tweet to return
     * 
     */
    public Tweet next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("There is no next tweet to return.");
        }

        Tweet currentTweet = next.getTweet();
        next = next.getNext();
        while (next != null && !next.getTweet().isUserVerified()) {
            next = next.getNext();
        }
        return currentTweet;
    }
}
