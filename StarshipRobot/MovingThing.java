// File header comes here
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Continue working on DancingBadger game
// Course:   CS 300 Spring 2023
//
// Author:   Zihan Xu
// Email:    zxu536@wisc.edu
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:   Zhenghong Wang
// Partner Email:  zwang2654@wisc.edu
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
 * The MovingThing class is a subclass of Thing and represents a game object that is capable of movement.
 * 
 * @author xuzihan
 *
 */
public class MovingThing extends Thing implements Comparable<MovingThing> {

  //movement speed of this MovingThing
  protected int speed;
  //indicates whether this MovingThing is facing right or not
  protected boolean isFacingRight;

  /**
   * Creates a new MovingThing and sets its speed, image file, and initial x and y position. 
   * A MovingThing object is initially facing right.
   * 
   * @param x - starting x-position of this MovingThing
   * @param y - starting y-position of this MovingThing
   * @param speed - movement speed of this MovingThing
   * @param imageFilename - filename of the image of this MovingThing, for instance "name.png"
   * 
   */
  public MovingThing(float x, float y, int speed, String imageFilename) {
    super(x, y, imageFilename);
    this.speed = speed;
    this.isFacingRight = true;
  }

  /**
   * Draws this MovingThing at its current position. The implementation details of this method is fully provided in the write-up of p05.
   * 
   */
  @Override
  public void draw() {
    // Draw this MovingThing at its current position
    processing.pushMatrix();
    processing.rotate(0.0f);
    processing.translate(getX(), getY());
    if (!isFacingRight) {
      processing.scale(-1.0f, 1.0f);
    }
    processing.image(image(), 0.0f, 0.0f);
    //processing.image(image(), -image().width / 2.0f, -image().height / 2.0f); // Adjust the position based on the image's center
    processing.popMatrix();
  }

  /**
   * Compares this object with the specified MovingThing for order, in the increasing order of their speeds.
   * 
   * @param other - the MovingThing object to be compared.
   * 
   */
  @Override
  public int compareTo(MovingThing other) {
    // Compare MovingThing objects based on their speed
    return Float.compare(this.speed, other.speed);
  }

}
