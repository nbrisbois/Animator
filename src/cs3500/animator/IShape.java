package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

public interface IShape {

  void render();

  /**
   * Changes the position of the same to the provided position
   * @param pos the position the shape will move to
   */
  void changePosition(Double pos) throws NullPointerException;

  /**
   * Getter to obtain the position of the shape
   * @return Point2D.Double representing the position
   */
  Double getPosition();

  /**
   * Changes the current size of the shape
   * @param size A two element array representing the new dimensions of the shape
   */
  void changeSize(double[] size) throws NullPointerException;

  /**
   * Getter to obtain the size dimensions of the shape
   * @return A two element array representing the dimensions of the shape
   */
  double[] getSize();

  /**
   * Changes the current color of the shape to the provided color
   * @param c New color of shape
   */
  void changeColor(Color c) throws NullPointerException;

  /**
   * Getter for the current shape color
   * @return current shape color
   */
  Color getColor();

  /**
   * Getter for the Overlap priority of the shape
   * @return unique integer
   */
  int getPriority();

}
