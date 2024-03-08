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

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;

/**
 * This Java class defines a graphic application called DancingBadgers that extends the PApplet class. 
 * The application includes features such as adding and removing Badger objects to a basketball court, 
 * rotating a basketball object, and performing a dance sequence with the Badger objects. 
 * 
 * @author Zhenghong Wang
 *
 */
public class DancingBadgers extends PApplet {

  //backgound image
  private static processing.core.PImage backgroundImage;
  //Maximum number of Badger objects allowed in the basketball court
  private static int badgersCountMax;
  //Tells whether the dance show is on. Initially,
  private boolean danceShowOn = false;
  //Generator of random numbers
  private static Random randGen;
  //arraylist storing Thing objects
  private static ArrayList<Thing> things = new ArrayList<>();
  
  //array storing badgers's dance show steps
  private static DanceStep[] badgersDanceSteps =
      new DanceStep[] {DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.DOWN,
          DanceStep.LEFT, DanceStep.RIGHT, DanceStep.RIGHT, DanceStep.LEFT, DanceStep.UP};
  
  //array storing the positions of the dancing badgers at the start of the dance show
  private static float[][] startDancePositions =
      new float[][] {{300, 250}, {364, 250}, {428, 250}, {492, 250}, {556, 250}};

  /**
   * Driver method to run this graphic application
   * 
   * @param args
   */
  public static void main(String[] args) {
    PApplet.main("DancingBadgers");
  }
  
  /**
   * Sets the size of the display window of this graphic application
   * 
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Gets the number of Badger objects present in the basketball arena
   * 
   * @return
   */
  public int badgersCount(){
    int record = 0;
    for (Thing thing:things){
      if(thing instanceof Badger){
        record += 1;
      }
    }
    return record;
  }
  
  /**
   * Sets the title and defines the initial environment properties of this graphic application. This method initializes all the data fields defined in this class.
   * 
   */
  @Override
  public void setup() {
    getSurface().setTitle("P5 Dancing Badgers");
    textAlign(3, 3);
    imageMode(3);
    focused = true;

    // Initialize random number generator
    randGen = new Random();
    // Load background image
    backgroundImage = loadImage("images" + File.separator + "background.png");
    // Set the max number of badgers
    badgersCountMax = 5;
    Thing.setProcessing(this);
    // Create Thing objects and add them to the ArrayList
    things.add(new Thing(50, 50, "target.png"));
    things.add(new Thing(750, 550, "target.png"));
    things.add(new Thing(750, 50, "shoppingCounter.png"));
    things.add(new Thing(50, 550, "shoppingCounter.png"));

    // create starship
    things.add(new StarshipRobot(things.get(2), things.get(0), 3));
    things.add(new StarshipRobot(things.get(3), things.get(1), 3));
    
    //Create basketball
    things.add(new Basketball(50, 300));
    things.add(new Basketball(750, 300));
  }

  /**
   * Callback method that draws and updates the application display window. This method runs in an infinite loop until the program exits.
   * 
   */
  @Override
  public void draw() {
    // Set background color
    background(255, 218, 185);

    // Draw the background image to the center of the screen
    image(backgroundImage, width / 2, height / 2);
    for (Thing thing : things) {
      thing.draw();
    }
  }

  /**
   * Callback method called each time the user presses the mouse.
   * This method iterates through the list of things. If the mouse is over a Clickable object, it calls its mousePressed method, then returns.
   * 
   */
  public void mousePressed() {
    for (Thing thing : things) {
      if(thing.isMouseOver()&& thing instanceof Basketball){
        ((Basketball) thing).mousePressed();
      }
      if (thing.isMouseOver() && thing instanceof Badger) {
          ((Badger) thing).mousePressed();

      }

    }
  }
  /**
   * Callback method called each time the mouse is released
   * This method calls the mouseReleased() method on every Clickable object stored in the things list.
   * 
   */
  public void mouseReleased() {

    for (Thing thing : things) {
      if (thing.isMouseOver() && thing instanceof Badger) {
        ((Badger) thing).stopDragging();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key.
   * 
   */
  public void keyPressed() {

    switch (Character.toUpperCase(key)) {
      //b-key: If the b-key is pressed and the danceShow is NOT on, a new badger is added to the list of things. Up to badgersCountMax can be added to the basketball arena.
      case 'B':
        if (!danceShowOn && this.badgersCount() < badgersCountMax) {
          things.add(
              new Badger(randGen.nextInt(width), randGen.nextInt(height), badgersDanceSteps));
          break;
        }
        break;

      //r-key: If the danceShow is NOT on and the r-key is pressed when the mouse is over a Badger object, this badger is removed from the list of things. If the mouse is over more than one badger, the badger at the lowest index position will be deleted.
      case 'R':
        for (Thing t : things) {
          if (!danceShowOn && t != null && t.isMouseOver()) {
            things.remove(t);
            break;
          }
        }
        break;
        
      //c-key: If the c-key is pressed, the danceShow is terminated (danceShowOn set to false), and ALL MovingThing objects are removed from the list of things. This key removes MovingThing objects ONLY.
      case 'C':
        if (!danceShowOn) {
          danceShowOn = false;
        }
        things.removeIf(thing -> thing instanceof MovingThing);
        break;

      /*
       * d-key: This key starts the dance show if the danceShowOn was false, and there is at least one Badger object in the basketball arena. 
       * Starting the dance show, sets the danceShowOn to true, sets the start dance positions of the Badger objects, 
       * and calls the startDancing() method on every Badger object present in the list of things. 
       * Pressing the d-key when the danceShowOn is true or when there are no Badger objects present in the basketball arena has no effect.
       * 
       */
      case 'D':
        // center the badgers
        int positionIndex = 0;
        for (int i = 0; i < things.size(); i++) {
          if (!danceShowOn && things.get(i) instanceof Badger) {
            float[] startPosition = startDancePositions[positionIndex];
            things.get(i).setX(startPosition[0]);
            things.get(i).setY(startPosition[1]);
            positionIndex++;
          }
        }
        if (!danceShowOn && badgersCount() > 0) {
          danceShowOn = true;
          for (Thing thing : things) {
            if (thing instanceof Badger) {
              Badger badger = (Badger) thing;
              badger.startDancing();
            }
          }
        }
        break;
      //s-key: If the s-key is pressed, the dancdShow is terminated and all the Badger objects stop dancing. Pressing the s-key does not remove any thing.
      case 'S':
        if (danceShowOn) {
          danceShowOn = false;
          for (Thing thing : things) {
            if (thing instanceof Badger) {
              Badger badger = (Badger) thing;
              badger.stopDancing();
            }
          }
        }
        break;
    }

  }

}
