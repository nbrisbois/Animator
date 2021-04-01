package cs3500.animator.model;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Point2D.Double;
import java.util.Queue;

/**
 * The interface representing a certain shape.
 */
public interface IShape {

  /**
   * Get the name of the type of the shape.
   *
   * @return returns the rendered shape.
   */
  String getShape();

  /**
   * gets the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Changes the position of the same to the provided position.
   *
   * @param pos the position the shape will move to
   */
  void changePosition(Double pos) throws NullPointerException;

  /**
   * Getter to obtain the position of the shape.
   *
   * @return Point2D.Double representing the position
   */
  Double getPosition();

  /**
   * Changes the current size of the shape.
   *
   * @param size A two element array representing the new dimensions of the shape
   */
  void changeSize(double[] size) throws NullPointerException;

  /**
   * Getter to obtain the size dimensions of the shape.
   *
   * @return A two element array representing the dimensions of the shape
   */
  double[] getSize();

  /**
   * Changes the current color of the shape to the provided color.
   *
   * @param c New color of shape
   */
  void changeColor(Color c) throws NullPointerException;

  /**
   * Getter for the current shape color.
   *
   * @return current shape color
   */
  Color getColor();

  /**
   * Getter to obtain the tick this shape appears.
   *
   * @return a int representing the starting tick of the shape
   */
  long getStartTick();


  /**
   * Getter for the Overlap priority of the shape.
   *
   * @return unique integer
   */
  int getPriority();

  /**
   * Add a singular motion to the List of motions attached to the shape.
   *
   * @param m A Motion object representing the new motion being added.
   */
  void addMotion(Motion m);

  /**
   * A getter method to observe all the motions this shape goes through.
   *
   * @return a priority queue of motions that this shape will go through
   */
  Queue<Motion> getMotion();

  /**
   * Remove the last motion this shape will goes through.
   */
  void removeMotion();

  /**
   * Updates the shapes attributes based off of the current and next motion the shape is executing.
   *
   * @param currentTick an integer representing the tick we want to calculate the effects of the
   *                    motion at
   * @throws NullPointerException thrown if queue peek does not return a motion
   */
  void calculateMotion(long currentTick);

  /**
   * Make a copy of the current shape.
   *
   * @return a shape representing the copy of this shape
   */
  IShape copy();

  /**
   * Used for SVG View to generate the svg representation of the shape.
   *
   * @return A SVG representation of the Shape and it's motions
   */
  String generateSVG();

  /**
   * renders the shape.
   *
   * @return returns the rendered shape
   */
  Shape render();

  /**
   * Gets the type of the shape in SVG style.
   *
   * @return String representation of the shape
   */
  String getType();

  /**
   * Gets the SVG attributes needed for the shape.
   *
   * @return String[] of attributes
   */
  String[] getSVGAttributes();

  /**
   * Change the tick of the shape
   *
   * @param t int
   */
  void changeTick(int t);

  /**
   * gets the X offset.
   *
   * @return the x offset.
   */
  int getOffsetX();

  /**
   * gets the Y offset.
   *
   * @return the y offset.
   */
  int getOffsetY();

  void isVisual();
}