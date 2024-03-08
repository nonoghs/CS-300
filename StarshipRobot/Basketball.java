// File header comes here
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Continue working on DancingBadger game
// Course:   CS 300 Spring 2023
//
// Author:   Zhenghong Wang
// Email:    zwang2654@wisc.edu
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:  Zihan Xu
// Partner Email:  zxu536@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   no
// Online Sources:  no
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This Java class defines a Basketball object that extends the Thing class and implements the Clickable interface. 
 * The class includes properties and methods for rotating the basketball and detecting when the user clicks on it.
 * 
 * @author Zhenghong Wang
 *
 */
public class Basketball extends Thing implements Clickable {
  
  //Total number of rotations this Basketball object has made since it was created.
  private static final float rotation = (float) (Math.PI / 2); // 90 degrees or π/2 radians
  //Defines the rotation angle in radians that this Basketball object make when clicked.
  private int rotations;
  
  /**
   * Creates a new Basketball object located at (x,y) position whose image filename is "basketball.png", and sets its rotation angle to PApplet.PI/2.
   * Initially, when created, the basketball has made zero rotations.
   * 
   * @param x - x-position of this Basketball object in the display window
   * @param y - y-position of this Basketball object in the display window
   */
  public Basketball(float x, float y) {
    super(x, y, "basketball.png");
    rotations = 0;
  }

  /**
   * Draws this rotating Basketball object to the display window. The implementation details of this method is fully provided in the write-up of p05.
   * 
   */
  @Override
  public void draw() {
    processing.pushMatrix();
    processing.translate(x, y);
    processing.rotate(this.rotations * rotation);
    processing.image(image(), 0.0f, 0.0f);
    processing.popMatrix();
  }

  /**
   * This method rotates this basketball object by incrementing the number of its rotations by one.
   * 
   */
  public void rotate() {
    rotations++;
  }
  
  /**
   * Defines the behavior of this basketball when the mouse is pressed. The basketball rotates when it is clicked (the mouse is over it when pressed).
   * 
   */
  public void mousePressed() {

    if (isMouseOver()) {
      System.out.println("test");
      rotate();

    }
  }

  /**
   * Called when the mouse is released. A basketball object does nothing when the mouse is released. This is a method with an empty body.
   * 
   */
  public void mouseReleased() {
    // A basketball object does nothing when the mouse is released.
  }

}
