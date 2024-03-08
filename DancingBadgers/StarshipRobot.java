// File header comes here
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    (descriptive title of the program making use of this file)
// Course:   CS 300 Spring 2023
//
// Author:   Zihan Xu
// Email:    zxu536@wisc.edu
// Lecturer: (Mouna Kacem)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    no
// Partner Email:  no
// Partner Lecturer's Name: no
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:   no
// Online Sources:  https://openai.com/blog/chatgpt/ & IDEA IDE
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * This class models Starship Robot objects delivering food to badger fans
 * 
 * @author xuzihan
 *
 */
public class StarshipRobot {
  private static PApplet processing; //PApplet object representing the display window where this StarshipRobot is going to be drawn.
  private int speed; //movement speed of this StarshipRobot
  private PImage image; //image of this StarshipRobot of type PImage
  private float x; //x-position of this StarshipRobot in the display window
  private float y; //y-position of this StarshipRobot in the display window
  private Thing source; //source point of this StarshipRobot at its current journey delivering food to badgers
  private Thing destination; //destination point of this StarshipRobot at its current journey delivering food to badgers
  
  
  /**
   * Creates a new StarshipRobot and sets its source, destination, and speed.
   * 
   * @param source Thing object representing the start point of this StarshipRobot
   * @param destination Thing object representing the destination point of this StarshipRobot
   * @param speed movement speed of this StarshipRobot
   */
  public StarshipRobot(Thing source, Thing destination, int speed) {
     image = processing.loadImage("images" + File.separator + "starshipRobot.png");
     this.speed = speed;
     this.source = source;
     this.destination = destination;
     x = source.getX();
     y = source.getY();
  }
  
  /**
   * Returns a reference to the image of this starship robot
   * 
   * @return the image of type PImage of the starship robot object
   */
  public processing.core.PImage image() {
    return image;
  }
  
  /**
   * Returns the x-position of this starship robot in the display window
   * 
   * @return the X coordinate of the starship robot position
   */
  public float getX() {
    return x;
  }
  
  /**
   * Returns the y-position of this starship robot in the display window
   * 
   * @return the y-position of the starship robot
   */
  public float getY() {
    return y;
  }
  
  /**
   * Sets the x-position of this starship robot in the display window
   * 
   * @param x x - the x-position to set
   */
  public void setX(float x) {
    x = getX();
  }
  
  /**
   * Sets the y-position of this starship robot in the display window
   * 
   * @param y y - the y-position to set
   */
  public void setY(float y) {
    y = getY();
  }
  
  /**
   * Sets the PApplet object display window where this StarshipRobot will be drawn. 
   * The processing PApplet data field is set to Badger.processing since Badger and StarshipRobot objects are going to be displayed to the same screen.
   */
  public static void setProcessing() {
    processing = Badger.getProcessing();
  }
  
  /**
   * Draws this StarshipRobot to the display window while it is in action delivering food
   * 
   */
  public void draw() {
    processing.image(image, x, y);
    this.go();
  }
  
  /**
   * Sets the PApplet object display window where this StarshipRobot will be drawn. The processing PApplet data field is 
   * set to Badger.processing since Badger and StarshipRobot objects are going to be displayed to the same screen.
   * 
   */
  private void moveTowardsDestination() {
    float dx = destination.getX() - this.x;
    float dy = destination.getY() - this.y;
    float distance = (float) Math.sqrt(dx*dx + dy*dy);

    float newX = this.x + speed * dx / distance;
    float newY = this.y + speed * dy / distance;
    this.x = (float) newX;
    this.y = (float) newY;
  }
  
  /**
   * Checks whether this StarshipRobot is over a specific Thing
   * 
   * @param thing a given Thing object
   * @return true if this StarshipRobot is over the Thing object passed as input, otherwise, returns false.
   */
  public boolean isOver(Thing thing) {
    float currentLeftBotX = this.x - this.image().width/2;
    float currentLeftBotY = this.y - this.image().height/2;
    float currentRightTopX = this.x + this.image().width/2;
    float currentRightTopY = this.y + this.image().height/2;
    
    float targetLeftBotX = thing.getX() - thing.image().width/2;
    float targetLeftBotY = thing.getY() - thing.image().height/2;
    float targetRightTopX = thing.getX() + thing.image().width/2;
    float targetRightTopY = thing.getY() + thing.image().height/2;
    
    if(targetLeftBotX > currentRightTopX || targetLeftBotY > currentRightTopY || targetRightTopX < currentLeftBotX || targetRightTopY < currentLeftBotY) {
      return false;
    }else {
      return true;
    }
    
}

    
  /**
   * Implements the behavior of this StarshipRobot going back-and-forth between its source and destination.
   * 
   */
  public void go() {
    if (this.isOver(destination)) {
        // if the robot is over the destination, switch source and destination
        Thing temp = source;
        source = destination;
        destination = temp;
        
    } else {
        // if the robot is not over the destination, move one step towards it
        this.moveTowardsDestination();
    }
}


}
