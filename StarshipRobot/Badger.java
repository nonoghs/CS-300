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
 * This Java class defines a Badger object that extends the MovingThing class and implements the 
 * Clickable interface. It includes properties and methods for dragging the Badger object and performing 
 * a dance sequence.
 * 
 * @author Zihan Xu
 *
 */
public class Badger extends MovingThing implements Clickable{
  
  //indicates whether this badger is being dragged or not
  private boolean isDragging;
  //indicates whether this badger is dancing or not
  private boolean isDancing;
  
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse
  
  //array storing this Badger's dance show steps
  private DanceStep[] danceSteps;
  //index position of the current dance step of this badger
  private int stepIndex;
  /*
   * stores the next dance (x, y) position of this Badger.
     nextDancePosition[0]: x-position
     nextDancePosition[1]: y-position
   */
  private float[] nextDancePosition;

  /**
   * Creates a new Badger object positioned at a specific position of the display window and whose moving speed is 2.
   * When created, a new badger is not dragging and is not dancing.
   * This constructor also sets the danceSteps of the created Badger to the one provided as input and initializes stepIndex to 1.
   * 
   * @param x - x-position of this Badger object within the display window
   * @param y - y-position of this Badger object within the display window
   * @param danceSteps - perfect-size array storing the dance steps of this badger
   */
  public Badger(float x, float y, DanceStep[] danceSteps) {
    super(x, y, 2, "badger.png");
    this.danceSteps = danceSteps;
    this.stepIndex = 1;
    this.isDragging = false;
    this.isDancing = false;
    this.nextDancePosition = new float[2];
  }
  
  /**
   * Draws this badger to the display window. Also, this method:
   * - calls the drag() behavior if this Badger is dragging
   * - calls the dance() behavior if this Badger is dancing
   * To draw the badger to the screen, think of using partial overriding (super.draw()) as the image of the Badger is not directly visible here.
   */
  @Override
  public void draw() {
    if(isDragging) {
      drag();
    }
    if(isDancing) {
      dance();
    }
    super.draw();
  }
  
  /**
   * Checks whether this badger is being dragged
   * 
   * @return true if the badger is being dragged, false otherwise
   */
  public boolean isDragging() {
    return isDragging;
  }

  /**
   * Helper method to drag this Badger object to follow the mouse moves
   * 
   */
  public void drag() {
    int dx = processing.mouseX - oldMouseX;
    int dy = processing.mouseY - oldMouseY;
    x += dx;
    y += dy;

    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
  }
  
  /**
   * Starts dragging this badger
   *
   */
  public void startDragging() {
    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;
    this.isDragging = true;
    drag();
  }

  /**
   * Stops dragging this Badger object
   *
   */
  public void stopDragging() {
    this.isDragging = false;
  }



  /**
   * Callback method called each time the user presses the mouse
   * 
   */
  public void mousePressed() {
    if(this.isMouseOver()&& !isDancing) {
      startDragging();
    }
  }

  /**
   * Defines the behavior of this Badger when the mouse is released. If the mouse is released, this badger stops dragging.
   * 
   */
  public void mouseReleased() {
    stopDragging();
  }

  /**
   * This helper method moves this badger one speed towards its nextDancePosition. Then, it checks whether this Badger is facing right and updates the isFacingRight data field accordingly.
   * After making one move dance, a badger is facing right if the x-move towards its next dance position is positive, otherwise, it is facing left.
   * 
   * @return - true if this Badger almost reached its next dance position, meaning that the distance to its next dance position is less than 2 times its speed. Otherwise, return false.
   * 
   */
  private boolean makeMoveDance() {
    // Move the Badger one speed towards its nextDancePosition
    float deltaX = nextDancePosition[0] - x;
    float deltaY = nextDancePosition[1] - y;
    float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);

    float newX = x + speed * deltaX / distance;
    float newY = y + speed * deltaY / distance;

    this.setX(newX);
    this.setY(newY);

    // Update isFacingRight according to the x-move direction
    isFacingRight = deltaX > 0;

    // Check if the Badger is close enough to the next dance position
    float newDeltaX = nextDancePosition[0] - newX;
    float newDeltaY = nextDancePosition[1] - newY;
    float newDistance = (float) Math.sqrt(newDeltaX * newDeltaX + newDeltaY * newDeltaY);

    return newDistance < 2 * speed;
  }

  /**
   * Implements the dance behavior of this Badger. This method prompts the Badger to make one move dance.
   * If the makeMoveDance method call returns true (meaning the badger almost reached its nextDancePosition), this method MUST:
   * - update its next dance position (see DanceStep.getPositionAfter()),
   * - increment the stepIndex.
   * 
   */
  private void dance() {
    if(makeMoveDance()) {
      stepIndex = (stepIndex + 1) % danceSteps.length;
      nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
    }
  }

  /**
   * Prompts this badger to start dancing. This method:
   * - updates the isDancing data field
   * - stops dragging this badger
   * - sets stepIndex to zero
   * - Resets the nextDancePosition
   * 
   */
  public void startDancing() {
    isDancing = true;
    stopDragging();
    stepIndex = 0;
    nextDancePosition = danceSteps[stepIndex].getPositionAfter(x, y);
  }

  /**
   * Prompts this badger to stop dancing. Sets the isDancing data field to false.
   */
  public void stopDancing() {
    isDancing = false;
  }

}
