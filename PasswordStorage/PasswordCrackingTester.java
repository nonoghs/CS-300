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
public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the
   * protected data field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {
    try {
        PasswordStorage storage = new PasswordStorage(Attribute.OCCURRENCE);

        if (storage.getComparisonCriteria() != Attribute.OCCURRENCE) {
            throw new Exception();
        }

        if (storage.size() != 0 || !storage.isEmpty()) {
            throw new Exception();
        }

        Password p1 = new Password("TestA", 9999);
        storage.addPassword(p1);

        if (storage.size() != 1 || storage.isEmpty()) {
            throw new Exception();
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }


  /**
   * Validates the Password class compareTo() method. Create at least two DIFFERENT
   * Password objects and compare them on each of the Attribute values. See the writeup
   * for details on how the various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, false otherwise
   */
  public static boolean testPasswordCompareTo() {
    try {
        Password p1 = new Password("test", 1000);
        Password p2 = new Password("#iloveyou#", 765);

        int occurrenceCase = p1.compareTo(p2, Attribute.OCCURRENCE);
        if (occurrenceCase <= 0) {
            throw new Exception("Occurrence comparison is not greater than 0.");
        }

        int strengthRatingCase = p1.compareTo(p2, Attribute.STRENGTH_RATING);
        if (strengthRatingCase >= 0) {
            throw new Exception("Strength rating comparison is not less than 0.");
        }

        int hashedPasswordCase = p1.compareTo(p2, Attribute.HASHED_PASSWORD);
        if (hashedPasswordCase >= 0) {
            throw new Exception("Hashed password comparison is not less than 0.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }


  /**
   * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all
   * possible configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
    try {
        Password passwordA = new Password("test", 1);
        Password passwordB = new Password("iloveyou", 1);
        Password passwordC = new Password("qwerty", 1);

        PasswordNode leafNode = new PasswordNode(passwordA);
        PasswordNode parentNode = new PasswordNode(passwordB, leafNode, null);
        PasswordNode rootNode = new PasswordNode(passwordC, parentNode, null);

        if (!leafNode.isLeafNode() || parentNode.isLeafNode() || rootNode.isLeafNode()) {
            throw new Exception("Leaf status");
        }

        if (leafNode.hasLeftChild() || !parentNode.hasLeftChild() || !rootNode.hasLeftChild()) {
            throw new Exception("Left child status");
        }

        if (leafNode.hasRightChild() || parentNode.hasRightChild() || rootNode.hasRightChild()) {
            throw new Exception("Right child");
        }

        if (leafNode.numberOfChildren() != 0 || parentNode.numberOfChildren() != 1 || rootNode.numberOfChildren() != 1) {
            throw new Exception("Number of children");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }



  // GIVE TO STUDENTS
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
      + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();

      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  // GIVE TO STUDENTS
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep
      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
	  rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
        .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }


      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }


    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * This method checks if an existing password can be correctly found in the tree and if a non-existing password returns
   * a null value when looked up. The tree is manually built in this test method to ensure a specific structure for testing.
   *
   * @return true if all lookup test cases pass, false otherwise.
   * @throws Exception if any of the test cases fail, providing a description of the failed test case.
   */
  public static boolean testLookup() {
    try {
        PasswordStorage bst = new PasswordStorage(Attribute.HASHED_PASSWORD);

        // Manually building the tree
        Password p1 = new Password("1234567890", 1000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);

        PasswordNode rootNode = new PasswordNode(p1);
        PasswordNode leftNode = new PasswordNode(p2);
        PasswordNode rightNode = new PasswordNode(p3);
        PasswordNode leftLeftNode = new PasswordNode(p4);

        rootNode.setLeft(leftNode);
        rootNode.setRight(rightNode);
        leftNode.setLeft(leftLeftNode);

        bst.root = rootNode;

        // Test lookup for existing password
        Password existPassword = bst.lookup(p3);
        if (existPassword == null || !existPassword.equals(p3)) {
            throw new Exception();
        }

        // Test lookup for non-existing password
        Password nonExistPassword = new Password("顶真", 1001);
        existPassword = bst.lookup(nonExistPassword);
        if (existPassword != null) {
            throw new Exception("Non-exist case");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }
  

  /**
   * This method checks if passwords are added correctly to an empty tree, a non-empty tree, and verifies the tree
   * structure after adding multiple passwords. The method also checks if the tree size is updated
   * correctly and if the passwords can be looked up after insertion.
   *
   * @return true if all addPassword test cases pass
   * @throws Exception if any of the test cases fail
   */
  public static boolean testAddPassword() {
    try {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        // Test add password to empty tree
        Password p1 = new Password("1234567890", 15000);
        bst.addPassword(p1);
        if (bst.size() != 1 || !bst.lookup(p1).equals(p1)) {
            throw new Exception("Empty tree");
        }

        // Test add password to non-empty tree
        Password p2 = new Password("test", 500);
        bst.addPassword(p2);
        if (bst.size() != 2 || !bst.lookup(p2).equals(p2)) {
            throw new Exception("Non-empty tree");
        }
        
        // Test adding more passwords and verify the tree structure
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 300);
        Password p6 = new Password("password", 2000);

        bst.addPassword(p3);
        bst.addPassword(p4);
        bst.addPassword(p5);
        bst.addPassword(p6);

        if (bst.size() != 6 || !bst.lookup(p3).equals(p3) || !bst.lookup(p4).equals(p4) ||
            !bst.lookup(p5).equals(p5) || !bst.lookup(p6).equals(p6)) {
            throw new Exception("Multiple case");
        }


    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  /**
   * This method checks if existing passwords are removed properly, if a NoSuchElementException is thrown when attempting
   * to remove a non-existing password, if the tree size remains unchanged after attempting to remove
   * a non-existing password, and if the root password and its predecessor are correctly handled during removal.
   * This test still returns fail however it could pass the autograder. I cannot find why.
   *
   * @return true if all removePassword test cases pass, false otherwise.
   * @throws Exception if any of the test cases fail, providing a description of the failed test case.
   */
  public static boolean testRemovePassword() {
    try {
        PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

        // Adding passwords to the tree
        Password p1 = new Password("1234567890", 15000);
        Password p2 = new Password("test", 500);
        Password p3 = new Password("iloveyou", 765);
        Password p4 = new Password("qwerty", 250);
        Password p5 = new Password("admin", 300);
        Password p6 = new Password("password", 2000);

        bst.addPassword(p1);
        bst.addPassword(p2);
        bst.addPassword(p3);
        bst.addPassword(p4);
        bst.addPassword(p5);
        bst.addPassword(p6);
        
        // Test remove existing password
        bst.removePassword(p2);
        if (bst.size() != 5 || bst.lookup(p2) != null) {
            throw new Exception("Failed to remove existing password.");
        }

        // Test remove non-existing password
        Password unExist= new Password("顶真", 1001);
        try {
            bst.removePassword(unExist);
            throw new Exception("NoSuchElementException is not activated");
        } catch (NoSuchElementException e) {
            // Expected exception, do nothing
        }

        if (bst.size() != 5) {
            throw new Exception("Size is incorrect");
        }

        // Test remove root password
        bst.removePassword(p1);
        if (bst.size() != 4 || bst.lookup(p1) != null) {
            throw new Exception("Root case");
        }
        bst.addPassword(p2);
        bst.removePassword(p3);
        if (bst.size() != 4 || bst.lookup(p3) != null || bst.lookup(p2) == null) {
            throw new Exception("Predecessor case");
        }

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }

    return true;
  }

  public static void main(String[] args) {
    runAllTests();
  }

  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
