package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;

/**
 * Rectangle Specific Implementation of a Shape Class.
 */
public class Rectangle extends Shape {

  /**
   * Constructor for a rectangle Shape.
   * @param pos   The spawn position of the Shape
   * @param h     Height of the Rectangle
   * @param w     Width of the Rectangle
   * @param color The color of the Rectangle
   * @param order The order priority number of the Rectangle Shape in respect to all other Shapes
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Rectangle(Double pos, double h, double w, Color color,
      int order) throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, order);
  }

  @Override
  public void render() {
    // Not sure what to do here
  }

  @Override
  public void changeSize(int size) {

  }

}
