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
import java.util.Random;
import processing.core.PImage;
import java.util.ArrayList;
/**
 * This is the main class for the p03 Dancing Bangers II program
 *
 */
public class DancingBadgers {

  private static PImage backgroundImage; // backgound image
  private static ArrayList<Badger> badgers;// array storing badger objects
  private static Random randGen; // Generator of random numbers
  private static int badgersCountMax; 
  private static ArrayList<Thing> things; //ArrayList storing Thing objects
  private static ArrayList<StarshipRobot> robots; //Arraylist storing StarshipRobot objects
  
  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    Utility.runApplication();
  }

  /**
   * Defines initial environment properties of this graphic application
   */
  public static void setup() {
    badgersCountMax = 5;
    backgroundImage = Utility.loadImage("images" + File.separator + "background.png");
    badgers = new ArrayList<Badger>();
    randGen = new Random();
    
    Thing.setProcessing();
    things = new ArrayList<Thing>();
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));
    
    StarshipRobot.setProcessing();
    robots = new ArrayList<StarshipRobot>();
    robots.add(new StarshipRobot(things.get(2), things.get(0), 3));
    robots.add(new StarshipRobot(things.get(3), things.get(1), 3));
  }


  /**
   * Callback method that draws and updates the application display window. This method runs in an
   * infinite loop until the program exits.
   */
  public static void draw() {
    Utility.background(Utility.color(255, 218, 185));
    Utility.image(backgroundImage, Utility.width() / 2, Utility.height() / 2);
    for(Thing thing: things) {
      if(thing != null) {
        thing.draw();
      }
    }
    
    for(Badger badger: badgers) {
      if(badger != null) {
        badger.draw();
      }
    }
    
    for(StarshipRobot robot : robots) {
      if(robot != null) {
        robot.draw();
      }
    }
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  public static void mousePressed() {
    for (Badger badger : badgers) {
      if(badger.isMouseOver()) {
        badger.startDragging();
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  public static void mouseReleased() {
    for(Badger badger : badgers) {
      if(badger != null) {
        badger.stopDragging();
      }
    }
  }


  /**
   * Callback method called each time the user presses a key
   */
  public static void keyPressed() {

    switch (Character.toUpperCase(Utility.key())) {
      case 'B': // add new badgers as long as the maximum numbers of badgers allowed to be
          // present in the field is not reached
          if (badgers.size() < badgersCountMax) {
              badgers.add(new Badger(randGen.nextInt(Utility.width()), randGen.nextInt(Utility.height())));
          }
          break;
      case 'R': // delete the badger being pressed
          for (int i = 0; i < badgers.size(); i++) {
              if (badgers.get(i) != null && badgers.get(i).isMouseOver()) {
                  badgers.remove(i);
                  break;
              }
          }
          break;
    }
  }
  
}
