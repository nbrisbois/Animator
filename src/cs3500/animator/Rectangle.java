package cs3500.animator;

import java.awt.Color;
import java.awt.geom.Point2D.Double;
import java.util.List;
import java.util.Queue;

/**
 * Rectangle Specific Implementation of a Shape Class.
 */
public class Rectangle extends Shape {

  /**
   * Constructor for a rectangle Shape.
   *
   * @param pos   The spawn position of the Shape
   * @param h     Height of the Rectangle
   * @param w     Width of the Rectangle
   * @param color The color of the Rectangle
   * @param startTick The start tick of the Polygon. This is where the shape will be rendered on the
   *                  initially on the Screen
   * @param motions   A list of motions detailing how the shape will move as time goes on
   * @throws NullPointerException     A NullPointerException is thrown when a null Object argument
   *                                  is provided
   * @throws IllegalArgumentException An IllegalArgumentException is thrown when the arguments are
   *                                  invalid
   */
  public Rectangle(Double pos, double h, double w, Color color, int startTick, Queue<Motion> motions)
      throws NullPointerException, IllegalArgumentException {
    super(pos, h, w, color, startTick, motions);
  }

  /**
   * Displaying all the motions this shape would go through.
   *
   * @return a string showing what motions the shape would go through
   */
  @Override
  public String render() {
    return "";
  }

  /**
   * Make a copy of the Rectangle.
   *
   * @return a new Rectangle
   */
  @Override
  public IShape copy() {
    return new Rectangle(this.position, this.dimensions[0], this.dimensions[1],
        this.color, this.startTick, this.motions);
  }

}