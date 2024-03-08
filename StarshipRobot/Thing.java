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

import java.io.File;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The Thing class is a basic building block for graphical objects in a display window of a graphic application
 * 
 * @author xuzihan
 *
 */
public class Thing {
  //PApplet object that represents the display window of this graphic application
  protected static processing.core.PApplet processing;
  //image of this graphic thing of type PImage
  private processing.core.PImage image;
  
  protected float x; // x-position of this thing in the display window
  protected float y; // y-position of this thing in the display window
 
  /**
   * Creates a new graphic Thing located at a specific (x, y) position of the display window
   * 
   * @param x - x-position of this thing in the display window
   * @param y - y-position of this thing in the display window
   * @param imageFilename - filename of the image of this thing, for instance "name.png"
   * 
   */
  public Thing(float x, float y, String imageFilename) {
    this.image = processing.loadImage("images" + File.separator + imageFilename);
    this.x = x;
    this.y = y;
  }
  
  /**
   * Draws this thing to the display window at its current (x,y) position
   * 
   */
  public void draw() {
    processing.image(this.image, this.x, this.y);
  }

  /**
   * Sets the PApplet object display window where this Thing object will be drawn
   * 
   * @param processing - PApplet object that represents the display window
   * 
   */
  public static void setProcessing(PApplet processing) {
    Thing.processing = processing;
  }

  /**
   * Returns a reference to the image of this thing
   * 
   * @return the image of type PImage of the thing object
   * 
   */
  public PImage image() {
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
  
  /**
   * Checks if the mouse is over this Thing object
   * 
   * @return true if the mouse is over this Thing, otherwise returns false.
   * 
   */
  public boolean isMouseOver() {
    float imageWidth = image.width;
    float imageHeight = image.height;
    float mouseX = processing.mouseX;
    float mouseY = processing.mouseY;

    return mouseX >= x - imageWidth / 2 && mouseX <= x + imageWidth / 2
        && mouseY >= y - imageHeight / 2 && mouseY <= y + imageHeight / 2;
  }
}
