import java.util.NoSuchElementException;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    Password tree
//Course:   CS 300 Spring 2023
//
//Author:   Zihan Xu
//Email:    zxu536@wisc.edu
//Lecturer: Mouna Kacem 
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:  no
//Partner Email:  no
//Partner Lecturer's Name:
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__Write-up states that pair programming is allowed for this assignment.
//__We have both read and understand the course Pair Programming Policy.
//__We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
///////////////////////////////////////////////////////////////////////////////
public class PasswordStorage {

  protected PasswordNode root; //the root of this BST that contains passwords
  private int size; //how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; //what password information to use to determine order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.COMPARISON_CRITERIA = comparisonCriteria;
    this.size = 0;
    this.root = null;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * @return the Attribute that is being used to make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return this.COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * @return the size of this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines whether or not this tree is empty.
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    if(this.root == null) {
      return true;
    }
    return false;
  }

  /**
   * Provides in-order String representation of this BST, with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * @return this BST as a string
   */
  @Override
  public String toString() {
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to create the string representation of this tree.
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
    // This method will be implemented recursively

    // Base case: if the currentNode is null, return an empty string
    if (currentNode == null) {
      return "";
    }

    // Get the string representation of the left subtree
    String leftSubtreeString = toStringHelper(currentNode.getLeft());

    // Get the password of the current node
    String currentNodePassword = currentNode.getPassword().toString();

    // Get the string representation of the right subtree
    String rightSubtreeString = toStringHelper(currentNode.getRight());

    // Combine the left subtree string, current node password, and right subtree string
    String result = leftSubtreeString + currentNodePassword + "\n" + rightSubtreeString;

    // Return the combined string
    return result;
  }


  /**
   * Determines whether or not this tree is actually a valid BST.
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
  }

  /**
   * Recurisvely determines if the the tree rooted at the current node is a valid BST. That is, every
   * value to the left of currentNode is "less than" the value in currentNode and every value to the
   * right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound, the smallest possible password
   * @param upperBound, the largest possible password
   * @return true if the tree rooted at currentNode is a BST, false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound, Password upperBound) {
    // This method must be implemented recursively

    // Base Case 1: If the tree rooted at currentNode is empty, it does not violate any BST rules
    if (currentNode == null) {
      return true;
    }

    // Get the current node's password
    Password currentPassword = currentNode.getPassword();

    // Base Case 2: If the current password is outside the lower or upper bound for this subtree,
    // it violates the rules for a valid BST
    if (currentPassword.compareTo(lowerBound, this.COMPARISON_CRITERIA) <= 0 ||
        currentPassword.compareTo(upperBound, this.COMPARISON_CRITERIA) >= 0) {
      return false;
    }

    // If we do not have a base case situation, we must use recursion to verify currentNode's child subtrees

    // Recursive Case 1: Check that the left subtree contains only passwords greater than lowerBound
    // and less than currentNode's password
    boolean isLeftSubtreeValid = isValidBSTHelper(currentNode.getLeft(), lowerBound, currentPassword);

    // Recursive Case 2: Check that the right subtree contains only passwords greater than
    // currentNode's password and less than upperBound
    boolean isRightSubtreeValid = isValidBSTHelper(currentNode.getRight(), currentPassword, upperBound);

    // Combine Recursive Case Answers: This is a valid BST if and only if both case 1 and 2 are valid
    return isLeftSubtreeValid && isRightSubtreeValid;
  }


  /**
   * Returns the password that matches the criteria of the provided key.
   * Ex. if the COMPARISON CRITERIA is OCCURRENCE and the key has an occurrence of 10
   * it will return the password stored in the tree with occurrence of 10
   * @param key, the password that contains the information for the password we are searching for
   * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  } 

  /**
   * Recursive helper method to find the matching password in this BST
   * @param key, password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, if it does not exist in the tree it this will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
    //THIS MUST BE IMPLEMENTED RECURSIVELY
    if(currentNode == null) {
      return null;
    }
    int comparison = key.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA);
    
    if(comparison == 0) {
      return currentNode.getPassword();
    }else if(comparison > 0){
      return lookupHelper(key, currentNode.getRight());
    }else {
      return lookupHelper(key, currentNode.getLeft());
    }
    
  }

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() throws NoSuchElementException{
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    PasswordNode currentNode =root;
    while (currentNode.getRight() != null) {
      currentNode = currentNode.getRight();
    }
    return currentNode.getPassword();
  }

  /**Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() throws NoSuchElementException{
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    PasswordNode currentNode = root;
    while(currentNode.getLeft() != null) {
      currentNode = currentNode.getLeft();
    }
    return currentNode.getPassword();
  }

  /**
   * Adds the Password to this BST.
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  public void addPassword(Password toAdd) {
    if (isEmpty()) {
        this.root = new PasswordNode(toAdd);
        size++;
    } else {
        if (addPasswordHelper(toAdd, root)) {
            size++;
        } else {
            throw new IllegalArgumentException();
        }
    }
  }


  /**
   * Recursive helper that traverses the tree and adds the password where it belongs
   * @param toAdd, the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
    int comparison = toAdd.compareTo(currentNode.getPassword(), this.COMPARISON_CRITERIA);

    // If the new password is smaller than the current node's password
    if (comparison < 0) {
        if (currentNode.getLeft() == null) {
            currentNode.setLeft(new PasswordNode(toAdd));
            return true;
        }
        return addPasswordHelper(toAdd, currentNode.getLeft());
    }

    // If the new password is larger than the current node's password
    if (comparison > 0) {
        if (currentNode.getRight() == null) {
            currentNode.setRight(new PasswordNode(toAdd));
            return true;
        }
        return addPasswordHelper(toAdd, currentNode.getRight());
    }

    // If the new password is equal to the current node's password (comparison == 0), do not add it
    return false;
  }


  /**
   * Removes the matching password from the tree
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
    if(isEmpty()) {
      throw new NoSuchElementException();
    }
    
    int origSize = size;
    root = removePasswordHelper(toRemove, root);
    
    if(origSize == size) {
      throw new NoSuchElementException();
    }
  }

  /**
   * Recursive helper method to that removes the password from this BST.
   * @param toRemove, the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of this subtree now that toRemove
   * has been removed. This may still be currentNode, or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
    if (currentNode == null) {
        return null;
    }

    int comparison = toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA);
    //if the password to remove is smaller than the current node's password, search in the left subtree
    if (comparison < 0) {
        currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
    } 
    // If the password to remove is larger than the current node's password, search in the right subtree
    else if (comparison > 0) { 
        currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
    } 
    // If the password to remove is equal to the current node's password, remove it
    else {
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            currentNode = null;
        } else if (currentNode.getLeft() == null) {
            currentNode = currentNode.getRight();
        } else if (currentNode.getRight() == null) {
            currentNode = currentNode.getLeft();
        } else {
            PasswordNode newNode = findPredecessor(currentNode);
            Password predecessorPassword = newNode.getPassword();
            currentNode = newNode;
            currentNode.setLeft(removePasswordHelper(predecessorPassword, currentNode.getLeft()));
        }
        size--;
    }

    return currentNode;
  }

  /**
   * A helper method to find the predecessor Node
   * 
   * @param currentNode
   * @return currentNode
   */
  private PasswordNode findPredecessor(PasswordNode currentNode) {
    currentNode = currentNode.getLeft();
    while(currentNode.getRight() != null) {
      currentNode = currentNode.getRight();
    }
    return currentNode;
  }
}
