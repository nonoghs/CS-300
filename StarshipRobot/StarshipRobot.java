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
// Partner Name: Zihan Xu
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
 * The StarshipRobot class is a subclass of the MovingThing class that represents a robot delivering food to badgers. 
 * It has a source and a destination point, and when created, a StarshipRobot object must face its destination.
 * 
 * @author xuzihan
 *
 */
public class StarshipRobot extends MovingThing {
  
  //source point of this StarshipRobot at its current journey delivering food to badgers
  private Thing source;
  //destination point of this StarshipRobot at its current journey delivering food to badgers
  private Thing destination;

  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed. The start point of this this StarshipRobot is set to the (x,y) position of source. 
   * When created, a StarshipRobot object must face its destination.
   * 
   * @param source - Thing object representing the start point of this StarshipRobot
   * @param destination - Thing object representing the destination point of this StarshipRobot
   * @param speed - movement speed of this StarshipRobot
   * 
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
    // source: Thing objet referring to the start point of this StarshipRobot
    // destination: Thing object referring to the destination point of this StarshipRobot
    // speed: movement speed of this StarshipRobot.
    super(source.getX(), source.getY(), speed, "starshipRobot.png");
    this.source = source;
    this.destination = destination;
    this.speed = speed;
    this.isFacingRight = source.getX() < destination.getX();
  }
  /**
   * Draws this StarshipRobot to the display window while it is in motion delivering food.
   * This method first prompts this StarshipRobot to go. Then, it draws it to the display window.
   * Think of partial overriding to draw this StarshipRobot as its image is not directly accessed from here.
   * 
   */
  @Override
  public void draw(){
    go();
    super.draw();
  }

  /**
   * check the position of starship and destination whether they overlap
   *
   * @param t - a given Thing object
   * @return whether starship is over desitination
   * 
   */
  public boolean isOver(Thing thing) {
    // (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2)
    float x1 = x - this.image().width / 2;
    float x2 = x + this.image().width / 2;
    float y1 = y - this.image().height / 2;
    float y2 = y + this.image().height / 2;

    float x3 = thing.getX() - thing.image().width / 2;
    float x4 = thing.getX() + thing.image().width / 2;
    float y3 = thing.getY() - thing.image().height / 2;
    float y4 = thing.getY() + thing.image().height / 2;

    return (x1 < x4) && (x3 < x2) && (y1 < y4) && (y3 < y2);
  }
  
  /**
   * make the starship move towards destination
   * 
   */
  private void moveTowardsDestination() {
    float dx = destination.getX() - this.x;
    float dy = destination.getY() - this.y;
    int distance = (int) Math.sqrt(dx * dx + dy * dy);

    if (distance == 0) {
      return;
    }

    double newX = this.x + speed * dx / distance;
    double newY = y + speed * dy / distance;
    if (speed > distance) {
      x = destination.getX();
      y = destination.getY();
    } else {
      x = (float) newX;
      y = (float) newY;
    }
  }

  /**
   * Move the StarshipRobot ONE step towards its destination if destination is reached (meaning the
   * StarshipRobot is over its destination), switch source and destination
   * 
   */
  public void go() {
    if (isOver(destination)) {
      Thing temp = source;
      source = destination;
      destination = temp;
      isFacingRight = !isFacingRight;
    } else {
      this.moveTowardsDestination();
    }
  }

}
