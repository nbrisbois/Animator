package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

/**
 * The interface of the shape.
 */
public interface IShape {

  String render();

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
  int getStartTik();

  /**
   * Change the starting tick of the shape.
   *
   * @param t an int representing the new tick
   */
  void changeTik(int t);

  /**
   * Getter for the Overlap priority of the shape.
   *
   * @return unique integer
   */
  int getPriority();

  /**
   * Adding a motion to this shape.
   *
   * @param m a motion representing the new motion being added
   */
  void addMotion(Motion m);

  /**
   * Applies the changes a motion would do to the shape.
   *
   * @param motionIndex an integer representing the index of the motion we want to execute
   * @return the Shape after undergoing a motion
   */
  IShape executeMotion(int motionIndex);

  /**
   * Make a copy of the current shape.
   *
   * @return a shape representing the copy of this shape
   */
  IShape copy();
}