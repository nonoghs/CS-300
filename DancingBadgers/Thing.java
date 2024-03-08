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
import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
/**
 * This class models a graphic Thing which can be drawn at a give (x,y) position within the display window of a graphic application.
 * 
 * @author Zihan Xu
 *
 */
public class Thing {
  private static PApplet processing; // PApplet object that represents the display window

  private PImage image; // badger's image

  private float x; // badger's x-position in the display window
  private float y; // badger's y-position in the display window
  
  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x x-coordinate of this thing in the display window
   * @param y y-coordinate of this thing in the display window
   * @param imageFileName filename of the image of this thing, for instance "name.png"
   */
  public Thing(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    this.image = processing.loadImage("images" + File.separator + imageFileName);
  }
  
  /**
   * Draws this thing to the display window at its current (x,y) position
   * 
   */
  public void draw() {
    processing.image(this.image, x, y);
  }
  
  /**
   * Sets the PApplet object display window where this Thing will be drawn. 
   * The processing PApplet static data field should be set to Badger.getProcessing() since Badgers 
   * and Thing objects are going to be displayed to the same screen.
   * 
   */
  public static void setProcessing() {
    processing = Badger.getProcessing();
  }
  
  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   */
  public processing.core.PImage image() {
    return this.image;
  }
  
  /**
   * Returns the x-position of this thing in the display window
   * 
   * @return the X coordinate of the thing position
   */
  public float getX() {
    return x;
  }
  
  /**
   * Returns the y-position of this thing in the display window
   * 
   * @return the y-position of the thing
   */
  public float getY() {
    return y;
  }
  
  /**
   * Sets the x-position of this thing in the display window
   * 
   * @param x x - the x-position to set
   */
  public void setX(float x) {
    this.x = x;
  }
  
  /**
   * Sets the y-position of this thing in the display window
   * 
   * @param y y - the y-position to set
   */
  public void setY(float y) {
    this.y = y;
  }
  
}
